/*
 * Alexander Zhulinsky
 *
 * Copyright (c) 2018 by MySelf corp
 *
 */
package ua.com.foxminded.calculator.servletes;

import static org.junit.jupiter.api.Assertions.assertNull;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.jni.Pool;

import com.fasterxml.jackson.databind.ObjectMapper;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import ua.com.foxminded.calculator.dto.CalculationResult;
import ua.com.foxminded.calculator.service.CalculationException;
import ua.com.foxminded.calculator.service.CalculationService;

/**
 * Communication with client
 *
 * @author Alexander Zhulinsky
 * @version 1.0 31 Aug 2018
 */
@WebServlet(
		name = "CalculationServlet",
        urlPatterns = "/calculations/calculate"
)
public class CalculationServlet extends HttpServlet {
		
	private static final long serialVersionUID = 2900362493788749479L;
	private static final Logger logger = LogManager.getLogger(CalculationServlet.class.getName());
	
	private static JedisPool pool = null;
	
	@Override
	public void init() {
		pool = new JedisPool(new JedisPoolConfig(), "localhost", 6379);
	}
	
	@Override
	public void destroy() {
		pool.destroy();
	}
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("application/json");
		Jedis jedis = null;
		String result = null;
		// Get the calculation Service
		CalculationService calculationService = new CalculationService();
		try {
			jedis = pool.getResource();
			String cacheKey = getCacheKey(request.getParameter("id"), 
					request.getParameter("firstnumber"), 
					request.getParameter("firstnumber"));
			String result = jedis.get(cacheKey);
			if (result == null) {
				CalculationResult result = calculationService.calculate(
						Integer.parseInt(request.getParameter("id"), 
						request.getParameter("firstnumber"), 
						request.getParameter("firstnumber"));
				// Initialize JSON Mapper
		        ObjectMapper mapper = new ObjectMapper();
				jedis.set(cacheKey, value)
			}
		} catch {
			
		} finally {
			
		}
	}
	
	private String getCacheKey(String id, String first, String second) {
		return id + first + second;
	}
	
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
