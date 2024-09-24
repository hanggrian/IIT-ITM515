package edu.iit.sat.itmd4515.uid.uidlab4;

import edu.iit.sat.itmd4515.uid.uidlab4.db.schemas.Track;
import jakarta.validation.ConstraintViolation;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;

public class TrackTest extends DatabaseTest {
    @Test
    public void invalid() {
        Track track = new Track();
        track.setTrackColor(stringSized(21));

        assertThat(validator.validate(track).stream().map(ConstraintViolation::getMessage))
            .containsExactly(
                "size must be between 0 and 20",
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
