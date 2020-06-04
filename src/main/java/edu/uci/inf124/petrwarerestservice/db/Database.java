package edu.uci.inf124.petrwarerestservice.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    public static Connection dbConnect() {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            String dbURL = "jdbc:mariadb://" + Credentials.HOSTNAME + ":" + Credentials.PORT_NUMBER + "/" + Credentials.DATABASE;
            return DriverManager.getConnection(dbURL, Credentials.DB_USERNAME, Credentials.DB_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
