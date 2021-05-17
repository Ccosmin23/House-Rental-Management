package view.admin;

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
import presenter.PAdmin;
import presenter.PEmployee;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GUI_Admin implements Initializable, IAdminView {
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

    PEmployee pEmployee;
    PAdmin pAdmin;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /*admin_first_name.setResizable(false);
        admin_last_name.setResizable(false);
        admin_username.setResizable(false);
        admin_password.setResizable(false);
        admin_role.setResizable(false);*/

        pAdmin = new PAdmin(this);
        //pAdmin.initializePEmployees();

        pAdmin.loadFromXML();
        pAdmin.refresh();

        initEmployeeTable();


    }





    @Override
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

    @Override
    public Employee getTextFromTextFields() {
        Employee employee = new Employee(tf_employee_firstName.getText(), tf_employee_lastName.getText(), tf_employee_username.getText(), tf_employee_password.getText(), tf_employee_role.getText());
        return employee;
    }

    @Override
    public void setTextToTextFields(Employee employee) {
        tf_employee_firstName.setText(employee.getFirstName());
        tf_employee_lastName.setText(employee.getLastName());
        tf_employee_username.setText(employee.getUsername());
        tf_employee_password.setText(employee.getPassword());
        tf_employee_role.setText(employee.getRole());
    }

    @FXML
    void handleAdminSignOut(ActionEvent event) throws Exception {
        pAdmin.saveToXML();

        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("view/client/client_view.fxml"));
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
         pAdmin.createEmployee();
    }

    @FXML
    void delete_employee(ActionEvent event) {
          pAdmin.deleteEmployee();
    }

    @FXML
    void readEmployee(ActionEvent event) {
        pAdmin.readEmployee();
    }

    @FXML
    void updateEmployee(ActionEvent event) throws IOException {
        pAdmin.updateEmployee();
    }






    //@Override
    public Button getSignInBtn() {
        return null;
    }


    @Override
    public void addEmployee(String firstName, String lastName, String username, String password, String email) {

    }

    @Override
    public void save(Employee employee) {

    }
}
