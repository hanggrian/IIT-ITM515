package edu.iit.sat.itmd4515.hanggrian.fp.db;

import edu.iit.sat.itmd4515.hanggrian.fp.db.schemas.Train;
import edu.iit.sat.itmd4515.hanggrian.fp.db.schemas.TrainCar;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.util.List;

/**
 * {@link TrainCar} Data Access Object.
 */
public final class TrainCars {
    private TrainCars() {}

    public static List<TrainCar> selectByTrain(EntityManager manager, Train train) {
        return manager
            .createQuery("FROM TrainCar WHERE train= :train", TrainCar.class)
            .setParameter("train", train)
            .getResultList();
    }

    public static void insert(EntityManager manager, TrainCar trainCar) {
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();
        manager.persist(trainCar);
        transaction.commit();
    }

    public static void delete(EntityManager manager, TrainCar trainCar) {
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();
        manager.remove(trainCar);
        transaction.commit();
    }
}
