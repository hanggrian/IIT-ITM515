package edu.iit.sat.itmd4515.hwijaya.lab2;

import java.io.IOException;
import java.time.LocalDate;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A single window desktop application that manages film rental.
 */
public class SakilaApp extends Application {
    private static final Logger LOGGER = LoggerFactory.getLogger(SakilaApp.class);

    public static final LocalDate EARLIEST_RENTAL_DATE = LocalDate.of(2005, 5, 24);

    @Override
    public void start(Stage stage) {
        Region root;
        try {
            FXMLLoader loader =
                new FXMLLoader(SakilaApp.class.getResource("/controller_main.fxml"));
            root = loader.load();
        } catch (IOException e) {
            LOGGER.error("FXML resources error.", e);
            throw new RuntimeException(e);
        }

        stage.setTitle("Sakila Rentals");
        stage.setScene(new Scene(root));
        stage.setMinWidth(800);
        stage.setMinHeight(600);
        stage.setWidth(800);
        stage.setHeight(600);
        stage.show();
    }
}
