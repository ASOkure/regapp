package uk.ac.nesc.idsd.security.spring;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CustomLogoutHandler implements LogoutHandler {
    private static final Log log = LogFactory.getLog(CustomLogoutHandler.class);

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        log.info("We are in Custom Logout Handler.");
        String mode = request.getParameter("mode");

        HttpSession session = request.getSession();
        log.info("session found = " + session);
        if (session != null) {
            log.info("set attribute to session: " + mode);
            session.setAttribute("mode", mode);
        }
    }
}
