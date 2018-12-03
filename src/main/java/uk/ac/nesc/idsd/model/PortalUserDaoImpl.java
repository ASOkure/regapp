package uk.ac.nesc.idsd.model;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;
import uk.ac.nesc.idsd.exception.DataException;
import uk.ac.nesc.idsd.exception.ErrorCodes;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@SuppressWarnings("unchecked")
public class PortalUserDaoImpl extends AbstractDao<PortalUser> implements PortalUserDao {
    private static final Log log = LogFactory.getLog(PortalUserDaoImpl.class);
    public static final String PASSWORD = "password";
    public static final String EMAIL = "email";
    public static final String NAME = "name";
    public static final String COUNTRY = "country";
    public static final String CENTRE = "centre";
    public static final String TEL = "tel";
    public static final String LOGIN_COUNTER = "loginCounter";
    public static final String ENABLED = "enabled";
    public static final String ACC_NON_EXPIRED = "accNonExpired";
    public static final String ACC_NON_LOCKED = "accNonLocked";
    public static final String ACC_CRED_EXPIRED = "accCredExpired";
    public static final String IP = "ip";
    public static final String FAX = "fax";
    public static final String POSITION = "position";
    public static final String SOCIETY = "society";
    public static final String ADDRESS = "address";
    public static final String PRIMARY_ROLE = "primaryRole";
    public static final String SECONDARY_ROLES = "secondaryRoles";
    public static final String SECONDARY_ROLES_OTHER = "secondaryRolesOther";
    public static final String INTEREST = "interest";
    public static final String INTEREST_OTHER = "interestOther";
    public static final String SPECIAL_INTEREST = "specialInterest";
    public static final String SEARCHABLE = "searchable";

    public PortalUserDaoImpl() {
    }

    public static void main(String[] args) {
        Calendar asOfDate = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String asOfDateStr = sdf.format(asOfDate.getTime());
        String query = "from PortalUser where last_login < '" + asOfDateStr + "'";
        log.info(query);
    }

    public static PortalUserDao getFromApplicationContext(ApplicationContext ctx) {
        return (PortalUserDao) ctx.getBean("portalUserDao");
    }

    @Override
    public List<PortalUser> findByCriteria(PortalUser instance) {
        log.debug("finding PortalUser instance by criteria");
        try {
            instance.setEnabled(true);
            instance.setAccountNonExpired(true);
            instance.setAccountNonLocked(true);
            instance.setCredentialsNonExpired(true);

//            DetachedCriteria criteria = DetachedCriteria.forClass(PortalUser.class);
            Criteria criteria = getCurrentSession().createCriteria(PortalUser.class);
            criteria.add(Example.create(instance));

            if (instance.getSecondaryRoles() != null) {
                String[] secondaryRoles = instance.getSecondaryRoles().split(",");
                if (null != secondaryRoles && secondaryRoles.length > 0) {
//                    criteria = criteria.createCriteria("secondaryRoles");
                    for (String secondaryRole : secondaryRoles) {
//            		criteria.add(Property.forName("secondaryRoles").in(new String[] {secondaryRole.trim()}));
                        criteria.add(Restrictions.like("secondaryRoles", secondaryRole, MatchMode.ANYWHERE).ignoreCase());
                    }
                }
//            	String secondaryRoles = instance.getSecondaryRoles();
//            	criteria.add(Restrictions.like("secondaryRoles", secondaryRoles, MatchMode.ANYWHERE).ignoreCase());
                instance.setSecondaryRoles(null);
            }
            if (instance.getInterest() != null) {
                String[] interests = instance.getInterest().split(",");
                if (null != interests && interests.length > 0) {
//                    criteria = criteria.createCriteria("interest");
                    for (String interest : interests) {
                        criteria.add(Restrictions.like("interest", interest, MatchMode.ANYWHERE).ignoreCase());
                    }
                }
                instance.setInterest(null);
            }
            if (instance.getSociety() != null) {
                String society = instance.getSociety();
                criteria.add(Restrictions.like("society", society, MatchMode.ANYWHERE).ignoreCase());
                instance.setSociety(null);
            }
            if (instance.getName() != null) {
                String fullname = instance.getName();
                criteria.add(Restrictions.like("name", fullname, MatchMode.ANYWHERE).ignoreCase());
                instance.setName(null);
            }
            if (instance.getPosition() != null) {
                String position = instance.getPosition();
                criteria.add(Restrictions.like("position", position, MatchMode.ANYWHERE).ignoreCase());
                instance.setPosition(null);
            }
//            List<PortalUser> results = (List<PortalUser>) getHibernateTemplate().findByCriteria(criteria);
            List<PortalUser> results = (List<PortalUser>) criteria.list();
            log.debug("find by criteria successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by criteria failed", re);
            throw re;
        }
    }

    @Override
    public PortalUser findUserById(java.lang.String id) {
        return super.findById(id);
    }

    @Override
    @Transactional
    public PortalUser findUserWithActiveRolesById(String id) throws DataException {
        log.debug("getting PortalUser instance with id: " + id);
        PortalUser user;
        try {
            Session session = getCurrentSession();
            session.enableFilter("betweenDates").setParameter("today", new Date());
            user = (PortalUser) session.get("uk.ac.nesc.idsd.model.PortalUser", id);
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
        if (user == null) {
            throw new DataException(ErrorCodes.NO_RECORD);
        } else {
            return user;
        }
    }

    @Override
    @Transactional
    public PortalUser findReadyOnlyPortalUserById(String id) {
        log.debug("getting read only PortalUser instance with id: " + id);
        PortalUser user;
        try {
            Session session = getCurrentSession();
            session.setDefaultReadOnly(true);
            session.enableFilter("betweenDates").setParameter("today", new Date());
            user = (PortalUser) session.get("uk.ac.nesc.idsd.model.PortalUser", id);
            session.setDefaultReadOnly(false);
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
        return user;
    }

    @Override
    public List<PortalUser> findByUsernames(List<String> ids) {
        log.debug("finding PortalUser instances with ids: " + ids);
        try {
            StringBuilder usernamesSB = new StringBuilder();
            for (String username : ids) {
                if (username != null && !username.trim().isEmpty()) {
                    usernamesSB.append("'");
                    usernamesSB.append(username.trim());
                    usernamesSB.append("',");
                }
            }
            String usernames = usernamesSB.substring(0, usernamesSB.length() - 1);
            String queryString = "select new PortalUser(name, email) from PortalUser u where u.username in (" + usernames + ")";
            Query query = getCurrentSession().createQuery(queryString);
            return query.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    @Override
    public List<PortalUser> findByPassword(Object password) {
        return findByProperty(PASSWORD, password);
    }

    @Override
    public List<PortalUser> findByEmail(Object email) {
        return findByProperty(EMAIL, email);
    }

    @Override
    public List<PortalUser> findByName(Object name) {
        return findByProperty(NAME, name);
    }

    @Override
    public List<PortalUser> findByCountry(Object country) {
        return findByProperty(COUNTRY, country);
    }

    @Override
    public List<PortalUser> findByCentre(Object centre) {
        return findByProperty(CENTRE, centre);
    }

    @Override
    public List<PortalUser> findByTel(Object tel) {
        return findByProperty(TEL, tel);
    }

    @Override
    public List<PortalUser> findByLoginCounter(Object loginCounter) {
        return findByProperty(LOGIN_COUNTER, loginCounter);
    }

    @Override
    public List<PortalUser> findByEnabled(Object enabled) {
        return findByProperty(ENABLED, enabled);
    }

    @Override
    public List<PortalUser> findByAccNonExpired(Object accNonExpired) {
        return findByProperty(ACC_NON_EXPIRED, accNonExpired);
    }

    @Override
    public List<PortalUser> findByAccNonLocked(Object accNonLocked) {
        return findByProperty(ACC_NON_LOCKED, accNonLocked);
    }

    @Override
    public List<PortalUser> findByAccCredExpired(Object accCredExpired) {
        return findByProperty(ACC_CRED_EXPIRED, accCredExpired);
    }

    @Override
    public List<PortalUser> findByIp(Object ip) {
        return findByProperty(IP, ip);
    }

    @Override
    public List<PortalUser> findByFax(Object fax) {
        return findByProperty(FAX, fax);
    }

    @Override
    public List<PortalUser> findByPosition(Object position) {
        return findByProperty(POSITION, position);
    }

    @Override
    public List<PortalUser> findBySociety(Object society) {
        return findByProperty(SOCIETY, society);
    }

    @Override
    public List<PortalUser> findByAddress(Object address) {
        return findByProperty(ADDRESS, address);
    }

    @Override
    public List<PortalUser> findByPrimaryRole(Object primaryRole) {
        return findByProperty(PRIMARY_ROLE, primaryRole);
    }

    @Override
    public List<PortalUser> findBySecondaryRoles(Object secondaryRoles) {
        return findByProperty(SECONDARY_ROLES, secondaryRoles);
    }

    @Override
    public List<PortalUser> findBySecondaryRolesOther(Object secondaryRolesOther) {
        return findByProperty(SECONDARY_ROLES_OTHER, secondaryRolesOther);
    }

    @Override
    public List<PortalUser> findByInterest(Object interest) {
        return findByProperty(INTEREST, interest);
    }

    @Override
    public List<PortalUser> findByInterestOther(Object interestOther) {
        return findByProperty(INTEREST_OTHER, interestOther);
    }

    @Override
    public List<PortalUser> findBySpecialInterest(Object specialInterest) {
        return findByProperty(SPECIAL_INTEREST, specialInterest);
    }

    @Override
    public List<PortalUser> findBySearchable(Object searchable) {
        return findByProperty(SEARCHABLE, searchable);
    }

    @Override
    public List<PortalUser> findAllButCurrentUser(String username) {
        log.debug("finding all PortalUser instances but current user");
        try {
            String queryString = "select new PortalUser(username, name, email, country, centre) from PortalUser where username <> '" +
                    username + "' order by name";
            Query query = getCurrentSession().createQuery(queryString);
            return query.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    @Override
    public List<PortalUser> findInactiveUsersFromAsOfDate(Calendar asOfDate) {
        List<PortalUser> users = new ArrayList<PortalUser>(0);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (asOfDate == null) {
            return users;
        }
        String asOfDateStr = sdf.format(asOfDate.getTime());
        String queryString = "from PortalUser where last_login < '" + asOfDateStr + "'";
        Query query = getCurrentSession().createQuery(queryString);
        return query.list();
    }

    @Override
    public List<PortalUser> findUnchangedPasswordUsersFromAsOfDate(Calendar asOfDate) {
        List<PortalUser> users = new ArrayList<PortalUser>(0);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (asOfDate == null) {
            return users;
        }
        String asOfDateStr = sdf.format(asOfDate.getTime());
        String queryString = "from PortalUser where last_password_change < '" + asOfDateStr + "'";
        Query query = getCurrentSession().createQuery(queryString);
        return query.list();
    }

	
}