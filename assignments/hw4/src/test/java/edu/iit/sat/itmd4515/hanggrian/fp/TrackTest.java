package edu.iit.sat.itmd4515.hanggrian.fp;

import edu.iit.sat.itmd4515.hanggrian.fp.db.schemas.Track;
import jakarta.validation.ConstraintViolation;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;

public class TrackTest extends DatabaseTest {
    @Test
    public void invalid() {
        Track track = new Track();
        track.setTrackColor(stringSized(11));

        assertThat(validator.validate(track).stream().map(ConstraintViolation::getMessage))
            .containsExactly(
                "size must be between 0 and 10",
                "must not be null"
            );
    }

    @Test
    public void valid() {
        Track track = new Track();
        track.setTrackColor("");
        track.setIs24h(false);

        assertThat(validator.validate(track)).isEmpty();
    }
}
