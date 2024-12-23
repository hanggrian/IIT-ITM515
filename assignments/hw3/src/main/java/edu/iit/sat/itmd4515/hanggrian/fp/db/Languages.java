package edu.iit.sat.itmd4515.hanggrian.fp.db;

import edu.iit.sat.itmd4515.hanggrian.fp.db.schemas.Language;
import jakarta.persistence.EntityManager;

public final class Languages {
    private Languages() {}

    public static Language selectOneByName(EntityManager manager, String name) {
        return manager
            .createQuery("FROM Language WHERE LOWER(name) = :name", Language.class)
            .setParameter("name", name.toLowerCase())
            .getSingleResult();
    }
}
