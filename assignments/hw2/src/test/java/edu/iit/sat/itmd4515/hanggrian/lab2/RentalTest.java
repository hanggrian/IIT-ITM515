package edu.iit.sat.itmd4515.hanggrian.lab2;

import edu.iit.sat.itmd4515.hanggrian.lab2.db.Rentals;
import edu.iit.sat.itmd4515.hanggrian.lab2.db.schemas.Customer;
import edu.iit.sat.itmd4515.hanggrian.lab2.db.schemas.Inventory;
import edu.iit.sat.itmd4515.hanggrian.lab2.db.schemas.Rental;
import edu.iit.sat.itmd4515.hanggrian.lab2.db.schemas.Staff;
import jakarta.validation.ConstraintViolation;
import java.util.Date;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;

public class RentalTest extends DatabaseTest {
    @Test
    public void invalidPojo() {
        Rental rental = new Rental();

        assertThat(validator.validate(rental).stream().map(ConstraintViolation::getMessage))
            .containsExactly(
                "must not be null",
                "must not be null",
                "must not be null",
                "must not be null"
            );
    }

    @Test
    public void validPojo() {
        Rental rental = new Rental();
        rental.setRentalDate(new Date());
        rental.setInventory(new Inventory());
        rental.setCustomer(new Customer());
        rental.setStaff(new Staff());

        assertThat(validator.validate(rental)).isEmpty();
    }

    @Test
    public void validDao() {
        assertThat(Rentals.selectById(session, 1)).hasSize(1);

        assertThat(Rentals.selectByDate(session, SakilaApp.EARLIEST_RENTAL_DATE)).isNotEmpty();
    }
}
