package edu.iit.sat.itmd4515.hanggrian.fp.controllers;

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
    @Inject private SecurityContext context;

    public String getWelcomeText() {
        return String.format("Welcome, %s!", context.getCallerPrincipal().getName());
    }
}
