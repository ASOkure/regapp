package uk.ac.nesc.idsd.model;

import org.springframework.context.ApplicationContext;

import java.util.List;

public class CountryDao extends AbstractDao<Country> {
    public static final String COUNTRY_NAME = "countryName";

    public static CountryDao getFromApplicationContext(ApplicationContext ctx) {
        return (CountryDao) ctx.getBean("CountryDAO");
    }

    public List<Country> findByCountryName(Object countryName) {
        return findByProperty(COUNTRY_NAME, countryName);
    }
}