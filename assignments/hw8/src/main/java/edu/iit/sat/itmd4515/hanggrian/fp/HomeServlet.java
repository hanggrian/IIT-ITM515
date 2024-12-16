package edu.iit.sat.itmd4515.hanggrian.fp;

import jakarta.annotation.security.DeclareRoles;
import jakarta.servlet.annotation.HttpConstraint;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * An elevated servlet to test login credentials. This page will show user principal associated with
 * the current session.
 */
@DeclareRoles({"user", "admin"})
@ServletSecurity(@HttpConstraint(rolesAllowed = {"user", "admin"}))
@WebServlet("/home")
public class HomeServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(HomeServlet.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        PrintWriter writer;
        try {
            writer = response.getWriter();
        } catch (IOException e) {
            LOGGER.error("IO error.", e);
            throw new RuntimeException(e);
        }
        for (String line : Arrays.asList(
            "<!DOCTYPE html>",
            "<html lang=\"en\">",
            "<head>",
            "  <meta charset=\"UTF-8\"/>",
            "  <title>ITMD 4/515 — Lab 8</title>",
            "  <meta name=\"viewport\" content=\"width=device-width\"/>",
            "  <meta http-equiv=\"X-UA-Compatible\" content=\"chrome=1\"/>",
            "  <link rel=\"stylesheet\" href=\"resources/styles/main.css\"/>",
            "  <link rel=\"stylesheet\" href=\"https://fonts.googleapis.com/css2?family=Material+Sy"
                + "mbols+Sharp:opsz,wght,FILL,GRAD@48,400,1,0\"/>",
            "  <script src=\"resources/scripts/theme.js\"></script>",
            "  <meta name=\"title\" content=\"IIT ITM515\"/>",
            "  <meta name=\"description\" content=\"Adv Software Programming at Illinois Tech — Fal"
                + "l 2024\"/>",
            "</head>",
            "<body>",
            "  <div class=\"wrapper\">",
            "    <header>",
            "      <h1>Web Application Security</h1>",
            "      <p>Adv Software Programming at Illinois Tech — Fall 2024</p>",
            "      <p class=\"view\">",
            "        <a href=\"https://github.com/hanggrian/IIT-ITM515/\">",
            "          View the Project on GitHub <small>hanggrian/IIT-ITM515</small>",
            "        </a>",
            "      </p>",
            "      <ul>",
            "        <li>",
            "          <a href=\"https://github.com/hanggrian/IIT-ITM515/\">View on<strong>GitHub</"
                + "strong></a>",
            "        </li>",
            "        <li>",
            "          <a href=\"https://github.com/hanggrian/IIT-ITM515/zipball/main/\">",
            "            Download<strong>ZIP File</strong>",
            "          </a>",
            "        </li>",
            "        <li>",
            "          <a href=\"https://github.com/hanggrian/IIT-ITM515/tarball/main/\">",
            "            Download<strong>TAR Ball</strong>",
            "          </a>",
            "        </li>",
            "      </ul>",
            "    </header>",
            "    <section>",
            "      <h1>Home</h1>",
            String.format("<p>Welcome, %s!<p>", request.getUserPrincipal().getName()),
            "    <footer>",
            "      <p>",
            "      <button",
            "        class=\"material-symbols-sharp\"",
            "        id=\"theme-toggle\"",
            "        title=\"Toggle dark mode\"",
            "        onclick=\"toggleDarkMode()\"></button>",
            "      </p>",
            "      <p>",
            "        This project is maintained by <a href=\"https://github.com/hanggrian/\">Hendra"
                + " Anggrian</a>",
            "      </p>",
            "      <p>",
            "        <small>",
            "          Hosted on GitHub Pages — Theme by <a href=\"https://github.com/orderedlist/"
                + "\">orderedlist</a>",
            "        </small>",
            "      </p>",
            "    </footer>",
            "  </div>",
            "  <script src=\"resources/scripts/scale.fix.js\"></script>",
            "</body>"
        )) {
            writer.write(line);
        }
    }
}
