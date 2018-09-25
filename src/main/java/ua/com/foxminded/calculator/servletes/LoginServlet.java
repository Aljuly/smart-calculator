/*
 * Alexander Zhulinsky
 *
 * Copyright (c) 2018 by MySelf corp
 *
 */
package ua.com.foxminded.calculator.servletes;

import com.fasterxml.jackson.databind.ObjectMapper;
import ua.com.foxminded.calculator.authorization.JwtManager;
import ua.com.foxminded.calculator.dto.LoginRequest;
import ua.com.foxminded.calculator.dto.LoginResponse;
import ua.com.foxminded.calculator.model.User;
import ua.com.foxminded.calculator.service.AuthenticationException;
import ua.com.foxminded.calculator.service.UserService;

import javax.naming.NamingException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Servlet for providing user login
 *
 * @author Alexander Zhulinsky
 * @version 1.0 27 Aug 2018
 */
//@WebServlet(urlPatterns = "/users/authenticate")
public class LoginServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(LoginServlet.class.getName());

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Get the UserService
        UserService userService = null;
        try {
            userService = new UserService();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }
        // Get the JWT Manager
        JwtManager jwtManager = new JwtManager();
        // Read the request
        BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String json = reader.readLine();
        // Initialize JSON Mapper
        ObjectMapper mapper = new ObjectMapper();
        LoginRequest loginRequest = mapper.readValue(json, LoginRequest.class);
        try {
            // Let's try to authenticate this user!
            User user = userService.authenticate(loginRequest.getUsername(), loginRequest.getPassword());
            // Create token for the authenticated User
            String jwt = jwtManager.createToken(user.getUserName(), "role");
            LoginResponse loginResponse = new LoginResponse(user.getUserName(), "role", jwt);
            // Prepare response
            response.setContentType("application/json");
            // Send token to the client
            mapper.writeValue(response.getOutputStream(), loginResponse);
            logger.info(loginRequest.getUsername() + " is logged in " );
        } catch (AuthenticationException e) {
            logger.error(e.getMessage());
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
        }
    }
}
