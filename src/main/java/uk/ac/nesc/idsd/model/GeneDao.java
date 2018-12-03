package uk.ac.nesc.idsd.model;

import org.springframework.context.ApplicationContext;

import java.util.List;

public class GeneDao extends AbstractDao<Gene> {
    public static final String GENE_NAME = "geneName";
    public static final String DESCRIPTION = "description";

    public static GeneDao getFromApplicationContext(ApplicationContext ctx) {
        return (GeneDao) ctx.getBean("GeneDAO");
    }

    @SuppressWarnings("unchecked")
    public List<Gene> findByGeneName(Object geneName) {
        return findByProperty(GENE_NAME, geneName);
    }

    @SuppressWarnings("unchecked")
    public List<Gene> findByDescription(Object description) {
        return findByProperty(DESCRIPTION, description);
    }
}