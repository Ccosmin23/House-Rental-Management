import model.Contact;

import java.io.IOException;

public class ServerMain {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Contact connection = new Contact(5555);
        connection.start();
    }


}
