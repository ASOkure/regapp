package uk.ac.nesc.idsd.model;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class ResearchResultDao extends AbstractDao<ResearchResult> {
    private static final Log log = LogFactory.getLog(ResearchResultDao.class);
    public static final String ORIGINAL_FILE_NAME = "originalFileName";
    public static final String CONTENT_TYPE = "contentType";
    public static final String ACCESS = "access";
    public static final String COMMENT = "comment";
    public static final String UPLOADER = "uploader";
    public static final String PATH = "path";
    public static final String DISK_FILE_NAME = "diskFileName";

    public static ResearchResultDao getFromApplicationContext(ApplicationContext ctx) {
        return (ResearchResultDao) ctx.getBean("ResearchResultDAO");
    }

    @SuppressWarnings("unchecked")
    public List<ResearchResult> findByOriginalFileName(Object originalFileName) {
        return findByProperty(ORIGINAL_FILE_NAME, originalFileName);
    }

    @SuppressWarnings("unchecked")
    public List<ResearchResult> findByContentType(Object contentType) {
        return findByProperty(CONTENT_TYPE, contentType);
    }

    @SuppressWarnings("unchecked")
    public List<ResearchResult> findByAccess(Object access) {
        return findByProperty(ACCESS, access);
    }

    @SuppressWarnings("unchecked")
    public List<ResearchResult> findByComment(Object comment) {
        return findByProperty(COMMENT, comment);
    }

    @SuppressWarnings("unchecked")
    public List<ResearchResult> findByUploader(Object uploader) {
        return findByProperty(UPLOADER, uploader);
    }

    @SuppressWarnings("unchecked")
    public List<ResearchResult> findByPath(Object path) {
        return findByProperty(PATH, path);
    }

    @SuppressWarnings("unchecked")
    public List<ResearchResult> findByDiskFileName(Object diskFileName) {
        return findByProperty(DISK_FILE_NAME, diskFileName);
    }

    @SuppressWarnings("rawtypes")
    public List findAllByUsername(String username) {
        log.debug("finding all ResearchResult instances");
        try {
            String queryString = "from ResearchResult rr where rr.uploader = '" + username + "'";
            Query query = getCurrentSession().createQuery(queryString);
            return query.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    @SuppressWarnings("rawtypes")
    public List findAllSharedByOthers(String username) {
        log.debug("finding all ResearchResult instances shared by others to current user");
        try {
            String queryString = "from ResearchResult rr " + "where rr.uploader <> '" + username + "' and rr.access like '%" + username + "%'";
            Query query = getCurrentSession().createQuery(queryString);
            return query.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }
}