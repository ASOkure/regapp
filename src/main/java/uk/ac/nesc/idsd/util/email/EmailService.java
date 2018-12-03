package uk.ac.nesc.idsd.util.email;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import uk.ac.nesc.idsd.util.Utility;

import javax.mail.Address;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by jiangj on 30/11/2014.
 */
public class EmailService implements Serializable {
    private static final Log log = LogFactory.getLog(EmailService.class);

    @Autowired
    private MailSender mailSender;
    @Autowired
    private SimpleMailMessage templateMessage;

    @Value("${debug}")
    private boolean debugMode;

    @Value("${DEBUG_REGISTRATION_RECIPIENTS}")
    private String debugRegistrationRecipients;

    @Value("${IDSD_REGISTRATION_RECIPIENTS}")
    private String idsdRegistrationRecipients;

    @Value("${ICAH_REGISTRATION_RECIPIENTS}")
    private String icahRegistrationRecipients;

    @Value("${DEBUG_FEEDBACK_RECIPIENTS}")
    private String debugFeedbackRecipients;

    @Value("${FEEDBACK_RECIPIENTS}")
    private String feedbackRecipients;

    @Value("${SEARCH_REQUEST_APPROVER_RECIPIENTS}")
    private String searchRequestApproverRecipients;

    public void setMailSender(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void send(String to, String subject, String msg) {
        SimpleMailMessage message = new SimpleMailMessage(templateMessage);
        if (StringUtils.isNotEmpty(to)) {
            Address[] addresses = new Address[0];
            try {
                addresses = InternetAddress.parse(to);
            } catch (AddressException e) {
                log.error("Error parsing email address: " + to, e);
            }
            Set<String> emailStrings = new HashSet<String>();
            if (null != addresses && addresses.length > 0) {
                for (Address add : addresses) {
                    log.trace(add.toString());
                    emailStrings.add(add.toString());
                }
            }
            message.setTo(emailStrings.toArray(new String[0]));
//        message.setTo(to);
            message.setSubject(subject);
            message.setText(msg);
            mailSender.send(message);
        }
    }

    public void sendIDSDPortalUserRegistrationEmailToAdmin(String mailContent) {
        String recipients;
        if (debugMode) {
            recipients = debugRegistrationRecipients;
        } else {
            recipients = idsdRegistrationRecipients;
        }
        send(recipients, "I-DSD user registration request", mailContent);
    }

    public void sendICAHPortalUserRegistrationEmailToAdmin(String mailContent) {
        String recipients;
        if (debugMode) {
            recipients = debugRegistrationRecipients;
        } else {
            recipients = icahRegistrationRecipients;
        }
        send(recipients, "I-CAH user registration request", mailContent);
    }

    public void sendPortalUserRegistrationEmailToUser(String emailAddress, String mailContent) {
        send(emailAddress, "I-DSD user account created", mailContent);
    }

    public void sendPortalUserApprovalEmail(String emailAddress, String mailContent) {
        send(emailAddress, "I-DSD user account approved", mailContent);
    }

    public void sendPortalUserPasswordResetEmail(String emailAddress, String mailContent) {
        send(emailAddress, "I-DSD user password reset", mailContent);
    }

    public void constructAddFeedbackEmailMsg(String mailContent) {
        send(Utility.getFeedbackEmailRecipients(), "I-DSD Feedback message added", mailContent);
    }

    public void constructUpdateFeedbackEmailMsg(String mailContent) {
        send(Utility.getFeedbackEmailRecipients(), "I-DSD Feedback message updated", mailContent);
    }

    public void testMode() throws MessagingException {
        log.info("debug mode is: " + debugMode);
    }

    public void sendSearchRequestConfirmationEmail(String email, String mailContent) {
        send(email, "Search Request to i-dsd.org Database", mailContent);
    }

    public void sendSearchRequestToResponder(String content) {
        String recipients;
        if (debugMode) {
            recipients = debugRegistrationRecipients;
        } else {
            recipients = searchRequestApproverRecipients;
        }
        send(recipients, "Search Request to i-dsd.org Database", content);
    }
}
