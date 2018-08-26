package ua.com.foxminded.calculator.service;

import ua.com.foxminded.calculator.dao.OperationDao;
import ua.com.foxminded.calculator.model.Operation;

public class DescriptionService {
    private OperationDao operationDao;
    
    public DescriptionService() {
        this.operationDao = new OperationDao();
    }
    
    public Operation getDescription(int id) {
        return operationDao.getDescriptionById(id);
    }
}
