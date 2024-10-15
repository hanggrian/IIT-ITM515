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
@Table(name = "track")
public class Track {
    @Id
    @Column(name = "track_color", nullable = false, length = 10)
    @NotNull
    @Size(max = 10)
    private String trackColor;

    @Column(name = "is_24h", nullable = false)
    @NotNull
    private Boolean is24h;

    @ManyToMany
    @JoinTable(
        name = "track_station",
        joinColumns = @JoinColumn(name = "track_color"),
        inverseJoinColumns = @JoinColumn(name = "station_name")
    )
    private Set<Station> stations = new HashSet<>();

    public String getTrackColor() {
        return trackColor;
    }

    public void setTrackColor(String trackColor) {
        this.trackColor = trackColor;
    }

    public Boolean getIs24h() {
        return is24h;
    }

    public void setIs24h(Boolean is24h) {
        this.is24h = is24h;
    }

    public Set<Station> getStations() {
        return stations;
    }

    public void setStations(Set<Station> stations) {
        this.stations = stations;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Track track = (Track) obj;
        return trackColor.equals(track.trackColor)
            && is24h.equals(track.is24h);
    }

    @Override
    public int hashCode() {
        return Objects.hash(trackColor, is24h);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(trackColor);
        if (is24h) {
            builder.append(" (24h)");
        }
        return builder.toString();
    }
}
