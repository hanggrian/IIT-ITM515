package edu.iit.sat.itmd4515.hanggrian.fp.db;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

/**
 * Hibernate utility class to create validator instances.
 */
public final class Validators {
    private static ValidatorFactory validatorFactory;

    private Validators() {}

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
        if (validatorFactory == null) {
            return;
        }
        validatorFactory.close();
        validatorFactory = null;
    }
}
