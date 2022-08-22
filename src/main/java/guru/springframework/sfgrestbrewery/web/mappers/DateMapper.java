package guru.springframework.sfgrestbrewery.web.mappers;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

/**
 * Created by jt on 2019-05-25.
 */
@Component
public class DateMapper {
    public OffsetDateTime asOffsetDateTime(LocalDateTime ts){

        OffsetDateTime offsetDateTime = null;
        if (ts != null){
            ZoneId zoneId = ZoneId.of("Europe/Berlin");
            ZoneOffset zoneOffset = zoneId.getRules().getOffset(ts);
            offsetDateTime = ts.atOffset(zoneOffset);
        }
        return offsetDateTime;
    }

    public LocalDateTime asTimestamp(OffsetDateTime offsetDateTime){
        LocalDateTime localDateTime = null;
        if(offsetDateTime != null) {
            localDateTime = offsetDateTime.toLocalDateTime();
        }
        return localDateTime;
    }
}
