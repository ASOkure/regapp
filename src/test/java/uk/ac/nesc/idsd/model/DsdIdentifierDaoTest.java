package uk.ac.nesc.idsd.model;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import uk.ac.nesc.idsd.AbstractTest;

import java.util.List;

/**
 * Created by jiangj on 21/03/2015.
 */
public class DsdIdentifierDaoTest extends AbstractTest {
    @Autowired
    private DsdIdentifierDao dsdIdentifierDao;

    @Test
    public void testFindByUploader() {
        List<DsdIdentifier> dsdIdentifiers = dsdIdentifierDao.findByUploader("sfa");
        log.info(dsdIdentifiers);
    }

    @Test
    public void testFindByCentre() {
        List<DsdIdentifier> dsdIdentifiers = dsdIdentifierDao.findByCentre("Glasgow");
        log.info(dsdIdentifiers);
    }

    @Test
    public void testFindById() {
        DsdIdentifier dsdIdentifier = dsdIdentifierDao.findById(15L);
        log.info(dsdIdentifier);
    }

}
