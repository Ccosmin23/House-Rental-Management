package presenter;

import model.Employee;
import model.Employees;
import model.persistence.EmployeePersistence;
import view.admin.IAdminView;
import view.employee.IEmployee;
import view.signIn.ISignIn;

import java.io.IOException;

public class PAdmin {

    EmployeePersistence employeePersistence = new EmployeePersistence("./employee_saved.xml");
    private Employees employees;
    private IAdminView iAdmin;
    private IEmployee iEmployee;
    private Employee employee;
    private Employee employee2;
    private Employees employees2;
    private ISignIn iSignIn;

    public PAdmin(IAdminView iAdmin) {
        this.iAdmin = iAdmin;
        employees = new Employees();
    }


    public void initializePEmployees() {
        Employee e1 = new Employee("fName_employee1", "lName1_employee1", "user", "pass", "employee");
        Employee e2 = new Employee("fName_employee2", "lName2_employee2", "emp2", "emp2", "employee");
        Employee e3 = new Employee("fName_employee3", "lName3_employee3", "emp3", "emp3", "employee");
        Employee e4 = new Employee("fName_employee4", "lName4_employee4", "admin", "admin", "admin");
        employees.addEmployee(e1);
        employees.addEmployee(e2);
        employees.addEmployee(e3);
        employees.addEmployee(e4);
        employeePersistence.save(employees);
        iAdmin.displayEmployeeTable(this.employees);
    }




    public void refresh(){
        iAdmin.displayEmployeeTable(this.employees);
    }


    public void saveToXML() throws IOException {
        employeePersistence.save(employees);
    }


    public void loadFromXML() {
        this.employees = employeePersistence.load();
    }


    public void createEmployee() throws IOException {
        employee = iAdmin.getTextFromTextFields();
        employees.addEmployee(employee);
        saveToXML();
        loadFromXML();
        iAdmin.displayEmployeeTable(this.employees);

    }

    public void deleteEmployee() {
        employee = iAdmin.selectEmployee();
        employees.deleteEmployee(employee);
        iAdmin.displayEmployeeTable(this.employees);
    }

    public void updateEmployee() throws IOException {
        employee = iAdmin.selectEmployee();
        employee2 = iAdmin.getTextFromTextFields();
        employees.updateEmployee(employee, employee2);
        iAdmin.displayEmployeeTable(employees);
        saveToXML();
        loadFromXML();
    }

    public void readEmployee() {
        employee = iAdmin.selectEmployee();
        iAdmin.setTextToTextFields(employee);
        iAdmin.displayEmployeeTable(this.employees);

    }


}
