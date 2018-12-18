package uk.ac.nesc.idsd.service.impl;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import uk.ac.nesc.idsd.bean.DsdIdentifierResearchResultBean;
import uk.ac.nesc.idsd.bean.ProgressBean;
import uk.ac.nesc.idsd.bean.SearchResult;
import uk.ac.nesc.idsd.bean.SearchResultCell;
import uk.ac.nesc.idsd.bean.SearchResultHeadCell;
import uk.ac.nesc.idsd.bean.SearchResultRow;
import uk.ac.nesc.idsd.bean.StatisticBean;
import uk.ac.nesc.idsd.bean.StatisticResultBean;
import uk.ac.nesc.idsd.exception.ErrorCodes;
import uk.ac.nesc.idsd.exception.UserException;
import uk.ac.nesc.idsd.model.Centre;
import uk.ac.nesc.idsd.model.DsdAssessment;
import uk.ac.nesc.idsd.model.DsdAssessmentDao;
import uk.ac.nesc.idsd.model.DsdCah;
import uk.ac.nesc.idsd.model.DsdCahDao;
import uk.ac.nesc.idsd.model.DsdCahVisit;
import uk.ac.nesc.idsd.model.DsdCahVisitDao;
import uk.ac.nesc.idsd.model.DsdCahVisitEpisode;
import uk.ac.nesc.idsd.model.DsdCahVisitEpisodeDao;
import uk.ac.nesc.idsd.model.DsdCahVisitMedDetail;
import uk.ac.nesc.idsd.model.DsdCahVisitMedDetailDao;
import uk.ac.nesc.idsd.model.DsdGeneAnalysis;
import uk.ac.nesc.idsd.model.DsdGeneAnalysisDao;
import uk.ac.nesc.idsd.model.DsdGeneScreened;
import uk.ac.nesc.idsd.model.DsdGeneScreenedDao;
import uk.ac.nesc.idsd.model.DsdIdentifier;
import uk.ac.nesc.idsd.model.DsdIdentifierDao;
import uk.ac.nesc.idsd.model.DsdNeonatalVisits;
import uk.ac.nesc.idsd.model.DsdSearchCriteria;
import uk.ac.nesc.idsd.model.Parameter;
import uk.ac.nesc.idsd.model.PortalUser;
import uk.ac.nesc.idsd.model.ResearchResult;
import uk.ac.nesc.idsd.security.Anonymization;
import uk.ac.nesc.idsd.security.Authorization;
import uk.ac.nesc.idsd.security.Consent;
import uk.ac.nesc.idsd.service.AuditService;
import uk.ac.nesc.idsd.service.CentreService;
import uk.ac.nesc.idsd.service.DsdIdentifierService;
import uk.ac.nesc.idsd.service.GeneService;
import uk.ac.nesc.idsd.service.ParameterService;
import uk.ac.nesc.idsd.service.UserService;
import uk.ac.nesc.idsd.service.exception.ServiceException;
import uk.ac.nesc.idsd.util.Utility;
import uk.ac.nesc.idsd.util.comparator.DsdAssessmentDateComparator;
import uk.ac.nesc.idsd.util.comparator.DsdGeneScreenedComparator;
import uk.ac.nesc.idsd.util.filters.DsdSearchFilter;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class DsdIdentifierServiceImpl implements DsdIdentifierService, Serializable {
    private static final Log log = LogFactory.getLog(DsdIdentifierServiceImpl.class);

    private static final String notKnown = "Not Known";
    private static final String no = "No";
    private static final String notApplicable = "Not Applicable";
    public static final String COMMA = ",";

    @Autowired
    protected AuditService auditService;
    @Autowired
    private DsdIdentifierDao dsdIdentifierDao;
    @Autowired
    private DsdAssessmentDao dsdAssessmentDao;
    @Autowired
    private DsdGeneAnalysisDao dsdGeneAnalysisDao;
    @Autowired
    private DsdGeneScreenedDao dsdGeneScreenedDao;
    @Autowired
    private DsdCahDao dsdCahDao;
    @Autowired
    private DsdCahVisitDao dsdCahVisitDao;
    @Autowired
    private DsdCahVisitEpisodeDao dsdCahVisitEpisodeDao;
    @Autowired
    private DsdCahVisitMedDetailDao dsdCahVisitMedDetailDao;
    @Autowired
    private ParameterService parameterService;
    @Autowired
    private GeneService geneService;
    @Autowired
    private UserService userService;
    @Autowired
    private CentreService centreService;

    public void delete(DsdIdentifier dsdIdentifier) {
        this.dsdIdentifierDao.delete(dsdIdentifier);
        this.auditService.logDeleteConfirmation(dsdIdentifier);
    }

    public void save(DsdIdentifier dsdIdentifier) throws ServiceException {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        if (null == dsdIdentifier.getUploadTime()) {
            dsdIdentifier.setUploadTime(ts);
        }
        if (null == dsdIdentifier.getLastUpdate()) {
            dsdIdentifier.setLastUpdate(ts);
        }

        Centre co;
        PortalUser u = null;
        try {
            co = this.centreService.getCentreByCentreName(dsdIdentifier.getCentre().getCentreName());
            u = this.userService.getPortalUserByUsername(Utility.getLoginUserName());
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e);
        }
        dsdIdentifier.setCentre(co);

        dsdIdentifier.setUploader(u);
        //mark as completed
        dsdIdentifier.setCompleteness(true);
        //mark as not in deleting process
        dsdIdentifier.setDeleteStatus(false);
        this.dsdIdentifierDao.save(dsdIdentifier);

    }

    public DsdIdentifier saveCore(Long registerId, DsdIdentifier dsdIdentifier) throws ServiceException {
        log.debug(" in saving core service call, dsdIdentifier = " + dsdIdentifier);
        if (registerId == null || registerId == 0L) {
            registerId = dsdIdentifier.getRegisterId();
        }
        if (null == dsdIdentifier) {
            log.warn("Trying to save core info of a null registry record. Return without saving.");
            return null;
        }
        if (null != dsdIdentifier.getCentreName() && !dsdIdentifier.getCentreName().isEmpty()) {
            Centre co = this.centreService.getCentreByCentreName(dsdIdentifier.getCentreName());
            dsdIdentifier.setCentre(co);
        }
        if (null != registerId) {
            DsdIdentifier dbInstance = this.dsdIdentifierDao.findById(registerId);
            dbInstance.setLocalId(dsdIdentifier.getLocalId());
            dbInstance.setYob(dsdIdentifier.getYob());
            dbInstance.setAofp(dsdIdentifier.getAofp());
            dbInstance.setClinician(dsdIdentifier.getClinician());
            dbInstance.setContact(dsdIdentifier.getContact());
            log.info("Saving Core, before save, birthWeight from DB is " + dbInstance.getBirthWeight() + ", setting the birthWeight to " + dsdIdentifier.getBirthWeight());
            dbInstance.setBirthWeight(dsdIdentifier.getBirthWeight());
            log.info("Saving Core, before save, birthLength from DB is " + dbInstance.getBirthLength() + ", setting the birthLength to " + dsdIdentifier.getBirthLength());
            dbInstance.setSexAssigned(dsdIdentifier.getSexAssigned());
            dbInstance.setCurrentGender(dsdIdentifier.getCurrentGender());
            dbInstance.setKaryotype(dsdIdentifier.getKaryotype());
            dbInstance.setDisorderType(dsdIdentifier.getDisorderType());
            dbInstance.setActualDiagnosis(dsdIdentifier.getActualDiagnosis());
            dbInstance.setConsent(dsdIdentifier.getConsent());
//                dbInstance.setSampleShared(dsdIdentifier.getSampleShared());
            dbInstance.setCoreFreeText(dsdIdentifier.getCoreFreeText());
            dbInstance.setLastUpdate(new Timestamp(System.currentTimeMillis()));
            this.dsdIdentifierDao.attachDirty(dbInstance);
            this.auditService.logUpdateCore(dbInstance);
            log.info("Saving Core, after save, birthWeight from DB is " + dbInstance.getBirthWeight());
            log.info("Saving Core, after save, birthLength from DB is " + dbInstance.getBirthLength());
            return dbInstance;
        } else {
            Timestamp ts = new Timestamp(System.currentTimeMillis());
            dsdIdentifier.setUploadTime(ts);
            dsdIdentifier.setLastUpdate(ts);

            if (null == dsdIdentifier.getUploader()) {
                PortalUser u = this.userService.getPortalUserByUsername(Utility.getLoginUserName());
                dsdIdentifier.setUploader(u);
            }
            if (null == dsdIdentifier.getCompleteness()) {
                //mark as completed
                dsdIdentifier.setCompleteness(Boolean.TRUE);
            }
            if (null == dsdIdentifier.getDeleteStatus()) {
                //mark as not in deleting process
                dsdIdentifier.setDeleteStatus(Boolean.FALSE);
            }
            this.dsdIdentifierDao.save(dsdIdentifier);
            this.auditService.logCreateCore(dsdIdentifier);
            return dsdIdentifier;
        }
    }

    public DsdIdentifier saveClinical(Long registerId, DsdIdentifier dsdIdentifier) {
        DsdIdentifier dbInstance = null;
        log.debug(" in saving diagnosis service call");
        if (registerId == null || registerId == 0L) {
            registerId = dsdIdentifier.getRegisterId();
        }
        try {
            dbInstance = this.dsdIdentifierDao.findById(registerId);
            //set values to db object.
            log.info("Saving clinical data, before saving, birthWeight from db is: " + dbInstance.getBirthWeight() + ", setting the weight to " + dsdIdentifier.getBirthWeight());
            dbInstance.setBirthWeight(dsdIdentifier.getBirthWeight());
            log.info("Saving clinical data, before saving, birthLength from db is: " + dbInstance.getBirthLength() + ", setting the length to " + dsdIdentifier.getBirthLength());
            dbInstance.setBirthLength(dsdIdentifier.getBirthLength());
            dbInstance.setAssocMalforms(dsdIdentifier.getAssocMalforms());
            dbInstance.setClinicalFeatures(dsdIdentifier.getClinicalFeatures());
            dbInstance.setBiochemistry(dsdIdentifier.getBiochemistry());
            dbInstance.setDnaAnalysis(dsdIdentifier.getDnaAnalysis());

            dbInstance.setParentalConsanguinity(dsdIdentifier.getParentalConsanguinity());
            dbInstance.setDsdHistory(dsdIdentifier.getDsdHistory());
            dbInstance.setInfertilityHistory(dsdIdentifier.getInfertilityHistory());
            dbInstance.setSampleAvailability(dsdIdentifier.getSampleAvailability());

            dbInstance.setClinicalInfo(dsdIdentifier.getClinicalInfo());
            dbInstance.setCaseNotes(dsdIdentifier.getCaseNotes());
            dbInstance.setGrowthData(dsdIdentifier.getGrowthData());
            dbInstance.setPubertyData(dsdIdentifier.getPubertyData());
            dbInstance.setDna(dsdIdentifier.getDna());
            dbInstance.setTissue(dsdIdentifier.getTissue());
            dbInstance.setCellLine(dsdIdentifier.getCellLine());
            dbInstance.setCellLineInfo(dsdIdentifier.getCellLineInfo());
            dbInstance.setUrine(dsdIdentifier.getUrine());
            dbInstance.setSerum(dsdIdentifier.getSerum());
            dbInstance.setSampleShared(dsdIdentifier.getSampleShared());

            dbInstance.setFreeText(dsdIdentifier.getFreeText());
            dbInstance.setSampleConsent(dsdIdentifier.getSampleConsent());
            dbInstance.setLastUpdate(new Timestamp(System.currentTimeMillis()));

            this.dsdIdentifierDao.attachDirty(dbInstance);
            log.info("After saving clinical, the birthWeight from DB = " + dbInstance.getBirthWeight());
            log.info("After saving clinical, the birthLength from DB = " + dbInstance.getBirthLength());
        } catch (Exception e) {
            log.error("Error occurred while saving DSD record into database.", e);
        }
        this.auditService.logUpdateClinicalHistoryAndBiomaterials(dbInstance);
        return dbInstance;
    }

    public DsdIdentifier saveAssessments(Long registerId, DsdIdentifier dsdIdentifier) {
        DsdIdentifier dbInstance = null;
        log.debug(" in saving assessments service call");
        log.debug("assessment size = " + dsdIdentifier.getDsdAssessments().size());
        if (registerId == null || registerId == 0L) {
            registerId = dsdIdentifier.getRegisterId();
        }
        dbInstance = this.dsdIdentifierDao.findById(registerId);
        if (null != dsdIdentifier.getDsdAssessments() && !dsdIdentifier.getDsdAssessments().isEmpty()) {
            for (DsdAssessment dsdAssessment : dsdIdentifier.getDsdAssessments()) {
                dsdAssessment.setDsdIdentifier(dbInstance);
                dbInstance.getDsdAssessments().add(dsdAssessment);
            }
        }

        dsdIdentifier.setLastUpdate(new Timestamp(System.currentTimeMillis()));

        this.dsdIdentifierDao.attachDirty(dbInstance);
//            log.debug("dbInstance new value = " + dbInstance.getDsdAssessments().size());
        return dbInstance;
    }

    public DsdIdentifier saveGeneAnalysis(Long registerId, DsdGeneAnalysis dsdGeneAnalysis, List<DsdGeneScreened> dsdGeneScreeneds) throws ServiceException {

        log.debug(" in saving gene analysis service call");
        if (registerId == null || registerId == 0L) {
            throw new ServiceException("Must give registerId to save gene analysis instance.");
        }
        DsdIdentifier dsdIdentifier = this.dsdIdentifierDao.findById(registerId);
        if (null == dsdIdentifier) {
            throw new ServiceException("Cannot find the dsd case by registerId: " + registerId);
        }
        try {
            DsdGeneAnalysis dbDsdGeneAnalysis = this.dsdGeneAnalysisDao.findById(registerId);
            Set<DsdGeneScreened> dsdGeneScreenedSet = new TreeSet<DsdGeneScreened>(new DsdGeneScreenedComparator());
            if (null == dbDsdGeneAnalysis) {
//                log.debug("don't have any dsdGeneAnalysis stored in db");
                if (null != dsdGeneScreeneds && !dsdGeneScreeneds.isEmpty()) {
                    for (DsdGeneScreened dgs : dsdGeneScreeneds) {
                        dgs.setDsdGeneAnalysis(dsdGeneAnalysis);
                        dsdGeneScreenedSet.add(dgs);
                    }
                    dsdGeneAnalysis.setDsdGeneScreeneds(dsdGeneScreenedSet);
                }
                dsdGeneAnalysis.setDsdIdentifier(dsdIdentifier);
                dsdIdentifier.setDsdGeneAnalysis(dsdGeneAnalysis);
                dsdIdentifier.setLastUpdate(new Timestamp(System.currentTimeMillis()));
                this.dsdIdentifierDao.attachDirty(dsdIdentifier);
                this.auditService.logCreateGeneAnalysis(dsdIdentifier);
            } else { // if db has dsdGeneAnalysis stored in db.
                //copy dsdGeneAnalysis values to db instance.
//                log.debug("found dsdGeneAnalysis stored in db");
//                log.debug("cgh in service = " + dsdGeneAnalysis.getCgh());
                dbDsdGeneAnalysis.setCgh(dsdGeneAnalysis.getCgh());
                dbDsdGeneAnalysis.setCghDetail(dsdGeneAnalysis.getCghDetail());
                dbDsdGeneAnalysis.setChromosomalRearrangement(dsdGeneAnalysis.getChromosomalRearrangement());
                dbDsdGeneAnalysis.setChromosomalRearrangementDetail(dsdGeneAnalysis.getChromosomalRearrangementDetail());
                dbDsdGeneAnalysis.setFunctionalStudy(dsdGeneAnalysis.getFunctionalStudy());
                dbDsdGeneAnalysis.setFunctionalStudyDetail(dsdGeneAnalysis.getFunctionalStudyDetail());
                dbDsdGeneAnalysis.setFurtherStudies(dsdGeneAnalysis.getFurtherStudies());
                dbDsdGeneAnalysis.setGeneticAnalysis(dsdGeneAnalysis.getGeneticAnalysis());
                dbDsdGeneAnalysis.setPublishDetail(dsdGeneAnalysis.getPublishDetail());
                dbDsdGeneAnalysis.setPublishedCase(dsdGeneAnalysis.getPublishedCase());
                dbDsdGeneAnalysis.setSingleGeneAnalysis(dsdGeneAnalysis.getSingleGeneAnalysis());

                //copy dsdGeneScreened to db instance.
                Map<Long, DsdGeneScreened> geneScreensMap = new HashMap<Long, DsdGeneScreened>();
                //sort dsdGeneScreened into a map
                if (null != dbDsdGeneAnalysis.getDsdGeneScreeneds() && !dbDsdGeneAnalysis.getDsdGeneScreeneds().isEmpty()) {
                    for (DsdGeneScreened dgs : dbDsdGeneAnalysis.getDsdGeneScreeneds()) {
                        geneScreensMap.put(dgs.getScreenId(), dgs);
                    }
                }
                //loop through each new dsdGeneScreened.
                if (null != dsdGeneScreeneds && !dsdGeneScreeneds.isEmpty()) {
//                	log.debug("gene screened size = " + dsdGeneScreeneds.size());
                    for (DsdGeneScreened dgs : dsdGeneScreeneds) {
                        if (null != dgs.getScreenId() && dgs.getScreenId() != 0L) {
                            DsdGeneScreened dsdGeneScreened = geneScreensMap.get(dgs.getScreenId());
                            if (null != dsdGeneScreened) {
                                dsdGeneScreened.setDirectSequence(dgs.getDirectSequence());
                                dsdGeneScreened.setGene(dgs.getGene());
                                dsdGeneScreened.setMutationDetail(dgs.getMutationDetail());
                                dsdGeneScreened.setOtherSequence(dgs.getOtherSequence());
                                dsdGeneScreened.setMutationFound(dgs.getMutationFound());
                                dsdGeneScreened.setDsdGeneAnalysis(dbDsdGeneAnalysis);
                                dsdGeneScreenedSet.add(dsdGeneScreened);
                                geneScreensMap.remove(dgs.getScreenId());
                            } else {
                                dgs.setDsdGeneAnalysis(dbDsdGeneAnalysis);
                                dsdGeneScreenedSet.add(dgs);
                            }
                        } else {
                            dgs.setDsdGeneAnalysis(dbDsdGeneAnalysis);
                            dsdGeneScreenedSet.add(dgs);
                        }
                    }
                    dbDsdGeneAnalysis.setDsdGeneScreeneds(dsdGeneScreenedSet);
                }
                dsdIdentifier.setDsdGeneAnalysis(dbDsdGeneAnalysis);
                dbDsdGeneAnalysis.setDsdIdentifier(dsdIdentifier);
                dsdIdentifier.setLastUpdate(new Timestamp(System.currentTimeMillis()));
                this.dsdIdentifierDao.attachDirty(dsdIdentifier);
//                log.debug("deleting screened gene records size = " + geneScreensMap.size());
                for (Long screenId : geneScreensMap.keySet()) {
                    DsdGeneScreened toDelete = this.dsdGeneScreenedDao.findById(screenId);
                    if (null != toDelete) {
                        this.dsdGeneScreenedDao.delete(toDelete);
                    }
                }
                this.auditService.logUpdateGeneAnalysis(dsdIdentifier);
            }
        } catch (Exception e) {
            log.error("Error occurred while saving DSD record into database.", e);
        }
        return dsdIdentifier;
    }

    public DsdIdentifier saveCah(DsdIdentifier dsdIdentifier, DsdCah dsdCah) throws ServiceException {
        DsdCah dbDsdCah;
        log.debug("Saving CAH: " + dsdCah);
        DsdIdentifier dbDsdIdentifier = dsdIdentifier;
        try {
            dbDsdIdentifier = this.dsdIdentifierDao.findById(dsdIdentifier.getRegisterId());
            dbDsdIdentifier.setAofp(dsdIdentifier.getAofp());
            log.info("Saving CAH, before saving, birthWeight from db is: " + dbDsdIdentifier.getBirthWeight() + ", setting the weight to " + dsdCah.getDsdIdentifier().getBirthWeight());
            dbDsdIdentifier.setBirthWeight(dsdCah.getDsdIdentifier().getBirthWeight());
            log.info("Saving CAH, before saving, birthLength from db is: " + dbDsdIdentifier.getBirthLength() + ", setting the length to " + dsdCah.getDsdIdentifier().getBirthLength());
            dbDsdIdentifier.setBirthLength(dsdCah.getDsdIdentifier().getBirthLength());

            dbDsdCah = dbDsdIdentifier.getDsdCah();
            if (null == dbDsdCah) {
                dbDsdIdentifier.setDsdCah(dsdCah);
                dsdCah.setDsdIdentifier(dbDsdIdentifier);
                this.dsdCahDao.save(dsdCah);
                this.auditService.logCreateCah(dbDsdIdentifier);
            } else {
                //set values to db object.
                dbDsdCah.setPrenatalDiagnosis(dsdCah.getPrenatalDiagnosis());
                dbDsdCah.setPrenatalDexamethasoneTherapy(dsdCah.getPrenatalDexamethasoneTherapy());
                dbDsdCah.setFatherHeight(dsdCah.getFatherHeight());
                dbDsdCah.setMotherHeight(dsdCah.getMotherHeight());
                dbDsdCah.setTargetHeight(dsdCah.getTargetHeight());
                dbDsdCah.setPraderStageAtFirstPresentation(dsdCah.getPraderStageAtFirstPresentation());
                dbDsdCah.setSwCrisisAtPresentation(dsdCah.getSwCrisisAtPresentation());
                dbDsdCah.setAdrenalCrisesAfterFirstPresentation(dsdCah.getAdrenalCrisesAfterFirstPresentation());
                dbDsdCah.setGestationalAge(dsdCah.getGestationalAge());
                dbDsdCah.setCurrentSaltReplacement(dsdCah.getCurrentSaltReplacement());
//                dbDsdCah.setCurrentGcReplacement(dsdCah.getCurrentGcReplacement());
                dbDsdCah.setCurrentFcTreatment(dsdCah.getCurrentFcTreatment());
                dbDsdCah.setCahFreeText(dsdCah.getCahFreeText());
                this.dsdCahDao.attachDirty(dbDsdCah);
                log.info("DsdCah ID = " + dbDsdCah.getCahId());
                this.auditService.logUpdateCah(dbDsdIdentifier);
            }
            this.dsdIdentifierDao.attachDirty(dbDsdIdentifier);
            log.info("Save CAH, after save, birth weight from DB = " + dbDsdIdentifier.getBirthWeight());
            log.info("Save CAH, after save, birth Length from DB = " + dbDsdIdentifier.getBirthLength());
        } catch (Exception e) {
            log.error("Error occurred while saving DSD record into database.", e);
        }
        return dbDsdIdentifier;
    }

    @Override
    public DsdCahVisit getCahVisitById(Long dsdCahVisitId) {
        return this.dsdCahVisitDao.findById(dsdCahVisitId);
    }

    @Override
    public DsdCahVisit createNewTransitCahVisitInstance(DsdIdentifier dsdIdentifier) {
        DsdCahVisit lastVisit = null;
        try {
            if (dsdIdentifier != null && dsdIdentifier.getRegisterId() != 0 && dsdIdentifier.getDsdCah() != null) {
                DsdCah dsdCah = this.dsdCahDao.findById(dsdIdentifier.getDsdCah().getCahId());
                log.info("When creating new transit cahVisit instance, dsdCah = " + dsdCah + ", dsdCahVisit size = " + dsdCah.getDsdCahVisits());
                Set<DsdCahVisit> dsdCahVisits = dsdCah.getDsdCahVisits();
                lastVisit = getLatestDsdCahVisit(dsdCahVisits);
                log.info("Found latest visit is: " + lastVisit);
            } else {
                log.info("DsdIdentifier passed to createNewTransitCahVisitInstance is null");
            }
        } catch (Exception e) {
            log.error("Exception while creating a new visit instance", e);
        }
        return createNewDsdCahVisitInstance(lastVisit);
    }

    protected DsdCahVisit createNewDsdCahVisitInstance(DsdCahVisit lastCahVisit) {
        DsdCahVisit dsdCahVisit = new DsdCahVisit();

        if (lastCahVisit != null) {
            dsdCahVisit.setWeight(lastCahVisit.getWeight());
            dsdCahVisit.setHeight(lastCahVisit.getHeight());
            dsdCahVisit.setWaistCircumference(lastCahVisit.getWaistCircumference());
            dsdCahVisit.setHipCircumference(lastCahVisit.getHipCircumference());
//            dsdCahVisit.setBmi(lastCahVisit.getBmi());
//            dsdCahVisit.setBsa(lastCahVisit.getBsa());
            dsdCahVisit.setCushingoid(lastCahVisit.getCushingoid());
            dsdCahVisit.setVirilisation(lastCahVisit.getVirilisation());

            //medications
//            dsdCahVisit.setGlucocorticoid(notKnown);
            dsdCahVisit.setFludrocortisone(lastCahVisit.getFludrocortisone());
            dsdCahVisit.setFludrocortisoneDose(lastCahVisit.getFludrocortisoneDose());
            dsdCahVisit.setFludrocortisoneFrequency(lastCahVisit.getFludrocortisoneFrequency());
            dsdCahVisit.setFludrocortisoneNote(lastCahVisit.getFludrocortisoneNote());
            dsdCahVisit.setCurrentGcReplacement(lastCahVisit.getCurrentGcReplacement());
//            dsdCahVisit.setCurrentFcTreatment(lastCahVisit.getCurrentFcTreatment());
            dsdCahVisit.setSaltReplacement(lastCahVisit.getSaltReplacement());
            dsdCahVisit.setSaltReplacementNote(lastCahVisit.getSaltReplacementNote());
            dsdCahVisit.setOestrogen(lastCahVisit.getOestrogen());
            dsdCahVisit.setOestrogenNote(lastCahVisit.getOestrogenNote());
            dsdCahVisit.setTestosterone(lastCahVisit.getTestosterone());
            dsdCahVisit.setTestosteroneNote(lastCahVisit.getTestosteroneNote());
            dsdCahVisit.setGnrhAnalogues(lastCahVisit.getGnrhAnalogues());
            dsdCahVisit.setGnrhAnaloguesNote(lastCahVisit.getGnrhAnaloguesNote());
            dsdCahVisit.setAntihypertensives(lastCahVisit.getAntihypertensives());
            dsdCahVisit.setAntihypertensivesNote(lastCahVisit.getAntihypertensivesNote());
            dsdCahVisit.setAntidiabetic(lastCahVisit.getAntidiabetic());
            dsdCahVisit.setAntidiabeticNote(lastCahVisit.getAntidiabeticNote());
            dsdCahVisit.setAntidepressants(lastCahVisit.getAntidepressants());
            dsdCahVisit.setAntidepressantsNote(lastCahVisit.getAntidepressantsNote());
            dsdCahVisit.setOtherDrugs(lastCahVisit.getOtherDrugs());
            dsdCahVisit.setOtherDrugsNote(lastCahVisit.getOtherDrugsNote());

            //comorbid conditions and surgery
            dsdCahVisit.setEua(lastCahVisit.getEua());
            dsdCahVisit.setEuaNote(lastCahVisit.getEuaNote());
            dsdCahVisit.setGenitaliaSurgery(lastCahVisit.getGenitaliaSurgery());
            dsdCahVisit.setGenitaliaSurgeryNote(lastCahVisit.getGenitaliaSurgeryNote());
            dsdCahVisit.setOtherSurgery(lastCahVisit.getOtherSurgery());
            dsdCahVisit.setOtherSurgeryNote(lastCahVisit.getOtherSurgeryNote());
            dsdCahVisit.setOtherCongenitalAbnormalities(lastCahVisit.getOtherCongenitalAbnormalities());
            dsdCahVisit.setOtherCongenitalAbnormalitiesNote(lastCahVisit.getOtherCongenitalAbnormalitiesNote());
            dsdCahVisit.setDiabetesMellitusType1(lastCahVisit.getDiabetesMellitusType1());
            dsdCahVisit.setDiabetesMellitusType1Note(lastCahVisit.getDiabetesMellitusType1Note());
            dsdCahVisit.setDiabetesMellitusType2(lastCahVisit.getDiabetesMellitusType2());
            dsdCahVisit.setDiabetesMellitusType2Note(lastCahVisit.getDiabetesMellitusType2Note());
            dsdCahVisit.setHypertension(lastCahVisit.getHypertension());
            dsdCahVisit.setHypertensionNote(lastCahVisit.getHypertensionNote());
            dsdCahVisit.setThyroidDisease(lastCahVisit.getThyroidDisease());
            dsdCahVisit.setThyroidDiseaseNote(lastCahVisit.getThyroidDiseaseNote());
            dsdCahVisit.setOsteoporosis(lastCahVisit.getOsteoporosis());
            dsdCahVisit.setOsteoporosisNote(lastCahVisit.getOsteoporosisNote());
            dsdCahVisit.setStroke(lastCahVisit.getStroke());
            dsdCahVisit.setStrokeNote(lastCahVisit.getStrokeNote());
            dsdCahVisit.setCardiovascularDisease(lastCahVisit.getCardiovascularDisease());
            dsdCahVisit.setCardiovascularDiseaseNote(lastCahVisit.getFludrocortisoneNote());
            dsdCahVisit.setSmoking(lastCahVisit.getSmoking());
            dsdCahVisit.setSmokingNote(lastCahVisit.getSmokingNote());
            dsdCahVisit.setAnaemia(lastCahVisit.getAnaemia());
            dsdCahVisit.setAnaemiaNote(lastCahVisit.getAnaemiaNote());
            dsdCahVisit.setDepression(lastCahVisit.getDepression());
            dsdCahVisit.setDepressionNote(lastCahVisit.getDepressionNote());
            dsdCahVisit.setAnxiety(lastCahVisit.getAnxiety());
            dsdCahVisit.setAnxietyNote(lastCahVisit.getAnxietyNote());
            dsdCahVisit.setPsychosis(lastCahVisit.getPsychosis());
            dsdCahVisit.setPsychosisNote(lastCahVisit.getPsychosisNote());
            dsdCahVisit.setOtherMentalHealthProblem(lastCahVisit.getOtherMentalHealthProblem());
            dsdCahVisit.setOtherMentalHealthProblemNote(lastCahVisit.getOtherMentalHealthProblemNote());
            dsdCahVisit.setJointHypermobility(lastCahVisit.getJointHypermobility());
            dsdCahVisit.setJointHypermobilityNote(lastCahVisit.getJointHypermobilityNote());
            dsdCahVisit.setOtherComorbidCondition(lastCahVisit.getOtherComorbidCondition());
            dsdCahVisit.setOtherComorbidConditionNote(lastCahVisit.getOtherComorbidConditionNote());

            //male options
            dsdCahVisit.setMaleGenitalStage(lastCahVisit.getMaleGenitalStage());
            dsdCahVisit.setMalePubicHairStage(lastCahVisit.getMalePubicHairStage());
            dsdCahVisit.setMaleAxillaryHairStage(lastCahVisit.getMaleAxillaryHairStage());
            dsdCahVisit.setTesticularVolumeLeft(lastCahVisit.getTesticularVolumeLeft());
            dsdCahVisit.setTesticularVolumeRight(lastCahVisit.getTesticularVolumeRight());
            dsdCahVisit.setTesticularTexture(lastCahVisit.getTesticularTexture());
            dsdCahVisit.setEvidenceOfTart(lastCahVisit.getEvidenceOfTart());
            dsdCahVisit.setMaleFertilityDesired(lastCahVisit.getMaleFertilityDesired());
            dsdCahVisit.setMaleFertilityAttempted(lastCahVisit.getMaleFertilityAttempted());
            dsdCahVisit.setSpermStorage(lastCahVisit.getSpermStorage());
            dsdCahVisit.setPartnerPastPregnancyNumber(lastCahVisit.getPartnerPastPregnancyNumber());
            dsdCahVisit.setMaleAssistedConceptionHistory(lastCahVisit.getMaleAssistedConceptionHistory());
            dsdCahVisit.setMaleAssistedConceptionHistoryNote(lastCahVisit.getMaleAssistedConceptionHistoryNote());

            //female options
            dsdCahVisit.setFemaleBreastStage(lastCahVisit.getFemaleBreastStage());
            dsdCahVisit.setFemalePubicHairStage(lastCahVisit.getFemalePubicHairStage());
            dsdCahVisit.setFemaleAxillaryHairStage(lastCahVisit.getFemaleAxillaryHairStage());
            dsdCahVisit.setMenarche(lastCahVisit.getMenarche());
            dsdCahVisit.setAgeAtMenarche(lastCahVisit.getAgeAtMenarche());
            dsdCahVisit.setRegularMenstrualCycle(lastCahVisit.getRegularMenstrualCycle());
            dsdCahVisit.setFemaleFertilityDesired(lastCahVisit.getFemaleFertilityDesired());
            dsdCahVisit.setPastPregnancyNumber(lastCahVisit.getPastPregnancyNumber());
            dsdCahVisit.setLiveBirthNumber(lastCahVisit.getLiveBirthNumber());
            dsdCahVisit.setFemaleAssistedConceptionHistory(lastCahVisit.getFemaleAssistedConceptionHistory());
            dsdCahVisit.setFemaleAssistedConceptionHistoryNote(lastCahVisit.getFemaleAssistedConceptionHistoryNote());
        } else {
            dsdCahVisit.setCushingoid(notKnown);
            dsdCahVisit.setVirilisation(notKnown);
            dsdCahVisit.setGlucocorticoid(notKnown);
            dsdCahVisit.setEua(notKnown);

            dsdCahVisit.setGenitaliaSurgery(notKnown);
            dsdCahVisit.setOtherSurgery(notKnown);
            dsdCahVisit.setOtherCongenitalAbnormalities(notKnown);
            dsdCahVisit.setDiabetesMellitusType1(notKnown);
            dsdCahVisit.setDiabetesMellitusType2(notKnown);
            dsdCahVisit.setHypertension(notKnown);
            dsdCahVisit.setThyroidDisease(notKnown);
            dsdCahVisit.setOsteoporosis(notKnown);
            dsdCahVisit.setStroke(notKnown);
            dsdCahVisit.setCardiovascularDisease(notKnown);
            dsdCahVisit.setAnaemia(notKnown);
            dsdCahVisit.setDepression(notKnown);
            dsdCahVisit.setAnxiety(notKnown);
            dsdCahVisit.setPsychosis(notKnown);
            dsdCahVisit.setOtherMentalHealthProblem(notKnown);
            dsdCahVisit.setJointHypermobility(notKnown);
            dsdCahVisit.setOtherComorbidCondition(notKnown);
            dsdCahVisit.setBoneMineralDensityDone(notKnown);
            dsdCahVisit.setEvidenceOfTart(notKnown);
        }
        return dsdCahVisit;
    }

    protected DsdCahVisit getLatestDsdCahVisit(final Set<DsdCahVisit> dsdCahVisits) {
        DsdCahVisit lastVisit = null;
        if (CollectionUtils.isNotEmpty(dsdCahVisits)) {
            List<DsdCahVisit> visitList = new ArrayList<>(dsdCahVisits);
            Collections.sort(visitList, new Comparator<DsdCahVisit>() {
                @Override
                public int compare(DsdCahVisit v1, DsdCahVisit v2) {
                    if (v2.getDate().compareTo(v1.getDate()) == 0) {
                        return v2.getDsdCahVisitId().compareTo(v1.getDsdCahVisitId());
                    } else {
                        return v2.getDate().compareTo(v1.getDate());
                    }
                }
            });
            lastVisit = visitList.get(0);
        }
        return lastVisit;
    }

    @Override
    public DsdCahVisit saveCahVisit(DsdIdentifier dsdIdentifier, DsdCahVisit dsdCahVisit) throws ServiceException {
        DsdIdentifier dbDsdIdentifier;
        DsdCah dsdCah;
        DsdCahVisit dbInstance;
        try {
            dbDsdIdentifier = this.dsdIdentifierDao.findById(dsdIdentifier.getRegisterId());
            dsdCah = dbDsdIdentifier.getDsdCah();
        } catch (Exception e) {
            throw new ServiceException("Error occurred while looking up CAH visit record from database.", e);
        }
        try {
            if (null != dsdCahVisit && null != dsdCahVisit.getDsdCahVisitId()) {
                log.info("Updating existing CAH visit");
                dbInstance = this.dsdCahVisitDao.findById(dsdCahVisit.getDsdCahVisitId());
                dbInstance.setDate(dsdCahVisit.getDate());
                dbInstance.setAge(dsdCahVisit.getAge());
                dbInstance.setWeight(dsdCahVisit.getWeight());
                dbInstance.setHeight(dsdCahVisit.getHeight());
                dbInstance.setWaistCircumference(dsdCahVisit.getWaistCircumference());
                dbInstance.setHipCircumference(dsdCahVisit.getHipCircumference());
                dbInstance.setBmi(dsdCahVisit.getBmi());
                dbInstance.setBsa(dsdCahVisit.getBsa());
                dbInstance.setCushingoid(dsdCahVisit.getCushingoid());
                dbInstance.setVirilisation(dsdCahVisit.getVirilisation());
                dbInstance.setBpSystolic(dsdCahVisit.getBpSystolic());
                dbInstance.setBpDiastolic(dsdCahVisit.getBpDiastolic());
                dbInstance.setSickDayEpisodes(dsdCahVisit.getSickDayEpisodes());
                dbInstance.setSickDayTotal(dsdCahVisit.getSickDayTotal());
                dbInstance.setDsdCahVisitEpisodes(dsdCahVisit.getDsdCahVisitEpisodes());
                dbInstance.setDailyAdherenceToTherapy(dsdCahVisit.getDailyAdherenceToTherapy());
                dbInstance.setTreatmentChange(dsdCahVisit.getTreatmentChange());
                dbInstance.setTreatmentChangeReason(dsdCahVisit.getTreatmentChangeReason());
                dbInstance.setGlucocorticoid(dsdCahVisit.getGlucocorticoid());
                dbInstance.setGlucocorticoidDetail(dsdCahVisit.getGlucocorticoidDetail());
                dbInstance.setGlucocorticoidNote(dsdCahVisit.getGlucocorticoidNote());
                dbInstance.setFludrocortisone(dsdCahVisit.getFludrocortisone());
                dbInstance.setFludrocortisoneDose(dsdCahVisit.getFludrocortisoneDose());
                dbInstance.setFludrocortisoneFrequency(dsdCahVisit.getFludrocortisoneFrequency());
                dbInstance.setFludrocortisoneNote(dsdCahVisit.getFludrocortisoneNote());
                dbInstance.setCurrentGcReplacement(dsdCahVisit.getCurrentGcReplacement());
//                dbInstance.setCurrentFcTreatment(dsdCahVisit.getCurrentFcTreatment());
                dbInstance.setSaltReplacement(dsdCahVisit.getSaltReplacement());
                dbInstance.setSaltReplacementDose(dsdCahVisit.getSaltReplacementDose());
                dbInstance.setSaltReplacementDoseUnit(dsdCahVisit.getSaltReplacementDoseUnit());
                dbInstance.setSaltReplacementFrequency(dsdCahVisit.getSaltReplacementFrequency());
                dbInstance.setSaltReplacementNote(dsdCahVisit.getSaltReplacementNote());
                dbInstance.setOestrogen(dsdCahVisit.getOestrogen());
                dbInstance.setOestrogenNote(dsdCahVisit.getOestrogenNote());
                dbInstance.setTestosterone(dsdCahVisit.getTestosterone());
                dbInstance.setTestosteroneNote(dsdCahVisit.getTestosteroneNote());
                dbInstance.setGnrhAnalogues(dsdCahVisit.getGnrhAnalogues());
                dbInstance.setGnrhAnaloguesNote(dsdCahVisit.getGnrhAnaloguesNote());
                dbInstance.setAntihypertensives(dsdCahVisit.getAntihypertensives());
                dbInstance.setAntihypertensivesNote(dsdCahVisit.getAntihypertensivesNote());
                dbInstance.setAntidiabetic(dsdCahVisit.getAntidiabetic());
                dbInstance.setAntidiabeticNote(dsdCahVisit.getAntidiabeticNote());
                dbInstance.setAntidepressants(dsdCahVisit.getAntidepressants());
                dbInstance.setAntidepressantsNote(dsdCahVisit.getAntidepressantsNote());
                dbInstance.setOtherDrugs(dsdCahVisit.getOtherDrugs());
                dbInstance.setOtherDrugsNote(dsdCahVisit.getOtherDrugsNote());
                dbInstance.setGenitaliaSurgery(dsdCahVisit.getGenitaliaSurgery());
                dbInstance.setGenitaliaSurgeryNote(dsdCahVisit.getGenitaliaSurgeryNote());
                dbInstance.setOtherSurgery(dsdCahVisit.getOtherSurgery());
                dbInstance.setOtherSurgeryNote(dsdCahVisit.getOtherSurgeryNote());
                dbInstance.setOtherCongenitalAbnormalities(dsdCahVisit.getOtherCongenitalAbnormalities());
                dbInstance.setOtherCongenitalAbnormalitiesNote(dsdCahVisit.getOtherCongenitalAbnormalitiesNote());
                dbInstance.setDiabetesMellitusType1(dsdCahVisit.getDiabetesMellitusType1());
                dbInstance.setDiabetesMellitusType1Note(dsdCahVisit.getDiabetesMellitusType1Note());
                dbInstance.setDiabetesMellitusType2(dsdCahVisit.getDiabetesMellitusType2());
                dbInstance.setDiabetesMellitusType2Note(dsdCahVisit.getDiabetesMellitusType2Note());
                dbInstance.setHypertension(dsdCahVisit.getHypertension());
                dbInstance.setHypertensionNote(dsdCahVisit.getHypertensionNote());
                dbInstance.setThyroidDisease(dsdCahVisit.getThyroidDisease());
                dbInstance.setThyroidDiseaseNote(dsdCahVisit.getThyroidDiseaseNote());
                dbInstance.setOsteoporosis(dsdCahVisit.getOsteoporosis());
                dbInstance.setOsteoporosisNote(dsdCahVisit.getOsteoporosisNote());
                dbInstance.setStroke(dsdCahVisit.getStroke());
                dbInstance.setStrokeNote(dsdCahVisit.getStrokeNote());
                dbInstance.setCardiovascularDisease(dsdCahVisit.getCardiovascularDisease());
                dbInstance.setCardiovascularDiseaseNote(dsdCahVisit.getCardiovascularDiseaseNote());
                dbInstance.setSmoking(dsdCahVisit.getSmoking());
                dbInstance.setSmokingNote(dsdCahVisit.getSmokingNote());
                dbInstance.setAnaemia(dsdCahVisit.getAnaemia());
                dbInstance.setAnaemiaNote(dsdCahVisit.getAnaemiaNote());
                dbInstance.setDepression(dsdCahVisit.getDepression());
                dbInstance.setDepressionNote(dsdCahVisit.getDepressionNote());
                dbInstance.setAnxiety(dsdCahVisit.getAnxiety());
                dbInstance.setAnxietyNote(dsdCahVisit.getAnxietyNote());
                dbInstance.setPsychosis(dsdCahVisit.getPsychosis());
                dbInstance.setPsychosisNote(dsdCahVisit.getPsychosisNote());
                dbInstance.setOtherMentalHealthProblem(dsdCahVisit.getOtherMentalHealthProblem());
                dbInstance.setOtherMentalHealthProblemNote(dsdCahVisit.getOtherMentalHealthProblemNote());
                dbInstance.setJointHypermobility(dsdCahVisit.getJointHypermobility());
                dbInstance.setJointHypermobilityNote(dsdCahVisit.getJointHypermobilityNote());
                dbInstance.setOtherComorbidCondition(dsdCahVisit.getOtherComorbidCondition());
                dbInstance.setOtherComorbidConditionNote(dsdCahVisit.getOtherComorbidConditionNote());
                dbInstance.setSodium(dsdCahVisit.getSodium());
                dbInstance.setSodiumDate(dsdCahVisit.getSodiumDate());
                dbInstance.setSodiumTime(dsdCahVisit.getSodiumTime());
                dbInstance.setSodiumNote(dsdCahVisit.getSodiumNote());
                dbInstance.setPotassium(dsdCahVisit.getPotassium());
                dbInstance.setPotassiumDate(dsdCahVisit.getPotassiumDate());
                dbInstance.setPotassiumTime(dsdCahVisit.getPotassiumTime());
                dbInstance.setPotassiumNote(dsdCahVisit.getPotassiumNote());
                dbInstance.setDeoxycortisol11(dsdCahVisit.getDeoxycortisol11());
                dbInstance.setDeoxycortisol11Date(dsdCahVisit.getDeoxycortisol11Date());
                dbInstance.setDeoxycortisol11Time(dsdCahVisit.getDeoxycortisol11Time());
                dbInstance.setDeoxycortisol11Note(dsdCahVisit.getDeoxycortisol11Note());
                dbInstance.setRenin(dsdCahVisit.getRenin());
                dbInstance.setReninDate(dsdCahVisit.getReninDate());
                dbInstance.setReninTime(dsdCahVisit.getReninTime());
                dbInstance.setReninNote(dsdCahVisit.getReninNote());
                dbInstance.setPlasmaReninActivity(dsdCahVisit.getPlasmaReninActivity());
                dbInstance.setPlasmaReninActivityDate(dsdCahVisit.getPlasmaReninActivityDate());
                dbInstance.setPlasmaReninActivityTime(dsdCahVisit.getPlasmaReninActivityTime());
                dbInstance.setPlasmaReninActivityNote(dsdCahVisit.getPlasmaReninActivityNote());
                dbInstance.setOhp17(dsdCahVisit.getOhp17());
                dbInstance.setOhp17Date(dsdCahVisit.getOhp17Date());
                dbInstance.setOhp17Time(dsdCahVisit.getOhp17Time());
                dbInstance.setOhp17Note(dsdCahVisit.getOhp17Note());
                dbInstance.setAndostenedione(dsdCahVisit.getAndostenedione());
                dbInstance.setAndostenedioneDate(dsdCahVisit.getAndostenedioneDate());
                dbInstance.setAndostenedioneTime(dsdCahVisit.getAndostenedioneTime());
                dbInstance.setAndostenedioneNote(dsdCahVisit.getAndostenedioneNote());
                dbInstance.setTotalTestosterone(dsdCahVisit.getTotalTestosterone());
                dbInstance.setTotalTestosteroneDate(dsdCahVisit.getTotalTestosteroneDate());
                dbInstance.setTotalTestosteroneTime(dsdCahVisit.getTotalTestosteroneTime());
                dbInstance.setTotalTestosteroneNote(dsdCahVisit.getTotalTestosteroneNote());
                dbInstance.setDihydrotestosterone(dsdCahVisit.getDihydrotestosterone());
                dbInstance.setDihydrotestosteroneDate(dsdCahVisit.getDihydrotestosteroneDate());
                dbInstance.setDihydrotestosteroneTime(dsdCahVisit.getDihydrotestosteroneTime());
                dbInstance.setDihydrotestosteroneNote(dsdCahVisit.getDihydrotestosteroneNote());
                dbInstance.setLh(dsdCahVisit.getLh());
                dbInstance.setLhDate(dsdCahVisit.getLhDate());
                dbInstance.setLhTime(dsdCahVisit.getLhTime());
                dbInstance.setLhNote(dsdCahVisit.getLhNote());
                dbInstance.setFsh(dsdCahVisit.getFsh());
                dbInstance.setFshDate(dsdCahVisit.getFshDate());
                dbInstance.setFshTime(dsdCahVisit.getFshTime());
                dbInstance.setFshNote(dsdCahVisit.getFshNote());
                dbInstance.setOestradiol(dsdCahVisit.getOestradiol());
                dbInstance.setOestradiolDate(dsdCahVisit.getOestradiolDate());
                dbInstance.setOestradiolTime(dsdCahVisit.getOestradiolTime());
                dbInstance.setOestradiolNote(dsdCahVisit.getOestradiolNote());
                dbInstance.setProgesterone(dsdCahVisit.getProgesterone());
                dbInstance.setProgesteroneDate(dsdCahVisit.getProgesteroneDate());
                dbInstance.setProgesteroneTime(dsdCahVisit.getProgesteroneTime());
                dbInstance.setProgesteroneNote(dsdCahVisit.getProgesteroneNote());
                dbInstance.setInhibinB(dsdCahVisit.getInhibinB());
                dbInstance.setInhibinBDate(dsdCahVisit.getInhibinBDate());
                dbInstance.setInhibinBTime(dsdCahVisit.getInhibinBTime());
                dbInstance.setInhibinBNote(dsdCahVisit.getInhibinBNote());
                dbInstance.setHaemoglobin(dsdCahVisit.getHaemoglobin());
                dbInstance.setHaemoglobinDate(dsdCahVisit.getHaemoglobinDate());
                dbInstance.setHaemoglobinTime(dsdCahVisit.getHaemoglobinTime());
                dbInstance.setHaemoglobinNote(dsdCahVisit.getHaemoglobinNote());
                dbInstance.setHaematocrit(dsdCahVisit.getHaematocrit());
                dbInstance.setHaematocritDate(dsdCahVisit.getHaematocritDate());
                dbInstance.setHaematocritTime(dsdCahVisit.getHaematocritTime());
                dbInstance.setHaematocritNote(dsdCahVisit.getHaematocritNote());
                dbInstance.setShbg(dsdCahVisit.getShbg());
                dbInstance.setShbgDate(dsdCahVisit.getShbgDate());
                dbInstance.setShbgTime(dsdCahVisit.getShbgTime());
                dbInstance.setShbgNote(dsdCahVisit.getShbgNote());
                dbInstance.setGlucose(dsdCahVisit.getGlucose());
                dbInstance.setGlucoseDate(dsdCahVisit.getGlucoseDate());
                dbInstance.setGlucoseTime(dsdCahVisit.getGlucoseTime());
                dbInstance.setGlucoseNote(dsdCahVisit.getGlucoseNote());
                dbInstance.setActh(dsdCahVisit.getActh());
                dbInstance.setActhDate(dsdCahVisit.getActhDate());
                dbInstance.setActhTime(dsdCahVisit.getActhTime());
                dbInstance.setActhNote(dsdCahVisit.getActhNote());
                dbInstance.setCortisol(dsdCahVisit.getCortisol());
                dbInstance.setCortisolDate(dsdCahVisit.getCortisolDate());
                dbInstance.setCortisolTime(dsdCahVisit.getCortisolTime());
                dbInstance.setCortisolNote(dsdCahVisit.getCortisolNote());
                dbInstance.setDheas(dsdCahVisit.getDheas());
                dbInstance.setDheasDate(dsdCahVisit.getDheasDate());
                dbInstance.setDheasTime(dsdCahVisit.getDheasTime());
                dbInstance.setDheasNote(dsdCahVisit.getDheasNote());
                dbInstance.setUrineSteroidsByGcms(dsdCahVisit.getUrineSteroidsByGcms());
                dbInstance.setUrineSteroidsByGcmsDate(dsdCahVisit.getUrineSteroidsByGcmsDate());
                dbInstance.setUrineSteroidsByGcmsTime(dsdCahVisit.getUrineSteroidsByGcmsTime());
                dbInstance.setUrineSteroidsByGcmsNote(dsdCahVisit.getUrineSteroidsByGcmsNote());
                dbInstance.setBoneAgeTestDate(dsdCahVisit.getBoneAgeTestDate());
                dbInstance.setBoneAgeResult(dsdCahVisit.getBoneAgeResult());
                dbInstance.setBoneAgeTestMethod(dsdCahVisit.getBoneAgeTestMethod());
                dbInstance.setBoneMineralDensityDone(dsdCahVisit.getBoneMineralDensityDone());
                dbInstance.setBoneMineralDensityTestDate(dsdCahVisit.getBoneMineralDensityTestDate());
                dbInstance.setBoneMineralDensityTestResult(dsdCahVisit.getBoneMineralDensityTestResult());
                dbInstance.setBoneMineralDensityTestResultNote(dsdCahVisit.getBoneMineralDensityTestResultNote());
                dbInstance.setMaleGenitalStage(dsdCahVisit.getMaleGenitalStage());
                dbInstance.setMalePubicHairStage(dsdCahVisit.getMalePubicHairStage());
                dbInstance.setMaleAxillaryHairStage(dsdCahVisit.getMaleAxillaryHairStage());
                dbInstance.setTesticularVolumeLeft(dsdCahVisit.getTesticularVolumeLeft());
                dbInstance.setTesticularVolumeRight(dsdCahVisit.getTesticularVolumeRight());
                dbInstance.setTesticularTexture(dsdCahVisit.getTesticularTexture());
                dbInstance.setEvidenceOfTart(dsdCahVisit.getEvidenceOfTart());
                dbInstance.setEvidenceOfTartNote(dsdCahVisit.getEvidenceOfTartNote());
                dbInstance.setMaleFertilityDesired(dsdCahVisit.getMaleFertilityDesired());
                dbInstance.setSpermStorage(dsdCahVisit.getSpermStorage());
                dbInstance.setPartnerPastPregnancyNumber(dsdCahVisit.getPartnerPastPregnancyNumber());
                dbInstance.setMaleAssistedConceptionHistory(dsdCahVisit.getMaleAssistedConceptionHistory());
                dbInstance.setMaleAssistedConceptionHistoryNote(dsdCahVisit.getMaleAssistedConceptionHistoryNote());
                dbInstance.setCahVisitNote(dsdCahVisit.getCahVisitNote());
                dbInstance.setFemaleBreastStage(dsdCahVisit.getFemaleBreastStage());
                dbInstance.setFemalePubicHairStage(dsdCahVisit.getFemalePubicHairStage());
                dbInstance.setFemaleAxillaryHairStage(dsdCahVisit.getFemaleAxillaryHairStage());
                dbInstance.setMenarche(dsdCahVisit.getMenarche());
                dbInstance.setAgeAtMenarche(dsdCahVisit.getAgeAtMenarche());
                dbInstance.setRegularMenstrualCycle(dsdCahVisit.getRegularMenstrualCycle());
                dbInstance.setFemaleFertilityDesired(dsdCahVisit.getFemaleFertilityDesired());
                dbInstance.setPastPregnancyNumber(dsdCahVisit.getPastPregnancyNumber());
                dbInstance.setLiveBirthNumber(dsdCahVisit.getLiveBirthNumber());
                dbInstance.setFemaleAssistedConceptionHistory(dsdCahVisit.getFemaleAssistedConceptionHistory());
                dbInstance.setFemaleAssistedConceptionHistoryNote(dsdCahVisit.getFemaleAssistedConceptionHistoryNote());
                dbInstance.setDsdCah(dsdCah);
//                dsdCah.getDsdCahVisits().add(dbInstance);
                log.info("Found episode size = " + dsdCahVisit.getDsdCahVisitEpisodes().size());
                if (CollectionUtils.isNotEmpty(dsdCahVisit.getDsdCahVisitEpisodes())) {
                    for (DsdCahVisitEpisode episode : dbInstance.getDsdCahVisitEpisodes()) {
                        log.info("deleting Episode = " + episode);
                        this.dsdCahVisitEpisodeDao.delete(episode);
                    }
                    Set<DsdCahVisitEpisode> dsdCahVisitEpisodeSet = new HashSet<>();
                    for (DsdCahVisitEpisode episode : dsdCahVisit.getDsdCahVisitEpisodes()) {
                        log.info("Episode = " + episode);
                        episode.setDsdCahVisit(dbInstance);
                        dsdCahVisitEpisodeSet.add(episode);
                    }
                    dbInstance.setDsdCahVisitEpisodes(dsdCahVisitEpisodeSet);
                }
                log.info("Found med size = " + dsdCahVisit.getDsdCahVisitMedDetails().size());
                if (CollectionUtils.isNotEmpty(dsdCahVisit.getDsdCahVisitMedDetails())) {
                    for (DsdCahVisitMedDetail detail : dbInstance.getDsdCahVisitMedDetails()) {
                        log.info("deleting Med Detail = " + detail);
                        this.dsdCahVisitMedDetailDao.delete(detail);
                    }
                    Set<DsdCahVisitMedDetail> dsdCahVisitMedDetailSet = new HashSet<>();
                    for (DsdCahVisitMedDetail detail : dsdCahVisit.getDsdCahVisitMedDetails()) {
                        log.info("detail = " + detail);
                        detail.setDsdCahVisit(dbInstance);
                        dsdCahVisitMedDetailSet.add(detail);
                    }
                    dbInstance.setDsdCahVisitMedDetails(dsdCahVisitMedDetailSet);
                }
                this.dsdCahVisitDao.attachDirty(dbInstance);
                dbDsdIdentifier.setUploadTime(new Timestamp(System.currentTimeMillis()));
                this.dsdIdentifierDao.attachDirty(dbDsdIdentifier);
                this.auditService.logUpdateCahVisit(dbInstance);
                log.info("Saving CAH Visit, after save, weight from DB = " + dbInstance.getWeight());
                log.info("Saving CAH Visit, after save, height from DB = " + dbInstance.getHeight());
            } else if (dsdCahVisit != null) {
                log.info("Creating a new CAH visit");
                if (CollectionUtils.isNotEmpty(dsdCahVisit.getDsdCahVisitEpisodes())) {
                    for (DsdCahVisitEpisode episode : dsdCahVisit.getDsdCahVisitEpisodes()) {
                        episode.setDsdCahVisit(dsdCahVisit);
                    }
                }
                if (CollectionUtils.isNotEmpty(dsdCahVisit.getDsdCahVisitMedDetails())) {
                    for (DsdCahVisitMedDetail detail : dsdCahVisit.getDsdCahVisitMedDetails()) {
                        detail.setDsdCahVisit(dsdCahVisit);
                    }
                }
                Set<DsdCahVisit> visitSet = new HashSet<>();
                visitSet.add(dsdCahVisit);
                dsdCahVisit.setDsdCah(dsdCah);
                dsdCah.setDsdCahVisits(visitSet);
                dsdCahVisitDao.save(dsdCahVisit);
                this.auditService.logCreateCahVisit(dsdCahVisit);
            }
        } catch (Exception e) {
            throw new ServiceException("Error occurred while saving DSD record into database.", e);
        }
        return dsdCahVisit;
    }

    public DsdAssessment getDsdAssessmentById(Long assessmentId) throws ServiceException {
        DsdAssessment dsdAssessment = null;
        if (null != assessmentId) {
            dsdAssessment = this.dsdAssessmentDao.findById(assessmentId);
        }
        if (dsdAssessment == null) {
            throw new ServiceException(ErrorCodes.NO_RECORD);
        }
        return dsdAssessment;
    }

    public void saveAssessment(Long registerId, DsdAssessment dsdAssessment) {
        log.debug(" in saving assessment service call");

        if (null == dsdAssessment || null == dsdAssessment.getAssessmentDate()) {
            log.warn("Trying to save a null dsdAssessment, or one with null assessmentDate. Return without saving. ");
            return;
        }

        if (registerId == null || registerId == 0L) {
            registerId = dsdAssessment.getDsdIdentifier().getRegisterId();
        }

        if (registerId != null) {
            DsdIdentifier dsdIdentifier = this.dsdIdentifierDao.findById(registerId);
            if (null == dsdAssessment.getAssessmentId()) {
                log.info("Brand new assessment object, saving new instance: " + dsdAssessment);
                dsdAssessment.setDsdIdentifier(dsdIdentifier);
                this.dsdAssessmentDao.save(dsdAssessment);
                this.auditService.logCreateAssessments(dsdIdentifier);
            } else {
                DsdAssessment dbInstance = this.dsdAssessmentDao.findById(dsdAssessment.getAssessmentId());
                //set values to dbObject.
                if (null != dbInstance) {
                    dbInstance.setAssessmentDate(dsdAssessment.getAssessmentDate());
                    dbInstance.setPhallusLength(dsdAssessment.getPhallusLength());
                    dbInstance.setPhallusSize(dsdAssessment.getPhallusSize());
                    dbInstance.setUrinaryMeatus(dsdAssessment.getUrinaryMeatus());
                    dbInstance.setLabioscrotalFusion(dsdAssessment.getLabioscrotalFusion());
                    dbInstance.setRightGonad(dsdAssessment.getRightGonad());
                    dbInstance.setLeftGonad(dsdAssessment.getLeftGonad());
                    dbInstance.setMullerianStructure(dsdAssessment.getMullerianStructure());
                    dbInstance.setWolffianStructure(dsdAssessment.getWolffianStructure());
                    dbInstance.setEms(dsdAssessment.getEms());
                    dbInstance.setModifiedPrader(dsdAssessment.getModifiedPrader());
                    dbInstance.setTannerStage(dsdAssessment.getTannerStage());
                    dbInstance.setGonadectomy(dsdAssessment.getGonadectomy());
                    dbInstance.getDsdIdentifier().setLastUpdate(new Timestamp(System.currentTimeMillis()));
                    dbInstance.setDsdIdentifier(dsdAssessment.getDsdIdentifier());
                    dbInstance.setFreeText(dsdAssessment.getFreeText());
                    log.info("existing assessment, updated db instance to: " + dbInstance);
                    dbInstance.setDsdIdentifier(dsdIdentifier);
                    this.dsdAssessmentDao.attachDirty(dbInstance);
                    this.auditService.logUpdateAssessments(dsdIdentifier);
                } else {
                    log.error("Cannot find existing Assessment record by AssessmentId: " + dsdAssessment.getAssessmentId());
                }
            }
        }
    }

    public void deleteAssessment(Long assessmentId) throws ServiceException {
        log.debug(" in deleting assessment service call");
        if (null == assessmentId) {
            throw new ServiceException("No DSD Assessment ID is given to delete.");
        }
        DsdAssessment dbInstance = getDsdAssessmentById(assessmentId);
        if (null != dbInstance) {
            dbInstance.getDsdIdentifier().getDsdAssessments().remove(dbInstance);
            this.dsdAssessmentDao.delete(dbInstance);
        }
    }

    public void update(DsdIdentifier dsdIdentifier) throws ServiceException {
        //this.dsdIdentifierDao.save(dsdIdentifier)
        Centre co = this.centreService.getCentreByCentreName(dsdIdentifier.getCentre().getCentreName());
        dsdIdentifier.setCentre(co);
        if (null == dsdIdentifier.getLastUpdate()) {
            dsdIdentifier.setLastUpdate(new Timestamp(System.currentTimeMillis()));
        }

        SortedSet<DsdAssessment> dsdAssessmentSet = new TreeSet<DsdAssessment>(new DsdAssessmentDateComparator());
        for (DsdAssessment da : dsdIdentifier.getDsdAssessments()) {
            da.setDsdIdentifier(dsdIdentifier);
            dsdAssessmentSet.add(da);
        }
        dsdIdentifier.setDsdAssessments(dsdAssessmentSet);

        DsdGeneAnalysis dga = dsdIdentifier.getDsdGeneAnalysis();

        Set<DsdGeneScreened> dgsSet = new TreeSet<DsdGeneScreened>(new DsdGeneScreenedComparator());
        for (DsdGeneScreened dgs : dga.getDsdGeneScreeneds()) {
            dgs.setDsdGeneAnalysis(dga);
            dgsSet.add(dgs);
        }
        dga.setDsdGeneScreeneds(dgsSet);
        dga.setDsdIdentifier(dsdIdentifier);
        dsdIdentifier.setDsdGeneAnalysis(dga);

        dsdIdentifier.getDsdGeneAnalysis().setDsdIdentifier(dsdIdentifier);

        this.dsdIdentifierDao.attachDirty(dsdIdentifier);
        //if(null != dsdIdentifier.getRegisterId()) log.error(dsdIdentifier.getRegisterId());
    }

    /**
     * Get result set by give dsd object example, perfume following functions:
     * 1. trim()
     * 2. check sampleConsent, if false, means no selection has been made, therefore set sampleConsent to null to clear the search criteiria
     * 3. enforce Consent check on the retruned DsdIdentifier list, take out those cannot be seen by current user.
     *
     * @throws UserException
     */
    public SearchResult getByExample(List<Parameter> selectedParameters, DsdIdentifier dsdIdentifier,
                                     DsdAssessment firstDsdAssessment,
                                     DsdAssessment latestDsdAssessment,
                                     String geneString) throws ServiceException {

        SearchResult searchResult = new SearchResult();

        //trim dsdIdentifier to get rid of all empty strings and spaces
        dsdIdentifier = new DsdIdentifier(dsdIdentifier);

        DsdSearchFilter dsdSearchFilter = new DsdSearchFilter();
        //set delete and completeness flag to null to 0 to avoid uncompleted and deleted record
        dsdIdentifier = dsdSearchFilter.dsdIdentifierRecordStatusFilter(dsdIdentifier);

        PortalUser currentUser = userService.getPortalUserByUsername(Utility.getLoginUserName());
        //filter for Local Contributors, if true, set country and centre for them even if they dont select
        dsdIdentifier = dsdSearchFilter.dsdIdentifierRoleFilter(dsdIdentifier, currentUser);

        //deal with sampleConsent: if false, set to null
        dsdIdentifier = dsdSearchFilter.dsdIdentifierConsentFilter(dsdIdentifier);

        //deal with Disorder Type = Any
        dsdIdentifier = dsdSearchFilter.dsdIdentifierDisorderTypeFilter(dsdIdentifier);

        //deal with Actual Diagnosis == All of the above,
        dsdIdentifier = dsdSearchFilter.dsdIdentifierActualDiagnosisFilter(dsdIdentifier);

        //deal with Centre == All centres
        dsdIdentifier = dsdSearchFilter.dsdIdentifierCentreFilter(dsdIdentifier);

        //get list from db by example
        List<DsdIdentifier> dsdList = new ArrayList<DsdIdentifier>();
        if (dsdIdentifier.getRegisterId() != null && dsdIdentifier.getRegisterId() != 0) {
            DsdIdentifier foundDsdIdentifier = this.dsdIdentifierDao.findById(dsdIdentifier.getRegisterId());
            if (null != foundDsdIdentifier) {
                dsdList.add(foundDsdIdentifier);
            }
        } else {
            dsdList = this.dsdIdentifierDao.findByCriteria(dsdIdentifier);
        }
        if (null == dsdList || dsdList.isEmpty()) {
            return searchResult;
        }

        if (log.isDebugEnabled()) {
            log.debug("findByExample size: " + dsdList.size());
        }
        //filter duplicated records
        dsdList = dsdSearchFilter.removeDuplicates(dsdList);

        if (log.isDebugEnabled()) {
            log.debug("size after filter duplicates : " + dsdList.size());
        }
        //filter for first and latest assessments
        dsdList = dsdSearchFilter.searchResultListAssessmentFilter(dsdList, firstDsdAssessment, latestDsdAssessment);

        //filter of gene search
        if (log.isDebugEnabled()) {
            log.debug("Before gene filter: " + dsdList.size());
        }
        dsdList = dsdSearchFilter.searchResultListGeneFilter(dsdList, geneService, geneString);
        if (log.isDebugEnabled()) {
            log.debug("After gene filter: " + dsdList.size());
        }

        //get list size before consent
        int originalResultSize = dsdList.size();

        //filter of consent
        if (log.isDebugEnabled()) {
            log.debug("Before consent filter: " + dsdList.size());
        }
        dsdList = Consent.enforce(dsdList, currentUser);
        if (log.isDebugEnabled()) {
            log.debug("After consent filter: " + dsdList.size());
        }

        //anonymization of the list
        dsdList = Anonymization.anonymize(dsdList, currentUser);

        //construct searchResult object
        searchResult = dsdSearchFilter.constructSearchResult(parameterService, selectedParameters, originalResultSize, dsdList, currentUser);
        if (log.isDebugEnabled()) {
            log.debug("final list size: " + dsdList.size());
        }
        return searchResult;
    }

    public SearchResult searchDsdRecords(DsdSearchCriteria dsdSearchCriteria) throws ServiceException {
        List<Parameter> selectedParameters = new ArrayList<>();
        if (StringUtils.isNotBlank(dsdSearchCriteria.getSelectedParameterIds())) {
            for (String s : dsdSearchCriteria.getSelectedParameterIds().split(COMMA)) {
                if (s.endsWith("f")) {
                    Integer paramId = Integer.parseInt(s.replace("f", "").trim());
                    Parameter p = new Parameter(parameterService.getParameterById(paramId));
                    p.setLabel("First " + p.getLabel());
                    selectedParameters.add(p);
                } else if (s.endsWith("l")) {
                    Integer paramId = Integer.parseInt(s.replace("l", "").trim());
                    Parameter p = new Parameter(parameterService.getParameterById(paramId));
                    p.setLabel("Last " + p.getLabel());
                    selectedParameters.add(p);
                } else {
                    Integer paramId = Integer.parseInt(s.trim());
                    selectedParameters.add(new Parameter(parameterService.getParameterById(paramId)));
                }
            }
        }
        return getByExample(selectedParameters, dsdSearchCriteria.getDsdIdentifier(), dsdSearchCriteria.getFirstDsdAssessment(), dsdSearchCriteria.getLastDsdAssessment(), dsdSearchCriteria.getSelectedGeneNames());
    }

    @SuppressWarnings("unchecked")
    public List<DsdIdentifier> getAll() {
        return this.dsdIdentifierDao.findAll();
    }

    public DsdIdentifier getById(Long registerId) {
        return this.dsdIdentifierDao.findById(registerId);
    }

    public List<DsdIdentifierResearchResultBean> getDsdIdentifierResearchResultBeans(PortalUser portalUser, DsdIdentifier dsdIdentifier) {
        List<DsdIdentifierResearchResultBean> beans = new ArrayList<DsdIdentifierResearchResultBean>();

        Set<ResearchResult> results = dsdIdentifier.getResearchResults();
        //log.error(results.size());
        for (ResearchResult rr : results) {
            DsdIdentifierResearchResultBean bean = new DsdIdentifierResearchResultBean();
            bean.setRegisterId(dsdIdentifier.getRegisterId());
            bean.setComment(rr.getComment());
            bean.setCategoryName(rr.getResearchResultCategory().getName());
            bean.setFileName(rr.getOriginalFileName());
            bean.setResultId(rr.getResultId());
            bean.setUploader(rr.getUploader());
            bean.setUploadTime(rr.getUploadTime());
            if (Authorization.authorizeFileDownload(portalUser.getUsername(), rr.getAccess(), rr.getUploader(), dsdIdentifier.getUploader().getUsername())) {
                bean.setAllow(true);
            } else {
                bean.setAllow(false);
            }
            beans.add(bean);
        }
        return beans;
    }

    @SuppressWarnings("unchecked")
    public StatisticResultBean getRecordStatistic() {
        try {
            Map<String, StatisticBean> statisticMap = new HashMap<String, StatisticBean>();
//			log.debug("start getting all dsd record;");
//			long start = System.currentTimeMillis();

            List<DsdIdentifier> dsdList = (List<DsdIdentifier>) this.dsdIdentifierDao.findDsdIdentifierStatistic();
            //int totalSize = this.dsdIdentifierDao.findTotalSize();
            int totalSize = dsdList.size();

//			long end = System.currentTimeMillis();
//			log.debug("fetching all dsd records, took " + (end - start));

            for (DsdIdentifier d : dsdList) {
//				log.error(statisticMap == null);
//				log.error(d.getRegisterId() + " ; " + d.getCentre() + " : ");
                StatisticBean statisticBean;
                if (statisticMap.containsKey(d.getCentreName().trim())) {
                    statisticBean = statisticMap.get(d.getCentreName().trim());
                } else {
                    statisticBean = new StatisticBean(d.getCentreName().trim(), d.getCountryName().trim(), 0, 0, 0, 0, 0, 0);
                    statisticMap.put(d.getCentreName().trim(), statisticBean);
                }
                if ((d.getSampleConsent() != null && d.getSampleConsent()) &&
                        ((d.getDna() != null && d.getDna().equalsIgnoreCase("Yes"))
                                || (d.getTissue() != null && d.getTissue().equalsIgnoreCase("Yes"))
                                || (d.getCellLine() != null && d.getCellLine().equalsIgnoreCase("Yes"))
                                || (d.getUrine() != null && d.getUrine().equalsIgnoreCase("Yes"))
                                || (d.getSerum() != null && d.getSerum().equalsIgnoreCase("Yes"))
                        )
                        ) {
                    statisticBean.setSample(statisticBean.getSample() + 1);
                }

                if (d.getConsent() != null && d.getConsent() == 1) {
                    statisticBean.setLocal(statisticBean.getLocal() + 1);
                } else if (d.getConsent() != null && d.getConsent() == 2) {
                    statisticBean.setNational(statisticBean.getNational() + 1);
                } else if (d.getConsent() != null && d.getConsent() == 3) {
                    statisticBean.setEuropean(statisticBean.getEuropean() + 1);
                } else if (d.getConsent() != null && d.getConsent() == 4) {
                    statisticBean.setInternational(statisticBean.getInternational() + 1);
                }
                statisticBean.setTotal(statisticBean.getTotal() + 1);
            }
            return new StatisticResultBean(new ArrayList<StatisticBean>(statisticMap.values()), totalSize);

        } catch (Exception e) {
            log.error("Error in getting statistic data in DsdIdentifierManagerImpl!", e);
            return null;
        }
    }

    /**
     * Statistics methods:
     */
    /*
     public StatisticResultBean getRecordStatistic() {
         try {
             List<DsdIdentifier> dsdIdentifiers = this.dsdIdentifierDao.findAll();

             List<String> centres = new ArrayList<String>();
             StatisticResultBean result = new StatisticResultBean();
             List<StatisticBean> jsonBeanList = new ArrayList<StatisticBean>();

             for(DsdIdentifier d : dsdIdentifiers) {
                 boolean exist = false;
                 StatisticBean jsonBean = null;
                 for(StatisticBean jbean : jsonBeanList) {
                     if(jbean.getCentre().trim().equalsIgnoreCase(d.getCentre().trim())) {
                         jsonBean = jbean;
                         exist = true;
                     }
                 }
                 if(!exist) {
                     //log.error(d.getCentre() + " is just added");
                     jsonBean = new StatisticBean();
                     jsonBean.setCentre(d.getCentre());
                     jsonBean.setCountry(d.getCountry());
                     jsonBean.setLocal(0);
                     jsonBean.setNational(0);
                     jsonBean.setEurodsdSample(0);
                     jsonBean.setEuropean(0);
                     jsonBean.setInternational(0);
                     centres.add(d.getCentre());
                     jsonBeanList.add(jsonBean);
                 }
                 switch(d.getConsent().intValue()) {
                     case 1: jsonBean.setLocal(jsonBean.getLocal() + 1); break;
                     case 2: jsonBean.setNational(jsonBean.getNational() + 1); break;
                     case 3: jsonBean.setEuropean(jsonBean.getEuropean() + 1); break;
                     case 4: jsonBean.setInternational(jsonBean.getInternational() + 1); break;
                 }
                 if(d.getSampleConsent() != null && d.getSampleConsent().booleanValue()) {
                     jsonBean.setEurodsdSample(jsonBean.getEurodsdSample() + 1);
                 }
                 jsonBean.setTotal(jsonBean.getTotal() + 1);
             }
             result.setResultList(jsonBeanList);
             result.setTotal(dsdIdentifiers.size());
             return result;
         } catch(exception e) {
             log.error("Error in getting statistic data in DsdIdentifierManagerImpl!", e);
             return null;
         }
     }
     */
    public long getTotalRecordNumber() {
        return this.dsdIdentifierDao.findSize();
    }

    /**
     * return a list of PorgressBeans.
     */
    @SuppressWarnings("unchecked")
    public List<ProgressBean> getProgressStatistic() {
        List<ProgressBean> progressBeanList = new ArrayList<ProgressBean>();
        try {
//			long start = System.currentTimeMillis();
//			log.error("started timing");
            List<DsdIdentifier> dsdIdentifiers = this.dsdIdentifierDao.findAll();
//			long end = System.currentTimeMillis();
//			log.error("performance = " + (end - start) + " ms");
            for (DsdIdentifier d : dsdIdentifiers) {
                boolean exist = false;
                ProgressBean progressBean = null;
                for (ProgressBean pb : progressBeanList) {
                    if (pb.getCentre().trim().equalsIgnoreCase(d.getCentreName().trim())) {
                        progressBean = pb;
                        exist = true;
                    }
                }
                if (!exist) {
                    //log.error(d.getCentre() + " is just added");
                    progressBean = new ProgressBean();
                    progressBean.setCentre(d.getCentreName());
                    progressBean.setTotalNumber(0);
                    progressBean.setBioNumber(0);
                    progressBean.setPlasmaNumber(0);
                    progressBean.setUrineNumber(0);
                    progressBeanList.add(progressBean);
                }
                //now check detail of this d record, and set those numbers
                if (d.getUrine().equalsIgnoreCase("Yes")) {
                    progressBean.setUrineNumber(progressBean.getUrineNumber() + 1);
                }
                if (d.getSerum().equalsIgnoreCase("Yes")) {
                    progressBean.setPlasmaNumber(progressBean.getPlasmaNumber() + 1);
                }
                if (d.getDna().equalsIgnoreCase("Yes") || d.getTissue().equalsIgnoreCase("Yes") ||
                        d.getCellLine().equalsIgnoreCase("Yes") || d.getUrine().equalsIgnoreCase("Yes") ||
                        d.getSerum().equalsIgnoreCase("Yes")) {
                    progressBean.setBioNumber(progressBean.getBioNumber() + 1);
                }
                if (d.getConsent() > 0) {
                    progressBean.setTotalNumber(progressBean.getTotalNumber() + 1);
                }
            }
            return progressBeanList;
        } catch (Exception e) {
            log.error("Exception in getting progress data", e);
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public List<String> getRegisterIds() {
        List<String> registerIds = new ArrayList<String>();
        List<DsdIdentifier> dsdIdentifiers = (List<DsdIdentifier>) this.dsdIdentifierDao.findAllRegisterIds();
        if (dsdIdentifiers == null || dsdIdentifiers.isEmpty()) {
            log.info("dsdIdentifierDao.findAll() returns null/empty list");
            return null;
        }
        for (DsdIdentifier d : dsdIdentifiers) {
            registerIds.add(d.getRegisterId().toString());
        }
        return registerIds;
    }

    public Map<PortalUser, String> getAge16Reminder() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        List<DsdIdentifier> dsdIdentifiers = this.dsdIdentifierDao.findByYob(year - 16);
        Map<String, String> map = new HashMap<String, String>();
        for (DsdIdentifier d : dsdIdentifiers) {
            String key = d.getContact();
            if (!map.containsKey(key)) {
                if (map.get(key) == null || map.get(key).isEmpty()) {
                    map.put(key, d.getRegisterId().toString());
                } else {
                    map.put(key, map.get(key) + COMMA + d.getRegisterId());
                }
            }
        }
        Map<PortalUser, String> beanMap = new HashMap<PortalUser, String>();
        for (String email : map.keySet()) {
            List<PortalUser> users = userService.getPortalUserByEmail(email);
            if (users == null || users.isEmpty()) {
                log.error("Searching for email " + email + ", find non user using this email!");
            } else {
                PortalUser user = users.get(0);
                beanMap.put(user, map.get(email));
            }
        }

        return beanMap;
    }

    @Override
    public DsdIdentifier getDsdIdentifierByIdForPatientAccess(Long registerId) {
        DsdIdentifier dsdIdentifier = getById(registerId);
        return new DsdIdentifier(dsdIdentifier.getRegisterId(),
                dsdIdentifier.getCentre(),
                dsdIdentifier.getCountryName(),
                dsdIdentifier.getCentreName(),
                dsdIdentifier.getYob(),
                dsdIdentifier.getBirthWeight(),
                dsdIdentifier.getAofp(),
                dsdIdentifier.getClinician(),
                dsdIdentifier.getContact(),
                dsdIdentifier.getKaryotype(),
                dsdIdentifier.getDisorderType(),
                dsdIdentifier.getActualDiagnosis(),
                dsdIdentifier.getSexAssigned(),
                dsdIdentifier.getCurrentGender(),
                dsdIdentifier.getBirthLength(),
                dsdIdentifier.getAssocMalforms(),
                dsdIdentifier.getConsent());
    }

    @Override
    public void deleteCahVisit(Long dsdCahVisitId) throws ServiceException {
        DsdCahVisit dsdCahVisit = this.dsdCahVisitDao.findById(dsdCahVisitId);
        if (null != dsdCahVisit) {
            this.dsdCahVisitDao.delete(dsdCahVisit);
            this.auditService.logDeleteCahVisit(dsdCahVisit);
        } else {
            throw new ServiceException("Cannot find dsdCahVisitId to delete: " + dsdCahVisitId);
        }
    }

    @Override
    public DsdCahVisit getLastDsdCahVisit(Long dsdCahId) {
        DsdCahVisit lastDsdCahVisit = null;
        DsdCah dsdCah = dsdCahDao.findById(dsdCahId);
        if (dsdCah != null && dsdCah.getDsdCahVisits() != null && CollectionUtils.isNotEmpty(dsdCah.getDsdCahVisits())) {
            lastDsdCahVisit = getLatestDsdCahVisit(dsdCah.getDsdCahVisits());
        }
        return lastDsdCahVisit;
    }

    @Override
    public Map<Long, String> saveDsdCoreInBulk(List<DsdIdentifier> dsdIdentifiers) {
        Map<Long, String> savedDsdCoreIdMap = new HashMap<>();
        for (DsdIdentifier dsdIdentifier : dsdIdentifiers) {
            DsdIdentifier savedDsdIdentifier = null;
            try {
                savedDsdIdentifier = saveCore(null, dsdIdentifier);
                this.auditService.logBulkCreateCore(dsdIdentifier);
            } catch (ServiceException e) {
                log.error("Error saving dsd core in bulk. ", e);
                throw new ServiceException("Error Saving DSD core for record: " + dsdIdentifier);
            }
            savedDsdCoreIdMap.put(savedDsdIdentifier.getRegisterId(), savedDsdIdentifier.getLocalId());
        }
        log.debug("save Dsd cores: " + savedDsdCoreIdMap);
        return savedDsdCoreIdMap;
    }

    @Override
    public SearchResult getMyRecords(String username) {
        List<DsdIdentifier> dsdIdentifiers = dsdIdentifierDao.findByUploader(username);
        PortalUser currentUser = userService.getPortalUserByUsername(Utility.getLoginUserName());
        List<Parameter> selectedParameters = parameterService.getMyContributionParameters();
        DsdSearchFilter dsdSearchFilter = new DsdSearchFilter();
        return dsdSearchFilter.constructSearchResult(parameterService, selectedParameters, dsdIdentifiers.size(), dsdIdentifiers, currentUser);
    }

    @Override
    public SearchResult getMyCentreRecords(String username) {
        PortalUser currentUser = userService.getPortalUserByUsername(Utility.getLoginUserName());
        List<DsdIdentifier> dsdIdentifiers = dsdIdentifierDao.findByCentre(currentUser.getCentre());
        List<Parameter> selectedParameters = parameterService.getMyCentreContributionParameters();
        DsdSearchFilter dsdSearchFilter = new DsdSearchFilter();
        return dsdSearchFilter.constructSearchResult(parameterService, selectedParameters, dsdIdentifiers.size(), dsdIdentifiers, currentUser);
    }

    @Override
    public String getCsvByDsdSearchResult(SearchResult searchResult) {
        StringBuilder stringBuilder = new StringBuilder();
        Set<Long> registerIds = new HashSet<Long>();
//        for(SearchResultRow row : searchResult.getResultRows()) {
//            registerIds.add(row.getRegisterId());
//        }
        stringBuilder.append(makeHeader(searchResult.getHeadRow()));
        for (SearchResultRow row : searchResult.getResultRows()) {
            for (SearchResultCell cell : row.getCells()) {
                double d = Double.parseDouble(cell.getColumnId());
                if (d <= 900000) {
                    stringBuilder.append(cleanValueForCsv(cell.getValue())).append(COMMA);
                }
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    private String makeHeader(List<SearchResultHeadCell> headRow) {
        StringBuilder stringBuilder = new StringBuilder();
        for (SearchResultHeadCell cell : headRow) {
            double d = Double.parseDouble(cell.getColumnId());
            if (d <= 900000) {
                stringBuilder.append(cell.getLabel()).append(COMMA);
            }
        }
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }

    @Override
    public String getCsvByIds(Set<Long> registerIds) {
        StringBuilder stringBuilder = new StringBuilder();
        List<DsdIdentifier> dsdIdentifiers = dsdIdentifierDao.findByIds(registerIds);
        stringBuilder.append(addHeader());
        for (DsdIdentifier dsdIdentifier : dsdIdentifiers) {
            stringBuilder.append(cleanValueForCsv(dsdIdentifier.getRegisterId())).append(COMMA)
                    .append(cleanValueForCsv(dsdIdentifier.getCountryName())).append(COMMA)
                    .append(cleanValueForCsv(dsdIdentifier.getCentreName())).append(COMMA)
                    .append(cleanValueForCsv(dsdIdentifier.getLocalId())).append(COMMA)
                    .append(cleanValueForCsv(dsdIdentifier.getYob())).append(COMMA)
                    .append(cleanValueForCsv(dsdIdentifier.getAofp())).append(COMMA)
                    .append(cleanValueForCsv(dsdIdentifier.getSexAssigned())).append(COMMA)
                    .append(cleanValueForCsv(dsdIdentifier.getCurrentGender())).append(COMMA)
                    .append(cleanValueForCsv(dsdIdentifier.getKaryotype())).append(COMMA)
                    .append(cleanValueForCsv(dsdIdentifier.getActualDiagnosis())).append(COMMA)
                    .append(cleanValueForCsv(dsdIdentifier.getBirthWeight())).append(COMMA)
                    .append(cleanValueForCsv(dsdIdentifier.getBirthLength())).append(COMMA)
                    .append(cleanValueForCsv(dsdIdentifier.getAssocMalforms())).append(COMMA)
                    .append(cleanValueForCsv(dsdIdentifier.getClinicalFeatures())).append(COMMA)
                    .append(cleanValueForCsv(dsdIdentifier.getBiochemistry())).append(COMMA)
                    .append(cleanValueForCsv(dsdIdentifier.getDnaAnalysis())).append(COMMA)
                    .append(cleanValueForCsv(dsdIdentifier.getParentalConsanguinity())).append(COMMA)
                    .append(cleanValueForCsv(dsdIdentifier.getDsdHistory())).append(COMMA)
                    .append(cleanValueForCsv(dsdIdentifier.getInfertilityHistory())).append(COMMA)
                    .append(cleanValueForCsv(dsdIdentifier.getSampleAvailability())).append(COMMA)
                    .append(cleanValueForCsv(dsdIdentifier.getClinicalInfo())).append(COMMA)
                    .append(cleanValueForCsv(dsdIdentifier.getCaseNotes())).append(COMMA)
                    .append(cleanValueForCsv(dsdIdentifier.getGrowthData())).append(COMMA)
                    .append(cleanValueForCsv(dsdIdentifier.getPubertyData())).append(COMMA)
                    .append(cleanValueForCsv(dsdIdentifier.getDna())).append(COMMA)
                    .append(cleanValueForCsv(dsdIdentifier.getTissue())).append(COMMA)
                    .append(cleanValueForCsv(dsdIdentifier.getCellLine())).append(COMMA)
                    .append(cleanValueForCsv(dsdIdentifier.getCellLineInfo())).append(COMMA)
                    .append(cleanValueForCsv(dsdIdentifier.getUrine())).append(COMMA)
                    .append(cleanValueForCsv(dsdIdentifier.getSerum())).append(COMMA)
                    .append(cleanValueForCsv(dsdIdentifier.getSampleShared())).append(COMMA);
            DsdGeneAnalysis dsdGeneAnalysis = dsdIdentifier.getDsdGeneAnalysis();
            if (dsdGeneAnalysis != null) {
                stringBuilder.append(cleanValueForCsv(dsdGeneAnalysis.getSingleGeneAnalysis())).append(COMMA)
                        .append(cleanValueForCsv(dsdGeneAnalysis.getChromosomalRearrangement())).append(COMMA)
                        .append(cleanValueForCsv(dsdGeneAnalysis.getChromosomalRearrangementDetail())).append(COMMA)
                        .append(cleanValueForCsv(dsdGeneAnalysis.getCgh())).append(COMMA)
                        .append(cleanValueForCsv(dsdGeneAnalysis.getCghDetail())).append(COMMA)
                        .append(cleanValueForCsv(dsdGeneAnalysis.getFunctionalStudy())).append(COMMA)
                        .append(cleanValueForCsv(dsdGeneAnalysis.getFunctionalStudyDetail())).append(COMMA)
                        .append(cleanValueForCsv(dsdGeneAnalysis.getPublishedCase())).append(COMMA)
                        .append(cleanValueForCsv(dsdGeneAnalysis.getPublishDetail())).append(COMMA)
                        .append(cleanValueForCsv(dsdGeneAnalysis.getFurtherStudies())).append(COMMA);
            } else {
                stringBuilder.append(COMMA).append(COMMA).append(COMMA).append(COMMA).append(COMMA)
                        .append(COMMA).append(COMMA).append(COMMA).append(COMMA).append(COMMA);
            }

            DsdCah cah = dsdIdentifier.getDsdCah();
            if (cah != null) {
                stringBuilder.append(cleanValueForCsv(cah.getPrenatalDiagnosis())).append(COMMA)
                        .append(cleanValueForCsv(cah.getPrenatalDexamethasoneTherapy())).append(COMMA)
                        .append(cleanValueForCsv(cah.getGestationalAge())).append(COMMA)
                        .append(cleanValueForCsv(cah.getPraderStageAtFirstPresentation())).append(COMMA)
                        .append(cleanValueForCsv(cah.getSwCrisisAtPresentation())).append(COMMA)
                        .append(cleanValueForCsv(cah.getAdrenalCrisesAfterFirstPresentation())).append(COMMA)
                        .append(cleanValueForCsv(cah.getCurrentFcTreatment())).append(COMMA)
                        .append(cleanValueForCsv(cah.getCurrentSaltReplacement())).append(COMMA);
            } else {
                stringBuilder.append(COMMA).append(COMMA).append(COMMA).append(COMMA)
                        .append(COMMA).append(COMMA).append(COMMA).append(COMMA);
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    private String addHeader() {
        return "RegisterId,CountryName,CentreName,LocalId,Yob,Aofp,SexAssigned,Karyotype,ActualDiagnosis,BirthWeight,BirthLength,AssocMalforms,ClinicalFeatures,Biochemistry,DnaAnalysis,ParentalConsanguinity,DsdHistory,InfertilityHistory,SampleAvailability,ClinicalInfo,CaseNotes,GrowthData,PubertyData,Dna,Tissue,CellLine,CellLineInfo,Urine,Serum,SampleShared,SingleGeneAnalysis,ChromosomalRearrangement,ChromosomalRearrangementDetail,Cgh,CghDetail,FunctionalStudy,FunctionalStudyDetail,PublishedCase,PublishDetail,FurtherStudies,PrenatalDiagnosis,PrenatalDexamethasoneTherapy,GestationalAge,PraderStageAtFirstPresentation,SwCrisisAtPresentation,AdrenalCrisesAfterFirstPresentation,CurrentFcTreatment,CurrentSaltReplacement\n";
    }

    private <T> T cleanValueForCsv(T value) {
        if (value instanceof String) {
            String string = (String) value;
            if (StringUtils.isNotBlank(string)) {
                return (T) string.replace("\n", "").replace("\r", "").replace(",", ";").trim();
            } else {
                return value;
            }
        } else {
            return value;
        }
    }

	@Override
	public DsdIdentifier saveNeonatal(DsdIdentifier dsdIdentifier, DsdNeonatalVisits dsdneonatalvisits)
			throws ServiceException {
		
				DsdIdentifier dbDsdIdentifier;
		        DsdCah dsdCah;
		        dsdneonatalvisits dbInstance;
		        try {
		            dbDsdIdentifier = this.dsdIdentifierDao.findById(dsdIdentifier.getRegisterId());
		            dsdCah = dbDsdIdentifier.getDsdCah();
		        } catch (Exception e) {
		            throw new ServiceException("Error occurred while looking up CAH visit record from database.", e);
		        }
		        try {
		            if (null != dsdneonatalvisits && null != dsdneonatalvisits.getDsd_neonatal_visit_id()) {
		                log.info("Updating existing CAH visit");
		                dbInstance = this.dsdneonatalvisitsDao.findById(dsdneonatalvisits.getdsdneonatalvisitsId());
		                dbInstance.setDate(dsdneonatalvisits.getDate());
		                dbInstance.setAge(dsdneonatalvisits.getAge());
		                dbInstance.setWeight(dsdneonatalvisits.getWeight());
		                dbInstance.setHeight(dsdneonatalvisits.getHeight());
		                dbInstance.setWaistCircumference(dsdneonatalvisits.getWaistCircumference());
		                dbInstance.setHipCircumference(dsdneonatalvisits.getHipCircumference());
		                dbInstance.setBmi(dsdneonatalvisits.getBmi());
		                dbInstance.setBsa(dsdneonatalvisits.getBsa());
		                dbInstance.setCushingoid(dsdneonatalvisits.getCushingoid());
		                dbInstance.setVirilisation(dsdneonatalvisits.getVirilisation());
		                dbInstance.setBpSystolic(dsdneonatalvisits.getBpSystolic());
		                dbInstance.setBpDiastolic(dsdneonatalvisits.getBpDiastolic());
		                dbInstance.setSickDayEpisodes(dsdneonatalvisits.getSickDayEpisodes());
		                dbInstance.setSickDayTotal(dsdneonatalvisits.getSickDayTotal());
		                dbInstance.setdsdneonatalvisitsEpisodes(dsdneonatalvisits.getdsdneonatalvisitsEpisodes());
		                dbInstance.setDailyAdherenceToTherapy(dsdneonatalvisits.getDailyAdherenceToTherapy());
		                dbInstance.setTreatmentChange(dsdneonatalvisits.getTreatmentChange());
		                dbInstance.setTreatmentChangeReason(dsdneonatalvisits.getTreatmentChangeReason());
		                dbInstance.setGlucocorticoid(dsdneonatalvisits.getGlucocorticoid());
		                dbInstance.setGlucocorticoidDetail(dsdneonatalvisits.getGlucocorticoidDetail());
		                dbInstance.setGlucocorticoidNote(dsdneonatalvisits.getGlucocorticoidNote());
		                dbInstance.setFludrocortisone(dsdneonatalvisits.getFludrocortisone());
		                dbInstance.setFludrocortisoneDose(dsdneonatalvisits.getFludrocortisoneDose());
		                dbInstance.setFludrocortisoneFrequency(dsdneonatalvisits.getFludrocortisoneFrequency());
		                dbInstance.setFludrocortisoneNote(dsdneonatalvisits.getFludrocortisoneNote());
		                dbInstance.setCurrentGcReplacement(dsdneonatalvisits.getCurrentGcReplacement());
//		                dbInstance.setCurrentFcTreatment(dsdneonatalvisits.getCurrentFcTreatment());
		                dbInstance.setSaltReplacement(dsdneonatalvisits.getSaltReplacement());
		                dbInstance.setSaltReplacementDose(dsdneonatalvisits.getSaltReplacementDose());
		                dbInstance.setSaltReplacementDoseUnit(dsdneonatalvisits.getSaltReplacementDoseUnit());
		                dbInstance.setSaltReplacementFrequency(dsdneonatalvisits.getSaltReplacementFrequency());
		                dbInstance.setSaltReplacementNote(dsdneonatalvisits.getSaltReplacementNote());
		                dbInstance.setOestrogen(dsdneonatalvisits.getOestrogen());
		                dbInstance.setOestrogenNote(dsdneonatalvisits.getOestrogenNote());
		                dbInstance.setTestosterone(dsdneonatalvisits.getTestosterone());
		                dbInstance.setTestosteroneNote(dsdneonatalvisits.getTestosteroneNote());
		                dbInstance.setGnrhAnalogues(dsdneonatalvisits.getGnrhAnalogues());
		                dbInstance.setGnrhAnaloguesNote(dsdneonatalvisits.getGnrhAnaloguesNote());
		                dbInstance.setAntihypertensives(dsdneonatalvisits.getAntihypertensives());
		                dbInstance.setAntihypertensivesNote(dsdneonatalvisits.getAntihypertensivesNote());
		                dbInstance.setAntidiabetic(dsdneonatalvisits.getAntidiabetic());
		                dbInstance.setAntidiabeticNote(dsdneonatalvisits.getAntidiabeticNote());
		                dbInstance.setAntidepressants(dsdneonatalvisits.getAntidepressants());
		                dbInstance.setAntidepressantsNote(dsdneonatalvisits.getAntidepressantsNote());
		                dbInstance.setOtherDrugs(dsdneonatalvisits.getOtherDrugs());
		                dbInstance.setOtherDrugsNote(dsdneonatalvisits.getOtherDrugsNote());
		                dbInstance.setGenitaliaSurgery(dsdneonatalvisits.getGenitaliaSurgery());
		                dbInstance.setGenitaliaSurgeryNote(dsdneonatalvisits.getGenitaliaSurgeryNote());
		                dbInstance.setOtherSurgery(dsdneonatalvisits.getOtherSurgery());
		                dbInstance.setOtherSurgeryNote(dsdneonatalvisits.getOtherSurgeryNote());
		                dbInstance.setOtherCongenitalAbnormalities(dsdneonatalvisits.getOtherCongenitalAbnormalities());
		                dbInstance.setOtherCongenitalAbnormalitiesNote(dsdneonatalvisits.getOtherCongenitalAbnormalitiesNote());
		                dbInstance.setDiabetesMellitusType1(dsdneonatalvisits.getDiabetesMellitusType1());
		                dbInstance.setDiabetesMellitusType1Note(dsdneonatalvisits.getDiabetesMellitusType1Note());
		                dbInstance.setDiabetesMellitusType2(dsdneonatalvisits.getDiabetesMellitusType2());
		                dbInstance.setDiabetesMellitusType2Note(dsdneonatalvisits.getDiabetesMellitusType2Note());
		                dbInstance.setHypertension(dsdneonatalvisits.getHypertension());
		                dbInstance.setHypertensionNote(dsdneonatalvisits.getHypertensionNote());
		                dbInstance.setThyroidDisease(dsdneonatalvisits.getThyroidDisease());
		                dbInstance.setThyroidDiseaseNote(dsdneonatalvisits.getThyroidDiseaseNote());
		                dbInstance.setOsteoporosis(dsdneonatalvisits.getOsteoporosis());
		                dbInstance.setOsteoporosisNote(dsdneonatalvisits.getOsteoporosisNote());
		                dbInstance.setStroke(dsdneonatalvisits.getStroke());
		                dbInstance.setStrokeNote(dsdneonatalvisits.getStrokeNote());
		                dbInstance.setCardiovascularDisease(dsdneonatalvisits.getCardiovascularDisease());
		                dbInstance.setCardiovascularDiseaseNote(dsdneonatalvisits.getCardiovascularDiseaseNote());
		                dbInstance.setSmoking(dsdneonatalvisits.getSmoking());
		                dbInstance.setSmokingNote(dsdneonatalvisits.getSmokingNote());
		                dbInstance.setAnaemia(dsdneonatalvisits.getAnaemia());
		                dbInstance.setAnaemiaNote(dsdneonatalvisits.getAnaemiaNote());
		                dbInstance.setDepression(dsdneonatalvisits.getDepression());
		                dbInstance.setDepressionNote(dsdneonatalvisits.getDepressionNote());
		                dbInstance.setAnxiety(dsdneonatalvisits.getAnxiety());
		                dbInstance.setAnxietyNote(dsdneonatalvisits.getAnxietyNote());
		                dbInstance.setPsychosis(dsdneonatalvisits.getPsychosis());
		                dbInstance.setPsychosisNote(dsdneonatalvisits.getPsychosisNote());
		                dbInstance.setOtherMentalHealthProblem(dsdneonatalvisits.getOtherMentalHealthProblem());
		                dbInstance.setOtherMentalHealthProblemNote(dsdneonatalvisits.getOtherMentalHealthProblemNote());
		                dbInstance.setJointHypermobility(dsdneonatalvisits.getJointHypermobility());
		                dbInstance.setJointHypermobilityNote(dsdneonatalvisits.getJointHypermobilityNote());
		                dbInstance.setOtherComorbidCondition(dsdneonatalvisits.getOtherComorbidCondition());
		                dbInstance.setOtherComorbidConditionNote(dsdneonatalvisits.getOtherComorbidConditionNote());
		                dbInstance.setSodium(dsdneonatalvisits.getSodium());
		                dbInstance.setSodiumDate(dsdneonatalvisits.getSodiumDate());
		                dbInstance.setSodiumTime(dsdneonatalvisits.getSodiumTime());
		                dbInstance.setSodiumNote(dsdneonatalvisits.getSodiumNote());
		                dbInstance.setPotassium(dsdneonatalvisits.getPotassium());
		                dbInstance.setPotassiumDate(dsdneonatalvisits.getPotassiumDate());
		                dbInstance.setPotassiumTime(dsdneonatalvisits.getPotassiumTime());
		                dbInstance.setPotassiumNote(dsdneonatalvisits.getPotassiumNote());
		                dbInstance.setDeoxycortisol11(dsdneonatalvisits.getDeoxycortisol11());
		                dbInstance.setDeoxycortisol11Date(dsdneonatalvisits.getDeoxycortisol11Date());
		                dbInstance.setDeoxycortisol11Time(dsdneonatalvisits.getDeoxycortisol11Time());
		                dbInstance.setDeoxycortisol11Note(dsdneonatalvisits.getDeoxycortisol11Note());
		                dbInstance.setRenin(dsdneonatalvisits.getRenin());
		                dbInstance.setReninDate(dsdneonatalvisits.getReninDate());
		                dbInstance.setReninTime(dsdneonatalvisits.getReninTime());
		                dbInstance.setReninNote(dsdneonatalvisits.getReninNote());
		                dbInstance.setPlasmaReninActivity(dsdneonatalvisits.getPlasmaReninActivity());
		                dbInstance.setPlasmaReninActivityDate(dsdneonatalvisits.getPlasmaReninActivityDate());
		                dbInstance.setPlasmaReninActivityTime(dsdneonatalvisits.getPlasmaReninActivityTime());
		                dbInstance.setPlasmaReninActivityNote(dsdneonatalvisits.getPlasmaReninActivityNote());
		                dbInstance.setOhp17(dsdneonatalvisits.getOhp17());
		                dbInstance.setOhp17Date(dsdneonatalvisits.getOhp17Date());
		                dbInstance.setOhp17Time(dsdneonatalvisits.getOhp17Time());
		                dbInstance.setOhp17Note(dsdneonatalvisits.getOhp17Note());
		                dbInstance.setAndostenedione(dsdneonatalvisits.getAndostenedione());
		                dbInstance.setAndostenedioneDate(dsdneonatalvisits.getAndostenedioneDate());
		                dbInstance.setAndostenedioneTime(dsdneonatalvisits.getAndostenedioneTime());
		                dbInstance.setAndostenedioneNote(dsdneonatalvisits.getAndostenedioneNote());
		                dbInstance.setTotalTestosterone(dsdneonatalvisits.getTotalTestosterone());
		                dbInstance.setTotalTestosteroneDate(dsdneonatalvisits.getTotalTestosteroneDate());
		                dbInstance.setTotalTestosteroneTime(dsdneonatalvisits.getTotalTestosteroneTime());
		                dbInstance.setTotalTestosteroneNote(dsdneonatalvisits.getTotalTestosteroneNote());
		                dbInstance.setDihydrotestosterone(dsdneonatalvisits.getDihydrotestosterone());
		                dbInstance.setDihydrotestosteroneDate(dsdneonatalvisits.getDihydrotestosteroneDate());
		                dbInstance.setDihydrotestosteroneTime(dsdneonatalvisits.getDihydrotestosteroneTime());
		                dbInstance.setDihydrotestosteroneNote(dsdneonatalvisits.getDihydrotestosteroneNote());
		                dbInstance.setLh(dsdneonatalvisits.getLh());
		                dbInstance.setLhDate(dsdneonatalvisits.getLhDate());
		                dbInstance.setLhTime(dsdneonatalvisits.getLhTime());
		                dbInstance.setLhNote(dsdneonatalvisits.getLhNote());
		                dbInstance.setFsh(dsdneonatalvisits.getFsh());
		                dbInstance.setFshDate(dsdneonatalvisits.getFshDate());
		                dbInstance.setFshTime(dsdneonatalvisits.getFshTime());
		                dbInstance.setFshNote(dsdneonatalvisits.getFshNote());
		                dbInstance.setOestradiol(dsdneonatalvisits.getOestradiol());
		                dbInstance.setOestradiolDate(dsdneonatalvisits.getOestradiolDate());
		                dbInstance.setOestradiolTime(dsdneonatalvisits.getOestradiolTime());
		                dbInstance.setOestradiolNote(dsdneonatalvisits.getOestradiolNote());
		                dbInstance.setProgesterone(dsdneonatalvisits.getProgesterone());
		                dbInstance.setProgesteroneDate(dsdneonatalvisits.getProgesteroneDate());
		                dbInstance.setProgesteroneTime(dsdneonatalvisits.getProgesteroneTime());
		                dbInstance.setProgesteroneNote(dsdneonatalvisits.getProgesteroneNote());
		                dbInstance.setInhibinB(dsdneonatalvisits.getInhibinB());
		                dbInstance.setInhibinBDate(dsdneonatalvisits.getInhibinBDate());
		                dbInstance.setInhibinBTime(dsdneonatalvisits.getInhibinBTime());
		                dbInstance.setInhibinBNote(dsdneonatalvisits.getInhibinBNote());
		                dbInstance.setHaemoglobin(dsdneonatalvisits.getHaemoglobin());
		                dbInstance.setHaemoglobinDate(dsdneonatalvisits.getHaemoglobinDate());
		                dbInstance.setHaemoglobinTime(dsdneonatalvisits.getHaemoglobinTime());
		                dbInstance.setHaemoglobinNote(dsdneonatalvisits.getHaemoglobinNote());
		                dbInstance.setHaematocrit(dsdneonatalvisits.getHaematocrit());
		                dbInstance.setHaematocritDate(dsdneonatalvisits.getHaematocritDate());
		                dbInstance.setHaematocritTime(dsdneonatalvisits.getHaematocritTime());
		                dbInstance.setHaematocritNote(dsdneonatalvisits.getHaematocritNote());
		                dbInstance.setShbg(dsdneonatalvisits.getShbg());
		                dbInstance.setShbgDate(dsdneonatalvisits.getShbgDate());
		                dbInstance.setShbgTime(dsdneonatalvisits.getShbgTime());
		                dbInstance.setShbgNote(dsdneonatalvisits.getShbgNote());
		                dbInstance.setGlucose(dsdneonatalvisits.getGlucose());
		                dbInstance.setGlucoseDate(dsdneonatalvisits.getGlucoseDate());
		                dbInstance.setGlucoseTime(dsdneonatalvisits.getGlucoseTime());
		                dbInstance.setGlucoseNote(dsdneonatalvisits.getGlucoseNote());
		                dbInstance.setActh(dsdneonatalvisits.getActh());
		                dbInstance.setActhDate(dsdneonatalvisits.getActhDate());
		                dbInstance.setActhTime(dsdneonatalvisits.getActhTime());
		                dbInstance.setActhNote(dsdneonatalvisits.getActhNote());
		                dbInstance.setCortisol(dsdneonatalvisits.getCortisol());
		                dbInstance.setCortisolDate(dsdneonatalvisits.getCortisolDate());
		                dbInstance.setCortisolTime(dsdneonatalvisits.getCortisolTime());
		                dbInstance.setCortisolNote(dsdneonatalvisits.getCortisolNote());
		                dbInstance.setDheas(dsdneonatalvisits.getDheas());
		                dbInstance.setDheasDate(dsdneonatalvisits.getDheasDate());
		                dbInstance.setDheasTime(dsdneonatalvisits.getDheasTime());
		                dbInstance.setDheasNote(dsdneonatalvisits.getDheasNote());
		                dbInstance.setUrineSteroidsByGcms(dsdneonatalvisits.getUrineSteroidsByGcms());
		                dbInstance.setUrineSteroidsByGcmsDate(dsdneonatalvisits.getUrineSteroidsByGcmsDate());
		                dbInstance.setUrineSteroidsByGcmsTime(dsdneonatalvisits.getUrineSteroidsByGcmsTime());
		                dbInstance.setUrineSteroidsByGcmsNote(dsdneonatalvisits.getUrineSteroidsByGcmsNote());
		                dbInstance.setBoneAgeTestDate(dsdneonatalvisits.getBoneAgeTestDate());
		                dbInstance.setBoneAgeResult(dsdneonatalvisits.getBoneAgeResult());
		                dbInstance.setBoneAgeTestMethod(dsdneonatalvisits.getBoneAgeTestMethod());
		                dbInstance.setBoneMineralDensityDone(dsdneonatalvisits.getBoneMineralDensityDone());
		                dbInstance.setBoneMineralDensityTestDate(dsdneonatalvisits.getBoneMineralDensityTestDate());
		                dbInstance.setBoneMineralDensityTestResult(dsdneonatalvisits.getBoneMineralDensityTestResult());
		                dbInstance.setBoneMineralDensityTestResultNote(dsdneonatalvisits.getBoneMineralDensityTestResultNote());
		                dbInstance.setMaleGenitalStage(dsdneonatalvisits.getMaleGenitalStage());
		                dbInstance.setMalePubicHairStage(dsdneonatalvisits.getMalePubicHairStage());
		                dbInstance.setMaleAxillaryHairStage(dsdneonatalvisits.getMaleAxillaryHairStage());
		                dbInstance.setTesticularVolumeLeft(dsdneonatalvisits.getTesticularVolumeLeft());
		                dbInstance.setTesticularVolumeRight(dsdneonatalvisits.getTesticularVolumeRight());
		                dbInstance.setTesticularTexture(dsdneonatalvisits.getTesticularTexture());
		                dbInstance.setEvidenceOfTart(dsdneonatalvisits.getEvidenceOfTart());
		                dbInstance.setEvidenceOfTartNote(dsdneonatalvisits.getEvidenceOfTartNote());
		                dbInstance.setMaleFertilityDesired(dsdneonatalvisits.getMaleFertilityDesired());
		                dbInstance.setSpermStorage(dsdneonatalvisits.getSpermStorage());
		                dbInstance.setPartnerPastPregnancyNumber(dsdneonatalvisits.getPartnerPastPregnancyNumber());
		                dbInstance.setMaleAssistedConceptionHistory(dsdneonatalvisits.getMaleAssistedConceptionHistory());
		                dbInstance.setMaleAssistedConceptionHistoryNote(dsdneonatalvisits.getMaleAssistedConceptionHistoryNote());
		                dbInstance.setCahVisitNote(dsdneonatalvisits.getCahVisitNote());
		                dbInstance.setFemaleBreastStage(dsdneonatalvisits.getFemaleBreastStage());
		                dbInstance.setFemalePubicHairStage(dsdneonatalvisits.getFemalePubicHairStage());
		                dbInstance.setFemaleAxillaryHairStage(dsdneonatalvisits.getFemaleAxillaryHairStage());
		                dbInstance.setMenarche(dsdneonatalvisits.getMenarche());
		                dbInstance.setAgeAtMenarche(dsdneonatalvisits.getAgeAtMenarche());
		                dbInstance.setRegularMenstrualCycle(dsdneonatalvisits.getRegularMenstrualCycle());
		                dbInstance.setFemaleFertilityDesired(dsdneonatalvisits.getFemaleFertilityDesired());
		                dbInstance.setPastPregnancyNumber(dsdneonatalvisits.getPastPregnancyNumber());
		                dbInstance.setLiveBirthNumber(dsdneonatalvisits.getLiveBirthNumber());
		                dbInstance.setFemaleAssistedConceptionHistory(dsdneonatalvisits.getFemaleAssistedConceptionHistory());
		                dbInstance.setFemaleAssistedConceptionHistoryNote(dsdneonatalvisits.getFemaleAssistedConceptionHistoryNote());
		                dbInstance.setDsdCah(dsdCah);
//		                dsdCah.getdsdneonatalvisitss().add(dbInstance);
		                log.info("Found episode size = " + dsdneonatalvisits.getdsdneonatalvisitsEpisodes().size());
		                if (CollectionUtils.isNotEmpty(dsdneonatalvisits.getdsdneonatalvisitsEpisodes())) {
		                    for (dsdneonatalvisitsEpisode episode : dbInstance.getdsdneonatalvisitsEpisodes()) {
		                        log.info("deleting Episode = " + episode);
		                        this.dsdneonatalvisitsEpisodeDao.delete(episode);
		                    }
		                    Set<dsdneonatalvisitsEpisode> dsdneonatalvisitsEpisodeSet = new HashSet<>();
		                    for (dsdneonatalvisitsEpisode episode : dsdneonatalvisits.getdsdneonatalvisitsEpisodes()) {
		                        log.info("Episode = " + episode);
		                        episode.setdsdneonatalvisits(dbInstance);
		                        dsdneonatalvisitsEpisodeSet.add(episode);
		                    }
		                    dbInstance.setdsdneonatalvisitsEpisodes(dsdneonatalvisitsEpisodeSet);
		                }
		                log.info("Found med size = " + dsdneonatalvisits.getdsdneonatalvisitsMedDetails().size());
		                if (CollectionUtils.isNotEmpty(dsdneonatalvisits.getdsdneonatalvisitsMedDetails())) {
		                    for (dsdneonatalvisitsMedDetail detail : dbInstance.getdsdneonatalvisitsMedDetails()) {
		                        log.info("deleting Med Detail = " + detail);
		                        this.dsdneonatalvisitsMedDetailDao.delete(detail);
		                    }
		                    Set<dsdneonatalvisitsMedDetail> dsdneonatalvisitsMedDetailSet = new HashSet<>();
		                    for (dsdneonatalvisitsMedDetail detail : dsdneonatalvisits.getdsdneonatalvisitsMedDetails()) {
		                        log.info("detail = " + detail);
		                        detail.setdsdneonatalvisits(dbInstance);
		                        dsdneonatalvisitsMedDetailSet.add(detail);
		                    }
		                    dbInstance.setdsdneonatalvisitsMedDetails(dsdneonatalvisitsMedDetailSet);
		                }
		                this.dsdneonatalvisitsDao.attachDirty(dbInstance);
		                dbDsdIdentifier.setUploadTime(new Timestamp(System.currentTimeMillis()));
		                this.dsdIdentifierDao.attachDirty(dbDsdIdentifier);
		                this.auditService.logUpdateCahVisit(dbInstance);
		                log.info("Saving CAH Visit, after save, weight from DB = " + dbInstance.getWeight());
		                log.info("Saving CAH Visit, after save, height from DB = " + dbInstance.getHeight());
		            } else if (dsdneonatalvisits != null) {
		                log.info("Creating a new CAH visit");
		                if (CollectionUtils.isNotEmpty(dsdneonatalvisits.getdsdneonatalvisitsEpisodes())) {
		                    for (dsdneonatalvisitsEpisode episode : dsdneonatalvisits.getdsdneonatalvisitsEpisodes()) {
		                        episode.setdsdneonatalvisits(dsdneonatalvisits);
		                    }
		                }
		                if (CollectionUtils.isNotEmpty(dsdneonatalvisits.getdsdneonatalvisitsMedDetails())) {
		                    for (dsdneonatalvisitsMedDetail detail : dsdneonatalvisits.getdsdneonatalvisitsMedDetails()) {
		                        detail.setdsdneonatalvisits(dsdneonatalvisits);
		                    }
		                }
		                Set<dsdneonatalvisits> visitSet = new HashSet<>();
		                visitSet.add(dsdneonatalvisits);
		                dsdneonatalvisits.setDsdCah(dsdCah);
		                dsdCah.setdsdneonatalvisitss(visitSet);
		                dsdneonatalvisitsDao.save(dsdneonatalvisits);
		                this.auditService.logCreateCahVisit(dsdneonatalvisits);
		            }
		        } catch (Exception e) {
		            throw new ServiceException("Error occurred while saving DSD record into database.", e);
		        }
		        return dsdneonatalvisits;
		    }

				
	}


