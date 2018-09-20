package ua.com.foxminded.calculator.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import ua.com.foxminded.calculator.model.Operation;

import javax.naming.NamingException;

public class OperationDao {
    private Connection connection;
    private static final String GET_DESCRIPTION_BY_ID = "SELECT * FROM operations WHERE operation_id=?";
    
    public OperationDao() throws SQLException, NamingException {
        this.connection = DatabaseConnector.createConnection();
    }
    
    public Operation getDescriptionById(int id) {
        Operation operation = new Operation();
        try (PreparedStatement statement = connection.prepareStatement(GET_DESCRIPTION_BY_ID)) {
            statement.setInt(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                if (!rs.next()) {
                    return null;
                } else {
                    while (rs.next()) {
                        operation.setId(rs.getInt(1));
                        operation.setName(rs.getString(2));
                        operation.setDescription(rs.getString(3));
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return operation;
    }
}
