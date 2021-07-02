package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CSignIn implements Initializable, Observer {

    private  String notificationsText = "";
    private  CClient cClient;
    @FXML
    public AnchorPane base_signIn_pane, signIn_pane;
    public Button signIn_btn, createAccount_btn, signInGoBack_btn;
    public TextField tf_username, tf_password;
    private CEmployee cEmployee;
    private CAdmin cAdmin;
    private Employee employee;
    private Client client;
    private RentalHouse rentalHouse;
    private MSignIn model;
    private Scene signInScene;
    private List<RentalHouse> rentals = new ArrayList<>();
    public Button ro_button,en_button,de_button,es_button;
    public Label signInLabel;

    private String[] goBackButtonText = {"Mergi inapoi","Go back", "Geh zurück","Regresa"};
    private String[] signInTextLabel = {"Autentificare","Sign in","Anmelden", "Iniciar sesión"};
    private String[] signInButtonText = {"Autentificare","Sign in","Anmelden", "Iniciar sesión"};
    private String[] usernameText = {"Nume utilizator","Username","Nutzername","Nombre de usuario"};
    private String[] passwordText = {"Parola","Password","Passwort","Contraseña"};

    public CSignIn() {
/*        clients = new Clients();
        rentalHouses = new RentalHouses();
        employees = new Employees();
        cClient = new CClient();*/
    }

    public void init() {
        //rentals = subject.getDatabase().rentalsList();
        base_signIn_pane.setVisible(true);

    }

    @FXML
    void romanianLanguage() {
        signInGoBack_btn.setText(goBackButtonText[0]);
        signInLabel.setText(signInTextLabel[0]);
        signIn_btn.setText(signInButtonText[0]);
        tf_username.setPromptText(usernameText[0]);
        tf_password.setPromptText(passwordText[0]);
    }

    @FXML
    void englishLanguage() {
        signInGoBack_btn.setText(goBackButtonText[1]);
        signInLabel.setText(signInTextLabel[1]);
        signIn_btn.setText(signInButtonText[1]);
        tf_username.setPromptText(usernameText[1]);
        tf_password.setPromptText(passwordText[1]);

    }

    @FXML
    void germanLanguage() {
        signInGoBack_btn.setText(goBackButtonText[2]);
        signInLabel.setText(signInTextLabel[2]);
        signIn_btn.setText(signInButtonText[2]);
        tf_username.setPromptText(usernameText[2]);
        tf_password.setPromptText(passwordText[2]);

    }

    @FXML
    void spanishLanguage() {
        signInGoBack_btn.setText(goBackButtonText[3]);
        signInLabel.setText(signInTextLabel[3]);
        signIn_btn.setText(signInButtonText[3]);
        tf_username.setPromptText(usernameText[3]);
        tf_password.setPromptText(passwordText[3]);

    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public MSignIn getModel() {
        return model;
    }

    public void setModel(MSignIn model) {
        this.model = model;
    }

    public Scene getSignInScene() {
        return signInScene;
    }

    public void setSignInScene(Scene signInScene) {
        this.signInScene = signInScene;
    }


    @FXML
    public void signInBtn(ActionEvent event) throws Exception {
        //initializePEmployees();
        String username = getUsername().getText();
        String password = getPassword().getText();

        employee = searchEmployee(username, password);
        System.out.println("signed as : " + employee.getUsername());


        if (employee.getRole().equals("admin")) {
            Stage window = (Stage) signIn_btn.getScene().getWindow();
            window.setTitle("Hello " + employee.getUsername());
            window.setScene(model.getAdminController().getAdminScene());
        } else {
            Stage window = (Stage) signIn_btn.getScene().getWindow();
            window.setTitle("Hello " + employee.getUsername());
            window.setScene(model.getEmployeeController().getEmployeeScene());
            //subject.getEmployeeController().setCurrentEmployee(employee);
        }

    }

    public Employee searchEmployee(String username, String password) {
        for (Employee e : model.getsEmployee().readEmployee()) {
            if (e.getUsername().equals(username) && e.getPassword().equals(password))
                return e;
        }
        return null;
    }


    public void initializePEmployees() {
/*        Employee e1 = new Employee("fName_employee1", "lName1_employee1", "user", "pass", "employee");
        Employee e2 = new Employee("fName_employee2", "lName2_employee2", "emp2", "emp2", "employee");
        Employee e3 = new Employee("fName_employee3", "lName3_employee3", "emp3", "emp3", "employee");
        Employee e4 = new Employee("fName_employee4", "lName4_employee4", "admin", "admin", "admin");
        employees.addEmployee(e1);
        employees.addEmployee(e2);
        employees.addEmployee(e3);
        employees.addEmployee(e4);*/
        //iEmployee.displayEmployeeTable(this.employees);
    }

    @FXML
    public void createAccount(ActionEvent event) throws Exception {

    }


    @FXML
    public void goBackFromSignInPane(ActionEvent event) throws Exception {
        Stage window = (Stage) signInGoBack_btn.getScene().getWindow();
        window.setTitle("Client");
        window.setScene(model.getClientController().getClientScene());
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


    @Override
    public void update() {

    }
}