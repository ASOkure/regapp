package uk.ac.nesc.idsd.security.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import uk.ac.nesc.idsd.service.AuditService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

public class CustomLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler implements LogoutSuccessHandler {

    @Autowired
    private AuditService auditService;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String queryString = request.getQueryString();

        String decoded = URLDecoder.decode(queryString, "UTF-8");
        String[] pares = decoded.split("&");
        Map<String, String> parametersMap = new HashMap<String, String>();
        for (String pare : pares) {
            String[] nameAndValue = pare.split("=");
            if (nameAndValue.length == 2) {
                parametersMap.put(nameAndValue[0], nameAndValue[1]);
            } else {
                logger.warn("name and value array element number != 2, para= " + pare);
            }
        }

        // Now you can get your parameter:
        String path = parametersMap.get("redirect");
        setDefaultTargetUrl(path);

        try {
            auditService.logUserLogout(authentication.getName(), request.getHeader("Referer"));
        } catch (Exception e) {
            logger.warn("User session is expired, cannot log the logout event to audit table. ", e);
        }

        super.onLogoutSuccess(request, response, authentication);
    }
}