package uk.ac.nesc.idsd.action.admin.user;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import uk.ac.nesc.idsd.action.AbstractAction;
import uk.ac.nesc.idsd.model.Country;
import uk.ac.nesc.idsd.model.PortalUser;
import uk.ac.nesc.idsd.model.Role;
import uk.ac.nesc.idsd.model.UserRole;
import uk.ac.nesc.idsd.security.spring.SecurityUserHolder;
import uk.ac.nesc.idsd.service.CentreService;
import uk.ac.nesc.idsd.service.ParameterService;
import uk.ac.nesc.idsd.service.UserService;
import uk.ac.nesc.idsd.service.exception.ServiceException;
import uk.ac.nesc.idsd.util.Utility;
import uk.ac.nesc.idsd.util.email.EmailService;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserApprovalAction extends AbstractAction {
    private static final long serialVersionUID = 2469127473385078316L;

    @Autowired
    private UserService userService;
    @Autowired
    private ParameterService parameterService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private CentreService centreService;

    private List<PortalUser> portalUsers = new ArrayList<PortalUser>(0);
    private List<String> secondaryRoles;
    private List<String> interests;
    private Date validateFrom;
    private Date validateTo;
    private String username;
    private PortalUser portalUser;
    private List<Role> roles;//for display purpose only - used by checkbox list
    private Set<Long> existingRoles;
    private String roleIds;//to take role ids from JSP
    private List<UserRole> userRoles;
    private String newPassword;
    private List<Country> countries = new ArrayList<Country>(0);

    public void prepare() {
        try {
            this.roles = this.userService.getAllRolesForApprover();
            if (null != this.username) {
                this.portalUser = retrievePortalUserFromDatabase(this.username);//get the user admin is modifying
            }
            if (null != this.portalUser && null != this.portalUser.getUserRoles() && !this.portalUser.getUserRoles().isEmpty()) {
                this.existingRoles = getExistingRolesIds(this.portalUser.getRoles());
            }
            //this.countries = this.parameterService.getCountriesForSearch();
        } catch (Exception e) {
            this.addActionError("Error when preparing user approval action. ");
            log.error("exception occurred while preparing user approval pages", e);
        }
    }

    @PreAuthorize("hasAnyRole('ROLE_SUPERVISOR')")
    public String searchView() {
        this.countries = this.parameterService.getCountriesForSearch();
        return SUCCESS;
    }

    @PreAuthorize("hasAnyRole('ROLE_SUPERVISOR')")
    public String searchUser() {
        this.portalUsers = this.userService.getPortalUsersByExample(Utility.clonePortalUser(this.portalUser), true);
        return SUCCESS;
    }

    @PreAuthorize("hasAnyRole('ROLE_SUPERVISOR')")
    public String viewAllUsers() {
        this.portalUsers = this.userService.getPortalUsersWaitingForApproval();
        return SUCCESS;
    }

    @PreAuthorize("hasAnyRole('ROLE_SUPERVISOR')")
    public String pickUser() {
        if (null == this.username) {
            this.addActionError("You must pick a user to proceed.");
            return INPUT;
        }
        try {
            log.info("Admin " + SecurityUserHolder.getCurrentUser() + " is viewing user " + this.username);
        } catch (ServiceException e1) {
            this.addActionError("You must be I-DSD Admin to use this feature. If you are, please log in again as your session has run out. ");
            log.error("Error when retrieving current login user from session. ", e1);
        }
        this.portalUser = retrievePortalUserFromDatabase(this.username);
        if (null == this.portalUser) {
            this.addActionError("User does not exist!");
            log.error(this.username + " does not exist!");
            return ERROR;
        }
        for (UserRole userRole : this.portalUser.getUserRoles()) {
            if ("ROLE_RESEARCHER".equalsIgnoreCase(userRole.getRole().getRoleName())) {
                this.validateFrom = userRole.getValidateFrom();
                this.validateTo = userRole.getValidateTo();
            }
        }
        //this.roles = this.userService.getRolesExceptUSER();
        //this.existingRoles = this.user.getRoles();
        //this.existingRoles = getExistingRolesIds(this.user.getRoles());
        return SUCCESS;
    }

    private PortalUser retrievePortalUserFromDatabase(String username) {
        PortalUser portalUser = null;
        try {
            portalUser = this.userService.getPortalUserByUsername(username);
        } catch (ServiceException e) {
            this.addActionError("Error occurred while retrieving user. ");
            log.error("Error occurred while retrieving user by username: " + username, e);
        }
        return portalUser;
    }

    private Set<Long> getExistingRolesIds(Set<Role> roles) {
        Set<Long> existingRoles = new HashSet<Long>();
        for (Role r : roles) {
            existingRoles.add(r.getRoleId());
        }
        return existingRoles;
    }

    @PreAuthorize("hasAnyRole('ROLE_SUPERVISOR')")
    public String approveUser() {
        try {
            this.portalUser = this.userService.assignRolesForPortalUser(this.username, this.roleIds, this.validateFrom, this.validateTo);
//			this.username = this.user.getUsername();
//			this.userRoles = new ArrayList<UserRole>(this.user.getUserRoles());
        } catch (ServiceException e) {
            this.addActionError("Error when assigning roles for user: " + this.username);
            log.error("Error when assigning user: " + this.username + " with roles: " + this.roleIds, e);
            return INPUT;
        }

        emailService.sendPortalUserApprovalEmail(this.portalUser.getEmail(),
                Utility.constructApprovalEmailToUser(this.portalUser.getUsername(), this.portalUser.getName()));
        return SUCCESS;
    }

    public void validateApproveUser() {
        if (null == this.username || null == this.roleIds) {
            this.addActionError("You must select a user and roles to proceed. ");
            if (null == this.username) {
                log.error("username == null");
            }
            if (null == this.roleIds) {
                log.error("this.roleIds == null");
            }
        }
        if (StringUtils.isEmpty(this.roleIds)) {
            this.addActionError("You must pick at least one role for this user! ");
        }

        if (null == this.portalUser) {
            this.addActionError("This user does not exist. ");
            log.error(this.username + " does not exist!");
        }

        String[] roleStrings = roleIds.split(",");
        for (String roleString : roleStrings) {
            if (roleString.equalsIgnoreCase("1")) {
                if (null != validateFrom && null != validateTo && validateFrom.after(validateTo)) {
                    this.addActionError("Validate from date must be before or same as the validate to date.");
                }
            }
        }
    }

    @PreAuthorize("hasAnyRole('ROLE_SUPERVISOR')")
    public String confirmUserDeletion() {
        try {
            this.userService.deletePortalUser(this.username);
        } catch (ServiceException e) {
            this.addActionError("Error when deleting user: " + this.username);
            log.error("Error when deleting user: " + this.username, e);
            return INPUT;
        }
        return SUCCESS;
    }

    @PreAuthorize("hasAnyRole('ROLE_SUPERVISOR')")
    public String userDeletionAttempt() {
        if (null == this.username) {
            this.addActionError("No user selected for deletion.");
            log.error("No user selected for deletion");
            return INPUT;
        }
        this.portalUser = retrievePortalUserFromDatabase(this.username);
        if (null == this.portalUser) {
            this.addActionError("The user you are trying to delete does not exist. ");
            return INPUT;
        }
        return SUCCESS;
    }

    @PreAuthorize("hasAnyRole('ROLE_SUPERVISOR')")
    public String resetUserPassword() {
        try {
            this.userService.resetPortalUserPassword(username);
        } catch (ServiceException e) {
            log.error("Error when resetting user password", e);
            this.addActionError(e.getMessage());
            return INPUT;
        }

        return SUCCESS;
    }

    @PreAuthorize("hasAnyRole('ROLE_SUPERVISOR')")
    public String editOthersProfileView() {
        if (null == username) {
            this.addActionError("Username is null. Please select a user to proceed.");
            return INPUT;
        }
        this.countries = this.centreService.getAllCountries();
        this.portalUser = retrievePortalUserFromDatabase(username);
        this.secondaryRoles = Utility.splitStringToList(this.portalUser.getSecondaryRoles());
        this.interests = Utility.splitStringToList(this.portalUser.getInterest());
        return SUCCESS;
    }

    @PreAuthorize("hasAnyRole('ROLE_SUPERVISOR')")
    public String editOthersProfile() {
        if (null == portalUser) {
            this.addActionError("User is null. Please retry.");
            return INPUT;
        }
        try {
            log.debug("primaryRole: " + this.portalUser.getPrimaryRole());
            log.debug("secondaryRole: " + this.portalUser.getSecondaryRoles());
            this.portalUser = this.userService.updatePortalUser(portalUser);
        } catch (ServiceException e) {
            this.addActionError("Error when updating profile for user: " + portalUser.getUsername());
            log.error("Error when updating profile for user " + this.portalUser.getUsername() + " by Admin", e);
        }
        return SUCCESS;
    }

    @PreAuthorize("hasAnyRole('ROLE_SUPERVISOR')")
    public String activateUser() {
        try {
            this.portalUser = this.userService.activatePortalUser(username);
        } catch (ServiceException e) {
            this.addActionError(e.getMessage());
            log.error(e);
            return INPUT;
        }
        this.portalUsers = this.userService.getPortalUsersByExample(Utility.clonePortalUser(this.portalUser), true);
        emailService.send(Utility.getEmailRecipient(this.portalUser.getEmail()),
                "I-DSD User Account Activation",
                Utility.constructActivationEmailMsg(this.portalUser.getName(), this.portalUser.getUsername()));
        return SUCCESS;
    }

    @PreAuthorize("hasAnyRole('ROLE_SUPERVISOR')")
    public String deactivateUser() {
        try {
            this.portalUser = this.userService.deactivatePortalUser(username);
        } catch (ServiceException e) {
            this.addActionError(e.getMessage());
            log.error(e);
            return INPUT;
        }
        this.portalUsers = this.userService.getPortalUsersByExample(Utility.clonePortalUser(this.portalUser), true);
        emailService.send(Utility.getEmailRecipient(this.portalUser.getEmail()),
                "I-DSD User Account Activation",
                Utility.constructDeactivationEmailMsg(this.portalUser.getName(), this.portalUser.getUsername()));
        return SUCCESS;
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

    public PortalUser getPortalUser() {
        return portalUser;
    }

    public void setPortalUser(PortalUser portalUser) {
        this.portalUser = portalUser;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(String roleIds) {
        this.roleIds = roleIds;
    }

    public Set<Long> getExistingRoles() {
        return existingRoles;
    }

    public void setExistingRoles(Set<Long> existingRoles) {
        this.existingRoles = existingRoles;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
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

    public Date getValidateFrom() {
        return validateFrom;
    }

    public void setValidateFrom(Date validateFrom) {
        this.validateFrom = validateFrom;
    }

    public Date getValidateTo() {
        return validateTo;
    }

    public void setValidateTo(Date validateTo) {
        this.validateTo = validateTo;
    }

    public List<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

}
