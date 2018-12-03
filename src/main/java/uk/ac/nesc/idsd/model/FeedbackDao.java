package uk.ac.nesc.idsd.model;

import org.springframework.context.ApplicationContext;

import java.util.List;

public class FeedbackDao extends AbstractDao<Feedback> {
    public static final String USER_ID = "userId";
    public static final String FDBK_TXT = "fdbkTxt";
    public static final String PARENT = "parent";

    public static FeedbackDao getFromApplicationContext(ApplicationContext ctx) {
        return (FeedbackDao) ctx.getBean("FeedbackDAO");
    }

    @SuppressWarnings("unchecked")
    public List<Feedback> findByUserId(Object userId) {
        return findByProperty(USER_ID, userId);
    }

    @SuppressWarnings("unchecked")
    public List<Feedback> findByFdbkTxt(Object fdbkTxt) {
        return findByProperty(FDBK_TXT, fdbkTxt);
    }

    @SuppressWarnings("unchecked")
    public List<Feedback> findByParent(Object parent) {
        return findByProperty(PARENT, parent);
    }
}