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
 * Class representing newly registered User
 *
 * @author Alexander Zhulinsky
 * @version 1.0 27 Aug 2018
 */
@Data
@NoArgsConstructor
public class RegisterRequest {
    private int id;
    private String userName;
    private String password;
    private String login;
    private String email;

    @JsonCreator
    public RegisterRequest(@JsonProperty("name") String userName,
                   @JsonProperty("password") String password,
                   @JsonProperty("login") String login,
                   @JsonProperty("email") String email) {
        this.userName = userName;
        this.password = password;
        this.login = login;
        this.email = email;
    }
}
