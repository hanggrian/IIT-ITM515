package edu.iit.sat.itmd4515.hanggrian.fp.controllers;

import edu.iit.sat.itmd4515.hanggrian.fp.db.Students;
import edu.iit.sat.itmd4515.hanggrian.fp.db.schemas.Student;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.security.enterprise.SecurityContext;
import java.io.Serializable;
import java.util.List;

/**
 * The home page host data tables of all students, it may only be accessed in the event of
 * successful login.
 */
@Named("homeController")
@RequestScoped
public class HomeController implements Serializable {
    @EJB Students students;
    @Inject SecurityContext context;

    /**
     * List all students except the pre-defined <b>admin</b> user in a data table.
     */
    public List<Student> getStudents() {
        return students.selectAllExceptAdmin();
    }

    /**
     * Navigate to edit student page.
     *
     * @param student data table entry to edit.
     */
    public String edit(Student student) {
        return "edit?faces-redirect=true&studentId=" + student.getStudentId();
    }

    /**
     * Verify the confirmation dialog to remove a student record, then display a relevant alert
     * message.
     *
     * @param student data table entry to remove.
     */
    public void delete(Student student) {
        students.delete(student);
        addMessage("Delete", String.format("Student %s deleted.", student));
    }

    /**
     * Condition to determine whether to display <b>Delete</b> button in the data table entry.
     */
    public boolean getIsAdmin() {
        return context.isCallerInRole("admin");
    }

    private static void addMessage(String summary, String detail) {
        FacesContext.getCurrentInstance().addMessage(
            null,
            new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail)
        );
    }
}
