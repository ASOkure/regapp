package uk.ac.nesc.idsd.model;

import org.springframework.context.ApplicationContext;

import java.util.List;

public class ResearchResultCategoryDao extends AbstractDao<ResearchResultCategory> {
    public static final String NAME = "name";

    public static ResearchResultCategoryDao getFromApplicationContext(ApplicationContext ctx) {
        return (ResearchResultCategoryDao) ctx.getBean("ResearchResultCategoryDAO");
    }

    @SuppressWarnings("unchecked")
    public List<ResearchResultCategory> findByName(Object name) {
        return findByProperty(NAME, name);
    }
}