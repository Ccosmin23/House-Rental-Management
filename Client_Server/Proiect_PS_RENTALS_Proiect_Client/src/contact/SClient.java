package contact;

import model.Client;

import java.io.IOException;
import java.util.List;

public class SClient {

    private final ProxyServer proxyServer;

    public SClient(ProxyServer proxyServer) {
        this.proxyServer = proxyServer;
    }


    public void addClient(Client e) {
        try {
            proxyServer.getRealServer().getObjectOutputStream().writeObject("add-client");
            proxyServer.getRealServer().getObjectOutputStream().writeObject(e);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        System.out.println("'add-client' request sent");
    }

    public List<Client> readClient() {
        List<Client> clientList = null;
        try {
            proxyServer.getRealServer().getObjectOutputStream().writeObject("read-client");
            clientList = (List<Client>) proxyServer.getRealServer().getObjectInputStream().readObject();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("'read-client' request sent");
        return clientList;
    }

    public void updateClient(Client oldE, Client newE) {
        try {
            proxyServer.getRealServer().getObjectOutputStream().writeObject("update-client");
            proxyServer.getRealServer().getObjectOutputStream().writeObject(oldE);
            proxyServer.getRealServer().getObjectOutputStream().writeObject(newE);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("'update-client' request sent");
    }

    public void deleteClient(Client e) {
        try {
            proxyServer.getRealServer().getObjectOutputStream().writeObject("delete-client");
            proxyServer.getRealServer().getObjectOutputStream().writeObject(e);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        System.out.println("'delete-client' request sent");
    }

}
