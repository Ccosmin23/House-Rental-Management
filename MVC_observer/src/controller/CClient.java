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
import model.Observer;
import model.*;

import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;


public class CClient implements Initializable, Observer {
    public PieChart pieChart, pieChart2;
    public BarChart<String, Number> barChart;
    public AnchorPane menuClient_pane, clients_pane, rentals_pane, employee_menu2, textfield_menu, employeeBase_pane, charts_pane, employee_menu, combos_pane;
    public ComboBox<String> city_combo, country_combo, houseName_combo, types_combo;
    public ComboBox<Integer> surface_combo, garden_combo, floors_combo, price_combo, bedroom_combo;

    public Button signIn_btn, view_statistics, view_rentals;
    public Button register_btn, search_btn;
    public TableView<Client> clients_table;
    public TableView<RentalHouse> rentals_table;
    public TableColumn<Client, String> client_firstName, client_lastName, client_email, client_status, clientHouseName, client_city, client_housingType, client_housePrice;
    public TableColumn<RentalHouse, String> city_column, country_column, price_column, housing_column, bedrooms_column, surface_column, garden_column, floors_column, rentalHouseName_column;
    public TextField notification_textField, tf_city, tf_country, tf_bedrooms, tf_houseName, tf_price, tf_garden, tf_surface, tf_housingType, tf_floors;
    public TextField tf_clientLastName, tf_clientFirstName, tf_clientEmail, tf_clientStatus, tf_clientHouseRented, tf_clientCity, tf_clientHousingType, tf_clientPrice;

    @FXML
    private Button ro_button,en_button,de_button,es_button;

    private String[] cityComboText = {"Oraș", "City", "Stadt", "Pueblo"};
    private String[] countryComboText = {"Județ", "County", "Bezirk", "Condado"};
    private String[] priceComboText = {"Preț", "Price", "Preis", "Precio"};
    private String[] housingTypeComboText = {"Tip locuinta", "Housing type", "Gehäusetyp", "Tipo de vivienda"};
    private String[] bedroomsComboText = {"Dormitoare", "Bedrooms", "Schlafzimmer", "Dormitorios"};
    private String[] usableSurfaceComboText = {"Suprafata locuinta", "Usable surface", "Nutzbare Oberfläche", "Superficie utilizable"};
    private String[] gardenSurfaceComboText = {"Suprafata gradina", "Garden surface", "Gartenfläche", "Superficie del jardín"};
    private String[] floorsComboText = {"Etaje", "Floors", "Böden", "Pisos"};
    private String[] houseNameComboText = {"Numele locuintei", "House name", "Hausname", "Nombre de la casa"};

    private String[] searchButtonText = {"Cauta","Search","Suche","Buscar"};
    private String[] viewRentalsButtonText = {"Vezi Locuinte","View Rentals","Mietobjekte ansehen", "Ver alquileres"};
    private String[] viewStatisticsButtonText = {"Vezi statistici","View Statistics","Statistiken ansehen", "Ver estadísticas"};
    private String[] signInButtonText = {"Autentificare","Sign in","Anmelden", "Iniciar sesión"};

    private String[] notificationText = {"Anunturi","Notifications","Benachrichtigungen","Notificaciones"};






    //maybe useless
    public Button show_clientsBtn;
    CClient cClient;
    CAdmin cAdmin;
    CEmployee cEmployee;

    //Objects
    ObservableList<String> price_list = FXCollections.observableArrayList("Price", "500", "1000", "1500", "2000", "2500", "3000", "3500", "4000", "4500", "5000");
    private Client client;
    private Clients clients;
    private RentalHouse rentalHouse;
    private final RentalHouses rentalHouses;
    private Subject subject;
    private Scene clientScene;
    private CSignIn cSignIn;
    private String notificationsText = new String();


    public CClient() {
        rentalHouses = new RentalHouses();
        cSignIn = new CSignIn();
    }



    public void init() {
        displayTableRentals(this.rentalHouses);

        //set list of values in COMBO-BOXES
        initRentalsTable();

       setComboItems();

        // set which anchor panes and tables must be visible at first run
        employeeBase_pane.setVisible(true);
        menuClient_pane.setVisible(true);
        combos_pane.setVisible(true);
        rentals_pane.setVisible(true);                  // rentals_pane is the pane containing the text fields for RENTALS CRUD operations
        charts_pane.setVisible(true);
        textfield_menu.setVisible(false);               // anchor pane containing text fields for RENTALS
    }



    @FXML
    void romanianLanguage() {
        /*
        * ToDo : call romanianLanguage() method from CSignIn to change simultaneously
        *   cSignIn.romanianLanguage();
        *  Tested! but it do not work..
        * */


        city_combo.setPromptText(cityComboText[0]);
        country_combo.setPromptText(countryComboText[0]);
        price_combo.setPromptText(priceComboText[0]);
        types_combo.setPromptText(housingTypeComboText[0]);
        bedroom_combo.setPromptText(bedroomsComboText[0]);
        surface_combo.setPromptText(usableSurfaceComboText[0]);
        garden_combo.setPromptText(gardenSurfaceComboText[0]);
        floors_combo.setPromptText(floorsComboText[0]);
        houseName_combo.setPromptText(houseNameComboText[0]);

        search_btn.setText(searchButtonText[0]);
        view_rentals.setText(viewRentalsButtonText[0]);
        view_statistics.setText(viewStatisticsButtonText[0]);
        signIn_btn.setText(signInButtonText[0]);

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

    }

    @FXML
    void englishLanguage() {
        city_combo.setPromptText(cityComboText[1]);
        country_combo.setPromptText(countryComboText[1]);
        price_combo.setPromptText(priceComboText[1]);
        types_combo.setPromptText(housingTypeComboText[1]);
        bedroom_combo.setPromptText(bedroomsComboText[1]);
        surface_combo.setPromptText(usableSurfaceComboText[1]);
        garden_combo.setPromptText(gardenSurfaceComboText[1]);
        floors_combo.setPromptText(floorsComboText[1]);
        houseName_combo.setPromptText(houseNameComboText[1]);

        search_btn.setText(searchButtonText[1]);
        view_rentals.setText(viewRentalsButtonText[1]);
        view_statistics.setText(viewStatisticsButtonText[1]);
        signIn_btn.setText(signInButtonText[1]);

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
    }

    @FXML
    void germanLanguage() {
        city_combo.setPromptText(cityComboText[2]);
        country_combo.setPromptText(countryComboText[2]);
        price_combo.setPromptText(priceComboText[2]);
        types_combo.setPromptText(housingTypeComboText[2]);
        bedroom_combo.setPromptText(bedroomsComboText[2]);
        surface_combo.setPromptText(usableSurfaceComboText[2]);
        garden_combo.setPromptText(gardenSurfaceComboText[2]);
        floors_combo.setPromptText(floorsComboText[2]);
        houseName_combo.setPromptText(houseNameComboText[2]);

        search_btn.setText(searchButtonText[2]);
        view_rentals.setText(viewRentalsButtonText[2]);
        view_statistics.setText(viewStatisticsButtonText[2]);
        signIn_btn.setText(signInButtonText[2]);

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

    }

    @FXML
    void spanishLanguage() {
        city_combo.setPromptText(cityComboText[3]);
        country_combo.setPromptText(countryComboText[3]);
        price_combo.setPromptText(priceComboText[3]);
        types_combo.setPromptText(housingTypeComboText[3]);
        bedroom_combo.setPromptText(bedroomsComboText[3]);
        surface_combo.setPromptText(usableSurfaceComboText[3]);
        garden_combo.setPromptText(gardenSurfaceComboText[3]);
        floors_combo.setPromptText(floorsComboText[3]);
        houseName_combo.setPromptText(houseNameComboText[3]);

        search_btn.setText(searchButtonText[3]);
        view_rentals.setText(viewRentalsButtonText[3]);
        view_statistics.setText(viewStatisticsButtonText[3]);
        signIn_btn.setText(signInButtonText[3]);

        notification_textField.setPromptText(notificationText[3]);

        city_column.setText(cityComboText[3]);
        country_column.setText(countryComboText[3]);
        price_column.setText(priceComboText[3]);
        housing_column.setText(housingTypeComboText[3]);
        bedrooms_column.setText(bedroomsComboText[3]);
        surface_column.setText(usableSurfaceComboText[3]);
        garden_column.setText(gardenSurfaceComboText[3]);
        floors_column.setText(floorsComboText[3]);
        rentalHouseName_column.setText(houseNameComboText[3]);

    }


    public void updateNotifications(String s) {
        this.notificationsText += s + "\n";
        notification_textField.setText(this.notificationsText);
    }






    public void setComboItems(){
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


    public RentalHouses getRentalHouses() {
        return rentalHouses;
    }


    public void setItemsCity() {
        List<String> elems = subject.getDatabase().rentalsList()
                .stream()
                .map(RentalHouse::getCity)
                .collect(Collectors.toList());

        ObservableList<String> items = FXCollections.observableList(elems);
        city_combo.setItems(items);
    }

    public void setItemsCountry() {
        List<String> elems = subject.getDatabase().rentalsList()
                .stream()
                .map(RentalHouse::getCountry)
                .collect(Collectors.toList());

        ObservableList<String> items = FXCollections.observableList(elems);
        country_combo.setItems(items);
    }

    public void setItemsHousingType() {
        List<String> elems = subject.getDatabase().rentalsList()
                .stream()
                .map(RentalHouse::getHousing_type)
                .collect(Collectors.toList());

        ObservableList<String> items = FXCollections.observableList(elems);
        types_combo.setItems(items);
    }

    public void setItemsHouseName() {
        List<String> elems = subject.getDatabase().rentalsList()
                .stream()
                .map(RentalHouse::getHouse_name)
                .collect(Collectors.toList());

        ObservableList<String> items = FXCollections.observableList(elems);
        houseName_combo.setItems(items);
    }


    public void setItemsSurface() {
        List<Integer> elems = subject.getDatabase().rentalsList()
                .stream()
                .map(RentalHouse::getUsable_surface)
                .collect(Collectors.toList());

        ObservableList<Integer> items = FXCollections.observableList(elems);
        surface_combo.setItems(items);
    }

    public void setItemsGarden() {
        List<Integer> elems = subject.getDatabase().rentalsList()
                .stream()
                .map(RentalHouse::getGarden_surface)
                .collect(Collectors.toList());

        ObservableList<Integer> items = FXCollections.observableList(elems);
        garden_combo.setItems(items);
    }

    public void setItemsBedrooms() {
        List<Integer> elems = subject.getDatabase().rentalsList()
                .stream()
                .map(RentalHouse::getBedrooms)
                .collect(Collectors.toList());

        ObservableList<Integer> items = FXCollections.observableList(elems);
        bedroom_combo.setItems(items);
    }

    public void setItemsFloors() {
        List<Integer> elems = subject.getDatabase().rentalsList()
                .stream()
                .map(RentalHouse::getFloors)
                .collect(Collectors.toList());

        ObservableList<Integer> items = FXCollections.observableList(elems);
        floors_combo.setItems(items);
    }

    public void setItemsPrice() {
        List<Integer> elems = subject.getDatabase().rentalsList()
                .stream()
                .map(RentalHouse::getPrice)
                .collect(Collectors.toList());

        ObservableList<Integer> items = FXCollections.observableList(elems);
        price_combo.setItems(items);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Scene getClientScene() {
        return clientScene;
    }

    public void setClientScene(Scene clientScene) {
        this.clientScene = clientScene;
    }



    public void refreshTables() {
        displayTableRentals(this.rentalHouses);
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
        List<RentalHouse> searched = subject.getDatabase().rentalsList();
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

        getPieChart().setData(list);
    }


    public HashMap<String, Number> computePrice() {
        HashMap<String, Number> hashMap = new HashMap<>();
        Set<String> set = new HashSet<>();

        for (RentalHouse r : subject.getDatabase().rentalsList()) {
            set.add(String.valueOf(subject.getDatabase().findByPrice(r.getPrice())));
        }

        for (String s : set) {
            int count = 0;
            for (RentalHouse r : subject.getDatabase().rentalsList()) {
                if (String.valueOf(subject.getDatabase().findByPrice(r.getPrice())).equals(s))
                    count++;
            }
            hashMap.put(s, count);
        }
        return hashMap;
    }




    public void displayTableRentals(RentalHouses rentalHouses) {
        //ObservableList<RentalHouse> rentals_list = FXCollections.observableArrayList(rentalHouses.getRentalHouseList());
        ObservableList<RentalHouse> rentals_list = FXCollections.observableArrayList(subject.getDatabase().rentalsList());
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


    //Statistics
    public PieChart getPieChart() {
        return pieChart;
    }

    public PieChart getPieChart2() {
        return pieChart2;
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
        Stage window = (Stage) signIn_btn.getScene().getWindow();
        window.setTitle("Sign In");
        window.setScene(subject.getSignInController().getSignInScene());
    }


    @FXML
    void clientRegister(ActionEvent event) throws Exception {
           /* Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("view/signUp/signUp.fxml"));
            Stage window = (Stage) register_btn.getScene().getWindow();
            window.setTitle("Client");
            window.setScene(new Scene(root, 1000, 600));*/


    }


    @Override
    public void update() {
        System.out.println("updated from Cclient");
    }
}
