package edu.iit.sat.itmd4515.uid.uidlab2.db;

import edu.iit.sat.itmd4515.uid.uidlab2.db.schemas.Payment;
import edu.iit.sat.itmd4515.uid.uidlab2.db.schemas.Rental;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * {@link Payment} Data Access Object.
 */
public final class Payments {
    private Payments() {}

    public static List<Payment> selectByRentalId(Session session, Rental rental) {
        return session
            .createQuery("FROM Payment WHERE rental= :rental", Payment.class)
            .setParameter("rental", rental)
            .getResultList();
    }

    public static Payment insert(Session session, BigDecimal amount, Rental rental) {
        Payment payment = new Payment();
        payment.setCustomer(rental.getCustomer());
        payment.setStaff(rental.getStaff());
        payment.setRental(rental);
        payment.setAmount(amount);
        payment.setPaymentDate(new Date());

        Transaction transaction = session.beginTransaction();
        session.persist(payment);
        transaction.commit();
        return payment;
    }

    public static void remove(Session session, Payment payment) {
        Transaction transaction = session.beginTransaction();
        session.remove(payment);
        transaction.commit();
    }
}
