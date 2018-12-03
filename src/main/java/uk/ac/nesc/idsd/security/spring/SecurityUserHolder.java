package uk.ac.nesc.idsd.security.spring;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import uk.ac.nesc.idsd.exception.ErrorCodes;
import uk.ac.nesc.idsd.exception.UserException;
import uk.ac.nesc.idsd.model.PatientUser;
import uk.ac.nesc.idsd.model.PortalUser;
import uk.ac.nesc.idsd.service.exception.ServiceException;

public class SecurityUserHolder {
    private static final Log log = LogFactory.getLog(SecurityUserHolder.class);

    @Autowired
    private UserDetailsService userDetailsService;

    public static User getCurrentUser() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user == null) {
            log.error("User session expired, cannot find user from session.");
            throw new ServiceException(ErrorCodes.USER_SESSION_EXPIRED);
        }
        return user;
    }

    public static PortalUser getCurrentPortalUser() {
        PortalUser user = (PortalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user == null) {
            log.error("User session expired, cannot find user from session.");
            throw new UserException(ErrorCodes.USER_SESSION_EXPIRED);
        }
        return user;
    }

    public static PatientUser getCurrentPatientUser() {
        PatientUser patientUser = null;
        try {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (null != principal) {
                patientUser = (PatientUser) principal;
            }
        } catch (Exception e) {
            log.error("Error looking up patient user from security context", e);
        }

        if (patientUser == null) {
            log.warn("Principal is null from security context, and will return NULL patientUser.");
        }
        return patientUser;
    }

    public UserDetailsService getUserDetailsService() {
        return userDetailsService;
    }

    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

//	public static User getCurrentUserForJSP() {
//		User user = null;
//		if(SecurityContextHolder != null && SecurityContextHolder.getContext() != null && SecurityContextHolder.getContext().getAuthentication() != null) {
//			user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		}
//		return user;
//	}

//	public static User refreshUser() throws UserException {
//		User user = null;
//		String username = "";
//		try {
//			user = getCurrentUser();
//			username = user.getUsername();
//			user = (User) userDetailsService.loadUserByUsername(username);
//	    	
//	    	if(user == null) {
//	    		throw new UsernameNotFoundException("User " + username + " does not exist!");
//	    	}
//	    	Authentication au = new AuthenticationImpl();
//	    	
//	    	//SecurityContextHolder.getContext().getAuthentication()
//		} catch (exception e) {
//			log.error(e.getStackTrace());
//		}
//		return user;
//	}

}