package edu.iit.sat.itmd4515.hanggrian.fp;

import edu.iit.sat.itmd4515.hanggrian.fp.db.schemas.Department;
import jakarta.validation.ConstraintViolation;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;

public class DepartmentTest extends PojoTest {
    @Test
    public void invalid() {
        Department department = new Department();
        department.setDepartmentName(stringSized(51));
        department.setWebsite(stringSized(255));
        department.setEmail(stringSized(255));
        department.setBuilding(stringSized(101));

        assertThat(validator.validate(department).stream().map(ConstraintViolation::getMessage))
            .containsExactly(
                "size must be between 0 and 50",
                "size must be between 0 and 254",
                "size must be between 0 and 254",
                "must not be null",
                "size must be between 0 and 100"
            );
    }

    @Test
    public void validPojo() {
        Department department = new Department();
        department.setDepartmentName("");
        department.setWebsite("");
        department.setEmail("");
        department.setPhoneNumber("");
        department.setBuilding("");

        assertThat(validator.validate(department)).isEmpty();
    }
}
