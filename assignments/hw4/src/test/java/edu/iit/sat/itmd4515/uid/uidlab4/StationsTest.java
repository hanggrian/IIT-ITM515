package edu.iit.sat.itmd4515.uid.uidlab4;

import edu.iit.sat.itmd4515.uid.uidlab4.db.Stations;
import edu.iit.sat.itmd4515.uid.uidlab4.db.schemas.Station;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;

public class StationsTest extends DatabaseTest {
    @Test
    public void createAndDelete() {
        Station station = new Station();
        station.setStationName("");
        station.setAddress("");
        station.setLat(BigDecimal.ZERO);
        station.setLng(BigDecimal.ZERO);
        station.setHasElevator(true);
        station.setHasParking(true);
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
        Station updatedStation = Stations.selectAll(entityManager).get(0);
        assertThat(updatedStation.getHasElevator())
            .isTrue();
        assertThat(updatedStation.getHasParking())
            .isTrue();
    }
}
