package ua.com.foxminded.calculator.dao;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConnector {

    public static Connection createConnection() throws NamingException, SQLException {
        /*
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
        */
        InitialContext context = new InitialContext();
        DataSource dataSource = (DataSource) context.lookup("java:/comp/env/jdbc/postgres");
        Connection connection = dataSource.getConnection();
        return connection;
    }

}
