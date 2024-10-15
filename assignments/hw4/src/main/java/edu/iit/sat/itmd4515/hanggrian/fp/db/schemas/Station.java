package edu.iit.sat.itmd4515.hanggrian.fp.db.schemas;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.Year;
import java.util.ArrayList;
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

    @Column(name = "lat", precision = 8, scale = 6)
    @Digits(integer = 8, fraction = 6)
    private BigDecimal lat;

    @Column(name = "lng", precision = 9, scale = 6)
    @Digits(integer = 9, fraction = 6)
    private BigDecimal lng;

    @Column(name = "address", nullable = false, length = 200)
    @Size(max = 200)
    @NotNull
    private String address;

    @Column(name = "has_parking", nullable = false)
    @NotNull
    private Boolean hasParking;

    @Column(name = "has_elevator", nullable = false)
    @NotNull
    private Boolean hasElevator;

    @Column(name = "since", nullable = false)
    @NotNull
    private Year since;

    @ManyToMany(mappedBy = "stations")
    private Set<Track> tracks = new HashSet<>();

    public String getStationName() {
        return stationName.toString();
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public Year getSince() {
        return since;
    }

    public void setSince(Year since) {
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
        Station other = (Station) obj;
        return stationName.equals(other.stationName)
            && lat.equals(other.lat)
            && lng.equals(other.lng)
            && address.equals(other.address)
            && hasParking.equals(other.hasParking)
            && hasElevator.equals(other.hasElevator)
            && since.equals(other.since);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stationName, lat, lng, address, hasParking, hasElevator, since);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(stationName);

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
