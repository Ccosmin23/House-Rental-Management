package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Employee;
import model.Employees;
import model.Observer;
import model.Subject;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class CAdmin implements Initializable, Observer {
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
    public TextField tf_employee_firstName, notification_textField;
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
    public TextField tf_firstName, tf_email, tf_lastName, tf_username, tf_password;

    @FXML
    private Button ro_button,en_button,de_button,es_button;

    //CRUD on CLIENTS
    private String[] createEmployeeButtonText = {"Creaza","Create","Erstellen","Crear"};
    private String[] readEmployeeButtonText = {"Citeste","Read","Lesen","Leer"};
    private String[] updateEmployeeButtonText = {"Actualizeaza","Update","Aktualisieren","Actualizar"};
    private String[] deleteEmployeeButtonText = {"Sterge","Delete","löschen","Eliminar"};
    private String[] signOutButtonText = {"Delogare","Sign out","Ausloggen", "Desconectar"};

    //COLUMNS on CLIENTS
    private String[] firstNameText = {"Prenume","First Name","Vorname","Primer nombre"};
    private String[] lastNameText = {"Nume","Last Name","Nachname", "Apellido"};
    private String[] usernameText = {"Nume utilizator","Username","Nutzername","Nombre de usuario"};
    private String[] passwordText = {"Parola","Password","Passwort","Contraseña"};
    private String[] roleText = {"Rol","Role","Rolle","Papel"};
    private String[] notificationText = {"Anunturi","Notifications","Benachrichtigungen","Notificaciones"};





    CEmployee cEmployee;
    CAdmin cAdmin;


    private Employees employees;
    private Employee employee;
    private Employee employee2;
    private Employees employees2;

    private Subject subject;
    private Scene adminScene;
    private String notificationsText = "";


    public CAdmin() {
        employees = new Employees();
    }



    @FXML
    void romanianLanguage() {
        tf_employee_firstName.setPromptText(firstNameText[0]);
         tf_employee_lastName.setPromptText(lastNameText[0]);
        tf_employee_username.setPromptText(usernameText[0]);
        tf_employee_password.setPromptText(passwordText[0]);
        tf_employee_role.setPromptText(roleText[0]);

        notification_textField.setPromptText(notificationText[0]);
        adminCreate_btn.setText(createEmployeeButtonText[0]);
        adminRead_btn.setText(readEmployeeButtonText[0]);
        adminUpdate_btn.setText(updateEmployeeButtonText[0]);
        adminDelete_btn.setText(deleteEmployeeButtonText[0]);
        adminSignOut_btn.setText(signOutButtonText[0]);

        admin_first_name.setText(firstNameText[0]);
        admin_last_name.setText(lastNameText[0]);
        admin_username.setText(usernameText[0]);
        admin_password.setText(passwordText[0]);
        admin_role.setText(roleText[0]);
    }

    @FXML
    void englishLanguage() {
        tf_employee_firstName.setPromptText(firstNameText[1]);
        tf_employee_lastName.setPromptText(lastNameText[1]);
        tf_employee_username.setPromptText(usernameText[1]);
        tf_employee_password.setPromptText(passwordText[1]);
        tf_employee_role.setPromptText(roleText[1]);

        notification_textField.setPromptText(notificationText[1]);
        adminCreate_btn.setText(createEmployeeButtonText[1]);
        adminRead_btn.setText(readEmployeeButtonText[1]);
        adminUpdate_btn.setText(updateEmployeeButtonText[1]);
        adminDelete_btn.setText(deleteEmployeeButtonText[1]);
        adminSignOut_btn.setText(signOutButtonText[1]);

        admin_first_name.setText(firstNameText[1]);
        admin_last_name.setText(lastNameText[1]);
        admin_username.setText(usernameText[1]);
        admin_password.setText(passwordText[1]);
        admin_role.setText(roleText[1]);
    }

    @FXML
    void germanLanguage() {
        tf_employee_firstName.setPromptText(firstNameText[2]);
        tf_employee_lastName.setPromptText(lastNameText[2]);
        tf_employee_username.setPromptText(usernameText[2]);
        tf_employee_password.setPromptText(passwordText[2]);
        tf_employee_role.setPromptText(roleText[2]);

        notification_textField.setPromptText(notificationText[2]);
        adminCreate_btn.setText(createEmployeeButtonText[2]);
        adminRead_btn.setText(readEmployeeButtonText[2]);
        adminUpdate_btn.setText(updateEmployeeButtonText[2]);
        adminDelete_btn.setText(deleteEmployeeButtonText[2]);
        adminSignOut_btn.setText(signOutButtonText[2]);

        admin_first_name.setText(firstNameText[2]);
        admin_last_name.setText(lastNameText[2]);
        admin_username.setText(usernameText[2]);
        admin_password.setText(passwordText[2]);
        admin_role.setText(roleText[2]);
    }

    @FXML
    void spanishLanguage() {
        tf_employee_firstName.setPromptText(firstNameText[3]);
        tf_employee_lastName.setPromptText(lastNameText[3]);
        tf_employee_username.setPromptText(usernameText[3]);
        tf_employee_password.setPromptText(passwordText[3]);
        tf_employee_role.setPromptText(roleText[3]);

        notification_textField.setPromptText(notificationText[3]);
        adminCreate_btn.setText(createEmployeeButtonText[3]);
        adminRead_btn.setText(readEmployeeButtonText[3]);
        adminUpdate_btn.setText(updateEmployeeButtonText[3]);
        adminDelete_btn.setText(deleteEmployeeButtonText[3]);
        adminSignOut_btn.setText(signOutButtonText[3]);

        admin_first_name.setText(firstNameText[3]);
        admin_last_name.setText(lastNameText[3]);
        admin_username.setText(usernameText[3]);
        admin_password.setText(passwordText[3]);
        admin_role.setText(roleText[3]);

    }




    public Scene getClientScene() {
        return adminScene;
    }

    public void setClientScene(Scene adminScene) {
        this.adminScene = adminScene;
    }


    public void refresh() {
        displayEmployeeTable(this.employees);
    }


    public void createEmployee() throws IOException {
        employee = getTextFromTextFields();
        subject.getDatabase().addEmployee(employee);

        displayEmployeeTable(this.employees);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String time = dtf.format(now);

        String s = new String("Admin added " + employee.getUsername() + " at " + time);
        subject.notifyEmployee(s);
    }

    public void deleteEmployee() {
        employee = selectEmployee();
        subject.getDatabase().deleteEmployee(employee);
        //employees.deleteEmployee(employee);
        displayEmployeeTable(this.employees);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String time = dtf.format(now);

        String s = new String("Admin deleted " + employee.getUsername() + " at " + time);
        subject.notifyEmployee(s);
    }

    public void updateEmployee() throws IOException {
        employee = selectEmployee();
        employee2 = getTextFromTextFields();
        subject.getDatabase().updateEmployee(employee, employee2);

        displayEmployeeTable(this.employees);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String time = dtf.format(now);

        String s = new String("Admin updated " + employee.getUsername() + " at " + time);
        subject.notifyEmployee(s);
    }

    public void readEmployee() {
        employee = selectEmployee();
        setTextToTextFields(employee);
        displayEmployeeTable(this.employees);

    }


    public void init() {

        displayEmployeeTable(this.employees);
        initEmployeeTable();


    }

    public void updateNotifications(String s) {
        this.notificationsText += s + "\n";
        notification_textField.setText(this.notificationsText);
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Scene getAdminScene() {
        return adminScene;
    }

    public void setAdminScene(Scene adminScene) {
        this.adminScene = adminScene;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


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
        ObservableList<Employee> employee_list = FXCollections.observableArrayList(subject.getDatabase().employeeList());
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
        //saveToXML();

        Stage window = (Stage) adminSignOut_btn.getScene().getWindow();
        window.setTitle("Client");
        window.setScene(subject.getClientController().getClientScene());

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



    @Override
    public void update() {
        System.out.println("updated from CAdmin");
    }
}
