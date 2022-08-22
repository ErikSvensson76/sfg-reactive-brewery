package guru.springframework.sfgrestbrewery.domain;

import guru.springframework.sfgrestbrewery.web.model.BeerStyleEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by jt on 2019-05-25.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "beer")
public class Beer{

    @Id
    @Column(value = "ID")
    private Integer id;
    @Column(value = "version")
    private Long version;
    @Column(value = "beer_name")
    private String beerName;
    @Column(value = "beer_style")
    private BeerStyleEnum beerStyle;
    @Column(value = "upc")
    private String upc;
    @Column(value = "quantity_on_hand")
    private Integer quantityOnHand;
    @Column(value = "price")
    private BigDecimal price;
    @Column(value = "created_date")
    private LocalDateTime createdDate;
    @Column(value = "last_modified_date")
    private LocalDateTime lastModifiedDate;


}
