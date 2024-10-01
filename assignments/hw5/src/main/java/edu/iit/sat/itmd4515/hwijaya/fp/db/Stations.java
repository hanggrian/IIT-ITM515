package edu.iit.sat.itmd4515.hwijaya.fp.db;

import edu.iit.sat.itmd4515.hwijaya.fp.db.schemas.Station;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.util.List;

/**
 * {@link Station} Data Access Object.
 */
public final class Stations {
    private Stations() {}

    public static List<Station> selectAll(EntityManager manager) {
        return manager
            .createQuery("FROM Station", Station.class)
            .getResultList();
    }

    public static void insert(EntityManager manager, Station station) {
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();
        manager.persist(station);
        transaction.commit();
    }

    public static void update(
        EntityManager manager,
        Station station,
        boolean hasElevator,
        boolean hasParking
    ) {
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();
        station.setHasElevator(hasElevator);
        station.setHasParking(hasParking);
        transaction.commit();
    }

    public static void delete(EntityManager manager, Station station) {
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();
        manager.remove(station);
        transaction.commit();
    }
}
