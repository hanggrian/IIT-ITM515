package edu.iit.sat.itmd4515.uid.uidlab3.db.schemas;

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
 * @see <a href="https://dev.mysql.com/doc/sakila/en/sakila-structure-tables-actor.html">
 * Table Reference
 * </a>
 */
@Entity
@Table(name = "actor")
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "actor_id", nullable = false)
    @NotNull
    private Short actorId;

    @Column(name = "first_name", nullable = false, length = 45)
    @NotNull
    @Size(max = 45)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 45)
    @NotNull
    @Size(max = 45)
    private String lastName;

    @ManyToMany(mappedBy = "actors")
    private Set<Film> films = new HashSet<>();

    public short getActorId() {
        return actorId;
    }

    public void setActorId(short actorId) {
        this.actorId = actorId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<Film> getFilms() {
        return films;
    }

    public void setFilms(Set<Film> films) {
        this.films = films;
    }
}
