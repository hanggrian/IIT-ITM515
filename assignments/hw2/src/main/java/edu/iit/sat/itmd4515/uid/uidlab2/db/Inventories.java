package edu.iit.sat.itmd4515.uid.uidlab2.db;

import edu.iit.sat.itmd4515.uid.uidlab2.db.schemas.Film;
import edu.iit.sat.itmd4515.uid.uidlab2.db.schemas.Inventory;
import edu.iit.sat.itmd4515.uid.uidlab2.db.schemas.Store;
import org.hibernate.Session;

/**
 * {@link Inventory} Data Access Object.
 */
public final class Inventories {
    private Inventories() {}

    public static Inventory selectOneByFilm(Session session, Store store, Film film) {
        return session
            .createQuery(
                "FROM Inventory WHERE store= :store "
                    + "AND film= :film",
                Inventory.class)
            .setParameter("store", store)
            .setParameter("film", film)
            .getResultList()
            .get(0);
    }
}
