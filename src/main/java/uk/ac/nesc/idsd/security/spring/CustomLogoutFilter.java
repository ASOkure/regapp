package uk.ac.nesc.idsd.security.spring;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CustomLogoutFilter extends SecurityContextLogoutHandler {

//    @Autowired

//    private org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler securityContextLogoutHandler;

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
//        securityContextLogoutHandler.logout(request, response, authentication);
        //include the request parameter
        String mode = request.getParameter("mode");

        HttpSession session = request.getSession();
        if (session != null) {
            session.setAttribute("mode", mode);
        }
    }
}