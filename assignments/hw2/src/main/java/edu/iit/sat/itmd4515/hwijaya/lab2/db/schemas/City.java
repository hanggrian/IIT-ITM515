package edu.iit.sat.itmd4515.hwijaya.lab2.db.schemas;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * @see <a href="https://dev.mysql.com/doc/sakila/en/sakila-structure-tables-city.html">
 *     Table Reference
 *     </a>
 */
@Entity
@Table(name = "city")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "city_id", nullable = false)
    private Short cityId;

    @Column(name = "city", nullable = false, length = 50)
    @NotNull
    @Size(max = 50)
    private String city;

    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    @NotNull
    private Country country;

    public short getCityId() {
        return cityId;
    }

    public void setCityId(short cityId) {
        this.cityId = cityId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
