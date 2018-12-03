package uk.ac.nesc.idsd.model;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import uk.ac.nesc.idsd.AbstractTest;
import uk.ac.nesc.idsd.exception.DataException;

import java.sql.Timestamp;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by jiangj on 17/12/2014.
 */
public class PortalUserDaoTest extends AbstractTest {

    @Autowired(required = true)
    @Qualifier("portalUserDao")
    private PortalUserDao portalUserDao;

    @Test
    public void testGetPortalUsersByExample() {
        PortalUser portalUser = new PortalUser();
        portalUser.setPosition("Professor");
        List<PortalUser> portalUserList = portalUserDao.findByCriteria(portalUser);
        log.info("Found portal user: " + portalUserList);
    }

    @Test
    public void testGetByUsername() throws DataException {
        PortalUser portalUser = portalUserDao.findUserWithActiveRolesById("jiangj");
        assertNotNull("PortalUser jiang is not null", portalUser);
        log.info("Found portal user: " + portalUser);
        log.info("Country = " + portalUser.getCountry());
        log.info("Centre = " + portalUser.getCentre());
    }

    @Test
    public void testMerge() {
        PortalUser portalUser = portalUserDao.findUserById("jiangj");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        portalUser.setLastLogin(timestamp);
        PortalUser portalUserAfterMerge = portalUserDao.merge(portalUser);
        log.info("Before merge, timestamp = " + timestamp);
        log.info("After merge, timestamp = " + portalUserAfterMerge.getLastLogin());
        assertEquals("Timestamp is saved", timestamp, portalUserAfterMerge.getLastLogin());
    }
}
