package view.employee;

import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.*;

import java.io.IOException;

public interface IEmployee {



    Employee getFromRegisterTextFields();

    void setClientsToTextFields(Client client);

    //Employee getEmployeeFromTextFields();

    public TextField getUsername();

    public TextField getPassword();

    Button getSignInBtn();

  //  Employee getFromRegisterTextFields();

    Button rentalsUpdatePressed();




    void setRentalsToTextFields(RentalHouse rentalHouse);

    RentalHouse getRentalFromTextFields();

    void displayTableRentals(RentalHouses rentalHouses);

    RentalHouse selectRental();

    public ComboBox<String> getCity_combo();
    public ComboBox<String> getCountry_combo();
    public ComboBox<String> getSurface_combo();
    public ComboBox<String> getGarden_combo();
    public ComboBox<String> getBedroom_combo();
    public ComboBox<String> getHouseName_combo();
    public ComboBox<String> getFloors_combo();
    public ComboBox<String> getPrice_combo();
    public ComboBox<String> getTypes_combo();

    void saveReports() throws IOException;

    PieChart getPieChart();

    BarChart<String, Number> getBarChart();



    Client getTextFromFields();

    void displayClientTable(Clients clients);

    Client selectClient();


}
