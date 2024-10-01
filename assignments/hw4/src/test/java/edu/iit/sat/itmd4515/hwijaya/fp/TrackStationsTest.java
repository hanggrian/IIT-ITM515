package edu.iit.sat.itmd4515.hwijaya.fp;

import edu.iit.sat.itmd4515.hwijaya.fp.db.Stations;
import edu.iit.sat.itmd4515.hwijaya.fp.db.TrackStations;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;

public class TrackStationsTest extends DatabaseTest {
    @Test
    public void read() {
        assertThat(
            TrackStations
                .selectByStation(entityManager, Stations.selectAll(entityManager).get(0))
        ).isNotEmpty();
    }
}
