package edu.iit.sat.itmd4515.hanggrian.fp;

import edu.iit.sat.itmd4515.hanggrian.fp.db.Departments;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;

public class DepartmentsTest extends DaoTest {
    private Departments departments;

    @BeforeEach
    public void createDao() {
        departments = new Departments();
        departments.manager = entityManager;
    }

    @Test
    public void validDao() {
        if (departments.isEmpty()) {
            return;
        }

        assertThat(departments.find("Applied Mathematics"))
            .isNotNull();
        assertThat(departments.find("Computer Science"))
            .isNotNull();
        assertThat(departments.find("Information Technology and Management"))
            .isNotNull();
    }
}
