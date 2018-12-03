package uk.ac.nesc.idsd.model;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Transactional
public class PatientUserDao extends AbstractDao<PatientUser> {
    private static final Log log = LogFactory.getLog(PatientUserDao.class);
    public static final String PASSWORD = "password";
    public static final String INTRODUCED_BY = "introducedBy";
    public static final String LOGIN_COUNT = "loginCount";
    public static final String DSD_IDENTIFIER_ID = "dsdIdentifierId";
    public static final String ENABLED = "enabled";
    public static final String ACCOUNT_NON_EXPIRED = "accountNonExpired";
    public static final String ACCOUNT_NON_LOCKED = "accountNonLocked";
    public static final String CREDENTIALS_NON_EXPIRED = "credentialsNonExpired";
    public static final String IP = "ip";

    public static PatientUserDao getFromApplicationContext(ApplicationContext ctx) {
        return (PatientUserDao) ctx.getBean("PatientUserDAO");
    }

    @SuppressWarnings("unchecked")
    public PatientUser findByUsernameAndDsdIdentifierId(String username, Long dsdIdentifierId) {
        log.debug("finding PatientUser instance with username: " + username + ", dsdIdentifierId: " + dsdIdentifierId);
        try {
            String queryString = "from PatientUser as model where model.username = ? and model.dsdIdentifierId = ?";
            Query queryObject = getCurrentSession().createQuery(queryString);
         
            
            queryObject.setParameter(0, username);
            queryObject.setParameter(1, dsdIdentifierId);
            List<PatientUser> patientUsers = queryObject.list();
            if (!CollectionUtils.isEmpty(patientUsers)) {
                return patientUsers.get(0);
            } else {
                return null;
            }
        } catch (RuntimeException re) {
            log.error("findByUsernameAndDsdIdentifierId failed", re);
            throw re;
        }
    }

    public List<PatientUser> findByPassword(Object password) {
        return findByProperty(PASSWORD, password);
    }

    public List<PatientUser> findByIntroducedBy(Object introducedBy) {
        return findByProperty(INTRODUCED_BY, introducedBy);
    }

    public List<PatientUser> findByLoginCount(Object loginCount) {
        return findByProperty(LOGIN_COUNT, loginCount);
    }

    public List<PatientUser> findByDsdIdentifierId(Object dsdIdentifierId) {
        return findByProperty(DSD_IDENTIFIER_ID, dsdIdentifierId);
    }

    public List<PatientUser> findByEnabled(Object enabled) {
        return findByProperty(ENABLED, enabled);
    }

    public List<PatientUser> findByAccountNonExpired(Object accountNonExpired) {
        return findByProperty(ACCOUNT_NON_EXPIRED, accountNonExpired);
    }

    public List<PatientUser> findByAccountNonLocked(Object accountNonLocked) {
        return findByProperty(ACCOUNT_NON_LOCKED, accountNonLocked);
    }

    public List<PatientUser> findByCredentialsNonExpired(
            Object credentialsNonExpired) {
        return findByProperty(CREDENTIALS_NON_EXPIRED, credentialsNonExpired);
    }

    public List<PatientUser> findByIp(Object ip) {
        return findByProperty(IP, ip);
    }
}