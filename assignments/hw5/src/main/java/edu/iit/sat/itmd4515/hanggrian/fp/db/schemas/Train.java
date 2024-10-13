package edu.iit.sat.itmd4515.hanggrian.fp.db.schemas;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.Year;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "train")
public class Train {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "train_id", nullable = false)
    private Integer trainId;

    @Column(name = "locomotive_serial", length = 20)
    @Size(max = 20)
    private String locomotiveSerial;

    @Column(name = "since", nullable = false)
    @NotNull
    private Year since;

    @Column(name = "track_color", nullable = false, length = 10)
    @NotNull
    @Size(max = 10)
    private String trackColor;

    @ManyToMany(mappedBy = "trains")
    private Set<Car> cars = new HashSet<>();

    public Integer getTrainId() {
        return trainId;
    }

    public void setTrainId(Integer trainId) {
        this.trainId = trainId;
    }

    public String getLocomotiveSerial() {
        return locomotiveSerial;
    }

    public void setLocomotiveSerial(String locomotiveSerial) {
        this.locomotiveSerial = locomotiveSerial;
    }

    public Year getSince() {
        return since;
    }

    public void setSince(Year since) {
        this.since = since;
    }

    public String getTrackColor() {
        return trackColor;
    }

    public void setTrackColor(String trackColor) {
        this.trackColor = trackColor;
    }

    public Set<Car> getCars() {
        return cars;
    }

    public void setCars(Set<Car> cars) {
        this.cars = cars;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Train other = (Train) obj;
        return trainId.equals(other.trainId)
            && locomotiveSerial.equals(other.locomotiveSerial)
            && since.equals(other.since)
            && trackColor.equals(other.trackColor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(trainId, locomotiveSerial, since, trackColor);
    }

    @Override
    public String toString() {
        StringBuilder builder =
            new StringBuilder("#")
                .append(trainId);

        List<String> features = new ArrayList<>();
        features.add(String.format("%d cars", cars.size()));
        if (locomotiveSerial != null) {
            features.add(locomotiveSerial);
        }
        builder
            .append(" (")
            .append(String.join(", ", features))
            .append(')');

        return builder.toString();
    }
}
