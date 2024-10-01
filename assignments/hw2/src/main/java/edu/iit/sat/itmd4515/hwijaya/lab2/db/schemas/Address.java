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
 * @see <a href="https://dev.mysql.com/doc/sakila/en/sakila-structure-tables-address.html">
 * Table Reference
 * </a>
 */
@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id", nullable = false)
    private Short addressId;

    @Column(name = "address", nullable = false, length = 50)
    @NotNull
    @Size(max = 50)
    private String address;

    @Column(name = "address2", length = 50)
    @Size(max = 50)
    private String address2;

    @Column(name = "district", nullable = false, length = 20)
    @NotNull
    @Size(max = 20)
    private String district;

    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    @NotNull
    private City city;

    @Column(name = "postal_code", length = 10)
    @Size(max = 10)
    private String postalCode;

    @Column(name = "phone", nullable = false, length = 20)
    @NotNull
    @Size(max = 20)
    private String phone;

    public short getAddressId() {
        return addressId;
    }

    public void setAddressId(short addressId) {
        this.addressId = addressId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return String.format(
            "%s, %s (%s, %s)",
            address,
            district,
            city.getCity(),
            city.getCountry().getCountry());
    }
}
