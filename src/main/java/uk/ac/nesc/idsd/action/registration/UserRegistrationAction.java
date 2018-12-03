package uk.ac.nesc.idsd.action.registration;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import uk.ac.nesc.idsd.Constants;
import uk.ac.nesc.idsd.action.AbstractAction;
import uk.ac.nesc.idsd.model.Centre;
import uk.ac.nesc.idsd.model.Country;
import uk.ac.nesc.idsd.model.PortalUser;
import uk.ac.nesc.idsd.service.CentreService;
import uk.ac.nesc.idsd.service.UserService;
import uk.ac.nesc.idsd.util.Utility;
import uk.ac.nesc.idsd.util.comparator.CentreComparator;
import uk.ac.nesc.idsd.util.comparator.CountryComparator;
import uk.ac.nesc.idsd.util.email.EmailService;

import java.util.*;

public class UserRegistrationAction extends AbstractAction {
    private static final long serialVersionUID = 2469127473385078316L;

    @Autowired
    private UserService userService;
    @Autowired
    private CentreService centreService;
    @Autowired
    private EmailService emailService;

    private PortalUser portalUser;
    private String repassword;
    private String privilege;
    private String comment;

    private List<Country> countries = new ArrayList<Country>();
    private String leadersJS;

    private String newCountry;
    private String newCentre;
    private String newCentreLead;

    private String mode;
    private String fullName;

    @Override
    public void prepare() {
        try {
            this.leadersJS = this.userService.getCentreLeaderJsString();
            this.countries = this.centreService.getAllCountries();
            Collections.sort(this.countries, new CountryComparator());
            for (Country c : this.countries) {
                Set<Centre> centres = new TreeSet<Centre>(new CentreComparator());
                for (Centre cc : c.getCentres()) {
                    centres.add(cc);
                }
                c.setCentres(centres);
            }
//            countries.add(new Country("Other"));
        } catch (Exception e) {
            log.error("Error when preparing createAssessmentAction", e);
        }
    }

    public String registerUserView() {
        return SUCCESS;
    }

    public String registerUser() {
        if (this.portalUser == null) {
            return INPUT;
        }
        if (StringUtils.isEmpty(this.portalUser.getCountry())) {
            this.portalUser.setCountry(newCountry);
        }
        if (StringUtils.isEmpty(this.portalUser.getCentre())) {
            this.portalUser.setCentre(newCentre);
        }
        this.portalUser.setCentreLeader(newCentreLead);
        this.userService.registerPortalUser(this.portalUser);
        String adminEmailMsg = Utility.constructRegistrationEmailToAdmin(
                this.portalUser.getName(), this.portalUser.getCentre(),
                this.portalUser.getEmail(), this.portalUser.getTel(),
                this.privilege, this.comment, mode);
        String userEmailMsg = Utility.constructRegistrationEmailToUser(this.portalUser.getUsername(), this.portalUser.getName(), mode);

        if (null != mode && "icah".equalsIgnoreCase(mode)) {
            emailService.sendICAHPortalUserRegistrationEmailToAdmin(adminEmailMsg);
        } else {
            emailService.sendIDSDPortalUserRegistrationEmailToAdmin(adminEmailMsg);
        }
        emailService.sendPortalUserRegistrationEmailToUser(this.portalUser.getEmail(), userEmailMsg);
        return SUCCESS;
    }

    public void validateRegisterUser() {
        logRegisteringUser();
        validateNewUser(portalUser);
    }

    protected void validateNewUser(PortalUser portalUser) {
        if (null == portalUser) {
            log.info("Submitted PortalUser is null ");
            this.addActionError("To be registered user is NULL. Please try registering a new account again.");
        } else if (!StringUtils.isEmpty(fullName)) {
            this.addActionError("Please leave this field empty. ");
        } else if (StringUtils.isEmpty(portalUser.getName())) {
            this.addActionError("Full name cannot be empty.");
        } else if (StringUtils.isEmpty(portalUser.getPassword())) {
            this.addActionError("Password cannot be empty");
//        } else if(!portalUser.getPassword().matches(Constants.PASSWORD_REG_EX)) {
//            this.addActionError("Your password must match the following criteria: " +
//                    "\n1. A digit must occur at least once" +
//                    "\n2. A lower case letter must occur at least once\n" +
//                    "\n3. An upper case letter must occur at least once\n" +
//                    "\n4. A special character must occur at least once, including @#$%^&+=\n" +
//                    "\n5. No whitespace allowed in the entire string\n" +
//                    "\n6. at least eight characters");
        } else if (StringUtils.isEmpty(repassword)) {
            this.addActionError("Password must be entered twice");
        } else if (StringUtils.isEmpty(portalUser.getEmail())) {
            this.addActionError("Email must not be empty");
        } else if (StringUtils.isEmpty(portalUser.getTel())) {
            this.addActionError("Telephone number cannot be empty");
        } else if (StringUtils.isEmpty(portalUser.getAddress())) {
            this.addActionError("Full institute postal address must not be empty");
        } else if (StringUtils.isEmpty(privilege)) {
            this.addActionError("Privileges for using the registry must not be empty");
        } else if (StringUtils.isEmpty(comment)) {
            this.addActionError("Reasons for using the registry must not be empty");
        } else if (StringUtils.isEmpty(portalUser.getPosition())) {
            this.addActionError("Position must not be empty");
        } else if (StringUtils.isEmpty(portalUser.getSociety())) {
            this.addActionError("Professional society membership field must not be empty");
        } else if (StringUtils.isEmpty(portalUser.getPrimaryRole())) {
            this.addActionError("Primary role must not be empty");
        } else if (!this.portalUser.getUsername().matches(Constants.USERNAME_REG_EXP)) {
            this.addActionError("User name contains forbidden characters");
        } else if (!this.portalUser.getName().matches(Constants.FULL_NAME_REG_EXP)) {
            this.addActionError("Name contains forbidden characters");
        } else if (!this.portalUser.getEmail().matches(Constants.EMAIL_REG_EXP)) {
            this.addActionError("Email address is not in the right format");
        } else if (!this.portalUser.getPassword().equals(repassword)) {
            this.addActionError("Password and re-type passwords are different!");
        } else if (userService.isExistedPortalUser(this.portalUser.getUsername())) {
            this.addActionError("User name already exist. Please pick another one!");
        } else if (userService.isExistedEmail(this.portalUser.getEmail())) {
            this.addActionError("This email address has been registered. " +
                    "If you have already registered with this email address, " +
                    "please use your existing account. " +
                    "If you forget your user credential or think this is an error, " +
                    "please email jipu.jiang@glasgow.ac.uk");
        }
    }

    private void logRegisteringUser() {
        if (portalUser != null) {
            log.info("PortalUser to be validated and registered is: " + portalUser.toLongString());
        }
        log.info("privilege = " + privilege);
        log.info("comment = " + comment);
        log.info("newCountry = " + newCountry);
        log.info("newCentre = " + newCentre);
        log.info("newCentreLead = " + newCentreLead);
        log.info("mode = " + mode);
        log.info("fullName = " + fullName);
    }

    public PortalUser getPortalUser() {
        return portalUser;
    }

    public void setPortalUser(PortalUser portalUser) {
        this.portalUser = portalUser;
    }

    public String getRepassword() {
        return repassword;
    }

    public void setRepassword(String repassword) {
        this.repassword = repassword;
    }

    public String getPrivilege() {
        return privilege;
    }

    public void setPrivilege(String privilege) {
        this.privilege = privilege;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }

    public String getLeadersJS() {
        return leadersJS;
    }

    public void setLeadersJS(String leadersJS) {
        this.leadersJS = leadersJS;
    }

    public String getNewCountry() {
        return newCountry;
    }

    public void setNewCountry(String newCountry) {
        this.newCountry = newCountry;
    }

    public String getNewCentre() {
        return newCentre;
    }

    public void setNewCentre(String newCentre) {
        this.newCentre = newCentre;
    }

    public String getNewCentreLead() {
        return newCentreLead;
    }

    public void setNewCentreLead(String newCentreLead) {
        this.newCentreLead = newCentreLead;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
