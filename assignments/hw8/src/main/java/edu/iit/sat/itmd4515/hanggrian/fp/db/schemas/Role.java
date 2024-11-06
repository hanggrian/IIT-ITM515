package edu.iit.sat.itmd4515.hanggrian.fp.db.schemas;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "role")
public class Role {
    @Id
    @Column(name = "role_title", nullable = false, length = 30)
    @NotNull
    @Size(max = 30)
    private String roleTitle;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users = new HashSet<>();

    public String getRoleTitle() {
        return roleTitle;
    }

    public void setRoleTitle(String title) {
        this.roleTitle = title;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public static class Builder implements SchemaBuilder<Role> {
        private String roleTitle;
        private Set<User> users;

        public Builder roleTitle(String roleTitle) {
            this.roleTitle = roleTitle;
            return this;
        }

        public Builder users(User... users) {
            this.users = new HashSet<>();
            Collections.addAll(this.users, users);
            return this;
        }

        @Override
        public Role build() {
            Role role = new Role();
            if (roleTitle != null) {
                role.setRoleTitle(roleTitle);
            }
            if (users != null) {
                role.setUsers(users);
            }
            return role;
        }
    }
}
