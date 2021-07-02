package model;

import contact.SRentalHouse;

import java.io.Serializable;

public class MClient extends Subject implements Serializable {

    private SRentalHouse sRentalHouse;

    public MClient(SRentalHouse sRentalHouse) {
        this.sRentalHouse = sRentalHouse;
    }

    public SRentalHouse getRentalHouse() {
        return sRentalHouse;

    }

    public void setRentalHouse(SRentalHouse sRentalHouse) {
        this.sRentalHouse = sRentalHouse;
    }
}

