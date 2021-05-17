package model.persistence;

import model.Clients;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ClientPersistence {

    private final String file;

    public ClientPersistence(String file) {
        this.file = file;
    }

    public Clients load() {
        try {
            FileInputStream fis = new FileInputStream(new File(file));
            XMLDecoder decoder = new XMLDecoder(fis);
            Clients clients = (Clients) decoder.readObject();
            decoder.close();
            fis.close();
            return clients;
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void save(Clients clients) {
        try {
            FileOutputStream fos = new FileOutputStream(new File(file));
            XMLEncoder encoder = new XMLEncoder(fos);
            encoder.writeObject(clients);
            encoder.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
