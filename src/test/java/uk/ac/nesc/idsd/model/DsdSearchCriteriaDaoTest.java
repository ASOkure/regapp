package uk.ac.nesc.idsd.model;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import uk.ac.nesc.idsd.AbstractTest;

/**
 * Created by jiangj on 21/03/2015.
 */
public class DsdSearchCriteriaDaoTest extends AbstractTest {
    @Autowired
    private DsdSearchCriteriaDao dsdSearchCriteriaDao;

    @Test
    public void testFindById() {
        DsdSearchCriteria dsdSearchCriteria = dsdSearchCriteriaDao.findById(17L);
        log.info(dsdSearchCriteria);
    }

}
