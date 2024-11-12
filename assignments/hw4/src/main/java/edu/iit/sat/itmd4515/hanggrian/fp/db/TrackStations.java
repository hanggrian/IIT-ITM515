package edu.iit.sat.itmd4515.hanggrian.fp.db;

import edu.iit.sat.itmd4515.hanggrian.fp.db.schemas.Station;
import edu.iit.sat.itmd4515.hanggrian.fp.db.schemas.TrackStation;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.util.List;

/**
 * {@link TrackStation} Data Access Object.
 */
public final class TrackStations {
    private TrackStations() {}

    public static List<TrackStation> selectByStation(EntityManager manager, Station station) {
        return manager
            .createQuery("FROM TrackStation WHERE station = :station", TrackStation.class)
            .setParameter("station", station)
            .getResultList();
    }

    public static void insert(EntityManager manager, TrackStation trackStation) {
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();
        manager.persist(trackStation);
        transaction.commit();
    }

    public static void delete(EntityManager manager, TrackStation trackStation) {
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();
        manager.remove(trackStation);
        transaction.commit();
    }
}
