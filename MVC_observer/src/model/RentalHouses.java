package model;

import java.util.ArrayList;
import java.util.List;


public class RentalHouses {


    private List<RentalHouse> rentalHouseList;

    public RentalHouses() {
        this.rentalHouseList = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "RentalHouses{" +
                "rentalHouseList=" + rentalHouseList +
                '}';
    }


    public RentalHouses(List<RentalHouse> rentalHouseList) {
        this.rentalHouseList = rentalHouseList;
    }

    public List<RentalHouse> getRentalHouseList() {
        return rentalHouseList;
    }

    public void setRentalHouseList(List<RentalHouse> rentalHouseList) {
        this.rentalHouseList = rentalHouseList;
    }

    public boolean exists(RentalHouse house) {
        for (RentalHouse h : this.getRentalHouseList()
        ) {
            if (h.equals(house)) return true;
        }
        return false;
    }


    public void addHouse(RentalHouse house) {
        if (!exists(house)) {
            this.getRentalHouseList().add(house);
        }
    }

    public void deleteRental(RentalHouse rentalHouse) {
        if (exists(rentalHouse)) {
            this.getRentalHouseList().remove(rentalHouse);
        }
    }

    public void updateHouse(RentalHouse rentalHouse1, RentalHouse rentalHouse2) {
        rentalHouse1.setCity(rentalHouse2.getCity());
        rentalHouse1.setCountry(rentalHouse2.getCountry());
        rentalHouse1.setPrice(rentalHouse2.getPrice());
        rentalHouse1.setHousing_type(rentalHouse2.getHousing_type());
        rentalHouse1.setBedrooms(rentalHouse2.getBedrooms());
        rentalHouse1.setUsable_surface(rentalHouse2.getUsable_surface());
        rentalHouse1.setGarden_surface(rentalHouse2.getGarden_surface());
        rentalHouse1.setFloors(rentalHouse2.getFloors());
        rentalHouse1.setHouse_name(rentalHouse2.getHouse_name());
        //rentalHouseList.add(rentalHouse1);

    }


}
