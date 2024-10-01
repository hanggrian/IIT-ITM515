package edu.iit.sat.itmd4515.hwijaya.fp.db;

import edu.iit.sat.itmd4515.hwijaya.fp.db.schemas.Track;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.util.List;

/**
 * {@link Track} Data Access Object.
 */
public final class Tracks {
    private Tracks() {}

    public static List<Track> selectAll(EntityManager manager) {
        return manager
            .createQuery("FROM Track", Track.class)
            .getResultList();
    }

    public static void insert(EntityManager manager, Track station) {
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();
        manager.persist(station);
        transaction.commit();
    }

    public static void update24h(EntityManager manager, Track track) {
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();
        track.setIs24h(!track.getIs24h());
        transaction.commit();
    }

    public static void delete(EntityManager manager, Track track) {
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();
        manager.remove(track);
        transaction.commit();
    }
}
