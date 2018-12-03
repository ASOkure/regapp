package uk.ac.nesc.idsd.factory.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.ac.nesc.idsd.model.Centre;
import uk.ac.nesc.idsd.model.CentreDao;
import uk.ac.nesc.idsd.model.Country;
import uk.ac.nesc.idsd.model.CountryDao;

/**
 * Created by jiangj on 12/06/2015.
 */
@Service
public class CentreFactory {

    @Autowired
    private CentreDao centreDao;
    @Autowired
    private CountryDao countryDao;

    public Centre create(String countryName, String centreName) {
        Centre centre = new Centre() ;
        Country country = new Country();
        country.setCountryName(countryName);
        countryDao.save(country);

        centre.setCountry(country);
        centre.setCentreName(centreName);
        centreDao.save(centre);
        return centre;
    }
}
