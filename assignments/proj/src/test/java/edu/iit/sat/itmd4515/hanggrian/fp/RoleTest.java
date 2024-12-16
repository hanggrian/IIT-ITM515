package edu.iit.sat.itmd4515.hanggrian.fp;

import edu.iit.sat.itmd4515.hanggrian.fp.db.schemas.Role;
import jakarta.validation.ConstraintViolation;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;

public class RoleTest extends PojoTest {
    @Test
    public void invalid() {
        Role role = new Role();
        role.setRoleTitle(stringSized(16));

        assertThat(validator.validate(role).stream().map(ConstraintViolation::getMessage))
            .containsExactly("size must be between 0 and 15");
    }

    @Test
    public void validPojo() {
        Role role = new Role();
        role.setRoleTitle("");

        assertThat(validator.validate(role)).isEmpty();
    }
}
