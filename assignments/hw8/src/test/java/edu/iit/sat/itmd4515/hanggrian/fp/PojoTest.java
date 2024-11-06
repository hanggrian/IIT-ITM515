package edu.iit.sat.itmd4515.hanggrian.fp;

import edu.iit.sat.itmd4515.hanggrian.fp.db.Validators;
import jakarta.validation.Validator;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;

public abstract class PojoTest {
    protected Validator validator;

    @BeforeEach
    public void setup() {
        validator = Validators.getValidator();
    }

    @AfterAll
    protected static void finishAll() {
        Validators.close();
    }

    protected String stringSized(int length) {
        return " ".repeat(Math.max(0, length));
    }
}
