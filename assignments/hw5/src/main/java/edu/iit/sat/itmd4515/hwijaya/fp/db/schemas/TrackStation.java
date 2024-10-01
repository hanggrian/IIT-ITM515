package edu.iit.sat.itmd4515.hwijaya.fp.db.schemas;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "track_station")
public class TrackStation {
    @Id
    @ManyToOne
    @JoinColumn(name = "track_color")
    private Track track;

    @Id
    @ManyToOne
    @JoinColumn(name = "station_name")
    private Station station;

    public Track getTrack() {
        return track;
    }

    public void setTrack(Track track) {
        this.track = track;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    @Override
    public int hashCode() {
        return Objects.hash(track.getTrackColor(), station.getStationName());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        TrackStation other = (TrackStation) obj;
        return track.getTrackColor().equals(other.track.getTrackColor())
            && station.getStationName().equals(other.station.getStationName());
    }
}
