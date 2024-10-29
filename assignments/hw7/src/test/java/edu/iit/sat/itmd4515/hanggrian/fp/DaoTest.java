package edu.iit.sat.itmd4515.hanggrian.fp;

import edu.iit.sat.itmd4515.hanggrian.fp.db.Validators;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.validation.Validator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public abstract class DaoTest {
    private static EntityManagerFactory entityManagerFactory;

    private static final String PERSISTENCE_UNIT = "sakila";

    protected Validator validator;
    protected EntityManager entityManager;

    @BeforeEach
    public void setup() {
        validator = Validators.getValidator();

        if (entityManagerFactory != null) {
            entityManager = entityManagerFactory.createEntityManager();
        }
        entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
        entityManager = entityManagerFactory.createEntityManager();
    }

    @AfterEach
    public void finish() {
        entityManager.close();
        Validators.close();
    }

    protected void transaction(Runnable runnable) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        runnable.run();
        transaction.commit();
    }
}
