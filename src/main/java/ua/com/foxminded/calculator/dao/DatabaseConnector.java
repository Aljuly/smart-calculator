package ua.com.foxminded.calculator.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DatabaseConnector {

    public static Connection createConnection() {
        String dbUrl = "jdbc:postgresql://localhost:5432/calculator";
        String user = "calcuser";
        String password = "1";
        Properties props = new Properties();
        props.setProperty("user", user);
        props.setProperty("password", password);
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(dbUrl, props);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }

}
