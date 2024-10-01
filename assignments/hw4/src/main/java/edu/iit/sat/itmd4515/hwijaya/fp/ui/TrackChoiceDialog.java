package edu.iit.sat.itmd4515.hwijaya.fp.ui;

import edu.iit.sat.itmd4515.hwijaya.fp.db.schemas.Track;
import java.util.Collection;
import javafx.scene.control.ChoiceDialog;

/**
 * Convenient class of choice dialog to choose track with pre-defined properties.
 */
public class TrackChoiceDialog extends ChoiceDialog<Track> {
    public TrackChoiceDialog(String title, Collection<Track> tracks) {
        super(null, tracks);
        setTitle(title);
        setHeaderText(title);
        setContentText("Track");
    }
}
