package ua.com.foxminded.calculator.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class UserDto {
    private int id;
    private String userName;
    private String password;
    private String login;
    private String email;
    private Date createdDate;
}
