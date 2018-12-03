package uk.ac.nesc.idsd.model;

import org.springframework.context.ApplicationContext;

public class GeneCategoryDao extends AbstractDao<GeneCategory> {

    public static final String NAME = "name";

    public static GeneCategoryDao getFromApplicationContext(ApplicationContext ctx) {
        return (GeneCategoryDao) ctx.getBean("GeneCategoryDAO");
    }
}