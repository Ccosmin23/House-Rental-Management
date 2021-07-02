package model;

import persistence.ClientPersistence;
import persistence.EmployeePersistence;
import persistence.RentalsPersistence;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class Contact {
    private int port;
    private ServerSocket serverSocket;
    private Socket socket;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;


    private EmployeePersistence employeePersistence = new EmployeePersistence();
    private ClientPersistence clientPersistence = new ClientPersistence();
    private RentalsPersistence rentalsPersistence = new RentalsPersistence();

    public Contact(int port) throws IOException {
        this.port = port;
        this.serverSocket = new ServerSocket(port);
    }

    public void start() throws IOException, ClassNotFoundException {
        System.out.println("ServerSocket awaiting connections...");
        this.socket = this.serverSocket.accept();
        System.out.println("Connection from " + socket + "!");

        OutputStream outputStream = this.socket.getOutputStream();
        this.objectOutputStream = new ObjectOutputStream(outputStream);

        InputStream inputStream = this.socket.getInputStream();
        this.objectInputStream = new ObjectInputStream(inputStream);

        communicate();
    }

    private void communicate() throws IOException, ClassNotFoundException {
        String s = "";
        while (!s.equals("stop")) {

            try {
                s = (String) objectInputStream.readObject();
            } catch (Exception e) { }

            switch (s) {
                case "add-employee":
                    System.out.println("'add-employee' request to server");
                    Employee e = (Employee) objectInputStream.readObject();
                    employeePersistence.addEmployee(e);
                    s = "";
                    break;
                case "read-employee":
                    System.out.println("'read-employee' request to server");
                    List<Employee> employeeList = employeePersistence.employeeList();
                    objectOutputStream.writeObject(employeeList);
                    s = "";
                    break;
                case "update-employee":
                    System.out.println("'update-employee' request to server");
                    Employee oldE = (Employee) objectInputStream.readObject();
                    Employee newE = (Employee) objectInputStream.readObject();
                    employeePersistence.updateEmployee(oldE, newE);
                    s = "";
                    break;
                case "delete-employee":
                    System.out.println("'delete-employee' request to server");
                    e = (Employee) objectInputStream.readObject();
                    employeePersistence.deleteEmployee(e);
                    s = "";
                    break;

                case "add-rentalHouse":
                    System.out.println("'add-rentalHouse' request to server");
                    RentalHouse r = (RentalHouse) objectInputStream.readObject();
                    rentalsPersistence.addRental(r);
                    s = "";
                    break;
                case "read-rentalHouse":
                    System.out.println("'read-rentalHouse' request to server");
                    List<RentalHouse> rentalHouses_list = rentalsPersistence.rentalsList();
                    objectOutputStream.writeObject(rentalHouses_list);
                    s = "";
                    break;
                case "update-rentalHouse":
                    System.out.println("'update-rentalHouse' request to server");
                    RentalHouse oldRentalHouse = (RentalHouse) objectInputStream.readObject();
                    RentalHouse newRentalHouse = (RentalHouse) objectInputStream.readObject();
                    rentalsPersistence.updateRental(oldRentalHouse, newRentalHouse);
                    s = "";
                    break;
                case "delete-rentalHouse":
                    System.out.println("'delete-rentalHouse' request to server");
                    r = (RentalHouse) objectInputStream.readObject();
                    rentalsPersistence.deleteRental(r);
                    s = "";
                    break;


                case "add-client":
                    System.out.println("'add-client' request to server");
                    Client client = (Client) objectInputStream.readObject();
                    clientPersistence.addClient(client);
                    s = "";
                    break;
                case "read-client":
                    System.out.println("'read-client' request to server");
                    List<Client> clientList = clientPersistence.clientsList();
                    objectOutputStream.writeObject(clientList);
                    s = "";
                    break;
                case "update-client":
                    System.out.println("'update-Client' request to server");
                    Client oldClient = (Client) objectInputStream.readObject();
                    Client newClient = (Client) objectInputStream.readObject();
                    clientPersistence.updateClient(oldClient, newClient);
                    s = "";
                    break;
                case "delete-client":
                    System.out.println("'delete-Client' request to server");
                    client = (Client) objectInputStream.readObject();
                    clientPersistence.deleteClient(client);
                    s = "";
                    break;
                case "find-rental-by-price":
                    System.out.println("'find-train-by-number' request to server");
                    int nr = (int) objectInputStream.readObject();
                    r = rentalsPersistence.findByPrice(nr);
                    objectOutputStream.writeObject(r);
                    s = "";
                    break;
                /*case "add-train":
                    System.out.println("'add-train' request to server");
                    Train t = (Train) objectInputStream.readObject();
                    trainPersistence.addTrain(t);
                    s = "";
                    break;
                case "read-train":
                    System.out.println("'read-train' request to server");
                    List<Train> trainList = trainPersistence.trainList();
                    objectOutputStream.writeObject(trainList);
                    s = "";
                    break;
                case "update-train":
                    System.out.println("'update-train' request to server");
                    Train oldT = (Train) objectInputStream.readObject();
                    Train newT = (Train) objectInputStream.readObject();
                    trainPersistence.updateTrain(oldT, newT);
                    s = "";
                    break;
                case "delete-train":
                    System.out.println("'delete-train' request to server");
                    t = (Train) objectInputStream.readObject();
                    trainPersistence.deleteTrain(t);
                    s = "";
                    break;
                case "find-train-by-number":
                    System.out.println("'find-train-by-number' request to server");
                    int nr = (int) objectInputStream.readObject();
                    t = trainPersistence.findTrainByNumber(nr);
                    objectOutputStream.writeObject(t);
                    s = "";
                    break;

                case "add-ticket":
                    System.out.println("'add-ticket' request to server");
                    Ticket ti = (Ticket) objectInputStream.readObject();
                    ticketPersistence.addTicket(ti);
                    s = "";
                    break;
                case "read-ticket":
                    System.out.println("'read-ticket' request to server");
                    List<Ticket> ticketList = ticketPersistence.ticketList();
                    objectOutputStream.writeObject(ticketList);
                    s = "";
                    break;

                case "delete-ticket":
                    System.out.println("'delete-ticket' request to server");
                    ti = (Ticket) objectInputStream.readObject();
                    ticketPersistence.deleteTicket(ti);
                    s = "";
                    break;
*/
                case "stop":
                    System.out.println("'stop' request to server");
                    stop();
                    break;

                default:
                    // System.out.println("default");
            }
        }
    }

    private void stop() throws IOException {
        System.out.println("Stopping Client-Server connection ...");
        serverSocket.close();
        socket.close();
        System.out.println("Client-Server connection successfully stopped!");
    }

}
