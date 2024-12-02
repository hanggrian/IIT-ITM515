package edu.iit.sat.itmd4515.hanggrian.fp.controllers;

import edu.iit.sat.itmd4515.hanggrian.fp.db.Departments;
import edu.iit.sat.itmd4515.hanggrian.fp.db.schemas.Department;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * A controller for detailed view of department. The description is in HTML format that is printed
 * directly to XHTML file with {@code escape} attribute. {@link Information} is a pair holder for
 * table view.
 */
@Named("departmentController")
@SessionScoped
public class DepartmentController implements Serializable {
    @EJB Departments departments;

    private Department department;

    public List<Information> getInformation() {
        Department dept = departmentNotNull();
        return Arrays.asList(
            new Information("Website", dept.getWebsite()),
            new Information("Email", dept.getEmail()),
            new Information("Phone No.", dept.getPhoneNumber()),
            new Information("Building", dept.getBuilding())
        );
    }

    public String getDescription() {
        return departmentNotNull().getDescription();
    }

    private Department departmentNotNull() {
        if (department == null) {
            department =
                departments.find(
                    FacesContext
                        .getCurrentInstance()
                        .getExternalContext()
                        .getRequestParameterMap()
                        .get("name")
                );
        }
        return department;
    }

    public static class Information {
        private String key;
        private String value;

        public Information(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
