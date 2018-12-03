package uk.ac.nesc.idsd.action.search;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import uk.ac.nesc.idsd.action.AbstractAction;
import uk.ac.nesc.idsd.bean.CentreInfo;
import uk.ac.nesc.idsd.exception.ErrorCodes;
import uk.ac.nesc.idsd.model.Centre;
import uk.ac.nesc.idsd.model.DsdIdentifier;
import uk.ac.nesc.idsd.model.Parameter;
import uk.ac.nesc.idsd.model.PatientUser;
import uk.ac.nesc.idsd.model.PortalUser;
import uk.ac.nesc.idsd.security.Authorization;
import uk.ac.nesc.idsd.security.Consent;
import uk.ac.nesc.idsd.security.Ownership;
import uk.ac.nesc.idsd.service.AuditService;
import uk.ac.nesc.idsd.service.DsdIdentifierService;
import uk.ac.nesc.idsd.service.ParameterService;
import uk.ac.nesc.idsd.service.UserService;
import uk.ac.nesc.idsd.service.exception.ServiceException;
import uk.ac.nesc.idsd.util.Utility;
import uk.ac.nesc.idsd.util.email.EmailService;

import java.util.ArrayList;
import java.util.List;

public class ReferPatientDsdAction extends AbstractAction {
    private static final long serialVersionUID = 4542293404653115521L;

    @Autowired
    private DsdIdentifierService dsdIdentifierService;
    @Autowired
    private ParameterService parameterService;
    @Autowired
    private AuditService auditService;
    @Autowired
    private UserService userService;
    @Autowired
    private EmailService emailService;
    private Long registerId;
    private DsdIdentifier dsdIdentifier;
    private String patientEmail;
    private String patientEmailRepeat;
    //private List<Section> sections;

    private List<Parameter> parameters = new ArrayList<Parameter>(0);
    private String consentString;

    private String mode;

    private CentreInfo centreInfo;

    public void prepare() {

    }

    @PreAuthorize("hasAnyRole('ROLE_RESEARCHER','ROLE_CONTRIBUTOR','ROLE_AUDITOR','ROLE_LOCAL_CONTRIBUTOR')")
    public String referPatientView() {
        String loginUserName = null;
        try {
            loginUserName = Utility.getLoginUserName();
        } catch (ServiceException e) {
            log.error("Error while retrieving username stored in session", e);
            this.addActionError(e.getMessage());
        }
        if (this.registerId == null) {
            log.warn("User " + loginUserName + " was trying to read record without given a record ID. ");
            this.addActionError(ErrorCodes.NO_REGISTRY_ID.getMessage());
            return ERROR;
        }
        this.dsdIdentifier = this.dsdIdentifierService.getById(this.registerId);
        if (this.dsdIdentifier == null) {
            log.warn("User " + loginUserName + " was trying to read record: " + this.registerId + ", and it does not exist. ");
            this.addActionError(ErrorCodes.NO_RECORD_FOUND.getMessage());
            return ERROR;
        }
        //TO-DO this relates to a rule - whether users can upload records not belonging to their centres.
        //if no, simple, only check Consent will be OK.
        //if yes, problem, we need to check both. In case user uploads a record that does not belong to their centre, but he is the owner.
        PortalUser portalUser;
        try {
            portalUser = userService.getPortalUserByUsername(loginUserName);
        } catch (ServiceException e) {
            log.error("Error when loading user from username: " + loginUserName, e);
            this.addActionError(ErrorCodes.USER_LOOKUP_ERROR.getMessage());
            return ERROR;
        }
        boolean isOwner = Ownership.check(this.dsdIdentifier, portalUser);
        if (!Consent.check(this.dsdIdentifier, portalUser) && !isOwner && !Authorization.isAuditor(portalUser)) {
            return CONSENT;
        }
//        this.dsdIdentifier = Anonymization.anonymize(d, portalUser);
        try {
            this.parameters = this.parameterService.getParametersForPatientUser();
        } catch (Exception e) {
            this.addActionError(ErrorCodes.RECORD_LOOKUP_ERROR.getMessage() + this.dsdIdentifier.getRegisterId());
            log.error("Error when viewing registerId: " + this.dsdIdentifier.getRegisterId(), e);
        }

        // prepare centre desc
        centreInfo = loadCentreInfo(dsdIdentifier.getCentre(), portalUser);
        return SUCCESS;
    }

    private CentreInfo loadCentreInfo(Centre centre, PortalUser portalUser) {
        CentreInfo centreInfo = new CentreInfo();
        PortalUser lead = centre.getLeader();
        centreInfo.setName(lead.getCentre());
        centreInfo.setAddress(lead.getAddress());
        centreInfo.setLeadName(lead.getName());
        centreInfo.setLeadTel(lead.getTel());
        centreInfo.setLeadEmail(lead.getEmail());
        centreInfo.setLeadUrl(lead.getProfileUrl());

        centreInfo.setCentreSpiel(centre.getDescription());
        centreInfo.setClinicDates(centre.getClinicsDates());
        centreInfo.setLocalResources(centre.getLocalResources());
        centreInfo.setLocalEvents(centre.getLocalEvents());
        centreInfo.setStudies(centre.getActiveStudies());
        centreInfo.setFurtherInfo(centre.getAdditionalInfo());

        centreInfo.setCentreLead(portalUser.getUsername().equalsIgnoreCase(centre.getLeader().getUsername()));

        return centreInfo;
    }

    @PreAuthorize("hasAnyRole('ROLE_RESEARCHER','ROLE_CONTRIBUTOR','ROLE_AUDITOR','ROLE_LOCAL_CONTRIBUTOR')")
    public String referPatient() {
        String loginUserName = null;
        try {
            loginUserName = Utility.getLoginUserName();
        } catch (ServiceException e) {
            log.error("Error while retrieving username stored in session", e);
            this.addActionError(e.getMessage());
        }
        if (this.registerId == null) {
            log.warn("User " + loginUserName + " was trying to read record without given a record ID. ");
            this.addActionError(ErrorCodes.NO_REGISTRY_ID.getMessage());
            return ERROR;
        }
        if (StringUtils.isEmpty(this.patientEmail)) {
            log.error("Patient email is not given for dsd ID: " + registerId);
            this.addActionError(ErrorCodes.NO_PATIENT_EMAIL_GIVEN.getMessage());
            return ERROR;
        }
        if (StringUtils.isEmpty(this.patientEmailRepeat)) {
            log.error("Repeat patient email is not given for dsd ID: " + registerId);
            this.addActionError(ErrorCodes.NO_REPEAT_PATIENT_EMAIL_GIVEN.getMessage());
            return ERROR;
        }

        if (!this.patientEmailRepeat.equalsIgnoreCase(this.patientEmail)) {
            log.error("2 emails entered do not match for dsd ID: " + registerId);
            this.addActionError(ErrorCodes.PATIENT_EMAIL_NOT_MATCH.getMessage());
            return ERROR;
        }

        this.dsdIdentifier = this.dsdIdentifierService.getById(this.registerId);
        if (dsdIdentifier == null) {
            log.warn("User " + loginUserName + " was trying to read record: " + this.registerId + ", and it does not exist. ");
            this.addActionError(ErrorCodes.NO_RECORD_FOUND.getMessage());
            return ERROR;
        }
        PortalUser portalUser;
        try {
            portalUser = userService.getPortalUserByUsername(loginUserName);
        } catch (ServiceException e) {
            log.error("Error when loading user from username: " + loginUserName, e);
            this.addActionError(ErrorCodes.USER_LOOKUP_ERROR.getMessage());
            return ERROR;
        }
        boolean isOwner = Ownership.check(dsdIdentifier, portalUser);
        if (!Consent.check(dsdIdentifier, portalUser) && !isOwner && !Authorization.isAuditor(portalUser)) {
            return CONSENT;
        }
        PatientUser patientUser;
        if (isOwner) {
            try {
                if (userService.isPatientUserExist(patientEmail, dsdIdentifier.getRegisterId())) {
                    log.error(ErrorCodes.DUPLICATED_PATIENT_EMAIL_FOR_RECORD.getMessage());
                    this.addActionError(ErrorCodes.DUPLICATED_PATIENT_EMAIL_FOR_RECORD.getMessage());
                    return ERROR;
                }
                patientUser = userService.createPatientUser(patientEmail, dsdIdentifier.getRegisterId(), portalUser);
            } catch (Exception e) {
                log.error(ErrorCodes.PATIENT_USER_CREATION_FAILURE.getMessage(), e);
                this.addActionError(ErrorCodes.PATIENT_USER_CREATION_FAILURE.getMessage());
                return ERROR;
            }
        } else {
            log.error(ErrorCodes.CANNOT_ASSIGN_PATIENT_ACCESS_AS_NONE_OWNER.getMessage());
            this.addActionError(ErrorCodes.CANNOT_ASSIGN_PATIENT_ACCESS_AS_NONE_OWNER.getMessage());
            return ERROR;
        }
        if (null != patientUser) {
            this.patientEmail = patientUser.getUsername();
            emailService.send(Utility.getEmailRecipient(patientEmail),
                    "I-DSD Access Activation",
                    Utility.constructPatientAccessInvitationEmailMsg(patientEmail));
        } else {
            this.addActionError(ErrorCodes.PATIENT_USER_CREATION_FAILURE.getMessage());
        }
        //finally, prepare other info for display
        this.consentString = Utility.getConsentLevels().get(this.dsdIdentifier.getConsent().toString());
        this.auditService.logReferPatientAccess(this.dsdIdentifier);
        return SUCCESS;
    }

    /**
     * setters
     *
     * @return
     */
    
    
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

    public List<Parameter> getParameters() {
        return parameters;
    }

    public void setParameters(List<Parameter> parameters) {
        this.parameters = parameters;
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

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public CentreInfo getCentreInfo() {
        return centreInfo;
    }

    public void setCentreInfo(CentreInfo centreInfo) {
        this.centreInfo = centreInfo;
    }

    public String getPatientEmail() {
        return patientEmail;
    }

    public void setPatientEmail(String patientEmail) {
        this.patientEmail = patientEmail;
    }

    public String getPatientEmailRepeat() {
        return patientEmailRepeat;
    }

    public void setPatientEmailRepeat(String patientEmailRepeat) {
        this.patientEmailRepeat = patientEmailRepeat;
    }

}
