package edu.iit.sat.itmd4515.uid.uidlab2.ui;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Dialog;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.VBox;

import static java.awt.Desktop.Action.BROWSE;
import static javafx.scene.control.Alert.AlertType.ERROR;
import static javafx.scene.control.ButtonType.CLOSE;

/**
 * An informational popup describing what the application is, it has no return value.
 */
public class AboutDialog extends Dialog<Void> {
    public AboutDialog() {
        setTitle("About");
        setHeaderText("About");
        setResizable(false);
        setContentText(
            "A simple JavaFX app that performs basic "
                + "CRUD operations to sample Sakila database");

        Hyperlink hyperlink = new Hyperlink("View in GitHub");
        hyperlink.setOnAction(
            event -> {
                try {
                    Desktop desktop = getDesktop();
                    if (desktop != null) {
                        desktop.browse(new URI("https://github.com/hanggrian/IIT-ITM515"));
                    }
                } catch (IOException | URISyntaxException e) {
                    // no logger, unlikely to throw error
                    throw new RuntimeException(e);
                }
            });
        VBox hyperlinkContainer = new VBox(hyperlink);
        hyperlinkContainer.setPadding(new Insets(20));

        getDialogPane().setExpandableContent(hyperlinkContainer);
        getDialogPane().getButtonTypes().add(CLOSE);
    }

    private static Desktop getDesktop() {
        if (!Desktop.isDesktopSupported()) {
            new Alert(ERROR, "Desktop to open URL not found.", CLOSE).showAndWait();
            return null;
        }
        Desktop desktop = Desktop.getDesktop();
        if (!desktop.isSupported(BROWSE)) {
            new Alert(ERROR, "Desktop to open URL not found.", CLOSE).showAndWait();
            return null;
        }
        return desktop;
    }
}
