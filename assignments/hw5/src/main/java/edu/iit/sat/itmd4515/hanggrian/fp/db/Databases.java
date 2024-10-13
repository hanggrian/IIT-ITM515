package edu.iit.sat.itmd4515.hanggrian.fp.db;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

/**
 * Hibernate utility class to create entity manager and validator instances.
 */
public final class Databases {
    private static EntityManagerFactory entityManagerFactory;
    private static ValidatorFactory validatorFactory;

    private static final String PERSISTENCE_UNIT = "cta";

    private Databases() {}

    /**
     * Returns a session from existing or newly-created factory.
     */
    public static EntityManager openSession() {
        if (entityManagerFactory != null) {
            return entityManagerFactory.createEntityManager();
        }
        entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
        return entityManagerFactory.createEntityManager();
    }

    /**
     * Returns a validator from existing or newly-created factory.
     */
    public static Validator getValidator() {
        if (validatorFactory != null) {
            return validatorFactory.getValidator();
        }
        validatorFactory = Validation.buildDefaultValidatorFactory();
        return validatorFactory.getValidator();
    }

    /**
     * Release database instances.
     */
    public static void close() {
        if (entityManagerFactory != null) {
            entityManagerFactory.close();
            entityManagerFactory = null;
        }
        if (validatorFactory == null) {
            return;
        }
        validatorFactory.close();
        validatorFactory = null;
    }
}
