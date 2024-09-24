package edu.iit.sat.itmd4515.uid.uidlab4;

import edu.iit.sat.itmd4515.uid.uidlab4.db.Tracks;
import edu.iit.sat.itmd4515.uid.uidlab4.db.schemas.Track;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;

public class TracksTest extends DatabaseTest {
    @Test
    public void createAndDelete() {
        Track track = new Track();
        track.setTrackColor("");
        track.setIs24h(true);
        Tracks.insert(entityManager, track);
        assertThat(Tracks.selectAll(entityManager))
            .contains(track);

        Tracks.delete(entityManager, track);
        assertThat(Tracks.selectAll(entityManager))
            .doesNotContain(track);
    }

    @Test
    public void read() {
        assertThat(Tracks.selectAll(entityManager))
            .isNotEmpty();

        Track track = new Track();
        track.setTrackColor("");
        track.setIs24h(true);
        Tracks.insert(entityManager, track);
        assertThat(Tracks.selectAll(entityManager))
            .contains(track);

        Tracks.delete(entityManager, track);
        assertThat(Tracks.selectAll(entityManager))
            .doesNotContain(track);
    }
}
