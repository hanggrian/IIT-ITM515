package edu.iit.sat.itmd4515.uid.uidlab4;

import edu.iit.sat.itmd4515.uid.uidlab4.db.Databases;
import edu.iit.sat.itmd4515.uid.uidlab4.db.Stations;
import edu.iit.sat.itmd4515.uid.uidlab4.db.TrackStations;
import edu.iit.sat.itmd4515.uid.uidlab4.db.Tracks;
import edu.iit.sat.itmd4515.uid.uidlab4.db.schemas.Station;
import edu.iit.sat.itmd4515.uid.uidlab4.db.schemas.Track;
import edu.iit.sat.itmd4515.uid.uidlab4.db.schemas.TrackStation;
import edu.iit.sat.itmd4515.uid.uidlab4.ui.AboutDialog;
import jakarta.persistence.EntityManager;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Dialog;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import static javafx.scene.control.Alert.AlertType.CONFIRMATION;
import static javafx.scene.control.Alert.AlertType.INFORMATION;
import static javafx.scene.control.ButtonType.NO;
import static javafx.scene.control.ButtonType.OK;
import static javafx.scene.control.ButtonType.YES;

public class MainController implements Initializable {
    private EntityManager entityManager;

    @FXML public HBox hbox;
    @FXML public MenuItem toggle24hMenu;
    @FXML public MenuItem toggleElevatorMenu;
    @FXML public MenuItem toggleParkingMenu;
    @FXML public MenuItem deleteStationMenu;
    @FXML public MenuItem listStationsMenu;
    @FXML public MenuItem listTracksMenu;

    @FXML public ListView<Track> trackList;
    @FXML public MenuItem listStationsMenu2;
    @FXML public MenuItem toggle24hMenu2;

    @FXML public ListView<Station> stationList;
    @FXML public MenuItem listTracksMenu2;
    @FXML public MenuItem addToTrackMenu;
    @FXML public MenuItem removeFromTrackMenu;
    @FXML public MenuItem toggleElevatorMenu2;
    @FXML public MenuItem toggleParkingMenu2;
    @FXML public MenuItem deleteStationMenu2;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        entityManager = Databases.openSession();

        bindMenuToList(listStationsMenu, trackList);
        bindMenuToList(listStationsMenu2, trackList);
        bindMenuToList(toggle24hMenu, trackList);
        bindMenuToList(toggle24hMenu2, trackList);

        bindMenuToList(listTracksMenu, stationList);
        bindMenuToList(listTracksMenu2, stationList);
        bindMenuToList(addToTrackMenu, stationList);
        bindMenuToList(removeFromTrackMenu, stationList);
        bindMenuToList(toggleElevatorMenu, stationList);
        bindMenuToList(toggleElevatorMenu2, stationList);
        bindMenuToList(toggleParkingMenu, stationList);
        bindMenuToList(toggleParkingMenu2, stationList);
        bindMenuToList(deleteStationMenu, stationList);
        bindMenuToList(deleteStationMenu2, stationList);

        trackList.setItems(FXCollections.observableList(Tracks.selectAll(entityManager)));
        trackList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        stationList.setItems(FXCollections.observableList(Stations.selectAll(entityManager)));
        stationList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    @FXML
    public void listStations() {
        stationList.getSelectionModel().clearSelection();
        trackList
            .getSelectionModel()
            .getSelectedItems()
            .stream()
            .flatMap(track -> track.getStations().stream())
            .forEach(station -> stationList.getSelectionModel().select(station));
        stationList.requestFocus();
    }

    @FXML
    public void listTracks() {
        trackList.getSelectionModel().clearSelection();
        stationList
            .getSelectionModel()
            .getSelectedItems()
            .stream()
            .flatMap(station -> station.getTracks().stream())
            .forEach(track -> trackList.getSelectionModel().select(track));
        trackList.requestFocus();
    }

    @FXML
    public void addToTrack() {
        Dialog<Track> dialog = new ChoiceDialog<>(null, trackList.getItems());
        dialog.setTitle("Add to Track");
        dialog.setHeaderText("Add to Track");
        dialog.setContentText("Track");
        dialog.showAndWait().ifPresent(
            track -> {
                Station station = stationList.getSelectionModel().getSelectedItem();
                if (TrackStations
                    .selectByStation(entityManager, station)
                    .stream()
                    .anyMatch(trackStation -> trackStation.getTrack().equals(track))
                ) {
                    new Alert(
                        INFORMATION,
                        String.format(
                            "%s is already in the %s line.",
                            station.getStationName(),
                            track.getTrackColor()
                        ), OK
                    ).showAndWait();
                    return;
                }

                TrackStation trackStation = new TrackStation();
                trackStation.setStation(station);
                trackStation.setTrack(track);
                TrackStations.insert(entityManager, trackStation);

                station.getTracks().add(track);
                new Alert(
                    INFORMATION,
                    String.format(
                        "Successfully added %s in the %s line.",
                        station.getStationName(),
                        track.getTrackColor()
                    ), OK
                ).showAndWait();
            }
        );
    }

    @FXML
    public void removeFromTrack() {
        Dialog<Track> dialog = new ChoiceDialog<>(null, trackList.getItems());
        dialog.setTitle("Remove from Track");
        dialog.setHeaderText("Remove from Track");
        dialog.setContentText("Track");
        dialog.showAndWait().ifPresent(
            track -> {
                Station station = stationList.getSelectionModel().getSelectedItem();
                List<TrackStation> trackStations =
                    TrackStations.selectByStation(entityManager, station);
                if (trackStations.isEmpty()) {
                    new Alert(
                        INFORMATION,
                        String.format(
                            "%s does not belong in the %s line.",
                            station.getStationName(),
                            track.getTrackColor()
                        ), OK
                    ).showAndWait();
                    return;
                }

                TrackStations.delete(entityManager, trackStations.get(0));

                station.getTracks().remove(track);
                new Alert(
                    INFORMATION,
                    String.format(
                        "Successfully removed %s from the %s line.",
                        station.getStationName(),
                        track.getTrackColor()
                    ), OK
                ).showAndWait();
            }
        );
    }

    @FXML
    public void toggle24h() {
        Track track = trackList.getSelectionModel().getSelectedItem();
        Tracks.update24h(entityManager, track);

        // force refresh
        trackList.setItems(FXCollections.observableList(Tracks.selectAll(entityManager)));
        new Alert(
            INFORMATION,
            String.format(
                "%s 24-hours status from the %s line.",
                track.getIs24h() ? "Added" : "Removed",
                track.getTrackColor()
            ), OK
        ).showAndWait();
    }

    @FXML
    public void toggleElevator() {
        Station station = stationList.getSelectionModel().getSelectedItem();
        Stations.update(
            entityManager,
            station,
            !station.getHasElevator(),
            station.getHasParking()
        );

        // force refresh
        stationList.setItems(FXCollections.observableList(Stations.selectAll(entityManager)));
        new Alert(
            INFORMATION,
            String.format(
                "%s elevator feature from the %s station.",
                station.getHasElevator() ? "Added" : "Removed",
                station.getStationName()
            ), OK
        ).showAndWait();
    }

    @FXML
    public void toggleParking() {
        Station station = stationList.getSelectionModel().getSelectedItem();
        Stations.update(
            entityManager,
            station,
            station.getHasElevator(),
            !station.getHasParking()
        );

        // force refresh
        stationList.setItems(FXCollections.observableList(Stations.selectAll(entityManager)));
        new Alert(
            INFORMATION,
            String.format(
                "%s parking feature from the %s station.",
                station.getHasParking() ? "Added" : "Removed",
                station.getStationName()
            ), OK
        ).showAndWait();
    }

    @FXML
    public void deleteStation() {
        Station station = stationList.getSelectionModel().getSelectedItem();
        Dialog<ButtonType> dialog =
            new Alert(
                CONFIRMATION,
                String.format("Are you sure to delete station %s?", station.getStationName()),
                YES,
                NO
            );
        dialog.setTitle("Delete Station");
        dialog.setHeaderText("Delete Station");
        dialog.showAndWait().ifPresent(
            buttonType -> {
                if (buttonType != YES) {
                    return;
                }
                Stations.delete(entityManager, station);

                stationList.getItems().remove(station);
                new Alert(
                    INFORMATION,
                    String.format("Removed %s station.", station.getStationName()),
                    OK
                ).showAndWait();
            }
        );
    }

    @FXML
    public void quit() {
        entityManager.close();
        Databases.close();
        Platform.exit();
        System.exit(0);
    }

    @FXML
    public void minimize() {
        ((Stage) hbox.getScene().getWindow()).setIconified(true);
    }

    @FXML
    public void zoom() {
        ((Stage) hbox.getScene().getWindow()).setMaximized(true);
    }

    @FXML
    public void about() {
        new AboutDialog().showAndWait();
    }

    private void bindMenuToList(MenuItem menu, ListView<?> list) {
        menu
            .disableProperty()
            .bind(
                Bindings
                    .size(list.getSelectionModel().getSelectedItems())
                    .isNotEqualTo(1)
            );
    }
}
