package uk.ac.nesc.idsd.service.impl;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import uk.ac.nesc.idsd.Constants;
import uk.ac.nesc.idsd.exception.DataException;
import uk.ac.nesc.idsd.exception.ErrorCodes;
import uk.ac.nesc.idsd.model.Centre;
import uk.ac.nesc.idsd.model.CentreDao;
import uk.ac.nesc.idsd.model.PatientUser;
import uk.ac.nesc.idsd.model.PatientUserDao;
import uk.ac.nesc.idsd.model.PortalUser;
import uk.ac.nesc.idsd.model.PortalUserDao;
import uk.ac.nesc.idsd.model.Role;
import uk.ac.nesc.idsd.model.RoleDao;
import uk.ac.nesc.idsd.model.UserRole;
import uk.ac.nesc.idsd.model.UserRoleDao;
import uk.ac.nesc.idsd.security.Authorization;
import uk.ac.nesc.idsd.security.RandomStringGenerator;
import uk.ac.nesc.idsd.security.encryption.PasswordUtil;
import uk.ac.nesc.idsd.service.UserService;
import uk.ac.nesc.idsd.service.exception.ServiceException;
import uk.ac.nesc.idsd.util.Utility;
import uk.ac.nesc.idsd.util.comparator.PortalUserComparator;
import uk.ac.nesc.idsd.util.comparator.UserRoleComparator;
import uk.ac.nesc.idsd.util.email.EmailService;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

// @Service
public class UserServiceImpl implements UserService, Serializable {
    private static final Log log = LogFactory.getLog(UserServiceImpl.class);

    @Autowired(required = true)
    @Qualifier("portalUserDao")
    private PortalUserDao portalUserDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private UserRoleDao userRoleDao;
    @Autowired
    private CentreDao centreDao;
    @Autowired
    private PatientUserDao patientUserDao;
    @Autowired
    private EmailService emailService;

    public static void main(String[] args) {
        DateTime then;
        DateTime now = new DateTime().withDate(2011, 8, 31);
        then = now.minusMonths(6);
        log.info("then = " + then);
    }

    public PortalUser getAndValidatePortalUser(String username, String password) throws ServiceException {
        PortalUser dbPortalUser = null;
        try {
            dbPortalUser = this.portalUserDao.findUserWithActiveRolesById(username);
        } catch (DataException e) {
            log.error("Error while retrieving portalUser with active roles by username: " + username, e);
            throw new ServiceException(ErrorCodes.WRONG_USER_OR_CREDENTIAL);
        }

        if (PasswordUtil.validatePasswordAgainstPortalUser(dbPortalUser, password)) {
            return dbPortalUser;
        } else {
            throw new ServiceException(ErrorCodes.WRONG_USER_OR_CREDENTIAL);
        }
    }

    @Override
    public boolean validatePortalUser(String username, String password) {
        PortalUser dbPortalUser = this.portalUserDao.findReadyOnlyPortalUserById(username);
        return PasswordUtil.validatePasswordAgainstPortalUser(dbPortalUser, password);
    }

    @Override
    public PortalUser getCurrentSessionUser() {
        return getPortalUserByUsername(Utility.getLoginUserName());
    }

    public PortalUser getPortalUserByUsername(String username) throws ServiceException {
        PortalUser user = this.portalUserDao.findUserById(username);
        if (user == null) {
            log.error("Cannot find user from DB for username: " + username);
            throw new ServiceException(ErrorCodes.NO_USER_FOUND);
        }
        return user;
    }

    public List<String> getPortalUsersByUsernames(String usernames) {
        List<String> accessList = new ArrayList<String>(0);
        List<String> usernameList = new ArrayList<String>(0);
        List<PortalUser> userList;
        if (usernames == null || usernames.isEmpty()) {
            return accessList;
        }
        String[] users = usernames.split(", ");
        if (users.length > 0) {
            for (String userId : users) {
                if (userId != null && !userId.trim().isEmpty()) {
                    usernameList.add(userId.trim());
                }
            }
        }

        if (CollectionUtils.isEmpty(usernameList)) {
            return accessList;
        }

        userList = this.portalUserDao.findByUsernames(usernameList);

        if (userList == null || userList.isEmpty()) {
            return accessList;
        }

        for (PortalUser user : userList) {
            if (user != null && user.getName() != null && user.getEmail() != null) {
                accessList.add(user.getName() + "(" + user.getEmail() + ")");
            }
        }
        return accessList;
    }

    public boolean isExistedPortalUser(String username) {
        return (portalUserDao.findUserById(username) != null);
    }

    public boolean isExistedEmail(String email) {
        return (this.portalUserDao.findByEmail(email) != null && !this.portalUserDao.findByEmail(email).isEmpty());
    }

    public void changePassword(String username, String oldPassword, String newPassword) throws ServiceException {
        PortalUser user = getAndValidatePortalUser(username, oldPassword);
        user.setPassword(PasswordUtil.encodePassword(newPassword));
        user.setLastPasswordChange(new Timestamp(System.currentTimeMillis()));
        this.portalUserDao.merge(user);//.save(user);
    }

    public void registerPortalUser(PortalUser portalUser) {
        portalUser.setName(Utility.formatUserName(portalUser.getName()));
        portalUser.setPassword(PasswordUtil.encodePassword(portalUser.getPassword()));
        portalUser.setRegisterTime(new Timestamp(System.currentTimeMillis()));
        portalUser.setLastLogin(new Timestamp(System.currentTimeMillis()));
        portalUser.setLastPasswordChange(new Timestamp(System.currentTimeMillis()));
        portalUser.setLoginCounter((long) 0);

        portalUser.setEnabled(true);
        portalUser.setAccountNonExpired(true);
        portalUser.setCredentialsNonExpired(true);
        portalUser.setAccountNonLocked(true);

        Set<UserRole> userRoles = new HashSet<UserRole>();
        UserRole userRole = new UserRole();
        userRole.setPortalUser(portalUser);
        userRole.setRole(getBasicPortalUserRole());
        userRoles.add(userRole);
        portalUser.setUserRoles(userRoles);

        portalUserDao.save(portalUser);
    }

    public Role getBasicPortalUserRole() {
        return roleDao.findByRoleName("ROLE_BASIC").get(0);
    }

    public PortalUser updatePortalUser(PortalUser newPortalUser) throws ServiceException {
        if (null == newPortalUser) {
            throw new ServiceException(ErrorCodes.SAVING_NULL_USER);
        }
        PortalUser existingPortalUser = this.getPortalUserByUsername(newPortalUser.getUsername());
        if (null == existingPortalUser) {
            throw new ServiceException(ErrorCodes.NO_USER_FOUND);
        }

        existingPortalUser = existingPortalUser.copy(newPortalUser);
        log.debug("primaryRole: " + existingPortalUser.getPrimaryRole());
        log.debug("secondaryRole: " + existingPortalUser.getSecondaryRoles());
        return portalUserDao.merge(existingPortalUser);
    }

    public PortalUser updatePortalUserProfileForCentreProfile(PortalUser newPortalUser) throws ServiceException {
        if (null == newPortalUser) {
            throw new ServiceException(ErrorCodes.SAVING_NULL_USER);
        }
        PortalUser existingPortalUser = this.getPortalUserByUsername(newPortalUser.getUsername());
        if (null == existingPortalUser) {
            throw new ServiceException(ErrorCodes.NO_USER_FOUND);
        }

        existingPortalUser.setAddress(newPortalUser.getAddress());
        existingPortalUser.setTel(newPortalUser.getTel());
        existingPortalUser.setEmail(newPortalUser.getEmail());
        existingPortalUser.setProfileUrl(newPortalUser.getProfileUrl());

        return portalUserDao.merge(existingPortalUser);
    }

    public PortalUser updatePortalUser(PortalUser newPortalUser, String password) throws ServiceException {
        if (null == newPortalUser) {
            throw new ServiceException(ErrorCodes.SAVING_NULL_USER);
        }
        PortalUser existingPortalUser = this.getAndValidatePortalUser(newPortalUser.getUsername(), password);

        if (newPortalUser.getEmail() != null && !newPortalUser.getEmail().isEmpty()) {
            //user changed email, check if new email exists in db already
            //if user did not change email, this validation should not be done.
            if (existingPortalUser.getEmail() != null && !existingPortalUser.getEmail().equalsIgnoreCase(newPortalUser.getEmail())) {
                List<PortalUser> emails = this.portalUserDao.findByEmail(newPortalUser.getEmail());
                if (emails != null && !emails.isEmpty()) {
                    throw new ServiceException(ErrorCodes.SAVING_USER_WITH_EMAIL_EXCEPTION);
                }
            }
        }
        existingPortalUser = existingPortalUser.copy(newPortalUser);
        return portalUserDao.merge(existingPortalUser);
    }

    @PreAuthorize("hasRole('ROLE_SUPERVISOR')")
    public void enablePortalUser(String username) throws ServiceException {
        PortalUser user = null;
        try {
            user = this.portalUserDao.findUserWithActiveRolesById(username);
        } catch (DataException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        if (!user.isEnabled()) {
            user.setEnabled(true);
            portalUserDao.save(this.portalUserDao.merge(user));
        }
    }

    @PreAuthorize("hasRole('ROLE_SUPERVISOR')")
    public PortalUser assignRolesForPortalUser(String username, String roleIds, Date validateFrom, Date validateTo) throws ServiceException {
        //find the user about to be assigned roles
        PortalUser user = this.portalUserDao.findUserById(username);
        //find approver username from login session
        String approver;
        approver = Utility.getLoginUserName();

        //find all user roles for this user, without filter
        PortalUser u = new PortalUser();
        u.setUsername(username);
        List<UserRole> allPortalUserRolesForThisPortalUser = this.userRoleDao.findByProperty("portalUser", u);
        log.info("found all PortalUserRoles linked to - " + username + ", userRoles = " + allPortalUserRolesForThisPortalUser);
        log.info("userRoles linked to the user object are: " + user.getUserRoles());

        //save userRoles to treeMap
        Map<Long, UserRole> currRoleMap = new TreeMap<Long, UserRole>();
        for (UserRole ur : allPortalUserRolesForThisPortalUser) {
            log.info("putting roles into map = [key = " + ur.getRole().getRoleId() + ", value = " + ur + "]");
            currRoleMap.put(ur.getRole().getRoleId(), ur);
        }
        log.info("Number of userRoles initially = " + currRoleMap.size());
        Set<UserRole> newPortalUserRoleSet = new TreeSet<UserRole>(new UserRoleComparator());
        //translate list of roleIds submitted from JPS to list of Strings
        log.info("RoleString submitted from JSP is: " + roleIds);
        String[] roleStrings = roleIds.split(",");
        //idea is: for each submitted role Id, look for userRole with this roleId
        //if find, update it, else if cannot find, create it
        //for these left, delete them.
        for (String roleString : roleStrings) {
            roleString = roleString.trim();
            if (StringUtils.isEmpty(roleString)) {
                log.warn("When assigning roles for user " + username + ", submitted roleId string is either null or empty: " + roleString + ". Check approve_user.jsp for roleId submission. ");
                continue;
            }
            log.info("looping submitted roleId: " + roleString);
            Long roleId = null;
            try {
                roleId = Long.parseLong(roleString);
            } catch (NumberFormatException nfe) {
                log.error("Error parsing roleId String: " + roleString + " as Long");
            }
            if (roleId == null) {
                log.error("Could not parse roleId String: " + roleString + ", left with NULL roleId. Log ERROR, and carry on");
                continue;
            }
            Role role = this.roleDao.findById(roleId);
            if (role == null) {
                log.error("There is no role of role string given = " + roleString + ", for user: " + user.getUsername());
                continue;
            }

            UserRole existingPortalUserRole = currRoleMap.get(role.getRoleId());

            if (null == existingPortalUserRole) {  // this submitted roleId is a new role for this user
                UserRole userRole = new UserRole();
                userRole.setPortalUser(user);
                userRole.setRole(role);
                if (role.getRoleId() == 1L) {
                    userRole.setValidateFrom(validateFrom);
                    userRole.setValidateTo(validateTo);
                }
                userRole.setApprovedBy(approver);
                newPortalUserRoleSet.add(userRole);
                this.userRoleDao.save(userRole);
            } else { // this submitted roleId exists already for this user, will just update it.
                log.info("userRole exist, we are updating it. " + existingPortalUserRole);
                if (role.getRoleId() == 1L) {
                    existingPortalUserRole.setValidateFrom(validateFrom);
                    existingPortalUserRole.setValidateTo(validateTo);
                }
                existingPortalUserRole.setApprovedBy(approver);
                this.userRoleDao.merge(existingPortalUserRole);
                newPortalUserRoleSet.add(existingPortalUserRole);
            }
            // remove roleId from currRoleMap
            currRoleMap.remove(role.getRoleId());
        }

        log.info("Number of userRoles to remove = " + currRoleMap.size());
        //remove roles that are no longer assigned
        for (Long roleId : currRoleMap.keySet()) {
            UserRole ur = currRoleMap.get(roleId);
            log.info("Removing userRole = " + currRoleMap.get(roleId));
            this.userRoleDao.delete(ur);
        }
        log.info("new user role set = " + newPortalUserRoleSet);
//        user.setAllPortalUserRoles(newPortalUserRoleSet);
        user.setUserRoles(newPortalUserRoleSet);
        PortalUser latestPortalUser = this.portalUserDao.merge(user);

        List<UserRole> afterRemoval = this.userRoleDao.findByProperty("portalUser", u);
        log.info("after merging, PortalUserRoles size = " + afterRemoval.size() + ", " + username + ", userRoles = " + afterRemoval);
//        log.info("after saving all user : " + u1.getAllPortalUserRoles());
        log.info("assignRolesForPortalUser() - after saving: " + latestPortalUser.getUserRoles());
        return latestPortalUser;
    }

    public List<PortalUser> getPortalUsersWaitingForApproval() {
//		PortalUser user = new PortalUser();
//		user.setAccCredExpired(new Boolean(true));
//		user.setAccNonExpired(new Boolean(true));
//		user.setAccNonLocked(new Boolean(true));
//		user.setEnabled(new Boolean (true));
//		Set<Role> roles = new HashSet<Role>();
//		List<Role> rList = this.roleDao.findByRoleName("ROLE_USER");
//		if (rList == null || rList.size() == 0 || rList.isEmpty())
//			throw new RoleException("Role ROLE_USER does not exist!");
//		roles.add(rList.get(0));
//		user.setRoles(roles);
//		return this.portalUserDao.findByExample(user);
        return this.portalUserDao.findAll();
    }

    @SuppressWarnings("unchecked")
    public List<Role> getAllRolesForApprover() {
        List<Role> roles = new ArrayList<Role>();
        for (Role role : this.roleDao.findAll()) {
//            if (!role.getRoleName().equalsIgnoreCase("ROLE_USER")) {
            roles.add(role);
//            }
        }
        return roles;
    }

    public void deletePortalUser(String username) throws ServiceException {
        if (null == username) {
            throw new ServiceException(ErrorCodes.EMPTY_USERNAME_GIVEN);
        }
        PortalUser user = getPortalUserByUsername(username);
        this.portalUserDao.delete(user);
    }

    public String getCentreLeaderJsString() {
        StringBuilder js = new StringBuilder();
        js.append("var leaderMap = new Array();");
        List<Centre> centres = this.centreDao.findAllLeadersForJS();
        for (int i = 0; i < centres.size(); i++) {
            Centre centre = centres.get(i);
            js.append("leaderMap[");
            js.append(i);
            js.append("]=new Array(\"");
            js.append(centre.getCentreName());
            js.append("\",\"");
            js.append(Utility.getLastName(centre.getLeader().getName()));
            js.append("\",\"");
            js.append(centre.getLeader().getEmail());
            js.append("\");");
        }
        return js.toString();

    }

    public List<PortalUser> getAllPortalUsers() {
        return this.portalUserDao.findAll();
    }

    @Override
    public List<PortalUser> getPortalUserByCentre(String centreName) {
        return this.portalUserDao.findByCentre(centreName);
    }

    public List<PortalUser> getAllButCurrentPortalUser(String currentPortalUsername) {
        return this.portalUserDao.findAllButCurrentUser(currentPortalUsername);
    }

    public List<PortalUser> getAllButContributors() {
        List<PortalUser> nonContributors = new ArrayList<PortalUser>();
        List<PortalUser> users = this.getAllPortalUsers();
        for (PortalUser u : users) {
            if (!Authorization.isContributor(u)) {
                nonContributors.add(u);
            }
        }
        return nonContributors;
    }

    public List<PortalUser> getPortalUserByEmail(String email) {
        return this.portalUserDao.findByEmail(email);
    }

    public List<PortalUser> getInactivePortalUserForLast6Months() {
        DateTime now = new DateTime();
        DateTime then = now.minusMonths(6);

        return this.portalUserDao.findInactiveUsersFromAsOfDate(then.toGregorianCalendar());
    }

    /**
     * Found users have not renew passwords in 6 month time.
     * And set them to be credential expired.
     *
     * @return
     */
    public List<PortalUser> getUnchangedPasswordPortalUserForLast6Months() {
        DateTime now = new DateTime();
        DateTime then = now.minusMonths(6);
        return this.portalUserDao.findUnchangedPasswordUsersFromAsOfDate(then.toGregorianCalendar());
    }

    public List<PortalUser> getInactivePortalUserForLast8Months() {
        DateTime now = new DateTime();
        DateTime then = now.minusMonths(8);

        return this.portalUserDao.findInactiveUsersFromAsOfDate(then.toGregorianCalendar());
    }

    /**
     * Found users have not renew passwords in 6 month time.
     * And set them to be credential expired.
     *
     * @return
     */
    public List<PortalUser> getUnchangedPasswordPortalUserForLast8Months() {
        DateTime now = new DateTime();
        DateTime then = now.minusMonths(8);
        return this.portalUserDao.findUnchangedPasswordUsersFromAsOfDate(then.toGregorianCalendar());
    }

    public List<PortalUser> getPortalUsersByExample(PortalUser example, boolean allPortalUsers) {
        List<PortalUser> userList = new ArrayList<PortalUser>(0);
        if (null == example) {
            return null;
        }
        if (null != example.getUsername() && !example.getUsername().isEmpty()) {
            PortalUser u = this.portalUserDao.findUserById(example.getUsername());
            if (null != u) {
                if (allPortalUsers) {
                    userList.add(u);
                } else if (u.getSearchable()) {
                    userList.add(u);
                }
            }
        } else {
            if (Constants.ALL_CENTRES.equalsIgnoreCase(example.getCentre())) {
                example.setCentre(null);
            }
            if (allPortalUsers) {
                example.setSearchable(null);
            } else {
                example.setSearchable(Boolean.TRUE);
            }
            Set<PortalUser> userSet = new HashSet<PortalUser>(this.portalUserDao.findByCriteria(example));
            userList = new ArrayList<PortalUser>(userSet);
            Collections.sort(userList, new PortalUserComparator());
        }
        return userList;
    }

    public void resetPortalUserPassword(String username) throws ServiceException {
        if (null == username) {
            throw new ServiceException("PortalUsername must be provided to reset password");
        }
        PortalUser user = this.portalUserDao.findUserById(username);
        if (null == user) {
            throw new ServiceException("Cannot find user by username [" + username + "]");
        }
        String autoGenPassword = RandomStringGenerator.generateRandomString();
        user.setPassword(PasswordUtil.encodePassword(autoGenPassword));
        user.setLastPasswordChange(new Timestamp(System.currentTimeMillis()));
        user = this.portalUserDao.merge(user);
        emailService.sendPortalUserPasswordResetEmail(Utility.getEmailRecipient(user.getEmail()),
                Utility.constructPortalUserPasswordResetEmailMsg(user.getName(), autoGenPassword));
    }

    public PortalUser deactivatePortalUser(String username) throws ServiceException {
        if (null == username) {
            throw new ServiceException("PortalUsername must be provided to deactivate. ");
        }
        PortalUser user = this.portalUserDao.findUserById(username);
        if (null == user) {
            throw new ServiceException("Cannot find user by username [" + username + "]");
        }
        user.setEnabled(Boolean.FALSE);
        user = this.portalUserDao.merge(user);

        return user;
    }

    public PortalUser activatePortalUser(String username) throws ServiceException {
        if (null == username) {
            throw new ServiceException("PortalUsername must be provided to deactivate. ");
        }
        PortalUser user = this.portalUserDao.findUserById(username);
        if (null == user) {
            throw new ServiceException("Cannot find user by username [" + username + "]");
        }
        user.setEnabled(Boolean.TRUE);
        user = this.portalUserDao.merge(user);
        return user;
    }

    public PatientUser createPatientUser(String patientUsername, Long dsdIdentifierId, PortalUser introducedBy) {
        PatientUser patientUser = new PatientUser();
        patientUser.setUsername(patientUsername);
        patientUser.setEnabled(false);
        patientUser.setAccountNonLocked(false);
        patientUser.setAccountNonExpired(false);
        patientUser.setCredentialsNonExpired(false);
        patientUser.setDsdIdentifierId(dsdIdentifierId);
        patientUser.setIntroducedBy(introducedBy);
        patientUser.setRegisterTime(new Timestamp(System.currentTimeMillis()));
        patientUserDao.save(patientUser);
        return patientUser;
    }

    @Override
    public boolean isPatientUserExist(String patientUsername, Long dsdIdentifierId) {
        PatientUser patientUser = patientUserDao.findByUsernameAndDsdIdentifierId(patientUsername, dsdIdentifierId);
        return null != patientUser;
    }

    @Override
    public PatientUser getPatientUser(String patientUsername) {
        return patientUserDao.findById(patientUsername);
    }

    @Override
 
    public PatientUser updatePatientUser(PatientUser patientUser, String password) {
        patientUser.setPassword(PasswordUtil.encodePassword(password));
        patientUser.setRegisterTime(new Timestamp(System.currentTimeMillis()));
        patientUser.setIp(Utility.getLoginUserIp());
        patientUser.setLastPasswordChange(new Timestamp(System.currentTimeMillis()));
        patientUser.setEnabled(true);
        patientUser.setEnabled(true);
        patientUser.setAccountNonLocked(true);
        patientUser.setAccountNonExpired(true);
        patientUser.setCredentialsNonExpired(true);
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority(Role.ROLE_PATIENT));
        patientUser.setAuthorities(authorities);
        return patientUserDao.merge(patientUser);
    }

    @Override
    public List<PatientUser> getPatientUsersForDsdIdentifier(Long registerId) {
        return patientUserDao.findByDsdIdentifierId(registerId);
    }

    public void setPortalUserDao(PortalUserDao portalUserDao) {
        this.portalUserDao = portalUserDao;
    }

    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    public void setUserRoleDao(UserRoleDao userRoleDao) {
        this.userRoleDao = userRoleDao;
    }

    public void setCentreDao(CentreDao centreDao) {
        this.centreDao = centreDao;
    }

    public void setPatientUserDao(PatientUserDao patientUserDao) {
        this.patientUserDao = patientUserDao;
    }

    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }
}
