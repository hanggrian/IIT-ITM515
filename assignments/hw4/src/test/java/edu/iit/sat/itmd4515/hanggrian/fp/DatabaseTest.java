package edu.iit.sat.itmd4515.hanggrian.fp;

import edu.iit.sat.itmd4515.hanggrian.fp.db.Databases;
import jakarta.persistence.EntityManager;
import jakarta.validation.Validator;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public abstract class DatabaseTest {
    protected Validator validator;
    protected EntityManager entityManager;

    @BeforeEach
    public void setup() {
        entityManager = Databases.openSession();
        validator = Databases.getValidator();
    }

    @AfterEach
    protected void finish() {
        entityManager.close();
    }

    @AfterAll
    protected static void finishAll() {
        Databases.close();
    }

    protected String stringSized(int length) {
        return " ".repeat(Math.max(0, length));
    }
}
