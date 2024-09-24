package edu.iit.sat.itmd4515.uid.uidlab4;

import edu.iit.sat.itmd4515.uid.uidlab4.db.schemas.Station;
import jakarta.validation.ConstraintViolation;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;

public class StationTest extends DatabaseTest {
    @Test
    public void invalid() {
        Station station = new Station();
        station.setStationName(stringSized(51));
        station.setAddress(stringSized(201));

        assertThat(validator.validate(station).stream().map(ConstraintViolation::getMessage))
            .containsExactly(
                "size must be between 0 and 50",
                "size must be between 0 and 200",
                "must not be null",
                "must not be null",
                "must not be null",
                "must not be null"
            );
    }

    @Test
    public void valid() {
        Station station = new Station();
        station.setStationName("");
        station.setAddress("");
        station.setLat(BigDecimal.ZERO);
        station.setLng(BigDecimal.ZERO);
        station.setHasParking(false);
        station.setHasElevator(false);

        assertThat(validator.validate(station)).isEmpty();
    }
}
