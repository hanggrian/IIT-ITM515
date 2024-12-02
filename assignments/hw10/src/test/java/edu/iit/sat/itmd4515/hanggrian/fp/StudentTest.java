package edu.iit.sat.itmd4515.hanggrian.fp;

import edu.iit.sat.itmd4515.hanggrian.fp.db.schemas.Student;
import jakarta.validation.ConstraintViolation;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;

public class StudentTest extends PojoTest {
    @Test
    public void invalid() {
        Student student = new Student();
        student.setStudentId(stringSized(16));
        student.setEmail(stringSized(255));

        assertThat(validator.validate(student).stream().map(ConstraintViolation::getMessage))
            .containsExactly(
                "size must be between 0 and 15",
                "size must be between 0 and 254",
                "must not be null"
            );
    }

    @Test
    public void validPojo() {
        Student student = new Student();
        student.setStudentId("");
        student.setEmail("");
        student.setPassword("");

        assertThat(validator.validate(student)).isEmpty();
    }
}
