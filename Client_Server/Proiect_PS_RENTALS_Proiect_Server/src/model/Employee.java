package model;

import java.io.Serializable;

//extends Person
public class Employee extends Person implements Serializable {

    private String username;
    private String password;
    private String role;

    public Employee() {
    }

    public Employee(String firstName, String lastName, String username, String password, String role) {
        super(firstName, lastName);
        this.username = username;
        this.password = password;
        this.role = role;

    }


    @Override
    public String toString() {
        return "Employee{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    //String role: admin or employee

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (!(object instanceof Employee)) {
            return false;
        }

        Employee employee = (Employee) object;
        return this.getUsername().equals(employee.getUsername());
    }


}
