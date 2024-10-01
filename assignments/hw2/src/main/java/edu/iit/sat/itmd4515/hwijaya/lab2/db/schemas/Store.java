package edu.iit.sat.itmd4515.hwijaya.lab2.db.schemas;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

/**
 * @see <a href="https://dev.mysql.com/doc/sakila/en/sakila-structure-tables-store.html">
 *     Table Reference
 *     </a>
 */
@Entity
@Table(name = "store")
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id", nullable = false)
    private Byte storeId;

    @OneToOne
    @JoinColumn(name = "manager_staff_id", nullable = false, unique = true)
    @NotNull
    private Staff managerStaff;

    @ManyToOne
    @JoinColumn(name = "address_id", nullable = false)
    @NotNull
    private Address address;

    public byte getStoreId() {
        return storeId;
    }

    public void setStoreId(byte storeId) {
        this.storeId = storeId;
    }

    public Staff getManagerStaff() {
        return managerStaff;
    }

    public void setManagerStaff(Staff managerStaff) {
        this.managerStaff = managerStaff;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
