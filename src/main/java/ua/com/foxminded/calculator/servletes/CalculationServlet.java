/*
 * Alexander Zhulinsky
 *
 * Copyright (c) 2018 by MySelf corp
 *
 */
package ua.com.foxminded.calculator.servletes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

import ua.com.foxminded.calculator.dto.CalculationRequest;
import ua.com.foxminded.calculator.service.CalculationException;
import ua.com.foxminded.calculator.service.CalculationService;

/**
 * Communication with client
 *
 * @author Alexander Zhulinsky
 * @version 1.0 31 Aug 2018
 */
public class CalculationServlet extends HttpServlet {
		
	private static final long serialVersionUID = 2900362493788749479L;
	private static final Logger logger = LogManager.getLogger(LoginServlet.class.getName());
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// Get the calculation Service
		CalculationService calculationService = new CalculationService();
		// Read the request data
		BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
        // Read JSON from request
		StringBuilder json = new StringBuilder();
        int cp;
        while ((cp = reader.read()) != -1) {
          json.append((char) cp);
        }
        // Initialize JSON Mapper
        ObjectMapper mapper = new ObjectMapper();
        try {
        	// Prepare response
            response.setContentType("application/json");
        	// Return response to client
        	mapper.writeValue(response.getOutputStream(), calculationService.calculate(
        			mapper.readValue(json.toString(), CalculationRequest.class)));
        } catch (CalculationException | IOException e) {
        	logger.error(e.getMessage());
        	response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE, e.getMessage());
		}
        
        
	}
	
}
