package edu.iit.sat.itmd4515.hanggrian.fp;

import edu.iit.sat.itmd4515.hanggrian.fp.db.schemas.Track;
import edu.iit.sat.itmd4515.hanggrian.fp.db.schemas.Train;
import jakarta.validation.ConstraintViolation;
import java.time.Year;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;

public class TrainTest extends DatabaseTest {
    @Test
    public void invalid() {
        Train train = new Train();
        train.setLocomotiveSerial(stringSized(21));
        train.setTrack(new Track());

        assertThat(validator.validate(train).stream().map(ConstraintViolation::getMessage))
            .containsExactly(
                "size must be between 0 and 20",
                "must not be null"
            );
    }

    @Test
    public void valid() {
        Train train = new Train();
        train.setLocomotiveSerial("");
        train.setSince(Year.now());
        train.setTrack(new Track());

        assertThat(validator.validate(train)).isEmpty();
    }
}
