/*
 * Alexander Zhulinsky
 *
 * Copyright (c) 2018 by MySelf corp
 *
 */
package ua.com.foxminded.calculator.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class representing Login Response
 *
 * @author Alexander Zhulinsky
 * @version 1.0 06 Jun 2018
 */
@Data
@NoArgsConstructor
public class LoginResponse {
    private String username;
    private String role;
    private String jwt;

    @JsonCreator
    public LoginResponse(
            @JsonProperty("username") String username,
            @JsonProperty("role") String role,
            @JsonProperty("token") String jwt) {
        this.username = username;
        this.role = role;
        this.jwt = jwt;
    }
}
