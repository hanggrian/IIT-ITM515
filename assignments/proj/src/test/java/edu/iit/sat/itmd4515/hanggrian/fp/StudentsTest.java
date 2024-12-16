package edu.iit.sat.itmd4515.hanggrian.fp;

import edu.iit.sat.itmd4515.hanggrian.fp.db.Students;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;

public class StudentsTest extends DaoTest {
    private Students students;

    @BeforeEach
    public void createDao() {
        students = new Students();
        students.manager = entityManager;
    }

    @Test
    public void validDao() {
        if (students.isEmpty()) {
            return;
        }

        assertThat(students.hasStudentId(SingletonStartup.ROLE_ADMIN))
            .isTrue();
        assertThat(students.selectAllExceptAdmin().size())
            .isEqualTo(SingletonStartup.STUDENT_COUNT);
    }
}
