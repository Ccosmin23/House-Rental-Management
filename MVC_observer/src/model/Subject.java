package model;

import controller.CAdmin;
import controller.CClient;
import controller.CEmployee;
import controller.CSignIn;
import model.persistence.DataBase;

import java.util.ArrayList;
import java.util.List;

public class Subject {
    private List<Observer> observers;
    private DataBase database;

    public Subject(){
        this.observers = new ArrayList<>();
        this.database = new DataBase();}

    public Subject(List<Observer> observers){this.observers = observers;}




    public void addObserver(Observer o){this.observers.add(o);}

    public void removeObserver(Observer o){this.observers.remove(o);}

    public List<Observer> getObservers() {
        return observers;
    }

    public void setObservers(List<Observer> observers) {
        this.observers = observers;
    }

    public void notifyObservers(){
        for(Observer o : this.observers){
            o.update();
        }
    }

    public DataBase getDatabase() {
        return database;
    }

    public void setDatabase(DataBase database) {
        this.database = database;
    }



    public CAdmin getAdminController() {
        CAdmin cAdmin = new CAdmin();
        for (Observer o : this.observers
        ) {
            if (o instanceof CAdmin) cAdmin = (CAdmin) o;
        }
        return cAdmin;
    }

    public CEmployee getEmployeeController() {
        CEmployee cEmployee = new CEmployee();
        for (Observer o : this.observers
        ) {
            if (o instanceof CEmployee) cEmployee = (CEmployee) o;
        }
        return cEmployee;
    }

    public CClient getClientController() {
        CClient cClient = new CClient();
        for (Observer o : this.observers
        ) {
            if (o instanceof CClient) cClient = (CClient) o;
        }
        return cClient;
    }

    public CSignIn getSignInController() {
        CSignIn csignIn = new CSignIn();
        for (Observer o : this.observers
        ) {
            if (o instanceof CSignIn) csignIn = (CSignIn) o;
        }
        return csignIn;
    }

    public void notifyAdmin(String s) {
        getAdminController().updateNotifications(s);
    }

    public void notifyEmployee(String s) {
        getEmployeeController().updateNotifications(s);
    }

    public void notifyClient(String s) {
        getClientController().updateNotifications(s);
    }


}
