package uk.ac.nesc.idsd.model;

import org.apache.commons.lang3.time.StopWatch;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import uk.ac.nesc.idsd.AbstractTest;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by jiangj on 21/03/2015.
 */
public class AbstractDaoTest extends AbstractTest {
    @Autowired
    private CountryDao countryDao;

    private final String COUNTRY_NAME = "United";
    private StopWatch stopWatch = new StopWatch();

    @Test
    public void testSave() {
        saveAndFindCountry();
    }

    @Test
    public void testFindById() {
        Country country = saveAndFindCountry();
        Country foundCountry = countryDao.findById(country.getCountryId());
        assertNotNull("Should be able to find the saved country by ID: " + country.getCountryId(), foundCountry);
        assertEquals("Found Country should have name of " + COUNTRY_NAME, COUNTRY_NAME, foundCountry.getCountryName());
    }

    @Test
    public void testGet() {
        Country country = saveAndFindCountry();
        Country foundCountry = countryDao.get(Country.class, country.getCountryId());
        assertNotNull("Should be able to find the saved country by ID: " + country.getCountryId(), foundCountry);
        assertEquals("Found Country should have name of " + COUNTRY_NAME, COUNTRY_NAME, foundCountry.getCountryName());
    }

    @Test
    public void testFindAll() {
        List<Country> countriesBefore = countryDao.findAll();
        int sizeBefore = countriesBefore.size();
        log.info("sizeBefore = " + sizeBefore);
        saveAndFindCountry();
        List<Country> countriesAfter = countryDao.findAll();
        int sizeAfter = countriesAfter.size();
        log.info("sizeAfter = " + sizeAfter);
        log.info(countriesBefore);
        log.info(countriesAfter);
        assertEquals("One more country after saved a new one", sizeAfter, sizeBefore + 1);
    }

    @Test
    public void testFindAllPerformance() {
        stopWatch.reset();
        stopWatch.start();
        List<Country> allCountries = countryDao.findAllWithQuery();
        stopWatch.stop();
        logger.info("Time spent on findAllWithQuery(): " + stopWatch.getTime() + " ms");

        stopWatch.reset();
        stopWatch.start();
        List<Country> distinctCountries = countryDao.findAll();
        stopWatch.stop();
        logger.info("Time spent on findAll(): " + stopWatch.getTime() + " ms");

        assertEquals("findAllWithQuery and findAll", allCountries.size(), distinctCountries.size());
    }

    @Test
    public void testFindByExample() {
        saveAndFindCountry();
        Country example = new Country(COUNTRY_NAME);
        List<Country> countries = countryDao.findByExample(example);
        assertNotNull("Should be able to find the saved country + " + COUNTRY_NAME, countries);
        assertEquals("Should be only 1 country named as " + COUNTRY_NAME, 1, countries.size());
    }

    @Test
    public void testFindByProperty() {
        saveAndFindCountry();
        List<Country> countries = countryDao.findByProperty(CountryDao.COUNTRY_NAME, COUNTRY_NAME);
        assertNotNull("Should be able to find the saved country + " + COUNTRY_NAME, countries);
        assertEquals("Should be only 1 country named as " + COUNTRY_NAME, 1, countries.size());
    }

    @Test
    public void testFindByPropertyWithRange() {
        saveAndFindCountry();
        List<Country> countries = countryDao.findByProperty(CountryDao.COUNTRY_NAME, COUNTRY_NAME);
        log.info("Countries = " + countries);
        assertNotNull("Should be able to find the saved country + " + COUNTRY_NAME, countries);
        assertEquals("Should be only 1 country named as " + COUNTRY_NAME, 1, countries.size());
    }

    private Country saveAndFindCountry() {
        Country country = new Country(COUNTRY_NAME);
        countryDao.save(country);

        List<Country> countries = countryDao.findByCountryName(COUNTRY_NAME);
        assertNotNull("Should be able to find the saved country + " + COUNTRY_NAME, countries);
        assertEquals("Should be only 1 country named as " + COUNTRY_NAME, 1, countries.size());

        return countries.get(0);
    }
}
