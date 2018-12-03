package uk.ac.nesc.idsd.model;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class OptionDao extends AbstractDao<Option> {
    private static final Log log = LogFactory.getLog(OptionDao.class);
    public static final String VALUE = "value";
    public static final String SCORE = "score";

    public static OptionDao getFromApplicationContext(ApplicationContext ctx) {
        return (OptionDao) ctx.getBean("OptionDAO");
    }

    public List<Option> findByValue(Object value) {
        return findByProperty(VALUE, value);
    }

    public List<Option> findByScore(Object score) {
        return findByProperty(SCORE, score);
    }

    public List<String> findAllOptionValuesByMenuId(Integer menuId) {
        String sql = "SELECT DISTINCT o.value FROM `option` o where o.menu_id = :menuId";
        try {
            Query query = getCurrentSession().createSQLQuery(sql).setParameter("menuId", menuId);
            return query.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }
}