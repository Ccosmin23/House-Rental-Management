package contact;

import model.RentalHouse;

import java.io.IOException;
import java.util.List;

public class SRentalHouse {

    private ProxyServer proxyServer;

    public SRentalHouse(ProxyServer proxyServer) {
        this.proxyServer = proxyServer;
    }

    public void addRentalHouse(RentalHouse r) {
        try {
            proxyServer.getRealServer().getObjectOutputStream().writeObject("add-rentalHouse");
            proxyServer.getRealServer().getObjectOutputStream().writeObject(r);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("'add-rentalHouse' request sent");
    }

    public List<RentalHouse> readRentalhouse() {
        List<RentalHouse> rentalHouseList = null;
        try {
            proxyServer.getRealServer().getObjectOutputStream().writeObject("read-rentalHouse");

            rentalHouseList = (List<RentalHouse>) proxyServer.getRealServer().getObjectInputStream().readObject();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("'read-rentalHouse' request sent");
        return rentalHouseList;
    }

    public void updateRentalhouse(RentalHouse oldT, RentalHouse newT) {
        try {
            proxyServer.getRealServer().getObjectOutputStream().writeObject("update-rentalHouse");
            proxyServer.getRealServer().getObjectOutputStream().writeObject(oldT);
            proxyServer.getRealServer().getObjectOutputStream().writeObject(newT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("'update-rentalHouse' request sent");
    }

    public void deleteRentalhouse(RentalHouse t) {
        try {
            proxyServer.getRealServer().getObjectOutputStream().writeObject("delete-rentalHouse");
            proxyServer.getRealServer().getObjectOutputStream().writeObject(t);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("'delete-rentalHouse' request sent");
    }

    public RentalHouse findByPrice(int nr) {

        RentalHouse rentalHouse = null;
        try {
            proxyServer.getRealServer().getObjectOutputStream().writeObject("find-rental-by-price");
            proxyServer.getRealServer().getObjectOutputStream().writeObject(nr);
            rentalHouse = (RentalHouse) proxyServer.getRealServer().getObjectInputStream().readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("'find-rental-by-price' request sent");
        return rentalHouse;
    }



}
