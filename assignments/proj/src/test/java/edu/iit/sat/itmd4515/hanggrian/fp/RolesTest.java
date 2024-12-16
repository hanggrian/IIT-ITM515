package edu.iit.sat.itmd4515.hanggrian.fp;

import edu.iit.sat.itmd4515.hanggrian.fp.db.Roles;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;

public class RolesTest extends DaoTest {
    private Roles roles;

    @BeforeEach
    public void createDao() {
        roles = new Roles();
        roles.manager = entityManager;
    }

    @Test
    public void validDao() {
        if (roles.isEmpty()) {
            return;
        }

        assertThat(roles.selectByTitle("user").getRoleTitle())
            .isEqualTo("user");
    }
}
