package uk.ac.nesc.idsd.action.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import uk.ac.nesc.idsd.action.AbstractAction;
import uk.ac.nesc.idsd.model.PortalUser;
import uk.ac.nesc.idsd.service.UserService;
import uk.ac.nesc.idsd.service.exception.ServiceException;
import uk.ac.nesc.idsd.util.Utility;
import uk.ac.nesc.idsd.util.email.EmailService;

import java.util.ArrayList;
import java.util.List;

public class UserAccountReminderAction extends AbstractAction {
    private static final long serialVersionUID = 2901675192113974690L;

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    private List<PortalUser> inactiveUsers = new ArrayList<PortalUser>(0);

    public String passwordResetReminder() {
        this.inactiveUsers = this.userService.getUnchangedPasswordPortalUserForLast6Months();
        for (PortalUser u : this.inactiveUsers) {
            log.debug("User = " + u.getUsername() + " : lastLogin = " + u.getLastLogin());
            emailService.send(Utility.getEmailRecipient(u.getEmail()),
                    "I-DSD User Account Password Reset Reminder",
                    Utility.constructUser6MonthsPasswordResetReminderEmailMsg(u.getName(), u.getUsername()));
        }
        return SUCCESS;
    }

    public String inactiveUserReminder() {
        this.inactiveUsers = this.userService.getInactivePortalUserForLast6Months();
        for (PortalUser u : this.inactiveUsers) {
            log.debug("User = " + u.getUsername() + " : lastLogin = " + u.getLastLogin());
            emailService.send(Utility.getEmailRecipient(u.getEmail()),
                    "I-DSD User Account Inactivity Reminder",
                    Utility.constructInactiveUserEmailMsg(u.getName(), u.getUsername()));
        }
        return SUCCESS;
    }

    /**
     * for users who have not updated password for 8 month, we send email to tell their password will be force reset
     * and we'll auto reset their password, and send new password in another email.
     * Please note, for security reason, the first email contains username only, second email contains password only.
     *
     * @return
     */
    public String forceResetPassword() {
        this.inactiveUsers = this.userService.getUnchangedPasswordPortalUserForLast8Months();
        for (PortalUser u : this.inactiveUsers) {
            log.debug("User = " + u.getUsername() + " : lastLogin = " + u.getLastLogin());
            emailService.send(Utility.getEmailRecipient(u.getEmail()),
                    "I-DSD User Account Password Reset Reminder",
                    Utility.constructForcePasswordResetEmailMsg(u.getName(), u.getUsername()));
            try {
                this.userService.resetPortalUserPassword(u.getUsername());
            } catch (ServiceException e) {
                log.error("Error while resetting user password for username: " + u.getUsername(), e);
                this.addActionError(e.getMessage());
                return INPUT;
            }
        }
        return SUCCESS;
    }

    /**
     * TODO: shall we consider user who has its password recently forced resetting. Should be left out of the query to find inactive users.
     * I dont think we should as if they were force reset, if user does not use them, these users should be considered as inactive users.
     * This can give users some more time before deactivating user account
     *
     * @return
     */
    public String deactivateUsers() {
        this.inactiveUsers = this.userService.getInactivePortalUserForLast8Months();
        for (PortalUser u : this.inactiveUsers) {
            log.debug("User = " + u.getUsername() + " : lastLogin = " + u.getLastLogin());
            try {
                this.userService.deactivatePortalUser(u.getUsername());
            } catch (ServiceException e) {
                this.addActionError(e.getMessage());
                log.error(e);
                return INPUT;
            }
            emailService.send(Utility.getEmailRecipient(u.getEmail()),
                    "I-DSD User Account Inactivity Reminder",
                    Utility.constructDeactivationInactiveUsersEmailMsg(u.getName(), u.getUsername()));
        }
        return SUCCESS;
    }

    public List<PortalUser> getInactiveUsers() {
        return inactiveUsers;
    }

    public void setInactiveUsers(List<PortalUser> inactiveUsers) {
        this.inactiveUsers = inactiveUsers;
    }

}
