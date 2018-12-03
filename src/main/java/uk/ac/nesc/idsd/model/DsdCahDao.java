package uk.ac.nesc.idsd.model;

import java.util.List;

/**
 * Created by jiangj on 10/03/2015.
 */
public interface DsdCahDao {

    void save(DsdCah transientInstance);

    void delete(DsdCah persistentInstance);

    List<DsdCah> findByExample(DsdCah instance);

    List<DsdCah> findByProperty(String propertyName, Object value);

    List<DsdCah> findByPrenatalDiagnosis(Object prenatalDiagnosis);

    List<DsdCah> findByGestationalAge(Object gestationalAge);

    List<DsdCah> findByAgeAtFirstPresentation(Object aafp);

    List<DsdCah> findByPraderStageAtFirstPresentation(Object praderStageAtFirstPresentation);

    List<DsdCah> findBySwCrisisAtPresentation(Object swCrisisAtPresentation);

    List<DsdCah> findByAdrenalCrisesAfterFirstPresentation(Object adrenalCrisesAfterFirstPresentation);

    List<DsdCah> findByCurrentSaltReplacement(Object currentSaltReplacement);

    List<DsdCah> findByCahFreeText(Object cahFreeText);

    List<DsdCah> findAll();

    DsdCah merge(DsdCah detachedInstance);

    void attachDirty(DsdCah instance);

    void attachClean(DsdCah instance);

    DsdCah findById(Long cahId);
}
