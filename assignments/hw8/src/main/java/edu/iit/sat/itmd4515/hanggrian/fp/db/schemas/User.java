package edu.iit.sat.itmd4515.hanggrian.fp.db.schemas;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
public class User {
    @Id
    @Column(name = "user_id", nullable = false, length = 15)
    @NotNull
    @Size(max = 15)
    private String userId;

    @Column(name = "email", nullable = false, length = 254)
    @NotNull
    @Size(max = 254)
    private String email;

    @Column(name = "password", nullable = false, length = 115)
    @NotNull
    @Size(max = 115)
    private String password;

    @Column(name = "first_name", length = 50)
    @Size(max = 50)
    private String firstName;

    @Column(name = "last_name", length = 100)
    @Size(max = 100)
    private String lastName;

    @ManyToMany
    @JoinTable(
        name = "user_role",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_title")
    )
    private Set<Role> roles = new HashSet<>();

    public String getUserId() {
        return userId;
    }

    public void setUserId(String username) {
        this.userId = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return firstName != null && lastName != null
            ? String.format("%s., %s", lastName.charAt(0), firstName)
            : userId;
    }

    public static class Builder implements SchemaBuilder<User> {
        private String userId;
        private String email;
        private String password;
        private String firstName;
        private String lastName;
        private Set<Role> roles;

        public Builder userId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder roles(Role... roles) {
            this.roles = new HashSet<>();
            Collections.addAll(this.roles, roles);
            return this;
        }

        @Override
        public User build() {
            User user = new User();
            user.setUserId(userId);
            user.setEmail(email);
            user.setPassword(password);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            if (roles != null) {
                user.setRoles(roles);
            }
            return user;
        }
    }
}
