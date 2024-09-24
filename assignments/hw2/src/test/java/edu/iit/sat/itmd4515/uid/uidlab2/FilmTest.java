package edu.iit.sat.itmd4515.uid.uidlab2;

import edu.iit.sat.itmd4515.uid.uidlab2.db.Films;
import edu.iit.sat.itmd4515.uid.uidlab2.db.schemas.Film;
import edu.iit.sat.itmd4515.uid.uidlab2.db.schemas.Language;
import jakarta.validation.ConstraintViolation;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;

public class FilmTest extends DatabaseTest {
    @Test
    public void invalidPojo() {
        Film film = new Film();
        film.setTitle(stringSized(129));

        assertThat(validator.validate(film).stream().map(ConstraintViolation::getMessage))
            .containsExactly(
                "must not be null",
                "size must be between 0 and 128",
                "must not be null",
                "must not be null",
                "must not be null",
                "must not be null"
            );
    }

    @Test
    public void validPojo() {
        Film film = new Film();
        film.setFilmId((short) 0);
        film.setTitle("");
        film.setLanguage(new Language());
        film.setRentalDuration((byte) 0);
        film.setRentalRate(BigDecimal.ZERO);
        film.setReplacementCost(BigDecimal.ZERO);

        assertThat(validator.validate(film)).isEmpty();
    }

    @Test
    public void validDao() {
        assertThat(Films.selectByTitleUpToTen(session, "wonder"))
            .hasSize(4);
        assertThat(Films.selectByTitleUpToTen(session, "ro"))
            .hasSize(10);
    }
}
