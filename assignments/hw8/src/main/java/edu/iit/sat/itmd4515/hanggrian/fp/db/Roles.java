package edu.iit.sat.itmd4515.hanggrian.fp.db;

import edu.iit.sat.itmd4515.hanggrian.fp.db.schemas.Role;
import jakarta.annotation.sql.DataSourceDefinition;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

/**
 * {@link Role} Data Access Object.
 */
@DataSourceDefinition(
    name = "java:app/jdbc/itmd4515",
    className = "com.mysql.cj.jdbc.MysqlDataSource",
    serverName = "localhost",
    portNumber = 3306,
    databaseName = "itmd4515",
    user = "itmd4515",
    password = "itmd4515",
    properties = {
        "useSSL=false",
        "allowPublicKeyRetrieval=true"
    }
)
@Stateless
public class Roles {
    @PersistenceContext(unitName = "itmd4515") public EntityManager manager;

    public boolean isEmpty() {
        return manager
            .createQuery("SELECT 1 FROM Role r")
            .setMaxResults(1)
            .getResultList()
            .isEmpty();
    }

    public Role selectByTitle(String title) {
        return manager.find(Role.class, title);
    }

    @Transactional
    public void insert(Role role) {
        manager.persist(role);
    }
}
