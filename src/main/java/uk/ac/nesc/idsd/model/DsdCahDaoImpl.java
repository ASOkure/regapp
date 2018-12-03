package uk.ac.nesc.idsd.model;

import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class DsdCahDaoImpl extends AbstractDao<DsdCah> implements DsdCahDao {
    String PRENATAL_DIAGNOSIS = "prenatalDiagnosis";
    String GESTATIONAL_AGE = "gestationalAge";
    String AAFP = "aafp";
    String PRADER_STAGE_AT_FIRST_PRESENTATION = "praderStageAtFirstPresentation";
    String SW_CRISIS_AT_PRESENTATION = "swCrisisAtPresentation";
    String ADRENAL_CRISES_AFTER_FIRST_PRESENTATION = "adrenalCrisesAfterFirstPresentation";
    String CURRENT_SALT_REPLACEMENT = "currentSaltReplacement";
    String CAH_FREE_TEXT = "cahFreeText";

    public static DsdCahDao getFromApplicationContext(ApplicationContext ctx) {
        return (DsdCahDao) ctx.getBean("DsdCahDAO");
    }

    @Override
    public List<DsdCah> findByPrenatalDiagnosis(Object prenatalDiagnosis) {
        return findByProperty(PRENATAL_DIAGNOSIS, prenatalDiagnosis);
    }

    @Override
    public List<DsdCah> findByGestationalAge(Object gestationalAge) {
        return findByProperty(GESTATIONAL_AGE, gestationalAge);
    }

    @Override
    public List<DsdCah> findByAgeAtFirstPresentation(Object aafp) {
        return findByProperty(AAFP, aafp);
    }

    @Override
    public List<DsdCah> findByPraderStageAtFirstPresentation(Object praderStageAtFirstPresentation) {
        return findByProperty(PRADER_STAGE_AT_FIRST_PRESENTATION, praderStageAtFirstPresentation);
    }

    @Override
    public List<DsdCah> findBySwCrisisAtPresentation(Object swCrisisAtPresentation) {
        return findByProperty(SW_CRISIS_AT_PRESENTATION, swCrisisAtPresentation);
    }

    @Override
    public List<DsdCah> findByAdrenalCrisesAfterFirstPresentation(Object adrenalCrisesAfterFirstPresentation) {
        return findByProperty(ADRENAL_CRISES_AFTER_FIRST_PRESENTATION, adrenalCrisesAfterFirstPresentation);
    }

    @Override
    public List<DsdCah> findByCurrentSaltReplacement(Object currentSaltReplacement) {
        return findByProperty(CURRENT_SALT_REPLACEMENT, currentSaltReplacement);
    }

    @Override
    public List<DsdCah> findByCahFreeText(Object cahFreeText) {
        return findByProperty(CAH_FREE_TEXT, cahFreeText);
    }

    @Override
    public DsdCah findById(Long cahId) {
        return super.findById(cahId);
    }


	
}