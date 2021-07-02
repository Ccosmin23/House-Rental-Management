package model.report;

import model.RentalHouse;

import java.io.IOException;
import java.util.List;

public class Report {
    private List<RentalHouse> rentals;

    public List<RentalHouse> getRentals() {
        return rentals;
    }

    public void setRentals(List<RentalHouse> rentals) {
        this.rentals = rentals;
    }

    public Report(){

    }

    public void writeReport() throws IOException {
    }


}
