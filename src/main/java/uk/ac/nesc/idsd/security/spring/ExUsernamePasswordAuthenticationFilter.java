package uk.ac.nesc.idsd.security.spring;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ExUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private static final Log log = LogFactory.getLog(ExUsernamePasswordAuthenticationFilter.class);

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        final String mode = request.getParameter("mode");

        log.info("mode found - " + mode);
        request.getSession().setAttribute("mode", mode);

        return super.attemptAuthentication(request, response);
    }
}