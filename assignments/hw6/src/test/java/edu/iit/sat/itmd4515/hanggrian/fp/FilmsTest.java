package edu.iit.sat.itmd4515.hanggrian.fp;

import edu.iit.sat.itmd4515.hanggrian.fp.db.FilmTexts;
import edu.iit.sat.itmd4515.hanggrian.fp.db.Films;
import edu.iit.sat.itmd4515.hanggrian.fp.db.Languages;
import edu.iit.sat.itmd4515.hanggrian.fp.db.schemas.Film;
import java.math.BigDecimal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;

public class FilmsTest extends DaoTest {
    private Films films;
    private FilmTexts filmTexts;
    private Languages languages;

    @BeforeEach
    public void createDao() {
        films = new Films();
        filmTexts = new FilmTexts();
        languages = new Languages();

        languages.manager = entityManager;
        filmTexts.manager = entityManager;
        films.manager = entityManager;
    }

    @Test
    public void validDao() {
        transaction(
            () -> {
                Film film = new Film();
                film.setTitle("Lorem Ipsum");
                film.setLanguage(languages.selectOneByName("english"));
                film.setRentalDuration((byte) 1);
                film.setRentalRate(new BigDecimal(2));
                film.setReplacementCost(new BigDecimal(3));
                film.setRating("R");
                films.insert(film);
            }
        );

        Film film = films.selectLast();
        assertThat(film.getTitle())
            .isEqualTo("Lorem Ipsum");
        assertThat(film.getLanguage().getName())
            .isEqualTo("English");
        assertThat(film.getRentalDuration())
            .isEqualTo((byte) 1);
        assertThat(film.getRentalRate().intValue())
            .isEqualTo(2);
        assertThat(film.getReplacementCost().intValue())
            .isEqualTo(3);
        assertThat(film.getRating())
            .isEqualTo("R");

        transaction(
            () -> {
                filmTexts.deleteAllByFilm(film);
                films.delete(film);
            }
        );
    }
}
