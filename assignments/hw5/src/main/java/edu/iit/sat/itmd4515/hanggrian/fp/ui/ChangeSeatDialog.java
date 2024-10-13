package edu.iit.sat.itmd4515.hanggrian.fp.ui;

import javafx.beans.binding.Bindings;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Tooltip;

import static javafx.scene.control.ButtonType.OK;

/**
 * A text input dialog that restricts user input to non-null and non-negative numeric for
 * {@link edu.iit.sat.itmd4515.hanggrian.fp.db.schemas.Car#setSeats(Integer)}.
 */
public class ChangeSeatDialog extends TextInputDialog {
    public ChangeSeatDialog(String carNo) {
        super();
        setTitle("Change Seat Count");
        setHeaderText(String.format("Car '%s'", carNo));
        setContentText("Digits");

        getEditor().setTooltip(new Tooltip("Enter a non-decimal number."));

        getDialogPane()
            .lookupButton(OK)
            .disableProperty()
            .bind(
                Bindings.createBooleanBinding(
                    () ->
                        getEditor().getText().isBlank()
                            || !isPositiveNumeric(getEditor().getText()),
                    getEditor().textProperty()
                )
            );
    }

    private static boolean isPositiveNumeric(String s) {
        try {
            return Integer.parseInt(s) >= 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
