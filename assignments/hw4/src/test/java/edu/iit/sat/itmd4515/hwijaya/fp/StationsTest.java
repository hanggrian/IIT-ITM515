package edu.iit.sat.itmd4515.hwijaya.fp;

import edu.iit.sat.itmd4515.hwijaya.fp.db.Stations;
import edu.iit.sat.itmd4515.hwijaya.fp.db.schemas.Station;
import java.time.Year;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;

public class StationsTest extends DatabaseTest {
    @Test
    public void createAndDelete() {
        Station station = new Station();
        station.setStationName("");
        station.setAddress("");
        station.setHasElevator(true);
        station.setHasParking(true);
        station.setSince(Year.now());
        Stations.insert(entityManager, station);
        assertThat(Stations.selectAll(entityManager))
            .contains(station);

        Stations.delete(entityManager, station);
        assertThat(Stations.selectAll(entityManager))
            .doesNotContain(station);
    }

    @Test
    public void read() {
        assertThat(Stations.selectAll(entityManager))
            .isNotEmpty();
    }

    @Test
    public void update() {
        Stations.update(entityManager, Stations.selectAll(entityManager).get(0), true, true);
        Station station = Stations.selectAll(entityManager).get(0);
        assertThat(station.getHasElevator())
            .isTrue();
        assertThat(station.getHasParking())
            .isTrue();
    }
}
