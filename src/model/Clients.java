package model;

import java.util.ArrayList;
import java.util.List;


public class Clients {


    private List<Client> clientList;


    public Clients() {
        this.clientList = new ArrayList<>();
    }

    public Clients(List<Client> clientList) {
        this.clientList = clientList;
    }

    @Override
    public String toString() {
        return "Clients{" +
                "clients=" + clientList +
                '}';
    }

    public List<Client> getClientList() {
        return clientList;
    }

    public void setClientList(List<Client> clientList) {
        this.clientList = clientList;
    }

    public boolean exists(Client client) {
        for (Client c : this.getClientList()) {
            if (c.equals(client))
                return true;
        }
        return false;
    }

    public void addClient(Client client) {
        if (!exists(client)) {
            this.clientList.add(client);
        }
    }

    public void deleteClient(Client client) {
        if (exists(client)) {
            this.clientList.remove(client);
        }
    }

    public void updateClient(Client client1, Client client2) {
        client1.setFirstName(client2.getFirstName());
        client1.setLastName(client2.getLastName());
        client1.setEmail(client2.getEmail());
        client1.setStatus(client2.getStatus());
        client1.setHouseName(client2.getHouseName());
        client1.setCity(client2.getCity());
        client1.setHousingType(client2.getHousingType());
        client1.setPrice(client2.getPrice());

    }

}
