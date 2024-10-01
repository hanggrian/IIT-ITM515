package edu.iit.sat.itmd4515.hwijaya.fp;

import edu.iit.sat.itmd4515.hwijaya.fp.db.Cars;
import edu.iit.sat.itmd4515.hwijaya.fp.db.schemas.Car;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;

public class CarsTest extends DatabaseTest {
    @Test
    public void createAndDelete() {
        Car car = new Car();
        car.setCarNo("");
        car.setSeats(0);
        Cars.insert(entityManager, car);
        assertThat(Cars.selectAll(entityManager))
            .contains(car);

        Cars.delete(entityManager, car);
        assertThat(Cars.selectAll(entityManager))
            .doesNotContain(car);
    }

    @Test
    public void read() {
        assertThat(Cars.selectAll(entityManager))
            .isNotEmpty();
    }

    @Test
    public void update() {
        Cars.updateSeat(entityManager, Cars.selectAll(entityManager).get(0), 50);
        Car car = Cars.selectAll(entityManager).get(0);
        assertThat(car.getSeats())
            .isEqualTo(50);
    }
}
