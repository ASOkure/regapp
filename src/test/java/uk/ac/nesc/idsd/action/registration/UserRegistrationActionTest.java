package uk.ac.nesc.idsd.action.registration;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import uk.ac.nesc.idsd.AbstractTest;
import uk.ac.nesc.idsd.model.PortalUser;
import uk.ac.nesc.idsd.model.PortalUserDao;
import uk.ac.nesc.idsd.service.exception.ServiceException;

import java.util.Collection;
import java.util.List;

public class UserRegistrationActionTest extends AbstractTest {
    @Autowired
    private UserRegistrationAction userRegistrationAction;

    @Autowired
    private PortalUserDao portalUserDao;

    @Test
    public void testValidateRegisterUserPassword() throws ServiceException {
        init();
        log.info(userRegistrationAction.getPortalUser());
        userRegistrationAction.validateRegisterUser();
        Collection<String> actionErrors = userRegistrationAction.getActionErrors();
        log.info(actionErrors);
    }

    private void init() {
        super.setUp();
        PortalUser portalUser = new PortalUser();
        portalUser.setUsername("JoeBlack");
        portalUser.setName("Joe Black");
        portalUser.setPassword("bIrkebek+18");
        portalUser.setEmail("joe.black@abc.com");
        portalUser.setTel("00441411231234");
        portalUser.setAddress("1 Steven Road, Glasgow");
        portalUser.setPosition("MyPosition");
        portalUser.setSociety("MySociety");
        portalUser.setPrimaryRole("Clinician");
        userRegistrationAction.setPortalUser(portalUser);
        userRegistrationAction.setRepassword("bIrkebek+18");
        userRegistrationAction.setPrivilege("Enter case");
        userRegistrationAction.setComment("comment");
        List<PortalUser> portalUserList = portalUserDao.findAll();
        log.info("PoratlUser list size = " + portalUserList.size());
    }
}
