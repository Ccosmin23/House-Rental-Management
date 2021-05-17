package controller;

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
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Client;
import model.Clients;
import model.RentalHouse;
import model.RentalHouses;
import model.persistence.RentalsPersistence;

import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;


public class CClient implements Initializable {
    public PieChart pieChart;
    public BarChart<String, Number> barChart;
    public AnchorPane menuClient_pane, clients_pane, rentals_pane, employee_menu2, textfield_menu, employeeBase_pane, charts_pane, employee_menu, combos_pane;
    public ComboBox<String> city_combo, country_combo, surface_combo, garden_combo, bedroom_combo, houseName_combo, floors_combo, price_combo, types_combo;
    public Button signIn_btn, view_statistics, view_rentals;
    public Button register_btn, search_btn;
    public TableView<Client> clients_table;
    public TableView<RentalHouse> rentals_table;
    public TableColumn<Client, String> client_firstName, client_lastName, client_email, client_status, clientHouseName, client_city, client_housingType, client_housePrice;
    public TableColumn<RentalHouse, String> city_column, country_column, price_column, housing_column, bedrooms_column, surface_column, garden_column, floors_column, rentalHouseName_column;
    public TextField tf_city, tf_country, tf_bedrooms, tf_houseName, tf_price, tf_garden, tf_surface, tf_housingType, tf_floors;
    public TextField tf_clientLastName, tf_clientFirstName, tf_clientEmail, tf_clientStatus, tf_clientHouseRented, tf_clientCity, tf_clientHousingType, tf_clientPrice;
    //maybe useless
    public Button show_clientsBtn;
    RentalsPersistence rentalsPersistence = new RentalsPersistence("./rentals_saved.xml");
    CClient cClient;
    CAdmin cAdmin;
    CEmployee cEmployee;
    ObservableList<String> country_list = FXCollections.observableArrayList("Country", "Alba", "Arad", "Arges", "Bacau", "Bihor", "Bistrita Nasaud", "Botosani", "Brasov", "Braila", "Caras Severin", "Calarasi", "Cluj", "Constanta", "Dolj", "Hunedoara", "Maramures", "Prahova", "Satu Mare", "Timis", "Vaslui");
    ObservableList<String> city_list = FXCollections.observableArrayList("City", "Alba Iulia", "Sebis", "Mioveni", "Buhusi", "Oradea", "Sangeorz Bai", "Saveni", "Predeal", "Ianca", "Bocsa", "Cluj Napoca", "Navodari", "Calafat", "Deva", "Baia Mare", "Ploiesti", "Negresti Oas", "Faget", "Murgeni");
    ObservableList<String> types_list = FXCollections.observableArrayList("Housing type", "Apartament", "Chalet", "House");
    ObservableList<String> surface_list = FXCollections.observableArrayList("Usable surface", "50", "100", "150", "200", "250", "300", "350", "400", "450", "500");
    ObservableList<String> garden_list = FXCollections.observableArrayList("Garden surface", "500", "1000", "1500", "2000");
    ObservableList<String> bedroom_list = FXCollections.observableArrayList("Bedrooms", "1", "2", "3", "4", "5");
    ObservableList<String> houseName_list = FXCollections.observableArrayList("House name", "Apartament1", "Apartament2", "House1", "House2", "Chalet1", "Chalet2");
    ObservableList<String> floors_list = FXCollections.observableArrayList("Floors", "1", "2", "3");

    //Objects
    ObservableList<String> price_list = FXCollections.observableArrayList("Price", "500", "1000", "1500", "2000", "2500", "3000", "3500", "4000", "4500", "5000");
    private Client client;
    private Clients clients;
    private RentalHouse rentalHouse;
    private RentalHouses rentalHouses;
    public CClient() {
        rentalHouses = new RentalHouses();
    }

    public void loadFromXML() {
        this.rentalHouses = rentalsPersistence.load();
    }

    public void refreshTables() {
        displayTableRentals(this.rentalHouses);
    }

    public void initializeRental() {
        RentalHouse house1 = new RentalHouse("Alba Iulia", "Alba", "500", "Apartament", "4", "500", "500", "1", "Apartament1");
        RentalHouse house2 = new RentalHouse("Predeal", "Brasov", "1500", "House", "5", "800", "500", "3", "House1");
        RentalHouse house3 = new RentalHouse("Murgeni", "Vaslui", "2500", "Chalet", "3", "400", "2000", "2", "Chalet1");
        RentalHouse house4 = new RentalHouse("Ploiesti", "Prahova", "3500", "Apartament", "3", "150", "1500", "1", "Apartament2");
        rentalHouses.addHouse(house1);
        rentalHouses.addHouse(house2);
        rentalHouses.addHouse(house3);
        rentalHouses.addHouse(house4);

        displayTableRentals(this.rentalHouses);
        rentalsPersistence.save(this.rentalHouses);

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

    @FXML
    public void searchBtn() {
        List<RentalHouse> searched = rentalHouses.getRentalHouseList();

        if (getCity_combo().getValue() != null) {
            searched = searched
                    .stream()
                    .filter(t -> t.getCity().equals(getCity_combo().getValue()))
                    .collect(Collectors.toList());
        }
        if (getCountry_combo().getValue() != null) {
            searched = searched
                    .stream()
                    .filter(t -> t.getCountry().equals(getCountry_combo().getValue()))
                    .collect(Collectors.toList());
        }
        if (getSurface_combo().getValue() != null) {
            searched = searched
                    .stream()
                    .filter(t -> t.getUsable_surface().equals(getSurface_combo().getValue()))
                    .collect(Collectors.toList());
        }
        if (getGarden_combo().getValue() != null) {
            searched = searched
                    .stream()
                    .filter(t -> t.getGarden_surface().equals(getGarden_combo().getValue()))
                    .collect(Collectors.toList());
        }
        if (getBedroom_combo().getValue() != null) {
            searched = searched
                    .stream()
                    .filter(t -> t.getBedrooms().equals(getBedroom_combo().getValue()))
                    .collect(Collectors.toList());
        }
        if (getHouseName_combo().getValue() != null) {
            searched = searched
                    .stream()
                    .filter(t -> t.getHouse_name().equals(getHouseName_combo().getValue()))
                    .collect(Collectors.toList());
        }
        if (getFloors_combo().getValue() != null) {
            searched = searched
                    .stream()
                    .filter(t -> t.getFloors().equals(getFloors_combo().getValue()))
                    .collect(Collectors.toList());
        }
        if (getPrice_combo().getValue() != null) {
            searched = searched
                    .stream()
                    .filter(t -> t.getPrice().equals(getPrice_combo().getValue()))
                    .collect(Collectors.toList());
        }
        if (getTypes_combo().getValue() != null) {
            searched = searched
                    .stream()
                    .filter(t -> t.getHousing_type().equals(getTypes_combo().getValue()))
                    .collect(Collectors.toList());
        }

        ArrayList<RentalHouse> aux = new ArrayList<>(searched);
        RentalHouses searchedRentals = new RentalHouses(aux);
        displayTableRentals(searchedRentals);
        clearCombo();

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

        getBarChart().getData().add(series);
        getPieChart().setData(list);

    }

    public HashMap<String, Number> computePrice() {

        HashMap<String, Number> hashMap = new HashMap<>();
        Set<String> set = new HashSet<String>();

        for (int i = 0; i < rentalHouses.getRentalHouseList().size(); i++) {
            set.add(rentalHouses.getRentalHouseList().get(i).getPrice());
            //System.out.println(i);
        }

        for (String s : set) {
            int count = 0;
            for (RentalHouse r : rentalHouses.getRentalHouseList()) {
                if (r.getPrice().equals(s))
                    count++;
                //  System.out.println(count);
            }
            hashMap.put(s, count);
        }
        return hashMap;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        cClient = new CClient();
        initRentalsTable();
        initializeRental();

        loadFromXML();
        refreshTables();


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
    public ComboBox<String> getCity_combo() {
        return city_combo;
    }

    public ComboBox<String> getCountry_combo() {
        return country_combo;
    }

    public ComboBox<String> getSurface_combo() {
        return surface_combo;
    }

    public ComboBox<String> getGarden_combo() {
        return garden_combo;
    }

    public ComboBox<String> getBedroom_combo() {
        return bedroom_combo;
    }

    public ComboBox<String> getHouseName_combo() {
        return houseName_combo;
    }

    public ComboBox<String> getFloors_combo() {
        return floors_combo;
    }

    public ComboBox<String> getPrice_combo() {
        return price_combo;
    }

    public ComboBox<String> getTypes_combo() {
        return types_combo;
    }


    //Statistics
    public PieChart getPieChart() {
        return pieChart;
    }

    public BarChart<String, Number> getBarChart() {
        return barChart;
    }


    @FXML
    void viewStatistics(ActionEvent event) {

        handleStatistics();

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

        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("view/sign_in.fxml"));
        Stage window = (Stage) signIn_btn.getScene().getWindow();
        window.setTitle("Sign In");
        window.setScene(new Scene(root, 1000, 600));

    }


    @FXML
    void clientRegister(ActionEvent event) throws Exception {
           /* Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("view/signUp/signUp.fxml"));
            Stage window = (Stage) register_btn.getScene().getWindow();
            window.setTitle("Client");
            window.setScene(new Scene(root, 1000, 600));*/


    }


}
