package edu.iit.sat.itmd4515.hanggrian.fp.controllers;

import edu.iit.sat.itmd4515.hanggrian.fp.db.Students;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.security.enterprise.SecurityContext;
import java.io.Serializable;

/**
 * Header is a template that is included in all webpages that requires login session. It displays
 * currently logged-in user.
 */
@Named("headerController")
@SessionScoped
public class HeaderController implements Serializable {
    @EJB Students students;
    @Inject SecurityContext context;

    /**
     * Returns the username of logged-in user as the title of header section.
     */
    public String getWelcomeText() {
        StringBuilder builder = new StringBuilder();
        builder.append("Logged in as ").append(context.getCallerPrincipal().getName());
        if (context.isCallerInRole("admin")) {
            builder.append(" (Admin)");
        }
        return builder.append('.').toString();
    }

    /**
     * Returns the most recent registrant as the subtitle of header section.
     */
    public String getGreetText() {
        return String.format(
            "Please welcome our newest student, %s!",
            students.getLatestExceptAdmin().getStudentId()
        );
    }
}
