package edu.iit.sat.itmd4515.hanggrian.fp;

import edu.iit.sat.itmd4515.hanggrian.fp.db.schemas.Car;
import jakarta.validation.ConstraintViolation;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;

public class CarTest extends DatabaseTest {
    @Test
    public void invalid() {
        Car car = new Car();
        car.setCarNo(stringSized(5));

        assertThat(validator.validate(car).stream().map(ConstraintViolation::getMessage))
            .containsExactly(
                "size must be between 0 and 4",
                "must not be null"
            );
    }

    @Test
    public void valid() {
        Car car = new Car();
        car.setCarNo("");
        car.setSeats(0);

        assertThat(validator.validate(car)).isEmpty();
    }
}
