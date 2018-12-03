package uk.ac.nesc.idsd.security.spring;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uk.ac.nesc.idsd.model.PortalUser;
import uk.ac.nesc.idsd.model.PortalUserDao;

import java.sql.Timestamp;

//@Service("securityManager")
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private static final Log log = LogFactory.getLog(UserDetailsServiceImpl.class);

    @Autowired
    private PortalUserDao portalUserDao;

    @Transactional
    public User loadUserByUsername(String userName) throws UsernameNotFoundException, DataAccessException {
        PortalUser portalUser = this.portalUserDao.findUserWithActiveRolesById(userName);
        if (!portalUser.isEnabled() || !portalUser.isAccountNonExpired() || !portalUser.isAccountNonLocked() || !portalUser.isCredentialsNonExpired()) {
            return null;
        }
        portalUser.setLastLogin(new Timestamp(System.currentTimeMillis()));
        long loginCounter = portalUser.getLoginCounter() != null ? portalUser.getLoginCounter() : 0L;
        portalUser.setLoginCounter(loginCounter + 1);
        log.info("User " + portalUser.getUsername() + " is trying to log in for the " + portalUser.getLoginCounter() + " time, with Roles: " + portalUser.getRoles());
        portalUser = this.portalUserDao.merge(portalUser);
        return portalUser;
    }

    public void setPortalUserDao(PortalUserDao portalUserDao) {
        this.portalUserDao = portalUserDao;
    }
}