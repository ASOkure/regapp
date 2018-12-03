package uk.ac.nesc.idsd.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import uk.ac.nesc.idsd.AbstractTest;
import uk.ac.nesc.idsd.model.PortalUser;

import java.util.List;

/**
 * Created by jiangj on 15/12/2014.
 */
public class UserServiceImplTest extends AbstractTest {
    private static final Log log = LogFactory.getLog(UserServiceImplTest.class);

    @Autowired
    private UserService userService;

    @Test
    public void testGetPortalUsersByExample() {
        //Send a composed mail
        PortalUser portalUser = new PortalUser();
        portalUser.setUsername("jiangj");
        List<PortalUser> portalUserList = userService.getPortalUsersByExample(portalUser, false);

        log.info("Found portal user: " + portalUserList);
//        mailer.testMode();

        //Send a pre-configured mail
//        mailer.sendPreConfiguredMail("Exception occurred everywhere.. where are you ????");
    }

    @Test
    public void testGetAllUsers() {
        List<PortalUser> users = userService.getAllPortalUsers();
        log.info(users);
    }
}
