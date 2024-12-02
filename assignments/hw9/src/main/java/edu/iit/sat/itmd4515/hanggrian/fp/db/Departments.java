package edu.iit.sat.itmd4515.hanggrian.fp.db;

import edu.iit.sat.itmd4515.hanggrian.fp.db.schemas.Department;
import jakarta.annotation.sql.DataSourceDefinition;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

/**
 * {@link Department} Data Access Object.
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
public class Departments {
    @PersistenceContext(unitName = "itmd4515") public EntityManager manager;

    public boolean isEmpty() {
        return manager
            .createQuery("FROM Department d")
            .setMaxResults(1)
            .getResultList()
            .isEmpty();
    }

    public Department find(String departmentName) {
        return manager.find(Department.class, departmentName);
    }

    @Transactional
    public void insert(Department department) {
        manager.persist(department);
    }
}
