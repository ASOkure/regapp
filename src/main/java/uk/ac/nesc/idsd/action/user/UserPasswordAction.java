package uk.ac.nesc.idsd.action.user;

import org.springframework.beans.factory.annotation.Autowired;
import uk.ac.nesc.idsd.action.AbstractAction;
import uk.ac.nesc.idsd.service.UserService;
import uk.ac.nesc.idsd.service.exception.ServiceException;
import uk.ac.nesc.idsd.util.Utility;

public class UserPasswordAction extends AbstractAction {
    private static final long serialVersionUID = 2469127473385078316L;

    @Autowired
    private UserService userService;
    private String oldPassword;
    private String password;
    private String repassword;

    public String changePassword() {
        try {
            this.userService.changePassword(Utility.getLoginUserName(), oldPassword, password);
        } catch (ServiceException e) {
            this.addActionError("User exception occurred when getting username from session.");
            log.error("User exception occurred when getting username from session", e);
            return ERROR;
        }
        return SUCCESS;
    }

    public void validateChangePassword() {
        try {
            if (!password.equals(repassword)) {
                this.addActionError("Password and re-type passwords are different!");
            } else if (password.equals(oldPassword)) {
                log.error("New set password is same as old one. ");
                this.addActionError("New set password is same as old one. Please pick a different one. ");
            } else if (!this.userService.validatePortalUser(Utility.getLoginUserName(), oldPassword)) {
                log.error("validate: username = " + Utility.getLoginUserName() + " and old password is wrong. ");
                this.addActionError("Your old password is wrong!");
            }
        } catch (ServiceException e) {
            addActionError("Error when validating user password");
            log.error("Error when validating user password", e);
        }
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepassword() {
        return repassword;
    }

    public void setRepassword(String repassword) {
        this.repassword = repassword;
    }

}
