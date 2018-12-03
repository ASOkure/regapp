package uk.ac.nesc.idsd.action.user;

import org.springframework.beans.factory.annotation.Autowired;
import uk.ac.nesc.idsd.action.AbstractAction;
import uk.ac.nesc.idsd.model.Country;
import uk.ac.nesc.idsd.model.PortalUser;
import uk.ac.nesc.idsd.service.ParameterService;
import uk.ac.nesc.idsd.service.UserService;
import uk.ac.nesc.idsd.service.exception.ServiceException;
import uk.ac.nesc.idsd.util.Utility;

import java.util.List;

public class SearchUserAction extends AbstractAction {
    private static final long serialVersionUID = 1L;

    @Autowired
    private UserService userService;
    @Autowired
    private ParameterService parameterService;
    private PortalUser portalUser;
    private List<PortalUser> portalUsers;
    private String username;
    private List<Country> countries;

    public String searchView() {
        this.countries = this.parameterService.getCountriesForSearch();
        return SUCCESS;
    }

    public String searchUsers() {
        if (null == this.portalUser) {
            return ERROR;
        }
        this.portalUsers = this.userService.getPortalUsersByExample(Utility.clonePortalUser(this.portalUser), false);
        return SUCCESS;
    }

    public String userDetail() {
        if (null == this.username) {
            return ERROR;
        }
        try {
            this.portalUser = this.userService.getPortalUserByUsername(this.username);
        } catch (ServiceException e) {
            log.error("Error occurred when retrieving user details. Error message : " + e.getMessage(), e);
        }
        return SUCCESS;
    }

    public PortalUser getPortalUser() {
        return portalUser;
    }

    public void setPortalUser(PortalUser portalUser) {
        this.portalUser = portalUser;
    }

    public List<PortalUser> getPortalUsers() {
        return portalUsers;
    }

    public void setPortalUsers(List<PortalUser> portalUsers) {
        this.portalUsers = portalUsers;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }

}
