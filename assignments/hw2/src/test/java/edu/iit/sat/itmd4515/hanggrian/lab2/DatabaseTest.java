package edu.iit.sat.itmd4515.hanggrian.lab2;

import edu.iit.sat.itmd4515.hanggrian.lab2.db.Databases;
import jakarta.validation.Validator;
import org.hibernate.Session;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class DatabaseTest {
    protected Validator validator;
    protected Session session;

    @BeforeEach
    public void setup() {
        session = Databases.openSession();
        validator = Databases.getValidator();
    }

    @AfterEach
    protected void finish() {
        session.close();
    }

    @AfterAll
    protected static void finishAll() {
        Databases.close();
    }

    protected String stringSized(int length) {
        return " ".repeat(Math.max(0, length));
    }
}
