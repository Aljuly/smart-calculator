package ua.com.foxminded.calculator.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ua.com.foxminded.calculator.model.Operation;

import javax.naming.NamingException;

public class OperationDao {
    private Connection connection;
    private static final Logger logger = LogManager.getLogger(OperationDao.class.getName());
    private static final String GET_DESCRIPTION_BY_ID = "SELECT * FROM calculator.operations WHERE operation_id=?";
    
    public OperationDao() throws SQLException, NamingException {
        this.connection = DatabaseConnector.createConnection();
    }
    
    public Operation getDescriptionById(int id) {
        Operation operation = new Operation();
        try (PreparedStatement statement = connection.prepareStatement(GET_DESCRIPTION_BY_ID, 
        		ResultSet.TYPE_SCROLL_INSENSITIVE, 
        		ResultSet.CONCUR_UPDATABLE)) {
            statement.setInt(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                if (!rs.next()) {
                    return null;
                } else {
                	rs.first();
                    operation.setId(rs.getInt(1));
                    operation.setName(rs.getString(2));
                    operation.setDescription(rs.getString(3));
                }
            }
        } catch (SQLException ex) {
        	logger.error(ex.getMessage());
        }
        return operation;
    }
}
