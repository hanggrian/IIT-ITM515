package edu.iit.sat.itmd4515.hwijaya.fp.db.schemas;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "train_car")
public class TrainCar {
    @Id
    @ManyToOne
    @JoinColumn(name = "train_id")
    private Train train;

    @Id
    @ManyToOne
    @JoinColumn(name = "car_no")
    private Car car;

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public int hashCode() {
        return Objects.hash(train.getTrainId(), car.getCarNo());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        TrainCar other = (TrainCar) obj;
        return train.getTrainId().equals(other.train.getTrainId())
            && car.getCarNo().equals(other.car.getCarNo());
    }
}
