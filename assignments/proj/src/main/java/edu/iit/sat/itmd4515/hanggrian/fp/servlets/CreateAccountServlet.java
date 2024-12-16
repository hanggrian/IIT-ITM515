package edu.iit.sat.itmd4515.hanggrian.fp.servlets;

import edu.iit.sat.itmd4515.hanggrian.fp.db.Roles;
import edu.iit.sat.itmd4515.hanggrian.fp.db.Students;
import edu.iit.sat.itmd4515.hanggrian.fp.db.schemas.Student;
import jakarta.ejb.EJB;
import jakarta.inject.Inject;
import jakarta.security.enterprise.identitystore.PasswordHash;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * URL handler to create an account, or show an error message if the ID is already taken. All the
 * values sent to this servlet are guaranteed to be in proper format because they are validated in
 * {@code signup.js}.
 */
@WebServlet("/create-account")
public class CreateAccountServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(CreateAccountServlet.class);

    @EJB Students students;
    @EJB Roles roles;
    @Inject PasswordHash hash;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        if (request.getParameter("create-account") == null) {
            gotoIndex(response);
            return;
        }

        String firstName = request.getParameter("first-name");
        String lastName = request.getParameter("last-name");
        String id = request.getParameter("id");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        PrintWriter writer;
        try {
            writer = response.getWriter();
        } catch (IOException e) {
            LOGGER.error("IO error.", e);
            throw new RuntimeException(e);
        }

        if (students.hasStudentId(id)) {
            showAlert(writer, "The ID has already been taken.");
            return;
        }

        students.insert(
            new Student.Builder()
                .firstName(firstName)
                .lastName(lastName)
                .studentId(id)
                .email(email)
                .password(hash.generate(password.toCharArray()))
                .roles(roles.selectByTitle("user"))
                .build()
        );
        gotoIndex(response);
    }

    private static void gotoIndex(HttpServletResponse response) {
        try {
            response.sendRedirect("index");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void showAlert(PrintWriter writer, String message) {
        writer.println("<script type=\"text/javascript\">");
        writer.println(String.format("alert('%s');", message));
        writer.println("location=signup;");
        writer.println("</script>");
    }
}
