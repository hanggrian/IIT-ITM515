package edu.iit.sat.itmd4515.hanggrian.fp.db;

import edu.iit.sat.itmd4515.hanggrian.fp.db.schemas.Film;
import edu.iit.sat.itmd4515.hanggrian.fp.db.schemas.FilmText;
import jakarta.annotation.sql.DataSourceDefinition;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;

/**
 * {@link FilmTexts} Data Access Object.
 */
@DataSourceDefinition(
    name = "java:app/jdbc/sakila",
    className = "com.mysql.cj.jdbc.MysqlDataSource",
    serverName = "localhost",
    portNumber = 3306,
    databaseName = "sakila",
    user = "itmd4515",
    password = "itmd4515",
    properties = {
        "useSSL=false",
        "allowPublicKeyRetrieval=true"
    }
)
@Stateless
public class FilmTexts {
    @PersistenceContext(unitName = "sakila") public EntityManager manager;

    @Transactional
    public void deleteAllByFilm(Film film) {
        List<FilmText> filmTexts =
            manager
                .createQuery("FROM FilmText WHERE filmId= :filmId", FilmText.class)
                .setParameter("filmId", film.getFilmId())
                .getResultList();

        for (FilmText filmText : filmTexts) {
            manager.remove(filmText);
        }
    }
}
