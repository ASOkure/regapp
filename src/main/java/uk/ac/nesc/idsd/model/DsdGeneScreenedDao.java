package uk.ac.nesc.idsd.model;

import org.springframework.context.ApplicationContext;

import java.util.List;

public class DsdGeneScreenedDao extends AbstractDao<DsdGeneScreened> {
    public static final String DIRECT_SEQUENCE = "directSequence";
    public static final String OTHER_SEQUENCE = "otherSequence";
    public static final String MUTATION_FOUND = "mutationFound";
    public static final String MUTATION_DETAIL = "mutationDetail";

    public static DsdGeneScreenedDao getFromApplicationContext(ApplicationContext ctx) {
        return (DsdGeneScreenedDao) ctx.getBean("DsdGeneScreenedDAO");
    }

    @SuppressWarnings("unchecked")
    public List<DsdGeneScreened> findByDirectSequence(Object directSequence) {
        return findByProperty(DIRECT_SEQUENCE, directSequence);
    }

    @SuppressWarnings("unchecked")
    public List<DsdGeneScreened> findByOtherSequence(Object otherSequence) {
        return findByProperty(OTHER_SEQUENCE, otherSequence);
    }

    @SuppressWarnings("unchecked")
    public List<DsdGeneScreened> findByMutationFound(Object mutationFound) {
        return findByProperty(MUTATION_FOUND, mutationFound);
    }

    @SuppressWarnings("unchecked")
    public List<DsdGeneScreened> findByMutationDetail(Object mutationDetail) {
        return findByProperty(MUTATION_DETAIL, mutationDetail);
    }
}