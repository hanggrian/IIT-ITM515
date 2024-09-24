package edu.iit.sat.itmd4515.uid.uidlab2;

import edu.iit.sat.itmd4515.uid.uidlab2.db.Databases;
import edu.iit.sat.itmd4515.uid.uidlab2.db.Payments;
import edu.iit.sat.itmd4515.uid.uidlab2.db.schemas.Customer;
import edu.iit.sat.itmd4515.uid.uidlab2.db.schemas.Inventory;
import edu.iit.sat.itmd4515.uid.uidlab2.db.schemas.Payment;
import edu.iit.sat.itmd4515.uid.uidlab2.db.schemas.Rental;
import edu.iit.sat.itmd4515.uid.uidlab2.db.schemas.Staff;
import jakarta.validation.ConstraintViolation;
import java.math.BigDecimal;
import java.util.Date;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;

public class PaymentTest extends DatabaseTest {
    @Test
    public void invalidPojo() {
        Inventory inventory = new Inventory();

        assertThat(validator.validate(inventory).stream().map(ConstraintViolation::getMessage))
            .containsExactly(
                "must not be null",
                "must not be null",
                "must not be null"
            );
    }

    @Test
    public void validPojo() {
        Payment payment = new Payment();
        payment.setPaymentId((short) 0);
        payment.setCustomer(new Customer());
        payment.setStaff(new Staff());
        payment.setAmount(BigDecimal.ZERO);
        payment.setPaymentDate(new Date());

        assertThat(validator.validate(payment)).isEmpty();
    }

    @Test
    public void validDao() {
        Session session = Databases.openSession();
        Rental rental =
            session
                .createQuery("FROM Rental WHERE id= 1", Rental.class)
                .getSingleResult();
        assertThat(Payments.selectByRentalId(session, rental)).isNotEmpty();
        session.close();
    }
}
