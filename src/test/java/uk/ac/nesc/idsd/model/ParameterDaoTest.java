package uk.ac.nesc.idsd.model;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import uk.ac.nesc.idsd.AbstractTest;

import java.util.Arrays;
import java.util.List;

/**
 * Created by jiangj on 21/03/2015.
 */
public class ParameterDaoTest extends AbstractTest {
    @Autowired
    private ParameterDao parameterDao;

    @Test
    public void testFindByIds() {
        List<Parameter> parameters = parameterDao.findByIds(Arrays.asList(1, 2, 3, 4, 5));
        log.info(parameters);
    }
}
