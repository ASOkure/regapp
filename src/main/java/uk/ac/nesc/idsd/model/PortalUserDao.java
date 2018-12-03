package uk.ac.nesc.idsd.model;

import uk.ac.nesc.idsd.exception.DataException;

import java.util.Calendar;
import java.util.List;

/**
 * Created by jiangj on 18/12/2014.
 */
public interface PortalUserDao {
    List<PortalUser> findByCriteria(PortalUser instance);

    void save(PortalUser transientInstance);

    void delete(PortalUser persistentInstance);

    PortalUser findUserById(String id);

    PortalUser findUserWithActiveRolesById(String id) throws DataException;

    PortalUser findReadyOnlyPortalUserById(String id);

    List<PortalUser> findByUsernames(List<String> ids);

    List<PortalUser> findByExample(PortalUser instance);

    List<PortalUser> findByProperty(String propertyName, Object value);

    List<PortalUser> findByPassword(Object password);

    List<PortalUser> findByEmail(Object email);

    List<PortalUser> findByName(Object name);

    List<PortalUser> findByCountry(Object country);

    List<PortalUser> findByCentre(Object centre);

    List<PortalUser> findByTel(Object tel);

    List<PortalUser> findByLoginCounter(Object loginCounter);

    List<PortalUser> findByEnabled(Object enabled);

    List<PortalUser> findByAccNonExpired(Object accNonExpired);

    List<PortalUser> findByAccNonLocked(Object accNonLocked);

    List<PortalUser> findByAccCredExpired(Object accCredExpired);

    List<PortalUser> findByIp(Object ip);

    List<PortalUser> findByFax(Object fax);

    List<PortalUser> findByPosition(Object position);

    List<PortalUser> findBySociety(Object society);

    List<PortalUser> findByAddress(Object address);

    List<PortalUser> findByPrimaryRole(Object primaryRole);

    List<PortalUser> findBySecondaryRoles(Object secondaryRoles);

    List<PortalUser> findBySecondaryRolesOther(Object secondaryRolesOther);

    List<PortalUser> findByInterest(Object interest);

    List<PortalUser> findByInterestOther(Object interestOther);

    List<PortalUser> findBySpecialInterest(Object specialInterest);

    List<PortalUser> findBySearchable(Object searchable);

    List<PortalUser> findAll();

    List<PortalUser> findAllButCurrentUser(String username);

    List<PortalUser> findInactiveUsersFromAsOfDate(Calendar asOfDate);

    List<PortalUser> findUnchangedPasswordUsersFromAsOfDate(Calendar asOfDate);

    PortalUser merge(PortalUser detachedInstance);

    void attachDirty(PortalUser instance);

    void attachClean(PortalUser instance);
}
