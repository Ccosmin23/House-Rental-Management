package view.employee;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.*;
import presenter.PClient;
import presenter.PEmployee;

import java.io.*;
import java.net.URL;
import java.util.*;


public class GUI_Employee implements Initializable, IEmployee {

    public PieChart pieChart;
    public BarChart<String, Number> barChart;




    @FXML
    public AnchorPane newAccount_pane;
    public Button innerSignIn_btn, goBack_btn, createAccount_btn;
    public TextField tf_firstName, tf_email, tf_lastName, tf_username,tf_password;





    public AnchorPane menuClient_pane, clients_pane, rentals_pane, employee_menu2, textfield_menu, employee_menu3, employeeBase_pane, charts_pane, employee_menu, combos_pane;
    public ComboBox<String> city_combo,country_combo,surface_combo,garden_combo,bedroom_combo,houseName_combo,floors_combo,price_combo,types_combo;
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
    PClient pClient;
    PEmployee pEmployee;

    ObservableList<String> country_list = FXCollections.observableArrayList("Country", "Alba", "Arad", "Arges", "Bacau", "Bihor", "Bistrita Nasaud", "Botosani", "Brasov", "Braila", "Caras Severin", "Calarasi", "Cluj", "Constanta", "Dolj", "Hunedoara", "Maramures", "Prahova", "Satu Mare", "Timis", "Vaslui");
    ObservableList<String> city_list = FXCollections.observableArrayList("City", "Alba Iulia", "Sebis", "Mioveni", "Buhusi", "Oradea", "Sangeorz Bai", "Saveni", "Predeal", "Ianca", "Bocsa", "Cluj Napoca", "Navodari", "Calafat", "Deva", "Baia Mare", "Ploiesti", "Negresti Oas", "Faget", "Murgeni");
    ObservableList<String> types_list = FXCollections.observableArrayList("Housing type", "Apartament", "Chalet", "House");
    ObservableList<String> surface_list = FXCollections.observableArrayList( "Usable surface","50", "100", "150", "200", "250", "300", "350", "400", "450", "500");
    ObservableList<String> garden_list = FXCollections.observableArrayList("Garden surface","500", "1000", "1500", "2000");
    ObservableList<String> bedroom_list = FXCollections.observableArrayList("Bedrooms", "1", "2", "3", "4", "5");
    ObservableList<String> houseName_list = FXCollections.observableArrayList("House name", "Apartament1", "Apartament2", "House1", "House2", "Chalet1", "Chalet2");
    ObservableList<String> floors_list = FXCollections.observableArrayList("Floors","1","2","3");
    ObservableList<String> price_list = FXCollections.observableArrayList("Price","500", "1000", "1500", "2000", "2500", "3000", "3500", "4000", "4500", "5000");




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        pEmployee = new PEmployee(this);
        initRentalsTable();
        initClientsTable();

        //initialize tables
        //pEmployee.initializeRental();
        //pEmployee.initializePClient();

        pEmployee.loadFromXML();
        pEmployee.refreshTables();

        //set list of values in COMBO-BOXES
        country_combo.setItems(country_list);
        city_combo.setItems(city_list);
        types_combo.setItems(types_list);
        surface_combo.setItems(surface_list);
        garden_combo.setItems(garden_list);
        bedroom_combo.setItems(bedroom_list);
        houseName_combo.setItems(houseName_list);
        floors_combo.setItems(floors_list);
        price_combo.setItems(price_list);

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

    public void hideRegisterPane() {
        newAccount_pane.setVisible(false);
    }


    @Override
    public Employee getFromRegisterTextFields() {
        Employee employee = new Employee(tf_firstName.getText(), tf_lastName.getText(), tf_username.getText(), tf_password.getText(), "new user");
        return employee;
    }

    @FXML
    public void createAccount(ActionEvent event) throws Exception {
        pEmployee.createAccount();
    }

/*    public void saveToXML() throws IOException {
        //  employeePersistence.save(employees);
    }*/

    @FXML
    void clientSignIn(ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("view/signIn/sign_in.fxml"));
        Stage window = (Stage) innerSignIn_btn.getScene().getWindow();
        window.setTitle("Sign In");
        window.setScene(new Scene(root, 1000, 600));
    }

    @FXML
    void goBackFromRegisterPane(ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("view/client/client_view.fxml"));
        Stage window = (Stage) goBack_btn.getScene().getWindow();
        window.setTitle("Client");
        window.setScene(new Scene(root, 1000, 600));
    }












    // CLIENTS' methods
    public void initClientsTable() {
        client_firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        client_lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        client_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        client_status.setCellValueFactory(new PropertyValueFactory<>("status"));
        clientHouseName.setCellValueFactory(new PropertyValueFactory<>("houseName"));
        client_city.setCellValueFactory(new PropertyValueFactory<>("city"));
        client_housingType.setCellValueFactory(new PropertyValueFactory<>("housingType"));
        client_housePrice.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    @Override
    public Client getTextFromFields() {
        Client client = new Client(tf_clientFirstName.getText(), tf_clientLastName.getText(), tf_clientEmail.getText(), tf_clientStatus.getText(),
                tf_clientHouseRented.getText(), tf_clientCity.getText(), tf_clientHousingType.getText(), Integer.parseInt(tf_clientPrice.getText()));

        return client;
    }



    @Override
    public void setClientsToTextFields(Client client) {
        tf_clientFirstName.setText(client.getFirstName());
        tf_clientLastName.setText(client.getLastName());
        tf_clientEmail.setText(client.getEmail());
        tf_clientStatus.setText(client.getStatus());
        tf_clientHouseRented.setText(client.getHouseName());
        tf_clientCity.setText(client.getCity());
        tf_clientHousingType.setText(client.getHousingType());
        tf_clientPrice.setText(String.valueOf(client.getPrice()));
    }

    @Override
    public void displayClientTable(Clients clients) {
        ObservableList<Client> clients_list = FXCollections.observableArrayList(clients.getClientList());
        clients_table.setItems(clients_list);
    }

    @Override
    public Client selectClient() {
        Client client = clients_table.getSelectionModel().getSelectedItem();
        return client;
    }

    // CRUD operations on CLIENTS
    @FXML
    void handleCreateClient(ActionEvent event) {
        pEmployee.createCLient();
    }

    @FXML
    void readClient(ActionEvent event) {
        pEmployee.readClient();
    }

    @FXML
    void updateClient(ActionEvent event) throws IOException {
        pEmployee.updateClient();
        //pEmployees.refreshTables();

    }

    @FXML
    void deleteClient(ActionEvent event) {
        pEmployee.deleteClient();
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

    @Override
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





    @Override
    public RentalHouse getRentalFromTextFields() {
        RentalHouse rentalHouse = new RentalHouse(tf_city.getText(), tf_country.getText(), tf_price.getText(),
                tf_housingType.getText(), tf_bedrooms.getText(), tf_surface.getText(),
                tf_garden.getText(), tf_floors.getText(), tf_houseName.getText());

        return rentalHouse;
    }


    @Override
    public TextField getUsername() {
        return null;
    }

    @Override
    public TextField getPassword() {
        return null;
    }

    @Override
    public Button getSignInBtn() {
        return signIn_btn;
    }



    @Override
    public void displayTableRentals(RentalHouses rentalHouses) {
        ObservableList<RentalHouse> rentals_list = FXCollections.observableArrayList(rentalHouses.getRentalHouseList());
        rentals_table.setItems(rentals_list);
    }

    @Override
    public RentalHouse selectRental() {
        RentalHouse rentalHouse = rentals_table.getSelectionModel().getSelectedItem();
        return rentalHouse;
    }

    // CRUD operations on RENTALS
    @FXML
    void handleCreateRental(ActionEvent event) {
        pEmployee.createRental();

    }

    @FXML
    void handleReadRentals(ActionEvent event) {
        pEmployee.readRental();
    }

    @FXML
    void handleUpdateRentals(ActionEvent event) throws Exception{
        pEmployee.updateRentals();
        //pEmployees.refreshTables();

        pEmployee.saveToXML();
        pEmployee.loadFromXML();

        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("view/employee/employee_view.fxml"));
        Stage window = (Stage) updateRental_btn.getScene().getWindow();
        window.setTitle("Sign In");
        window.setScene(new Scene(root, 1000, 600));

       // pEmployees.refreshTables();

    }

    @Override
    public Button rentalsUpdatePressed(){
        return updateRental_btn;
    }



    @FXML
    void handleDeleteRentals(ActionEvent event) {
        pEmployee.deleteRentals();
    }

    // comboboxes for RENTALS
    @Override
    public ComboBox<String> getCity_combo() {return city_combo;}

    @Override
    public ComboBox<String> getCountry_combo(){return country_combo; }

    @Override
    public ComboBox<String> getSurface_combo(){return surface_combo;}

    @Override
    public ComboBox<String> getGarden_combo(){return garden_combo;}

    @Override
    public ComboBox<String> getBedroom_combo(){return bedroom_combo;}

    @Override
    public ComboBox<String> getHouseName_combo(){return houseName_combo;}

    @Override
    public ComboBox<String> getFloors_combo(){return floors_combo;}

    @Override
    public ComboBox<String> getPrice_combo(){return price_combo;}

    @Override
    public ComboBox<String> getTypes_combo(){return types_combo;}



    //  EMPLOYEE attributes
    @FXML
    public void searchBtn() {
        pEmployee.searchBtn();
    }

    @FXML
    public void saveReports() throws IOException {
        pEmployee.saveReports();}

    @FXML
    void viewClients() {

        pEmployee.handleStatistics();

        employeeBase_pane.setVisible(true);
        clients_pane.setVisible(true);                  // clients_pane is the pane containing the text fields for CLIENTS CRUD operations
        employee_menu.setVisible(true);
        combos_pane.setVisible(true);
        rentals_pane.setVisible(false);                  // rentals_pane is the pane containing the text fields for RENTALS CRUD operations
        charts_pane.setVisible(false);
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
        pEmployee.handleStatistics();

        employeeBase_pane.setVisible(true);
        clients_pane.setVisible(false);                  // clients_pane is the pane containing the text fields for CLIENTS CRUD operations
        //employee_menu.setVisible(false);
        combos_pane.setVisible(true);
        rentals_pane.setVisible(false);                  // rentals_pane is the pane containing the text fields for RENTALS CRUD operations
        charts_pane.setVisible(true);
    }


    //Statistics
    @Override
    public PieChart getPieChart() {return pieChart;}

    @Override
    public BarChart<String, Number> getBarChart(){
        return barChart;
    }




    @FXML
    void employeeSignOut(ActionEvent event) throws Exception{
        //pClients.saveToXML();
        //pRentals.saveToXML();
        pEmployee.saveToXML();

        //System.out.println(pClients.getClients().toString());

        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("view/client/client_view.fxml"));
        Stage window = (Stage) employee_signOut.getScene().getWindow();
        window.setTitle("Sign In");
        window.setScene(new Scene(root, 1000, 600));
    }



}


