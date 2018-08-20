/*
 * Alexander Zhulinsky
 *
 * Copyright (c) 2018 by MySelf corp
 *
 */
package ua.com.foxminded.calculator;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import ua.com.foxminded.calculator.dao.DatabaseConnector;

/**
 * Class for testing DsatsabsasaeConnector class
 *
 * @author Alexander Zhulinsky
 * @version 1.0 18 August 2018
 **/
public class DsatsabsasaeConnectorTest {
    
    private Connection connection;
    
    @Test
    public void shouldReturnDatabaseConnection() throws SQLException {
        connection = DatabaseConnector.createConnection();
        assertNotNull(connection);
        connection.close();
    }
    
}
                