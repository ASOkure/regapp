package uk.ac.nesc.idsd.model;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class CentreDao extends AbstractDao<Centre> {
    private static final Log log = LogFactory.getLog(CentreDao.class);
    public static final String CENTRE_NAME = "centreName";
    public static final String DESCRIPTION = "description";
    public static final String ADDRESS = "address";

    public static CentreDao getFromApplicationContext(ApplicationContext ctx) {
        return (CentreDao) ctx.getBean("CentreDAO");
    }

    public List<Centre> findByCentreName(Object centreName) {
        return findByProperty(CENTRE_NAME, centreName);
    }

    public Centre findUniqueByCentreName(String centreName) {
        return findUniqueByProperty(CENTRE_NAME, centreName);
    }

//    public List findCentreNames() {
//        log.debug("finding all Country & Centres instances with min data");
//        try {
//            String queryString = "select new Centre(co.centreName, co.countryName) " +
//                    "from Country co, Centre ce " +
//                    "where ce.contry.countryId = co.countryId ";
//            return getHibernateTemplate().find(queryString);
//        } catch (RuntimeException re) {
//            log.error("find all failed", re);
//            throw re;
//        }
//    }

    @SuppressWarnings("unchecked")
    public List<Centre> findCountriesCentres() {
        log.debug("finding all Country & Centres instances with min data");
        try {
            String queryString = "select new Centre(ce.centreName, co.countryName) " +
                    "from Country co, Centre ce " +
                    "where ce.country.countryId = co.countryId ";
            Query query = getCurrentSession().createQuery(queryString);
            return query.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public List<Centre> findCentresNative() {
        log.debug("finding all Country & Centres instances with min data");
        try {
            String queryString = "select ce.centre_name, co.country_name from centre ce join country co on co.country_id = ce.country_id;";
            Query query = getCurrentSession().createSQLQuery(queryString);
            List<Object[]> rows = query.list();
            List<Centre> centreList = new ArrayList<>();
            for (Object[] row : rows) {
                centreList.add(new Centre(row[0].toString(), row[1].toString()));
            }
            return centreList;
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    @SuppressWarnings("unchecked")
    public List<Centre> findAllLeadersForJS() {
        log.debug("finding all Centre instances");
        try {
            String queryString = "select new Centre(ce.centreName, u.name, u.email) " +
                    "from Centre ce, PortalUser u where  " +
                    " ce.leader.username = u.username";
            Query query = getCurrentSession().createQuery(queryString);
            return query.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    @SuppressWarnings("unchecked")
    public List<Centre> findCentresByLeaderUsername(String username) {
        log.debug("finding all Centre instances");
        try {
            String queryString = "select new Centre(ce.centreId) " +
                    "from Centre ce, Country co, PortalUser u where ce.country.countryId = co.countryId " +
                    "and ce.leader.username = u.username and u.username = '" + username + "'";
            Query query = getCurrentSession().createQuery(queryString);
            return query.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }
}