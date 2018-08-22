package ua.com.foxminded.calculator.dto;

import lombok.Data;

@Data
public class LoginResponse {
    private String username;
    private String role;
    private String jwt;
}
