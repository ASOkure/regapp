package uk.ac.nesc.idsd.action.search;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import uk.ac.nesc.idsd.action.AbstractAction;
import uk.ac.nesc.idsd.bean.AuditHistoryBean;
import uk.ac.nesc.idsd.bean.DsdIdentifierResearchResultBean;
import uk.ac.nesc.idsd.exception.ErrorCodes;
import uk.ac.nesc.idsd.model.Country;
import uk.ac.nesc.idsd.model.DsdCahVisit;
import uk.ac.nesc.idsd.model.DsdCahVisitEpisode;
import uk.ac.nesc.idsd.model.DsdCahVisitMedDetail;
import uk.ac.nesc.idsd.model.DsdIdentifier;
import uk.ac.nesc.idsd.model.Option;
import uk.ac.nesc.idsd.model.PatientUser;
import uk.ac.nesc.idsd.model.PortalUser;
import uk.ac.nesc.idsd.model.Section;
import uk.ac.nesc.idsd.security.Anonymization;
import uk.ac.nesc.idsd.security.Authorization;
import uk.ac.nesc.idsd.security.Consent;
import uk.ac.nesc.idsd.security.Ownership;
import uk.ac.nesc.idsd.service.AuditService;
import uk.ac.nesc.idsd.service.DsdIdentifierService;
import uk.ac.nesc.idsd.service.ParameterService;
import uk.ac.nesc.idsd.service.UserService;
import uk.ac.nesc.idsd.service.exception.ServiceException;
import uk.ac.nesc.idsd.util.Utility;

import java.util.ArrayList;
import java.util.List;

public class ReadDsdAction extends AbstractAction {
    private static final long serialVersionUID = 4542293404653115521L;

    @Autowired
    private DsdIdentifierService dsdIdentifierService;
    @Autowired
    private ParameterService parameterService;
    @Autowired
    private AuditService auditService;
    @Autowired
    private UserService userService;

    private Long registerId;
    private DsdIdentifier dsdIdentifier;
    private boolean isEditable = false;
    private boolean isFullView = false;
    private boolean isReferable = false;

    private List<Section> dsdIdentifierSections = new ArrayList<Section>(0);
    private Section dsdAssessmentSection;
    private Section dsdGeneAnalysisSection;
    private Section dsdCahSection;
    private List<Section> cahVisitSections = new ArrayList<Section>(0);

    private String consentString;

    private List<AuditHistoryBean> recordHistory = new ArrayList<AuditHistoryBean>(0);
    private List<DsdIdentifierResearchResultBean> researchResults = new ArrayList<DsdIdentifierResearchResultBean>(0);
    private List<Country> countries = new ArrayList<Country>(0);
    private List<PatientUser> patientUsers = new ArrayList<PatientUser>(0);

    private String mode;
    private Long dsdCahVisitId;
    private DsdCahVisit dsdCahVisit;

    private List<DsdCahVisitEpisode> dsdCahVisitEpisodes;
    private List<DsdCahVisitMedDetail> dsdCahVisitMedDetails;
    private List<Option> unitOptions;
    private List<Option> oralSteroidsOptions;
    private List<Option> yesNoOptions;
    private List<Option> emergencyAttendanceOptions;
    private List<Option> glucocorticoidsMedicineOptions;

    public void prepare() {
    }

    private void prepareForJspView() {
        unitOptions = parameterService.getMedUnitOptions();
        oralSteroidsOptions = parameterService.getOralSteroidsOptions();
        yesNoOptions = parameterService.getYesNoOptions();
        emergencyAttendanceOptions = parameterService.getEmergencyAttendanceOptions();
        glucocorticoidsMedicineOptions = parameterService.getGlucocorticoidsMedicineOptions();
    }

    /**
     * View full detail of a DSD record by giving registerId
     * steps:
     * 1. check permission to view record
     * 2. check registerId is not null
     * 3. check if this record exists
     * 4. check consent/ownership for this record
     * 5.
     *
     * @return String
     * @throws Exception
     */
    @PreAuthorize("hasAnyRole('ROLE_RESEARCHER','ROLE_CONTRIBUTOR','ROLE_AUDITOR','ROLE_LOCAL_CONTRIBUTOR')")
    public String readDsdRecord() {
        String sessionUsername = null;
        try {
            sessionUsername = Utility.getLoginUserName();
        } catch (ServiceException e) {
            log.error("Error while retrieving username from session. ", e);
            this.addActionError("Error while retrieving user session. Error: " + e.getMessage());
        }
        if (this.registerId == null) {
            log.warn("User " + sessionUsername + " was trying to read record without given a record ID. ");
            this.addActionError(ErrorCodes.NO_REGISTRY_ID.getMessage());
            return ERROR;
        }
        DsdIdentifier d = this.dsdIdentifierService.getById(this.registerId);
        if (d == null) {
            log.warn("User " + sessionUsername + " was trying to read record: " + this.registerId + ", and it does not exist. ");
            this.addActionError(ErrorCodes.NO_RECORD_FOUND.getMessage());
            return ERROR;
        }
        //TO-DO this relates to a rule - whether users can upload records not belonging to their centres.
        //if no, simple, only check Consent will be OK.
        //if yes, problem, we need to check both. In case user uploads a record that does not belong to their centre, but he is the owner.
        PortalUser portalUser;
        try {
            portalUser = userService.getPortalUserByUsername(sessionUsername);
        } catch (ServiceException e) {
            log.error("Error when loading user from username: " + sessionUsername, e);
            this.addActionError(ErrorCodes.USER_LOOKUP_ERROR.getMessage());
            return ERROR;
        }
        boolean isOwner = Ownership.isEditable(d, portalUser);
        if (!Consent.check(d, portalUser) && !isOwner && !Authorization.isAuditor(portalUser)) {
            return CONSENT;
        }
        this.dsdIdentifier = Anonymization.anonymize(d, portalUser);
        //for owners, record can be edited. And should see full view
        if (isOwner) {
            this.isEditable = true;
            this.isFullView = true;
            this.isReferable = true;
        } else if (Authorization.isAuditor(portalUser)) {//for researchers, should see full view.
            this.isFullView = true;
        }

        try {
            if (this.isEditable || this.isFullView) {
                this.dsdIdentifierSections = this.parameterService.getViewDsdCoreSections();
                this.dsdAssessmentSection = this.parameterService.getDsdAssessmentSection();
                this.dsdGeneAnalysisSection = this.parameterService.getDsdGeneAnalysisSection();
            } else {
                this.dsdIdentifierSections = this.parameterService.getReadDsdSectionsForNoneOwner();
            }
            this.dsdCahSection = this.parameterService.getViewDsdCahSection();
        } catch (Exception e) {
            this.addActionError(ErrorCodes.RECORD_LOOKUP_ERROR.getMessage() + this.dsdIdentifier.getRegisterId());
            log.error("Error when viewing registerId: " + this.dsdIdentifier.getRegisterId(), e);
        }

        //finally, prepare other info for display
        this.consentString = Utility.getConsentLevels().get(this.dsdIdentifier.getConsent().toString());
        this.auditService.auditRead(this.dsdIdentifier);
        this.recordHistory = this.auditService.getHistory(registerId.toString());
        this.researchResults = this.dsdIdentifierService.getDsdIdentifierResearchResultBeans(portalUser, d);
        this.patientUsers = this.userService.getPatientUsersForDsdIdentifier(registerId);
        return SUCCESS;
    }

    public String readDsdCahVisitRecord() {
        if (this.dsdCahVisitId == null) {
            return ERROR;
        }
        dsdIdentifier = this.dsdIdentifierService.getById(this.registerId);
        cahVisitSections = parameterService.getCahVisitSections(dsdIdentifier.getSexAssigned());
        dsdCahVisit = dsdIdentifierService.getCahVisitById(dsdCahVisitId);
        prepareForJspView();
        dsdCahVisitEpisodes = new ArrayList<DsdCahVisitEpisode>(dsdCahVisit.getDsdCahVisitEpisodes());
        dsdCahVisitMedDetails = new ArrayList<DsdCahVisitMedDetail>(dsdCahVisit.getDsdCahVisitMedDetails());
        return SUCCESS;
    }

    public Long getRegisterId() {
        return registerId;
    }

    public void setRegisterId(Long registerId) {
        this.registerId = registerId;
    }

    public DsdIdentifier getDsdIdentifier() {
        return dsdIdentifier;
    }

    public void setDsdIdentifier(DsdIdentifier dsdIdentifier) {
        this.dsdIdentifier = dsdIdentifier;
    }

    public void setDsdIdentifierService(DsdIdentifierService dsdIdentifierService) {
        this.dsdIdentifierService = dsdIdentifierService;
    }

    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }

    public List<Section> getDsdIdentifierSections() {
        return dsdIdentifierSections;
    }

    public void setDsdIdentifierSections(List<Section> dsdIdentifierSections) {
        this.dsdIdentifierSections = dsdIdentifierSections;
    }

    public Section getDsdAssessmentSection() {
        return dsdAssessmentSection;
    }

    public void setDsdAssessmentSection(Section dsdAssessmentSection) {
        this.dsdAssessmentSection = dsdAssessmentSection;
    }

    public Section getDsdGeneAnalysisSection() {
        return dsdGeneAnalysisSection;
    }

    public void setDsdGeneAnalysisSection(Section dsdGeneAnalysisSection) {
        this.dsdGeneAnalysisSection = dsdGeneAnalysisSection;
    }

    public String getConsentString() {
        return consentString;
    }

    public void setConsentString(String consentString) {
        this.consentString = consentString;
    }

    public void setAuditService(AuditService auditService) {
        this.auditService = auditService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public List<AuditHistoryBean> getRecordHistory() {
        return recordHistory;
    }

    public void setRecordHistory(List<AuditHistoryBean> recordHistory) {
        this.recordHistory = recordHistory;
    }

    public List<DsdIdentifierResearchResultBean> getResearchResults() {
        return researchResults;
    }

    public void setResearchResults(
            List<DsdIdentifierResearchResultBean> researchResults) {
        this.researchResults = researchResults;
    }

    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }

    public boolean getIsEditable() {
        return isEditable;
    }

    public void setIsEditable(boolean isEditable) {
        this.isEditable = isEditable;
    }

    public boolean getIsFullView() {
        return isFullView;
    }

    public void setIsFullView(boolean isFullView) {
        this.isFullView = isFullView;
    }

    public boolean isReferable() {
        return isReferable;
    }

    public void setReferable(boolean isReferable) {
        this.isReferable = isReferable;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public Section getDsdCahSection() {
        return dsdCahSection;
    }

    public void setDsdCahSection(Section dsdCahSection) {
        this.dsdCahSection = dsdCahSection;
    }

    public List<PatientUser> getPatientUsers() {
        return patientUsers;
    }

    public void setPatientUsers(List<PatientUser> patientUsers) {
        this.patientUsers = patientUsers;
    }

    public List<Section> getCahVisitSections() {
        return cahVisitSections;
    }

    public void setCahVisitSections(List<Section> cahVisitSections) {
        this.cahVisitSections = cahVisitSections;
    }

    public Long getDsdCahVisitId() {
        return dsdCahVisitId;
    }

    public void setDsdCahVisitId(Long dsdCahVisitId) {
        this.dsdCahVisitId = dsdCahVisitId;
    }

    public DsdCahVisit getDsdCahVisit() {
        return dsdCahVisit;
    }

    public void setDsdCahVisit(DsdCahVisit dsdCahVisit) {
        this.dsdCahVisit = dsdCahVisit;
    }

    public List<Option> getGlucocorticoidsMedicineOptions() {
        return glucocorticoidsMedicineOptions;
    }

    public void setGlucocorticoidsMedicineOptions(List<Option> glucocorticoidsMedicineOptions) {
        this.glucocorticoidsMedicineOptions = glucocorticoidsMedicineOptions;
    }

    public List<Option> getEmergencyAttendanceOptions() {
        return emergencyAttendanceOptions;
    }

    public void setEmergencyAttendanceOptions(List<Option> emergencyAttendanceOptions) {
        this.emergencyAttendanceOptions = emergencyAttendanceOptions;
    }

    public List<Option> getYesNoOptions() {
        return yesNoOptions;
    }

    public void setYesNoOptions(List<Option> yesNoOptions) {
        this.yesNoOptions = yesNoOptions;
    }

    public List<Option> getOralSteroidsOptions() {
        return oralSteroidsOptions;
    }

    public void setOralSteroidsOptions(List<Option> oralSteroidsOptions) {
        this.oralSteroidsOptions = oralSteroidsOptions;
    }

    public List<Option> getUnitOptions() {
        return unitOptions;
    }

    public void setUnitOptions(List<Option> unitOptions) {
        this.unitOptions = unitOptions;
    }

    public List<DsdCahVisitMedDetail> getDsdCahVisitMedDetails() {
        return dsdCahVisitMedDetails;
    }

    public void setDsdCahVisitMedDetails(List<DsdCahVisitMedDetail> dsdCahVisitMedDetails) {
        this.dsdCahVisitMedDetails = dsdCahVisitMedDetails;
    }

    public List<DsdCahVisitEpisode> getDsdCahVisitEpisodes() {
        return dsdCahVisitEpisodes;
    }

    public void setDsdCahVisitEpisodes(List<DsdCahVisitEpisode> dsdCahVisitEpisodes) {
        this.dsdCahVisitEpisodes = dsdCahVisitEpisodes;
    }
}