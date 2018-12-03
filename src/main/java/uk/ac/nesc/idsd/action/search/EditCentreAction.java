package uk.ac.nesc.idsd.action.search;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import uk.ac.nesc.idsd.action.AbstractAction;
import uk.ac.nesc.idsd.exception.ErrorCodes;
import uk.ac.nesc.idsd.model.Centre;
import uk.ac.nesc.idsd.model.PortalUser;
import uk.ac.nesc.idsd.service.CentreService;
import uk.ac.nesc.idsd.service.UserService;
import uk.ac.nesc.idsd.service.exception.ServiceException;
import uk.ac.nesc.idsd.util.Utility;

public class EditCentreAction extends AbstractAction {
    private static final long serialVersionUID = 4542293404653115521L;

    @Autowired
    private UserService userService;
    @Autowired
    private CentreService centreService;

    private Centre centre;
    private PortalUser portalUser;
    private Long registerId;

    public void prepare() {
        portalUser = retrievePortalUser();
    }

    @PreAuthorize("hasAnyRole('ROLE_RESEARCHER','ROLE_CONTRIBUTOR','ROLE_AUDITOR','ROLE_LOCAL_CONTRIBUTOR')")
    public String viewCentre() {
        try {
            this.centre = this.centreService.getCentreByCentreName(portalUser.getCentre());
        } catch (Exception e) {
            this.addActionError("Your user profile has a centre name that cannot be matched to any registered centres in the registry. " +
                    "Your profile has centre name of " + portalUser.getCentre());
            log.error("Error looking up centre by name: " + portalUser.getCentre(), e);
            return INPUT;
        }
        return SUCCESS;
    }

    @PreAuthorize("hasAnyRole('ROLE_RESEARCHER','ROLE_CONTRIBUTOR','ROLE_AUDITOR','ROLE_LOCAL_CONTRIBUTOR')")
    public String saveCentre() {
        try {
            centreService.updateAdditionalCentreInfo(centre);
            userService.updatePortalUserProfileForCentreProfile(portalUser);
        } catch (Exception e) {
            this.addActionError("Error updating centre " + portalUser.getCentre());
            log.error("Error updating centre " + portalUser.getCentre(), e);
            return INPUT;
        }
        return SUCCESS;
    }

    private PortalUser retrievePortalUser() {
        PortalUser portalUser = null;
        try {
            portalUser = userService.getPortalUserByUsername(Utility.getLoginUserName());
        } catch (ServiceException e) {
            log.error("Error when loading user from username: " + Utility.getLoginUserName(), e);
            this.addActionError(ErrorCodes.USER_LOOKUP_ERROR.getMessage());
        }
        return portalUser;
    }

    public Centre getCentre() {
        return centre;
    }

    public void setCentre(Centre centre) {
        this.centre = centre;
    }

    public void setPortalUser(PortalUser portalUser) {
        this.portalUser = portalUser;
    }

    public PortalUser getPortalUser() {
        return portalUser;
    }

    public Long getRegisterId() {
        return registerId;
    }

    public void setRegisterId(Long registerId) {
        this.registerId = registerId;
    }
}
