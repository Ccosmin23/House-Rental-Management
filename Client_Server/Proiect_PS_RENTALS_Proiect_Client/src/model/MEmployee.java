package model;

import contact.SClient;
import contact.SRentalHouse;

import java.io.Serializable;

public class MEmployee extends Subject implements Serializable {
    private SRentalHouse sRentalHouse;
    private SClient sClient;

    public MEmployee(SRentalHouse sRentalHouse, SClient sClient) {
        this.sRentalHouse = sRentalHouse;
        this.sClient = sClient;
    }

    public SRentalHouse getRentalHouse() {
        return sRentalHouse;
    }

    public void setRentalHouse(SRentalHouse sRentalHouse) {
        this.sRentalHouse = sRentalHouse;
    }

    public SClient getClient() {
        return sClient;
    }

    public void setClient(SClient sClient) {
        this.sClient = sClient;
    }



    public void updateClient(Client client, Client client2) {
    }
}
