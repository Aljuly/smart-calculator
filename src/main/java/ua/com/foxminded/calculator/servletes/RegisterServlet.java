package ua.com.foxminded.calculator.servletes;

import com.fasterxml.jackson.databind.ObjectMapper;
import ua.com.foxminded.calculator.authorization.JwtManager;
import ua.com.foxminded.calculator.dto.LoginResponse;
import ua.com.foxminded.calculator.dto.RegisterRequest;
import ua.com.foxminded.calculator.model.User;
import ua.com.foxminded.calculator.service.AuthenticationException;
import ua.com.foxminded.calculator.service.UserService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Logger;

@WebServlet(urlPatterns = "/users/register")
public class RegisterServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(LoginServlet.class.getName());

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Get the UserService
        UserService userService = new UserService();
        // Get the JWT Manager
        JwtManager jwtManager = new JwtManager();
        // Read the request
        BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String json = reader.readLine();
        // Initialize JSON Mapper
        ObjectMapper mapper = new ObjectMapper();
        RegisterRequest registerRequest = mapper.readValue(json, RegisterRequest.class);
        try {
            // Let's try to register new user!
            User user = userService.create(registerRequest);
            // Create token for the authenticated User
            String jwt = jwtManager.createToken(user.getUserName(), "role");
            LoginResponse loginResponse = new LoginResponse(user.getUserName(), "role", jwt);
            // Prepare response
            response.setContentType("application/json");
            // Send token to the client
            mapper.writeValue(response.getOutputStream(), loginResponse);
        } catch (AuthenticationException e) {
            logger.warning(e.getMessage());
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
        }
    }
}
