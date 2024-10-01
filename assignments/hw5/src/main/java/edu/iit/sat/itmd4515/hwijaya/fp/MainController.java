package edu.iit.sat.itmd4515.hwijaya.fp;

import edu.iit.sat.itmd4515.hwijaya.fp.db.Cars;
import edu.iit.sat.itmd4515.hwijaya.fp.db.Databases;
import edu.iit.sat.itmd4515.hwijaya.fp.db.Stations;
import edu.iit.sat.itmd4515.hwijaya.fp.db.TrackStations;
import edu.iit.sat.itmd4515.hwijaya.fp.db.Tracks;
import edu.iit.sat.itmd4515.hwijaya.fp.db.Trains;
import edu.iit.sat.itmd4515.hwijaya.fp.db.schemas.Car;
import edu.iit.sat.itmd4515.hwijaya.fp.db.schemas.Station;
import edu.iit.sat.itmd4515.hwijaya.fp.db.schemas.Track;
import edu.iit.sat.itmd4515.hwijaya.fp.db.schemas.TrackStation;
import edu.iit.sat.itmd4515.hwijaya.fp.db.schemas.Train;
import edu.iit.sat.itmd4515.hwijaya.fp.ui.AboutDialog;
import edu.iit.sat.itmd4515.hwijaya.fp.ui.ChangeSeatDialog;
import edu.iit.sat.itmd4515.hwijaya.fp.ui.ChangeSerialDialog;
import edu.iit.sat.itmd4515.hwijaya.fp.ui.TrackChoiceDialog;
import jakarta.persistence.EntityManager;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitPane;
import javafx.stage.Stage;

import static javafx.scene.control.Alert.AlertType.CONFIRMATION;
import static javafx.scene.control.Alert.AlertType.INFORMATION;
import static javafx.scene.control.ButtonType.NO;
import static javafx.scene.control.ButtonType.OK;
import static javafx.scene.control.ButtonType.YES;
import static javafx.scene.control.SelectionMode.MULTIPLE;
import static javafx.scene.control.SelectionMode.SINGLE;

public class MainController implements Initializable {
    private EntityManager entityManager;

    @FXML public MenuItem toggle24hMenu;
    @FXML public MenuItem toggleElevatorMenu;
    @FXML public MenuItem toggleParkingMenu;
    @FXML public MenuItem changeSeatMenu;
    @FXML public MenuItem changeSerialMenu;
    @FXML public MenuItem deleteStationMenu;
    @FXML public MenuItem listStationsMenu;
    @FXML public MenuItem listTracksMenu;
    @FXML public MenuItem listTrainsMenu;
    @FXML public MenuItem listCarsMenu;
    @FXML public SplitPane splitPane;

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

    @FXML public ListView<Car> carList;
    @FXML public MenuItem listTrainsMenu2;
    @FXML public MenuItem changeSeatMenu2;

    @FXML public ListView<Train> trainList;
    @FXML public MenuItem listCarsMenu2;
    @FXML public MenuItem changeSerialMenu2;

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

        bindMenuToList(listTrainsMenu, carList);
        bindMenuToList(listTrainsMenu2, carList);
        bindMenuToList(changeSeatMenu, carList);
        bindMenuToList(changeSeatMenu2, carList);

        bindMenuToList(listCarsMenu, trainList);
        bindMenuToList(listCarsMenu2, trainList);
        bindMenuToList(changeSerialMenu, trainList);
        bindMenuToList(changeSerialMenu2, trainList);

        trackList.setItems(FXCollections.observableList(Tracks.selectAll(entityManager)));
        trackList.getSelectionModel().setSelectionMode(MULTIPLE);

        stationList.setItems(FXCollections.observableList(Stations.selectAll(entityManager)));
        stationList.getSelectionModel().setSelectionMode(MULTIPLE);

        carList.setItems(FXCollections.observableList(Cars.selectAll(entityManager)));
        carList.getSelectionModel().setSelectionMode(MULTIPLE);

        trainList.setItems(FXCollections.observableList(Trains.selectAll(entityManager)));
        trainList.getSelectionModel().setSelectionMode(SINGLE);
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
    public void listTrains() {
        trainList.getSelectionModel().clearSelection();
        carList
            .getSelectionModel()
            .getSelectedItems()
            .stream()
            .flatMap(track -> track.getTrains().stream())
            .forEach(station -> trainList.getSelectionModel().select(station));
        trainList.requestFocus();
    }

    @FXML
    public void listCars() {
        carList.getSelectionModel().clearSelection();
        trainList
            .getSelectionModel()
            .getSelectedItems()
            .stream()
            .flatMap(station -> station.getCars().stream())
            .forEach(track -> carList.getSelectionModel().select(track));
        carList.requestFocus();
    }

    @FXML
    public void addToTrack() {
        Station station = stationList.getSelectionModel().getSelectedItem();
        List<Track> availableTracks = new ArrayList<>(trackList.getItems());
        availableTracks.removeAll(station.getTracks());
        new TrackChoiceDialog("Add to Track", station.getStationName(), availableTracks)
            .showAndWait()
            .ifPresent(
                track -> {
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
        Station station = stationList.getSelectionModel().getSelectedItem();
        new TrackChoiceDialog("Remove from Track", station.getStationName(), station.getTracks())
            .showAndWait()
            .ifPresent(
                track -> {
                    TrackStations.delete(
                        entityManager,
                        TrackStations.selectByStation(entityManager, station).get(0)
                    );

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

        forceRefresh(
            trackList,
            Tracks.selectAll(entityManager),
            String.format(
                "%s 24-hours status from the %s line.",
                track.getIs24h() ? "Added" : "Removed",
                track.getTrackColor()
            )
        );
    }

    @FXML
    public void toggleElevator() {
        Station station = stationList.getSelectionModel().getSelectedItem();
        Stations.update(entityManager, station, !station.getHasElevator(), station.getHasParking());

        forceRefresh(
            stationList,
            Stations.selectAll(entityManager),
            String.format(
                "%s elevator feature from the %s station.",
                station.getHasElevator() ? "Added" : "Removed",
                station.getStationName()
            )
        );
    }

    @FXML
    public void toggleParking() {
        Station station = stationList.getSelectionModel().getSelectedItem();
        Stations.update(entityManager, station, station.getHasElevator(), !station.getHasParking());

        forceRefresh(
            stationList,
            Stations.selectAll(entityManager),
            String.format(
                "%s parking feature from the %s station.",
                station.getHasParking() ? "Added" : "Removed",
                station.getStationName()
            )
        );
    }

    @FXML
    public void changeSeat() {
        Car car = carList.getSelectionModel().getSelectedItem();
        new ChangeSeatDialog(car.getCarNo())
            .showAndWait()
            .ifPresent(
                s -> {
                    int result = Integer.parseInt(s);
                    Cars.updateSeat(entityManager, car, result);

                    forceRefresh(
                        carList,
                        Cars.selectAll(entityManager),
                        String.format("Updated seats to %d.", result)
                    );
                }
            );
    }

    @FXML
    public void changeSerial() {
        Train train = trainList.getSelectionModel().getSelectedItem();
        new ChangeSerialDialog(train.getTrainId())
            .showAndWait()
            .ifPresent(
                s -> {
                    String result = s.isBlank() ? null : s;
                    Trains.updateSerial(entityManager, train, result);

                    forceRefresh(
                        trainList,
                        Trains.selectAll(entityManager),
                        String.format("Updated locomotive serial to %s.", result)
                    );
                }
            );
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
        ((Stage) splitPane.getScene().getWindow()).setIconified(true);
    }

    @FXML
    public void zoom() {
        ((Stage) splitPane.getScene().getWindow()).setMaximized(true);
    }

    @FXML
    public void about() {
        new AboutDialog().showAndWait();
    }

    private static void bindMenuToList(MenuItem menu, ListView<?> list) {
        menu
            .disableProperty()
            .bind(
                Bindings
                    .size(list.getSelectionModel().getSelectedItems())
                    .isNotEqualTo(1)
            );
    }

    private static <T> void forceRefresh(ListView<T> list, List<T> collection, String message) {
        list.setItems(FXCollections.observableList(collection));
        new Alert(INFORMATION, message, OK).showAndWait();
    }
}
