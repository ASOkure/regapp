package uk.ac.nesc.idsd.model;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;

import static org.hibernate.criterion.Example.create;

/**
 * Created by jiangj on 12/06/2015.
 * 
 */

@Transactional
public abstract class AbstractDao<T> {
    private static final Log log = LogFactory.getLog(AbstractDao.class);

    private Class<T> persistentClass;
    @Autowired
    private SessionFactory sessionFactory;

    public AbstractDao() {
        Type genericSuperClass = getClass().getGenericSuperclass();

        ParameterizedType parametrizedType = null;
        while (parametrizedType == null) {
            if ((genericSuperClass instanceof ParameterizedType)) {
                parametrizedType = (ParameterizedType) genericSuperClass;
            } else {
                genericSuperClass = ((Class<?>) genericSuperClass).getGenericSuperclass();
            }
        }

        this.persistentClass = (Class<T>) parametrizedType.getActualTypeArguments()[0];
//        this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
    
    public void save(T transientInstance) {
        log.debug("saving instance");
        try {
        	
            getCurrentSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(T persistentInstance) {
        log.debug("deleting instance");
        try {
            getCurrentSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

//    public T findById(Integer id) {
//        return findUserWithActiveRolesById(id.longValue());
//    }

    public T findById(Serializable id) {
        try {
            return (T) getCurrentSession().get(getPersistentClass(), id);
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public <T> T get(final Class<T> type, final Long id) {
        return (T) sessionFactory.getCurrentSession().get(type, id);
    }

    @SuppressWarnings("unchecked")
    public List<T> findAllWithQuery() {
        log.debug("finding all instances");
        try {
//            final Criteria crit = getCurrentSession().createCriteria(getPersistentClass());
//            crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
//            return crit.list();
            String queryString = "from " + getPersistentClass().getSimpleName();
            Query query = getCurrentSession().createQuery(queryString);
            return query.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public List<T> findAll() {
        log.debug("finding all instances");
        try {
            final Criteria crit = getCurrentSession().createCriteria(getPersistentClass());
            crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            return crit.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    @SuppressWarnings("unchecked")
    public List<T> findByExample(T instance) {
        log.debug("finding instance by example");
        try {
            List<T> results = getCurrentSession().createCriteria(getPersistentClass()).add(create(instance)).list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }

    public List<T> findByProperty(String propertyName, Object value) {
        return findByProperty(propertyName, value, -1, -1);
    }

    public List<T> findByProperty(String propertyName, Object value, int start, int offset) {
        log.debug("finding instance with property: " + propertyName + ", value: " + value);
        try {
//            String queryString = "from " + getPersistentClass().getSimpleName() + " as model where model." + propertyName + "= ?";
//            Query queryObject = getCurrentSession().createQuery(queryString);
//            queryObject.setParameter(0, value);
//            return queryObject.list();
            Criteria criteria = getCurrentSession().createCriteria(getPersistentClass());
            criteria.add((Restrictions.eq(propertyName, value)));
            criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            if (start >= 0 && offset >= 0) {
                criteria.setFirstResult(start);
                criteria.setMaxResults(offset);
            }
            return criteria.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public T findUniqueByProperty(String propertyName, Object value) {
        log.debug("finding instance with property: " + propertyName + ", value: " + value);
        try {
            Criteria criteria = getCurrentSession().createCriteria(getPersistentClass());
            criteria.add((Restrictions.eq(propertyName, value)));
            criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            Object result = criteria.uniqueResult();
            return (T) result;
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public long findSize() {
        log.debug("finding size of all instances");
        try {
            return (Long) getCurrentSession().createCriteria(getPersistentClass()).setProjection(Projections.rowCount()).uniqueResult();
        } catch (RuntimeException re) {
            log.error("find size failed", re);
            throw re;
        }
    }

    public T merge(T detachedInstance) {
        log.debug("merging instance");
        try {
            T result = (T) getCurrentSession().merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }
   @Transactional
    public void attachDirty(T instance) {
        log.debug("attaching dirty instance");
        try {
            getCurrentSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    @SuppressWarnings("deprecation")
    public void attachClean(T instance) {
        log.debug("attaching clean T instance");
        try {
            getCurrentSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public Class<T> getPersistentClass() {
        return persistentClass;
    }
     

    @Transactional
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

}
