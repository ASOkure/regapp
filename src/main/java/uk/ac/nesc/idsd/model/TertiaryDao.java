package uk.ac.nesc.idsd.model;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class TertiaryDao extends AbstractDao<Tertiary> {
    private static final Log log = LogFactory.getLog(TertiaryDao.class);
    public static final String VALUE = "value";
    public static final String IDSD_PARTNER = "idsdPartner";

    public static TertiaryDao getFromApplicationContext(ApplicationContext ctx) {
        return (TertiaryDao) ctx.getBean("TertiaryDAO");
    }

    public List<String> findAllTertiaryValuesByOptionValue(String optionValue) {
        String sql = "SELECT t.value\n" +
                "FROM tertiary t join `option` o on o.option_id = t.option_id\n" +
                "where o.value = :optionValue";
        try {
            Query query = getCurrentSession().createSQLQuery(sql).setParameter("optionValue", optionValue);
            return query.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    @SuppressWarnings("unchecked")
    public List<Tertiary> findByValue(Object value) {
        return findByProperty(VALUE, value);
    }

    @SuppressWarnings("unchecked")
    public List<Tertiary> findByIdsdPartner(Object idsdPartner) {
        return findByProperty(IDSD_PARTNER, idsdPartner);
    }
}