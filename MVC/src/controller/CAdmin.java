package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Employee;
import model.Employees;
import model.persistence.EmployeePersistence;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CAdmin implements Initializable {

    EmployeePersistence employeePersistence = new EmployeePersistence("./employee_saved.xml");
    private Employees employees;
    private Employee employee;
    private Employee employee2;
    private Employees employees2;

    public CAdmin() {
        employees = new Employees();
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
        displayEmployeeTable(this.employees);
    }




    public void refresh(){
        displayEmployeeTable(this.employees);
    }


    public void saveToXML() throws IOException {
        employeePersistence.save(employees);
    }


    public void loadFromXML() {
        this.employees = employeePersistence.load();
    }


    public void createEmployee() throws IOException {
        employee = getTextFromTextFields();
        employees.addEmployee(employee);
        saveToXML();
        loadFromXML();
        displayEmployeeTable(this.employees);

    }

    public void deleteEmployee() {
        employee = selectEmployee();
        employees.deleteEmployee(employee);
        displayEmployeeTable(this.employees);
    }

    public void updateEmployee() throws IOException {
        employee = selectEmployee();
        employee2 = getTextFromTextFields();
        employees.updateEmployee(employee, employee2);
        displayEmployeeTable(employees);
        saveToXML();
        loadFromXML();
    }

    public void readEmployee() {
        employee = selectEmployee();
        setTextToTextFields(employee);
        displayEmployeeTable(this.employees);

    }


















































    @FXML
    public AnchorPane admin_pane, admin2_pane;
    @FXML
    public Button adminCreate_btn;
    @FXML
    public Button adminRead_btn;
    @FXML
    public Button adminUpdate_btn;
    @FXML
    public Button adminDelete_btn;
    @FXML
    public Button adminSignOut_btn;
    @FXML
    public TextField tf_employee_firstName;
    @FXML
    public TextField tf_employee_username;
    @FXML
    public TextField tf_employee_role;
    @FXML
    public TableView<Employee> employee_table;
    @FXML
    public TableColumn<Employee, String> admin_first_name;
    @FXML
    public TableColumn<Employee, String> admin_last_name;
    @FXML
    public TableColumn<Employee, String> admin_username;
    @FXML
    public TableColumn<Employee, String> admin_password;
    @FXML
    public TableColumn<Employee, String> admin_role;
    @FXML
    public TextField tf_employee_lastName;
    @FXML
    public PasswordField tf_employee_password;
    @FXML
    public AnchorPane newAccount_pane;
    public Button innerSignIn_btn, goBack_btn, createAccount_btn;
    public TextField tf_firstName, tf_email, tf_lastName, tf_username,tf_password;

    CEmployee cEmployee;
    CAdmin cAdmin;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /*admin_first_name.setResizable(false);
        admin_last_name.setResizable(false);
        admin_username.setResizable(false);
        admin_password.setResizable(false);
        admin_role.setResizable(false);*/

        cAdmin = new CAdmin();
        //pAdmin.initializePEmployees();

        loadFromXML();
        refresh();

        initEmployeeTable();


    }





    public Employee getFromRegisterTextFields() {
        Employee employee = new Employee(tf_employee_firstName.getText(), tf_employee_lastName.getText(), tf_employee_username.getText(), tf_employee_password.getText(), tf_employee_role.getText());
        return employee;
    }







    public void initEmployeeTable() {
        admin_first_name.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        admin_last_name.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        admin_username.setCellValueFactory(new PropertyValueFactory<>("username"));
        admin_password.setCellValueFactory(new PropertyValueFactory<>("password"));
        admin_role.setCellValueFactory(new PropertyValueFactory<>("role"));
    }







    public void setRentalsToTextFields(Employee employee) {
        tf_employee_firstName.setText(employee.getFirstName());
        tf_employee_lastName.setText(employee.getLastName());
        tf_employee_username.setText(employee.getUsername());
        tf_employee_password.setText(employee.getPassword());
        tf_employee_role.setText(employee.getRole());
    }

    //@Override
    public void displayEmployeeTable(Employees employees) {
        ObservableList<Employee> employee_list = FXCollections.observableArrayList(employees.getEmployeeList());
        employee_table.setItems(employee_list);
    }

    public Employee getTextFromTextFields() {
        Employee employee = new Employee(tf_employee_firstName.getText(), tf_employee_lastName.getText(), tf_employee_username.getText(), tf_employee_password.getText(), tf_employee_role.getText());
        return employee;
    }

    public void setTextToTextFields(Employee employee) {
        tf_employee_firstName.setText(employee.getFirstName());
        tf_employee_lastName.setText(employee.getLastName());
        tf_employee_username.setText(employee.getUsername());
        tf_employee_password.setText(employee.getPassword());
        tf_employee_role.setText(employee.getRole());
    }

    @FXML
    void handleAdminSignOut(ActionEvent event) throws Exception {
        saveToXML();

        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("view/client_view.fxml"));
        Stage window = (Stage) adminSignOut_btn.getScene().getWindow();
        window.setTitle("Client");
        window.setScene(new Scene(root, 1000, 600));

    }

    public Employee selectEmployee() {
        Employee employee = employee_table.getSelectionModel().getSelectedItem();
        return employee;
    }




    @FXML
    void create_employee(ActionEvent event) throws IOException {
        createEmployee();
    }

    @FXML
    void delete_employee(ActionEvent event) {
        deleteEmployee();
    }

    @FXML
    void readEmployee(ActionEvent event) {
        readEmployee();
    }

    @FXML
    void updateEmployee(ActionEvent event) throws IOException {
        updateEmployee();
    }






    //@Override
    public Button getSignInBtn() {
        return null;
    }


    public void addEmployee(String firstName, String lastName, String username, String password, String email) {

    }

    public void save(Employee employee) {

    }


}
