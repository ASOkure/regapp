package uk.ac.nesc.idsd.security.spring;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import uk.ac.nesc.idsd.model.PatientUser;
import uk.ac.nesc.idsd.model.PatientUserDao;
import uk.ac.nesc.idsd.model.Role;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

//@Service("securityManager")
@Transactional
public class PatientUserDetailsServiceImpl implements UserDetailsService {
    private static final Log log = LogFactory.getLog(PatientUserDetailsServiceImpl.class);

    @Autowired
    private PatientUserDao patientUserDao;

    private Set<Role> roles = new HashSet<Role>(0);
    private Role role = new Role(Role.ROLE_PATIENT);

    @Transactional
    public PatientUser loadUserByUsername(String userName) throws UsernameNotFoundException, DataAccessException {
        PatientUser patientUser = this.patientUserDao.findById(userName);
        if (patientUser == null) {
            throw new UsernameNotFoundException("User " + userName + " does not exist!");
        }
        if (!patientUser.isEnabled() || !patientUser.isAccountNonExpired() || !patientUser.isAccountNonLocked() || !patientUser.isCredentialsNonExpired()) {
            return null;
        }
        long loginCounter = patientUser.getLoginCounter() != null ? patientUser.getLoginCounter() : 0L;
        if (loginCounter == 0L && null == patientUser.getFirstLogin()) {
            patientUser.setFirstLogin(new Timestamp(System.currentTimeMillis()));
        }
        patientUser.setLoginCounter(loginCounter + 1);
        log.info("Patient User " + patientUser.getUsername() + " just logged in for " + patientUser.getLoginCounter() + " times. ");
        patientUser.setLastLogin(new Timestamp(System.currentTimeMillis()));
        patientUser = this.patientUserDao.merge(patientUser);
        //force patient access role to be ROLE_PATIENT only.
        roles.add(role);
        patientUser.setRoles(roles);

        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        patientUser.setAuthorities(authorities);
        log.info("after login, roles = " + patientUser.getRoles());
        return patientUser;
    }

    public PatientUserDao getPatientUserDao() {
        return patientUserDao;
    }

    public void setPatientUserDao(PatientUserDao patientUserDao) {
        this.patientUserDao = patientUserDao;
    }
}