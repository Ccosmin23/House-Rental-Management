package view.admin;

import model.Employee;
import model.Employees;

public interface IAdminView {

    void addEmployee(String firstName, String lastName , String username, String password, String email);

    void save(Employee employee);

    void displayEmployeeTable(Employees employees);

    Employee getTextFromTextFields();

    void setTextToTextFields(Employee employee);

    Employee selectEmployee();

    public Employee getFromRegisterTextFields();

}
