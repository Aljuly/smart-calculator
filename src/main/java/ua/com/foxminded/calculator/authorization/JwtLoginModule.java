package ua.com.foxminded.calculator.authorization;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;

import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.CredentialExpiredException;
import javax.security.auth.login.FailedLoginException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;
import java.io.IOException;
import java.security.Principal;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JwtLoginModule implements LoginModule {

    private static final Logger logger = Logger.getLogger(JwtLoginModule.class.getName());

    // Subject reference holds the principals
    private Subject subject;

    // Store the handler
    private CallbackHandler callbackHandler;
    private Principal identity;
    private Principal group;

    private boolean isAuthenticated = false;
    private boolean commitSucceeded = false;

    /**
     * Constructor
     */
    public JwtLoginModule() {
        super();
    }

    /**
     * Initializer
     *
     * @param subject
     * @param callbackHandler
     * @param sharedState
     * @param options
     */
    @Override
    public void initialize(final Subject subject,
                           final CallbackHandler callbackHandler,
                           final Map<String, ?> sharedState,
                           final Map<String, ?> options) {

        // Subject reference holds the principals
        this.subject = subject;

        // Store the handler
        this.callbackHandler = callbackHandler;
    }


    /**
     * Login method
     * @return @throws LoginException
     */
    @Override
    public boolean login() throws LoginException {
        String jwt = getJwt();
        if (jwt != null) {
            try {
                logger.info("JWT provided!");
                JwtManager jwtmanager = new JwtManager();

                // verify the received token
                Jws<Claims> jws = jwtmanager.parseToken(jwt);

                // because token parsing is successful, token is valid
                String user = jws.getBody().getSubject();
                identity = new UserPrincipal(user);

                String role = (String) jws.getBody().get("role");
                group = new RolePrincipal(role);

                logger.info("JWT is valid, logging in user " + user + " with role " + role);

                isAuthenticated = true;
                return true;
            } catch ( SignatureException | MalformedJwtException
                    | UnsupportedJwtException | IllegalArgumentException e ) {
                throw new FailedLoginException( "Invalid security token provided" );
            } catch ( ExpiredJwtException e ) {
                throw new CredentialExpiredException( "The security token is expired" );
            }
        }
        return false;
    }

    /**
     * Adds the username / roles to the principal
     *
     * @return @throws LoginException
     */
    @Override
    public boolean commit() throws LoginException {
        if (!isAuthenticated) {
            return false;
        } else {
            try {
                subject.getPrincipals().add(identity);
                subject.getPrincipals().add(group);
            } catch (Exception e) {
                throw new LoginException(e.getMessage());
            }
            commitSucceeded = true;
            return true;
        }
    }

    /**
     * Terminates the logged in session on error
     *
     * @return @throws LoginException
     */
    @Override
    public boolean abort() throws LoginException {
        if (!isAuthenticated) {
            return false;
        } else if (!commitSucceeded) {
            isAuthenticated = false;
            identity = null;
            group = null;
        } else {
            logout();
        }
        return true;
    }

    /**
     * Logs the user out
     *
     * @return @throws LoginException
     */
    @Override
    public boolean logout() {
        isAuthenticated = commitSucceeded;
        subject.getPrincipals().clear();
        identity = null;
        group = null;
        return true;
    }

    private String getJwt() throws LoginException {
        NameCallback callback = new NameCallback( "prompt" );
        try {
            callbackHandler.handle(new Callback[]{callback} );
            return callback.getName();
        } catch (IOException | UnsupportedCallbackException e) {
            String msg = "Failed getting the security token";
            logger.log(Level.SEVERE, msg, e);
            throw new LoginException(msg);
        }
    }
}
