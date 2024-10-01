package edu.iit.sat.itmd4515.hwijaya.fp;

import edu.iit.sat.itmd4515.hwijaya.fp.db.schemas.Train;
import jakarta.validation.ConstraintViolation;
import java.time.Year;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;

public class TrainTest extends DatabaseTest {
    @Test
    public void invalid() {
        Train train = new Train();
        train.setLocomotiveSerial(stringSized(21));
        train.setTrackColor(stringSized(11));

        assertThat(validator.validate(train).stream().map(ConstraintViolation::getMessage))
            .containsExactly(
                "size must be between 0 and 20",
                "size must be between 0 and 10",
                "must not be null"
            );
    }

    @Test
    public void valid() {
        Train train = new Train();
        train.setLocomotiveSerial("");
        train.setSince(Year.now());
        train.setTrackColor("");

        assertThat(validator.validate(train)).isEmpty();
    }
}
