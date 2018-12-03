package uk.ac.nesc.idsd.model;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import uk.ac.nesc.idsd.AbstractTest;

/**
 * Created by jiangj on 21/03/2015.
 */
public class SectionDaoTest extends AbstractTest {

    @Autowired
    private SectionDao sectionDao;

    @Test
    public void testFindAll() {
        log.info("in test class");
        sectionDao.findAll();
    }

    @Test
    public void testFindById() {
        Section section = sectionDao.findById(12);
        log.info("parameters under section = " + section.getParameters());
    }
}
