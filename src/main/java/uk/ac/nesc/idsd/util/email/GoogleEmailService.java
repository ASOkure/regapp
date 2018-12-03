package uk.ac.nesc.idsd.util.email;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import uk.ac.nesc.idsd.util.Utility;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

public class GoogleEmailService {
    private static final Log log = LogFactory.getLog(GoogleEmailService.class);

    static Properties fileProperties = Utility.getProperties("email.properties");

    final static String SMTP_HOST_NAME = fileProperties.getProperty("SMTP_HOST_NAME");
    final static int SMTP_HOST_PORT = new Integer(fileProperties.getProperty("SMTP_HOST_PORT"));
    final static String SMTP_AUTH_USER = fileProperties.getProperty("SMTP_AUTH_USER");
    final static String SMTP_AUTH_PWD = fileProperties.getProperty("SMTP_AUTH_PWD");
    final static String TRANSPORT_PROTOCOL = fileProperties.getProperty("TRANSPORT_PROTOCOL");
    final static String FROM = fileProperties.getProperty("FROM");
    final static String FROM_USER = fileProperties.getProperty("FROM_USER");
    final static String SMTPS_AUTH = fileProperties.getProperty("SMTPS_AUTH");
    final static boolean DEBUG_MODE = Boolean.valueOf(fileProperties.getProperty("DEBUG_MODE"));

    public static void send(String mailContent, String subject, String address) throws MessagingException {
        Properties props = new Properties();
//		props.put("mail.transport.protocol", TRANSPORT_PROTOCOL);
//		props.put("mail.smtps.host", SMTP_HOST_NAME);
//		//props.put("mail.smtps.auth", SMTPS_AUTH);
        props.put("mail.smtps.quitwait", "false");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", SMTPS_AUTH);
        props.put("mail.smtp.host", SMTP_HOST_NAME);
        props.put("mail.smtp.port", SMTP_HOST_PORT);
        props.put("mail.smtp.from", FROM);

        Session mailSession = Session.getDefaultInstance(props);
        mailSession.setDebug(DEBUG_MODE);
        Transport transport = mailSession.getTransport(TRANSPORT_PROTOCOL);

        MimeMessage message = new MimeMessage(mailSession);
        try {
            message.setFrom(new InternetAddress(FROM, FROM_USER, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            log.error("Error while setting FROM filed for the email message", e);
        }
        message.addFrom(InternetAddress.parse(FROM, false));
        message.setReplyTo(InternetAddress.parse(FROM, false));
        //message.setSubject("I-DSD user registration request");
        message.setSubject(subject, "UTF-8");
        message.setContent(mailContent, "text/plain");

        Address[] toUser = InternetAddress.parse(address);

        message.addRecipients(Message.RecipientType.TO, toUser);

        transport.connect(SMTP_HOST_NAME, SMTP_HOST_PORT, SMTP_AUTH_USER, SMTP_AUTH_PWD);

        transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
        transport.close();
    }

    public static void main(String[] args) throws Exception {
        send("Test", "Test", "jipu.jiang@glasgow.ac.uk");
    }
}