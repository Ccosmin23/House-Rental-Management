package persistence;

import model.Client;
import model.Person;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClientPersistence extends Persistence{

    public List<Client> clientsList(){
        List<Client> clients = new ArrayList<>();
        try{
            String q = "select * from client";
            Statement st = this.getConnection().createStatement();
            ResultSet rs = st.executeQuery(q);
            while(rs.next()){
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String email = rs.getString("email");
                //String client_status = rs.getString("client_status");
                String houseName = rs.getString("houseName");
                String city = rs.getString("city");
                String housingType = rs.getString("housingType");
                int price = rs.getInt("price");


                Client client = new Client(firstName,lastName,email,houseName,city,housingType,price);
                clients.add(client);
            }
            st.close();

        }catch(SQLException throwables){
            throwables.printStackTrace();
        }
        return clients;
    }


    public void addPerson(Person p){
        try {
            String q = "INSERT INTO person VALUES (?, ?)";
            PreparedStatement statement = null;
            statement = this.getConnection().prepareStatement(q);

            statement.setString(1, p.getFirstName());
            statement.setString(2, p.getLastName());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println(p.getFirstName() + " inserted as person into database\n");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void deletePerson(Person p) {
        try {
            String q = "delete from person where firstname=?";
            PreparedStatement statement = this.getConnection().prepareStatement(q);
            statement.setString(1, p.getFirstName());

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println(p.getFirstName() + " deleted from person table\n");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void addClient(Client r){


        Person p = new Person(r.getFirstName(), r.getLastName());
        addPerson(p);

        try {
            String q = "INSERT INTO client VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = null;
            statement = this.getConnection().prepareStatement(q);

            statement.setString(1, r.getFirstName());
            statement.setString(2, r.getLastName());
            statement.setString(3, r.getEmail());
            statement.setString(4, r.getHouseName());
            statement.setString(5, r.getCity());
            statement.setString(6, r.getHousingType());
            statement.setInt(7, r.getPrice());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println(r.getFirstName() + " was added\n");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateClient(Client oldClient, Client newClient) {
        try {
            String q = "UPDATE client SET firstName=?, lastName=?, email=?, houseName=?, city=?, housingType=?, price=?  WHERE email=?";
            PreparedStatement statement = this.getConnection().prepareStatement(q);

            statement.setString(1, newClient.getFirstName());
            statement.setString(2, newClient.getLastName());
            statement.setString(3, newClient.getEmail());
            statement.setString(4, newClient.getHouseName());
            statement.setString(5, newClient.getCity());
            statement.setString(6, newClient.getHousingType());
            statement.setInt(7, newClient.getPrice());
            statement.setString(8, oldClient.getEmail());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println(oldClient.getEmail() + " was updated\n");
            }
        } catch (SQLException throwables) {
            System.out.println("\ndatele introduse nu exista in baza de date");
            Person p = new Person(newClient.getFirstName(), newClient.getLastName());
            addPerson(p);
            try {
                String q = "UPDATE client SET firstName=?, lastName=?, email=?, houseName=?, city=?, housingType=?, price=?  WHERE email=?";
                PreparedStatement statement = this.getConnection().prepareStatement(q);

                statement.setString(1, newClient.getFirstName());
                statement.setString(2, newClient.getLastName());
                statement.setString(3, newClient.getEmail());
                statement.setString(4, newClient.getHouseName());
                statement.setString(5, newClient.getCity());
                statement.setString(6, newClient.getHousingType());
                statement.setInt(7, newClient.getPrice());
                statement.setString(8, oldClient.getEmail());

                int rowsUpdated = statement.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println(oldClient.getEmail() + " was updated\n");
                }
            }catch (SQLException throwables1) {
                throwables1.printStackTrace();
            }
        }
    }

    public void deleteClient(Client e) {

        try {
            String q = "delete from client where email=?";
            PreparedStatement statement = this.getConnection().prepareStatement(q);
            statement.setString(1, e.getEmail());

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println(e.getFirstName() + " deleted from employee table\n");
            }
            Person p = new Person(e.getFirstName(), e.getLastName());
            deletePerson(p);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        Person p = new Person(e.getFirstName(), e.getLastName());
        deletePerson(p);
    }

}
