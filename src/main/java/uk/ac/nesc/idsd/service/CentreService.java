package uk.ac.nesc.idsd.service;

import uk.ac.nesc.idsd.model.Centre;
import uk.ac.nesc.idsd.model.Country;
import uk.ac.nesc.idsd.service.exception.ServiceException;

import java.util.List;

public interface CentreService {

    List<Centre> getAllCentres();

    List<Country> getAllCountries();

    Centre getCentreByCentreName(String centreName);

    Country getCountryById(Long countryId);

    void updateCountry(Long countryId, String countryName);

    void saveCountry(String countryName);

    void deleteCountry(Long countryId);

    Centre getCentreById(Long centreId);

    void saveCentre(Centre centre);

    void updateCentre(Centre centre);

    void updateAdditionalCentreInfo(Centre centre);

    void saveCentre(Long countryId, String centreName);

    void updateCentre(Long centreId, String centreName);

    void deleteCentre(Long centreId);

    String getCentreLeaderUsername(Long centreId);

    String saveCentreLeader(Long centreId, String username) throws ServiceException;

}
