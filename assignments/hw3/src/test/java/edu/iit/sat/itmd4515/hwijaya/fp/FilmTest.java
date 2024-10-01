package edu.iit.sat.itmd4515.hwijaya.fp;

import edu.iit.sat.itmd4515.hwijaya.fp.db.schemas.Film;
import edu.iit.sat.itmd4515.hwijaya.fp.db.schemas.Language;
import jakarta.validation.ConstraintViolation;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;

public class FilmTest extends DatabaseTest {
    @Test
    public void invalidPojo() {
        Film film = new Film();
        film.setTitle(stringSized(129));
        film.setDescription(stringSized(256));

        assertThat(validator.validate(film).stream().map(ConstraintViolation::getMessage))
            .containsExactly(
                "size must be between 0 and 128",
                "size must be between 0 and 255",
                "must not be null",
                "must not be null",
                "must not be null",
                "must not be null"
            );
    }

    @Test
    public void validPojo() {
        Film film = new Film();
        film.setTitle("");
        film.setLanguage(new Language());
        film.setRentalDuration((byte) 0);
        film.setRentalRate(BigDecimal.ZERO);
        film.setReplacementCost(BigDecimal.ZERO);

        assertThat(validator.validate(film)).isEmpty();
    }
}
