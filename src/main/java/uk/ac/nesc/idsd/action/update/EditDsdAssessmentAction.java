package uk.ac.nesc.idsd.action.update;

import org.springframework.security.access.prepost.PreAuthorize;
import uk.ac.nesc.idsd.model.Section;
import uk.ac.nesc.idsd.service.exception.ServiceException;
import uk.ac.nesc.idsd.util.Utility;

import java.text.SimpleDateFormat;

public class EditDsdAssessmentAction extends AbstractEditAction {
    private static final long serialVersionUID = -2996600567890505899L;

    private Section dsdAssessmentSection;
    private boolean editAssessment = false;

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
    public String editAssessmentView() {
        return checkConsentAndOwner();
    }

    /**
     * add new DsdAssessment
     *
     * @return
     * @throws Exception
     */
    @PreAuthorize("hasAnyRole('ROLE_CONTRIBUTOR','ROLE_LOCAL_CONTRIBUTOR')")
    public String editAssessmentAdd() {
        String returnStatus = checkConsentAndOwner();
        this.dsdIdentifierService.saveAssessment(this.registerId, this.dsdAssessment);
        return returnStatus;
    }

    /**
     * edit existing DsdAssessment record
     *
     * @return
     * @throws Exception
     */
    @PreAuthorize("hasAnyRole('ROLE_CONTRIBUTOR','ROLE_LOCAL_CONTRIBUTOR')")
    public String editAssessmentEdit() {
        String returnStatus = checkConsentAndOwner();
        validateAssessmentId();
        try {
            log.info("editing assessmentId = " + this.assessmentId);
            this.dsdAssessment = this.dsdIdentifierService.getDsdAssessmentById(this.assessmentId);
        } catch (ServiceException e) {
            log.error("Error while retrieving dsdAssessment from the database in EditDsdAssessment.prepare() method.", e);
        }
        log.info("after re-initialize, the size of assessments = " + this.dsdIdentifier.getDsdAssessments().size());
        this.editAssessment = true;
        return returnStatus;
    }

    /**
     * delete existing DsdAssessment record
     *
     * @return
     * @throws Exception
     */
    @PreAuthorize("hasAnyRole('ROLE_CONTRIBUTOR','ROLE_LOCAL_CONTRIBUTOR')")
    public String editAssessmentDelete() {
        String returnStatus = checkConsentAndOwner();
        validateAssessmentId();
        try {
            this.dsdIdentifierService.deleteAssessment(this.assessmentId);
            this.dsdIdentifier = this.dsdIdentifierService.getById(this.registerId);
        } catch (ServiceException e) {
            log.error("Error while deleting assessment", e);
            this.addActionError(e.getMessage());
            return INPUT;
        }
        return returnStatus;
    }

    private void validateAssessmentId() {
        if (null == this.assessmentId) {
            this.addActionError("Assessment ID must be provided");
        }
    }

    /**
     * submit all DsdAssessment
     *
     * @return
     * @throws Exception
     */
    @PreAuthorize("hasAnyRole('ROLE_CONTRIBUTOR','ROLE_LOCAL_CONTRIBUTOR')")
    public String editAssessmentSubmit() {
        String returnStatus = checkConsentAndOwner();
        this.dsdIdentifierService.saveAssessment(this.registerId, this.dsdAssessment);
        this.dsdIdentifier = this.dsdIdentifierService.getById(this.registerId);
        return afterSave(returnStatus);
    }

    /**
     * validation
     */
    /**
     * Validation for addAssessment method must not null, date must not exist in
     * the list, date must not be earlier than Year of Birth
     */
    public void validateEditAssessmentAdd() {
        log.info("in the validateEditAssessmentAdd method");
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
                    log.info("Validating: " + this.dsdIdentifier.getDsdAssessments());
                    if (Utility.isDateExistInDsdAssessmentSet(this.dsdIdentifier.getDsdAssessments(), this.dsdAssessment)) {
                        this.addActionError("Assessment date already exist! Please check the assessment you are adding: " + Utility.convertDateToString(dsdAssessment.getAssessmentDate()));
                    }
                }
            }
        }
    }

    /**
     * Validation for submitAssessment method
     * must enter at least 2 assessments
     */
    @PreAuthorize("hasAnyRole('ROLE_CONTRIBUTOR','ROLE_LOCAL_CONTRIBUTOR')")
    public void validateEditAssessmentSubmit() {
        try {
            this.dsdIdentifier = Utility.getUpdateSessionDsdObject();
        } catch (ServiceException e) {
            this.addActionError("Error when fetching DSD record from session.");
            log.error("Error when fetching DSd record from session", e);
        }
        if (null == this.dsdIdentifier) {
            this.addActionError("The session object is expired after 30 minutes. Please login and try this again.");
        } else {
            log.debug("size of dsdAssessment = " + this.dsdIdentifier.getDsdAssessments() == null ? 0 : this.dsdIdentifier.getDsdAssessments().size());
            log.debug("dsdAssessment = " + this.dsdAssessment);
            if (Utility.isDuplicateDateExistInDsdAssessmentSet(this.dsdIdentifier.getDsdAssessments())) {
                this.addActionError("Duplicated assessment dates exist. Please check the assessment history and correct this error!");
            }
        }
    }

    public Section getDsdAssessmentSection() {
        return dsdAssessmentSection;
    }

    public void setDsdAssessmentSection(Section dsdAssessmentSection) {
        this.dsdAssessmentSection = dsdAssessmentSection;
    }

    public boolean isEditAssessment() {
        return editAssessment;
    }

    public void setEditAssessment(boolean editAssessment) {
        this.editAssessment = editAssessment;
    }

}
