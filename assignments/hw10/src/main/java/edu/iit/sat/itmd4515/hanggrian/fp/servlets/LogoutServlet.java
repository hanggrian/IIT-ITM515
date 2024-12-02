package edu.iit.sat.itmd4515.hanggrian.fp.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Lightweight servlet to invalidate current session and return to the initial state of web
 * application.
 */
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(LogoutServlet.class);

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().invalidate();
        try {
            request.getRequestDispatcher("index").forward(request, response);
        } catch (IOException | ServletException e) {
            LOGGER.error("IO error.", e);
            throw new RuntimeException(e);
        }
    }
}
