package edu.iit.sat.itmd4515.hanggrian.fp;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * An app for CTA schema found in {@code cta-schema.sql}.
 */
public class CtaApp extends Application {
    private static final Logger LOGGER = LoggerFactory.getLogger(CtaApp.class);

    @Override
    public void start(Stage stage) {
        Region root;
        try {
            FXMLLoader loader =
                new FXMLLoader(CtaApp.class.getResource("/controller_main.fxml"));
            root = loader.load();
        } catch (IOException e) {
            LOGGER.error("FXML resources error.", e);
            throw new RuntimeException(e);
        }

        stage.setTitle("CTA Stations");
        stage.setScene(new Scene(root));
        stage.setMinWidth(720);
        stage.setMinHeight(480);
        stage.setWidth(720);
        stage.setHeight(480);
        stage.show();
    }
}
