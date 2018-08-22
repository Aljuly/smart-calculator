/*
 * Alexander Zhulinsky
 *
 * Copyright (c) 2018 by MySelf corp
 *
 */
package ua.com.foxminded.calculator.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import ua.com.foxminded.calculator.dto.UserDto;

import java.sql.Date;

/**
 * Class for storing information about particular user
 *
 * @author Alexander Zhulinsky
 * @version 1.0 16 Jun 2018
 */
@Data
@NoArgsConstructor
public class User {
    private int id;
    private String userName;
    private String hash;
    private String login;
    private String email;
    private Date createdDate;

    public User(UserDto userDto) {
        this.userName = userDto.getUserName();
        this.login = userDto.getLogin();
        this.email = userDto.getEmail();
        this.createdDate = userDto.getCreatedDate();
    }
}
