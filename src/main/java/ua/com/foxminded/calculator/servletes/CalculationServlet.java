/*
 * Alexander Zhulinsky
 *
 * Copyright (c) 2018 by MySelf corp
 *
 */
package ua.com.foxminded.calculator.servletes;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

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
	private static final Logger logger = LogManager.getLogger(CalculationServlet.class.getName());
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// Get the calculation Service
		CalculationService calculationService = new CalculationService();
		// Read the request data
		int id = Integer.parseInt(request.getParameter("id"));
		String first = request.getParameter("firstnumber");
		String second = request.getParameter("secondnumber");
        // Initialize JSON Mapper
        ObjectMapper mapper = new ObjectMapper();
        try {
        	// Prepare response
            response.setContentType("application/json");
        	// Return the response to client
        	mapper.writeValue(response.getOutputStream(), calculationService.calculate(id, first, second));
        } catch (CalculationException | IOException e) {
        	logger.error(e.getMessage());
        	response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE, e.getMessage());
		}
        
        
	}
	
}
