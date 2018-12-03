package uk.ac.nesc.idsd.action.user;

import org.springframework.beans.factory.annotation.Autowired;
import uk.ac.nesc.idsd.action.AbstractAction;
import uk.ac.nesc.idsd.model.PortalUser;
import uk.ac.nesc.idsd.service.UserService;
import uk.ac.nesc.idsd.service.exception.ServiceException;
import uk.ac.nesc.idsd.util.Utility;

import java.util.List;

public class EditUserProfileAction extends AbstractAction {
    private static final long serialVersionUID = 1L;

    @Autowired
    private UserService userService;
    private PortalUser portalUser;
    private String password;
    private List<String> secondaryRoles;
    private List<String> interests;
    private Boolean searchable;

    public String viewProfile() {
        try {
            this.portalUser = this.userService.getCurrentSessionUser();
        } catch (ServiceException e) {
            log.error("Error while retrieving current logged in user from session", e);
            this.addActionError(e.getMessage());
            return INPUT;
        }
        this.secondaryRoles = Utility.splitStringToList(this.portalUser.getSecondaryRoles());
        this.interests = Utility.splitStringToList(this.portalUser.getInterest());
        this.searchable = this.portalUser.getSearchable();
        return SUCCESS;
    }

    public String editProfile() {
        log.info("we are in edit user profile method!!!");
        if (null == this.portalUser) {
            return ERROR;
        }
        if (null == this.password) {
            this.addActionError("Please enter your password when editing your user account profile.");
            return INPUT;
        }
        if (!this.userService.validatePortalUser(portalUser.getUsername(), this.password)) {
            this.addActionError("Password is not failure when trying to save your new user account profile. Save operation aborted for security reasons ");
            return INPUT;
        }
        try {
            this.portalUser = this.userService.updatePortalUser(this.portalUser, this.password);
        } catch (ServiceException e) {
            log.error("Error while updating user profile for username: " + this.portalUser.getUsername(), e);
            this.addActionError(e.getMessage());
        }
        return SUCCESS;
    }

    public PortalUser getPortalUser() {
        return portalUser;
    }

    public void setPortalUser(PortalUser portalUser) {
        this.portalUser = portalUser;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getSecondaryRoles() {
        return secondaryRoles;
    }

    public void setSecondaryRoles(List<String> secondaryRoles) {
        this.secondaryRoles = secondaryRoles;
    }

    public List<String> getInterests() {
        return interests;
    }

    public void setInterests(List<String> interests) {
        this.interests = interests;
    }

    public Boolean getSearchable() {
        return searchable;
    }

    public void setSearchable(Boolean searchable) {
        this.searchable = searchable;
    }

}
