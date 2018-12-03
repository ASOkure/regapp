package uk.ac.nesc.idsd.util.email;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.mail.MessagingException;

public class MailerTest {

    public static void main(String[] args) throws MessagingException {
        //Create the application context
        ApplicationContext context = new ClassPathXmlApplicationContext("test-application-context.xml");

        //Get the mailer instance
        EmailService mailer = (EmailService) context.getBean("emailService");

        //Send a composed mail
        mailer.send("jipu.jiang@glasgow.ac.uk,jorbear2002@hotmail.com", "Test Subject", "Testing body");

//        mailer.testMode();

        //Send a pre-configured mail
//        mailer.sendPreConfiguredMail("Exception occurred everywhere.. where are you ????");
    }
}