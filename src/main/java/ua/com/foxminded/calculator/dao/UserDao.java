package ua.com.foxminded.calculator.dao;

import ua.com.foxminded.calculator.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDao {

    private Connection connection;
    private static final String CREATE_USER = "INSERT INTO user (name, hash, login, email, date) VALUES (?, ?, ?, ?, ?)";
    private static final String GET_USER_BY_ID = "SELECT * FROM user WHERE user_id=?";
    private static final String GET_USER_BY_NAME = "SELECT * FROM user WHERE name=?";
    private static final String GET_USERS = "SELECT * FROM users";
    private static final String UPDATE_USER = "UPDATE users SET name=?, hash=?, login=?, email=?, date=? WHERE user_id=?";
    private static final String DELETE_USER = "DELETE FROM users WHERE user_id=?";

    public UserDao() {
        this.connection = DatabaseConnector.createConnection();
    }

    public void createUser(User user) {
        try (PreparedStatement statement = connection.prepareStatement(CREATE_USER, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getHash());
            statement.setString(3, user.getLogin());
            statement.setString(4, user.getEmail());
            statement.setDate(5, user.getCreatedDate());
            statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    user.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public User getUserById(int id) {
        User user = new User();
        try (PreparedStatement statement = connection.prepareStatement(GET_USER_BY_ID)) {
            statement.setInt(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                if (!rs.next()) {
                    return null;
                } else {
                    while (rs.next()) {
                        user.setId(rs.getInt(1));
                        user.setUserName(rs.getString(2));
                        user.setHash(rs.getString(3));
                        user.setLogin(rs.getString(4));
                        user.setEmail(rs.getString(5));
                        user.setCreatedDate(rs.getDate(6));
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

    public User getUserByName(String name) {
        User user = new User();
        try (PreparedStatement statement = connection.prepareStatement(GET_USER_BY_NAME)) {
            statement.setString(1, name);
            try (ResultSet rs = statement.executeQuery()) {
                if (!rs.next()) {
                    return null;
                } else {
                    while (rs.next()) {
                        user.setId(rs.getInt(1));
                        user.setUserName(rs.getString(2));
                        user.setHash(rs.getString(3));
                        user.setLogin(rs.getString(4));
                        user.setEmail(rs.getString(5));
                        user.setCreatedDate(rs.getDate(6));
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

    public List<User> getAllUsers() {
        List<User> allUsers = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(GET_USERS);
             ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt(1));
                user.setUserName(rs.getString(2));
                user.setHash(rs.getString(3));
                user.setLogin(rs.getString(4));
                user.setEmail(rs.getString(5));
                user.setCreatedDate(rs.getDate(6));
                allUsers.add(user);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return allUsers;
    }

    public void updateUser(User user) {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_USER)) {
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getHash());
            statement.setString(3, user.getLogin());
            statement.setString(4, user.getEmail());
            statement.setDate(5, user.getCreatedDate());
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteUser(int id) {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_USER)) {
            statement.setInt(1, id);
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
