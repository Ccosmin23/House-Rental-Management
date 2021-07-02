package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Client;
import model.Employee;
import model.MEmployee;
import model.RentalHouse;
import model.report.Report;
import model.report.ReportFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;


public class CEmployee implements Initializable, Observer {

    //COLUMNS on RENTALS
    private final String[] cityComboText = {"Oraș", "City", "Stadt", "Pueblo"};
    private final String[] countryComboText = {"Județ", "County", "Bezirk", "Condado"};
    private final String[] priceComboText = {"Preț", "Price", "Preis", "Precio"};
    private final String[] housingTypeComboText = {"Tip locuinta", "Housing type", "Gehäusetyp", "Tipo de vivienda"};
    private final String[] bedroomsComboText = {"Dormitoare", "Bedrooms", "Schlafzimmer", "Dormitorios"};
    private final String[] usableSurfaceComboText = {"Suprafata locuinta", "Usable surface", "Nutzbare Oberfläche", "Superficie utilizable"};
    private final String[] gardenSurfaceComboText = {"Suprafata gradina", "Garden surface", "Gartenfläche", "Superficie del jardín"};
    private final String[] floorsComboText = {"Etaje", "Floors", "Böden", "Pisos"};
    private final String[] houseNameComboText = {"Numele locuintei", "House name", "Hausname", "Nombre de la casa"};
    //COLUMNS on CLIENTS
    private final String[] firstNameText = {"Prenume", "First Name", "Vorname", "Primer nombre"};
    private final String[] lastNameText = {"Nume", "Last Name", "Nachname", "Apellido"};
    private final String[] searchButtonText = {"Cauta", "Search", "Suche", "Buscar"};
    private final String[] viewRentalsButtonText = {"Vezi Locuinte", "View Rentals", "Mietobjekte ansehen", "Ver alquileres"};
    private final String[] viewClientsButtonText = {"Vezi Clienti", "View Clients", "Kunden anzeigen", "Ver clientes"};
    private final String[] saveReportsButtonText = {"Salveaza Raport", "Save Reports", "Berichte speichern", "Guardar informes"};
    private final String[] viewStatisticsButtonText = {"Vezi statistici", "View Statistics", "Statistiken ansehen", "Ver estadísticas"};
    private final String[] signInButtonText = {"Delogare", "Sign out", "Ausloggen", "Desconectar"};
    private final String[] chooseReportTypeText = {"Alege raport", "Choose report", "Bericht auswählen", "Elija informe"};
    //CRUD on RENTALS
    private final String[] createRentalButtonText = {"Creaza", "Create", "Erstellen", "Crear"};
    private final String[] readRentalButtonText = {"Citeste", "Read", "Lesen", "Leer"};
    private final String[] updateRentalButtonText = {"Actualizeaza", "Update", "Aktualisieren", "Actualizar"};
    private final String[] deleteRentalButtonText = {"Sterge", "Delete", "löschen", "Eliminar"};
    //CRUD on CLIENTS
    private final String[] createClientButtonText = {"Creaza", "Create", "Erstellen", "Crear"};
    private final String[] readClientButtonText = {"Citeste", "Read", "Lesen", "Leer"};
    private final String[] updateClientButtonText = {"Actualizeaza", "Update", "Aktualisieren", "Actualizar"};
    private final String[] deleteClientButtonText = {"Sterge", "Delete", "löschen", "Eliminar"};
    //private MEmployee model;
    private final String[] notificationText = {"Anunturi", "Notifications", "Benachrichtigungen", "Notificaciones"};
    public PieChart pieChart;
    public BarChart<String, Number> barChart;
    @FXML
    public AnchorPane newAccount_pane;
    public Button innerSignIn_btn, goBack_btn, createAccount_btn;
    public TextField notification_textField, tf_firstName, tf_email, tf_lastName, tf_username, tf_password;
    public AnchorPane menuClient_pane, clients_pane, rentals_pane, employee_menu2, textfield_menu, employee_menu3, employeeBase_pane, charts_pane, employee_menu, combos_pane;
    public ComboBox<String> reports_combo, city_combo, country_combo, houseName_combo, types_combo;
    public ComboBox<Integer> surface_combo, garden_combo, bedroom_combo, floors_combo, price_combo;
    public Button deleteClient_btn, updateClient_btn, readClient_btn, createClient_btn;
    public Button admin_btn, signIn_btn, clear_btn, view_statistics, employee_signOut, view_rentals, view_clients, save_reports, edit_clients;
    public Button createRental_btn, readRental_btn, updateRental_btn, deleteRental_btn;
    public Button register_btn, search_btn;
    public TableView<Client> clients_table;
    public TableView<RentalHouse> rentals_table;
    public TableColumn<Client, String> client_firstName, client_lastName, client_email, client_status, clientHouseName, client_city, client_housingType, client_housePrice;
    public TableColumn<RentalHouse, String> city_column, country_column, price_column, housing_column, bedrooms_column, surface_column, garden_column, floors_column, rentalHouseName_column;
    public TextField tf_city, tf_country, tf_bedrooms, tf_houseName, tf_price, tf_garden, tf_surface, tf_housingType, tf_floors;
    public TextField tf_clientLastName, tf_clientFirstName, tf_clientEmail, tf_clientStatus, tf_clientHouseRented, tf_clientCity, tf_clientHousingType, tf_clientPrice;
    //Objects
    CClient cClient;
    CEmployee cEmployee;
    ObservableList<String> reports_list = FXCollections.observableArrayList("csv", "json");
    private List<Employee> employees;
    private List<Client> clients;
    private List<RentalHouse> rentalHouses;
    private String notificationsText = "";
    @FXML
    private Button ro_button, en_button, de_button, es_button;
    private Employee employee;
    private Client client;
    private RentalHouse rentalHouse;
    private RentalHouse rentalHouse2;
    private MEmployee model;
    private Scene employeeScene;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void init() {

        initRentalsTable();
        initClientsTable();
        rentalHouses = model.getRentalHouse().readRentalhouse();
        clients = model.getClient().readClient();

        displayTableRentals(this.rentalHouses);
        displayClientTable(this.clients);

        //set list of values in COMBO-BOXES
        setComboItems();

        employeeBase_pane.setVisible(true);
        clients_pane.setVisible(false);                  // clients_pane is the pane containing the text fields for CLIENTS CRUD operations
        employee_menu.setVisible(true);
        employee_menu2.setVisible(true);
        employee_menu3.setVisible(true);
        combos_pane.setVisible(true);
        rentals_pane.setVisible(true);                  // rentals_pane is the pane containing the text fields for RENTALS CRUD operations
        charts_pane.setVisible(true);
        textfield_menu.setVisible(true);               // anchor pane containing text fields for RENTALS
        view_clients.setVisible(true);
        save_reports.setVisible(true);
    }


    @FXML
    void romanianLanguage() {
        /*
         * ToDo : call romanianLanguage() method from CSignIn to change simultaneously
         *   cSignIn.romanianLanguage();
         *  Tested! but it do not work..
         * */
        tf_clientFirstName.setPromptText(firstNameText[0]);
        tf_clientLastName.setPromptText(lastNameText[0]);
        tf_clientHouseRented.setPromptText(houseNameComboText[0]);
        tf_clientCity.setPromptText(cityComboText[0]);
        tf_clientHousingType.setPromptText(housingTypeComboText[0]);
        tf_clientPrice.setPromptText(priceComboText[0]);

        tf_city.setPromptText(cityComboText[0]);
        tf_country.setPromptText(countryComboText[0]);
        tf_bedrooms.setPromptText(bedroomsComboText[0]);
        tf_houseName.setPromptText(houseNameComboText[0]);
        tf_price.setPromptText(priceComboText[0]);
        tf_garden.setPromptText(gardenSurfaceComboText[0]);
        tf_surface.setPromptText(usableSurfaceComboText[0]);
        tf_housingType.setPromptText(housingTypeComboText[0]);
        tf_floors.setPromptText(floorsComboText[0]);


        city_combo.setPromptText(cityComboText[0]);
        country_combo.setPromptText(countryComboText[0]);
        price_combo.setPromptText(priceComboText[0]);
        types_combo.setPromptText(housingTypeComboText[0]);
        bedroom_combo.setPromptText(bedroomsComboText[0]);
        surface_combo.setPromptText(usableSurfaceComboText[0]);
        garden_combo.setPromptText(gardenSurfaceComboText[0]);
        floors_combo.setPromptText(floorsComboText[0]);
        houseName_combo.setPromptText(houseNameComboText[0]);
        reports_combo.setPromptText(chooseReportTypeText[0]);

        search_btn.setText(searchButtonText[0]);
        view_rentals.setText(viewRentalsButtonText[0]);
        view_clients.setText(viewClientsButtonText[0]);
        view_statistics.setText(viewStatisticsButtonText[0]);
        save_reports.setText(saveReportsButtonText[0]);
        employee_signOut.setText(signInButtonText[0]);
        //CRUD buttons on RENTALS
        createRental_btn.setText(createRentalButtonText[0]);
        readRental_btn.setText(readRentalButtonText[0]);
        updateRental_btn.setText(updateRentalButtonText[0]);
        deleteRental_btn.setText(deleteRentalButtonText[0]);
        //CRUD buttons on Clients
        createClient_btn.setText(createClientButtonText[0]);
        readClient_btn.setText(readClientButtonText[0]);
        updateClient_btn.setText(updateClientButtonText[0]);
        deleteClient_btn.setText(deleteClientButtonText[0]);


        notification_textField.setPromptText(notificationText[0]);

        city_column.setText(cityComboText[0]);
        country_column.setText(countryComboText[0]);
        price_column.setText(priceComboText[0]);
        housing_column.setText(housingTypeComboText[0]);
        bedrooms_column.setText(bedroomsComboText[0]);
        surface_column.setText(usableSurfaceComboText[0]);
        garden_column.setText(gardenSurfaceComboText[0]);
        floors_column.setText(floorsComboText[0]);
        rentalHouseName_column.setText(houseNameComboText[0]);

        //firstNameText
        client_firstName.setText(firstNameText[0]);
        client_lastName.setText(lastNameText[0]);
        clientHouseName.setText(houseNameComboText[0]);
        client_city.setText(cityComboText[0]);
        client_housingType.setText(housingTypeComboText[0]);
        client_housePrice.setText(priceComboText[0]);
    }

    @FXML
    void englishLanguage() {
        tf_clientFirstName.setPromptText(firstNameText[1]);
        tf_clientLastName.setPromptText(lastNameText[1]);
        tf_clientHouseRented.setPromptText(houseNameComboText[1]);
        tf_clientCity.setPromptText(cityComboText[1]);
        tf_clientHousingType.setPromptText(housingTypeComboText[1]);
        tf_clientPrice.setPromptText(priceComboText[1]);


        tf_city.setPromptText(cityComboText[1]);
        tf_country.setPromptText(countryComboText[1]);
        tf_bedrooms.setPromptText(bedroomsComboText[1]);
        tf_houseName.setPromptText(houseNameComboText[1]);
        tf_price.setPromptText(priceComboText[1]);
        tf_garden.setPromptText(gardenSurfaceComboText[1]);
        tf_surface.setPromptText(usableSurfaceComboText[1]);
        tf_housingType.setPromptText(housingTypeComboText[1]);
        tf_floors.setPromptText(floorsComboText[1]);


        city_combo.setPromptText(cityComboText[1]);
        country_combo.setPromptText(countryComboText[1]);
        price_combo.setPromptText(priceComboText[1]);
        types_combo.setPromptText(housingTypeComboText[1]);
        bedroom_combo.setPromptText(bedroomsComboText[1]);
        surface_combo.setPromptText(usableSurfaceComboText[1]);
        garden_combo.setPromptText(gardenSurfaceComboText[1]);
        floors_combo.setPromptText(floorsComboText[1]);
        houseName_combo.setPromptText(houseNameComboText[1]);
        reports_combo.setPromptText(chooseReportTypeText[1]);

        search_btn.setText(searchButtonText[1]);
        view_rentals.setText(viewRentalsButtonText[1]);
        view_clients.setText(viewClientsButtonText[1]);
        view_statistics.setText(viewStatisticsButtonText[1]);
        save_reports.setText(saveReportsButtonText[1]);
        employee_signOut.setText(signInButtonText[1]);
        //CRUD buttons on RENTALS
        createRental_btn.setText(createRentalButtonText[1]);
        readRental_btn.setText(readRentalButtonText[1]);
        updateRental_btn.setText(updateRentalButtonText[1]);
        deleteRental_btn.setText(deleteRentalButtonText[1]);
        //CRUD buttons on Clients
        createClient_btn.setText(createClientButtonText[1]);
        readClient_btn.setText(readClientButtonText[1]);
        updateClient_btn.setText(updateClientButtonText[1]);
        deleteClient_btn.setText(deleteClientButtonText[1]);

        notification_textField.setPromptText(notificationText[1]);

        city_column.setText(cityComboText[1]);
        country_column.setText(countryComboText[1]);
        price_column.setText(priceComboText[1]);
        housing_column.setText(housingTypeComboText[1]);
        bedrooms_column.setText(bedroomsComboText[1]);
        surface_column.setText(usableSurfaceComboText[1]);
        garden_column.setText(gardenSurfaceComboText[1]);
        floors_column.setText(floorsComboText[1]);
        rentalHouseName_column.setText(houseNameComboText[1]);

        client_firstName.setText(firstNameText[1]);
        client_lastName.setText(lastNameText[1]);
        clientHouseName.setText(houseNameComboText[1]);
        client_city.setText(cityComboText[1]);
        client_housingType.setText(housingTypeComboText[1]);
        client_housePrice.setText(priceComboText[1]);
    }

    @FXML
    void germanLanguage() {
        tf_clientFirstName.setPromptText(firstNameText[2]);
        tf_clientLastName.setPromptText(lastNameText[2]);
        tf_clientHouseRented.setPromptText(houseNameComboText[2]);
        tf_clientCity.setPromptText(cityComboText[2]);
        tf_clientHousingType.setPromptText(housingTypeComboText[2]);
        tf_clientPrice.setPromptText(priceComboText[2]);


        tf_city.setPromptText(cityComboText[2]);
        tf_country.setPromptText(countryComboText[2]);
        tf_bedrooms.setPromptText(bedroomsComboText[2]);
        tf_houseName.setPromptText(houseNameComboText[2]);
        tf_price.setPromptText(priceComboText[2]);
        tf_garden.setPromptText(gardenSurfaceComboText[2]);
        tf_surface.setPromptText(usableSurfaceComboText[2]);
        tf_housingType.setPromptText(housingTypeComboText[2]);
        tf_floors.setPromptText(floorsComboText[2]);


        city_combo.setPromptText(cityComboText[2]);
        country_combo.setPromptText(countryComboText[2]);
        price_combo.setPromptText(priceComboText[2]);
        types_combo.setPromptText(housingTypeComboText[2]);
        bedroom_combo.setPromptText(bedroomsComboText[2]);
        surface_combo.setPromptText(usableSurfaceComboText[2]);
        garden_combo.setPromptText(gardenSurfaceComboText[2]);
        floors_combo.setPromptText(floorsComboText[2]);
        houseName_combo.setPromptText(houseNameComboText[2]);
        reports_combo.setPromptText(chooseReportTypeText[2]);

        search_btn.setText(searchButtonText[2]);
        view_rentals.setText(viewRentalsButtonText[2]);
        view_clients.setText(viewClientsButtonText[2]);
        view_statistics.setText(viewStatisticsButtonText[2]);
        save_reports.setText(saveReportsButtonText[2]);
        employee_signOut.setText(signInButtonText[2]);

        //CRUD buttons on RENTALS
        createRental_btn.setText(createRentalButtonText[2]);
        readRental_btn.setText(readRentalButtonText[2]);
        updateRental_btn.setText(updateRentalButtonText[2]);
        deleteRental_btn.setText(deleteRentalButtonText[2]);
        //CRUD buttons on Clients
        createClient_btn.setText(createClientButtonText[2]);
        readClient_btn.setText(readClientButtonText[2]);
        updateClient_btn.setText(updateClientButtonText[2]);
        deleteClient_btn.setText(deleteClientButtonText[2]);

        notification_textField.setPromptText(notificationText[2]);

        city_column.setText(cityComboText[2]);
        country_column.setText(countryComboText[2]);
        price_column.setText(priceComboText[2]);
        housing_column.setText(housingTypeComboText[2]);
        bedrooms_column.setText(bedroomsComboText[2]);
        surface_column.setText(usableSurfaceComboText[2]);
        garden_column.setText(gardenSurfaceComboText[2]);
        floors_column.setText(floorsComboText[2]);
        rentalHouseName_column.setText(houseNameComboText[2]);

        client_firstName.setText(firstNameText[2]);
        client_lastName.setText(lastNameText[2]);
        clientHouseName.setText(houseNameComboText[2]);
        client_city.setText(cityComboText[2]);
        client_housingType.setText(housingTypeComboText[2]);
        client_housePrice.setText(priceComboText[2]);

    }

    @FXML
    void spanishLanguage() {
        tf_clientFirstName.setPromptText(firstNameText[3]);
        tf_clientLastName.setPromptText(lastNameText[3]);
        tf_clientHouseRented.setPromptText(houseNameComboText[3]);
        tf_clientCity.setPromptText(cityComboText[3]);
        tf_clientHousingType.setPromptText(housingTypeComboText[3]);
        tf_clientPrice.setPromptText(priceComboText[3]);


        tf_city.setPromptText(cityComboText[3]);
        tf_country.setPromptText(countryComboText[3]);
        tf_bedrooms.setPromptText(bedroomsComboText[3]);
        tf_houseName.setPromptText(houseNameComboText[3]);
        tf_price.setPromptText(priceComboText[3]);
        tf_garden.setPromptText(gardenSurfaceComboText[3]);
        tf_surface.setPromptText(usableSurfaceComboText[3]);
        tf_housingType.setPromptText(housingTypeComboText[3]);
        tf_floors.setPromptText(floorsComboText[3]);


        city_combo.setPromptText(cityComboText[3]);
        country_combo.setPromptText(countryComboText[3]);
        price_combo.setPromptText(priceComboText[3]);
        types_combo.setPromptText(housingTypeComboText[3]);
        bedroom_combo.setPromptText(bedroomsComboText[3]);
        surface_combo.setPromptText(usableSurfaceComboText[3]);
        garden_combo.setPromptText(gardenSurfaceComboText[3]);
        floors_combo.setPromptText(floorsComboText[3]);
        houseName_combo.setPromptText(houseNameComboText[3]);
        reports_combo.setPromptText(chooseReportTypeText[3]);

        search_btn.setText(searchButtonText[3]);
        view_rentals.setText(viewRentalsButtonText[3]);
        view_clients.setText(viewClientsButtonText[3]);
        view_statistics.setText(viewStatisticsButtonText[3]);
        save_reports.setText(saveReportsButtonText[3]);
        employee_signOut.setText(signInButtonText[3]);
        //CRUD buttons on RENTALS
        createRental_btn.setText(createRentalButtonText[3]);
        readRental_btn.setText(readRentalButtonText[3]);
        updateRental_btn.setText(updateRentalButtonText[3]);
        deleteRental_btn.setText(deleteRentalButtonText[3]);
        //CRUD buttons on Clients
        createClient_btn.setText(createClientButtonText[3]);
        readClient_btn.setText(readClientButtonText[3]);
        updateClient_btn.setText(updateClientButtonText[3]);
        deleteClient_btn.setText(deleteClientButtonText[3]);

        notification_textField.setPromptText(notificationText[3]);

        //Table COLUMNS on RENTALS
        city_column.setText(cityComboText[3]);
        country_column.setText(countryComboText[3]);
        price_column.setText(priceComboText[3]);
        housing_column.setText(housingTypeComboText[3]);
        bedrooms_column.setText(bedroomsComboText[3]);
        surface_column.setText(usableSurfaceComboText[3]);
        garden_column.setText(gardenSurfaceComboText[3]);
        floors_column.setText(floorsComboText[3]);
        rentalHouseName_column.setText(houseNameComboText[3]);
        //Table COLUMNS on CLIENTS
        client_firstName.setText(firstNameText[3]);
        client_lastName.setText(lastNameText[3]);
        clientHouseName.setText(houseNameComboText[3]);
        client_city.setText(cityComboText[3]);
        client_housingType.setText(housingTypeComboText[3]);
        client_housePrice.setText(priceComboText[3]);
    }

    public void updateNotifications(String s) {
        this.notificationsText += s + "\n";
        notification_textField.setText(this.notificationsText);
    }


    public void setComboItems() {
        reports_combo.setItems(reports_list);
        setItemsCity();
        setItemsCountry();
        setItemsHousingType();
        setItemsSurface();
        setItemsGarden();
        setItemsBedrooms();
        setItemsFloors();
        setItemsPrice();
        setItemsHouseName();
    }

    public void setItemsCity() {
        List<String> elems = model.getRentalHouse().readRentalhouse()
                .stream()
                .map(RentalHouse::getCity)
                .collect(Collectors.toList());

        ObservableList<String> items = FXCollections.observableList(elems);
        city_combo.setItems(items);
    }

    public void setItemsCountry() {
        List<String> elems = model.getRentalHouse().readRentalhouse()
                .stream()
                .map(RentalHouse::getCountry)
                .collect(Collectors.toList());

        ObservableList<String> items = FXCollections.observableList(elems);
        country_combo.setItems(items);
    }

    public void setItemsHousingType() {
        List<String> elems = model.getRentalHouse().readRentalhouse()
                .stream()
                .map(RentalHouse::getHousing_type)
                .collect(Collectors.toList());

        ObservableList<String> items = FXCollections.observableList(elems);
        types_combo.setItems(items);
    }

    public void setItemsHouseName() {
        List<String> elems = model.getRentalHouse().readRentalhouse()
                .stream()
                .map(RentalHouse::getHouse_name)
                .collect(Collectors.toList());

        ObservableList<String> items = FXCollections.observableList(elems);
        houseName_combo.setItems(items);
    }

    public void setItemsSurface() {
        List<Integer> elems = model.getRentalHouse().readRentalhouse()
                .stream()
                .map(RentalHouse::getUsable_surface)
                .collect(Collectors.toList());

        ObservableList<Integer> items = FXCollections.observableList(elems);
        surface_combo.setItems(items);
    }


    public void setItemsGarden() {
        List<Integer> elems = model.getRentalHouse().readRentalhouse()
                .stream()
                .map(RentalHouse::getGarden_surface)
                .collect(Collectors.toList());

        ObservableList<Integer> items = FXCollections.observableList(elems);
        garden_combo.setItems(items);
    }

    public void setItemsBedrooms() {
        List<Integer> elems = model.getRentalHouse().readRentalhouse()
                .stream()
                .map(RentalHouse::getBedrooms)
                .collect(Collectors.toList());

        ObservableList<Integer> items = FXCollections.observableList(elems);
        bedroom_combo.setItems(items);
    }

    public void setItemsFloors() {
        List<Integer> elems = model.getRentalHouse().readRentalhouse()
                .stream()
                .map(RentalHouse::getFloors)
                .collect(Collectors.toList());

        ObservableList<Integer> items = FXCollections.observableList(elems);
        floors_combo.setItems(items);
    }

    public void setItemsPrice() {
        List<Integer> elems = model.getRentalHouse().readRentalhouse()
                .stream()
                .map(RentalHouse::getPrice)
                .collect(Collectors.toList());

        ObservableList<Integer> items = FXCollections.observableList(elems);
        price_combo.setItems(items);
    }


    @Override
    public void update() {

    }

    public MEmployee getModel() {
        return model;
    }

    public void setModel(MEmployee model) {
        this.model = model;
    }

    public Scene getEmployeeScene() {
        return employeeScene;
    }

    public void setEmployeeScene(Scene employeeScene) {
        this.employeeScene = employeeScene;
    }

    public void setCurrentEmployee(Employee currentEmployee) {
        this.employee = currentEmployee;
    }

    public Employee searchEmployee(String username, String password) {
        for (Employee e : this.employees) {
            if (e.getUsername().equals(username) && e.getPassword().equals(password))
                return e;
        }
        return null;
    }

    public void signInBtn() throws Exception {
        String username = getUsername().getText();
        String password = getPassword().getText();

        employee = searchEmployee(username, password);
        System.out.println("signed as : " + employee.getUsername());

        if (employee.getRole().equals("admin")) {
            Stage window = (Stage) signIn_btn.getScene().getWindow();
            window.setTitle("Admin");
            window.setScene(model.getAdminController().getAdminScene());
        } else {
            Stage window = (Stage) signIn_btn.getScene().getWindow();
            window.setTitle("Employee " + employee.getFirstName());
            window.setScene(model.getEmployeeController().getEmployeeScene());
            model.getEmployeeController().setCurrentEmployee(employee);
        }
    }


    public void refreshTables() {
        displayTableRentals(model.getRentalHouse().readRentalhouse());
        displayClientTable(model.getClient().readClient());
    }

    public void createRental() {
        rentalHouse = getRentalFromTextFields();
        //subject.getDatabase().addRentalHouse(rentalHouse);
        model.getRentalHouse().addRentalHouse(rentalHouse);
        displayTableRentals(model.getRentalHouse().readRentalhouse());
        setComboItems();

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String time = dtf.format(now);

        String s = "Employee added " + rentalHouse.getHouse_name() + " at " + time;
        model.notifyAdmin(s);
        model.notifyClient(s);
    }

    public void readRental() {
        rentalHouse = selectRental();
        setRentalsToTextFields(rentalHouse);
        displayTableRentals(model.getRentalHouse().readRentalhouse());
    }

    public void updateRentals() throws SQLException {
        rentalHouse = selectRental();
        rentalHouse2 = getRentalFromTextFields();
        model.getRentalHouse().updateRentalhouse(rentalHouse, rentalHouse2);

        displayTableRentals(model.getRentalHouse().readRentalhouse());
        //setComboItems();

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String time = dtf.format(now);

        String s = "Employee updated " + rentalHouse.getHouse_name() + " at " + time;
        model.notifyClient(s);

    }

    public void deleteRentals() {
        rentalHouse = selectRental();

        model.getRentalHouse().deleteRentalhouse(rentalHouse);
        displayTableRentals(model.getRentalHouse().readRentalhouse());

        refreshTables();
        setComboItems();

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String time = dtf.format(now);

        String s = "Employee deleted " + rentalHouse.getHouse_name() + " at " + time;
        model.notifyClient(s);
    }

    // RENTALS' methods
    public void initRentalsTable() {
        city_column.setCellValueFactory(new PropertyValueFactory<>("city"));
        country_column.setCellValueFactory(new PropertyValueFactory<>("country"));
        price_column.setCellValueFactory(new PropertyValueFactory<>("price"));
        housing_column.setCellValueFactory(new PropertyValueFactory<>("housing_type"));
        bedrooms_column.setCellValueFactory(new PropertyValueFactory<>("bedrooms"));
        surface_column.setCellValueFactory(new PropertyValueFactory<>("usable_surface"));
        garden_column.setCellValueFactory(new PropertyValueFactory<>("garden_surface"));
        floors_column.setCellValueFactory(new PropertyValueFactory<>("floors"));
        rentalHouseName_column.setCellValueFactory(new PropertyValueFactory<>("house_name"));
    }

    public void setRentalsToTextFields(RentalHouse rentalHouse) {
        tf_city.setText(rentalHouse.getCity());
        tf_country.setText(rentalHouse.getCountry());
        tf_bedrooms.setText(String.valueOf(rentalHouse.getBedrooms()));
        tf_houseName.setText(rentalHouse.getHouse_name());
        tf_price.setText(String.valueOf(rentalHouse.getPrice()));
        tf_garden.setText(String.valueOf(rentalHouse.getGarden_surface()));
        tf_surface.setText(String.valueOf(rentalHouse.getUsable_surface()));
        tf_housingType.setText(rentalHouse.getHousing_type());
        tf_floors.setText(String.valueOf(rentalHouse.getFloors()));
    }


    public RentalHouse getRentalFromTextFields() {
        RentalHouse rentalHouse = new RentalHouse(tf_city.getText(), tf_country.getText(), Integer.parseInt(tf_price.getText()),
                tf_housingType.getText(), Integer.parseInt(tf_bedrooms.getText()), Integer.parseInt(tf_surface.getText()),
                Integer.parseInt(tf_garden.getText()), Integer.parseInt(tf_floors.getText()), tf_houseName.getText());

        return rentalHouse;
    }


    @FXML
    public void searchBtn() {
        List<RentalHouse> searched = model.getRentalHouse().readRentalhouse();
        if (city_combo.getValue() != null) {
            searched = searched
                    .stream()
                    .filter(t -> t.getCity().equals(city_combo.getValue()))
                    .collect(Collectors.toList());
        }
        if (country_combo.getValue() != null) {
            searched = searched
                    .stream()
                    .filter(t -> t.getCountry().equals(country_combo.getValue()))
                    .collect(Collectors.toList());
        }
        if (surface_combo.getValue() != null) {
            searched = searched
                    .stream()
                    .filter(t -> t.getUsable_surface() == surface_combo.getValue())
                    .collect(Collectors.toList());
        }
        if (garden_combo.getValue() != null) {
            searched = searched
                    .stream()
                    .filter(t -> t.getGarden_surface() == garden_combo.getValue())
                    .collect(Collectors.toList());
        }
        if (bedroom_combo.getValue() != null) {
            searched = searched
                    .stream()
                    .filter(t -> t.getBedrooms() == bedroom_combo.getValue())
                    .collect(Collectors.toList());
        }
        if (houseName_combo.getValue() != null) {
            searched = searched
                    .stream()
                    .filter(t -> t.getHouse_name().equals(houseName_combo.getValue()))
                    .collect(Collectors.toList());
        }
        if (floors_combo.getValue() != null) {
            searched = searched
                    .stream()
                    .filter(t -> t.getFloors() == floors_combo.getValue())
                    .collect(Collectors.toList());
        }
        if (price_combo.getValue() != null) {
            searched = searched
                    .stream()
                    .filter(t -> t.getPrice() == price_combo.getValue())
                    .collect(Collectors.toList());
        }
        if (types_combo.getValue() != null) {
            searched = searched
                    .stream()
                    .filter(t -> t.getHousing_type().equals(types_combo.getValue()))
                    .collect(Collectors.toList());
        }

        displayTableRentals(searched);
        clearCombo();
    }

    public void displayTableRentals(List<RentalHouse> rentalHouses) {
        ObservableList<RentalHouse> rentals_list = FXCollections.observableArrayList(rentalHouses);
        rentals_table.setItems(rentals_list);
    }

    public void displayClientTable(List<Client> clients) {
        ObservableList<Client> clients_list = FXCollections.observableArrayList(clients);
        clients_table.setItems(clients_list);
    }


    @FXML
    public void saveReports() throws IOException {
        String str = getReports_combo().getValue();
        Report report = ReportFactory.createReport(str);
        report.setRentals(model.getRentalHouse().readRentalhouse());
        report.writeReport();

    }

    public void handleStatistics() {
        ObservableList<PieChart.Data> list = FXCollections.observableArrayList();
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        HashMap<String, Number> hashMap = null;

        series.setName("compare by price");
        hashMap = computePrice();

        for (Map.Entry<String, Number> entry : hashMap.entrySet()) {
            String key = entry.getKey();
            Number value = entry.getValue();
            series.getData().add(new XYChart.Data(key, value));
            list.add(new PieChart.Data(key, value.doubleValue()));
        }

        //barChart.getData().add(series);
        pieChart.setData(list);
        //getPieChart().setData(list);

    }

    public HashMap<String, Number> computePrice() {
        HashMap<String, Number> hashMap = new HashMap<>();
        Set<String> set = new HashSet<String>();

        for (RentalHouse r : model.getRentalHouse().readRentalhouse()) {
            set.add(String.valueOf(model.getRentalHouse().findByPrice(r.getPrice())));
            //System.out.println(i);
        }

        for (String s : set) {
            int count = 0;
            for (RentalHouse r : model.getRentalHouse().readRentalhouse()) {
                if (String.valueOf(model.getRentalHouse().findByPrice(r.getPrice())).equals(s))
                    count++;
            }
            hashMap.put(s, count);
        }
        return hashMap;
    }


    public void displayTableRentals() {
        ObservableList<RentalHouse> rentals_list = FXCollections.observableArrayList(model.getRentalHouse().readRentalhouse());
        rentals_table.setItems(rentals_list);
    }

    public RentalHouse selectRental() {
        RentalHouse rentalHouse = rentals_table.getSelectionModel().getSelectedItem();
        return rentalHouse;
    }

    // CRUD operations on RENTALS
    @FXML
    void handleCreateRental(ActionEvent event) {
        createRental();

    }

    @FXML
    void handleReadRentals(ActionEvent event) {
        readRental();
    }

    @FXML
    void handleUpdateRentals(ActionEvent event) throws Exception {
        updateRentals();
    }

    public Button rentalsUpdatePressed() {
        return updateRental_btn;
    }


    @FXML
    void handleDeleteRentals(ActionEvent event) {
        deleteRentals();
    }


    @FXML
    void viewRentals() {
        employeeBase_pane.setVisible(true);
        clients_pane.setVisible(false);                  // clients_pane is the pane containing the text fields for CLIENTS CRUD operations
        //employee_menu.setVisible(false);
        combos_pane.setVisible(true);
        rentals_pane.setVisible(true);                  // rentals_pane is the pane containing the text fields for RENTALS CRUD operations
        charts_pane.setVisible(false);
    }

    @FXML
    void viewStatistics(ActionEvent event) {

//        public PieChart pieChart; //handleStatistics
        handleStatistics();

        employeeBase_pane.setVisible(true);
        clients_pane.setVisible(false);                  // clients_pane is the pane containing the text fields for CLIENTS CRUD operations
        //employee_menu.setVisible(false);
        combos_pane.setVisible(true);
        rentals_pane.setVisible(false);                  // rentals_pane is the pane containing the text fields for RENTALS CRUD operations
        charts_pane.setVisible(true);
    }



    /*
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     * */


    // CLIENTS' methods
    public void initClientsTable() {
        client_firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        client_lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        client_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        //client_status.setCellValueFactory(new PropertyValueFactory<>("client_status"));
        clientHouseName.setCellValueFactory(new PropertyValueFactory<>("houseName"));
        client_city.setCellValueFactory(new PropertyValueFactory<>("city"));
        client_housingType.setCellValueFactory(new PropertyValueFactory<>("housingType"));
        client_housePrice.setCellValueFactory(new PropertyValueFactory<>("price"));
    }


    // CRUD operations on CLIENTS
    @FXML
    void handleCreateClient(ActionEvent event) {
        createCLient();
    }

    @FXML
    void readClient(ActionEvent event) {
        readClient();
    }

    @FXML
    void updateClient(ActionEvent event) throws IOException {
        updateClient();
        //pEmployees.refreshTables();

    }

    @FXML
    void deleteClient(ActionEvent event) {
        deleteClient();
    }

    public void createCLient() {
        client = getTextFromFields();
        model.getClient().addClient(client);
        displayClientTable(model.getClient().readClient());

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String time = dtf.format(now);

        String s = "Employee added " + client.getFirstName() + " at " + time;
        model.notifyAdmin(s);
    }

    public void readClient() {
        client = selectClient();
        setClientsToTextFields(client);
        displayClientTable(model.getClient().readClient());
    }

    public void deleteClient() {
        client = selectClient();

        model.getClient().deleteClient(client);
        displayClientTable(model.getClient().readClient());

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String time = dtf.format(now);

        String s = "Employee deleted " + client.getFirstName() + " at " + time;
        model.notifyAdmin(s);
    }

    public void updateClient() throws IOException {
        client = selectClient();
        Client client2 = getTextFromFields();
        model.getClient().updateClient(client, client2);

        displayClientTable(model.getClient().readClient());

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String time = dtf.format(now);

        String s = "Employee updated " + client.getFirstName() + " at " + time;
        model.notifyAdmin(s);
    }


    public Employee getFromRegisterTextFields() {
        Employee employee = new Employee(tf_firstName.getText(), tf_lastName.getText(), tf_username.getText(), tf_password.getText(), "new user");
        return employee;
    }


    public void saveToXML() throws IOException {
        //  employeePersistence.save(employees);
    }

    @FXML
    void clientSignIn(ActionEvent event) throws Exception {
        Stage window = (Stage) signIn_btn.getScene().getWindow();
        window.setTitle("Employee");
        window.setScene(model.getEmployeeController().getEmployeeScene());
    }

    @FXML
    void goBackFromRegisterPane(ActionEvent event) throws Exception {

    }


    public Client getTextFromFields() {
        Client client = new Client(tf_clientFirstName.getText(), tf_clientLastName.getText(), tf_clientEmail.getText(),
                tf_clientHouseRented.getText(), tf_clientCity.getText(), tf_clientHousingType.getText(), Integer.parseInt(tf_clientPrice.getText()));
        return client;
    }

    public void setClientsToTextFields(Client client) {
        tf_clientFirstName.setText(client.getFirstName());
        tf_clientLastName.setText(client.getLastName());
        tf_clientEmail.setText(client.getEmail());
        //tf_clientStatus.setText(client.getStatus());
        tf_clientHouseRented.setText(client.getHouseName());
        tf_clientCity.setText(client.getCity());
        tf_clientHousingType.setText(client.getHousingType());
        tf_clientPrice.setText(String.valueOf(client.getPrice()));
    }


    public Client selectClient() {
        Client client = clients_table.getSelectionModel().getSelectedItem();
        return client;
    }


    public TextField getUsername() {
        return null;
    }

    public TextField getPassword() {
        return null;
    }

    public Button getSignInBtn() {
        return signIn_btn;
    }


    // comboboxes for RENTALS
    public ComboBox<String> getCity_combo() {
        return city_combo;
    }

    public ComboBox<String> getCountry_combo() {
        return country_combo;
    }

    public ComboBox<String> getReports_combo() {
        return reports_combo;
    }

    public ComboBox<Integer> getSurface_combo() {
        return surface_combo;
    }

    public ComboBox<Integer> getGarden_combo() {
        return garden_combo;
    }

    public ComboBox<Integer> getBedroom_combo() {
        return bedroom_combo;
    }

    public ComboBox<String> getHouseName_combo() {
        return houseName_combo;
    }

    public ComboBox<Integer> getFloors_combo() {
        return floors_combo;
    }

    public ComboBox<Integer> getPrice_combo() {
        return price_combo;
    }

    public ComboBox<String> getTypes_combo() {
        return types_combo;
    }


    @FXML
    void viewClients() {

        handleStatistics();

        employeeBase_pane.setVisible(true);
        clients_pane.setVisible(true);                  // clients_pane is the pane containing the text fields for CLIENTS CRUD operations
        employee_menu.setVisible(true);
        combos_pane.setVisible(true);
        rentals_pane.setVisible(false);                  // rentals_pane is the pane containing the text fields for RENTALS CRUD operations
        charts_pane.setVisible(false);
    }


    //Statistics
    public PieChart getPieChart() {
        return pieChart;
    }

    public BarChart<String, Number> getBarChart() {
        return barChart;
    }


    @FXML
    void employeeSignOut(ActionEvent event) throws Exception {
        Stage window = (Stage) employee_signOut.getScene().getWindow();
        window.setTitle("Client");
        window.setScene(model.getClientController().getClientScene());
        model.getClientController().init();

    }

    public void clearCombo() {
        getCity_combo().valueProperty().set(null);
        getCountry_combo().valueProperty().set(null);
        getSurface_combo().valueProperty().set(null);
        getGarden_combo().valueProperty().set(null);
        getBedroom_combo().valueProperty().set(null);
        getHouseName_combo().valueProperty().set(null);
        getFloors_combo().valueProperty().set(null);
        getPrice_combo().valueProperty().set(null);
        getTypes_combo().valueProperty().set(null);
    }

    public void createAccount() throws Exception {
        employee = getFromRegisterTextFields();
        employees.add(employee);
        System.out.println("new user signed as: " + employee.getUsername());
        //saveToXML();
    }

    public void hideRegisterPane() {
        newAccount_pane.setVisible(false);
    }

    @FXML
    public void createAccount(ActionEvent event) throws Exception {
        createAccount();
    }


}


