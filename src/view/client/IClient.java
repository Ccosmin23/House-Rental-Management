package view.client;

import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ComboBox;
import model.Client;
import model.Clients;
import model.RentalHouse;
import model.RentalHouses;

public interface IClient {

    //void setTextToTextFields(Client client);

   // Client getTextFromFields();

    void displayTableRentals(RentalHouses rentalHouses);

    //Client selectClient();
    public ComboBox<String> getCity_combo();
    public ComboBox<String> getCountry_combo();
    public ComboBox<String> getSurface_combo();
    public ComboBox<String> getGarden_combo();
    public ComboBox<String> getBedroom_combo();
    public ComboBox<String> getHouseName_combo();
    public ComboBox<String> getFloors_combo();
    public ComboBox<String> getPrice_combo();
    public ComboBox<String> getTypes_combo();

    PieChart getPieChart();

    BarChart<String, Number> getBarChart();

}
