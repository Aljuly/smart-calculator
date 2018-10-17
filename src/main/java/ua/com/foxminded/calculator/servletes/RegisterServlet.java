package ua.com.foxminded.calculator.servletes;

import ua.com.foxminded.calculator.authorization.JwtManager;
import ua.com.foxminded.calculator.dto.LoginResponse;
import ua.com.foxminded.calculator.dto.RegisterRequest;
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

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebServlet(
		name = "RegisterServlet",
        urlPatterns = "/users/register"
)
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = LogManager.getLogger(RegisterServlet.class.getName());

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Get the UserService
        UserService userService = null;
        try {
            userService = new UserService();
        } catch (SQLException e) {
        	logger.error(e.getMessage());
        } catch (NamingException e) {
        	logger.error(e.getMessage());
        }
        // Get the JWT Manager
        JwtManager jwtManager = new JwtManager();
        // Read the request
        BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
        // Read JSON from request
        StringBuilder json = new StringBuilder();
        int cp;
        while ((cp = reader.read()) != -1) {
          json.append((char) cp);
        }
        // Initialize JSON Mapper
        ObjectMapper mapper = new ObjectMapper();
        RegisterRequest registerRequest = mapper.readValue(json.toString(), RegisterRequest.class);
        try {
            // Let's try to register new user!
        	if (userService == null) {
        		throw new AuthenticationException("Cant establish corresponding service");
        	}
            User user = userService.create(registerRequest);
            // Create token for the authenticated User
            String jwt = jwtManager.createToken(user.getUserName(), "role");
            LoginResponse loginResponse = new LoginResponse(user.getUserName(), "role", jwt);
            // Prepare response
            response.setContentType("application/json");
            // Send token to the client
            mapper.writeValue(response.getOutputStream(), loginResponse);
            logger.info(registerRequest.getUserName() + " Registered successful");
        } catch (AuthenticationException e) {
            logger.error(e.getMessage());
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
        }
    }
}
