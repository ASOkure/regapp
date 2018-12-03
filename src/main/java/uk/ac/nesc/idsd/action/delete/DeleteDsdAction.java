package uk.ac.nesc.idsd.action.delete;

import org.springframework.security.access.prepost.PreAuthorize;
import uk.ac.nesc.idsd.action.AbstractAction;
import uk.ac.nesc.idsd.model.DsdIdentifier;
import uk.ac.nesc.idsd.model.PortalUser;
import uk.ac.nesc.idsd.model.Section;
import uk.ac.nesc.idsd.security.Consent;
import uk.ac.nesc.idsd.security.Ownership;
import uk.ac.nesc.idsd.service.DsdIdentifierService;
import uk.ac.nesc.idsd.service.ParameterService;
import uk.ac.nesc.idsd.service.UserService;
import uk.ac.nesc.idsd.service.exception.ServiceException;
import uk.ac.nesc.idsd.util.Utility;

import java.util.ArrayList;
import java.util.List;

public class DeleteDsdAction extends AbstractAction {
    private static final long serialVersionUID = -602649629060260704L;

    private DsdIdentifierService dsdIdentifierService;
    private ParameterService parameterService;
    private UserService userService;

    private Long registerId;
    private DsdIdentifier dsdIdentifier;

    private List<Section> dsdIdentifierSections = new ArrayList<Section>();
    private Section dsdAssessmentSection;
    private Section dsdGeneAnalysisSection;
    private Section dsdCahSection;
    private String consentString;

    @PreAuthorize("hasAnyRole('ROLE_CONTRIBUTOR','ROLE_LOCAL_CONTRIBUTOR')")
    public String delete() {
        log.info("Here");
        log.info(this.registerId);
        log.info(this.mode);
        PortalUser user;
        //if not registerId is given, throw exception.
        if (this.registerId == null) {
            this.addActionError("No record Id is given!");
            return INPUT;
        }
        try {
            user = userService.getPortalUserByUsername(Utility.getLoginUserName());
        } catch (ServiceException e) {
            log.error("Deleting record, exception when looking for user and leader. ", e);
            this.addActionError(e.getMessage());
            return INPUT;
        }
        DsdIdentifier d = this.dsdIdentifierService.getById(this.registerId);
        if (d == null) {
            this.addActionError("The record you are querying does NOT exist!");
            return INPUT;
        }

        //TO-DO this relates to a rule - whether users can upload records not belonging to their centres.
        //if no, simple, only check Consent will be OK.
        //if yes, problem, we need to check both. In case user uploads a record that does not belong to their centre, but he is the owner.
        if (!Consent.check(d, user)) {
            return CONSENT;
        }
        if (Ownership.isUnableToDelete(d, user)) {
            return OWNER;
        }

        this.dsdIdentifier = d;

        //PREPARE DISPLAY PARAMETERS
        this.dsdIdentifierSections = this.parameterService.getViewDsdCoreSections();
        this.dsdAssessmentSection = this.parameterService.getDsdAssessmentSection();
        this.dsdGeneAnalysisSection = this.parameterService.getDsdGeneAnalysisSection();
        this.dsdCahSection = this.parameterService.getViewDsdCahSection();

        this.consentString = Utility.getConsentLevels().get(this.dsdIdentifier.getConsent().toString());
        return SUCCESS;
    }

    /**
     * getters & setters
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

    public void setDsdIdentifierService(
            DsdIdentifierService dsdIdentifierService) {
        this.dsdIdentifierService = dsdIdentifierService;
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

    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public Section getDsdCahSection() {
        return dsdCahSection;
    }

    public void setDsdCahSection(Section dsdCahSection) {
        this.dsdCahSection = dsdCahSection;
    }

}
