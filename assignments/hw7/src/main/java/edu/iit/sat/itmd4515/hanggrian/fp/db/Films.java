package edu.iit.sat.itmd4515.hanggrian.fp.db;

import edu.iit.sat.itmd4515.hanggrian.fp.db.schemas.Film;
import jakarta.annotation.sql.DataSourceDefinition;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

/**
 * {@link Film} Data Access Object.
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
public class Films {
    @PersistenceContext(unitName = "sakila") public EntityManager manager;

    public Film selectLast() {
        return manager
            .createQuery("FROM Film ORDER BY filmId DESC", Film.class)
            .setMaxResults(1)
            .getSingleResult();
    }

    @Transactional
    public void insert(Film film) {
        manager.persist(film);
    }

    @Transactional
    public void delete(Film film) {
        manager.remove(film);
    }
}
