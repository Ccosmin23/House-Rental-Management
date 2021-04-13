package view.client;

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
import model.Client;
import model.RentalHouse;
import model.RentalHouses;
import presenter.PAdmin;
import presenter.PClient;
import presenter.PEmployee;

import java.net.URL;
import java.util.ResourceBundle;



public class GUI_Client implements Initializable, IClient {

    public PieChart pieChart;
    public BarChart<String, Number> barChart;

    public AnchorPane menuClient_pane, clients_pane, rentals_pane, employee_menu2, textfield_menu, employeeBase_pane, charts_pane, employee_menu, combos_pane;
    public ComboBox<String> city_combo,country_combo,surface_combo,garden_combo,bedroom_combo,houseName_combo,floors_combo,price_combo,types_combo;
    public Button signIn_btn, view_statistics, view_rentals;
    public Button register_btn, search_btn;

    public TableView<Client> clients_table;
    public TableView<RentalHouse> rentals_table;
    public TableColumn<Client, String> client_firstName, client_lastName, client_email, client_status, clientHouseName, client_city, client_housingType, client_housePrice;
    public TableColumn<RentalHouse, String> city_column, country_column, price_column, housing_column, bedrooms_column, surface_column, garden_column, floors_column, rentalHouseName_column;
    public TextField tf_city, tf_country, tf_bedrooms, tf_houseName, tf_price, tf_garden, tf_surface, tf_housingType, tf_floors;
    public TextField tf_clientLastName, tf_clientFirstName, tf_clientEmail, tf_clientStatus, tf_clientHouseRented, tf_clientCity, tf_clientHousingType, tf_clientPrice;

    //Objects

    PClient pClient;
    PAdmin pAdmin;
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


    //maybe useless
    public Button show_clientsBtn;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        pClient = new PClient(this);
        initRentalsTable();
        //pClient.initializeRental();

        pClient.loadFromXML();
        pClient.refreshTables();



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

        // set which anchor panes and tables must be visible at first run
        employeeBase_pane.setVisible(true);
        menuClient_pane.setVisible(true);
        combos_pane.setVisible(true);
        rentals_pane.setVisible(true);                  // rentals_pane is the pane containing the text fields for RENTALS CRUD operations
        charts_pane.setVisible(true);
        textfield_menu.setVisible(false);               // anchor pane containing text fields for RENTALS


    }


    @Override
    public void displayTableRentals(RentalHouses rentalHouses) {
        ObservableList<RentalHouse> rentals_list = FXCollections.observableArrayList(rentalHouses.getRentalHouseList());
        rentals_table.setItems(rentals_list);
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


    //Statistics
    @Override
    public PieChart getPieChart() {return pieChart;}

    @Override
    public BarChart<String, Number> getBarChart(){
        return barChart;
    }



    @FXML
    public void searchBtn() {
        pClient.searchBtn();
    }

    @FXML
    void viewStatistics(ActionEvent event) {

        pClient.handleStatistics();

        employeeBase_pane.setVisible(true);
        //clients_pane.setVisible(false);                  // clients_pane is the pane containing the text fields for CLIENTS CRUD operations
        //employee_menu.setVisible(false);
        combos_pane.setVisible(true);
        rentals_pane.setVisible(false);                  // rentals_pane is the pane containing the text fields for RENTALS CRUD operations
        charts_pane.setVisible(true);
    }

    @FXML
    void viewRentals(ActionEvent event) {
        employeeBase_pane.setVisible(true);
        //clients_pane.setVisible(false);                  // clients_pane is the pane containing the text fields for CLIENTS CRUD operations
        //employee_menu.setVisible(false);
        combos_pane.setVisible(true);
        rentals_pane.setVisible(true);                  // rentals_pane is the pane containing the text fields for RENTALS CRUD operations
        charts_pane.setVisible(false);
    }

    @FXML
    void clientSignIn(ActionEvent actionEvent) throws Exception {

        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("view/signIn/sign_in.fxml"));
        Stage window = (Stage) signIn_btn.getScene().getWindow();
        window.setTitle("Sign In");
        window.setScene(new Scene(root, 1000, 600));

    }



    @FXML
    void clientRegister(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("view/signUp/signUp.fxml"));
        Stage window = (Stage) register_btn.getScene().getWindow();
        window.setTitle("Client");
        window.setScene(new Scene(root, 1000, 600));


    }













   /* @Override
    public Client getTextFromFields() {
        Client client = new Client(tf_clientFirstName.getText(), tf_clientLastName.getText(), tf_clientEmail.getText(), tf_clientStatus.getText(),
                tf_clientHouseRented.getText(), tf_clientCity.getText(), tf_clientHousingType.getText(), Integer.parseInt(tf_clientPrice.getText()));

        return client;
    }*/

   /* @Override
    public void setTextToTextFields(Client client) {
        tf_clientFirstName.setText(client.getFirstName());
        tf_clientLastName.setText(client.getLastName());
        tf_clientEmail.setText(client.getEmail());
        tf_clientStatus.setText(client.getStatus());
        tf_clientHouseRented.setText(client.getHouseName());
        tf_clientCity.setText(client.getCity());
        tf_clientHousingType.setText(client.getHousingType());
        tf_clientPrice.setText(String.valueOf(client.getPrice()));
    }*/

   /* @Override
    public void displayClientTable(Clients clients) {
        ObservableList<Client> clients_list = FXCollections.observableArrayList(clients.getClientList());
        clients_table.setItems(clients_list);
    }

    @Override
    public Client selectClient() {
        Client client = clients_table.getSelectionModel().getSelectedItem();
        return client;
    }
*/


  /*  @Override
    public void setTextToTextFields(RentalHouse rentalHouse) {
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
    public RentalHouse getTextFromTextFields() {
        RentalHouse rentalHouse = new RentalHouse(tf_city.getText(), tf_country.getText(), tf_price.getText(),
                tf_housingType.getText(), tf_bedrooms.getText(), tf_surface.getText(),
                tf_garden.getText(), tf_floors.getText(), tf_houseName.getText());

        return rentalHouse;
    }*/

/*    @Override
    public void displayTableRentals(RentalHouses rentalHouses) {
        ObservableList<RentalHouse> rentals_list = FXCollections.observableArrayList(rentalHouses.getRentalHouseList());
        rentals_table.setItems(rentals_list);
    }

    @Override
    public RentalHouse selectRental() {
        RentalHouse rentalHouse = rentals_table.getSelectionModel().getSelectedItem();
        return rentalHouse;
    }*/




  /*  // comboboxes for RENTALS
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
*/




  /*  //Statistics
    @Override
    public PieChart getPieChart() {return pieChart;}

    @Override
    public BarChart<String, Number> getBarChart(){
        return barChart;
    }*/








}


