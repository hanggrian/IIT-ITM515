package edu.iit.sat.itmd4515.hanggrian.fp.db.schemas;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "car")
public class Car {
    @Id
    @Column(name = "car_no", nullable = false, length = 4)
    @NotNull
    @Size(max = 4)
    private String carNo;

    @Column(name = "seats", nullable = false)
    @NotNull
    private Integer seats;

    @ManyToMany
    @JoinTable(
        name = "train_car",
        joinColumns = @JoinColumn(name = "car_no"),
        inverseJoinColumns = @JoinColumn(name = "train_id")
    )
    private Set<Train> trains = new HashSet<>();

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }

    public Integer getSeats() {
        return seats;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
    }

    public Set<Train> getTrains() {
        return trains;
    }

    public void setTrains(Set<Train> trains) {
        this.trains = trains;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Car other = (Car) obj;
        return carNo.equals(other.carNo)
            && seats.equals(other.seats);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carNo, seats);
    }

    @Override
    public String toString() {
        return String.format("%s (%d seats)", carNo, seats);
    }
}
