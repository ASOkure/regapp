package uk.ac.nesc.idsd.model;

import org.springframework.context.ApplicationContext;

import java.util.List;

public class DsdAssessmentDao extends AbstractDao<DsdAssessment> {
    public static final String PHALLUS_LENGTH = "phallusLength";
    public static final String PHALLUS_SIZE = "phallusSize";
    public static final String URINARY_MEATUS = "urinaryMeatus";
    public static final String LABIOSCROTAL_FUSION = "labioscrotalFusion";
    public static final String RIGHT_GONAD = "rightGonad";
    public static final String LEFT_GONAD = "leftGonad";
    public static final String MULLERIAN_STRUCTURE = "mullerianStructure";
    public static final String WOLFFIAN_STRUCTURE = "wolffianStructure";
    public static final String EMS = "ems";
    public static final String MODIFIED_PRADER = "modifiedPrader";
    public static final String TANNER_STAGE = "tannerStage";
    public static final String GONADECTOMY = "gonadectomy";
    public static final String GESTATION = "gestation";

    public static DsdAssessmentDao getFromApplicationContext(ApplicationContext ctx) {
        return (DsdAssessmentDao) ctx.getBean("DsdAssessmentDAO");
    }

    @SuppressWarnings("unchecked")
    public List<DsdAssessment> findByPhallusLength(Object phallusLength) {
        return findByProperty(PHALLUS_LENGTH, phallusLength);
    }

    @SuppressWarnings("unchecked")
    public List<DsdAssessment> findByPhallusSize(Object phallusSize) {
        return findByProperty(PHALLUS_SIZE, phallusSize);
    }

    @SuppressWarnings("unchecked")
    public List<DsdAssessment> findByUrinaryMeatus(Object urinaryMeatus) {
        return findByProperty(URINARY_MEATUS, urinaryMeatus);
    }

    @SuppressWarnings("unchecked")
    public List<DsdAssessment> findByLabioscrotalFusion(Object labioscrotalFusion) {
        return findByProperty(LABIOSCROTAL_FUSION, labioscrotalFusion);
    }

    @SuppressWarnings("unchecked")
    public List<DsdAssessment> findByRightGonad(Object rightGonad) {
        return findByProperty(RIGHT_GONAD, rightGonad);
    }

    @SuppressWarnings("unchecked")
    public List<DsdAssessment> findByLeftGonad(Object leftGonad) {
        return findByProperty(LEFT_GONAD, leftGonad);
    }

    @SuppressWarnings("unchecked")
    public List<DsdAssessment> findByMullerianStructure(Object mullerianStructure) {
        return findByProperty(MULLERIAN_STRUCTURE, mullerianStructure);
    }

    @SuppressWarnings("unchecked")
    public List<DsdAssessment> findByWolffianStructure(Object wolffianStructure) {
        return findByProperty(WOLFFIAN_STRUCTURE, wolffianStructure);
    }

    @SuppressWarnings("unchecked")
    public List<DsdAssessment> findByEms(Object ems) {
        return findByProperty(EMS, ems);
    }

    @SuppressWarnings("unchecked")
    public List<DsdAssessment> findByModifiedPrader(Object modifiedPrader) {
        return findByProperty(MODIFIED_PRADER, modifiedPrader);
    }

    @SuppressWarnings("unchecked")
    public List<DsdAssessment> findByTannerStage(Object tannerStage) {
        return findByProperty(TANNER_STAGE, tannerStage);
    }

    @SuppressWarnings("unchecked")
    public List<DsdAssessment> findByGonadectomy(Object gonadectomy) {
        return findByProperty(GONADECTOMY, gonadectomy);
    }

    @SuppressWarnings("unchecked")
    public List<DsdAssessment> findByGestation(Object gestation) {
        return findByProperty(GESTATION, gestation);
    }
}