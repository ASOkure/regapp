package uk.ac.nesc.idsd.model;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import uk.ac.nesc.idsd.AbstractTest;

import java.util.List;

/**
 * Created by jiangj on 21/03/2015.
 */
public class CountryDaoTest extends AbstractTest {
    @Autowired
    private CountryDao countryDao;

    @Test
    public void testFindByCountryName() throws Exception {
        List<Country> countries = countryDao.findByCountryName("United Kingdom");
        log.info(countries);
    }
}
