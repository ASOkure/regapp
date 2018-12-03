package uk.ac.nesc.idsd.model;

import org.apache.commons.lang3.StringUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "dsd_search_criteria", catalog = "idsd")
public class DsdSearchCriteria implements java.io.Serializable {
    private Long searchId;
    private Long registerId;
    private String country;
    private String centre;
    private String localId;
    private Integer yob;
    private Double birthWeight;
    private Double birthLength;
    private String aofp;
    private String karyotype;
    private String disorderType;
    private String actualDiagnosis;
    private String sexAssigned;
    private String currentGender;
    private String assocMalforms;
    private String dnaAnalysis;
    private String clinicalInfo;
    private String caseNotes;
    private String growthData;
    private String pubertyData;
    private String dna;
    private String tissue;
    private String cellLine;
    private String cellLineInfo;
    private String urine;
    private String serum;
    private String sampleShared;
    private String infertilityHistory;
    private String sampleAvailability;
    private String clinicalFeatures;
    private String biochemistry;
    private String parentalConsanguinity;
    private String dsdHistory;

    private Date firstAssessmentDate;
    private Float firstPhallusLength;
    private String firstPhallusSize;
    private String firstUrinaryMeatus;
    private String firstLabioscrotalFusion;
    private String firstRightGonad;
    private String firstLeftGonad;
    private String firstMullerianStructure;
    private String firstWolffianStructure;
    private Float firstEms;
    private String firstModifiedPrader;
    private String firstTannerStage;
    private String firstGonadectomy;
    private String firstGestation;

    private Date lastAssessmentDate;
    private Float lastPhallusLength;
    private String lastPhallusSize;
    private String lastUrinaryMeatus;
    private String lastLabioscrotalFusion;
    private String lastRightGonad;
    private String lastLeftGonad;
    private String lastMullerianStructure;
    private String lastWolffianStructure;
    private Float lastEms;
    private String lastModifiedPrader;
    private String lastTannerStage;
    private String lastGonadectomy;
    private String lastGestation;

    private String geneticAnalysis;
    private String singleGeneAnalysis;
    private String chromosomalRearrangement;
    private String cgh;
    private String functionalStudy;
    private String publishedCase;
    private String furtherStudies;
    private String selectedGeneNames;

    private String prenatalDiagnosis;
    private String prenatalDexamethasoneTherapy;
    private String gestationalAge;
    private Double fatherHeight;
    private Double motherHeight;
    private Double targetHeight;
    private String praderStageAtFirstPresentation;
    private String swCrisisAtPresentation;
    private String adrenalCrisesAfterFirstPresentation;
    private String currentFcTreatment;
    private String currentSaltReplacement;

    private String selectedParameterIds;

    private Timestamp requestTime;
    private Timestamp respondTime;

    private String requesterId;
    private String responderId;

    private Integer consent;
    private Boolean sampleConsent;

    private Boolean completed;
    private Boolean deleted;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "search_id", unique = true, nullable = false)
    public Long getSearchId() {
        return searchId;
    }

    public void setSearchId(Long searchId) {
        this.searchId = searchId;
    }

    @Column(name = "register_id")
    public Long getRegisterId() {
        return this.registerId;
    }

    public void setRegisterId(Long registerId) {
        this.registerId = registerId;
    }

    @Column(name = "country", length = 100)
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Column(name = "centre", length = 100)
    public String getCentre() {
        return centre;
    }

    public void setCentre(String centre) {
        this.centre = centre;
    }

    @Column(name = "local_id", length = 200)
    public String getLocalId() {
        return this.localId;
    }

    public void setLocalId(String localId) {
        this.localId = localId;
    }

    @Column(name = "yob")
    public Integer getYob() {
        return this.yob;
    }

    public void setYob(Integer yob) {
        this.yob = yob;
    }

    @Column(name = "birth_weight", precision = 12, scale = 2)
    public Double getBirthWeight() {
        return birthWeight;
    }

    public void setBirthWeight(Double birthWeight) {
        this.birthWeight = birthWeight;
    }

    @Column(name = "aofp", length = 100)
    public String getAofp() {
        return this.aofp;
    }

    public void setAofp(String aofp) {
        this.aofp = aofp;
    }

    @Column(name = "karyotype", length = 100)
    public String getKaryotype() {
        return this.karyotype;
    }

    public void setKaryotype(String karyotype) {
        this.karyotype = karyotype;
    }

    @Column(name = "disorder_type", length = 100)
    public String getDisorderType() {
        return this.disorderType;
    }

    public void setDisorderType(String disorderType) {
        this.disorderType = disorderType;
    }

    @Column(name = "actual_diagnosis", length = 100)
    public String getActualDiagnosis() {
        return this.actualDiagnosis;
    }

    public void setActualDiagnosis(String actualDiagnosis) {
        this.actualDiagnosis = actualDiagnosis;
    }

    @Column(name = "sex_assigned", length = 20)
    public String getSexAssigned() {
        return this.sexAssigned;
    }

    public void setSexAssigned(String sexAssigned) {
        this.sexAssigned = sexAssigned;
    }

    @Column(name = "current_gender", length = 20)
    public String getCurrentGender() {
        return currentGender;
    }

    public void setCurrentGender(String currentGender) {
        this.currentGender = currentGender;
    }

    @Column(name = "assoc_malforms", length = 100)
    public String getAssocMalforms() {
        return this.assocMalforms;
    }

    public void setAssocMalforms(String assocMalforms) {
        this.assocMalforms = assocMalforms;
    }

    @Column(name = "dna_analysis", length = 100)
    public String getDnaAnalysis() {
        return this.dnaAnalysis;
    }

    public void setDnaAnalysis(String dnaAnalysis) {
        this.dnaAnalysis = dnaAnalysis;
    }

    @Column(name = "clinical_info", length = 100)
    public String getClinicalInfo() {
        return this.clinicalInfo;
    }

    public void setClinicalInfo(String clinicalInfo) {
        this.clinicalInfo = clinicalInfo;
    }

    @Column(name = "case_notes", length = 100)
    public String getCaseNotes() {
        return this.caseNotes;
    }

    public void setCaseNotes(String caseNotes) {
        this.caseNotes = caseNotes;
    }

    @Column(name = "growth_data", length = 100)
    public String getGrowthData() {
        return this.growthData;
    }

    public void setGrowthData(String growthData) {
        this.growthData = growthData;
    }

    @Column(name = "puberty_data", length = 100)
    public String getPubertyData() {
        return this.pubertyData;
    }

    public void setPubertyData(String pubertyData) {
        this.pubertyData = pubertyData;
    }

    @Column(name = "dna", length = 100)
    public String getDna() {
        return this.dna;
    }

    public void setDna(String dna) {
        this.dna = dna;
    }

    @Column(name = "tissue", length = 100)
    public String getTissue() {
        return this.tissue;
    }

    public void setTissue(String tissue) {
        this.tissue = tissue;
    }

    @Column(name = "cell_line", length = 100)
    public String getCellLine() {
        return this.cellLine;
    }

    public void setCellLine(String cellLine) {
        this.cellLine = cellLine;
    }

    @Column(name = "cell_line_info", length = 100)
    public String getCellLineInfo() {
        return this.cellLineInfo;
    }

    public void setCellLineInfo(String cellLineInfo) {
        this.cellLineInfo = cellLineInfo;
    }

    @Column(name = "urine", length = 100)
    public String getUrine() {
        return this.urine;
    }

    public void setUrine(String urine) {
        this.urine = urine;
    }

    @Column(name = "serum", length = 100)
    public String getSerum() {
        return this.serum;
    }

    public void setSerum(String serum) {
        this.serum = serum;
    }

    @Column(name = "sample_shared", length = 10)
    public String getSampleShared() {
        return this.sampleShared;
    }

    public void setSampleShared(String sampleShared) {
        this.sampleShared = sampleShared;
    }

    @Column(name = "infertility_history", length = 100)
    public String getInfertilityHistory() {
        return this.infertilityHistory;
    }

    public void setInfertilityHistory(String infertilityHistory) {
        this.infertilityHistory = infertilityHistory;
    }

    @Column(name = "sample_availability", length = 100)
    public String getSampleAvailability() {
        return this.sampleAvailability;
    }

    public void setSampleAvailability(String sampleAvailability) {
        this.sampleAvailability = sampleAvailability;
    }

    @Column(name = "clinical_features", length = 100)
    public String getClinicalFeatures() {
        return this.clinicalFeatures;
    }

    public void setClinicalFeatures(String clinicalFeatures) {
        this.clinicalFeatures = clinicalFeatures;
    }

    @Column(name = "biochemistry", length = 100)
    public String getBiochemistry() {
        return this.biochemistry;
    }

    public void setBiochemistry(String biochemistry) {
        this.biochemistry = biochemistry;
    }

    @Column(name = "parental_consanguinity", length = 100)
    public String getParentalConsanguinity() {
        return this.parentalConsanguinity;
    }

    public void setParentalConsanguinity(String parentalConsanguinity) {
        this.parentalConsanguinity = parentalConsanguinity;
    }

    @Column(name = "consent")
    public Integer getConsent() {
        return this.consent;
    }

    public void setConsent(Integer consent) {
        this.consent = consent;
    }

    @Column(name = "sample_consent")
    public Boolean getSampleConsent() {
        return sampleConsent;
    }

    public void setSampleConsent(Boolean sampleConsent) {
        this.sampleConsent = sampleConsent;
    }

    @Column(name = "request_time", length = 19)
    public Timestamp getRequestTime() {
        return this.requestTime;
    }

    public void setRequestTime(Timestamp requestTime) {
        this.requestTime = requestTime;
    }

    @Column(name = "respond_time", length = 19)
    public Timestamp getRespondTime() {
        return this.respondTime;
    }

    public void setRespondTime(Timestamp respondTime) {
        this.respondTime = respondTime;
    }

    @Column(name = "dsd_history", length = 100)
    public String getDsdHistory() {
        return this.dsdHistory;
    }

    public void setDsdHistory(String dsdHistory) {
        this.dsdHistory = dsdHistory;
    }

    @Column(name = "birth_length", precision = 12, scale = 2)
    public Double getBirthLength() {
        return this.birthLength;
    }

    public void setBirthLength(Double birthLength) {
        this.birthLength = birthLength;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "first_assessment_date", length = 10)
    public Date getFirstAssessmentDate() {
        return this.firstAssessmentDate;
    }

    public void setFirstAssessmentDate(Date firstAssessmentDate) {
        this.firstAssessmentDate = firstAssessmentDate;
    }

    @Column(name = "first_phallus_length", precision = 12, scale = 0)
    public Float getFirstPhallusLength() {
        return this.firstPhallusLength;
    }

    public void setFirstPhallusLength(Float firstPhallusLength) {
        this.firstPhallusLength = firstPhallusLength;
    }

    @Column(name = "first_phallus_size", length = 100)
    public String getFirstPhallusSize() {
        return this.firstPhallusSize;
    }

    public void setFirstPhallusSize(String firstPhallusSize) {
        this.firstPhallusSize = firstPhallusSize;
    }

    @Column(name = "first_urinary_meatus", length = 100)
    public String getFirstUrinaryMeatus() {
        return this.firstUrinaryMeatus;
    }

    public void setFirstUrinaryMeatus(String firstUrinaryMeatus) {
        this.firstUrinaryMeatus = firstUrinaryMeatus;
    }

    @Column(name = "first_labioscrotal_fusion", length = 100)
    public String getFirstLabioscrotalFusion() {
        return this.firstLabioscrotalFusion;
    }

    public void setFirstLabioscrotalFusion(String firstLabioscrotalFusion) {
        this.firstLabioscrotalFusion = firstLabioscrotalFusion;
    }

    @Column(name = "first_right_gonad", length = 100)
    public String getFirstRightGonad() {
        return this.firstRightGonad;
    }

    public void setFirstRightGonad(String firstRightGonad) {
        this.firstRightGonad = firstRightGonad;
    }

    @Column(name = "first_left_gonad", length = 100)
    public String getFirstLeftGonad() {
        return this.firstLeftGonad;
    }

    public void setFirstLeftGonad(String firstLeftGonad) {
        this.firstLeftGonad = firstLeftGonad;
    }

    @Column(name = "first_mullerian_structure", length = 100)
    public String getFirstMullerianStructure() {
        return this.firstMullerianStructure;
    }

    public void setFirstMullerianStructure(String firstMullerianStructure) {
        this.firstMullerianStructure = firstMullerianStructure;
    }

    @Column(name = "first_wolffian_structure", length = 100)
    public String getFirstWolffianStructure() {
        return this.firstWolffianStructure;
    }

    public void setFirstWolffianStructure(String firstWolffianStructure) {
        this.firstWolffianStructure = firstWolffianStructure;
    }

    @Column(name = "first_ems", precision = 12, scale = 0)
    public Float getFirstEms() {
        return this.firstEms;
    }

    public void setFirstEms(Float firstEms) {
        this.firstEms = firstEms;
    }

    @Column(name = "first_modified_prader", length = 100)
    public String getFirstModifiedPrader() {
        return this.firstModifiedPrader;
    }

    public void setFirstModifiedPrader(String firstModifiedPrader) {
        this.firstModifiedPrader = firstModifiedPrader;
    }

    @Column(name = "first_tanner_stage", length = 10)
    public String getFirstTannerStage() {
        return this.firstTannerStage;
    }

    public void setFirstTannerStage(String firstTannerStage) {
        this.firstTannerStage = firstTannerStage;
    }

    @Column(name = "first_gonadectomy", length = 100)
    public String getFirstGonadectomy() {
        return this.firstGonadectomy;
    }

    public void setFirstGonadectomy(String firstGonadectomy) {
        this.firstGonadectomy = firstGonadectomy;
    }

    @Column(name = "first_gestation", length = 100)
    public String getFirstGestation() {
        return this.firstGestation;
    }

    public void setFirstGestation(String firstGestation) {
        this.firstGestation = firstGestation;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "last_assessment_date", length = 10)
    public Date getLastAssessmentDate() {
        return this.lastAssessmentDate;
    }

    public void setLastAssessmentDate(Date lastAssessmentDate) {
        this.lastAssessmentDate = lastAssessmentDate;
    }

    @Column(name = "last_phallus_length", precision = 12, scale = 0)
    public Float getLastPhallusLength() {
        return this.lastPhallusLength;
    }

    public void setLastPhallusLength(Float lastPhallusLength) {
        this.lastPhallusLength = lastPhallusLength;
    }

    @Column(name = "last_phallus_size", length = 100)
    public String getLastPhallusSize() {
        return this.lastPhallusSize;
    }

    public void setLastPhallusSize(String lastPhallusSize) {
        this.lastPhallusSize = lastPhallusSize;
    }

    @Column(name = "last_urinary_meatus", length = 100)
    public String getLastUrinaryMeatus() {
        return this.lastUrinaryMeatus;
    }

    public void setLastUrinaryMeatus(String lastUrinaryMeatus) {
        this.lastUrinaryMeatus = lastUrinaryMeatus;
    }

    @Column(name = "last_labioscrotal_fusion", length = 100)
    public String getLastLabioscrotalFusion() {
        return this.lastLabioscrotalFusion;
    }

    public void setLastLabioscrotalFusion(String lastLabioscrotalFusion) {
        this.lastLabioscrotalFusion = lastLabioscrotalFusion;
    }

    @Column(name = "last_right_gonad", length = 100)
    public String getLastRightGonad() {
        return this.lastRightGonad;
    }

    public void setLastRightGonad(String lastRightGonad) {
        this.lastRightGonad = lastRightGonad;
    }

    @Column(name = "last_left_gonad", length = 100)
    public String getLastLeftGonad() {
        return this.lastLeftGonad;
    }

    public void setLastLeftGonad(String lastLeftGonad) {
        this.lastLeftGonad = lastLeftGonad;
    }

    @Column(name = "last_mullerian_structure", length = 100)
    public String getLastMullerianStructure() {
        return this.lastMullerianStructure;
    }

    public void setLastMullerianStructure(String lastMullerianStructure) {
        this.lastMullerianStructure = lastMullerianStructure;
    }

    @Column(name = "last_wolffian_structure", length = 100)
    public String getLastWolffianStructure() {
        return this.lastWolffianStructure;
    }

    public void setLastWolffianStructure(String lastWolffianStructure) {
        this.lastWolffianStructure = lastWolffianStructure;
    }

    @Column(name = "last_ems", precision = 12, scale = 0)
    public Float getLastEms() {
        return this.lastEms;
    }

    public void setLastEms(Float lastEms) {
        this.lastEms = lastEms;
    }

    @Column(name = "last_modified_prader", length = 100)
    public String getLastModifiedPrader() {
        return this.lastModifiedPrader;
    }

    public void setLastModifiedPrader(String lastModifiedPrader) {
        this.lastModifiedPrader = lastModifiedPrader;
    }

    @Column(name = "last_tanner_stage", length = 10)
    public String getLastTannerStage() {
        return this.lastTannerStage;
    }

    public void setLastTannerStage(String lastTannerStage) {
        this.lastTannerStage = lastTannerStage;
    }

    @Column(name = "last_gonadectomy", length = 100)
    public String getLastGonadectomy() {
        return this.lastGonadectomy;
    }

    public void setLastGonadectomy(String lastGonadectomy) {
        this.lastGonadectomy = lastGonadectomy;
    }

    @Column(name = "last_gestation", length = 100)
    public String getLastGestation() {
        return this.lastGestation;
    }

    public void setLastGestation(String lastGestation) {
        this.lastGestation = lastGestation;
    }


    @Column(name = "genetic_analysis", length = 10)
    public String getGeneticAnalysis() {
        return this.geneticAnalysis;
    }

    public void setGeneticAnalysis(String geneticAnalysis) {
        this.geneticAnalysis = geneticAnalysis;
    }

    @Column(name = "single_gene_analysis", length = 10)
    public String getSingleGeneAnalysis() {
        return this.singleGeneAnalysis;
    }

    public void setSingleGeneAnalysis(String singleGeneAnalysis) {
        this.singleGeneAnalysis = singleGeneAnalysis;
    }

    @Column(name = "chromosomal_rearrangement", length = 10)
    public String getChromosomalRearrangement() {
        return this.chromosomalRearrangement;
    }

    public void setChromosomalRearrangement(String chromosomalRearrangement) {
        this.chromosomalRearrangement = chromosomalRearrangement;
    }

    @Column(name = "cgh", length = 10)
    public String getCgh() {
        return this.cgh;
    }

    public void setCgh(String cgh) {
        this.cgh = cgh;
    }

    @Column(name = "functional_study", length = 10)
    public String getFunctionalStudy() {
        return this.functionalStudy;
    }

    public void setFunctionalStudy(String functionalStudy) {
        this.functionalStudy = functionalStudy;
    }

    @Column(name = "published_case", length = 10)
    public String getPublishedCase() {
        return this.publishedCase;
    }

    public void setPublishedCase(String publishedCase) {
        this.publishedCase = publishedCase;
    }

    @Column(name = "further_studies", length = 100)
    public String getFurtherStudies() {
        return this.furtherStudies;
    }

    public void setFurtherStudies(String furtherStudies) {
        this.furtherStudies = furtherStudies;
    }

    @Column(name = "selected_gene_names")
    public String getSelectedGeneNames() {
        return selectedGeneNames;
    }

    public void setSelectedGeneNames(String selectedGeneNames) {
        this.selectedGeneNames = selectedGeneNames;
    }

    @Column(name = "prenatal_diagnosis", length = 45)
    public String getPrenatalDiagnosis() {
        return prenatalDiagnosis;
    }

    public void setPrenatalDiagnosis(String prenatalDiagnosis) {
        this.prenatalDiagnosis = prenatalDiagnosis;
    }

    @Column(name = "prenatal_dexamethasone_therapy", length = 45)
    public String getPrenatalDexamethasoneTherapy() {
        return prenatalDexamethasoneTherapy;
    }

    public void setPrenatalDexamethasoneTherapy(String prenatalDexamethasoneTherapy) {
        this.prenatalDexamethasoneTherapy = prenatalDexamethasoneTherapy;
    }

    @Column(name = "gestational_age", length = 100)
    public String getGestationalAge() {
        return this.gestationalAge;
    }

    public void setGestationalAge(String gestationalAge) {
        this.gestationalAge = gestationalAge;
    }

    @Column(name = "father_height", precision = 12, scale = 2)
    public Double getFatherHeight() {
        return fatherHeight;
    }

    public void setFatherHeight(Double fatherHeight) {
        this.fatherHeight = fatherHeight;
    }

    @Column(name = "mother_height", precision = 12, scale = 2)
    public Double getMotherHeight() {
        return motherHeight;
    }

    public void setMotherHeight(Double motherHeight) {
        this.motherHeight = motherHeight;
    }

    @Column(name = "target_height", precision = 12, scale = 2)
    public Double getTargetHeight() {
        return targetHeight;
    }

    public void setTargetHeight(Double targetHeight) {
        this.targetHeight = targetHeight;
    }

    @Column(name = "prader_stage_at_first_presentation", length = 45)
    public String getPraderStageAtFirstPresentation() {
        return this.praderStageAtFirstPresentation;
    }

    public void setPraderStageAtFirstPresentation(
            String praderStageAtFirstPresentation) {
        this.praderStageAtFirstPresentation = praderStageAtFirstPresentation;
    }

    @Column(name = "sw_crisis_at_presentation", length = 45)
    public String getSwCrisisAtPresentation() {
        return this.swCrisisAtPresentation;
    }

    public void setSwCrisisAtPresentation(String swCrisisAtPresentation) {
        this.swCrisisAtPresentation = swCrisisAtPresentation;
    }

    @Column(name = "adrenal_crises_after_first_presentation", length = 45)
    public String getAdrenalCrisesAfterFirstPresentation() {
        return this.adrenalCrisesAfterFirstPresentation;
    }

    public void setAdrenalCrisesAfterFirstPresentation(
            String adrenalCrisesAfterFirstPresentation) {
        this.adrenalCrisesAfterFirstPresentation = adrenalCrisesAfterFirstPresentation;
    }

    @Column(name = "current_fc_treatment", length = 45)
    public String getCurrentFcTreatment() {
        return this.currentFcTreatment;
    }

    public void setCurrentFcTreatment(String currentFcTreatment) {
        this.currentFcTreatment = currentFcTreatment;
    }

    @Column(name = "current_salt_replacement", length = 45)
    public String getCurrentSaltReplacement() {
        return this.currentSaltReplacement;
    }

    public void setCurrentSaltReplacement(String currentSaltReplacement) {
        this.currentSaltReplacement = currentSaltReplacement;
    }

    @Column(name = "selected_parameter_ids")
    public String getSelectedParameterIds() {
        return selectedParameterIds;
    }

    public void setSelectedParameterIds(String selectedParameterIds) {
        this.selectedParameterIds = selectedParameterIds;
    }

    @Column(name = "requester_id")
    public String getRequesterId() {
        return requesterId;
    }

    public void setRequesterId(String requesterId) {
        this.requesterId = requesterId;
    }

    @Column(name = "responder_id")
    public String getResponderId() {
        return responderId;
    }

    public void setResponderId(String responderId) {
        this.responderId = responderId;
    }

    @Column(name = "completed")
    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    @Column(name = "deleted")
    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    @Transient
    public DsdIdentifier getDsdIdentifier() {
        DsdIdentifier dsdIdentifier = new DsdIdentifier();
        if (getRegisterId() != null) {
            dsdIdentifier.setRegisterId(dsdIdentifier.getRegisterId());
        }
        if (StringUtils.isNotBlank(getCentre())) {
            dsdIdentifier.setCentreName(getCentre());
        }
        if (StringUtils.isNotBlank(getCountry())) {
            dsdIdentifier.setCountryName(getCountry());
        }
        if (StringUtils.isNotBlank(getLocalId())) {
            dsdIdentifier.setLocalId(getLocalId().trim());
        }
        if (getYob() != null) {
            dsdIdentifier.setYob(getYob());
        }
        if (StringUtils.isNotBlank(getAofp())) {
            dsdIdentifier.setAofp(getAofp().trim());
        }
        if (StringUtils.isNotBlank(getSexAssigned())) {
            dsdIdentifier.setSexAssigned(getSexAssigned().trim());
        }
        if (StringUtils.isNotBlank(getCurrentGender())) {
            dsdIdentifier.setCurrentGender(getCurrentGender().trim());
        }
        if (StringUtils.isNotBlank(getKaryotype())) {
            dsdIdentifier.setKaryotype(getKaryotype().trim());
        }
        if (StringUtils.isNotBlank(getDisorderType())) {
            dsdIdentifier.setDisorderType(getDisorderType().trim());
        }
        if (StringUtils.isNotBlank(getActualDiagnosis())) {
            dsdIdentifier.setActualDiagnosis(getActualDiagnosis().trim());
        }
        if (getBirthWeight() != null) {
            dsdIdentifier.setBirthWeight(getBirthWeight());
        }
        if (getBirthLength() != null) {
            dsdIdentifier.setBirthLength(getBirthLength());
        }
        if (StringUtils.isNotBlank(getAssocMalforms())) {
            dsdIdentifier.setAssocMalforms(getAssocMalforms().trim());
        }
        if (StringUtils.isNotBlank(getClinicalFeatures())) {
            dsdIdentifier.setClinicalFeatures(getClinicalFeatures().trim());
        }
        if (StringUtils.isNotBlank(getBiochemistry())) {
            dsdIdentifier.setBiochemistry(getBiochemistry().trim());
        }
        if (StringUtils.isNotBlank(getDnaAnalysis())) {
            dsdIdentifier.setDnaAnalysis(getDnaAnalysis().trim());
        }
        if (StringUtils.isNotBlank(getParentalConsanguinity())) {
            dsdIdentifier.setParentalConsanguinity(getParentalConsanguinity().trim());
        }
        if (StringUtils.isNotBlank(getDsdHistory())) {
            dsdIdentifier.setDsdHistory(getDsdHistory().trim());
        }
        if (StringUtils.isNotBlank(getInfertilityHistory())) {
            dsdIdentifier.setInfertilityHistory(getInfertilityHistory().trim());
        }
        if (StringUtils.isNotBlank(getSampleAvailability())) {
            dsdIdentifier.setSampleAvailability(getSampleAvailability().trim());
        }
        if (StringUtils.isNotBlank(getClinicalInfo())) {
            dsdIdentifier.setClinicalInfo(getClinicalInfo().trim());
        }
        if (StringUtils.isNotBlank(getCaseNotes())) {
            dsdIdentifier.setCaseNotes(getCaseNotes().trim());
        }
        if (StringUtils.isNotBlank(getGrowthData())) {
            dsdIdentifier.setGrowthData(getGrowthData().trim());
        }
        if (StringUtils.isNotBlank(getPubertyData())) {
            dsdIdentifier.setPubertyData(getPubertyData().trim());
        }
        if (StringUtils.isNotBlank(getDna())) {
            dsdIdentifier.setDna(getDna().trim());
        }
        if (StringUtils.isNotBlank(getTissue())) {
            dsdIdentifier.setTissue(getTissue().trim());
        }
        if (StringUtils.isNotBlank(getCellLine())) {
            dsdIdentifier.setCellLine(getCellLine().trim());
        }
        if (StringUtils.isNotBlank(getCellLineInfo())) {
            dsdIdentifier.setCellLineInfo(getCellLineInfo().trim());
        }
        if (StringUtils.isNotBlank(getUrine())) {
            dsdIdentifier.setUrine(getUrine().trim());
        }
        if (StringUtils.isNotBlank(getSerum())) {
            dsdIdentifier.setSerum(getSerum().trim());
        }
        if (StringUtils.isNotBlank(getSampleShared())) {
            dsdIdentifier.setSampleShared(getSampleShared().trim());
        }
        if (getConsent() != null) {
            dsdIdentifier.setConsent(getConsent());
        }
        if (getSampleConsent() != null) {
            dsdIdentifier.setSampleConsent(getSampleConsent());
        }
        dsdIdentifier.setDsdGeneAnalysis(getDsdGeneAnalysis());
        dsdIdentifier.setDsdCah(getDsdCah());
        return dsdIdentifier;
    }

    @Transient
    public DsdAssessment getFirstDsdAssessment() {
        DsdAssessment firstDsdAssessment = new DsdAssessment();
        if (getFirstAssessmentDate() != null) {
            firstDsdAssessment.setAssessmentDate(getFirstAssessmentDate());
        }
        if (getFirstPhallusLength() != null) {
            firstDsdAssessment.setPhallusLength(getFirstPhallusLength());
        }
        if (StringUtils.isNotBlank(getFirstPhallusSize())) {
            firstDsdAssessment.setPhallusSize(getFirstPhallusSize());
        }
        if (StringUtils.isNotBlank(getFirstUrinaryMeatus())) {
            firstDsdAssessment.setUrinaryMeatus(getFirstUrinaryMeatus());
        }
        if (StringUtils.isNotBlank(getFirstLabioscrotalFusion())) {
            firstDsdAssessment.setLabioscrotalFusion(getFirstLabioscrotalFusion());
        }
        if (StringUtils.isNotBlank(getFirstRightGonad())) {
            firstDsdAssessment.setRightGonad(getFirstRightGonad());
        }
        if (StringUtils.isNotBlank(getFirstLeftGonad())) {
            firstDsdAssessment.setLeftGonad(getFirstLeftGonad());
        }
        if (StringUtils.isNotBlank(getFirstMullerianStructure())) {
            firstDsdAssessment.setMullerianStructure(getFirstMullerianStructure());
        }
        if (StringUtils.isNotBlank(getFirstWolffianStructure())) {
            firstDsdAssessment.setWolffianStructure(getFirstWolffianStructure());
        }
        if (getFirstEms() != null) {
            firstDsdAssessment.setEms(getFirstEms());
        }
        if (StringUtils.isNotBlank(getFirstModifiedPrader())) {
            firstDsdAssessment.setModifiedPrader(getFirstModifiedPrader());
        }
        if (StringUtils.isNotBlank(getFirstTannerStage())) {
            firstDsdAssessment.setTannerStage(getFirstTannerStage());
        }
        if (StringUtils.isNotBlank(getFirstGonadectomy())) {
            firstDsdAssessment.setTannerStage(getFirstTannerStage());
        }
        return firstDsdAssessment;
    }

    @Transient
    public DsdAssessment getLastDsdAssessment() {
        DsdAssessment lastDsdAssessment = new DsdAssessment();
        if (getLastAssessmentDate() != null) {
            lastDsdAssessment.setAssessmentDate(getLastAssessmentDate());
        }
        if (getLastPhallusLength() != null) {
            lastDsdAssessment.setPhallusLength(getLastPhallusLength());
        }
        if (StringUtils.isNotBlank(getLastPhallusSize())) {
            lastDsdAssessment.setPhallusSize(getLastPhallusSize());
        }
        if (StringUtils.isNotBlank(getLastUrinaryMeatus())) {
            lastDsdAssessment.setUrinaryMeatus(getLastUrinaryMeatus());
        }
        if (StringUtils.isNotBlank(getLastLabioscrotalFusion())) {
            lastDsdAssessment.setLabioscrotalFusion(getLastLabioscrotalFusion());
        }
        if (StringUtils.isNotBlank(getLastRightGonad())) {
            lastDsdAssessment.setRightGonad(getLastRightGonad());
        }
        if (StringUtils.isNotBlank(getLastLeftGonad())) {
            lastDsdAssessment.setLeftGonad(getLastLeftGonad());
        }
        if (StringUtils.isNotBlank(getLastMullerianStructure())) {
            lastDsdAssessment.setMullerianStructure(getLastMullerianStructure());
        }
        if (StringUtils.isNotBlank(getLastWolffianStructure())) {
            lastDsdAssessment.setWolffianStructure(getLastWolffianStructure());
        }
        if (getLastEms() != null) {
            lastDsdAssessment.setEms(getLastEms());
        }
        if (StringUtils.isNotBlank(getLastModifiedPrader())) {
            lastDsdAssessment.setModifiedPrader(getLastModifiedPrader());
        }
        if (StringUtils.isNotBlank(getLastTannerStage())) {
            lastDsdAssessment.setTannerStage(getLastTannerStage());
        }
        if (StringUtils.isNotBlank(getLastGonadectomy())) {
            lastDsdAssessment.setTannerStage(getLastTannerStage());
        }
        return lastDsdAssessment;
    }

    @Transient
    public DsdGeneAnalysis getDsdGeneAnalysis() {
        DsdGeneAnalysis dsdGeneAnalysis = new DsdGeneAnalysis();
        if (StringUtils.isNotBlank(getSingleGeneAnalysis())) {
            dsdGeneAnalysis.setSingleGeneAnalysis(getSingleGeneAnalysis());
        }
        if (StringUtils.isNotBlank(getChromosomalRearrangement())) {
            dsdGeneAnalysis.setChromosomalRearrangement(getChromosomalRearrangement());
        }
        if (StringUtils.isNotBlank(getCgh())) {
            dsdGeneAnalysis.setCgh(getCgh());
        }
        if (StringUtils.isNotBlank(getFunctionalStudy())) {
            dsdGeneAnalysis.setFunctionalStudy(getFunctionalStudy());
        }
        if (StringUtils.isNotBlank(getFurtherStudies())) {
            dsdGeneAnalysis.setFurtherStudies(getFurtherStudies());
        }
        return dsdGeneAnalysis;
    }

    @Transient
    public DsdCah getDsdCah() {
        DsdCah dsdCah = new DsdCah();
        if (StringUtils.isNotBlank(getPrenatalDiagnosis())) {
            dsdCah.setPrenatalDiagnosis(getPrenatalDiagnosis());
        }
        if (StringUtils.isNotBlank(getPrenatalDexamethasoneTherapy())) {
            dsdCah.setPrenatalDexamethasoneTherapy(getPrenatalDexamethasoneTherapy());
        }
        if (StringUtils.isNotBlank(getGestationalAge())) {
            dsdCah.setGestationalAge(getGestationalAge());
        }
        if (getFatherHeight() != null) {
            dsdCah.setFatherHeight(getFatherHeight());
        }
        if (getMotherHeight() != null) {
            dsdCah.setMotherHeight(getMotherHeight());
        }
        if (getTargetHeight() != null) {
            dsdCah.setTargetHeight(getTargetHeight());
        }
        if (StringUtils.isNotBlank(getPraderStageAtFirstPresentation())) {
            dsdCah.setPraderStageAtFirstPresentation(getPraderStageAtFirstPresentation());
        }
        if (StringUtils.isNotBlank(getSwCrisisAtPresentation())) {
            dsdCah.setSwCrisisAtPresentation(getSwCrisisAtPresentation());
        }
        if (StringUtils.isNotBlank(getAdrenalCrisesAfterFirstPresentation())) {
            dsdCah.setAdrenalCrisesAfterFirstPresentation(getAdrenalCrisesAfterFirstPresentation());
        }
        if (StringUtils.isNotBlank(getCurrentFcTreatment())) {
            dsdCah.setCurrentFcTreatment(getCurrentFcTreatment());
        }
        if (StringUtils.isNotBlank(getCurrentSaltReplacement())) {
            dsdCah.setCurrentSaltReplacement(getCurrentSaltReplacement());
        }
        return dsdCah;
    }

    @Override
    public String toString() {
        if (this == null) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        if (null != this.getRegisterId()) {
            stringBuilder.append("registerId=").append(this.getRegisterId()).append(";");
        }
        if (null != this.getCountry() && !this.getCountry().isEmpty()) {
            stringBuilder.append("country=").append(this.getCountry()).append(";");
        }
        if (null != this.getCentre()) {
            stringBuilder.append("centre=").append(this.getCentre()).append(";");
        }
        if (null != this.getLocalId()) {
            stringBuilder.append("localId=").append(this.getLocalId()).append(";");
        }
        if (null != this.getYob()) {
            stringBuilder.append("yob=").append(this.getYob()).append(";");
        }
        if (null != this.getAofp()) {
            stringBuilder.append("aofp=").append(this.getAofp()).append(";");
        }
        if (null != this.getBirthWeight()) {
            stringBuilder.append("birthWeight=").append(this.getBirthWeight()).append(";");
        }
        if (null != this.getSexAssigned()) {
            stringBuilder.append("sexAssigned=").append(this.getSexAssigned()).append(";");
        }
        if (null != this.getCurrentGender()) {
            stringBuilder.append("currentGender=").append(this.getCurrentGender()).append(";");
        }
        if (null != this.getKaryotype()) {
            stringBuilder.append("karyotype=").append(this.getKaryotype()).append(";");
        }
        if (null != this.getActualDiagnosis()) {
            stringBuilder.append("actualDiagnosis=").append(this.getActualDiagnosis()).append(";");
        }
        if (null != this.getDisorderType()) {
            stringBuilder.append("disorderType=").append(this.getDisorderType()).append(";");
        }
        if (null != this.getAssocMalforms()) {
            stringBuilder.append("assocMalforms= ").append(this.getAssocMalforms()).append(";");
        }
        if (null != this.getClinicalFeatures()) {
            stringBuilder.append("clinicalFeatures=").append(this.getClinicalFeatures()).append(";");
        }
        if (null != this.getBiochemistry()) {
            stringBuilder.append("biochemistry=").append(this.getBiochemistry()).append(";");
        }
        if (null != this.getDnaAnalysis()) {
            stringBuilder.append("dnaAnalysis=").append(this.getDnaAnalysis()).append(";");
        }
        if (null != this.getParentalConsanguinity()) {
            stringBuilder.append("parentalConsanguinity=").append(this.getParentalConsanguinity()).append(";");
        }
        if (null != this.getDsdHistory()) {
            stringBuilder.append("dsdHistory=").append(this.getDsdHistory()).append(";");
        }
        if (null != this.getInfertilityHistory()) {
            stringBuilder.append("infertilityHistory=").append(this.getInfertilityHistory()).append(";");
        }
        if (null != this.getSampleAvailability()) {
            stringBuilder.append("aampleAvailability=").append(this.getSampleAvailability()).append(";");
        }
        if (null != this.getClinicalInfo()) {
            stringBuilder.append("clinicalInfo=").append(this.getClinicalInfo()).append(";");
        }
        if (null != this.getCaseNotes()) {
            stringBuilder.append("caseNote=").append(this.getCaseNotes()).append(";");
        }
        if (null != this.getGrowthData()) {
            stringBuilder.append("growthData=").append(this.getGrowthData()).append(";");
        }
        if (null != this.getPubertyData()) {
            stringBuilder.append("pubertyData=").append(this.getPubertyData()).append(";");
        }
        if (null != this.getDna()) {
            stringBuilder.append("dna=").append(this.getDna()).append(";");
        }
        if (null != this.getTissue()) {
            stringBuilder.append("tissue=").append(this.getTissue()).append(";");
        }
        if (null != this.getCellLine()) {
            stringBuilder.append("cellLine=").append(this.getCellLine()).append(";");
        }
        if (null != this.getCellLineInfo()) {
            stringBuilder.append("cellLineInfo=").append(this.getCellLineInfo()).append(";");
        }
        if (null != this.getUrine()) {
            stringBuilder.append("urine=").append(this.getUrine()).append(";");
        }
        if (null != this.getSerum()) {
            stringBuilder.append("serum=").append(this.getSerum()).append(";");
        }
        if (null != this.getSampleShared()) {
            stringBuilder.append("sampleShared=").append(this.getSampleShared()).append(";");
        }
        if (null != this.getFirstAssessmentDate()) {
            stringBuilder.append("firstAssessmentDate=").append(this.getFirstAssessmentDate()).append(";");
        }
        if (null != this.getFirstPhallusLength()) {
            stringBuilder.append("firstPhallusLength=").append(this.getFirstPhallusLength()).append(";");
        }
        if (null != this.getFirstPhallusSize()) {
            stringBuilder.append("firstPhallusSize=").append(this.getFirstPhallusSize()).append(";");
        }
        if (null != this.getFirstUrinaryMeatus()) {
            stringBuilder.append("firstUrinaryMeatus=").append(this.getFirstUrinaryMeatus()).append(";");
        }
        if (null != this.getFirstLabioscrotalFusion()) {
            stringBuilder.append("firstLabioscrotalFusion=").append(this.getFirstLabioscrotalFusion()).append(";");
        }
        if (null != this.getFirstRightGonad()) {
            stringBuilder.append("firstRightGonad=").append(this.getFirstRightGonad()).append(";");
        }
        if (null != this.getFirstLeftGonad()) {
            stringBuilder.append("firstLeftGonad=").append(this.getFirstLeftGonad()).append(";");
        }
        if (null != this.getFirstMullerianStructure()) {
            stringBuilder.append("firstMullerianStructure=").append(this.getFirstMullerianStructure()).append(";");
        }
        if (null != this.getFirstWolffianStructure()) {
            stringBuilder.append("firstWolffianStructure=").append(this.getFirstWolffianStructure()).append(";");
        }
        if (null != this.getFirstEms()) {
            stringBuilder.append("firstEms=").append(this.getFirstEms()).append(";");
        }
        if (null != this.getFirstModifiedPrader()) {
            stringBuilder.append("firstModifiedPrader=").append(this.getFirstModifiedPrader()).append(";");
        }
        if (null != this.getFirstTannerStage()) {
            stringBuilder.append("firstTannerStage=").append(this.getFirstTannerStage()).append(";");
        }
        if (null != this.getFirstGonadectomy()) {
            stringBuilder.append("firstGonadectomy=").append(this.getFirstGonadectomy()).append(";");
        }
        if (null != this.getFirstGestation()) {
            stringBuilder.append("firstGestation=").append(this.getFirstGestation()).append(";");
        }

        if (null != this.getLastAssessmentDate()) {
            stringBuilder.append("lastAssessmentDate=").append(this.getLastAssessmentDate()).append(";");
        }
        if (null != this.getLastPhallusLength()) {
            stringBuilder.append("lastPhallusLength=").append(this.getLastPhallusLength()).append(";");
        }
        if (null != this.getLastPhallusSize()) {
            stringBuilder.append("lastPhallusSize=").append(this.getLastPhallusSize()).append(";");
        }
        if (null != this.getLastUrinaryMeatus()) {
            stringBuilder.append("lastUrinaryMeatus=").append(this.getLastUrinaryMeatus()).append(";");
        }
        if (null != this.getLastLabioscrotalFusion()) {
            stringBuilder.append("lastLabioscrotalFusion=").append(this.getLastLabioscrotalFusion()).append(";");
        }
        if (null != this.getLastRightGonad()) {
            stringBuilder.append("lastRightGonad=").append(this.getLastRightGonad()).append(";");
        }
        if (null != this.getLastLeftGonad()) {
            stringBuilder.append("lastLeftGonad=").append(this.getLastLeftGonad()).append(";");
        }
        if (null != this.getLastMullerianStructure()) {
            stringBuilder.append("lastMullerianStructure=").append(this.getLastMullerianStructure()).append(";");
        }
        if (null != this.getLastWolffianStructure()) {
            stringBuilder.append("lastWolffianStructure=").append(this.getLastWolffianStructure()).append(";");
        }
        if (null != this.getLastEms()) {
            stringBuilder.append("lastEms=").append(this.getLastEms()).append(";");
        }
        if (null != this.getLastModifiedPrader()) {
            stringBuilder.append("lastModifiedPrader=").append(this.getLastModifiedPrader()).append(";");
        }
        if (null != this.getLastTannerStage()) {
            stringBuilder.append("lastTannerStage=").append(this.getLastTannerStage()).append(";");
        }
        if (null != this.getLastGonadectomy()) {
            stringBuilder.append("lastGonadectomy=").append(this.getLastGonadectomy()).append(";");
        }
        if (null != this.getLastGestation()) {
            stringBuilder.append("lastGestation=").append(this.getLastGestation()).append(";");
        }

        if (null != this.getSingleGeneAnalysis()) {
            stringBuilder.append("singleGeneAnalysis=").append(this.getSingleGeneAnalysis()).append(";");
        }
        if (null != this.getChromosomalRearrangement()) {
            stringBuilder.append("chromosomalRearrangement=").append(this.getChromosomalRearrangement()).append(";");
        }
        if (null != this.getCgh()) {
            stringBuilder.append("cgh=").append(this.getCgh()).append(";");
        }
        if (null != this.getFunctionalStudy()) {
            stringBuilder.append("functionalStudy=").append(this.getFunctionalStudy()).append(";");
        }
        if (null != this.getPublishedCase()) {
            stringBuilder.append("publishedCase=").append(this.getPublishedCase()).append(";");
        }
        if (null != this.getFurtherStudies()) {
            stringBuilder.append("furtherStudy=").append(this.getFurtherStudies()).append(";");
        }

        if (null != this.getSelectedGeneNames()) {
            stringBuilder.append("selectedGeneNames=").append(this.getSelectedGeneNames()).append(";");
        }
        if (null != this.getSelectedParameterIds()) {
            stringBuilder.append("selectedParameterIds=").append(this.getSelectedParameterIds()).append(";");
        }

        if (StringUtils.isNotBlank(this.getPrenatalDiagnosis())) {
            stringBuilder.append("prenatalDiagnosis=").append(this.getPrenatalDiagnosis()).append(";");
        }
        if (StringUtils.isNotBlank(this.getPrenatalDexamethasoneTherapy())) {
            stringBuilder.append("prenatalDexamethasoneTherapy=").append(this.getPrenatalDexamethasoneTherapy()).append(";");
        }
        if (StringUtils.isNotBlank(this.getGestationalAge())) {
            stringBuilder.append("gestationalAge=").append(this.getGestationalAge()).append(";");
        }
        if (this.getFatherHeight() != null) {
            stringBuilder.append("fatherHeight=").append(this.getFatherHeight()).append(";");
        }
        if (this.getMotherHeight() != null) {
            stringBuilder.append("motherHeight=").append(this.getMotherHeight()).append(";");
        }
        if (this.getTargetHeight() != null) {
            stringBuilder.append("targetHeight=").append(this.getTargetHeight()).append(";");
        }
        if (StringUtils.isNotBlank(this.getPraderStageAtFirstPresentation())) {
            stringBuilder.append("praderStageAtFirstPresentation=").append(this.getPraderStageAtFirstPresentation()).append(";");
        }
        if (StringUtils.isNotBlank(this.getSwCrisisAtPresentation())) {
            stringBuilder.append("swCrisisAtPresentation=").append(this.getSwCrisisAtPresentation()).append(";");
        }
        if (StringUtils.isNotBlank(this.getAdrenalCrisesAfterFirstPresentation())) {
            stringBuilder.append("adrenalCrisesAfterFirstPresentation=").append(this.getAdrenalCrisesAfterFirstPresentation()).append(";");
        }
        if (StringUtils.isNotBlank(this.getCurrentFcTreatment())) {
            stringBuilder.append("currentFcTreatment=").append(this.getCurrentFcTreatment()).append(";");
        }
        if (StringUtils.isNotBlank(this.getCurrentSaltReplacement())) {
            stringBuilder.append("currentSaltReplacement=").append(this.getCurrentSaltReplacement()).append(";");
        }

        if (null != this.getRequesterId()) {
            stringBuilder.append("requesterId=").append(this.getRequesterId()).append(";");
        }
        if (null != this.getRequestTime()) {
            stringBuilder.append("requestTime=").append(this.getRequestTime()).append(";");
        }
        if (null != this.getResponderId()) {
            stringBuilder.append("responderId=").append(this.getResponderId()).append(";");
        }
        if (null != this.getRespondTime()) {
            stringBuilder.append("executorTime=").append(this.getRespondTime()).append(";");
        }
        if (null != this.getConsent()) {
            stringBuilder.append("consent=").append(this.getConsent()).append(";");
        }
        if (null != this.getSampleConsent()) {
            stringBuilder.append("sampleConsent=").append(this.getSampleConsent()).append(";");
        }
        if (null != this.getCompleted()) {
            stringBuilder.append("completed=").append(this.getCompleted()).append(";");
        }
        if (null != this.getDeleted()) {
            stringBuilder.append("deleted=").append(this.getDeleted()).append(";");
        }

        return stringBuilder.toString();
    }

    @Transient
    public void setExampleDsdRecord(DsdIdentifier dsdIdentifier, DsdAssessment firstDsdAssessment, DsdAssessment lastDsdAssessment, DsdGeneAnalysis dsdGeneAnalysis, DsdCah dsdCah) {
        if (dsdIdentifier == null) {
            return;
        }
        if (dsdIdentifier.getRegisterId() != null) {
            this.setRegisterId(dsdIdentifier.getRegisterId());
        }
        if (StringUtils.isNotBlank(dsdIdentifier.getCentreName())) {
            this.setCentre(dsdIdentifier.getCentreName());
        }
        if (StringUtils.isNotBlank(dsdIdentifier.getCountryName())) {
            this.setCountry(dsdIdentifier.getCountryName());
        }
        if (StringUtils.isNotBlank(dsdIdentifier.getLocalId())) {
            this.setLocalId(dsdIdentifier.getLocalId().trim());
        }
        if (dsdIdentifier.getYob() != null) {
            this.setYob(dsdIdentifier.getYob());
        }
        if (StringUtils.isNotBlank(dsdIdentifier.getAofp())) {
            this.setAofp(dsdIdentifier.getAofp().trim());
        }
        if (StringUtils.isNotBlank(dsdIdentifier.getSexAssigned())) {
            this.setSexAssigned(dsdIdentifier.getSexAssigned().trim());
        }
        if (StringUtils.isNotBlank(dsdIdentifier.getCurrentGender())) {
            this.setCurrentGender(dsdIdentifier.getCurrentGender().trim());
        }
        if (StringUtils.isNotBlank(dsdIdentifier.getKaryotype())) {
            this.setKaryotype(dsdIdentifier.getKaryotype().trim());
        }
        if (StringUtils.isNotBlank(dsdIdentifier.getDisorderType())) {
            this.setDisorderType(dsdIdentifier.getDisorderType().trim());
        }
        if (StringUtils.isNotBlank(dsdIdentifier.getActualDiagnosis())) {
            this.setActualDiagnosis(dsdIdentifier.getActualDiagnosis().trim());
        }
        if (dsdIdentifier.getBirthWeight() != null) {
            this.setBirthWeight(dsdIdentifier.getBirthWeight());
        }
        if (dsdIdentifier.getBirthLength() != null) {
            this.setBirthLength(dsdIdentifier.getBirthLength());
        }
        if (StringUtils.isNotBlank(dsdIdentifier.getAssocMalforms())) {
            this.setAssocMalforms(dsdIdentifier.getAssocMalforms().trim());
        }
        if (StringUtils.isNotBlank(dsdIdentifier.getClinicalFeatures())) {
            this.setClinicalFeatures(dsdIdentifier.getClinicalFeatures().trim());
        }
        if (StringUtils.isNotBlank(dsdIdentifier.getBiochemistry())) {
            this.setBiochemistry(dsdIdentifier.getBiochemistry().trim());
        }
        if (StringUtils.isNotBlank(dsdIdentifier.getDnaAnalysis())) {
            this.setDnaAnalysis(dsdIdentifier.getDnaAnalysis().trim());
        }
        if (StringUtils.isNotBlank(dsdIdentifier.getParentalConsanguinity())) {
            this.setParentalConsanguinity(dsdIdentifier.getParentalConsanguinity().trim());
        }
        if (StringUtils.isNotBlank(dsdIdentifier.getDsdHistory())) {
            this.setDsdHistory(dsdIdentifier.getDsdHistory().trim());
        }
        if (StringUtils.isNotBlank(dsdIdentifier.getInfertilityHistory())) {
            this.setInfertilityHistory(dsdIdentifier.getInfertilityHistory().trim());
        }
        if (StringUtils.isNotBlank(dsdIdentifier.getSampleAvailability())) {
            this.setSampleAvailability(dsdIdentifier.getSampleAvailability().trim());
        }
        if (StringUtils.isNotBlank(dsdIdentifier.getClinicalInfo())) {
            this.setClinicalInfo(dsdIdentifier.getClinicalInfo().trim());
        }
        if (StringUtils.isNotBlank(dsdIdentifier.getCaseNotes())) {
            this.setCaseNotes(dsdIdentifier.getCaseNotes().trim());
        }
        if (StringUtils.isNotBlank(dsdIdentifier.getGrowthData())) {
            this.setGrowthData(dsdIdentifier.getGrowthData().trim());
        }
        if (StringUtils.isNotBlank(dsdIdentifier.getPubertyData())) {
            this.setPubertyData(dsdIdentifier.getPubertyData().trim());
        }
        if (StringUtils.isNotBlank(dsdIdentifier.getDna())) {
            this.setDna(dsdIdentifier.getDna().trim());
        }
        if (StringUtils.isNotBlank(dsdIdentifier.getTissue())) {
            this.setTissue(dsdIdentifier.getTissue().trim());
        }
        if (StringUtils.isNotBlank(dsdIdentifier.getCellLine())) {
            this.setCellLine(dsdIdentifier.getCellLine().trim());
        }
        if (StringUtils.isNotBlank(dsdIdentifier.getCellLineInfo())) {
            this.setCellLineInfo(dsdIdentifier.getCellLineInfo().trim());
        }
        if (StringUtils.isNotBlank(dsdIdentifier.getUrine())) {
            this.setUrine(dsdIdentifier.getUrine().trim());
        }
        if (StringUtils.isNotBlank(dsdIdentifier.getSerum())) {
            this.setSerum(dsdIdentifier.getSerum().trim());
        }
        if (StringUtils.isNotBlank(dsdIdentifier.getSampleShared())) {
            this.setSampleShared(dsdIdentifier.getSampleShared().trim());
        }
        if (firstDsdAssessment.getAssessmentDate() != null) {
            this.setFirstAssessmentDate(firstDsdAssessment.getAssessmentDate());
        }
        if (firstDsdAssessment.getPhallusLength() != null) {
            this.setFirstPhallusLength(firstDsdAssessment.getPhallusLength());
        }
        if (StringUtils.isNotBlank(firstDsdAssessment.getPhallusSize())) {
            this.setFirstPhallusSize(firstDsdAssessment.getPhallusSize());
        }
        if (StringUtils.isNotBlank(firstDsdAssessment.getUrinaryMeatus())) {
            this.setFirstUrinaryMeatus(firstDsdAssessment.getUrinaryMeatus());
        }
        if (StringUtils.isNotBlank(firstDsdAssessment.getLabioscrotalFusion())) {
            this.setFirstLabioscrotalFusion(firstDsdAssessment.getLabioscrotalFusion());
        }
        if (StringUtils.isNotBlank(firstDsdAssessment.getRightGonad())) {
            this.setFirstRightGonad(firstDsdAssessment.getRightGonad());
        }
        if (StringUtils.isNotBlank(firstDsdAssessment.getLeftGonad())) {
            this.setFirstLeftGonad(firstDsdAssessment.getLeftGonad());
        }
        if (StringUtils.isNotBlank(firstDsdAssessment.getMullerianStructure())) {
            this.setFirstMullerianStructure(firstDsdAssessment.getMullerianStructure());
        }
        if (StringUtils.isNotBlank(firstDsdAssessment.getWolffianStructure())) {
            this.setFirstWolffianStructure(firstDsdAssessment.getWolffianStructure());
        }
        if (firstDsdAssessment.getEms() != null) {
            this.setFirstEms(firstDsdAssessment.getEms());
        }
        if (StringUtils.isNotBlank(firstDsdAssessment.getModifiedPrader())) {
            this.setFirstModifiedPrader(firstDsdAssessment.getModifiedPrader());
        }
        if (StringUtils.isNotBlank(firstDsdAssessment.getTannerStage())) {
            this.setFirstTannerStage(firstDsdAssessment.getTannerStage());
        }
        if (StringUtils.isNotBlank(firstDsdAssessment.getGonadectomy())) {
            this.setFirstTannerStage(firstDsdAssessment.getTannerStage());
        }

        if (lastDsdAssessment.getAssessmentDate() != null) {
            this.setLastAssessmentDate(lastDsdAssessment.getAssessmentDate());
        }
        if (lastDsdAssessment.getPhallusLength() != null) {
            this.setLastPhallusLength(lastDsdAssessment.getPhallusLength());
        }
        if (StringUtils.isNotBlank(lastDsdAssessment.getPhallusSize())) {
            this.setLastPhallusSize(lastDsdAssessment.getPhallusSize());
        }
        if (StringUtils.isNotBlank(lastDsdAssessment.getUrinaryMeatus())) {
            this.setLastUrinaryMeatus(lastDsdAssessment.getUrinaryMeatus());
        }
        if (StringUtils.isNotBlank(lastDsdAssessment.getLabioscrotalFusion())) {
            this.setLastLabioscrotalFusion(lastDsdAssessment.getLabioscrotalFusion());
        }
        if (StringUtils.isNotBlank(lastDsdAssessment.getRightGonad())) {
            this.setLastRightGonad(lastDsdAssessment.getRightGonad());
        }
        if (StringUtils.isNotBlank(lastDsdAssessment.getLeftGonad())) {
            this.setLastLeftGonad(lastDsdAssessment.getLeftGonad());
        }
        if (StringUtils.isNotBlank(lastDsdAssessment.getMullerianStructure())) {
            this.setLastMullerianStructure(lastDsdAssessment.getMullerianStructure());
        }
        if (StringUtils.isNotBlank(lastDsdAssessment.getWolffianStructure())) {
            this.setLastWolffianStructure(lastDsdAssessment.getWolffianStructure());
        }
        if (lastDsdAssessment.getEms() != null) {
            this.setLastEms(lastDsdAssessment.getEms());
        }
        if (StringUtils.isNotBlank(lastDsdAssessment.getModifiedPrader())) {
            this.setLastModifiedPrader(lastDsdAssessment.getModifiedPrader());
        }
        if (StringUtils.isNotBlank(lastDsdAssessment.getTannerStage())) {
            this.setLastTannerStage(lastDsdAssessment.getTannerStage());
        }
        if (StringUtils.isNotBlank(lastDsdAssessment.getGonadectomy())) {
            this.setLastTannerStage(lastDsdAssessment.getTannerStage());
        }

        if (StringUtils.isNotBlank(dsdGeneAnalysis.getSingleGeneAnalysis())) {
            this.setSingleGeneAnalysis(dsdGeneAnalysis.getSingleGeneAnalysis());
        }
        if (StringUtils.isNotBlank(dsdGeneAnalysis.getChromosomalRearrangement())) {
            this.setChromosomalRearrangement(dsdGeneAnalysis.getChromosomalRearrangement());
        }
        if (StringUtils.isNotBlank(dsdGeneAnalysis.getCgh())) {
            this.setCgh(dsdGeneAnalysis.getCgh());
        }
        if (StringUtils.isNotBlank(dsdGeneAnalysis.getFunctionalStudy())) {
            this.setFunctionalStudy(dsdGeneAnalysis.getFunctionalStudy());
        }
        if (StringUtils.isNotBlank(dsdGeneAnalysis.getFurtherStudies())) {
            this.setFurtherStudies(dsdGeneAnalysis.getFurtherStudies());
        }

        if (StringUtils.isNotBlank(dsdCah.getPrenatalDiagnosis())) {
            this.setPrenatalDiagnosis(dsdCah.getPrenatalDiagnosis());
        }
        if (StringUtils.isNotBlank(dsdCah.getPrenatalDexamethasoneTherapy())) {
            this.setPrenatalDexamethasoneTherapy(dsdCah.getPrenatalDexamethasoneTherapy());
        }
        if (StringUtils.isNotBlank(dsdCah.getGestationalAge())) {
            this.setGestationalAge(dsdCah.getGestationalAge());
        }
        if (dsdCah.getFatherHeight() != null) {
            this.setFatherHeight(dsdCah.getFatherHeight());
        }
        if (dsdCah.getMotherHeight() != null) {
            this.setMotherHeight(dsdCah.getMotherHeight());
        }
        if (dsdCah.getTargetHeight() != null) {
            this.setTargetHeight(dsdCah.getTargetHeight());
        }
        if (StringUtils.isNotBlank(dsdCah.getPraderStageAtFirstPresentation())) {
            this.setPraderStageAtFirstPresentation(dsdCah.getPraderStageAtFirstPresentation());
        }
        if (StringUtils.isNotBlank(dsdCah.getSwCrisisAtPresentation())) {
            this.setSwCrisisAtPresentation(dsdCah.getSwCrisisAtPresentation());
        }
        if (StringUtils.isNotBlank(dsdCah.getAdrenalCrisesAfterFirstPresentation())) {
            this.setAdrenalCrisesAfterFirstPresentation(dsdCah.getAdrenalCrisesAfterFirstPresentation());
        }
        if (StringUtils.isNotBlank(dsdCah.getCurrentFcTreatment())) {
            this.setCurrentFcTreatment(dsdCah.getCurrentFcTreatment());
        }
        if (StringUtils.isNotBlank(dsdCah.getCurrentSaltReplacement())) {
            this.setCurrentSaltReplacement(dsdCah.getCurrentSaltReplacement());
        }

        if (dsdIdentifier.getConsent() != null) {
            this.setConsent(dsdIdentifier.getConsent());
        }
        if (dsdIdentifier.getSampleConsent() != null) {
            this.setSampleConsent(dsdIdentifier.getSampleConsent());
        }
    }

}