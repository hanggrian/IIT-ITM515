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
    @Column(name = "role_title", nullable = false, length = 15)
    @NotNull
    @Size(max = 15)
    private String roleTitle;

    @ManyToMany(mappedBy = "roles")
    private Set<Student> students = new HashSet<>();

    public String getRoleTitle() {
        return roleTitle;
    }

    public void setRoleTitle(String title) {
        this.roleTitle = title;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public static class Builder implements SchemaBuilder<Role> {
        private String roleTitle;
        private Set<Student> students;

        public Builder roleTitle(String roleTitle) {
            this.roleTitle = roleTitle;
            return this;
        }

        public Builder students(Student... students) {
            this.students = new HashSet<>();
            Collections.addAll(this.students, students);
            return this;
        }

        @Override
        public Role build() {
            Role role = new Role();
            role.setRoleTitle(roleTitle);
            if (students != null) {
                role.setStudents(students);
            }
            return role;
        }
    }
}
