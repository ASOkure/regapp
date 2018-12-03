package uk.ac.nesc.idsd.model;

import org.springframework.context.ApplicationContext;

import java.util.List;

public class RoleDao extends AbstractDao<Role> {
    public static final String ROLE_NAME = "roleName";
    public static final String ROLE_DESCRIPTION = "roleDescription";
    public static final String CREATED_BY = "createdBy";

    public static RoleDao getFromApplicationContext(ApplicationContext ctx) {
        return (RoleDao) ctx.getBean("RoleDAO");
    }

    @SuppressWarnings("unchecked")
    public List<Role> findByRoleName(Object roleName) {
        return findByProperty(ROLE_NAME, roleName);
    }

    @SuppressWarnings("unchecked")
    public List<Role> findByRoleDescription(Object roleDescription) {
        return findByProperty(ROLE_DESCRIPTION, roleDescription);
    }

    @SuppressWarnings("unchecked")
    public List<Role> findByCreatedBy(Object createdBy) {
        return findByProperty(CREATED_BY, createdBy);
    }
}