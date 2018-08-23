/*
 * Alexander Zhulinsky
 *
 * Copyright (c) 2018 by MySelf corp
 *
 */
package ua.com.foxminded.calculator.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class representing Login Request
 *
 * @author Alexander Zhulinsky
 * @version 1.0 06 Jun 2018
 */
@Data
@NoArgsConstructor
public class LoginRequest {

    private String username;
    private String password;

    public LoginRequest(
            @JsonProperty("username") String username,
            @JsonProperty("password") String password) {
        this.username = username;
        this.password = password;
    }
}
