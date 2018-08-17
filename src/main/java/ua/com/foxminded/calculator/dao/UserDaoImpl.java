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

public class UserDaoImpl implements UserDao {

    private final Connection conn = DatabaseConnector.createConnection();
    private static final String CREATE_USER = "INSERT INTO user (name, hash, login, email, date) VALUES (?, ?, ?, ?, ?)";
    private static final String GET_USER_BY_ID = "SELECT * FROM user WHERE user_id=?";
    private static final String GET_USERS = "SELECT * FROM users";
    private static final String UPDATE_USER = "UPDATE users SET name=?, hash=?, login=?, email=?, date=? WHERE user_id=?";
    private static final String DELETE_USER = "DELETE FROM users WHERE user_id=?";

    @Override
    public void createUser(User user) {
        try (PreparedStatement statement = conn.prepareStatement(CREATE_USER, Statement.RETURN_GENERATED_KEYS)) {
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
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public User getUserById(int id) {
        User user = new User();
        try (PreparedStatement statement = conn.prepareStatement(GET_USER_BY_ID)) {
            statement.setInt(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    user.setId(rs.getInt(1));
                    user.setUserName(rs.getString(2));
                    user.setHash(rs.getString(3));
                    user.setLogin(rs.getString(4));
                    user.setEmail(rs.getString(5));
                    user.setCreatedDate(rs.getDate(5));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> allUsers = new ArrayList<>();
        try (PreparedStatement statement = conn.prepareStatement(GET_USERS);
             ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt(1));
                user.setUserName(rs.getString(2));
                user.setHash(rs.getString(3));
                user.setLogin(rs.getString(4));
                user.setEmail(rs.getString(5));
                user.setCreatedDate(rs.getDate(5));
                allUsers.add(user);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return allUsers;
    }

    @Override
    public void updateUser(User user) {
        try (PreparedStatement statement = conn.prepareStatement(UPDATE_USER)) {
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getHash());
            statement.setString(3, user.getLogin());
            statement.setString(4, user.getEmail());
            statement.setDate(5, user.getCreatedDate());
            statement.executeUpdate();
            conn.commit();
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void deleteUser(int id) {
        try (PreparedStatement statement = conn.prepareStatement(DELETE_USER)) {
            statement.setInt(1, id);
            statement.executeUpdate();
            conn.commit();
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
