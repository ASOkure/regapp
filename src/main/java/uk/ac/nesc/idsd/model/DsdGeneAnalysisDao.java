package uk.ac.nesc.idsd.model;

import org.springframework.context.ApplicationContext;

import java.util.List;

public class DsdGeneAnalysisDao extends AbstractDao<DsdGeneAnalysis> {
    public static final String GENETIC_ANALYSIS = "geneticAnalysis";
    public static final String SINGLE_GENE_ANALYSIS = "singleGeneAnalysis";
    public static final String CHROMOSOMAL_REARRANGEMENT = "chromosomalRearrangement";
    public static final String CHROMOSOMAL_REARRANGEMENT_DETAIL = "chromosomalRearrangementDetail";
    public static final String CGH = "cgh";
    public static final String CGH_DETAIL = "cghDetail";
    public static final String FUNCTIONAL_STUDY = "functionalStudy";
    public static final String FUNCTIONAL_STUDY_DETAIL = "functionalStudyDetail";
    public static final String PUBLISHED_CASE = "publishedCase";
    public static final String PUBLISH_DETAIL = "publishDetail";
    public static final String FURTHER_STUDIES = "furtherStudies";

    public static DsdGeneAnalysisDao getFromApplicationContext(ApplicationContext ctx) {
        return (DsdGeneAnalysisDao) ctx.getBean("DsdGeneAnalysisDAO");
    }

    @SuppressWarnings("unchecked")
    public List<DsdGeneAnalysis> findByGeneticAnalysis(Object geneticAnalysis) {
        return findByProperty(GENETIC_ANALYSIS, geneticAnalysis);
    }

    @SuppressWarnings("unchecked")
    public List<DsdGeneAnalysis> findBySingleGeneAnalysis(Object singleGeneAnalysis) {
        return findByProperty(SINGLE_GENE_ANALYSIS, singleGeneAnalysis);
    }

    @SuppressWarnings("unchecked")
    public List<DsdGeneAnalysis> findByChromosomalRearrangement(Object chromosomalRearrangement) {
        return findByProperty(CHROMOSOMAL_REARRANGEMENT, chromosomalRearrangement);
    }

    @SuppressWarnings("unchecked")
    public List<DsdGeneAnalysis> findByChromosomalRearrangementDetail(Object chromosomalRearrangementDetail) {
        return findByProperty(CHROMOSOMAL_REARRANGEMENT_DETAIL, chromosomalRearrangementDetail);
    }

    @SuppressWarnings("unchecked")
    public List<DsdGeneAnalysis> findByCgh(Object cgh) {
        return findByProperty(CGH, cgh);
    }

    @SuppressWarnings("unchecked")
    public List<DsdGeneAnalysis> findByCghDetail(Object cghDetail) {
        return findByProperty(CGH_DETAIL, cghDetail);
    }

    @SuppressWarnings("unchecked")
    public List<DsdGeneAnalysis> findByFunctionalStudy(Object functionalStudy) {
        return findByProperty(FUNCTIONAL_STUDY, functionalStudy);
    }

    @SuppressWarnings("unchecked")
    public List<DsdGeneAnalysis> findByFunctionalStudyDetail(Object functionalStudyDetail) {
        return findByProperty(FUNCTIONAL_STUDY_DETAIL, functionalStudyDetail);
    }

    @SuppressWarnings("unchecked")
    public List<DsdGeneAnalysis> findByPublishedCase(Object publishedCase) {
        return findByProperty(PUBLISHED_CASE, publishedCase);
    }

    @SuppressWarnings("unchecked")
    public List<DsdGeneAnalysis> findByPublishDetail(Object publishDetail) {
        return findByProperty(PUBLISH_DETAIL, publishDetail);
    }

    @SuppressWarnings("unchecked")
    public List<DsdGeneAnalysis> findByFurtherStudies(Object furtherStudies) {
        return findByProperty(FURTHER_STUDIES, furtherStudies);
    }
}