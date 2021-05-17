package presenter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import model.Client;
import model.Clients;
import model.RentalHouse;
import model.RentalHouses;
import model.persistence.RentalsPersistence;
import view.client.IClient;

import java.util.*;
import java.util.stream.Collectors;


public class PClient {
    private IClient iClient;
    private Client client;
    private Clients clients;
    RentalsPersistence rentalsPersistence = new RentalsPersistence("./rentals_saved.xml");
    private RentalHouse rentalHouse;
    private RentalHouses rentalHouses;

    public PClient(IClient iClient) {
        this.iClient = iClient;
        rentalHouses = new RentalHouses();
    }

    public void loadFromXML(){
        this.rentalHouses = rentalsPersistence.load();
    }


    public void refreshTables(){
        iClient.displayTableRentals(this.rentalHouses);
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

        iClient.displayTableRentals(this.rentalHouses);
        rentalsPersistence.save(this.rentalHouses);

    }

    public void clearCombo() {
        iClient.getCity_combo().valueProperty().set(null);
        iClient.getCountry_combo().valueProperty().set(null);
        iClient.getSurface_combo().valueProperty().set(null);
        iClient.getGarden_combo().valueProperty().set(null);
        iClient.getBedroom_combo().valueProperty().set(null);
        iClient.getHouseName_combo().valueProperty().set(null);
        iClient.getFloors_combo().valueProperty().set(null);
        iClient.getPrice_combo().valueProperty().set(null);
        iClient.getTypes_combo().valueProperty().set(null);
    }


    public void searchBtn() {
        List<RentalHouse> searched = rentalHouses.getRentalHouseList();

        if (iClient.getCity_combo().getValue() != null) {
            searched = searched
                    .stream()
                    .filter(t -> t.getCity().equals(iClient.getCity_combo().getValue()))
                    .collect(Collectors.toList());
        }
        if (iClient.getCountry_combo().getValue() != null) {
            searched = searched
                    .stream()
                    .filter(t -> t.getCountry().equals(iClient.getCountry_combo().getValue()))
                    .collect(Collectors.toList());
        }
        if (iClient.getSurface_combo().getValue() != null) {
            searched = searched
                    .stream()
                    .filter(t -> t.getUsable_surface().equals(iClient.getSurface_combo().getValue()))
                    .collect(Collectors.toList());
        }
        if (iClient.getGarden_combo().getValue() != null) {
            searched = searched
                    .stream()
                    .filter(t -> t.getGarden_surface().equals(iClient.getGarden_combo().getValue()))
                    .collect(Collectors.toList());
        }
        if (iClient.getBedroom_combo().getValue() != null) {
            searched = searched
                    .stream()
                    .filter(t -> t.getBedrooms().equals(iClient.getBedroom_combo().getValue()))
                    .collect(Collectors.toList());
        }
        if (iClient.getHouseName_combo().getValue() != null) {
            searched = searched
                    .stream()
                    .filter(t -> t.getHouse_name().equals(iClient.getHouseName_combo().getValue()))
                    .collect(Collectors.toList());
        }
        if (iClient.getFloors_combo().getValue() != null) {
            searched = searched
                    .stream()
                    .filter(t -> t.getFloors().equals(iClient.getFloors_combo().getValue()))
                    .collect(Collectors.toList());
        }
        if (iClient.getPrice_combo().getValue() != null) {
            searched = searched
                    .stream()
                    .filter(t -> t.getPrice().equals(iClient.getPrice_combo().getValue()))
                    .collect(Collectors.toList());
        }
        if (iClient.getTypes_combo().getValue() != null) {
            searched = searched
                    .stream()
                    .filter(t -> t.getHousing_type().equals(iClient.getTypes_combo().getValue()))
                    .collect(Collectors.toList());
        }

        ArrayList<RentalHouse> aux = new ArrayList<>(searched);
        RentalHouses searchedRentals = new RentalHouses(aux);
        iClient.displayTableRentals(searchedRentals);
        clearCombo();

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

        iClient.getBarChart().getData().add(series);
        iClient.getPieChart().setData(list);

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













}
