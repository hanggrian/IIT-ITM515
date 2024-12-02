package edu.iit.sat.itmd4515.hanggrian.fp.controllers;

import edu.iit.sat.itmd4515.hanggrian.fp.ApplicationConfig;
import edu.iit.sat.itmd4515.hanggrian.fp.db.Departments;
import edu.iit.sat.itmd4515.hanggrian.fp.db.Students;
import edu.iit.sat.itmd4515.hanggrian.fp.db.schemas.Department;
import edu.iit.sat.itmd4515.hanggrian.fp.db.schemas.Student;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;

/**
 * Webpage to update student record with AJAX input validation. Configured in `web.xml`, this page
 * is only accessible for user with `admin` role. Note that department dropdown menu doesn't need to
 * be validated because an item is always selected.
 */
@Named("editController")
@ViewScoped
public class EditController implements Serializable {
    private static final Pattern EMAIL_REGEX =
        Pattern.compile("^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$");
    private static final Pattern DISPLAY_NAME_REGEX =
        Pattern.compile("^[A-Z][a-z]*(\\s+[A-Z][a-z]*)*$");

    @EJB Students students;
    @EJB Departments departments;
    @Inject ApplicationConfig config;

    private String studentId;
    private String firstName;
    private String lastName;
    private String email;
    private String departmentName;

    private String password;
    private String confirmPassword;

    @PostConstruct
    public void init() {
        String studentId =
            FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap()
                .get("studentId");
        if (studentId == null) {
            return;
        }
        Student student = students.find(studentId);
        this.studentId = student.getStudentId();
        this.firstName = student.getFirstName();
        this.lastName = student.getLastName();
        this.email = student.getEmail();
        this.departmentName = student.getDepartment().getDepartmentName();
        this.password = "";
        this.confirmPassword = "";
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public List<Department> getDepartments() {
        return departments.listAll();
    }

    public boolean isValid() {
        boolean valid = true;
        if (isEditingPassword()) {
            valid =
                password.length() <= 64
                    && confirmPassword.length() <= 64
                    && password.equals(confirmPassword);
        }
        return
            valid
                && !StringUtils.isBlank(firstName)
                && firstName.length() <= 50
                && DISPLAY_NAME_REGEX.matcher(firstName).matches()
                && !StringUtils.isBlank(lastName)
                && lastName.length() <= 100
                && DISPLAY_NAME_REGEX.matcher(lastName).matches()
                && !StringUtils.isBlank(email)
                && email.length() <= 254
                && EMAIL_REGEX.matcher(email).matches();
    }

    public String cancel() {
        return "home?faces-redirect=true";
    }

    public String edit() {
        Student student = students.find(studentId);
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setEmail(email);
        student.setDepartment(departments.find(departmentName));
        if (isEditingPassword()) {
            student.setPassword(config.hashPassword(password));
        }
        students.update(student);
        return "home?faces-redirect=true";
    }

    private boolean isEditingPassword() {
        return !StringUtils.isBlank(password)
            || !StringUtils.isBlank(confirmPassword);
    }
}
