package contact;

import model.Employee;

import java.io.IOException;
import java.util.List;

public class SEmployee {

    private final ProxyServer proxyServer;

    public SEmployee(ProxyServer proxyServer) {
        this.proxyServer = proxyServer;
    }


    public void addEmployee(Employee e) {
        try {
            proxyServer.getRealServer().getObjectOutputStream().writeObject("add-employee");
            proxyServer.getRealServer().getObjectOutputStream().writeObject(e);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        System.out.println("'add-employee' request sent");
    }

    public List<Employee> readEmployee() {
        List<Employee> employeeList = null;
        try {
            proxyServer.getRealServer().getObjectOutputStream().writeObject("read-employee");
            employeeList = (List<Employee>) proxyServer.getRealServer().getObjectInputStream().readObject();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("'read-employee' request sent");
        return employeeList;
    }

    public void updateEmployee(Employee oldE, Employee newE) {
        try {
            proxyServer.getRealServer().getObjectOutputStream().writeObject("update-employee");
            proxyServer.getRealServer().getObjectOutputStream().writeObject(oldE);
            proxyServer.getRealServer().getObjectOutputStream().writeObject(newE);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("'update-employee' request sent");
    }

    public void deleteEmployee(Employee e) {
        try {
            proxyServer.getRealServer().getObjectOutputStream().writeObject("delete-employee");
            proxyServer.getRealServer().getObjectOutputStream().writeObject(e);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        System.out.println("'delete-employee' request sent");
    }
}
