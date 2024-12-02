package edu.iit.sat.itmd4515.hanggrian.fp;

import edu.iit.sat.itmd4515.hanggrian.fp.db.Roles;
import edu.iit.sat.itmd4515.hanggrian.fp.db.Users;
import edu.iit.sat.itmd4515.hanggrian.fp.db.schemas.Role;
import edu.iit.sat.itmd4515.hanggrian.fp.db.schemas.User;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.inject.Inject;
import jakarta.security.enterprise.identitystore.PasswordHash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The startup service to supply initial login credentials.
 */
@Singleton
@Startup
public class SingletonStartup {
    private static final String USER_ITMD4515 = "itmd4515";
    private static final String USER_ADMIN = "admin";

    private static final Logger LOGGER = LoggerFactory.getLogger(SingletonStartup.class);

    @EJB Roles roles;
    @EJB Users users;
    @Inject PasswordHash passwordHash;

    @PostConstruct
    public void initialize() {
        LOGGER.info("Initializing startup service...");

        Role userRole = new Role.Builder().roleTitle("user").build();
        Role adminRole = new Role.Builder().roleTitle("admin").build();
        if (roles.isEmpty()) {
            LOGGER.info("Empty roles, populating...");
            roles.insert(userRole);
            roles.insert(adminRole);
        }

        if (!users.hasUserId(USER_ITMD4515)) {
            LOGGER.info("Missing default user, inserting...");
            users.insert(
                new User.Builder()
                    .userId(USER_ITMD4515)
                    .email(USER_ITMD4515 + "@iit.edu")
                    .password(passwordHash.generate(USER_ITMD4515.toCharArray()))
                    .firstName("John")
                    .lastName("Doe")
                    .roles(userRole)
                    .build()
            );
        }
        if (users.hasUserId(USER_ADMIN)) {
            return;
        }
        LOGGER.info("Missing default admin, inserting...");
        users.insert(
            new User.Builder()
                .userId(USER_ADMIN)
                .email(USER_ADMIN + "@iit.edu")
                .password(passwordHash.generate(USER_ADMIN.toCharArray()))
                .roles(userRole, adminRole)
                .build()
        );

        LOGGER.info("Startup service finished.");
    }
}
