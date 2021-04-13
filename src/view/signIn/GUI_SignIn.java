package view.signIn;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.*;
import presenter.PAdmin;
import presenter.PEmployee;
import view.employee.IEmployee;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GUI_SignIn implements Initializable, IEmployee {

    private PEmployee pEmployee;
    private PAdmin pAdmin;


    @FXML
    public AnchorPane base_signIn_pane, signIn_pane;
    public Button signIn_btn, createAccount_btn, signInGoBack_btn;
    public TextField tf_username, tf_password;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pEmployee = new PEmployee(this);
        base_signIn_pane.setVisible(true);


    }

    @FXML
    public void signInBtn(ActionEvent event) throws Exception {
        pEmployee.signInBtn();
    }

    @FXML
    public void createAccount(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("view/admin/admin_view.fxml"));
        Stage window = (Stage) createAccount_btn.getScene().getWindow();
        window.setTitle("Client");
        window.setScene(new Scene(root, 1000, 600));
    }


    @FXML
    public void goBackFromSignInPane(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("view/client/client_view.fxml"));
        Stage window = (Stage) signInGoBack_btn.getScene().getWindow();
        window.setTitle("Client");
        window.setScene(new Scene(root, 1000, 600));
    }





    public Button getSignInBtn() {
        return signIn_btn;
    }


    @Override
    public Button rentalsUpdatePressed() {
        return null;
    }



    @Override
    public void setRentalsToTextFields(RentalHouse rentalHouse) {

    }

    @Override
    public RentalHouse getRentalFromTextFields() {
        return null;
    }

    @Override
    public void displayTableRentals(RentalHouses rentalHouses) {

    }

    @Override
    public RentalHouse selectRental() {
        return null;
    }

    @Override
    public ComboBox<String> getCity_combo() {
        return null;
    }

    @Override
    public ComboBox<String> getCountry_combo() {
        return null;
    }

    @Override
    public ComboBox<String> getSurface_combo() {
        return null;
    }

    @Override
    public ComboBox<String> getGarden_combo() {
        return null;
    }

    @Override
    public ComboBox<String> getBedroom_combo() {
        return null;
    }

    @Override
    public ComboBox<String> getHouseName_combo() {
        return null;
    }

    @Override
    public ComboBox<String> getFloors_combo() {
        return null;
    }

    @Override
    public ComboBox<String> getPrice_combo() {
        return null;
    }

    @Override
    public ComboBox<String> getTypes_combo() {
        return null;
    }

    @Override
    public void saveReports() throws IOException {

    }

    @Override
    public PieChart getPieChart() {
        return null;
    }

    @Override
    public BarChart<String, Number> getBarChart() {
        return null;
    }

    @Override
    public Client getTextFromFields() {
        return null;
    }

    @Override
    public void displayClientTable(Clients clients) {

    }

    @Override
    public Client selectClient() {
        return null;
    }


    @Override
    public Employee getFromRegisterTextFields() {
        return null;
    }

    @Override
    public void setClientsToTextFields(Client client) {

    }

    public TextField getUsername() {
        return tf_username;

    }

    public TextField getPassword() {
        return tf_password;
    }





}