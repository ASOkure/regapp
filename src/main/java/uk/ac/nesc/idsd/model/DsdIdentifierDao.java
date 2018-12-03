package uk.ac.nesc.idsd.model;

import java.util.List;
import java.util.Set;

/**
 * Created by jiangj on 10/03/2015.
 */
public interface DsdIdentifierDao {
    String COUNTRY = "country";
    String CENTRE = "centre";
    String LOCAL_ID = "localId";
    String YOB = "yob";
    String BIRTH_WEIGHT = "birthWeight";
    String AOFP = "aofp";
    String CLINICIAN = "clinician";
    String CONTACT = "contact";
    String KARYOTYPE = "karyotype";
    String DISORDER_TYPE = "disorderType";
    String ACTUAL_DIAGNOSIS = "actualDiagnosis";
    String SEX_ASSIGNED = "sexAssigned";
    String GENETIC_CERTAINTY = "geneticCertainty";
    String ASSOC_MALFORMS = "assocMalforms";
    String DNA_ANALYSIS = "dnaAnalysis";
    String CLINICAL_INFO = "clinicalInfo";
    String CASE_NOTES = "caseNotes";
    String GROWTH_DATA = "growthData";
    String PUBERTY_DATA = "pubertyData";
    String DNA = "dna";
    String TISSUE = "tissue";
    String CELL_LINE = "cellLine";
    String CELL_LINE_INFO = "cellLineInfo";
    String URINE = "urine";
    String SERUM = "serum";
    String SAMPLE_SHARED = "sampleShared";
    String SAMPLE_SHARED_DETAIL = "sampleSharedDetail";
    String INFERTILITY_HISTORY = "infertilityHistory";
    String SAMPLE_AVAILABILITY = "sampleAvailability";
    String CLINICAL_FEATURES = "clinicalFeatures";
    String BIOCHEMISTRY = "biochemistry";
    String PARENTAL_CONSANGUINITY = "parentalConsanguinity";
    String CONSENT = "consent";
    String SAMPLE_CONSENT = "sampleConsent";
    String FREE_TEXT = "freeText";
    String UPLOADER = "uploader";
    String DSD_HISTORY = "dsdHistory";
    String COMPLETENESS = "completeness";
    String DELETE_STATUS = "deleteStatus";
    String CORE_FREE_TEXT = "coreFreeText";
    String BIRTH_LENGTH = "birthLength";

    List<DsdIdentifier> findByCriteria(DsdIdentifier instance);

    List findDsdIdentifierStatistic();

    List findAllRegisterIds();

    List<DsdIdentifier> findByUploader(String username);

    List<DsdIdentifier> findByCentre(String centreName);

    long getTotalContributionNumber();

    List<DsdIdentifier> findByProperty(String propertyName, Object value);

    List<DsdIdentifier> findByCountry(Object country);

//    List<DsdIdentifier> findByCentre(Object centre);

    List<DsdIdentifier> findByLocalId(Object localId);

    List<DsdIdentifier> findByYob(Object yob);

    List<DsdIdentifier> findByBirthWeight(Object birthWeight);

    List<DsdIdentifier> findByAofp(Object aofp);

    List<DsdIdentifier> findByClinician(Object clinician);

    List<DsdIdentifier> findByContact(Object contact);

    List<DsdIdentifier> findByKaryotype(Object karyotype);

    List<DsdIdentifier> findByDisorderType(Object disorderType);

    List<DsdIdentifier> findByActualDiagnosis(Object actualDiagnosis);

    List<DsdIdentifier> findBySexAssigned(Object sexAssigned);

    List<DsdIdentifier> findByGeneticCertainty(Object geneticCertainty);

    List<DsdIdentifier> findByAssocMalforms(Object assocMalforms);

    List<DsdIdentifier> findByDnaAnalysis(Object dnaAnalysis);

    List<DsdIdentifier> findByClinicalInfo(Object clinicalInfo);

    List<DsdIdentifier> findByCaseNotes(Object caseNotes);

    List<DsdIdentifier> findByGrowthData(Object growthData);

    List<DsdIdentifier> findByPubertyData(Object pubertyData);

    List<DsdIdentifier> findByDna(Object dna);

    List<DsdIdentifier> findByTissue(Object tissue);

    List<DsdIdentifier> findByCellLine(Object cellLine);

    List<DsdIdentifier> findByCellLineInfo(Object cellLineInfo);

    List<DsdIdentifier> findByUrine(Object urine);

    List<DsdIdentifier> findBySerum(Object serum);

    List<DsdIdentifier> findBySampleShared(Object sampleShared);

    List<DsdIdentifier> findBySampleSharedDetail(Object sampleSharedDetail);

    List<DsdIdentifier> findByInfertilityHistory(Object infertilityHistory);

    List<DsdIdentifier> findBySampleAvailability(Object sampleAvailability);

    List<DsdIdentifier> findByClinicalFeatures(Object clinicalFeatures);

    List<DsdIdentifier> findByBiochemistry(Object biochemistry);

    List<DsdIdentifier> findByParentalConsanguinity(Object parentalConsanguinity);

    List<DsdIdentifier> findByConsent(Object consent);

    List<DsdIdentifier> findBySampleConsent(Object sampleConsent);

    List<DsdIdentifier> findByFreeText(Object freeText);

    List<DsdIdentifier> findByUploader(Object uploader);

    List<DsdIdentifier> findByDsdHistory(Object dsdHistory);

    List<DsdIdentifier> findByCompleteness(Object completeness);

    List<DsdIdentifier> findByDeleteStatus(Object deleteStatus);

    List<DsdIdentifier> findByCoreFreeText(Object coreFreeText);

    List<DsdIdentifier> findByBirthLength(Object birthLength);

    List findAll();

    DsdIdentifier merge(DsdIdentifier detachedInstance);

    void attachDirty(DsdIdentifier instance);

    void attachClean(DsdIdentifier instance);

    void save(DsdIdentifier transientInstance);

    void delete(DsdIdentifier persistentInstance);

    List<DsdIdentifier> findByExample(DsdIdentifier instance);

    DsdIdentifier findById(Long registerId);

    long findSize();

    List<DsdIdentifier> findByIds(Set<Long> registerIds);
}
