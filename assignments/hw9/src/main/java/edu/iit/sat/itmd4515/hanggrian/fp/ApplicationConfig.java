package edu.iit.sat.itmd4515.hanggrian.fp;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.security.enterprise.authentication.mechanism.http.BasicAuthenticationMechanismDefinition;
import jakarta.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;
import jakarta.security.enterprise.identitystore.Pbkdf2PasswordHash;

/**
 * Password hashing configuration, enabling injection to obfuscate secret keys.
 */
@DatabaseIdentityStoreDefinition(
    dataSourceLookup = "java:app/jdbc/itmd4515",
    callerQuery = "#{'select password from student where student_id = ?'}",
    groupsQuery = "select role_title from student_role where student_id = ?",
    hashAlgorithm = Pbkdf2PasswordHash.class,
    priorityExpression = "#{100}",
    hashAlgorithmParameters = {
        "Pbkdf2PasswordHash.Iterations=3072",
        "${applicationConfig.dyna}",
    }
)
@BasicAuthenticationMechanismDefinition(realmName="defaultRealm")
@ApplicationScoped
@Named
public class ApplicationConfig {
    public String[] getDyna() {
        return new String[]{
            "Pbkdf2PasswordHash.Algorithm=PBKDF2WithHmacSHA256",
            "Pbkdf2PasswordHash.SaltSizeBytes=64",
        };
    }
}
