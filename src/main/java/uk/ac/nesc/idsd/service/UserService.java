package uk.ac.nesc.idsd.service;

import uk.ac.nesc.idsd.model.PatientUser;
import uk.ac.nesc.idsd.model.PortalUser;
import uk.ac.nesc.idsd.model.Role;
import uk.ac.nesc.idsd.service.exception.ServiceException;

import java.util.Date;
import java.util.List;

public interface UserService {

    PortalUser getAndValidatePortalUser(String username, String password) throws ServiceException;

    boolean validatePortalUser(String username, String password);

    PortalUser getCurrentSessionUser() throws ServiceException;

    PortalUser getPortalUserByUsername(String username) throws ServiceException;

    List<String> getPortalUsersByUsernames(String usernames);

    boolean isExistedPortalUser(String username);

    boolean isExistedEmail(String email);

    void changePassword(String username, String oldpassword, String newPassword) throws ServiceException;

    void registerPortalUser(PortalUser user);

    Role getBasicPortalUserRole();

    PortalUser updatePortalUser(PortalUser newPortalUser) throws ServiceException;

    PortalUser updatePortalUser(PortalUser newPortalUser, String password) throws ServiceException;

    PortalUser updatePortalUserProfileForCentreProfile(PortalUser newPortalUser) throws ServiceException;

    void enablePortalUser(String username) throws ServiceException;

    PortalUser assignRolesForPortalUser(String username, String roles, Date validateFrom, Date validateTo) throws ServiceException;

    List<PortalUser> getPortalUsersWaitingForApproval();

    List<Role> getAllRolesForApprover();

    void deletePortalUser(String username) throws ServiceException;

    String getCentreLeaderJsString();

    List<PortalUser> getAllPortalUsers();

    List<PortalUser> getPortalUserByCentre(String centreName);

    List<PortalUser> getAllButCurrentPortalUser(String currentPortalUsername);

    List<PortalUser> getPortalUserByEmail(String email);

    List<PortalUser> getInactivePortalUserForLast6Months();

    List<PortalUser> getUnchangedPasswordPortalUserForLast6Months();

    List<PortalUser> getInactivePortalUserForLast8Months();

    List<PortalUser> getUnchangedPasswordPortalUserForLast8Months();

    List<PortalUser> getPortalUsersByExample(PortalUser user, boolean allPortalUsers);

    void resetPortalUserPassword(String username) throws ServiceException;

    PortalUser deactivatePortalUser(String username) throws ServiceException;

    PortalUser activatePortalUser(String username) throws ServiceException;

    PatientUser createPatientUser(String patientUsername, Long dsdIdentifierId, PortalUser introducedBy);

    boolean isPatientUserExist(String patientUsername, Long dsdIdentifierId);

    PatientUser getPatientUser(String patientUsername);

    PatientUser updatePatientUser(PatientUser patientUser, String password);

    List<PatientUser> getPatientUsersForDsdIdentifier(Long registerId);
}
