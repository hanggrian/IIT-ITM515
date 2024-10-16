package edu.iit.sat.itmd4515.hanggrian.fp.db.schemas;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.Year;
import java.util.ArrayList;
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

    @ManyToOne
    @JoinColumn(name = "track_color", nullable = false)
    private Track track;

    @OneToMany(mappedBy = "train")
    private Set<TrainCar> trainCars;

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

    public Track getTrack() {
        return track;
    }

    public void setTrack(Track track) {
        this.track = track;
    }

    public Set<TrainCar> getTrainCars() {
        return trainCars;
    }

    public void setTrainCars(Set<TrainCar> trainCars) {
        this.trainCars = trainCars;
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
            && track.equals(other.track);
    }

    @Override
    public int hashCode() {
        return Objects.hash(trainId, locomotiveSerial, since, track);
    }

    @Override
    public String toString() {
        StringBuilder builder =
            new StringBuilder("#")
                .append(trainId);

        List<String> features = new ArrayList<>();
        features.add(String.format("%d cars", trainCars.size()));
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
