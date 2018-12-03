package uk.ac.nesc.idsd.model;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import uk.ac.nesc.idsd.AbstractTest;

import java.util.List;

/**
 * Created by jiangj on 21/03/2015.
 */
public class TertiaryDaoTest extends AbstractTest {
    @Autowired
    private TertiaryDao tertiaryDao;

    @Test
    public void testFindAllValuesByMenuId() {
        List<String> tertiaryValues = tertiaryDao.findAllTertiaryValuesByOptionValue("Congenital Adrenal Hyperplasia");
        log.info(tertiaryValues);
    }
}
