package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.*;
import model.persistence.ClientPersistence;
import model.persistence.EmployeePersistence;
import model.persistence.RentalsPersistence;

import java.net.URL;
import java.util.ResourceBundle;

public class CSignIn implements Initializable {

    @FXML
    public AnchorPane base_signIn_pane, signIn_pane;
    public Button signIn_btn, createAccount_btn, signInGoBack_btn;
    public TextField tf_username, tf_password;
    EmployeePersistence employeePersistence = new EmployeePersistence("./employee_saved.xml");
    ClientPersistence clientPersistence = new ClientPersistence("./clients_saved.xml");
    RentalsPersistence rentalsPersistence = new RentalsPersistence("./rentals_saved.xml");
    private CEmployee cEmployee;
    private CAdmin cAdmin;
    private Employees employees;
    private Employee employee;
    private Client client;
    private Clients clients;
    private RentalHouse rentalHouse;
    private RentalHouses rentalHouses;


    public CSignIn() {
        clients = new Clients();
        rentalHouses = new RentalHouses();
        employees = new Employees();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        base_signIn_pane.setVisible(true);


    }

    @FXML
    public void signInBtn(ActionEvent event) throws Exception {
        //initializePEmployees();
        loadFromXML();

        String username = getUsername().getText();
        String password = getPassword().getText();

        employee = searchEmployee(username, password);
        System.out.println("signed as : " + employee.getUsername());


        if (employee.getRole().equals("admin")) {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("view/admin_view.fxml"));
            Stage window = (Stage) getSignInBtn().getScene().getWindow();
            window.setTitle("Admin");
            window.setScene(new Scene(root, 1000, 600));

        } else {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("view/employee_view.fxml"));
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

    public void loadFromXML() {
        this.rentalHouses = rentalsPersistence.load();
        this.clients = clientPersistence.load();
        this.employees = employeePersistence.load();
//        refreshTables();
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

    @FXML
    public void createAccount(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("view/admin_view.fxml"));
        Stage window = (Stage) createAccount_btn.getScene().getWindow();
        window.setTitle("Client");
        window.setScene(new Scene(root, 1000, 600));
    }


    @FXML
    public void goBackFromSignInPane(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("view/client_view.fxml"));
        Stage window = (Stage) signInGoBack_btn.getScene().getWindow();
        window.setTitle("Client");
        window.setScene(new Scene(root, 1000, 600));
    }


    public Button getSignInBtn() {
        return signIn_btn;
    }


    public TextField getUsername() {
        return tf_username;

    }

    public TextField getPassword() {
        return tf_password;
    }


}