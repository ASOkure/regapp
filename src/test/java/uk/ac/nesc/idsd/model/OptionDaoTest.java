package uk.ac.nesc.idsd.model;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import uk.ac.nesc.idsd.AbstractTest;

import java.util.List;

/**
 * Created by jiangj on 21/03/2015.
 */
public class OptionDaoTest extends AbstractTest {
    @Autowired
    private OptionDao optionDao;

    @Test
    public void testFindAllValuesByMenuId() {
        List<String> optionValues = optionDao.findAllOptionValuesByMenuId(3);
        log.info(optionValues);
    }
}
