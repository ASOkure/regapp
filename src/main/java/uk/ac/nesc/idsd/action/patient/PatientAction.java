package uk.ac.nesc.idsd.action.patient;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import uk.ac.nesc.idsd.Constants;
import uk.ac.nesc.idsd.action.AbstractAction;
import uk.ac.nesc.idsd.bean.CentreInfo;
import uk.ac.nesc.idsd.exception.ErrorCodes;
import uk.ac.nesc.idsd.exception.UserException;
import uk.ac.nesc.idsd.model.Centre;
import uk.ac.nesc.idsd.model.DsdIdentifier;
import uk.ac.nesc.idsd.model.Parameter;
import uk.ac.nesc.idsd.model.PatientUser;
import uk.ac.nesc.idsd.model.PortalUser;
import uk.ac.nesc.idsd.security.spring.SecurityUserHolder;
import uk.ac.nesc.idsd.service.AuditService;
import uk.ac.nesc.idsd.service.DsdIdentifierService;
import uk.ac.nesc.idsd.service.ParameterService;
import uk.ac.nesc.idsd.service.UserService;
import uk.ac.nesc.idsd.service.impl.UserServiceImpl;

import java.util.List;

public class PatientAction extends AbstractAction {
    private static final long serialVersionUID = -3054764114486823194L;

    
    @Autowired
    private UserService userService;
    
   @Autowired
    private DsdIdentifierService dsdIdentifierService;
    @Autowired
    private ParameterService parameterService;
    @Autowired
    private AuditService auditService;

    private String u;
    private PatientUser patientUser;
    private String password;
    private String repassword;
    private DsdIdentifier dsdIdentifier;
    private List<Parameter> parameters;
    private String mode;
    private CentreInfo centreInfo;
    
  

    public String verifyPatientUser() {
        try {
            patientUser = userService.getPatientUser(u);
        } catch (Exception e) {
            log.error(ErrorCodes.PATIENT_USER_RETRIEVAL_ERROR.getMessage(), e);
            this.addActionError(ErrorCodes.PATIENT_USER_RETRIEVAL_ERROR.getMessage());
            return ERROR;
        }
        if (null == patientUser) {
            log.error(ErrorCodes.PATIENT_ACCOUNT_NOT_EXIST.getMessage());
            this.addActionError(ErrorCodes.PATIENT_ACCOUNT_NOT_EXIST.getMessage());
            return ERROR;
        }
        log.info(patientUser);
        return SUCCESS;
    }

    
   // degug this method 
    public String activatePatientUser() {
        try {
            patientUser = userService.getPatientUser(u);
        } catch (Exception e) {
            log.error(ErrorCodes.PATIENT_USER_RETRIEVAL_ERROR.getMessage(), e);
            this.addActionError(ErrorCodes.PATIENT_USER_RETRIEVAL_ERROR.getMessage());
            return ERROR;
        }
        if (null == patientUser) {
            log.error(ErrorCodes.PATIENT_ACCOUNT_NOT_EXIST.getMessage());
            this.addActionError(ErrorCodes.PATIENT_ACCOUNT_NOT_EXIST.getMessage());
            return ERROR;
        } else {
            patientUser = userService.updatePatientUser(patientUser, password);
        }
        return SUCCESS;
    }

 
    
   
    
    @PreAuthorize("hasAnyRole('ROLE_PATIENT')")
    public String viewPatientRecord() {
        log.info("in viewPatientRecord");
        if (StringUtils.isEmpty(u)) {
            try {
                patientUser = SecurityUserHolder.getCurrentPatientUser();
                log.info("Got patient from session");
            } catch (UserException e) {
                log.error(ErrorCodes.PATIENT_SESSION_EXPIRED.getMessage(), e);
                this.addActionError(ErrorCodes.PATIENT_SESSION_EXPIRED.getMessage());
            }
            if (null == patientUser) {
                log.error(ErrorCodes.PATIENT_ACCOUNT_NOT_EXIST.getMessage());
                this.addActionError(ErrorCodes.PATIENT_ACCOUNT_NOT_EXIST.getMessage());
                return ERROR;
            }
        } else {
            try {
                patientUser = userService.getPatientUser(u);
            } catch (Exception e) {
                log.error(ErrorCodes.PATIENT_USER_RETRIEVAL_ERROR.getMessage(), e);
                this.addActionError(ErrorCodes.PATIENT_USER_RETRIEVAL_ERROR.getMessage());
                return ERROR;
            }
        }

        if (null == patientUser) {
            log.error(ErrorCodes.PATIENT_ACCOUNT_NOT_EXIST.getMessage());
            this.addActionError(ErrorCodes.PATIENT_ACCOUNT_NOT_EXIST.getMessage());
            return ERROR;
        }
//        if (!Authorization.isPatientAccess(patientUser)) {
//            log.error(ErrorCodes.NO_PATIENT_ACCESS_ROLE_IS_GIVEN.getMessage());
//            this.addActionError(ErrorCodes.NO_PATIENT_ACCESS_ROLE_IS_GIVEN.getMessage());
//            return ERROR;
//        }
        if (null == patientUser.getDsdIdentifierId()) {
            log.error(ErrorCodes.PATIENT_ACCOUNT_DOES_NOT_LINK_TO_RECORD.getMessage());
            this.addActionError(ErrorCodes.PATIENT_ACCOUNT_DOES_NOT_LINK_TO_RECORD.getMessage());
            return ERROR;
        }
        dsdIdentifier = this.dsdIdentifierService.getById(patientUser.getDsdIdentifierId());
        if (dsdIdentifier == null) {
            log.error("Patient user " + patientUser.getUsername() + " cannot find its linked record with ID: " + patientUser.getDsdIdentifierId());
            this.addActionError(ErrorCodes.NO_RECORD_FOUND.getMessage());
            return ERROR;
        }
        try {
            this.parameters = this.parameterService.getParametersForPatientUser();
        } catch (Exception e) {
            this.addActionError(ErrorCodes.RECORD_LOOKUP_ERROR.getMessage() + this.dsdIdentifier.getRegisterId());
            log.error("Error when viewing registerId: " + this.dsdIdentifier.getRegisterId(), e);
        }
        if (null == mode) {
            mode = "idsd";
        }
        this.auditService.logPatientRead(this.dsdIdentifier);
        centreInfo = loadCentreInfo(dsdIdentifier.getCentre());
        return SUCCESS;
    }

    private CentreInfo loadCentreInfo(Centre centre) {
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

        return centreInfo;
    }

    public void validateActivatePatientUser() {
        log.info("inside validate");
        if (StringUtils.isEmpty(password) || StringUtils.isEmpty(repassword)) {
            this.addActionError("Password cannot be empty");
        } else if (!password.equals(repassword)) {
            this.addActionError("Password and re-type passwords are different!");
        } else if (!password.matches(Constants.PASSWORD_REG_EX)) {
            this.addActionError("Your password must match the following criteria: " +
                    "\n1. A digit must occur at least once" +
                    "\n2. A lower case letter must occur at least once\n" +
                    "\n3. An upper case letter must occur at least once\n" +
                    "\n4. A special character must occur at least once\n" +
                    "\n5. No whitespace allowed in the entire string\n" +
                    "\n6. at least eight characters");
        }
    }

    public String getU() {
        return u;
    }

    public void setU(String u) {
        this.u = u;
    }

    public void setPatientUser(PatientUser patientUser) {
        this.patientUser = patientUser;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepassword() {
        return repassword;
    }

    public void setRepassword(String repassword) {
        this.repassword = repassword;
    }

    public DsdIdentifier getDsdIdentifier() {
        return dsdIdentifier;
    }

    public void setDsdIdentifier(DsdIdentifier dsdIdentifier) {
        this.dsdIdentifier = dsdIdentifier;
    }

    public List<Parameter> getParameters() {
        return parameters;
    }

    public void setParameters(List<Parameter> parameters) {
        this.parameters = parameters;
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
}
