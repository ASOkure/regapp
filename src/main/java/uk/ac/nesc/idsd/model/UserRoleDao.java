package uk.ac.nesc.idsd.model;

import org.springframework.context.ApplicationContext;

import java.util.List;

public class UserRoleDao extends AbstractDao<UserRole> {
    public static final String APPROVED_BY = "approvedBy";

    public static UserRoleDao getFromApplicationContext(ApplicationContext ctx) {
        return (UserRoleDao) ctx.getBean("UserRoleDAO");
    }

    public List<UserRole> findByApprovedBy(Object approvedBy) {
        return findByProperty(APPROVED_BY, approvedBy);
    }
}