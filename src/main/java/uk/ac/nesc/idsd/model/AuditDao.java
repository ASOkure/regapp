package uk.ac.nesc.idsd.model;

import org.springframework.context.ApplicationContext;

import java.util.List;

public class AuditDao extends AbstractDao<Audit> {
    public static final String USER_ID = "userId";
    public static final String ACTION = "action";
    public static final String RECORD_ID = "recordId";
    public static final String PARAM = "param";
    public static final String IP = "ip";

    public static AuditDao getFromApplicationContext(ApplicationContext ctx) {
        return (AuditDao) ctx.getBean("AuditDAO");
    }

    @SuppressWarnings("unchecked")
    public List<Audit> findByUserId(Object userId) {
        return findByProperty(USER_ID, userId);
    }

    @SuppressWarnings("unchecked")
    public List<Audit> findByAction(Object action) {
        return findByProperty(ACTION, action);
    }

    @SuppressWarnings("unchecked")
    public List<Audit> findByRecordId(Object recordId) {
        return findByProperty(RECORD_ID, recordId);
    }

    @SuppressWarnings("unchecked")
    public List<Audit> findByParam(Object param) {
        return findByProperty(PARAM, param);
    }

    @SuppressWarnings("unchecked")
    public List<Audit> findByIp(Object ip) {
        return findByProperty(IP, ip);
    }


}