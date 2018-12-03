package uk.ac.nesc.idsd.action.update;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import uk.ac.nesc.idsd.action.AbstractDsdAction;
import uk.ac.nesc.idsd.model.PortalUser;
import uk.ac.nesc.idsd.security.Consent;
import uk.ac.nesc.idsd.security.Ownership;
import uk.ac.nesc.idsd.service.exception.ServiceException;
import uk.ac.nesc.idsd.util.Utility;

public abstract class AbstractEditAction extends AbstractDsdAction {
    protected static final Log log = LogFactory.getLog(AbstractEditAction.class);
    private static final long serialVersionUID = -6191752504674403166L;

    protected String afterSave(String returnStatus) {
        this.registerId = this.dsdIdentifier.getRegisterId();

        if ("save".equals(buttonName)) {
            return "save";
        } else if ("next".equals(buttonName)) {
            try {
                Utility.setUploadSessionDsdObject(this.dsdIdentifier);
                Utility.setUploadSessionRegisterId(this.registerId);
            } catch (ServiceException e) {
                log.error("User exception in afterSave method in AbstractEditAction", e);
            }
        }
        return returnStatus;
    }

    protected String checkConsentAndOwner() {
        PortalUser portalUser;
        try {
            portalUser = userService.getPortalUserByUsername(Utility.getLoginUserName());
        } catch (ServiceException e1) {
            this.addActionError("Error when looking for user information for updating DSD record.");
            log.error("Error when looking for user information for updating DSD record.", e1);
            return INPUT;
        }
        if (this.registerId == null) {
            log.error("No record Id is given!");
            this.addActionError("No record Id is given!");
            return INPUT;
        }

        if (this.dsdIdentifier == null) {
            log.error("The record you are querying does NOT exist!");
            this.addActionError("The record you are querying does NOT exist!");
            return ERROR;
        }
        //TO-DO this relates to a rule - whether users can upload records not belonging to their centres.
        //if no, simple, only check Consent will be OK.
        //if yes, problem, we need to check both. In case user uploads a record that does not belong to their centre, but he is the owner.
        if (log.isDebugEnabled()) {
            log.debug("Consent checking: record id = " + this.dsdIdentifier.getRegisterId() + ", consent level = " + this.dsdIdentifier.getConsent());
        }
        if (!Consent.check(this.dsdIdentifier, portalUser)) {
            log.error("User " + portalUser.getUsername() + " does not consent for this record");
            return CONSENT;
        }
        if (!Ownership.isEditable(this.dsdIdentifier, portalUser)) {
            log.error("User " + portalUser.getUsername() + " is not owner or leader for this record");
            return OWNER;
        }
        return SUCCESS;
    }

}
