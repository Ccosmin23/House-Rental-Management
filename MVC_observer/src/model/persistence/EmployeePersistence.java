/*
package model.persistence;

import model.Clients;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import model.Employees;
import model.RentalHouses;
public class EmployeePersistence {

    private String file;


    public EmployeePersistence(String file){
        this.file = file;
    }


    public Employees load(){
        try {
            FileInputStream fis = new FileInputStream(new File(file));
            XMLDecoder decoder = new XMLDecoder(fis);
            Employees employees = (Employees) decoder.readObject();
            decoder.close();
            fis.close();
            return employees;
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void save(Employees employees){
        try {
            FileOutputStream fos = new FileOutputStream(new File(file));
            XMLEncoder encoder = new XMLEncoder(fos);
            encoder.writeObject(employees);
            encoder.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
*/
