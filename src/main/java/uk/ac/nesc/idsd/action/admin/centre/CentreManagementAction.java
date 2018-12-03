package uk.ac.nesc.idsd.action.admin.centre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import uk.ac.nesc.idsd.action.AbstractAction;
import uk.ac.nesc.idsd.model.Centre;
import uk.ac.nesc.idsd.model.Country;
import uk.ac.nesc.idsd.model.PortalUser;
import uk.ac.nesc.idsd.service.CentreService;
import uk.ac.nesc.idsd.service.UserService;

import java.util.ArrayList;
import java.util.List;

public class CentreManagementAction extends AbstractAction {
    private static final long serialVersionUID = -7159115571427609943L;

    @Autowired
    private CentreService centreService;
    @Autowired
    private UserService userService;

    private List<Centre> centres = new ArrayList<Centre>(0);
    private List<Country> countries = new ArrayList<Country>(0);

    private Long countryId;
    private Long centreId;

    private String countryName;
    private String centreName;

    private String currentLeaderUsername;

    private Country country;
    private Centre centre;

    private String username;
    private List<PortalUser> portalUsers;

    private String message;

    public void prepare() {
        this.centres = this.centreService.getAllCentres();
        this.countries = this.centreService.getAllCountries();
    }

    @PreAuthorize("hasAnyRole('ROLE_SUPERVISOR')")
    public String centreManagementView() {
        return SUCCESS;
    }

    @PreAuthorize("hasAnyRole('ROLE_SUPERVISOR')")
    public String createCountry() {
        try {
            this.centreService.saveCountry(countryName);
            this.message = "Country " + this.countryName + " was saved successfully";
        } catch (Exception e) {
            this.addActionError("Exception occurred when creating country: " + this.countryName);
            log.error("Exception occurred when creating country: " + this.countryName, e);
            return INPUT;
        }

        return SUCCESS;
    }

    @PreAuthorize("hasAnyRole('ROLE_SUPERVISOR')")
    public String editCountry() {
        try {
            this.centreService.updateCountry(countryId, countryName);
            this.message = "Country name has been updated successfully to " + this.countryName;
        } catch (Exception e) {
            this.addActionError("Error occurred when updating country name to " + this.countryName);
            log.error("Error occurred when updating country name to " + this.countryName, e);
            return INPUT;
        }
        return SUCCESS;
    }

    @PreAuthorize("hasAnyRole('ROLE_SUPERVISOR')")
    public String deleteCountry() {
        try {
            this.centreService.deleteCountry(this.countryId);
            this.message = "Country \"" + this.countryName + "\" has been successfully deleted!";
        } catch (Exception e) {
            this.addActionError("Error when deleting country " + this.countryName);
            log.error("Error occurred when deleting country " + this.countryName, e);
            return INPUT;
        }
        return SUCCESS;
    }

    @PreAuthorize("hasAnyRole('ROLE_SUPERVISOR')")
    public String createCentre() {
        try {
            this.centreService.saveCentre(countryId, centreName);
            this.message = "Centre " + this.centreName + " was saved successfully";
        } catch (Exception e) {
            this.addActionError("Error when creating centreName: " + centreName);
            log.error("Error creating centreName: " + centreName + ", for countryId = " + countryId, e);
            return INPUT;
        }
        return SUCCESS;
    }

    @PreAuthorize("hasAnyRole('ROLE_SUPERVISOR')")
    public String editCentreView() {
        try {
            this.centre = this.centreService.getCentreById(centreId);
        } catch (Exception e) {
            this.addActionError("Error looking up centre by ID" + centreId);
            log.error("Error looking up centre by ID: " + centreId, e);
            return INPUT;
        }
        return SUCCESS;
    }

    @PreAuthorize("hasAnyRole('ROLE_SUPERVISOR')")
    public String editCentre() {
        try {
            centre.setCentreId(centreId);
            this.centreService.updateCentre(centre);
            this.message = "Centre name has been updated successfully to " + this.centreName;
        } catch (Exception e) {
            this.addActionError("Error updating centre " + centreName);
            log.error("Error updating centre " + centreName, e);
            return INPUT;
        }
        return SUCCESS;
    }

    @PreAuthorize("hasAnyRole('ROLE_SUPERVISOR')")
    public String deleteCentre() {
        try {
            this.centreService.deleteCentre(this.centreId);
            this.message = "Centre \"" + this.centreName + "\" has been successfully deleted!";
        } catch (Exception e) {
            this.addActionError("Error when deleting centre " + centreName);
            log.error("Error when deleting centreId = " + centreId + ", centreName = " + centreName, e);
            return INPUT;
        }
        return SUCCESS;
    }

    public void prepareEditLeader() {
        this.portalUsers = this.userService.getAllPortalUsers();
    }

    @PreAuthorize("hasAnyRole('ROLE_SUPERVISOR')")
    public String viewLeader() {
        try {
            this.currentLeaderUsername = this.centreService.getCentreLeaderUsername(this.centreId);
        } catch (Exception e) {
            this.addActionError("Error when viewing centre leader for centre: " + this.centreName);
            log.error("Error when viewing centre leader for centreId = " + this.centreId + ", centreName = " + this.centreName, e);
            return INPUT;
        }
        this.portalUsers = this.userService.getPortalUserByCentre(this.centreName);
        return SUCCESS;
    }

    @PreAuthorize("hasAnyRole('ROLE_SUPERVISOR')")
    public String editLeader() {
        try {
            String name = this.centreService.saveCentreLeader(this.centreId, this.username);
            this.message = "Leader of '" + this.centreName + "' has been changed to '" + name + "'!";
        } catch (Exception e) {
            this.addActionError("Error when editing leader for centre: " + this.centreName);
            log.error("Error when editing leader for centre: " + this.centreName + ", to be " + this.username, e);
            return INPUT;
        }
        return SUCCESS;
    }

    public List<Centre> getCentres() {
        return centres;
    }

    public void setCentres(List<Centre> centres) {
        this.centres = centres;
    }

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public Long getCentreId() {
        return centreId;
    }

    public void setCentreId(Long centreId) {
        this.centreId = centreId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCentreName() {
        return centreName;
    }

    public void setCentreName(String centreName) {
        this.centreName = centreName;
    }

    public String getCurrentLeaderUsername() {
        return currentLeaderUsername;
    }

    public void setCurrentLeaderUsername(String currentLeaderUsername) {
        this.currentLeaderUsername = currentLeaderUsername;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Centre getCentre() {
        return centre;
    }

    public void setCentre(Centre centre) {
        this.centre = centre;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<PortalUser> getPortalUsers() {
        return portalUsers;
    }

    public void setPortalUsers(List<PortalUser> portalUsers) {
        this.portalUsers = portalUsers;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }

}
