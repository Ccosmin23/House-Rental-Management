package persistence;

import java.io.Serializable;
import java.sql.*;

public class Persistence implements Serializable {

    private static Connection connection = tryConnection();

    public Persistence() { }

    public Connection getConnection() {
        return connection;
    }

    private static Connection tryConnection() {
        Connection connection = null;

        String url = "jdbc:mysql://localhost:3306/rentals";
        String username = "root";
        String password = "root";

        try {
            connection = DriverManager.getConnection(url, username, password);
            if (connection != null)
                System.out.println("Successfully connected to MySQL database!");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }
}
