package edu.iit.sat.itmd4515.hanggrian.fp.db.schemas;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * @see <a href="https://dev.mysql.com/doc/sakila/en/sakila-structure-tables-film_category.html">
 *     Table Reference
 *     </a>
 */
@Entity
@Table(name = "film_category")
public class FilmCategory {
    @Id
    @ManyToOne
    @JoinColumn(name = "film_id")
    private Film film;

    @Id
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
