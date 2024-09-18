package edu.iit.sat.itmd4515.uid.uidlab3.db.schemas;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * @see <a href="https://dev.mysql.com/doc/sakila/en/sakila-structure-tables-film_actor.html">
 *     Table Reference
 *     </a>
 */
@Entity
@Table(name = "film_actor")
public class FilmActor {
    @Id
    @ManyToOne
    @JoinColumn(name = "film_id")
    private Film film;

    @Id
    @ManyToOne
    @JoinColumn(name = "actor_id")
    private Actor actor;

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }
}
