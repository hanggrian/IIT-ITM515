package edu.iit.sat.itmd4515.hanggrian.fp.db.schemas;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "student")
public class Student {
    @Id
    @Column(name = "student_id", nullable = false, length = 15)
    @NotNull
    @Size(max = 15)
    private String studentId;

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

    @ManyToOne
    @JoinColumn(name = "department_name")
    private Department department;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @ManyToMany
    @JoinTable(
        name = "student_role",
        joinColumns = @JoinColumn(name = "student_id"),
        inverseJoinColumns = @JoinColumn(name = "role_title")
    )
    private Set<Role> roles = new HashSet<>();

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
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

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getFullName() {
        return String.format("%s., %s", firstName.charAt(0), lastName);
    }

    public String getDepartmentName() {
        return department.getDepartmentName();
    }

    @Override
    public String toString() {
        return firstName != null && lastName != null
            ? getFullName()
            : studentId;
    }

    public static class Builder implements SchemaBuilder<Student> {
        private String studentId;
        private String email;
        private String password;
        private String firstName;
        private String lastName;
        private Department department;
        private Date createdAt;
        private Set<Role> roles;

        public Builder studentId(String studentId) {
            this.studentId = studentId;
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

        public Builder department(Department department) {
            this.department = department;
            return this;
        }

        public Builder createdAt(Date createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder roles(Role... roles) {
            this.roles = new HashSet<>();
            Collections.addAll(this.roles, roles);
            return this;
        }

        @Override
        public Student build() {
            Student student = new Student();
            student.setStudentId(studentId);
            student.setEmail(email);
            student.setPassword(password);
            student.setFirstName(firstName);
            student.setLastName(lastName);
            student.setDepartment(department);
            student.setCreatedAt(createdAt);
            if (roles != null) {
                student.setRoles(roles);
            }
            return student;
        }
    }
}
