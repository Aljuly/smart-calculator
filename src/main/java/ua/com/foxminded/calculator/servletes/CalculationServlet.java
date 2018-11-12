/*
 * Alexander Zhulinsky
 *
 * Copyright (c) 2018 by MySelf corp
 *
 */
package ua.com.foxminded.calculator.servletes;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
	
	private static final String VALUE = "VALUE";
	private static final String ALERT = "ALERT";
	
	@Override
	public void init() {
		pool = new JedisPool(new JedisPoolConfig(), "192.168.99.100", 6379);
	}
	
	@Override
	public void destroy() {
		pool.destroy();
	}
	
	@SuppressWarnings("deprecation")
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) 
	        throws IOException, CalculationException {
	    // Prepare output
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		// Get caching
		Jedis jedis = null;
		String value = null;
		String alert = null;
		// Get the calculation Service
		CalculationService calculationService = new CalculationService();
		try {
			jedis = pool.getResource();
		     // Read the request data
	        String id = request.getParameter("id");
	        String first = request.getParameter("firstnumber");
	        String second = request.getParameter("secondnumber");
	        // Get the cache key
			String cacheKey = getCacheKey(id, first, second);
			// Search for the cached value
			value = jedis.hget(cacheKey, VALUE);
			alert = jedis.hget(cacheKey, ALERT);
			// If value does not exists, define it
			// Initialize JSON Mapper
		    ObjectMapper mapper = new ObjectMapper();
			if (value == null) {
				CalculationResult result = calculationService.calculate(
						Integer.parseInt(request.getParameter("id")), 
						first, 
						second);
				// Write result to the cache
		        value = mapper.writeValueAsString(result);
		        Map<String, String> data = new HashMap<>();
				data.put(VALUE, value);
				data.put(ALERT, result.getAlert());
				jedis.hmset(cacheKey, data);
				if (result.hasAlert()) {
					throw new CalculationException(result.getAlert());
				}	        
			} else {
			    logger.info("From cache");
			    // Check whereas cached value contain error message
			    if ((alert != null) && (alert.length() > 0)) {
			    		throw new CalculationException(alert);
			    }
			}
		} catch (CalculationException e) {
			logger.error(e.getMessage());
            response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE, e.getMessage());
		} finally {
			pool.returnResource(jedis);
		}
		// Write result to the output
		try {
		    out.print(value);
		} finally {
            out.close();
        }
	}
	
	private String getCacheKey(String id, String first, String second) {
		return id + first + second;
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            processRequest(request, response);
        } catch (IOException | NumberFormatException | CalculationException e) {
        	logger.error(e.getMessage());
        	response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE, "An Error has occurred!");
		}
	}
}
