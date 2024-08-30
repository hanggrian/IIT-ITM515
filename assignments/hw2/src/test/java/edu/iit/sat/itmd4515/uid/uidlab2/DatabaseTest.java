package edu.iit.sat.itmd4515.uid.uidlab2;

import edu.iit.sat.itmd4515.uid.uidlab2.db.Databases;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.hibernate.Session;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class DatabaseTest {
    protected Validator validator;
    protected Session session;

    @BeforeEach
    public void setup() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
        session = Databases.open();
    }

    @AfterEach
    protected void finish() {
        session.close();
    }

    protected String stringSized(int length) {
        return " ".repeat(Math.max(0, length));
    }
}
