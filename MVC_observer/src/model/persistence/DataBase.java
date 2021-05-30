package model.persistence;

import model.Client;
import model.Employee;
import model.Person;
import model.RentalHouse;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBase {
    private String url = "jdbc:mysql://localhost:3306/rentals";
    private String username = "root";
    private String password = "root";
    private Connection connection;


    public DataBase() {
        this.setConnection(tryConnection());
    }

    public Connection getConnection() {
        return this.connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    private Connection tryConnection() {

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, username, password);
            if (connection != null)
                System.out.println("Database connected");

        } catch (SQLException throwables) {
            System.out.println("arrived in catch block");
            throwables.printStackTrace();
        }
        return connection;
    }



    public List<RentalHouse> rentalsList(){
        List<RentalHouse> rentals = new ArrayList<>();
        try{
            String q = "select * from rentalhouse";
            Statement st = this.getConnection().createStatement();
            ResultSet rs = st.executeQuery(q);
            while(rs.next()){
                String city = rs.getString("city");
                String country = rs.getString("country");
                int price = rs.getInt("price");
                String housing_type = rs.getString("housing_type");
                int bedrooms = rs.getInt("bedrooms");
                int usable_surface = rs.getInt("usable_surface");
                int garden_surface = rs.getInt("garden_surface");
                int floors = rs.getInt("floors");
                String house_name = rs.getString("house_name");

           RentalHouse rentalHouse = new RentalHouse(city,country,price,housing_type,bedrooms,usable_surface,garden_surface,floors,house_name);
                rentals.add(rentalHouse);
            }
            st.close();

        }catch(SQLException throwables){
            throwables.printStackTrace();
        }
        return rentals;
    }

    public RentalHouse findByPrice(int no){
        RentalHouse rentalHouse = null;
        try {
            String q = "select * from rentalhouse where price=?";
            PreparedStatement statement = this.getConnection().prepareStatement(q);
            statement.setInt(1, no);
            ResultSet rs = statement.executeQuery();
            rs.next();
            String city = rs.getString("city");
            String country = rs.getString("country");
            int price = rs.getInt("price");
            String housing_type = rs.getString("housing_type");
            int bedrooms = rs.getInt("bedrooms");
            int usable_surface = rs.getInt("usable_surface");
            int garden_surface = rs.getInt("garden_surface");
            int floors = rs.getInt("floors");
            String house_name = rs.getString("house_name");

            rentalHouse = new RentalHouse(city, country, price, housing_type, bedrooms, usable_surface, garden_surface, floors, house_name);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rentalHouse;
    }

    public void addRental(RentalHouse r){
        try {
            String q = "INSERT INTO rentalhouse VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = null;
            statement = this.getConnection().prepareStatement(q);

            statement.setString(1, r.getCity());
            statement.setString(2, r.getCountry());
            statement.setInt(3, r.getPrice());
            statement.setString(4, r.getHousing_type());
            statement.setInt(5, r.getBedrooms());
            statement.setInt(6, r.getUsable_surface());
            statement.setInt(7, r.getGarden_surface());
            statement.setInt(8, r.getFloors());
            statement.setString(9, r.getHouse_name());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println(r.getHouse_name() + " was updated\n");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateRental(RentalHouse oldRentalHouse, RentalHouse newRentalHouse) {
        try {
            String q = "UPDATE rentalhouse SET city=?, country=?, price=?, housing_type=?, bedrooms=?, usable_surface=?, garden_surface=?, floors=?, house_name=?  WHERE house_name=?";
            PreparedStatement statement = this.getConnection().prepareStatement(q);

            statement.setString(1, newRentalHouse.getCity());
            statement.setString(2, newRentalHouse.getCountry());
            statement.setInt(3, newRentalHouse.getPrice());
            statement.setString(4, newRentalHouse.getHousing_type());
            statement.setInt(5, newRentalHouse.getBedrooms());
            statement.setInt(6, newRentalHouse.getUsable_surface());
            statement.setInt(7, newRentalHouse.getGarden_surface());
            statement.setInt(8, newRentalHouse.getFloors());
            statement.setString(9, newRentalHouse.getHouse_name());
            statement.setString(10, oldRentalHouse.getHouse_name());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                //System.out.println(oldRentalHouse.getHouse_name() + " was updated\n");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void deleteRental(RentalHouse e) {
        try {
            String q = "delete from rentalhouse where house_name=?";
            PreparedStatement statement = this.getConnection().prepareStatement(q);
            statement.setString(1, e.getHouse_name());

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println(e.getHouse_name() + " deleted from employee table\n");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }




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

    public void addClient(Client r){
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
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        Person p = new Person(e.getFirstName(), e.getLastName());
        deletePerson(p);
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

    public void addEmployee(Employee e){
        Person p = new Person(e.getFirstName(), e.getLastName());
        addPerson(p);
        try {
            String q = "INSERT INTO employee VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = null;
            statement = this.getConnection().prepareStatement(q);

            statement.setString(1, e.getUsername());
            statement.setString(2, e.getPassword());
            statement.setString(3, e.getRole());
            statement.setString(4, e.getFirstName());
            statement.setString(5, e.getLastName());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println(e.getUsername() + " inserted as " +e.getRole() + " into database\n");
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

    public void deleteEmployee(Employee e) {
        try {
            String q = "delete from employee where username=?";
            PreparedStatement statement = this.getConnection().prepareStatement(q);
            statement.setString(1, e.getUsername());

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println(e.getUsername() + " deleted from employee table\n");
            }
            Person p = new Person(e.getFirstName(), e.getLastName());
            deletePerson(p);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    public void updateEmployee(Employee oldEmployee, Employee newEmployee) {
        try {
            String q = "UPDATE employee SET username=?, password=?, role=?, firstName=?, lastName=?  WHERE username=?";
            PreparedStatement statement = this.getConnection().prepareStatement(q);

            statement.setString(1, newEmployee.getUsername());
            statement.setString(2, newEmployee.getPassword());
            statement.setString(3, newEmployee.getRole());
            statement.setString(4, newEmployee.getFirstName());
            statement.setString(5, newEmployee.getLastName());
            statement.setString(6, oldEmployee.getUsername());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println(oldEmployee.getUsername() + " was updated\n");
            }
        } catch (SQLException throwables) {
            System.out.println("\ndatele introduse nu exista in baza de date");
            Person p = new Person(newEmployee.getFirstName(), newEmployee.getLastName());
            addPerson(p);
            try {
                String q = "UPDATE employee SET username=?, password=?, role=?, firstName=?, lastName=?  WHERE username=?";
                PreparedStatement statement = this.getConnection().prepareStatement(q);

                statement.setString(1, newEmployee.getUsername());
                statement.setString(2, newEmployee.getPassword());
                statement.setString(3, newEmployee.getRole());
                statement.setString(4, newEmployee.getFirstName());
                statement.setString(5, newEmployee.getLastName());
                statement.setString(6, oldEmployee.getUsername());

                int rowsUpdated = statement.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println(oldEmployee.getUsername() + " was updated\n");
                }
        }catch (SQLException throwables1) {
            throwables1.printStackTrace();
            }
        }
    }

    public List<Employee> employeeList() {
        List<Employee> employees = new ArrayList<>();
        try {
            String q = "select * from employee";
            Statement st = this.getConnection().createStatement();
            ResultSet rs = st.executeQuery(q);

            while (rs.next()) {
                String username = rs.getString("username");
                String password = rs.getString("password");
                String role = rs.getString("role");
                String firstname = rs.getString("firstName");
                String lastname = rs.getString("lastName");


                Employee e = new Employee(firstname,lastname,username, password, role);
                employees.add(e);
            }
            st.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return employees;
    }



}
