package uk.ac.nesc.idsd.model;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class ParameterDao extends AbstractDao<Parameter> {
    private static final Log log = LogFactory.getLog(ParameterDao.class);
    public static final String TYPE = "type";
    public static final String LABEL = "label";
    public static final String PARAM_NAME = "paramName";
    public static final String MANDATORY = "mandatory";
    public static final String HIDDEN = "hidden";
    public static final String SEARCHABLE = "searchable";
    public static final String TOOLTIP = "tooltip";

    public static ParameterDao getFromApplicationContext(ApplicationContext ctx) {
        return (ParameterDao) ctx.getBean("ParameterDAO");
    }

    public List<Parameter> findByIds(List<Integer> ids) {
        try {
            String queryString = "FROM Parameter p WHERE p.paramId IN (:list)";
            Query query = getCurrentSession().createQuery(queryString).setParameterList("list", ids);
            return query.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    @SuppressWarnings("unchecked")
    public List<Parameter> findByType(Object type) {
        return findByProperty(TYPE, type);
    }

    @SuppressWarnings("unchecked")
    public List<Parameter> findByLabel(Object label) {
        return findByProperty(LABEL, label);
    }

    @SuppressWarnings("unchecked")
    public List<Parameter> findByParamName(Object paramName) {
        return findByProperty(PARAM_NAME, paramName);
    }

    @SuppressWarnings("unchecked")
    public List<Parameter> findByMandatory(Object mandatory) {
        return findByProperty(MANDATORY, mandatory);
    }

    @SuppressWarnings("unchecked")
    public List<Parameter> findByHidden(Object hidden) {
        return findByProperty(HIDDEN, hidden);
    }

    @SuppressWarnings("unchecked")
    public List<Parameter> findBySearchable(Object searchable) {
        return findByProperty(SEARCHABLE, searchable);
    }

    @SuppressWarnings("unchecked")
    public List<Parameter> findByTooltip(Object tooltip) {
        return findByProperty(TOOLTIP, tooltip);
    }
}