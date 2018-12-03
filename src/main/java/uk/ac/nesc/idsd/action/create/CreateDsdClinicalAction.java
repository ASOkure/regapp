package uk.ac.nesc.idsd.action.create;

import org.springframework.security.access.prepost.PreAuthorize;
import uk.ac.nesc.idsd.util.Utility;

import java.util.ArrayList;
import java.util.List;

public class CreateDsdClinicalAction extends AbstractCreateAction {
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
    public String createDsdDiagnosisView() {
        if (null != this.dsdIdentifier && null != this.dsdIdentifier.getSampleConsent()) {
            this.sampleConsent = this.dsdIdentifier.getSampleConsent();
        }
        return SUCCESS;
    }

    /**
     * create dsdIdentifier object, and store in session.
     * prepare display parameters for assessments page
     */
    @PreAuthorize("hasAnyRole('ROLE_CONTRIBUTOR','ROLE_LOCAL_CONTRIBUTOR')")
    public String createDsdDiagnosis() {
        if (null != this.dsdIdentifier) {
            this.dsdIdentifier.setSampleConsent(this.sampleConsent);
            this.dsdIdentifier = this.dsdIdentifierService.saveClinical(this.registerId, this.dsdIdentifier);
        }
        return afterSave();
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
