package uk.ac.nesc.idsd.action.update;

import org.springframework.security.access.prepost.PreAuthorize;
import uk.ac.nesc.idsd.service.exception.ServiceException;
import uk.ac.nesc.idsd.util.Utility;

import java.util.ArrayList;
import java.util.List;

public class EditDsdClinicalAction extends AbstractEditAction {
    private static final long serialVersionUID = 3364034021765942123L;

    private List<String> assocMalformsList = new ArrayList<String>();
    private Boolean sampleConsent;

    @Override
    public void prepare() {
        this.sections = this.parameterService.getCreateDiagnosisSections();
        initialiseDsdIdentifierInstance();
        assocMalformsList = Utility.csvStringToList(this.dsdIdentifier.getAssocMalforms());
    }

    @PreAuthorize("hasAnyRole('ROLE_CONTRIBUTOR','ROLE_LOCAL_CONTRIBUTOR')")
    public String editClinicalHistoryAndBiomaterialsView() {
        if (null != this.dsdIdentifier && null != this.dsdIdentifier.getSampleConsent()) {
            this.sampleConsent = this.dsdIdentifier.getSampleConsent();
        }
        return SUCCESS;
    }

    @PreAuthorize("hasAnyRole('ROLE_CONTRIBUTOR','ROLE_LOCAL_CONTRIBUTOR')")
    public String editClinicalHistoryAndBiomaterials() {
        String returnStatus = checkConsentAndOwner();

        if (null != this.dsdIdentifier) {
            try {
                this.dsdIdentifier.setSampleConsent(this.sampleConsent);
                this.dsdIdentifier = this.dsdIdentifierService.saveClinical(this.registerId, this.dsdIdentifier);
                Utility.setUpdateSessionDsdObject(this.dsdIdentifier);
            } catch (ServiceException e) {
                this.addActionError("User exception when editing diagnosis section for registerId: " + this.registerId);
                log.error("User exception when editing diagnosis section for registerId: " + this.registerId, e);
                return INPUT;
            }
        } else {
            this.addActionError("Error when editing diagnosis section for registerId: " + this.registerId);
            log.error("Error when editing diagnosis section for registerId: " + this.registerId + ", this.dsdIdentifier is null? " + (this.dsdIdentifier == null));
        }
        return afterSave(returnStatus);
    }

    public List<String> getAssocMalformsList() {
        return assocMalformsList;
    }

    public void setAssocMalformsList(List<String> assocMalformsList) {
        this.assocMalformsList = assocMalformsList;
    }

    public Boolean getSampleConsent() {
        return sampleConsent;
    }

    public void setSampleConsent(Boolean sampleConsent) {
        this.sampleConsent = sampleConsent;
    }

}
