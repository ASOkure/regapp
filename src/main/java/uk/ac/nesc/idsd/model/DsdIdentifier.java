package uk.ac.nesc.idsd.model;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Sort;
import org.hibernate.annotations.SortType;
import uk.ac.nesc.idsd.util.Utility;
import uk.ac.nesc.idsd.util.comparator.DsdAssessmentDateComparator;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.NavigableSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

@Entity
@Table(name = "dsd_identifier", catalog = "idsd")
public class DsdIdentifier implements java.io.Serializable {
    private static final long serialVersionUID = 514289375982785797L;

    private Long registerId;
    private PortalUser uploader;
    private Centre centre;
   // @Transient
    private String countryName;
  //  @Transient
    private String centreName;
    private String localId;
    private Integer yob;
    private Double birthWeight;
    private String aofp;
   // @Transient
    private String clinician;
   // @Transient
    private String contact;
    private String karyotype;
    private String disorderType;
    private String actualDiagnosis;
    private String sexAssigned;
    private String currentGender;
    private String geneticCertainty;
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
    private String sampleSharedDetail;
    private String infertilityHistory;
    private String sampleAvailability;
    private String clinicalFeatures;
    private String biochemistry;
    private String parentalConsanguinity;
    private Integer consent;
    private Boolean sampleConsent;
    private String freeText;
    private Timestamp uploadTime;
    private Timestamp lastUpdate;
    private String dsdHistory;
    private Boolean completeness;
    private Boolean deleteStatus;
    private Set<ResearchResult> researchResults = new HashSet<ResearchResult>(0);
    private SortedSet<DsdAssessment> dsdAssessments = new TreeSet<DsdAssessment>(new DsdAssessmentDateComparator());
    private DsdCah dsdCah;
    private DsdGeneAnalysis dsdGeneAnalysis;
    private String coreFreeText;
    private Double birthLength;

    public DsdIdentifier() {
    }

    //constructor for patient access
    public DsdIdentifier(Long registerId, Centre centre, String countryName, String centreName, Integer yob, Double birthWeight, String aofp, String clinician,
                         String contact, String karyotype, String disorderType, String actualDiagnosis, String sexAssigned, String currentGender, Double birthLength, String assocMalforms, Integer consent) {
        this.registerId = registerId;
        this.centre = centre;
        this.countryName = countryName;
        this.centreName = centreName;
        this.yob = yob;
        this.birthWeight = birthWeight;
        this.aofp = aofp;
        this.clinician = clinician;
        this.contact = contact;
        this.karyotype = karyotype;
        this.disorderType = disorderType;
        this.actualDiagnosis = actualDiagnosis;
        this.sexAssigned = sexAssigned;
        this.currentGender = currentGender;
        this.birthLength = birthLength;
        this.assocMalforms = assocMalforms;
        this.consent = consent;
    }

    public DsdIdentifier(Long registerId) {
        this.registerId = registerId;
    }

    public DsdIdentifier(String dna, String tissue,
                         String cellLine, String urine, String serum, Boolean sampleConsent, Integer consent) {
        this.dna = dna;
        this.tissue = tissue;
        this.cellLine = cellLine;
        this.urine = urine;
        this.serum = serum;
        this.sampleConsent = sampleConsent;
        this.consent = consent;
    }

    public DsdIdentifier(String countryName, String centreName, String dna, String tissue,
                         String cellLine, String urine, String serum, Boolean sampleConsent, Integer consent) {
        if (null == this.centre) {
            this.centre = new Centre(centreName);
        } else {
            this.centre.setCentreName(centreName);
        }
        if (null == this.centre.getCountry()) {
            this.centre.setCountry(new Country(countryName));
        } else {
            this.centre.getCountry().setCountryName(countryName);
        }
        this.centreName = centreName;
        this.countryName = countryName;
        this.dna = dna;
        this.tissue = tissue;
        this.cellLine = cellLine;
        this.urine = urine;
        this.serum = serum;
        this.sampleConsent = sampleConsent;
        this.consent = consent;
    }

    public DsdIdentifier(Centre centre, PortalUser uploader,
                         String localId, Integer yob, Double birthWeight, String aofp,
                         String karyotype,
                         String disorderType, String actualDiagnosis, String sexAssigned, String currentGender,
                         String geneticCertainty, String assocMalforms, String dnaAnalysis,
                         String clinicalInfo, String caseNotes, String growthData,
                         String pubertyData, String dna, String tissue, String cellLine,
                         String cellLineInfo, String urine, String serum,
                         String sampleShared, String sampleSharedDetail,
                         String infertilityHistory, String sampleAvailability,
                         String clinicalFeatures, String biochemistry,
                         String parentalConsanguinity, Integer consent,
                         Boolean sampleConsent, String freeText,
                         Timestamp uploadTime, Timestamp lastUpdate, String dsdHistory,
                         Boolean completeness, Boolean deleteStatus,
                         Set<ResearchResult> researchResults,
                         SortedSet<DsdAssessment> dsdAssessments, DsdGeneAnalysis dsdGeneAnalysis, DsdCah dsdCah) {
        this.centre = centre;
        this.uploader = uploader;
        this.clinician = centre.getLeader().getName();
        this.contact = centre.getLeader().getEmail();
        this.countryName = this.centre.getCountry().getCountryName();
        this.centreName = this.centre.getCentreName();
        this.localId = localId;
        this.yob = yob;
        this.birthWeight = birthWeight;
        this.aofp = aofp;
        this.karyotype = karyotype;
        this.disorderType = disorderType;
        this.actualDiagnosis = actualDiagnosis;
        this.sexAssigned = sexAssigned;
        this.currentGender = currentGender;
        this.geneticCertainty = geneticCertainty;
        this.assocMalforms = assocMalforms;
        this.dnaAnalysis = dnaAnalysis;
        this.clinicalInfo = clinicalInfo;
        this.caseNotes = caseNotes;
        this.growthData = growthData;
        this.pubertyData = pubertyData;
        this.dna = dna;
        this.tissue = tissue;
        this.cellLine = cellLine;
        this.cellLineInfo = cellLineInfo;
        this.urine = urine;
        this.serum = serum;
        this.sampleShared = sampleShared;
        this.sampleSharedDetail = sampleSharedDetail;
        this.infertilityHistory = infertilityHistory;
        this.sampleAvailability = sampleAvailability;
        this.clinicalFeatures = clinicalFeatures;
        this.biochemistry = biochemistry;
        this.parentalConsanguinity = parentalConsanguinity;
        this.consent = consent;
        this.sampleConsent = sampleConsent;
        this.freeText = freeText;
        this.uploadTime = uploadTime;
        this.lastUpdate = lastUpdate;
        this.dsdHistory = dsdHistory;
        this.completeness = completeness;
        this.deleteStatus = deleteStatus;
        this.researchResults = researchResults;
        this.dsdAssessments = dsdAssessments;
        this.dsdCah = dsdCah;
        this.dsdGeneAnalysis = dsdGeneAnalysis;
    }

    public DsdIdentifier(DsdIdentifier that) {
        if (that == null) {
            return;
        }
        if (StringUtils.isNotBlank(that.getActualDiagnosis())) {
            this.setActualDiagnosis(that.getActualDiagnosis().trim());
        }
        if (StringUtils.isNotBlank(that.getAofp())) {
            this.setAofp(that.getAofp().trim());
        }
        if (StringUtils.isNotBlank(that.getAssocMalforms())) {
            this.setAssocMalforms(that.getAssocMalforms().trim());
        }
        if (StringUtils.isNotBlank(that.getBiochemistry())) {
            this.setBiochemistry(that.getBiochemistry().trim());
        }
        if (that.getBirthWeight() != null) {
            this.setBirthWeight(that.getBirthWeight());
        }
        if (StringUtils.isNotBlank(that.getCaseNotes())) {
            this.setCaseNotes(that.getCaseNotes().trim());
        }
        if (StringUtils.isNotBlank(that.getCellLine())) {
            this.setCellLine(that.getCellLine().trim());
        }
        if (StringUtils.isNotBlank(that.getCellLineInfo())) {
            this.setCellLineInfo(that.getCellLineInfo().trim());
        }
        if (that.getCentre() != null) {
            this.setCentre(that.getCentre());
        }
        if (StringUtils.isNotBlank(that.getClinicalFeatures())) {
            this.setClinicalFeatures(that.getClinicalFeatures().trim());
        }
        if (StringUtils.isNotBlank(that.getClinicalInfo())) {
            this.setClinicalInfo(that.getClinicalInfo().trim());
        }
        if (StringUtils.isNotBlank(that.getClinician())) {
            this.setClinician(that.getClinician().trim());
            if (null == this.getCentre()) {
                Centre ce = new Centre();
                ce.setLeader(new PortalUser(that.getClinician()));
                this.setCentre(ce);
            } else if (null == this.getCentre().getLeader()) {
                this.getCentre().setLeader(new PortalUser(that.getClinician()));
            } else {
                this.getCentre().getLeader().setName(that.getClinician());
            }
        }
        if (that.getConsent() != null) {
            this.setConsent(that.getConsent());
        }
        if (StringUtils.isNotBlank(that.getContact())) {
            this.setContact(that.getContact().trim());
            if (null == this.getCentre()) {
                PortalUser u = new PortalUser();
                u.setEmail(that.getContact().trim());
                Centre ce = new Centre();
                ce.setLeader(u);
                this.setCentre(ce);
            } else if (null == this.getCentre().getLeader()) {
                PortalUser u = new PortalUser();
                u.setEmail(that.getContact().trim());
                this.getCentre().setLeader(u);
            } else {
                this.getCentre().getLeader().setEmail(that.getContact().trim());
            }
        }
        if (StringUtils.isNotBlank(that.getCountryName())) {
            this.setCountryName(that.getCountryName().trim());
            if (null == this.getCentre()) {
                Centre ce = new Centre();
                Country co = new Country();
                co.setCountryName(that.getCountryName().trim());
                ce.setCountry(co);
                this.setCentre(ce);
            } else {
                if (null == this.getCentre().getCountry()) {
                    Country co = new Country();
                    co.setCountryName(that.getCountryName().trim());
                } else {
                    this.getCentre().getCountry().setCountryName(that.getCountryName().trim());
                }
            }
        }
        if (StringUtils.isNotBlank(that.getCentreName())) {
            this.setCentreName(that.getCentreName().trim());
            if (null == that.getCentre()) {
                Centre ce = new Centre();
                ce.setCentreName(that.getCentreName().trim());
                this.setCentre(ce);
            } else {
                this.getCentre().setCentreName(that.getCentreName().trim());
            }
        }
        if (StringUtils.isNotBlank(that.getDisorderType())) {
            this.setDisorderType(that.getDisorderType().trim());
        }
        if (StringUtils.isNotBlank(that.getDna())) {
            this.setDna(that.getDna().trim());
        }
        if (StringUtils.isNotBlank(that.getDnaAnalysis())) {
            this.setDnaAnalysis(that.getDnaAnalysis().trim());
        }
        if (StringUtils.isNotBlank(that.getFreeText())) {
            this.setFreeText(that.getFreeText().trim());
        }
        if (StringUtils.isNotBlank(that.getGeneticCertainty())) {
            this.setGeneticCertainty(that.getGeneticCertainty().trim());
        }
        if (StringUtils.isNotBlank(that.getGrowthData())) {
            this.setGrowthData(that.getGrowthData().trim());
        }
        if (StringUtils.isNotBlank(that.getInfertilityHistory())) {
            this.setInfertilityHistory(that.getInfertilityHistory().trim());
        }
        if (StringUtils.isNotBlank(that.getKaryotype())) {
            this.setKaryotype(that.getKaryotype().trim());
        }
        if (StringUtils.isNotBlank(that.getLocalId())) {
            this.setLocalId(that.getLocalId().trim());
        }
        if (StringUtils.isNotBlank(that.getParentalConsanguinity())) {
            this.setParentalConsanguinity(that.getParentalConsanguinity().trim());
        }
        if (StringUtils.isNotBlank(that.getPubertyData())) {
            this.setPubertyData(that.getPubertyData().trim());
        }
        if (that.getRegisterId() != null) {
            this.setRegisterId(that.getRegisterId());
        }
        if (StringUtils.isNotBlank(that.getSampleAvailability())) {
            this.setSampleAvailability(that.getSampleAvailability().trim());
        }
        if (that.getSampleConsent() != null) {
            this.setSampleConsent(that.getSampleConsent());
        }
        if (StringUtils.isNotBlank(that.getSampleShared())) {
            this.setSampleShared(that.getSampleShared().trim());
        }
        if (StringUtils.isNotBlank(that.getSampleSharedDetail())) {
            this.setSampleSharedDetail(that.getSampleSharedDetail().trim());
        }
        if (StringUtils.isNotBlank(that.getSerum())) {
            this.setSerum(that.getSerum().trim());
        }
        if (StringUtils.isNotBlank(that.getSexAssigned())) {
            this.setSexAssigned(that.getSexAssigned().trim());
        }
        if (StringUtils.isNotBlank(that.getCurrentGender())) {
            this.setCurrentGender(that.getCurrentGender().trim());
        }
        if (StringUtils.isNotBlank(that.getTissue())) {
            this.setTissue(that.getTissue().trim());
        }
        if (StringUtils.isNotBlank(that.getUrine())) {
            this.setUrine(that.getUrine().trim());
        }
        if (that.getYob() != null) {
            this.setYob(that.getYob());
        }
        if (StringUtils.isNotBlank(that.getDsdHistory())) {
            this.setDsdHistory(that.getDsdHistory().trim());
        }
        if (StringUtils.isNotBlank(that.getCoreFreeText())) {
            this.setCoreFreeText(that.getCoreFreeText().trim());
        }
        if (that.getBirthLength() != null) {
            this.setBirthLength(that.getBirthLength());
        }

        if (CollectionUtils.isNotEmpty(that.getDsdAssessments())) {
            NavigableSet<DsdAssessment> newDsdAssessmentSet = new TreeSet<>(new DsdAssessmentDateComparator());
            for (DsdAssessment da : that.getDsdAssessments()) {
                newDsdAssessmentSet.add(new DsdAssessment(da));
            }
            this.setDsdAssessments(newDsdAssessmentSet);
        }

        if (that.getDsdGeneAnalysis() != null) {
            this.setDsdGeneAnalysis(new DsdGeneAnalysis(that.getDsdGeneAnalysis()));
        }

        if (that.getDsdCah() != null) {
            this.setDsdCah(new DsdCah(that.getDsdCah()));
        }

        if (that.getLastUpdate() != null) {
            this.setLastUpdate(that.getLastUpdate());
        }
        if (that.getUploader() != null) {
            this.setUploader(that.getUploader());
        }
        if (that.getUploadTime() != null) {
            this.setUploadTime(that.getUploadTime());
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "register_id", unique = true, nullable = false)
    public Long getRegisterId() {
        return this.registerId;
    }

    public void setRegisterId(Long registerId) {
        this.registerId = registerId;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "uploader_id")
    @BatchSize(size = 200)
    public PortalUser getUploader() {
        return uploader;
    }

    public void setUploader(PortalUser uploader) {
        this.uploader = uploader;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "centre_id")
    public Centre getCentre() {
        return this.centre;
    }

    public void setCentre(Centre centre) {
        this.centre = centre;
    }

    //	@Column(name = "country", length = 100)
    @Transient
    public String getCountryName() {
        if (null != this.centre && null != this.centre.getCountry()) {
            this.countryName = this.centre.getCountry().getCountryName();
        }
        return this.countryName;
    }

    @Transient
    public void setCountryName(String countryName) {
        this.countryName = countryName;
        if (null == this.centre) {
            Country co = new Country();
            co.setCountryName(countryName);
            Centre ce = new Centre();
            ce.setCountry(co);
            this.centre = ce;
        } else {
            if (null == this.centre.getCountry()) {
                Country co = new Country();
                co.setCountryName(countryName);
                this.centre.setCountry(co);
            } else {
                this.centre.getCountry().setCountryName(countryName);
            }
        }
    }

    @Transient
    public String getCentreName() {
        if (null != this.centre) {
            this.centreName = this.centre.getCentreName();
        }
        return this.centreName;
    }

    @Transient
    public void setCentreName(String centreName) {
        this.centreName = centreName;
        if (null == this.centre) {
            Centre ce = new Centre();
            ce.setCentreName(centreName);
            this.centre = ce;
        } else {
            this.centre.setCentreName(centreName);
        }
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

    @Transient
    public String getClinician() {
        if (null != this.centre && null != this.centre.getLeader()) {
            return Utility.getLastName(this.centre.getLeader().getName());
        }
        return null;
    }

    @Transient
    public void setClinician(String clinician) {
        this.clinician = clinician;
    }

    //	@Column(name = "contact", length = 200)
    @Transient
    public String getContact() {
        if (null != this.centre && null != this.centre.getLeader()) {
            return this.centre.getLeader().getEmail();
        }
        return null;
    }

    @Transient
    public void setContact(String contact) {
        this.contact = contact;
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

    @Column(name = "genetic_certainty", length = 100)
    public String getGeneticCertainty() {
        return this.geneticCertainty;
    }

    public void setGeneticCertainty(String geneticCertainty) {
        this.geneticCertainty = geneticCertainty;
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

    @Column(name = "sample_shared_detail")
    public String getSampleSharedDetail() {
        return this.sampleSharedDetail;
    }

    public void setSampleSharedDetail(String sampleSharedDetail) {
        this.sampleSharedDetail = sampleSharedDetail;
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
        return this.sampleConsent;
    }

    public void setSampleConsent(Boolean sampleConsent) {
        this.sampleConsent = sampleConsent;
    }

    @Column(name = "free_text")
    public String getFreeText() {
        return this.freeText;
    }

    public void setFreeText(String freeText) {
        this.freeText = freeText;
    }

    @Column(name = "upload_time", length = 19)
    public Timestamp getUploadTime() {
        return this.uploadTime;
    }

    public void setUploadTime(Timestamp uploadTime) {
        this.uploadTime = uploadTime;
    }

    @Column(name = "last_update", length = 19)
    public Timestamp getLastUpdate() {
        return this.lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Column(name = "dsd_history", length = 100)
    public String getDsdHistory() {
        return this.dsdHistory;
    }

    public void setDsdHistory(String dsdHistory) {
        this.dsdHistory = dsdHistory;
    }

    @Column(name = "completeness")
    public Boolean getCompleteness() {
        return this.completeness;
    }

    public void setCompleteness(Boolean completeness) {
        this.completeness = completeness;
    }

    @Column(name = "delete_status")
    public Boolean getDeleteStatus() {
        return this.deleteStatus;
    }

    public void setDeleteStatus(Boolean deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "dsdIdentifier")
    public DsdGeneAnalysis getDsdGeneAnalysis() {
        return this.dsdGeneAnalysis;
    }

    public void setDsdGeneAnalysis(DsdGeneAnalysis dsdGeneAnalysis) {
        this.dsdGeneAnalysis = dsdGeneAnalysis;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "dsdIdentifier")
    @OrderBy("assessmentDate ASC")
    @Sort(type = SortType.NATURAL)
    @BatchSize(size = 100)
    public SortedSet<DsdAssessment> getDsdAssessments() {
        return this.dsdAssessments;
    }

    public void setDsdAssessments(SortedSet<DsdAssessment> dsdAssessments) {
        this.dsdAssessments = dsdAssessments;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "dsdIdentifier")
    @OrderBy
    @BatchSize(size = 100)
    public Set<ResearchResult> getResearchResults() {
        return this.researchResults;
    }

    public void setResearchResults(Set<ResearchResult> researchResults) {
        this.researchResults = researchResults;
    }

    @Column(name = "core_free_text")
    public String getCoreFreeText() {
        return coreFreeText;
    }

    public void setCoreFreeText(String coreFreeText) {
        this.coreFreeText = coreFreeText;
    }

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "dsdIdentifier")
    public DsdCah getDsdCah() {
        return this.dsdCah;
    }

    public void setDsdCah(DsdCah dsdCah) {
        this.dsdCah = dsdCah;
    }

    @Column(name = "birth_length", precision = 12, scale = 2)
    public Double getBirthLength() {
        return this.birthLength;
    }

    public void setBirthLength(Double birthLength) {
        this.birthLength = birthLength;
    }

    @Override
    public String toString() {
        if (this == null) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        if (null != this.getRegisterId()) {
            stringBuilder.append("registerId=").append(this.getRegisterId()).append(";");
        }
        if (StringUtils.isNotBlank(this.getCountryName())) {
            stringBuilder.append("country=").append(this.getCountryName()).append(";");
        }
        if (StringUtils.isNotBlank(this.getCentreName())) {
            stringBuilder.append("centre=").append(this.getCentreName()).append(";");
        }
        if (StringUtils.isNotBlank(this.getLocalId())) {
            stringBuilder.append("localId=").append(this.getLocalId()).append(";");
        }
        if (this.getYob() != null) {
            stringBuilder.append("yob=").append(this.getYob()).append(";");
        }
        if (StringUtils.isNotBlank(this.getAofp())) {
            stringBuilder.append("aofp=").append(this.getAofp()).append(";");
        }
        if (StringUtils.isNotBlank(this.getClinician())) {
            stringBuilder.append("clinician=").append(this.getClinician()).append(";");
        }
        if (StringUtils.isNotBlank(this.getContact())) {
            stringBuilder.append("contact=").append(this.getContact()).append(";");
        }
        if (this.getBirthWeight() != null) {
            stringBuilder.append("birthWeight=").append(this.getBirthWeight()).append(";");
        }
        if (StringUtils.isNotBlank(this.getSexAssigned())) {
            stringBuilder.append("sexAssigned=").append(this.getSexAssigned()).append(";");
        }
        if (StringUtils.isNotBlank(this.getCurrentGender())) {
            stringBuilder.append("currentGender=").append(this.getCurrentGender()).append(";");
        }
        if (StringUtils.isNotBlank(this.getKaryotype())) {
            stringBuilder.append("karyotype=").append(this.getKaryotype()).append(";");
        }
        if (StringUtils.isNotBlank(this.getActualDiagnosis())) {
            stringBuilder.append("actualDiagnosis=").append(this.getActualDiagnosis()).append(";");
        }
        if (StringUtils.isNotBlank(this.getDisorderType())) {
            stringBuilder.append("disorderType=").append(this.getDisorderType()).append(";");
        }
        if (StringUtils.isNotBlank(this.getGeneticCertainty())) {
            stringBuilder.append("geneticCertainty=").append(this.getGeneticCertainty()).append(";");
        }
        if (StringUtils.isNotBlank(this.getAssocMalforms())) {
            stringBuilder.append("assocMalforms=").append(this.getAssocMalforms()).append(";");
        }
        if (StringUtils.isNotBlank(this.getClinicalFeatures())) {
            stringBuilder.append("clinicalFeatures=").append(this.getClinicalFeatures()).append(";");
        }
        if (StringUtils.isNotBlank(this.getBiochemistry())) {
            stringBuilder.append("biochemistry=").append(this.getBiochemistry()).append(";");
        }
        if (StringUtils.isNotBlank(this.getDnaAnalysis())) {
            stringBuilder.append("dnaAnalysis=").append(this.getDnaAnalysis()).append(";");
        }
        if (StringUtils.isNotBlank(this.getParentalConsanguinity())) {
            stringBuilder.append("parentalConsanguinity=").append(this.getParentalConsanguinity()).append(";");
        }
        if (StringUtils.isNotBlank(this.getDsdHistory())) {
            stringBuilder.append("dsdHistory=").append(this.getDsdHistory()).append(";");
        }
        if (StringUtils.isNotBlank(this.getInfertilityHistory())) {
            stringBuilder.append("infertilityHistory=").append(this.getInfertilityHistory()).append(";");
        }
        if (StringUtils.isNotBlank(this.getSampleAvailability())) {
            stringBuilder.append("sampleAvailability=").append(this.getSampleAvailability()).append(";");
        }
        if (StringUtils.isNotBlank(this.getClinicalInfo())) {
            stringBuilder.append("clinicalInfo=").append(this.getClinicalInfo()).append(";");
        }
        if (StringUtils.isNotBlank(this.getCaseNotes())) {
            stringBuilder.append("caseNote=").append(this.getCaseNotes()).append(";");
        }
        if (StringUtils.isNotBlank(this.getGrowthData())) {
            stringBuilder.append("growthData=").append(this.getGrowthData()).append(";");
        }
        if (StringUtils.isNotBlank(this.getPubertyData())) {
            stringBuilder.append("pubertyData=").append(this.getPubertyData()).append(";");
        }
        if (StringUtils.isNotBlank(this.getDna())) {
            stringBuilder.append("dna=").append(this.getDna()).append(";");
        }
        if (StringUtils.isNotBlank(this.getTissue())) {
            stringBuilder.append("tissue=").append(this.getTissue()).append(";");
        }
        if (StringUtils.isNotBlank(this.getCellLine())) {
            stringBuilder.append("cellLine=").append(this.getCellLine()).append(";");
        }
        if (StringUtils.isNotBlank(this.getCellLineInfo())) {
            stringBuilder.append("cellLineInfo=").append(this.getCellLineInfo()).append(";");
        }
        if (StringUtils.isNotBlank(this.getUrine())) {
            stringBuilder.append("urine=").append(this.getUrine()).append(";");
        }
        if (StringUtils.isNotBlank(this.getSerum())) {
            stringBuilder.append("serum=").append(this.getSerum()).append(";");
        }
        if (StringUtils.isNotBlank(this.getSampleShared())) {
            stringBuilder.append("sampleShared=").append(this.getSampleShared()).append(";");
        }
        if (StringUtils.isNotBlank(this.getCoreFreeText())) {
            stringBuilder.append("coreFreeText=").append(this.getCoreFreeText()).append(";");
        }
        if (StringUtils.isNotBlank(this.getFreeText())) {
            stringBuilder.append("freeText=").append(this.getFreeText()).append(";");
        }
        if (this.getUploader() != null) {
            stringBuilder.append("uploader=").append(this.getUploader().getUsername()).append(";");
        }

        //prepare Assessment list
        if (CollectionUtils.isNotEmpty(this.getDsdAssessments())) {
            for (DsdAssessment assessment : this.getDsdAssessments()) {
                stringBuilder.append(assessment.toString());
            }
        }

        if (getDsdGeneAnalysis() != null) {
            stringBuilder.append(getDsdGeneAnalysis().toString());
        }

        if (stringBuilder.length() > 0 && stringBuilder.lastIndexOf(";") == stringBuilder.length() - 1) {
            return stringBuilder.substring(0, stringBuilder.length() - 1);
        }
        return stringBuilder.toString();
    }

    public String toCoreString() {
        return "DsdIdentifier{" +
                "registerId=" + registerId +
                ", centre=" + centre +
                ", localId='" + localId + '\'' +
                ", yob=" + yob +
                ", aofp='" + aofp + '\'' +
                ", uploader=" + uploader +
                ", sexAssigned='" + sexAssigned + '\'' +
                ", currentGender='" + currentGender + '\'' +
                ", karyotype='" + karyotype + '\'' +
                ", disorderType='" + disorderType + '\'' +
                ", actualDiagnosis='" + actualDiagnosis + '\'' +
                ", coreFreeText='" + coreFreeText + '\'' +
                ", consent=" + consent +
                ", uploadTime=" + uploadTime +
                ", lastUpdate=" + lastUpdate +
                '}';
    }

    public String toDiagnosisString() {
        return "DsdIdentifier{" +
                "registerId=" + registerId +
                ", birthWeight=" + birthWeight +
                ", birthLength=" + birthLength +
                ", geneticCertainty='" + geneticCertainty + '\'' +
                ", assocMalforms='" + assocMalforms + '\'' +
                ", clinicalFeatures='" + clinicalFeatures + '\'' +
                ", biochemistry='" + biochemistry + '\'' +
                ", dnaAnalysis='" + dnaAnalysis + '\'' +
                ", parentalConsanguinity='" + parentalConsanguinity + '\'' +
                ", dsdHistory='" + dsdHistory + '\'' +
                ", infertilityHistory='" + infertilityHistory + '\'' +
                ", sampleAvailability='" + sampleAvailability + '\'' +
                ", clinicalInfo='" + clinicalInfo + '\'' +
                ", caseNotes='" + caseNotes + '\'' +
                ", growthData='" + growthData + '\'' +
                ", pubertyData='" + pubertyData + '\'' +
                ", dna='" + dna + '\'' +
                ", tissue='" + tissue + '\'' +
                ", cellLine='" + cellLine + '\'' +
                ", cellLineInfo='" + cellLineInfo + '\'' +
                ", urine='" + urine + '\'' +
                ", serum='" + serum + '\'' +
                ", sampleShared='" + sampleShared + '\'' +
                ", sampleConsent=" + sampleConsent +
                ", freeText='" + freeText + '\'' +
                ", uploadTime=" + uploadTime +
                ", lastUpdate=" + lastUpdate +
                '}';
    }

    public String toAssessmentString() {
        return "DsdIdentifier{" +
                "registerId=" + registerId +
                ", dsdAssessments=" + dsdAssessments +
                ", uploadTime=" + uploadTime +
                ", lastUpdate=" + lastUpdate +
                '}';
    }

    public String toGeneAnalysisString() {
        return "DsdIdentifier{" +
                "registerId=" + registerId +
                ", dsdGeneAnalysis=" + dsdGeneAnalysis +
                ", uploadTime=" + uploadTime +
                ", lastUpdate=" + lastUpdate +
                '}';
    }

    public String toCahString() {
        return "DsdIdentifier{" +
                "registerId=" + registerId +
                ", dsdCah=" + dsdCah +
                ", uploadTime=" + uploadTime +
                ", lastUpdate=" + lastUpdate +
                '}';
    }

    public String toCahVisitString() {
        return "DsdIdentifier{" +
                "registerId=" + registerId +
                ", dsdCah=" + dsdCah +
                ", uploadTime=" + uploadTime +
                ", lastUpdate=" + lastUpdate +
                '}';
    }

    public String toLongString() {
        return "DsdIdentifier{" +
                "registerId=" + registerId +
                ", uploader=" + uploader +
                ", centre=" + centre +
                ", countryName='" + countryName + '\'' +
                ", centreName='" + centreName + '\'' +
                ", localId='" + localId + '\'' +
                ", yob=" + yob +
                ", birthWeight=" + birthWeight +
                ", aofp='" + aofp + '\'' +
                ", clinician='" + clinician + '\'' +
                ", contact='" + contact + '\'' +
                ", karyotype='" + karyotype + '\'' +
                ", disorderType='" + disorderType + '\'' +
                ", actualDiagnosis='" + actualDiagnosis + '\'' +
                ", sexAssigned='" + sexAssigned + '\'' +
                ", currentGender='" + currentGender + '\'' +
                ", geneticCertainty='" + geneticCertainty + '\'' +
                ", assocMalforms='" + assocMalforms + '\'' +
                ", dnaAnalysis='" + dnaAnalysis + '\'' +
                ", clinicalInfo='" + clinicalInfo + '\'' +
                ", caseNotes='" + caseNotes + '\'' +
                ", growthData='" + growthData + '\'' +
                ", pubertyData='" + pubertyData + '\'' +
                ", dna='" + dna + '\'' +
                ", tissue='" + tissue + '\'' +
                ", cellLine='" + cellLine + '\'' +
                ", cellLineInfo='" + cellLineInfo + '\'' +
                ", urine='" + urine + '\'' +
                ", serum='" + serum + '\'' +
                ", sampleShared='" + sampleShared + '\'' +
                ", sampleSharedDetail='" + sampleSharedDetail + '\'' +
                ", infertilityHistory='" + infertilityHistory + '\'' +
                ", sampleAvailability='" + sampleAvailability + '\'' +
                ", clinicalFeatures='" + clinicalFeatures + '\'' +
                ", biochemistry='" + biochemistry + '\'' +
                ", parentalConsanguinity='" + parentalConsanguinity + '\'' +
                ", consent=" + consent +
                ", sampleConsent=" + sampleConsent +
                ", freeText='" + freeText + '\'' +
                ", uploadTime=" + uploadTime +
                ", lastUpdate=" + lastUpdate +
                ", dsdHistory='" + dsdHistory + '\'' +
                ", completeness=" + completeness +
                ", deleteStatus=" + deleteStatus +
                ", researchResults=" + researchResults +
                ", dsdAssessments=" + dsdAssessments +
                ", dsdCah=" + dsdCah +
                ", dsdGeneAnalysis=" + dsdGeneAnalysis +
                ", coreFreeText='" + coreFreeText + '\'' +
                ", birthLength=" + birthLength +
                '}';
    }
}