package uk.ac.nesc.idsd.action.create;

import org.springframework.security.access.prepost.PreAuthorize;
import uk.ac.nesc.idsd.model.DsdIdentifier;
import uk.ac.nesc.idsd.model.Section;
import uk.ac.nesc.idsd.service.ParameterService;
import uk.ac.nesc.idsd.service.exception.ServiceException;
import uk.ac.nesc.idsd.util.Utility;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateAssessmentAction extends AbstractCreateAction {
    private static final long serialVersionUID = 8253568530311635597L;

    private Section dsdAssessmentSection;
    private Date d = null;

    @Override
    public void prepare() {
        try {
            this.dsdAssessmentSection = this.parameterService.getDsdAssessmentSection();
            initialiseDsdIdentifierInstance();
        } catch (Exception e) {
            log.error("Error when preparing createAssessmentAction", e);
            this.addActionError("Error loading record from database. ");
        }
    }

    @PreAuthorize("hasAnyRole('ROLE_CONTRIBUTOR','ROLE_LOCAL_CONTRIBUTOR')")
    public String assessmentView() {
        return SUCCESS;
    }

    @PreAuthorize("hasAnyRole('ROLE_CONTRIBUTOR','ROLE_LOCAL_CONTRIBUTOR')")
    public String addAssessment() {
        log.info("registerId - " + registerId + ", dsdAssessment = " + dsdAssessment);
        this.dsdIdentifierService.saveAssessment(this.registerId, this.dsdAssessment);
        return SUCCESS;
    }

    @PreAuthorize("hasAnyRole('ROLE_CONTRIBUTOR','ROLE_LOCAL_CONTRIBUTOR')")
    public String editAssessment() {
        validateAssessmentId();
        try {
            log.info("Editing assessmentId for new DSD record = " + this.assessmentId);
            this.dsdAssessment = this.dsdIdentifierService.getDsdAssessmentById(this.assessmentId);
        } catch (ServiceException e) {
            this.addActionError("Error when editing assessmentId: " + this.assessmentId);
            log.error("Error when editing assessmentId: " + this.assessmentId, e);
            return INPUT;
        }
        return SUCCESS;
    }

    private void validateAssessmentId() {
        if (null == this.assessmentId) {
            this.addActionError("Assessment ID must be provided");
        }
    }

    @PreAuthorize("hasAnyRole('ROLE_CONTRIBUTOR','ROLE_LOCAL_CONTRIBUTOR')")
    public String removeAssessment() {
        validateAssessmentId();
        try {
            this.dsdIdentifierService.deleteAssessment(this.assessmentId);
            this.dsdIdentifier = this.dsdIdentifierService.getById(this.registerId);
        } catch (ServiceException e) {
            log.error("Error while removing assessment id: " + this.assessmentId, e);
            this.addActionError(e.getMessage());
            return INPUT;
        }
        return SUCCESS;
    }

    /*
     * keep assessment set in the dsdIdentifier, and save dsdIdentifier to
     * session Add assessment to dsdIdentifier if it's not null and it doesn't
     * exist in dsdIdentifier.getDsdAssessment() set.
     */
    @PreAuthorize("hasAnyRole('ROLE_CONTRIBUTOR','ROLE_LOCAL_CONTRIBUTOR')")
    public String submitAssessment() {
        this.dsdIdentifierService.saveAssessment(this.registerId, this.dsdAssessment);
        this.dsdIdentifier = this.dsdIdentifierService.getById(this.registerId);
        return afterSave();
    }

    /**
     * Validation for addAssessment method must not null, date must not exist in
     * the list, date must not be earlier than Year of Birth
     */
    public void validateAddAssessment() {
        log.info("In validation method");
        if (null == this.dsdIdentifier || null == this.dsdAssessment) {
            this.addActionError("Error while saving assessments.");
        } else if (null == this.dsdAssessment.getAssessmentDate()) {
            this.addActionError("You cannot save an empty DSD Assessment record!");
        } else {
            SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
            if (this.dsdAssessment.getAssessmentDate() == null) {
                this.addActionError("Assessment Date cannot be empty");
            } else {
                int assessmentYear = Integer.parseInt(yearFormat.format(this.dsdAssessment.getAssessmentDate()));
                if (assessmentYear < this.dsdIdentifier.getYob()) {
                    this.addActionError("Assessment date cannot be earlier than Year of Birth (" + this.dsdIdentifier.getYob() + ") of this patient.");
                } else {
                    if (Utility.isDateExistInDsdAssessmentSet(this.dsdIdentifier.getDsdAssessments(), this.dsdAssessment)) {
                        this.addActionError("Assessment date already exist! Please check the assessment you are adding: " + Utility.convertDateToString(dsdAssessment.getAssessmentDate()));
                    }
                }
            }
        }
    }

    /**
     * --Validation for submitAssessment method must enter at least 2
     * assessments
     */
    public void validateSubmitAssessment() {
        if (null == this.dsdIdentifier || null == this.dsdIdentifier.getDsdAssessments()) {
            this.addActionError("Error loading record from database. ");
        } else {
            if (Utility.isDuplicateDateExistInDsdAssessmentSet(this.dsdIdentifier.getDsdAssessments())) {
                this.addActionError("Duplicated assessment dates exist. Please check the assessment history and correct this error!");
            }
        }
    }

    public DsdIdentifier getDsdIdentifier() {
        return dsdIdentifier;
    }

    public void setDsdIdentifier(DsdIdentifier dsdIdentifier) {
        this.dsdIdentifier = dsdIdentifier;
    }

    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }

    public Date getD() {
        return d;
    }

    public void setD(Date d) {
        this.d = d;
    }

    public Section getDsdAssessmentSection() {
        return dsdAssessmentSection;
    }

    public void setDsdAssessmentSection(Section dsdAssessmentSection) {
        this.dsdAssessmentSection = dsdAssessmentSection;
    }

}
