package edu.iit.sat.itmd4515.hanggrian.fp.ui;

import edu.iit.sat.itmd4515.hanggrian.fp.db.schemas.Track;
import java.util.Collection;
import javafx.scene.control.ChoiceDialog;

/**
 * Convenient class of choice dialog to choose track with pre-defined properties.
 */
public class TrackChoiceDialog extends ChoiceDialog<Track> {
    public TrackChoiceDialog(String title, String stationName, Collection<Track> tracks) {
        super(null, tracks);
        setTitle(title);
        setHeaderText(String.format("Station '%s'", stationName));
        setContentText("Track");
    }
}
