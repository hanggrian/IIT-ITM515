package edu.iit.sat.itmd4515.hanggrian.fp;

import edu.iit.sat.itmd4515.hanggrian.fp.db.Roles;
import edu.iit.sat.itmd4515.hanggrian.fp.db.Users;
import edu.iit.sat.itmd4515.hanggrian.fp.db.schemas.User;
import jakarta.ejb.EJB;
import jakarta.inject.Inject;
import jakarta.security.enterprise.identitystore.Pbkdf2PasswordHash;
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
 * {@code create-account.js}.
 */
@WebServlet(name = "signup", value = {"/signup"})
public class SignupServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(SignupServlet.class);

    @EJB Users users;
    @EJB Roles roles;
    @Inject Pbkdf2PasswordHash passwordHash;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
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

        if (users.hasUserId(id)) {
            showAlert(writer, "The ID has already been taken.");
            return;
        }

        users.insert(
            new User.Builder()
                .firstName(firstName)
                .lastName(lastName)
                .userId(id)
                .email(email)
                .password(passwordHash.generate(password.toCharArray()))
                .roles(roles.selectByTitle("user"))
                .build()
        );
        try {
            response.sendRedirect("index");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void showAlert(PrintWriter writer, String message) {
        writer.println("<script type=\"text/javascript\">");
        writer.println(String.format("alert('%s');", message));
        writer.println("location=create-account.xhtml;");
        writer.println("</script>");
    }
}
