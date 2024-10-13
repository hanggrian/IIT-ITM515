package edu.iit.sat.itmd4515.hanggrian.fp.db;

import edu.iit.sat.itmd4515.hanggrian.fp.db.schemas.Train;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.util.List;

/**
 * {@link Train} Data Access Object.
 */
public final class Trains {
    private Trains() {}

    public static List<Train> selectAll(EntityManager manager) {
        return manager
            .createQuery("FROM Train", Train.class)
            .getResultList();
    }

    public static void insert(EntityManager manager, Train train) {
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();
        manager.persist(train);
        transaction.commit();
    }

    public static void updateSerial(EntityManager manager, Train train, String serial) {
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();
        train.setLocomotiveSerial(serial);
        transaction.commit();
    }

    public static void delete(EntityManager manager, Train train) {
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();
        manager.remove(train);
        transaction.commit();
    }
}
