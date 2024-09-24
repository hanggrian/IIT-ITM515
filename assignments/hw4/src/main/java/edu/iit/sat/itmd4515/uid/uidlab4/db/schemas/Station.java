package edu.iit.sat.itmd4515.uid.uidlab4.db.schemas;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "station")
public class Station {
    @Id
    @Column(name = "station_name", nullable = false, length = 50)
    @Size(max = 50)
    @NotNull
    private String stationName;

    @Column(name = "address", nullable = false, length = 200)
    @Size(max = 200)
    @NotNull
    private String address;

    @Column(name = "lat", nullable = false, precision = 8, scale = 6)
    @NotNull
    @Digits(integer = 8, fraction = 6)
    private BigDecimal lat;

    @Column(name = "lng", nullable = false, precision = 9, scale = 6)
    @NotNull
    @Digits(integer = 9, fraction = 6)
    private BigDecimal lng;

    @Column(name = "has_parking", nullable = false)
    @NotNull
    private Boolean hasParking;

    @Column(name = "has_elevator", nullable = false)
    @NotNull
    private Boolean hasElevator;

    @Column(name = "since")
    private Date since;

    @ManyToMany(mappedBy = "stations")
    private Set<Track> tracks = new HashSet<>();

    public String getStationName() {
        return stationName.toString();
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getLat() {
        return lat;
    }

    public void setLat(BigDecimal lat) {
        this.lat = lat;
    }

    public BigDecimal getLng() {
        return lng;
    }

    public void setLng(BigDecimal lng) {
        this.lng = lng;
    }

    public Boolean getHasParking() {
        return hasParking;
    }

    public void setHasParking(Boolean hasParking) {
        this.hasParking = hasParking;
    }

    public Boolean getHasElevator() {
        return hasElevator;
    }

    public void setHasElevator(Boolean hasElevator) {
        this.hasElevator = hasElevator;
    }

    public Date getSince() {
        return since;
    }

    public void setSince(Date since) {
        this.since = since;
    }

    public Set<Track> getTracks() {
        return tracks;
    }

    public void setTracks(Set<Track> tracks) {
        this.tracks = tracks;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Station station = (Station) obj;
        return stationName.equals(station.stationName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stationName, address, lat, lng, hasParking, hasElevator, since);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(stationName);

        List<String> features = new ArrayList<>();
        if (hasElevator) {
            features.add("elevator");
        }
        if (hasParking) {
            features.add("parking");
        }
        if (!features.isEmpty()) {
            builder
                .append(" (")
                .append(String.join(", ", features))
                .append(')');
        }

        return builder.toString();
    }
}
