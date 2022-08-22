package guru.springframework.sfgrestbrewery.web.controller;

import guru.springframework.sfgrestbrewery.bootstrap.BeerLoader;
import guru.springframework.sfgrestbrewery.services.BeerService;
import guru.springframework.sfgrestbrewery.web.model.BeerDto;
import guru.springframework.sfgrestbrewery.web.model.BeerPagedList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@WebFluxTest(BeerController.class)
public class BeerControllerTest {

    @Autowired
    WebTestClient webTestClient;

    @MockBean
    BeerService beerService;

    BeerDto validBeer;

    @BeforeEach
    public void setUp() {
        validBeer = BeerDto.builder().id(1)
                .beerName("Test beer")
                .beerStyle("PALE_ALE")
                .upc(BeerLoader.BEER_1_UPC)
                .build();
    }

    @Test
    void getBeerById() {
        UUID beerId = UUID.randomUUID();
        given(beerService.getById(any(), any())).willReturn(validBeer);

        webTestClient.get()
                .uri("/api/v1/beer/"+beerId)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(BeerDto.class)
                .value(BeerDto::getBeerName, equalTo(validBeer.getBeerName()));
    }

    @Test
    void listBeers() {
        BeerPagedList beerPagedList = new BeerPagedList(
                List.of(validBeer)
        );
        given(beerService.listBeers(any(), any(), any(), any())).willReturn(beerPagedList);

        webTestClient.get()
                .uri("/api/v1/beer")
                .accept(MediaType.APPLICATION_JSON)
                .exchange().expectStatus().isOk()
                .expectBody(BeerPagedList.class)
                .value(beerDtos -> beerDtos.getSize(), equalTo(beerPagedList.getContent().size()));
    }

    @Test
    void getBeerByUpc() {
        String upc = BeerLoader.BEER_1_UPC;
        given(beerService.getByUpc(any())).willReturn(validBeer);

        webTestClient.get()
                .uri("/api/v1/beerUpc/"+upc)
                .accept(MediaType.APPLICATION_JSON)
                .exchange().expectStatus().isOk()
                .expectBody(BeerDto.class)
                .value(BeerDto::getUpc, equalTo(BeerLoader.BEER_1_UPC));

    }
}