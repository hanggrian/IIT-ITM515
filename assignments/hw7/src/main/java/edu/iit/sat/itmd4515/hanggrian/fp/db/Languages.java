package edu.iit.sat.itmd4515.hanggrian.fp.db;

import edu.iit.sat.itmd4515.hanggrian.fp.db.schemas.Language;
import jakarta.annotation.sql.DataSourceDefinition;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

/**
 * {@link Language} Data Access Object.
 */
@DataSourceDefinition(
    name = "java:app/jdbc/sakila",
    className = "com.mysql.cj.jdbc.MysqlDataSource",
    serverName = "localhost",
    portNumber = 3306,
    databaseName = "sakila",
    user = "itmd4515",
    password = "itmd4515",
    properties = {
        "useSSL=false",
        "allowPublicKeyRetrieval=true"
    }
)
@Stateless
public class Languages {
    @PersistenceContext(unitName = "sakila") public EntityManager manager;

    public boolean isEmpty() {
        return manager
            .createQuery("SELECT 1 FROM Language l")
            .setMaxResults(1)
            .getResultList()
            .isEmpty();
    }

    public Language selectByName(String name) {
        return manager
            .createQuery("FROM Language language WHERE LOWER(language.name)= :name", Language.class)
            .setParameter("name", name.toLowerCase())
            .getSingleResult();
    }

    @Transactional
    public void insert(Language language) {
        manager.persist(language);
    }
}
