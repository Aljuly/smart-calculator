/*
 * Alexander Zhulinsky
 *
 * Copyright (c) 2018 by MySelf corp
 *
 */
package ua.com.foxminded.calculator.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import ua.com.foxminded.calculator.dto.RegisterRequest;

import java.sql.Date;
import java.time.LocalDateTime;

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

    public User(RegisterRequest userDto) {
        this.userName = userDto.getUserName();
        this.login = userDto.getLogin();
        this.email = userDto.getEmail();
        // get current date from the system clock
        this.createdDate = java.sql.Date.valueOf(LocalDateTime.now().toLocalDate());
    }
}
