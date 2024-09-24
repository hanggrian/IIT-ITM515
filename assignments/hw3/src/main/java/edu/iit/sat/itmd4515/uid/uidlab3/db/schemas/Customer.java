package edu.iit.sat.itmd4515.uid.uidlab3.db.schemas;

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
import java.util.Date;

/**
 * @see <a href="https://dev.mysql.com/doc/sakila/en/sakila-structure-tables-customer.html">
 *     Table Reference
 *     </a>
 */
@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id", nullable = false)
    @NotNull
    private Short customerId;

    @ManyToOne
    @JoinColumn(name = "store_id", nullable = false)
    @NotNull
    private Store store;

    @Column(name = "first_name", nullable = false, length = 45)
    @NotNull
    @Size(max = 45)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 45)
    @NotNull
    @Size(max = 45)
    private String lastName;

    @Column(name = "email", length = 50)
    @Size(max = 50)
    private String email;

    @ManyToOne
    @JoinColumn(name = "address_id", nullable = false)
    @NotNull
    private Address address;

    @Column(name = "active", nullable = false)
    @NotNull
    private Boolean active;

    @Column(name = "create_date", nullable = false)
    @NotNull
    private Date createDate;

    public short getCustomerId() {
        return customerId;
    }

    public void setCustomerId(short customerId) {
        this.customerId = customerId;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
