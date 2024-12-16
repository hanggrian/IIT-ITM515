package edu.iit.sat.itmd4515.hanggrian.fp.db;

import edu.iit.sat.itmd4515.hanggrian.fp.db.schemas.Student;
import jakarta.annotation.sql.DataSourceDefinition;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;

/**
 * {@link Student} Data Access Object.
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
public class Students {
    @PersistenceContext(unitName = "itmd4515") public EntityManager manager;

    public boolean isEmpty() {
        return manager
            .createQuery("FROM Student s")
            .setMaxResults(1)
            .getResultList()
            .isEmpty();
    }

    public boolean hasStudentId(String studentId) {
        return !manager
            .createQuery("FROM Student s WHERE s.studentId = :studentId", Student.class)
            .setParameter("studentId", studentId)
            .setMaxResults(1)
            .getResultList()
            .isEmpty();
    }

    public List<Student> selectAllExceptAdmin() {
        return manager
            .createQuery("FROM Student s WHERE s.studentId != 'admin'", Student.class)
            .getResultList();
    }

    public Student getLatestExceptAdmin() {
        return manager
            .createQuery(
                "FROM Student s WHERE s.studentId != 'admin' ORDER BY s.createdAt DESC",
                Student.class
            ).setMaxResults(1)
            .getSingleResult();
    }

    public Student find(String studentId) {
        return manager.find(Student.class, studentId);
    }

    @Transactional
    public void insert(Student student) {
        manager.persist(student);
    }

    @Transactional
    public void update(Student student) {
        manager.merge(student);
    }

    @Transactional
    public void delete(Student student) {
        if (!manager.contains(student)) {
            student = manager.merge(student);
        }
        manager.remove(student);
    }
}
