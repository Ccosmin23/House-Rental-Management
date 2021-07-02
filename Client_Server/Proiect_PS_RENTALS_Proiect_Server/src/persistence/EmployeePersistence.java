package persistence;

import model.Employee;
import model.Person;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmployeePersistence extends Persistence {

    //private String file;
    public EmployeePersistence() {
    }
/*
    public EmployeePersistence(String file){
        this.file = file;
    }*/

    public void addPerson(Person p) {
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

    public void addEmployee(Employee e) {
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
                System.out.println(e.getUsername() + " inserted as " + e.getRole() + " into database\n");
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

            if (!(newEmployee.getUsername().equals(oldEmployee.getUsername()))) {
                statement.setString(1, newEmployee.getUsername());
            }
            else
            {
                statement.setString(1, oldEmployee.getUsername());
            }

            if (!(newEmployee.getPassword().equals(oldEmployee.getPassword()))) {
                statement.setString(2, newEmployee.getPassword());
            }
            else{
                statement.setString(2, oldEmployee.getPassword());
            }

            if (!(newEmployee.getRole().equals(oldEmployee.getRole()))) {
                statement.setString(3, newEmployee.getRole());
            }
            else{
                statement.setString(3, oldEmployee.getRole());
            }

            if (!(newEmployee.getFirstName().equals(oldEmployee.getFirstName()))) {
                statement.setString(4, newEmployee.getFirstName());
            }
            else{
                statement.setString(4, oldEmployee.getFirstName());
            }

            if (!(newEmployee.getLastName().equals(oldEmployee.getLastName()))) {
                statement.setString(5, newEmployee.getLastName());
            }
            else{
                statement.setString(5, oldEmployee.getLastName());
            }

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
            } catch (SQLException throwables1) {
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


                Employee e = new Employee(firstname, lastname, username, password, role);
                employees.add(e);
            }
            st.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return employees;
    }


}
