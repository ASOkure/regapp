package uk.ac.nesc.idsd.action.update;

import org.springframework.security.access.prepost.PreAuthorize;
import uk.ac.nesc.idsd.model.Country;
import uk.ac.nesc.idsd.model.DsdGeneScreened;
import uk.ac.nesc.idsd.model.DsdIdentifier;
import uk.ac.nesc.idsd.model.PortalUser;
import uk.ac.nesc.idsd.model.Section;
import uk.ac.nesc.idsd.security.Consent;
import uk.ac.nesc.idsd.security.Ownership;
import uk.ac.nesc.idsd.service.UserService;
import uk.ac.nesc.idsd.service.exception.ServiceException;
import uk.ac.nesc.idsd.util.Utility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class EditDsdViewAction extends AbstractEditAction {
    private static final long serialVersionUID = 1066059794091512696L;

    private List<Section> dsdIdentifierSections = new ArrayList<Section>();
    private List<String> checklist = new ArrayList<String>();
    private Map<String, String> consentLevelMap;
    private Integer consent;
    private Boolean sampleConsent;

    @Override
    public void prepare() {
        this.leadersJS = this.userService.getCentreLeaderJsString();
        this.countries = this.parameterService.getCountriesForCurrentUser();
        this.consentLevelMap = Utility.getConsentLevels();
        this.sections = this.parameterService.getCoreAndDiagnosisSectionsForUpdateView();
        log.debug("Create DSD Identifier prepare()");
        initialiseDsdIdentifierInstance();
    }

    @PreAuthorize("hasAnyRole('ROLE_CONTRIBUTOR','ROLE_LOCAL_CONTRIBUTOR')")
    public String editDsdRecordView() {
        PortalUser portalUser;
        try {
            portalUser = userService.getPortalUserByUsername(Utility.getLoginUserName());
        } catch (ServiceException e) {
            log.error("Error while retrieving portalUser of current logged in user: " + Utility.getLoginUserName(), e);
            this.addActionError(e.getMessage());
            return INPUT;
        }
        if (portalUser == null) {
            return EXPIRE;
        }

        if (this.registerId == null) {
            this.addActionError("No record Id is given!");
            return INPUT;
        }

        DsdIdentifier d = this.dsdIdentifierService.getById(this.registerId);
        if (d == null) {
            this.addActionError("The record you are querying does NOT exist!");
            return INPUT;
        }

        // TO-DO this relates to a rule - whether users can upload records not
        // belonging to their centres.
        // if no, simple, only check Consent will be OK.
        // if yes, problem, we need to check both. In case user uploads a record
        // that does not belong to their centre, but he is the owner.
        if (!Consent.check(d, portalUser)) {
            return CONSENT;
        }
        if (!Ownership.isEditable(d, portalUser)) {
            return OWNER;
        }

        this.dsdIdentifier = d;
        try {
            Utility.setUpdateSessionDsdObject(this.dsdIdentifier);
            if (this.registerId == null && this.dsdIdentifier != null) {
                this.registerId = this.dsdIdentifier.getRegisterId();
            }
            Utility.setUpdateSessionRegisterId(this.registerId);
        } catch (ServiceException e) {
            this.addActionError("Error when constructing view for editing record with registerId: "
                    + this.registerId);
            log.error(
                    "Error when constructing view for editing record with registerId: "
                            + this.registerId, e);
        }

        try {
            if (null != this.dsdIdentifier.getAssocMalforms()
                    && !this.dsdIdentifier.getAssocMalforms().isEmpty()) {
                String[] checklistStrings = this.dsdIdentifier
                        .getAssocMalforms().trim().split(", ");
                this.checklist = Arrays.asList(checklistStrings);
            }
        } catch (Exception e) {
            this.addActionError("Error when parsing AssocMalformations. ");
            log.error(
                    "Error occurred while splitting AssocMalformations into String Array. ",
                    e);
        }
        /**
         * VERY IMPORTANT TO AVOID LAZY LOADING IN THIS CASE
         */
        // must use these lines to void lazy loading error in the following
        // pages
        if (this.dsdIdentifier.getDsdAssessments() != null) {
            this.dsdIdentifier.getDsdAssessments().size();// fetch all
        }
        // dsdAssessments
        if (this.dsdIdentifier.getDsdGeneAnalysis() != null
                && this.dsdIdentifier.getDsdGeneAnalysis()
                .getDsdGeneScreeneds() != null) {
            this.dsdIdentifier.getDsdGeneAnalysis().getDsdGeneScreeneds()
                    .size(); // fetch all gene screened
            for (DsdGeneScreened dgs : this.dsdIdentifier.getDsdGeneAnalysis()
                    .getDsdGeneScreeneds()) {
                log.info(dgs.getGene().getGeneName());// fetch from gene tabel
            }
        }

        // initialize consent level and sample consent flag
        this.consent = this.dsdIdentifier.getConsent();
        this.sampleConsent = this.dsdIdentifier.getSampleConsent();
        return SUCCESS;
    }

    public List<Section> getDsdIdentifierSections() {
        return dsdIdentifierSections;
    }

    public void setDsdIdentifierSections(List<Section> dsdIdentifierSections) {
        this.dsdIdentifierSections = dsdIdentifierSections;
    }

    public Long getRegisterId() {
        return registerId;
    }

    public void setRegisterId(Long registerId) {
        this.registerId = registerId;
    }

    public List<String> getChecklist() {
        return checklist;
    }

    public void setChecklist(List<String> checklist) {
        this.checklist = checklist;
    }

    /**
     * @return the leadersJS
     */
    public String getLeadersJS() {
        return leadersJS;
    }

    /**
     * @param leadersJS the leadersJS to set
     */
    public void setLeadersJS(String leadersJS) {
        this.leadersJS = leadersJS;
    }

    /**
     * @param userService the userService to set
     */
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }

    public Map<String, String> getConsentLevelMap() {
        return consentLevelMap;
    }

    public void setConsentLevelMap(Map<String, String> consentLevelMap) {
        this.consentLevelMap = consentLevelMap;
    }

    public Integer getConsent() {
        return consent;
    }

    public void setConsent(Integer consent) {
        this.consent = consent;
    }

    public Boolean getSampleConsent() {
        return sampleConsent;
    }

    public void setSampleConsent(Boolean sampleConsent) {
        this.sampleConsent = sampleConsent;
    }
}
