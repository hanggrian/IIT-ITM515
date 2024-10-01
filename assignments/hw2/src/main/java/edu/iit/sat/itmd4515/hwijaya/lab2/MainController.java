package edu.iit.sat.itmd4515.hwijaya.lab2;

import edu.iit.sat.itmd4515.hwijaya.lab2.db.Databases;
import edu.iit.sat.itmd4515.hwijaya.lab2.db.Payments;
import edu.iit.sat.itmd4515.hwijaya.lab2.db.Rentals;
import edu.iit.sat.itmd4515.hwijaya.lab2.db.schemas.Film;
import edu.iit.sat.itmd4515.hwijaya.lab2.db.schemas.Payment;
import edu.iit.sat.itmd4515.hwijaya.lab2.db.schemas.Rental;
import edu.iit.sat.itmd4515.hwijaya.lab2.ui.AboutDialog;
import edu.iit.sat.itmd4515.hwijaya.lab2.ui.AddPaymentDialog;
import edu.iit.sat.itmd4515.hwijaya.lab2.ui.ChangeFilmDialog;
import java.net.URL;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.function.Function;
import javafx.application.Platform;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Control;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.controlsfx.control.MasterDetailPane;
import org.hibernate.Session;

import static javafx.beans.binding.Bindings.createBooleanBinding;
import static javafx.beans.binding.Bindings.createObjectBinding;
import static javafx.beans.binding.Bindings.not;

/**
 * The controller that bounds to {@code controller_main.fxml} from the resources.
 */
public class MainController implements Initializable {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private static final NumberFormat NUMBER_FORMAT = NumberFormat.getInstance(Locale.US);

    @FXML public MenuItem rentalRemoveMenu;
    @FXML public MenuItem filmChangeMenu;
    @FXML public MenuItem paymentAddMenu;
    @FXML public MenuItem paymentRemoveMenu;

    @FXML public MasterDetailPane masterDetailPane;

    @FXML public TableView<Rental> rentalTable;
    @FXML public TableColumn<Rental, Integer> rentalColumnId;
    @FXML public TableColumn<Rental, String> rentalColumnDate;
    @FXML public TableColumn<Rental, String> rentalColumnReturnDate;
    @FXML public TableColumn<Rental, String> rentalColumnStaff;
    @FXML public MenuItem rentalRemoveMenu2;
    @FXML public MenuItem filmChangeMenu2;
    @FXML public RadioButton rentalDateToggle;
    @FXML public DatePicker rentalDatePicker;
    @FXML public Button rentalDatePrevButton;
    @FXML public Button rentalDateNextButton;
    @FXML public RadioButton rentalIdToggle;
    @FXML public TextField rentalIdField;

    @FXML public Label rateLabel;
    @FXML public Label storeLabel;
    @FXML public Label staffLabel;
    @FXML public Label customerLabel;
    @FXML public Label addressLabel;
    @FXML public Label titleLabel;
    @FXML public Label subtitleLabel;
    @FXML public Label descriptionLabel;
    @FXML public Label captionLabel;
    @FXML public TableView<Payment> paymentTable;
    @FXML public TableColumn<Payment, String> paymentColumnDate;
    @FXML public TableColumn<Payment, String> paymentColumnAmount;
    @FXML public MenuItem paymentAddMenu2;
    @FXML public MenuItem paymentRemoveMenu2;

    private Session session;
    private SimpleBooleanProperty labelsTrigger;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        session = Databases.openSession();
        labelsTrigger = new SimpleBooleanProperty(false);

        bindRadioButton(
            rentalDateToggle,
            rentalDatePicker,
            rentalDatePrevButton,
            rentalDateNextButton
        );
        bindRadioButton(rentalIdToggle, rentalIdField);

        bindMenuToTable(rentalRemoveMenu, rentalRemoveMenu2, rentalTable);
        bindMenuToTable(filmChangeMenu, filmChangeMenu2, rentalTable);
        bindMenuToTable(paymentAddMenu, paymentAddMenu2, rentalTable);
        bindMenuToTable(paymentRemoveMenu, paymentRemoveMenu2, paymentTable);

        bindInformationLabel(
            rateLabel,
            rental -> {
                Film film = rental.getInventory().getFilm();
                return String.format("$%s rent or $%s",
                    NUMBER_FORMAT.format(film.getRentalRate()),
                    NUMBER_FORMAT.format(film.getReplacementCost())
                );
            }
        );
        bindInformationLabel(
            storeLabel,
            rental -> rental.getInventory().getStore().getAddress().toString()
        );
        bindInformationLabel(
            staffLabel,
            rental ->
                chainStrings(
                    rental.getStaff().getLastName(),
                    rental.getStaff().getFirstName()
                )
        );
        bindInformationLabel(
            customerLabel,
            rental ->
                chainStrings(
                    rental.getCustomer().getLastName(),
                    rental.getCustomer().getFirstName()
                )
        );
        bindInformationLabel(
            addressLabel,
            rental -> rental.getCustomer().getAddress().toString()
        );
        bindInformationLabel(
            titleLabel,
            rental -> rental.getInventory().getFilm().toString()
        );
        bindInformationLabel(
            subtitleLabel,
            rental -> {
                Film film = rental.getInventory().getFilm();
                return String.format(
                    "%s, %dm, %s, %s",
                    film.getRating().toString(),
                    film.getLength(),
                    film.getCategories().iterator().next().getName(),
                    film.getLanguage().getName()
                );
            }
        );
        bindInformationLabel(
            descriptionLabel,
            rental -> rental.getInventory().getFilm().getDescription()
        );
        bindInformationLabel(
            captionLabel,
            rental ->
                chainStrings(
                    rental
                        .getInventory()
                        .getFilm()
                        .getActors()
                        .stream()
                        .map(actor -> actor.getFirstName() + ' ' + actor.getLastName())
                        .toArray(String[]::new)
                )
        );

        masterDetailPane
            .showDetailNodeProperty()
            .bind(rentalTable.getSelectionModel().selectedItemProperty().isNotNull());

        rentalColumnId.setCellValueFactory(
            param -> new SimpleObjectProperty<>(param.getValue().getRentalId())
        );
        rentalColumnDate.setCellValueFactory(
            param -> new SimpleStringProperty(DATE_FORMAT.format(param.getValue().getRentalDate()))
        );
        rentalColumnReturnDate.setCellValueFactory(
            param -> new SimpleStringProperty(DATE_FORMAT.format(param.getValue().getReturnDate()))
        );
        rentalColumnStaff.setCellValueFactory(
            param -> new SimpleStringProperty(param.getValue().getStaff().getUsername())
        );
        rentalTable.itemsProperty().bind(
            createObjectBinding(
                () -> {
                    if (rentalDateToggle.isSelected()) {
                        return rentalDatePicker.getValue() == null
                            ? FXCollections.emptyObservableList()
                            : FXCollections.observableArrayList(
                            Rentals.selectByDate(session, rentalDatePicker.getValue()));
                    }
                    return rentalIdField.getText().isBlank()
                        ? FXCollections.emptyObservableList()
                        : FXCollections.observableArrayList(
                        Rentals.selectById(session, Integer.parseInt(rentalIdField.getText())));
                },
                rentalDateToggle.selectedProperty(),
                rentalDatePicker.valueProperty(),
                rentalIdToggle.selectedProperty(),
                rentalIdField.textProperty()
            )
        );

        paymentColumnDate.setCellValueFactory(
            param -> new SimpleStringProperty(DATE_FORMAT.format(param.getValue().getPaymentDate()))
        );
        paymentColumnAmount.setCellValueFactory(
            param ->
                new SimpleStringProperty('$' + NUMBER_FORMAT.format(param.getValue().getAmount()))
        );
        paymentTable.itemsProperty().bind(
            createObjectBinding(
                () -> {
                    Rental rental = rentalTable.getSelectionModel().getSelectedItem();
                    if (rental == null) {
                        return FXCollections.emptyObservableList();
                    }
                    return FXCollections.observableArrayList(
                        Payments.selectByRentalId(session, rental));
                },
                rentalTable.getSelectionModel().selectedItemProperty()
            )
        );

        rentalDatePicker.setValue(SakilaApp.EARLIEST_RENTAL_DATE); // earliest retail date
    }

    @FXML
    public void prevRental() {
        rentalDatePicker.setValue(rentalDatePicker.getValue().minusDays(1));
    }

    @FXML
    public void nextRental() {
        rentalDatePicker.setValue(rentalDatePicker.getValue().plusDays(1));
    }

    @FXML
    public void changeFilm() {
        Rental rental = rentalTable.getSelectionModel().getSelectedItem();
        Film film = rental.getInventory().getFilm();
        new ChangeFilmDialog(film)
            .showAndWait()
            .ifPresent(
                film2 -> {
                    Rentals.updateFilm(session, rental, film2);
                    labelsTrigger.set(!labelsTrigger.get());
                }
            );
    }

    @FXML
    public void addPayment() {
        new AddPaymentDialog(rentalTable.getSelectionModel().getSelectedItem())
            .showAndWait()
            .ifPresent(payment -> paymentTable.getItems().add(payment));
    }

    @FXML
    public void removePayment() {
        Payment payment = paymentTable.getSelectionModel().getSelectedItem();
        new Alert(
            Alert.AlertType.CONFIRMATION,
            String.format(
                "Confirm to delete $%s received at %s?",
                NUMBER_FORMAT.format(payment.getAmount()),
                DATE_FORMAT.format(payment.getPaymentDate())
            ),
            ButtonType.YES,
            ButtonType.NO
        ).showAndWait()
            .ifPresent(
                buttonType -> {
                    if (buttonType != ButtonType.YES) {
                        return;
                    }
                    Payments.remove(session, payment);
                    paymentTable.getItems().remove(payment);
                }
            );
    }

    @FXML
    public void quit() {
        session.close();
        Databases.close();
        Platform.exit();
        System.exit(0);
    }

    @FXML
    public void minimize() {
        ((Stage) masterDetailPane.getScene().getWindow()).setIconified(true);
    }

    @FXML
    public void zoom() {
        ((Stage) masterDetailPane.getScene().getWindow()).setMaximized(true);
    }

    @FXML
    public void about() {
        new AboutDialog().showAndWait();
    }

    private void bindRadioButton(RadioButton button, Control... controls) {
        BooleanBinding binding = not(button.selectedProperty());
        for (Control control : controls) {
            control.disableProperty().bind(binding);
        }
        button.selectedProperty().addListener(observable -> controls[0].requestFocus());
    }

    private void bindInformationLabel(Label label, Function<Rental, String> function) {
        label.textProperty().bind(
            createObjectBinding(
                () -> {
                    Rental rental = rentalTable.getSelectionModel().getSelectedItem();
                    if (rental == null) {
                        return "–";
                    }
                    return function.apply(rental);
                },
                rentalTable.getSelectionModel().selectedItemProperty(),
                labelsTrigger
            )
        );
    }

    private void bindMenuToTable(MenuItem menu, MenuItem menu2, TableView<?> table) {
        menu.disableProperty().bind(
            createBooleanBinding(
                () -> table.getSelectionModel().getSelectedItem() == null,
                table.getSelectionModel().selectedItemProperty()
            )
        );
        menu2.disableProperty().bind(menu.disableProperty());
    }

    private static String chainStrings(String... array) {
        StringBuilder builder = new StringBuilder();
        for (String s : array) {
            if (s == null || s.isBlank()) {
                continue;
            }
            if (builder.length() == 0) {
                builder.append(s);
                continue;
            }
            builder.append(", ");
            builder.append(s);
        }
        return builder.toString();
    }
}
