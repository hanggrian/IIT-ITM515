package edu.iit.sat.itmd4515.uid.uidlab3.db.schemas;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

/**
 * @see <a href="https://dev.mysql.com/doc/sakila/en/sakila-structure-tables-film_text.html">
 *     Table Reference
 *     </a>
 */
@Entity
@Table(name = "film_text")
public class FilmText {
    @Id
    @Column(name = "film_id")
    private short filmId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "film_id")
    private Film film;

    @Column(name = "title", nullable = false)
    @NotNull
    private String title;

    @Column(name = "description")
    private String description;

    public short getFilmId() {
        return filmId;
    }

    public void setFilmId(short filmId) {
        this.filmId = filmId;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
