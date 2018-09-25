/*
 * Alexander Zhulinsky
 *
 * Copyright (c) 2018 by MySelf corp
 *
 */
package ua.com.foxminded.calculator.servletes;

import com.fasterxml.jackson.databind.ObjectMapper;
import ua.com.foxminded.calculator.dto.DescriptionResponse;
import ua.com.foxminded.calculator.model.Operation;
import ua.com.foxminded.calculator.service.DescriptionService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Servlet for getting additional info about selected calculation mode
 *
 * @author Alexander Zhulinsky
 * @version 1.0 27 Aug 2018
 */
public class DescriptionServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(LoginServlet.class.getName());

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Get DescriptionService
        DescriptionService descriptionService = new DescriptionService();
        try {
            // Get request parameter
            int id = Integer.parseInt(request.getParameter("id"));
            // Find corresponding description
            Operation operation = descriptionService.getDescription(id);
            DescriptionResponse descriptionResponse = new DescriptionResponse(
                    operation.getId(),
                    operation.getName(),
                    operation.getDescription());
            // Prepare response
            response.setContentType("application/json");
            // Initialize JSON Mapper
            ObjectMapper mapper = new ObjectMapper();
            // Send token to the client
            mapper.writeValue(response.getOutputStream(), descriptionResponse);
        } catch(Exception e) {
            logger.error(e.getMessage());
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        }
    }

}
