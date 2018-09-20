package ua.com.foxminded.calculator.service;

import ua.com.foxminded.calculator.dao.OperationDao;
import ua.com.foxminded.calculator.model.Operation;

import javax.naming.NamingException;
import java.sql.SQLException;

public class DescriptionService {
    private OperationDao operationDao;
    
    public DescriptionService() {
        try {
            this.operationDao = new OperationDao();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
    
    public Operation getDescription(int id) {
        return operationDao.getDescriptionById(id);
    }
}
