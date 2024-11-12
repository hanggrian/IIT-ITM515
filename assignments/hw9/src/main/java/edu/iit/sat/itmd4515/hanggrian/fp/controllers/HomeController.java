package edu.iit.sat.itmd4515.hanggrian.fp.controllers;

import edu.iit.sat.itmd4515.hanggrian.fp.db.Students;
import edu.iit.sat.itmd4515.hanggrian.fp.db.schemas.Student;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * The home page host data tables of all students, it may only be accessed in the event of
 * successful login.
 */
@Named("homeController")
@SessionScoped
public class HomeController implements Serializable {
    @EJB Students students;

    public List<Student> getStudents() {
        return students.selectAllExceptAdmin();
    }
}
