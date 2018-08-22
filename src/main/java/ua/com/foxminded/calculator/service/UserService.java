package ua.com.foxminded.calculator.service;

import org.mindrot.jbcrypt.BCrypt;
import ua.com.foxminded.calculator.dao.UserDao;
import ua.com.foxminded.calculator.dto.UserDto;
import ua.com.foxminded.calculator.model.User;

public class UserService {

    private UserDao userDao;

    public UserService() {
        this.userDao = new UserDao();
    }

    public User create(UserDto userDto) throws Exception {
        // Validate user
        if (userDao.getUserByName(userDto.getUserName()) != null) {
            throw new Exception("User " + userDto.getUserName() + "  already registered!");
        }
        User user = new User(userDto);
        // Hash password
        user.setHash(BCrypt.hashpw(userDto.getPassword(), BCrypt.gensalt(12)));
        userDao.createUser(user);
        return user;
    }

    public User authenticate(String name, String password) throws AuthenticationException {
        User user = userDao.getUserByName(name);
        if (user != null && BCrypt.checkpw(password, user.getHash())) {
            return user;
        }
        throw new AuthenticationException("Failed logging in user: " + name + ". Unknown name or wrong password!");
    }

}
