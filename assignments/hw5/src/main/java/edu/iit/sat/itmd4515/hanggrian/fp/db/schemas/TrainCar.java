package edu.iit.sat.itmd4515.hanggrian.fp.db.schemas;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "train_car")
public class TrainCar {
    @EmbeddedId
    private TrainCarId id;

    @ManyToOne
    @MapsId("trainId")
    @JoinColumn(name = "train_id")
    private Train train;

    @ManyToOne
    @MapsId("carNo")
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

    @Embeddable
    public static class TrainCarId {
        @Column(name = "train_id", nullable = false)
        private Integer trainId;

        @Column(name = "car_no", length = 4, nullable = false)
        private String carNo;

        public Integer getTrainId() {
            return trainId;
        }

        public void setTrainId(Integer trainId) {
            this.trainId = trainId;
        }

        public String getCarNo() {
            return carNo;
        }

        public void setCarNo(String carNo) {
            this.carNo = carNo;
        }
    }
}
