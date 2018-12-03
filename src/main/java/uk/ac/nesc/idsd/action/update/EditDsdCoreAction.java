package uk.ac.nesc.idsd.action.update;

import org.springframework.security.access.prepost.PreAuthorize;
import uk.ac.nesc.idsd.service.exception.ServiceException;
import uk.ac.nesc.idsd.util.Utility;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EditDsdCoreAction extends AbstractEditAction {
    private static final long serialVersionUID = 1795462987564410229L;

    private List<String> checklist = new ArrayList<String>();
    private Map<String, String> consentLevelMap;
    private Integer consent;

    @Override
    public void prepare() {
        this.leadersJS = this.userService.getCentreLeaderJsString();
        this.countries = this.parameterService.getCountriesForCurrentUser();
        this.consentLevelMap = Utility.getConsentLevels();
        this.sections = this.parameterService.getCoreAndDiagnosisSectionsForUpdateView();
        initialiseDsdIdentifierInstance();
    }

    @PreAuthorize("hasAnyRole('ROLE_CONTRIBUTOR','ROLE_LOCAL_CONTRIBUTOR')")
    public String editDsdRecordView() {
        String returnStatus = checkConsentAndOwner();
        if (!returnStatus.equalsIgnoreCase(SUCCESS)) {
            return returnStatus;
        }
        //prepare getAssocMalforms into a list of Strings for VIEW on JSP
        this.checklist = Utility.csvStringToList(this.dsdIdentifier.getAssocMalforms());
        //initialize consent level and sample consent flag
        this.consent = this.dsdIdentifier.getConsent();
        /**
         * VERY IMPORTANT TO AVOID LAZY LOADING IN THIS CASE
         */
        //must use these lines to void lazy loading error in the following pages
        if (this.dsdIdentifier.getDsdAssessments() != null) {
            this.dsdIdentifier.getDsdAssessments().size();//fetch all dsdAssessments
        }
        if (this.dsdIdentifier.getDsdGeneAnalysis() != null
                && this.dsdIdentifier.getDsdGeneAnalysis().getDsdGeneScreeneds() != null) {
            this.dsdIdentifier.getDsdGeneAnalysis().getDsdGeneScreeneds().size(); //fetch all gene screened
        }
        return returnStatus;
    }

    @PreAuthorize("hasAnyRole('ROLE_CONTRIBUTOR','ROLE_LOCAL_CONTRIBUTOR')")
    public String editRecordIdentifier() {
        String returnStatus = checkConsentAndOwner();
        log.info(this.dsdIdentifier);
        if (null != this.dsdIdentifier) {
            //get values from consent table, and set into dsdIdentifier
            this.dsdIdentifier.setConsent(this.consent);
            try {
                this.dsdIdentifier = this.dsdIdentifierService.saveCore(this.registerId, this.dsdIdentifier);
                Utility.setUpdateSessionDsdObject(this.dsdIdentifier);
            } catch (ServiceException e) {
                this.addActionError("User exception when editing record with registerId: " + this.registerId);
                log.error("User exception when editing record with registerId: " + this.registerId, e);
                return INPUT;
            }
        } else {
            log.error("this.dsdIdentifier is null ");
            this.addActionError(ERROR);
        }
        return afterSave(returnStatus);
    }

    public List<String> getChecklist() {
        return checklist;
    }

    public void setChecklist(List<String> checklist) {
        this.checklist = checklist;
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

}

	
