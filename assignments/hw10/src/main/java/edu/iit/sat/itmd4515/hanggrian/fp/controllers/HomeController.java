package edu.iit.sat.itmd4515.hanggrian.fp.controllers;

import edu.iit.sat.itmd4515.hanggrian.fp.db.Students;
import edu.iit.sat.itmd4515.hanggrian.fp.db.schemas.Student;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
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

    public List<Student> getStudents() {
        return students.selectAllExceptAdmin();
    }

    public String edit(Student student) {
        return "edit?faces-redirect=true&studentId=" + student.getStudentId();
    }

    public void delete(Student student) {
        students.delete(student);
        addMessage("Delete", String.format("Student %s deleted.", student));
    }

    private static void addMessage(String summary, String detail) {
        FacesContext.getCurrentInstance().addMessage(
            null,
            new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail)
        );
    }
}
