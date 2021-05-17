package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
import model.*;
import model.persistence.ClientPersistence;
import model.persistence.EmployeePersistence;
import model.persistence.RentalsPersistence;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

public class CEmployee implements Initializable {

    private Employees employees;
    private Employee employee;
    EmployeePersistence employeePersistence = new EmployeePersistence("./employee_saved.xml");
    private Client client;
    private Clients clients;
    ClientPersistence clientPersistence = new ClientPersistence("./clients_saved.xml");
    private RentalHouse rentalHouse;
    private RentalHouses rentalHouses;
    RentalsPersistence rentalsPersistence = new RentalsPersistence("./rentals_saved.xml");


    public CEmployee() {
        clients = new Clients();
        rentalHouses = new RentalHouses();
        employees = new Employees();
    }


    public void createAccount() throws Exception {
        employee = getFromRegisterTextFields();
        employees.addEmployee(employee);
        System.out.println("new user signed as: " + employee.getUsername());
        saveToXML();
    }

    public void initializePEmployees() {
        Employee e1 = new Employee("fName_employee1", "lName1_employee1", "user", "pass", "employee");
        Employee e2 = new Employee("fName_employee2", "lName2_employee2", "emp2", "emp2", "employee");
        Employee e3 = new Employee("fName_employee3", "lName3_employee3", "emp3", "emp3", "employee");
        Employee e4 = new Employee("fName_employee4", "lName4_employee4", "admin", "admin", "admin");
        employees.addEmployee(e1);
        employees.addEmployee(e2);
        employees.addEmployee(e3);
        employees.addEmployee(e4);
        employeePersistence.save(employees);
        //iEmployee.displayEmployeeTable(this.employees);
    }

    public void signInBtn() throws Exception{
        initializePEmployees();
        loadFromXML();

        String username = getUsername().getText();
        String password = getPassword().getText();

        employee = searchEmployee(username, password);
        System.out.println("signed as : " + employee.getUsername());


        if(employee.getRole().equals("admin")){
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("view/admin/admin_view.fxml"));
            Stage window = (Stage) getSignInBtn().getScene().getWindow();
            window.setTitle("Admin");
            window.setScene(new Scene(root, 1000, 600));

        }
        else {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("view/employee/employee_view.fxml"));
            Stage window = (Stage) getSignInBtn().getScene().getWindow();
            window.setTitle("Employee");
            window.setScene(new Scene(root, 1000, 600));
        }

    }

    public Employee searchEmployee(String username, String password) {
        for (Employee e : this.employees.getEmployeeList()) {
            if (e.getUsername().equals(username) && e.getPassword().equals(password))
                return e;
        }

        return null;
    }

    public RentalHouses getRentalHouses() {
        return rentalHouses;
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

    public void refreshTables(){
        displayTableRentals(this.rentalHouses);
        displayClientTable(this.clients);
    }


    public void saveToXML() throws IOException {
        rentalsPersistence.save(rentalHouses);
        clientPersistence.save(clients);
        employeePersistence.save(employees);

    }


    public void loadFromXML(){
        this.rentalHouses = rentalsPersistence.load();
        this.clients = clientPersistence.load();
        this.employees = employeePersistence.load();
        refreshTables();
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
                    .filter(t -> t.getCountry().equals(getCountry_combo().getValue())).collect(Collectors.toList());
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
                    .filter(t -> t.getHouse_name().equals(getHouseName_combo().getValue())).
                            collect(Collectors.toList());
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

    @FXML
    public void saveReports() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("savedReports_CSV.csv"), StandardCharsets.UTF_8));
        try {
            for (RentalHouse house : rentalHouses.getRentalHouseList()) {
                StringBuffer oneLine = new StringBuffer();
                oneLine.append(house.getCity() != null ? house.getCity() : "eroareCity");
                oneLine.append("\n");

                oneLine.append(house.getCountry() != null ? house.getCountry() : "eroareCountry");
                oneLine.append("\n");

                oneLine.append(house.getPrice() != null ? house.getPrice() : "eroarePrice");
                oneLine.append("\n");

                oneLine.append(house.getHousing_type() != null ? house.getHousing_type() : "eroareHousingType");
                oneLine.append("\n");

                oneLine.append(house.getBedrooms() != null ? house.getBedrooms() : "eroareBedrooms");
                oneLine.append("\n");

                oneLine.append(house.getUsable_surface() != null ? house.getUsable_surface() : "eroareSurface");
                oneLine.append("\n");

                oneLine.append(house.getGarden_surface() != null ? house.getGarden_surface() : "eroareGarden");
                oneLine.append("\n");

                oneLine.append(house.getFloors() != null ? house.getFloors() : "eroareFloors");
                oneLine.append("\n");

                oneLine.append(house.getHouse_name() != null ? house.getHouse_name() : "eroareHouseName");
                oneLine.append("\n\n\n");

                bw.write(oneLine.toString());
                bw.newLine();
            }
            bw.flush();
            bw.close();
        } catch (UnsupportedEncodingException e) {
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }

        Gson json = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter file = new FileWriter("E:/Facultate/An3/Sem2/PS/Proiect_PS_RENTALS/json.txt")) {
            file.write(String.valueOf(json));
        }

        List<String> rentalsJson = new ArrayList<>();
        for (RentalHouse r : rentalHouses.getRentalHouseList()) {
            rentalsJson.add(json.toJson(r));
        }
        System.out.println(rentalsJson);

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


    public void createRental() {
        rentalHouse = getRentalFromTextFields();
        rentalHouses.addHouse(rentalHouse);
        displayTableRentals(this.rentalHouses);

    }

    public void readRental() {
        rentalHouse = selectRental();
        setRentalsToTextFields(rentalHouse);
        displayTableRentals(this.rentalHouses);
    }


    public void updateRentals() {
        rentalHouse = selectRental();
        RentalHouse rentalHouse2 = getRentalFromTextFields();
        rentalHouses.updateHouse(rentalHouse, rentalHouse2);
        displayTableRentals(rentalHouses);

    }

    public void deleteRentals() {
        rentalHouse = selectRental();
        rentalHouses.deleteRental(rentalHouse);
        displayTableRentals(rentalHouses);
    }












    public Clients getClients() {
        return clients;
    }

    public void initializePClient(){
        Client c1 = new Client("pafnuti", "cebisev", "russian.math@gmail.com", "rented", "Apartament1", "Alba Iulia", "Apartament", 500);
        Client c2 = new Client("louis", "cauchy", "makeStudentsLifeHeavier@gmail.com", "leave", "House1", "Predeal", "House", 1500);
        Client c3 = new Client("mihai", "eminescu", "mihaita.eminescu@gmail.com", "on hold", "Chalet1", "Murgeni", "Chalet", 2500);
        Client c4 = new Client("sandor", "petofi", "hero.oftransylvania@gmail.com", "rented", "Apartament2", "Ploiesti", "Apartament", 3500);
        clients.addClient(c1);
        clients.addClient(c2);
        clients.addClient(c3);
        clients.addClient(c4);
        displayClientTable(this.clients);
        clientPersistence.save(this.clients);
    }

    public void createCLient(){
        client = getTextFromFields();
        clients.addClient(client);
        displayClientTable(clients);

    }

    public void readClient(){
        client = selectClient();
        setClientsToTextFields(client);
        displayClientTable(this.clients);
    }

    public void deleteClient(){
        client = selectClient();
        clients.deleteClient(client);
        displayClientTable(clients);
    }

    public void updateClient() throws IOException {
        client = selectClient();
        Client client2 = getTextFromFields();
        clients.updateClient(client, client2);
        displayClientTable(clients);
        saveToXML();
        loadFromXML();

    }
















































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
    CClient cClient;
    CEmployee cEmployee;

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

        cEmployee = new CEmployee();
        initRentalsTable();
        initClientsTable();

        //initialize tables
        initializeRental();
        initializePClient();

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


    public Employee getFromRegisterTextFields() {
        Employee employee = new Employee(tf_firstName.getText(), tf_lastName.getText(), tf_username.getText(), tf_password.getText(), "new user");
        return employee;
    }

    @FXML
    public void createAccount(ActionEvent event) throws Exception {
        createAccount();
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

    public Client getTextFromFields() {
        Client client = new Client(tf_clientFirstName.getText(), tf_clientLastName.getText(), tf_clientEmail.getText(), tf_clientStatus.getText(),
                tf_clientHouseRented.getText(), tf_clientCity.getText(), tf_clientHousingType.getText(), Integer.parseInt(tf_clientPrice.getText()));

        return client;
    }



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

    public void displayClientTable(Clients clients) {
        ObservableList<Client> clients_list = FXCollections.observableArrayList(clients.getClientList());
        clients_table.setItems(clients_list);
    }

    public Client selectClient() {
        Client client = clients_table.getSelectionModel().getSelectedItem();
        return client;
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
        RentalHouse rentalHouse = new RentalHouse(tf_city.getText(), tf_country.getText(), tf_price.getText(),
                tf_housingType.getText(), tf_bedrooms.getText(), tf_surface.getText(),
                tf_garden.getText(), tf_floors.getText(), tf_houseName.getText());

        return rentalHouse;
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



    public void displayTableRentals(RentalHouses rentalHouses) {
        ObservableList<RentalHouse> rentals_list = FXCollections.observableArrayList(rentalHouses.getRentalHouseList());
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
    void handleUpdateRentals(ActionEvent event) throws Exception{
        updateRentals();
        //pEmployees.refreshTables();

        saveToXML();
        loadFromXML();

        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("view/employee_view.fxml"));
        Stage window = (Stage) updateRental_btn.getScene().getWindow();
        window.setTitle("Sign In");
        window.setScene(new Scene(root, 1000, 600));

        // pEmployees.refreshTables();

    }

    public Button rentalsUpdatePressed(){
        return updateRental_btn;
    }



    @FXML
    void handleDeleteRentals(ActionEvent event) {
        deleteRentals();
    }

    // comboboxes for RENTALS
    public ComboBox<String> getCity_combo() {return city_combo;}

    public ComboBox<String> getCountry_combo(){return country_combo; }

    public ComboBox<String> getSurface_combo(){return surface_combo;}

    public ComboBox<String> getGarden_combo(){return garden_combo;}

    public ComboBox<String> getBedroom_combo(){return bedroom_combo;}

    public ComboBox<String> getHouseName_combo(){return houseName_combo;}

    public ComboBox<String> getFloors_combo(){return floors_combo;}

    public ComboBox<String> getPrice_combo(){return price_combo;}

    public ComboBox<String> getTypes_combo(){return types_combo;}






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


    //Statistics
    public PieChart getPieChart() {return pieChart;}

    public BarChart<String, Number> getBarChart(){
        return barChart;
    }




    @FXML
    void employeeSignOut(ActionEvent event) throws Exception{
        //pClients.saveToXML();
        //pRentals.saveToXML();
        saveToXML();

        //System.out.println(pClients.getClients().toString());

        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("view/client_view.fxml"));
        Stage window = (Stage) employee_signOut.getScene().getWindow();
        window.setTitle("Sign In");
        window.setScene(new Scene(root, 1000, 600));
    }

}






















