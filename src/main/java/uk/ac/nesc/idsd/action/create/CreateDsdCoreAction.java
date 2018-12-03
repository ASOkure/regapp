package uk.ac.nesc.idsd.action.create;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import uk.ac.nesc.idsd.model.DsdIdentifier;
import uk.ac.nesc.idsd.service.exception.ServiceException;
import uk.ac.nesc.idsd.util.Utility;

import java.util.Map;

public class CreateDsdCoreAction extends AbstractCreateAction {
    private static final long serialVersionUID = 3364034021765942123L;
    private static final String ICAH = "icah";
    private final String EMPTY_CENTRE_LIST_ERROR = "Country and Centre on your user account does not match any existing country and centre set up on the registry. " +
            "Please go to \"My Account\" -> \"Edit My Profile\" and change your country and centre to existing ones. " +
            "If you believe your country and centre should be registered as new additions to the registry, " +
            "please contact Project Manager, Jillian Bryce at jillian.bryce@glasgow.ac.uk";
    private Map<String, String> consentLevelMap;
    private Integer consent;

    @Override
    public void prepare() {
        this.leadersJS = this.userService.getCentreLeaderJsString();
        this.countries = this.parameterService.getCountriesForCurrentUser();
        if (CollectionUtils.isEmpty(this.countries) && !getActionErrors().contains(EMPTY_CENTRE_LIST_ERROR)) {
            addActionError(EMPTY_CENTRE_LIST_ERROR);
        }
        this.consentLevelMap = Utility.getConsentLevels();
        this.sections = this.parameterService.getCoreAndDiagnosisSectionsForCreateView();
        initialiseDsdIdentifierInstance();
    }

    @PreAuthorize("hasAnyRole('ROLE_CONTRIBUTOR','ROLE_LOCAL_CONTRIBUTOR')")
    public String createDsdIdentifierView() {
        //initialize consent level and sample consent flag
        //if user moves back to section 1 from section 2 and 3.
        if (null != this.dsdIdentifier) {
            if (null != this.dsdIdentifier.getConsent()) {
                this.consent = this.dsdIdentifier.getConsent();
            }
        } else {
            this.dsdIdentifier = new DsdIdentifier();
            if (null != mode && ICAH.equalsIgnoreCase(mode)
                    && (null == this.dsdIdentifier.getDisorderType() ||
                    (null != this.dsdIdentifier.getDisorderType() && this.dsdIdentifier.getDisorderType().isEmpty()))) {
                this.dsdIdentifier.setDisorderType("Congenital Adrenal Hyperplasia");
            }
        }
        return SUCCESS;
    }

    /**
     * create dsdIdentifier object, and store in session.
     * prepare display parameters for assessments page
     */
    @PreAuthorize("hasAnyRole('ROLE_CONTRIBUTOR','ROLE_LOCAL_CONTRIBUTOR')")
    public String createDsdIdentifier() {
        if (null != this.dsdIdentifier) {
            //get values from consent table, and set into dsdIdentifier
            log.info("   User selected consent level to be: " + this.consent);
            this.dsdIdentifier.setConsent(this.consent);
            log.info("Set dsdIdentifier's consent level to be: " + this.dsdIdentifier.getConsent());

            try {
                this.dsdIdentifier = this.dsdIdentifierService.saveCore(this.registerId, this.dsdIdentifier);
                if ("Congenital Adrenal Hyperplasia".equalsIgnoreCase(this.dsdIdentifier.getDisorderType())) {
                    log.info("setting icah mode");
                    this.mode = "icah";
                }
            } catch (ServiceException e) {
                this.addActionError("Error when saving record for registerId: " + this.registerId);
                log.error("Error when saving record for registerId: " + this.registerId + ", detail = " + this.dsdIdentifier, e);
                return INPUT;
            }
        } else {
            log.error("this.dsdIdentifier is null ");
            this.addActionError(ERROR);
        }
        return afterSave();
    }

    public void validateCreateDsdIdentifier() {
        if (StringUtils.isEmpty(this.dsdIdentifier.getCountryName())) {
            this.addFieldError("dsdIdentifier.countryName", "Country cannot be empty");
        }
        if (StringUtils.isEmpty(this.dsdIdentifier.getCentreName())) {
            this.addFieldError("dsdIdentifier.centreName", "Centre cannot be empty");
        }
        if (this.dsdIdentifier.getYob() == null || this.dsdIdentifier.getYob() == 0) {
            this.addFieldError("dsdIdentifier.yob", "Year of Birth cannot be empty");
        }
        if (StringUtils.isEmpty(this.dsdIdentifier.getSexAssigned())) {
            this.addFieldError("dsdIdentifier.sexAssigned", "Original Sex Assigned cannot be empty");
        }
        if (StringUtils.isEmpty(this.dsdIdentifier.getCurrentGender())) {
            this.addFieldError("dsdIdentifier.currentGender", "Current Gender cannot be empty");
        }
        if (StringUtils.isEmpty(this.dsdIdentifier.getKaryotype())) {
            this.addFieldError("dsdIdentifier.karyotype", "Karyotype cannot be empty");
        }
        if (StringUtils.isEmpty(this.dsdIdentifier.getDisorderType())) {
            this.addFieldError("dsdIdentifier.disorderType", "Disorder Type cannot be empty");
        }
        if (StringUtils.isEmpty(this.dsdIdentifier.getActualDiagnosis())) {
            this.addFieldError("dsdIdentifier.actualDiagnosis", "Actual Diagnosis cannot be empty");
        }
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
