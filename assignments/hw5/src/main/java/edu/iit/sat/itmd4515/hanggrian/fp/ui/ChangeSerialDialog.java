package edu.iit.sat.itmd4515.hanggrian.fp.ui;

import javafx.beans.binding.Bindings;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Tooltip;

import static javafx.scene.control.ButtonType.OK;

/**
 * A text input dialog that restricts user input to nullable text of maximum 20 characters.
 * {@link edu.iit.sat.itmd4515.hanggrian.fp.db.schemas.Train#setLocomotiveSerial(String)}
 */
public class ChangeSerialDialog extends TextInputDialog {
    public ChangeSerialDialog(int trainId) {
        super();
        setTitle("Change Serial Number");
        setHeaderText(String.format("Train #%d", trainId));
        setContentText("Text");

        getEditor().setTooltip(new Tooltip("Enter text no more than 20 characters."));

        getDialogPane()
            .lookupButton(OK)
            .disableProperty()
            .bind(
                Bindings.createBooleanBinding(
                    () -> getEditor().getText().length() > 20,
                    getEditor().textProperty()
                )
            );
    }
}
