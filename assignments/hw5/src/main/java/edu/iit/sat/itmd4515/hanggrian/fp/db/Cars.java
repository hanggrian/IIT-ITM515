package edu.iit.sat.itmd4515.hanggrian.fp.db;

import edu.iit.sat.itmd4515.hanggrian.fp.db.schemas.Car;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.util.List;

/**
 * {@link Car} Data Access Object.
 */
public final class Cars {
    private Cars() {}

    public static List<Car> selectAll(EntityManager manager) {
        return manager
            .createQuery("FROM Car", Car.class)
            .getResultList();
    }

    public static void insert(EntityManager manager, Car car) {
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();
        manager.persist(car);
        transaction.commit();
    }

    public static void updateSeat(EntityManager manager, Car car, int seats) {
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();
        car.setSeats(seats);
        transaction.commit();
    }

    public static void delete(EntityManager manager, Car car) {
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();
        manager.remove(car);
        transaction.commit();
    }
}
