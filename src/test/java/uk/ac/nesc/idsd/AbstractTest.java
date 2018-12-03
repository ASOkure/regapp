package uk.ac.nesc.idsd;

import org.apache.commons.lang3.time.StopWatch;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import uk.ac.nesc.idsd.model.PortalUser;
import uk.ac.nesc.idsd.service.UserService;
import uk.ac.nesc.idsd.service.exception.ServiceException;

/**
 * Created by jiangj on 03/03/2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
//@TransactionConfiguration(transactionManager = "transactionManager")
@Transactional
public abstract class AbstractTest extends AbstractTransactionalJUnit4SpringContextTests {
    protected static final Log log = LogFactory.getLog(AbstractTest.class);

    protected PortalUser portalUser;

    @Autowired
    private UserService userService;
    private StopWatch stopWatch;

    @Before
    public void setUp() {
        stopWatch = new StopWatch();
        stopWatch.start();
        log.info("Stopwatch started. ");
        createUser();
    }

    @After
    public void after() {
        stopWatch.stop();
        logger.info("Time spent: " + stopWatch.getTime() + " ms");
    }

    protected void createUser() {
        String username = "test_user";
        try {
            portalUser = userService.getPortalUserByUsername(username);
        } catch (ServiceException e) {
            log.debug("Portal user dose not exist for username: " + username);
        }
        if (portalUser == null) {
            String defaultPassword = "abc";
            String email = "test_user@test.com";
            portalUser = new PortalUser(username, username, email, "United Kingdom", "Glasgow");
            portalUser.setPassword(defaultPassword);
            userService.registerPortalUser(portalUser);
            Authentication authentication = new UsernamePasswordAuthenticationToken(portalUser, defaultPassword);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
    }
}
