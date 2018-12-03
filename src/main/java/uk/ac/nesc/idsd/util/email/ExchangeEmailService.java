package uk.ac.nesc.idsd.util.email;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import uk.ac.nesc.idsd.util.Utility;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

public class ExchangeEmailService {
    private static final Log log = LogFactory.getLog(ExchangeEmailService.class);

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
    final static String IDSD_REGISTRATION_RECIPIENTS = Utility.isDebug() ? fileProperties.getProperty("DEBUG_REGISTRATION_RECIPIENTS") : fileProperties.getProperty("IDSD_REGISTRATION_RECIPIENTS");
    final static String ICAH_REGISTRATION_RECIPIENTS = Utility.isDebug() ? fileProperties.getProperty("DEBUG_REGISTRATION_RECIPIENTS") : fileProperties.getProperty("ICAH_REGISTRATION_RECIPIENTS");

    public static void main(String[] args) throws Exception {
        send("Test", "Test", "jipu.jiang@glasgow.ac.uk");
    }

    public static void send(String mailContent, String subject, String address) throws MessagingException {
        Properties properties = new Properties();
        properties.put("mail.transport.protocol", TRANSPORT_PROTOCOL);
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", SMTP_HOST_NAME);
        properties.put("mail.smtp.port", String.valueOf(SMTP_HOST_PORT));
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.socketFactory.port", SMTP_HOST_PORT);
        properties.put("mail.smtp.socketFactory.fallback", "false");
//        properties.put("mail.smtp.starttls.enable", "true");
//        properties.put("mail.smtp.ssl.enable", "true");
//        properties.put("mail.smtp.auth.plain.disable", true);
//        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        Transport transport = null;
        try {
            Session.getDefaultInstance(properties, null);
            Session mailSession = Session.getInstance(properties,
                    new javax.mail.Authenticator() {
                        protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(SMTP_AUTH_USER, SMTP_AUTH_PWD);
                        }
                    });
            mailSession.setDebug(true);
            transport = mailSession.getTransport(TRANSPORT_PROTOCOL);
            MimeMessage message = new MimeMessage(mailSession);

            message.addFrom(InternetAddress.parse(FROM, false));
            message.setReplyTo(InternetAddress.parse(FROM, false));
            //message.setSubject("I-DSD user registration request");
            message.setSubject(subject, "UTF-8");
            message.setContent(mailContent, "text/plain");
            try {
                message.setFrom(new InternetAddress(FROM, FROM_USER, "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                log.error("Error while setting FROM filed for the email message", e);
            }
            message.addFrom(InternetAddress.parse(FROM, false));
            message.setReplyTo(InternetAddress.parse(FROM, false));

            Address[] toUser = InternetAddress.parse(address);

            message.addRecipients(Message.RecipientType.TO, toUser);
            transport = mailSession.getTransport();
//            transport.connect(username, password);
            transport.connect(SMTP_HOST_NAME, SMTP_HOST_PORT, SMTP_AUTH_USER, SMTP_AUTH_PWD);
            transport.sendMessage(message, message.getAllRecipients());
        } finally {
            if (transport != null) {
                try {
                    transport.close();
                } catch (MessagingException logOrIgnore) {
                    log.error("Error closing transport", logOrIgnore);
                }
            }
        }
    }

}