package uk.ac.nesc.idsd.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import uk.ac.nesc.idsd.exception.DataException;
import uk.ac.nesc.idsd.model.Centre;
import uk.ac.nesc.idsd.model.CentreDao;
import uk.ac.nesc.idsd.model.Country;
import uk.ac.nesc.idsd.model.CountryDao;
import uk.ac.nesc.idsd.model.PortalUser;
import uk.ac.nesc.idsd.model.PortalUserDao;
import uk.ac.nesc.idsd.service.CentreService;
import uk.ac.nesc.idsd.service.exception.NoneCheckedServiceException;
import uk.ac.nesc.idsd.service.exception.ServiceException;

import java.io.Serializable;
import java.util.List;

public class CentreServiceImpl implements CentreService, Serializable {
    protected static final Log log = LogFactory.getLog(CentreServiceImpl.class);

    @Autowired
    private CentreDao centreDao;
    @Autowired
    private CountryDao countryDao;
    @Autowired
    private PortalUserDao portalUserDao;

    @Override
    public List<Centre> getAllCentres() {
        return this.centreDao.findAll();
    }

    @Override
    public List<Country> getAllCountries() {
        return this.countryDao.findAll();
    }

    public Centre getCentreByCentreName(String centreName) {
        List<Centre> centres = this.centreDao.findByCentreName(centreName);
        if (null != centres && !centres.isEmpty()) {
            return centres.get(0);
        } else {
            throw new NoneCheckedServiceException("Centre name [" + centreName + "] does not exist in registry.");
        }
    }

    @Override
    public Country getCountryById(Long countryId) {
        return this.countryDao.findById(countryId);
    }

    @Override
    public void updateCountry(Long countryId, String countryName) {
        Country country = getCountryById(countryId);
        country.setCountryName(countryName);
    }

    @Override
    public void saveCountry(String countryName) {
        Country country = new Country();
        country.setCountryName(countryName);
        this.countryDao.save(country);
    }

    @Override
    public void deleteCountry(Long countryId) {
        Country country = null;
        country = getCountryById(countryId);
        this.countryDao.delete(country);
    }

    @Override
    public Centre getCentreById(Long centreId) {
        return this.centreDao.findById(centreId);
    }

    @Override
    public void saveCentre(Centre centre) {
        this.centreDao.save(centre);
    }

    @Override
    public void updateCentre(Centre centre) {
        Centre c = this.getCentreById(centre.getCentreId());
        c.setCentreName(centre.getCentreName());
        c.setAddress(centre.getAddress());
        c.setDescription(centre.getDescription());
        c.setClinicsDates(centre.getClinicsDates());
        c.setLocalResources(centre.getLocalResources());
        c.setLocalEvents(centre.getLocalEvents());
        c.setActiveStudies(centre.getActiveStudies());
        c.setAdditionalInfo(centre.getAdditionalInfo());
        centreDao.save(c);
    }

    @Override
    public void updateAdditionalCentreInfo(Centre centre) {
        Centre c = this.getCentreById(centre.getCentreId());
        c.setAddress(centre.getAddress());
        c.setDescription(centre.getDescription());
        c.setClinicsDates(centre.getClinicsDates());
        c.setLocalResources(centre.getLocalResources());
        c.setLocalEvents(centre.getLocalEvents());
        c.setActiveStudies(centre.getActiveStudies());
        c.setAdditionalInfo(centre.getAdditionalInfo());
        centreDao.save(c);
    }

    @Override
    public void saveCentre(Long countryId, String centreName) {
        Centre centre = new Centre();
        centre.setCentreName(centreName);
        Country country = null;
        country = getCountryById(countryId);
        centre.setCountry(country);
        this.centreDao.save(centre);
    }

    @Override
    public void updateCentre(Long centreId, String centreName) {
        Centre centre = getCentreById(centreId);
        centre.setCentreName(centreName);
        this.centreDao.merge(centre);
    }

    @Override
    public void deleteCentre(Long centreId) {
        Centre centre = getCentreById(centreId);
        this.centreDao.delete(centre);
    }

    @Override
    public String getCentreLeaderUsername(Long centreId) {
        Centre centre = this.centreDao.findById(centreId);
        String username = null;
        if (null != centre && null != centre.getLeader()) {
            username = centre.getLeader().getUsername();
        }
        return username;
    }

    @Override
    public String saveCentreLeader(Long centreId, String username) throws ServiceException {
        Centre centre = getCentreById(centreId);
        PortalUser portalUser = null;
        try {
            portalUser = this.portalUserDao.findUserWithActiveRolesById(username);
        } catch (DataException e) {
            log.error("Error while retrieving user with active roles from username: " + username, e);
            throw new ServiceException("Error while retrieving user by username: " + username);
        }
        centre.setLeader(portalUser);
        this.centreDao.merge(centre);
        return portalUser.getName();
    }
}
