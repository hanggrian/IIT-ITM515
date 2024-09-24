package edu.iit.sat.itmd4515.uid.uidlab2.db.schemas;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

/**
 * @see <a href="https://dev.mysql.com/doc/sakila/en/sakila-structure-tables-category.html">
 *     Table Reference
 *     </a>
 */
@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id", nullable = false)
    @NotNull
    private Byte categoryId;

    @Column(name = "name", nullable = false, length = 25)
    @NotNull
    @Size(max = 25)
    private String name;

    @ManyToMany(mappedBy = "categories")
    private Set<Film> films = new HashSet<>();

    public byte getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(byte categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Film> getFilms() {
        return films;
    }

    public void setFilms(Set<Film> films) {
        this.films = films;
    }
}
