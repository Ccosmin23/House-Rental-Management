package model;

import java.util.ArrayList;
import java.util.List;


public class Employees {

    private List<Employee> employeeList;


    public Employees() {
        this.employeeList = new ArrayList<>();
    }


    public String toString() {
        return employeeList.toString();
    }


    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public boolean exists(Employee employee) {
        for (Employee e : this.getEmployeeList()) {
            if (e.equals(employee))
                return true;
        }
        return false;
    }

    public void addEmployee(Employee employee) {
        if (!exists(employee)) {
            this.getEmployeeList().add(employee);
        }
    }

    public void deleteEmployee(Employee employee) {
        if (exists(employee)) {
            this.getEmployeeList().remove(employee);
        }
    }

    public void updateEmployee(Employee employee1, Employee employee2) {
        employee1.setFirstName(employee2.getFirstName());
        employee1.setLastName(employee2.getLastName());
        employee1.setUsername(employee2.getUsername());
        employee1.setPassword(employee2.getPassword());
        employee1.setRole(employee2.getRole());
        //employeeList.add(employee1);
    }


}
