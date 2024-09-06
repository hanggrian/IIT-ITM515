package edu.iit.sat.itmd4515.uid.uidlab2.ui;

import edu.iit.sat.itmd4515.uid.uidlab2.db.Databases;
import edu.iit.sat.itmd4515.uid.uidlab2.db.Payments;
import edu.iit.sat.itmd4515.uid.uidlab2.db.schemas.Payment;
import edu.iit.sat.itmd4515.uid.uidlab2.db.schemas.Rental;
import java.math.BigDecimal;
import javafx.geometry.Pos;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import org.hibernate.Session;

import static javafx.application.Platform.runLater;
import static javafx.beans.binding.Bindings.createBooleanBinding;

/**
 * Custom {@link javafx.scene.control.TextInputDialog} that restricts to a decimal value.
 */
public class AddPaymentDialog extends Dialog<Payment> {
    public AddPaymentDialog(Rental rental) {
        setTitle("Add Payment");
        setHeaderText(getTitle());

        TextField field = new TextField();
        field.setPromptText("Amount");
        field.setTooltip(
            new Tooltip("Enter a numeric value with optional precision as the payment amount.")
        );

        HBox hbox = new HBox();
        hbox.setSpacing(10);
        hbox.setAlignment(Pos.BASELINE_CENTER);
        hbox.getChildren().add(new Label("Amount:"));
        hbox.getChildren().add(field);

        getDialogPane().setContent(hbox);
        getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        getDialogPane()
            .lookupButton(ButtonType.OK)
            .disableProperty()
            .bind(
                createBooleanBinding(
                    () -> {
                        if (field.getText().isBlank()) {
                            return true;
                        }
                        try {
                            double amount = Double.parseDouble(field.getText());
                            return amount <= 0;
                        } catch (NumberFormatException e) {
                            return true;
                        }
                    },
                    field.textProperty()
                )
            );

        setResultConverter(
            param -> {
                if (param != ButtonType.OK) {
                    return null;
                }
                Session session = Databases.open();
                Payment payment = Payments.insert(session, new BigDecimal(field.getText()), rental);
                session.close();
                return payment;
            });

        runLater(field::requestFocus);
    }
}
