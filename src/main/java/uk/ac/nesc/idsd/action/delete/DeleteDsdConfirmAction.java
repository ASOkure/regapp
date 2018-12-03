package uk.ac.nesc.idsd.action.delete;

import org.springframework.security.access.prepost.PreAuthorize;
import uk.ac.nesc.idsd.action.AbstractAction;
import uk.ac.nesc.idsd.model.DsdIdentifier;
import uk.ac.nesc.idsd.model.PortalUser;
import uk.ac.nesc.idsd.security.Consent;
import uk.ac.nesc.idsd.security.Ownership;
import uk.ac.nesc.idsd.service.DsdIdentifierService;
import uk.ac.nesc.idsd.service.UserService;
import uk.ac.nesc.idsd.service.exception.ServiceException;
import uk.ac.nesc.idsd.util.Utility;

public class DeleteDsdConfirmAction extends AbstractAction {
    private static final long serialVersionUID = -4647878142231447792L;

    private DsdIdentifierService dsdIdentifierService;
    private UserService userService;

    private Long registerId;

    @PreAuthorize("hasAnyRole('ROLE_CONTRIBUTOR','ROLE_LOCAL_CONTRIBUTOR')")
    public String deleteConfirm() {
        PortalUser user;
        try {
            user = userService.getPortalUserByUsername(Utility.getLoginUserName());
        } catch (ServiceException e) {
            log.error("Error while retrieving current logged in user", e);
            this.addActionError(e.getMessage());
            return INPUT;
        }

        if (this.registerId == null) {
            log.error("No record ID was given ");
            this.addActionError("No record ID was given!");
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
        //TO-DO change to delete confirmation audit.
        this.dsdIdentifierService.delete(d);
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

    public void setDsdIdentifierService(
            DsdIdentifierService dsdIdentifierService) {
        this.dsdIdentifierService = dsdIdentifierService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

}
