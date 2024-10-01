package edu.iit.sat.itmd4515.hwijaya.fp;

import edu.iit.sat.itmd4515.hwijaya.fp.db.Databases;
import edu.iit.sat.itmd4515.hwijaya.fp.db.Films;
import edu.iit.sat.itmd4515.hwijaya.fp.db.Languages;
import edu.iit.sat.itmd4515.hwijaya.fp.db.schemas.Film;
import jakarta.persistence.EntityManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A servlet that handles POST request from `index.jsp` form input data.
 */
@WebServlet(name = "submit", value = {"/submit"})
public class SubmitServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(SubmitServlet.class);

    private EntityManager entityManager;
    private Validator validator;

    @Override
    public void init() throws ServletException {
        super.init();
        entityManager = Databases.openSession();
        validator = Databases.getValidator();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        Film film = new Film();
        film.setTitle(request.getParameter("title"));
        film.setDescription(request.getParameter("description"));
        film.setReleaseYear(Integer.parseInt(request.getParameter("release-year")));
        film.setLanguage(
            Languages.selectOneByName(entityManager, request.getParameter("language"))
        );
        film.setRentalDuration((byte) Integer.parseInt(request.getParameter("rental-duration")));
        film.setRentalRate(new BigDecimal(request.getParameter("rental-rate")));
        film.setLength(Short.parseShort(request.getParameter("length")));
        film.setReplacementCost(new BigDecimal(request.getParameter("replacement-cost")));
        film.setRating(Film.Rating.valueOf(request.getParameter("rating")));
        StringBuilder specialFeatures = new StringBuilder();
        appendNullable(specialFeatures, request.getParameter("behind-the-scenes"));
        appendNullable(specialFeatures, request.getParameter("commentaries"));
        appendNullable(specialFeatures, request.getParameter("deleted-scenes"));
        appendNullable(specialFeatures, request.getParameter("trailers"));
        film.setSpecialFeatures(specialFeatures.toString());

        PrintWriter writer;
        try {
            writer = response.getWriter();
        } catch (IOException e) {
            LOGGER.error("IO error.", e);
            throw new RuntimeException(e);
        }

        Set<ConstraintViolation<Film>> violations = validator.validate(film);
        if (!violations.isEmpty()) {
            StringBuilder builder = new StringBuilder();
            for (ConstraintViolation<Film> violation : violations) {
                if (builder.length() > 0) {
                    builder.append(", ");
                }
                builder
                    .append(violation.getPropertyPath())
                    .append(' ')
                    .append(violation.getMessage());
            }
            showAlert(writer, builder.append('.').toString());
            return;
        }

        Films.insert(entityManager, film);
        showAlert(writer, String.format("Film inserted with ID %d.", film.getFilmId()));
    }

    @Override
    public void destroy() {
        entityManager.close();
        Databases.close();
    }

    private void showAlert(PrintWriter writer, String message) {
        writer.println("<script type=\"text/javascript\">");
        writer.println(String.format("alert('%s');", message));
        writer.println("location='index.jsp';");
        writer.println("</script>");
    }

    private void appendNullable(StringBuilder builder, Object obj) {
        if (obj == null) {
            return;
        }
        if (builder.length() > 0) {
            builder.append(',');
        }
        builder.append(obj);
    }
}
