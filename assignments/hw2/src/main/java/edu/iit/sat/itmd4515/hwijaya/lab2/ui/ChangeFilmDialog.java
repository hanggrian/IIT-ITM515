package edu.iit.sat.itmd4515.hwijaya.lab2.ui;

import edu.iit.sat.itmd4515.hwijaya.lab2.db.Databases;
import edu.iit.sat.itmd4515.hwijaya.lab2.db.Films;
import edu.iit.sat.itmd4515.hwijaya.lab2.db.schemas.Film;
import javafx.collections.FXCollections;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.VBox;
import org.hibernate.Session;

import static javafx.application.Platform.runLater;
import static javafx.beans.binding.Bindings.createBooleanBinding;
import static javafx.beans.binding.Bindings.createObjectBinding;
import static javafx.scene.input.MouseButton.PRIMARY;

/**
 * Custom {@link javafx.scene.control.ChoiceDialog} that can be filtered by user input, displays
 * up to 10 items.
 */
public class ChangeFilmDialog extends Dialog<Film> {
    public ChangeFilmDialog(Film film) {
        setTitle("Change Film");
        setHeaderText(getTitle());

        Session session = Databases.openSession();
        Tooltip tooltip = new Tooltip("Films are limited to store inventories.");

        TextField field = new TextField();
        field.setPromptText("Title");
        field.setTooltip(tooltip);

        ListView<Film> list = new ListView<>();
        list.setTooltip(tooltip);
        list.setPrefHeight(250);
        list.itemsProperty().bind(
            createObjectBinding(
                () -> {
                    if (field.getText().isBlank()) {
                        return FXCollections.emptyObservableList();
                    }
                    return FXCollections.observableArrayList(
                        Films.selectByTitleUpToTen(session, field.getText()));
                },
                field.textProperty()
            )
        );
        list.setOnMouseClicked(
            event -> {
                if (!event.getButton().equals(PRIMARY) || event.getClickCount() != 2) {
                    return;
                }
                if (list.getSelectionModel().getSelectedItem() == null) {
                    return;
                }
                setResult(list.getSelectionModel().getSelectedItem());
                close();
            }
        );

        VBox vbox = new VBox();
        vbox.setSpacing(10);
        vbox.getChildren().add(field);
        vbox.getChildren().add(list);

        getDialogPane().setContent(vbox);
        getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        getDialogPane()
            .lookupButton(ButtonType.OK)
            .disableProperty()
            .bind(
                createBooleanBinding(
                    () -> list.getSelectionModel().getSelectedItem() == null,
                    list.getSelectionModel().selectedItemProperty()
                )
            );

        setResultConverter(
            param -> {
                if (param != ButtonType.OK) {
                    return null;
                }
                session.close();
                return list.getSelectionModel().getSelectedItem();
            }
        );

        runLater(() -> field.setText(film.getTitle()));
    }
}
