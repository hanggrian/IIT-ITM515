package edu.iit.sat.itmd4515.uid.uidlab2.db;

import edu.iit.sat.itmd4515.uid.uidlab2.db.schemas.Film;
import edu.iit.sat.itmd4515.uid.uidlab2.db.schemas.Rental;
import java.time.LocalDate;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * {@link Rental} Data Access Object.
 */
public final class Rentals {
    private Rentals() {}

    public static List<Rental> selectById(Session session, int id) {
        return session
            .createQuery("FROM Rental WHERE id= :id", Rental.class)
            .setParameter("id", id)
            .getResultList();
    }

    public static List<Rental> selectByDate(Session session, LocalDate date) {
        return session
            .createQuery(
                "FROM Rental WHERE DATE(rentalDate)= :rental_date "
                    + "OR DATE(returnDate)= :return_date",
                Rental.class
            ).setParameter("rental_date", date)
            .setParameter("return_date", date)
            .getResultList();
    }

    public static void updateFilm(Session session, Rental rental, Film film) {
        Transaction transaction = session.beginTransaction();
        rental.setInventory(
            Inventories.selectOneByFilm(session, rental.getCustomer().getStore(), film)
        );
        transaction.commit();
    }
}
