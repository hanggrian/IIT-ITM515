package edu.iit.sat.itmd4515.hanggrian.fp.db.schemas;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.Base64;

@Entity
@Table(name = "department")
public class Department {
    @Id
    @Column(name = "department_name", nullable = false, length = 50)
    @NotNull
    @Size(max = 50)
    private String departmentName;

    @Column(name = "website", nullable = false, length = 254)
    @NotNull
    @Size(max = 254)
    private String website;

    @Column(name = "email", nullable = false, length = 254)
    @NotNull
    @Size(max = 254)
    private String email;

    @Column(name = "phone_number", nullable = false, length = 10)
    @NotNull
    @Size(max = 10)
    private String phoneNumber;

    @Column(name = "building", length = 100)
    @Size(max = 100)
    private String building;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getDescription() {
        return new String(Base64.getDecoder().decode(description));
    }

    public void setDescription(String description) {
        this.description = Base64.getEncoder().encodeToString(description.getBytes());
    }

    public static class Builder implements SchemaBuilder<Department> {
        private String departmentName;
        private String website;
        private String email;
        private String phoneNumber;
        private String building;
        private String description;

        public Builder departmentName(String departmentName) {
            this.departmentName = departmentName;
            return this;
        }

        public Builder website(String website) {
            this.website = website;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder building(String building) {
            this.building = building;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        @Override
        public Department build() {
            Department department = new Department();
            department.setDepartmentName(departmentName);
            department.setWebsite(website);
            department.setEmail(email);
            department.setPhoneNumber(phoneNumber);
            department.setBuilding(building);
            department.setDescription(description);
            return department;
        }
    }
}
