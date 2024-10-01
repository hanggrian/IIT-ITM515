package edu.iit.sat.itmd4515.hwijaya.fp.db;

import edu.iit.sat.itmd4515.hwijaya.fp.db.schemas.Film;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public final class Films {
    private Films() {}

    public static void insert(EntityManager manager, Film film) {
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();
        manager.persist(film);
        transaction.commit();
    }
}
