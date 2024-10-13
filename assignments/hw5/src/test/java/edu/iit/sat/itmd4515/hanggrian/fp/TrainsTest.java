package edu.iit.sat.itmd4515.hanggrian.fp;

import edu.iit.sat.itmd4515.hanggrian.fp.db.Tracks;
import edu.iit.sat.itmd4515.hanggrian.fp.db.Trains;
import edu.iit.sat.itmd4515.hanggrian.fp.db.schemas.Track;
import edu.iit.sat.itmd4515.hanggrian.fp.db.schemas.Train;
import java.time.Year;
import java.util.List;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;

public class TrainsTest extends DatabaseTest {
    @Test
    public void createAndDelete() {
        List<Track> tracks = Tracks.selectAll(entityManager);
        assertThat(tracks).isNotEmpty();

        Train train = new Train();
        train.setLocomotiveSerial("");
        train.setSince(Year.now());
        train.setTrack(tracks.get(0));
        Trains.insert(entityManager, train);
        assertThat(Trains.selectAll(entityManager))
            .contains(train);

        Trains.delete(entityManager, train);
        assertThat(Trains.selectAll(entityManager))
            .doesNotContain(train);
    }

    @Test
    public void read() {
        assertThat(Trains.selectAll(entityManager))
            .isNotEmpty();
    }

    @Test
    public void update() {
        Trains.updateSerial(entityManager, Trains.selectAll(entityManager).get(0), null);
        Train train = Trains.selectAll(entityManager).get(0);
        assertThat(train.getLocomotiveSerial())
            .isNull();
    }
}
