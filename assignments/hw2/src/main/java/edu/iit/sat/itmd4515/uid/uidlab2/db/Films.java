package edu.iit.sat.itmd4515.uid.uidlab2.db;

import edu.iit.sat.itmd4515.uid.uidlab2.db.schemas.Film;
import java.util.List;
import org.hibernate.Session;

/**
 * {@link Film} Data Access Object.
 */
public final class Films {
    private Films() {}

    public static List<Film> selectByTitleUpToTen(Session session, String title) {
        return session
            .createQuery("FROM Film WHERE LOWER(title) LIKE LOWER(:title)", Film.class)
            .setParameter("title", '%' + title + '%')
            .setMaxResults(10)
            .getResultList();
    }
}
