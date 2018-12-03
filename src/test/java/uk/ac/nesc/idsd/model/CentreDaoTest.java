package uk.ac.nesc.idsd.model;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import uk.ac.nesc.idsd.AbstractTest;

import java.util.List;

/**
 * Created by jiangj on 21/03/2015.
 */
public class CentreDaoTest extends AbstractTest {
    @Autowired
    private CentreDao centreDao;
    private static String MENU_NAME_VALUE = "YesNo";

    @Test
    public void testCountriesCentres() throws Exception {
        List<Centre> centres = centreDao.findCountriesCentres();
        log.info(centres);
    }

    @Test
    public void testFindAllCentreLeads() throws Exception {
        List<Centre> centres = centreDao.findAllLeadersForJS();
        log.info(centres);
    }

    @Test
    public void testFindCentresByLeaderUsername() throws Exception {
        List<Centre> centres = centreDao.findCentresByLeaderUsername("sfa");
        log.info(centres);
    }

    @Test
    public void testFindByCentreName() {
        List<Centre> centres = centreDao.findByCentreName("Glasgow");
        log.info(centres);
    }
}
