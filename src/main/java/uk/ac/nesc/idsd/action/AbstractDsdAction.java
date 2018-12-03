package uk.ac.nesc.idsd.action;

import org.springframework.beans.factory.annotation.Autowired;
import uk.ac.nesc.idsd.model.Country;
import uk.ac.nesc.idsd.model.DsdAssessment;
import uk.ac.nesc.idsd.model.DsdIdentifier;
import uk.ac.nesc.idsd.model.Section;
import uk.ac.nesc.idsd.service.DsdIdentifierService;
import uk.ac.nesc.idsd.service.ParameterService;
import uk.ac.nesc.idsd.service.UserService;
import uk.ac.nesc.idsd.service.exception.ServiceException;
import uk.ac.nesc.idsd.util.Utility;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class AbstractDsdAction extends AbstractAction {
    private static final long serialVersionUID = -6415500970180437871L;

    @Autowired
    protected ParameterService parameterService;
    @Autowired
    protected UserService userService;
    @Autowired
    protected DsdIdentifierService dsdIdentifierService;

    protected String mode;
    protected Long registerId;
    protected Long assessmentId;
    protected DsdIdentifier dsdIdentifier;
    protected DsdAssessment dsdAssessment;
    protected String leadersJS;
    protected List<Country> countries = new ArrayList<Country>(0);
    protected List<Section> sections = new ArrayList<Section>(0);
    protected String buttonName;

    public void prepare() {
        initialiseDsdIdentifierInstance();
    }

    private boolean isEmptyDsdIdentifier() {
        return (this.dsdIdentifier == null || this.dsdIdentifier.getRegisterId() == null);
    }

    protected void initialiseDsdIdentifierInstance() {
        try {
            if (this.registerId == null) {
                log.info("registerId is null");
                this.registerId = Utility.getUpdateSessionRegisterId();
            }
            log.info(this.registerId);
            if (this.registerId == null && !isEmptyDsdIdentifier()) {
                this.registerId = this.dsdIdentifier.getRegisterId();
            }
            if (this.registerId != null) {
                Utility.setUpdateSessionRegisterId(this.registerId);
                this.dsdIdentifier = this.dsdIdentifierService.getById(this.registerId);
                log.info(this.dsdIdentifier);
                // if assessmentId exists from URL, filter out that one from the dsdAssessment set.
                if (null != dsdIdentifier && null != dsdIdentifier.getDsdAssessments() && !dsdIdentifier.getDsdAssessments().isEmpty()) {
                    SortedSet<DsdAssessment> dsdAssessments = new TreeSet<DsdAssessment>();
                    for (DsdAssessment da : dsdIdentifier.getDsdAssessments()) {
                        if (!da.getAssessmentId().equals(this.assessmentId)) {
                            dsdAssessments.add(da);
                        } else {
                            this.dsdAssessment = da;
                        }
                    }
                    this.dsdIdentifier.setDsdAssessments(dsdAssessments);
                    Utility.setUpdateSessionDsdObject(this.dsdIdentifier);
                }
            }
        } catch (ServiceException e) {
            this.addActionError("Error when initialising dsd records for editing");
            log.error("user Exception occurred when initialising dsd records for editing", e);
        }
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public Long getRegisterId() {
        return registerId;
    }

    public void setRegisterId(Long registerId) {
        this.registerId = registerId;
    }

    public Long getAssessmentId() {
        return assessmentId;
    }

    public void setAssessmentId(Long assessmentId) {
        this.assessmentId = assessmentId;
    }

    public DsdIdentifier getDsdIdentifier() {
        return dsdIdentifier;
    }

    public void setDsdIdentifier(DsdIdentifier dsdIdentifier) {
        this.dsdIdentifier = dsdIdentifier;
    }

    public DsdAssessment getDsdAssessment() {
        return dsdAssessment;
    }

    public void setDsdAssessment(DsdAssessment dsdAssessment) {
        this.dsdAssessment = dsdAssessment;
    }

    public String getLeadersJS() {
        return leadersJS;
    }

    public void setLeadersJS(String leadersJS) {
        this.leadersJS = leadersJS;
    }

    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }

    public String getButtonName() {
        return buttonName;
    }

    public void setButtonName(String buttonName) {
        this.buttonName = buttonName;
    }
}
