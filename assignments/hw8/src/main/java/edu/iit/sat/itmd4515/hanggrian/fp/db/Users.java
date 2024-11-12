package edu.iit.sat.itmd4515.hanggrian.fp.db;

import edu.iit.sat.itmd4515.hanggrian.fp.db.schemas.User;
import jakarta.annotation.sql.DataSourceDefinition;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

/**
 * {@link User} Data Access Object.
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
public class Users {
    @PersistenceContext(unitName = "itmd4515") public EntityManager manager;

    public boolean hasUserId(String userId) {
        return !manager
            .createQuery("FROM User u WHERE u.userId = :userId", User.class)
            .setParameter("userId", userId)
            .setMaxResults(1)
            .getResultList()
            .isEmpty();
    }

    @Transactional
    public void insert(User user) {
        manager.persist(user);
    }
}
