/*
package model.persistence;

import model.Clients;
import model.Employees;
import model.RentalHouse;
import model.RentalHouses;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class RentalsPersistence {
    private String file;

    public RentalsPersistence(String file){
        this.file = file;
    }


    public RentalHouses load(){
        try {
            FileInputStream fis = new FileInputStream(new File(file));
            XMLDecoder decoder = new XMLDecoder(fis);
            RentalHouses rentals = (RentalHouses) decoder.readObject();
            decoder.close();
            fis.close();
            return rentals;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void save(RentalHouses rentals){
        try {
            FileOutputStream fos = new FileOutputStream(new File(file));
            XMLEncoder encoder = new XMLEncoder(fos);
            encoder.writeObject(rentals);
            encoder.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
*/
