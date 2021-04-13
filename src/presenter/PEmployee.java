package presenter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import model.*;
import model.persistence.ClientPersistence;
import model.persistence.EmployeePersistence;
import model.persistence.RentalsPersistence;
import view.employee.IEmployee;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

public class PEmployee {

    private IEmployee iEmployee;
    private Employees employees;
    private Employee employee;
    EmployeePersistence employeePersistence = new EmployeePersistence("./employee_saved.xml");
    private Client client;
    private Clients clients;
    ClientPersistence clientPersistence = new ClientPersistence("./clients_saved.xml");
    private RentalHouse rentalHouse;
    private RentalHouses rentalHouses;
    RentalsPersistence rentalsPersistence = new RentalsPersistence("./rentals_saved.xml");


    public PEmployee(IEmployee iEmployee) {
        this.iEmployee = iEmployee;
        clients = new Clients();
        rentalHouses = new RentalHouses();
        employees = new Employees();
    }


    public void createAccount() throws Exception {
        employee = iEmployee.getFromRegisterTextFields();
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
        //initializePEmployees();
        loadFromXML();

        String username = iEmployee.getUsername().getText();
        String password = iEmployee.getPassword().getText();

        employee = searchEmployee(username, password);
        System.out.println("signed as : " + employee.getUsername());


        if(employee.getRole().equals("admin")){
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("view/admin/admin_view.fxml"));
            Stage window = (Stage) iEmployee.getSignInBtn().getScene().getWindow();
            window.setTitle("Admin");
            window.setScene(new Scene(root, 1000, 600));

        }
        else {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("view/employee/employee_view.fxml"));
            Stage window = (Stage) iEmployee.getSignInBtn().getScene().getWindow();
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

        iEmployee.displayTableRentals(this.rentalHouses);
        rentalsPersistence.save(this.rentalHouses);

    }

    public void refreshTables(){
        iEmployee.displayTableRentals(this.rentalHouses);
        iEmployee.displayClientTable(this.clients);
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
        iEmployee.getCity_combo().valueProperty().set(null);
        iEmployee.getCountry_combo().valueProperty().set(null);
        iEmployee.getSurface_combo().valueProperty().set(null);
        iEmployee.getGarden_combo().valueProperty().set(null);
        iEmployee.getBedroom_combo().valueProperty().set(null);
        iEmployee.getHouseName_combo().valueProperty().set(null);
        iEmployee.getFloors_combo().valueProperty().set(null);
        iEmployee.getPrice_combo().valueProperty().set(null);
        iEmployee.getTypes_combo().valueProperty().set(null);
    }


    public void searchBtn() {
        List<RentalHouse> searched = rentalHouses.getRentalHouseList();

        if (iEmployee.getCity_combo().getValue() != null) {
            searched = searched
                    .stream()
                    .filter(t -> t.getCity().equals(iEmployee.getCity_combo().getValue()))
                    .collect(Collectors.toList());
        }
        if (iEmployee.getCountry_combo().getValue() != null) {
            searched = searched
                    .stream()
                    .filter(t -> t.getCountry().equals(iEmployee.getCountry_combo().getValue())).collect(Collectors.toList());
        }
        if (iEmployee.getSurface_combo().getValue() != null) {
            searched = searched
                    .stream()
                    .filter(t -> t.getUsable_surface().equals(iEmployee.getSurface_combo().getValue()))
                    .collect(Collectors.toList());
        }
        if (iEmployee.getGarden_combo().getValue() != null) {
            searched = searched
                    .stream()
                    .filter(t -> t.getGarden_surface().equals(iEmployee.getGarden_combo().getValue()))
                    .collect(Collectors.toList());
        }
        if (iEmployee.getBedroom_combo().getValue() != null) {
            searched = searched
                    .stream()
                    .filter(t -> t.getBedrooms().equals(iEmployee.getBedroom_combo().getValue()))
                    .collect(Collectors.toList());
        }
        if (iEmployee.getHouseName_combo().getValue() != null) {
            searched = searched
                    .stream()
                    .filter(t -> t.getHouse_name().equals(iEmployee.getHouseName_combo().getValue())).
                    collect(Collectors.toList());
        }
        if (iEmployee.getFloors_combo().getValue() != null) {
            searched = searched
                    .stream()
                    .filter(t -> t.getFloors().equals(iEmployee.getFloors_combo().getValue()))
                    .collect(Collectors.toList());
        }
        if (iEmployee.getPrice_combo().getValue() != null) {
            searched = searched
                    .stream()
                    .filter(t -> t.getPrice().equals(iEmployee.getPrice_combo().getValue()))
                    .collect(Collectors.toList());
        }
        if (iEmployee.getTypes_combo().getValue() != null) {
            searched = searched
                    .stream()
                    .filter(t -> t.getHousing_type().equals(iEmployee.getTypes_combo().getValue()))
                    .collect(Collectors.toList());
        }

        ArrayList<RentalHouse> aux = new ArrayList<>(searched);
        RentalHouses searchedRentals = new RentalHouses(aux);
        iEmployee.displayTableRentals(searchedRentals);
        clearCombo();

    }

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

        iEmployee.getBarChart().getData().add(series);
        iEmployee.getPieChart().setData(list);

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
        rentalHouse = iEmployee.getRentalFromTextFields();
        rentalHouses.addHouse(rentalHouse);
        iEmployee.displayTableRentals(this.rentalHouses);

    }

    public void readRental() {
        rentalHouse = iEmployee.selectRental();
        iEmployee.setRentalsToTextFields(rentalHouse);
        iEmployee.displayTableRentals(this.rentalHouses);
    }


    public void updateRentals() {
        rentalHouse = iEmployee.selectRental();
        RentalHouse rentalHouse2 = iEmployee.getRentalFromTextFields();
        rentalHouses.updateHouse(rentalHouse, rentalHouse2);
        iEmployee.displayTableRentals(rentalHouses);

    }

    public void deleteRentals() {
        rentalHouse = iEmployee.selectRental();
        rentalHouses.deleteRental(rentalHouse);
        iEmployee.displayTableRentals(rentalHouses);
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
        iEmployee.displayClientTable(this.clients);
        clientPersistence.save(this.clients);
    }

    public void createCLient(){
        client = iEmployee.getTextFromFields();
        clients.addClient(client);
        iEmployee.displayClientTable(clients);

    }

    public void readClient(){
        client = iEmployee.selectClient();
        iEmployee.setClientsToTextFields(client);
        iEmployee.displayClientTable(this.clients);
    }

    public void deleteClient(){
        client = iEmployee.selectClient();
        clients.deleteClient(client);
        iEmployee.displayClientTable(clients);
    }

    public void updateClient() throws IOException {
        client = iEmployee.selectClient();
        Client client2 = iEmployee.getTextFromFields();
        clients.updateClient(client, client2);
        iEmployee.displayClientTable(clients);
        saveToXML();
        loadFromXML();

    }








}






















