package uk.ac.nesc.idsd.security.spring;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.encoding.LdapShaPasswordEncoder;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.authentication.encoding.PlaintextPasswordEncoder;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.Assert;

/**
 * An {@link AuthenticationProvider} implementation that retrieves user details
 * from an {@link UserDetailsService}.
 */
@SuppressWarnings("deprecation")
public class TestDaoAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {
    private static final Log log = LogFactory.getLog(TestDaoAuthenticationProvider.class);

    private PasswordEncoder passwordEncoder = new LdapShaPasswordEncoder();

    private SaltSource saltSource;

    private UserDetailsService userDetailsService;

    private boolean includeDetailsObject = true;

    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        Object salt = null;

        log.error("in additional");
        if (this.saltSource != null) {
            salt = this.saltSource.getSalt(userDetails);
        }
        log.error("@@ - Salt = " + salt);
        if (authentication.getCredentials() == null) {
            logger.debug("Authentication failed: no credentials provided");

            throw new BadCredentialsException(messages.getMessage(
                    "AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"),
                    includeDetailsObject ? userDetails : null);
        }

        String presentedPassword = authentication.getCredentials().toString();
        log.error("passwordEncoder = " + passwordEncoder.getClass().getSimpleName());
        log.error("encode password to: " + passwordEncoder.encodePassword(authentication.getCredentials().toString(), salt));
        log.error("is password valid? = " + passwordEncoder.isPasswordValid(userDetails.getPassword(), presentedPassword, salt));
        if (!passwordEncoder.isPasswordValid(userDetails.getPassword(), presentedPassword, salt)) {
            logger.debug("Authentication failed: password does not match stored value");

            throw new BadCredentialsException(messages.getMessage(
                    "AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"),
                    includeDetailsObject ? userDetails : null);
        }
    }

    protected void doAfterPropertiesSet() throws Exception {
        Assert.notNull(this.userDetailsService, "A UserDetailsService must be set");
    }

    @SuppressWarnings("unused")
    protected final UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
            throws AuthenticationException {
        UserDetails loadedUser;
        log.error("before loading user");
        try {
            loadedUser = this.getUserDetailsService().loadUserByUsername(username);
            log.debug("is loadedUser null? " + (loadedUser == null));
            log.error("after loading user from db, the password is " + loadedUser.getPassword());
        } catch (UsernameNotFoundException notFound) {
            throw notFound;
        } catch (Exception repositoryProblem) {
            throw new AuthenticationServiceException(repositoryProblem.getMessage(), repositoryProblem);
        }

        if (loadedUser == null) {
            throw new AuthenticationServiceException("UserDetailsService returned null, which is an interface contract violation");
        }
        return loadedUser;
    }

    protected PasswordEncoder getPasswordEncoder() {
        return passwordEncoder;
    }

    /**
     * Sets the PasswordEncoder instance to be used to encode and validate passwords.
     * If not set, {@link PlaintextPasswordEncoder} will be used by default.
     *
     * @param passwordEncoder The passwordEncoder to use
     */
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    protected SaltSource getSaltSource() {
        return saltSource;
    }

    /**
     * The source of salts to use when decoding passwords. <code>null</code>
     * is a valid value, meaning the <code>DaoAuthenticationProvider</code>
     * will present <code>null</code> to the relevant <code>PasswordEncoder</code>.
     *
     * @param saltSource to use when attempting to decode passwords via the <code>PasswordEncoder</code>
     */
    public void setSaltSource(SaltSource saltSource) {
        this.saltSource = saltSource;
    }

    protected UserDetailsService getUserDetailsService() {
        return userDetailsService;
    }

    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    protected boolean isIncludeDetailsObject() {
        return includeDetailsObject;
    }

    /**
     * Determines whether the UserDetails will be included in the <tt>extraInformation</tt> field of a
     * thrown BadCredentialsException. Defaults to true, but can be set to false if the exception will be
     * used with a remoting protocol, for example.
     *
     * @deprecated use {@link org.springframework.security.authentication.ProviderManager#setClearExtraInformation(boolean)}
     */
    public void setIncludeDetailsObject(boolean includeDetailsObject) {
        this.includeDetailsObject = includeDetailsObject;
    }

}