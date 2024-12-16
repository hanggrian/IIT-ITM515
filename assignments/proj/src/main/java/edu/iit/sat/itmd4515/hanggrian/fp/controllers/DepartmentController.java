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
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

/**
 * A controller for detailed view of department. The description is in HTML format that is printed
 * directly to XHTML file with {@code escape} attribute.
 */
@Named("departmentController")
@SessionScoped
public class DepartmentController implements Serializable {
    @EJB Departments departments;

    private Department department;

    /**
     * Returns the long description of the department as paragraphs.
     */
    public String getDescription() {
        return departmentNotNull().getDescription();
    }

    /**
     * Collect the rest of department information as data table entries.
     */
    public List<Pair<String, String>> getInformation() {
        Department dept = departmentNotNull();
        return Arrays.asList(
            new ImmutablePair<>("Website", dept.getWebsite()),
            new ImmutablePair<>("Email", dept.getEmail()),
            new ImmutablePair<>("Phone No.", dept.getPhoneNumber()),
            new ImmutablePair<>("Building", dept.getBuilding())
        );
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
}
