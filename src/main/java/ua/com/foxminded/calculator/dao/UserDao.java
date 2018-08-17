package ua.com.foxminded.calculator.dao;

import ua.com.foxminded.calculator.model.User;

import java.util.List;

public interface UserDao {
    void createUser(User product);
    User getUserById(int id);
    List<User> getAllUsers();
    void updateUser(User user);
    void deleteUser(int id);
}
