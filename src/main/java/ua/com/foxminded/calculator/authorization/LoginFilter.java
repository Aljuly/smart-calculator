package ua.com.foxminded.calculator.authorization;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebFilter(
		filterName="LoginFilter",
		urlPatterns="/calculations/*"
)
public class LoginFilter implements Filter {

	private static final Logger logger = LogManager.getLogger(LoginFilter.class.getName());

    private static final String AUTH_HEADER_KEY = "Authorization";
    private static final String AUTH_HEADER_VALUE_PREFIX = "Bearer "; // with trailing space to separate token

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    	logger.info( "JwtAuthenticationFilter initialized" );
    }

    @Override
    public void doFilter(final ServletRequest servletRequest,
                         final ServletResponse servletResponse,
                         final FilterChain filterChain) throws IOException, ServletException {
        
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest; 
        
        try {
            // LookUp for the selected calculation mode. If selected division with period,
            // define is current user authenticated...
            String jwt = getBearerToken(httpRequest);
            if (jwt != null && !jwt.isEmpty()) {
                // Here we calling JwtLoginModule.login() method thru Tomcat internals
                //httpRequest.login(jwt, "");
                logger.info("Logged in using JWT");
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
            	logger.log(Level.WARN, "Only registered users can have access to this operation!! ");
            	filterChain.doFilter(servletRequest, servletResponse);
            	/*
            	((HttpServletResponse) servletResponse).sendError(
            			HttpServletResponse.SC_UNAUTHORIZED, 
            			"Only registered users can have access to this operation ");
            	*/
            }
        } catch (final Exception e) {
        	logger.log(Level.WARN, "Failed logging in with security token", e);
        	filterChain.doFilter(servletRequest, servletResponse);
        	/*
        	((HttpServletResponse) servletResponse).sendError(
        			HttpServletResponse.SC_UNAUTHORIZED, 
        			"Failed logging in with security token ");
        	*/
        }
    }

    @Override
    public void destroy() {
    	logger.info( "JwtAuthenticationFilter destroyed" );
    }

    /**
     * Get the bearer token from the HTTP request.
     * The token is in the HTTP request "Authorization" header in the form of: "Bearer [token]"
     */
    private String getBearerToken(HttpServletRequest request) {
        String authHeader = request.getHeader(AUTH_HEADER_KEY);
        if (authHeader != null && authHeader.startsWith(AUTH_HEADER_VALUE_PREFIX)) {
            return authHeader.substring(AUTH_HEADER_VALUE_PREFIX.length());
        }
        return null;
    }
}
