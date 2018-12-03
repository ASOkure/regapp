package uk.ac.nesc.idsd.action.feedback;

import org.springframework.beans.factory.annotation.Autowired;
import uk.ac.nesc.idsd.action.AbstractAction;
import uk.ac.nesc.idsd.model.PortalUser;
import uk.ac.nesc.idsd.service.UserService;
import uk.ac.nesc.idsd.util.Utility;
import uk.ac.nesc.idsd.util.email.EmailService;

import java.util.Date;

public class SendFeedbackAction extends AbstractAction {
    @Autowired
    private EmailService emailService;
    @Autowired
    private UserService userService;
    private String feedback;

    public String sendFeedback() {
        PortalUser portalUser = userService.getCurrentSessionUser();
        emailService.send(Utility.getFeedbackEmailRecipients(), constructEmailTitle(portalUser.getName()), constructEmailBody(portalUser, feedback, mode));
        return SUCCESS;
    }

    private String constructEmailTitle(String name) {
        return "New feedback from" + name;
    }

    private String constructEmailBody(PortalUser portalUser, String feedback, String mode) {
        String target = "I-DSD";
        if (null != mode && "icah".equalsIgnoreCase(mode)) {
            target = "I-CAH";
        }
        return "Dear " + target + " PM, \n" +
                "\n\"" + portalUser.getUsername() + " has provided a new feedback. \n" +
                "\nDetail: \n" +
                "\nFull Name: " + portalUser.getName() +
                "\nCountry: " + portalUser.getCountry() +
                "\nCentre: " + portalUser.getCentre() +
                "\nEmail: " + portalUser.getEmail() +
                "\nTelephone: " + portalUser.getTel() +
                "\n\nFeedback: \n" + feedback +
                "\n\n\nBest regards, " +
                "\nSystem Administrator\n" +
                (new Date()).toString();
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
