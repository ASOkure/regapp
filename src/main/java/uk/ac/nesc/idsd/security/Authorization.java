package uk.ac.nesc.idsd.security;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.CollectionUtils;
import uk.ac.nesc.idsd.model.PatientUser;
import uk.ac.nesc.idsd.model.Role;
import uk.ac.nesc.idsd.model.UserRole;
import uk.ac.nesc.idsd.security.spring.User;
import uk.ac.nesc.idsd.util.Utility;

import java.util.Date;
import java.util.Properties;
import java.util.Set;

public class Authorization {
    private static final Log log = LogFactory.getLog(Authorization.class);

    public static boolean isPatientAccess(PatientUser patientUser) {
        Set<Role> roles = patientUser.getRoles();
        if (!CollectionUtils.isEmpty(roles)) {
            for (Role r : roles) {
                if (r.getRoleName().equalsIgnoreCase("ROLE_PATIENT")) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isAuditor(User user) {
        Set<Role> roles = user.getRoles();
        if (!CollectionUtils.isEmpty(roles)) {
            for (Role r : roles) {
                if (r.getRoleName().equalsIgnoreCase("ROLE_AUDITOR")) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isLocalContributor(User user) {
        Set<Role> roles = user.getRoles();
        if (!CollectionUtils.isEmpty(roles)) {
            for (Role r : roles) {
                if (r.getRoleName().equalsIgnoreCase("ROLE_LOCAL_CONTRIBUTOR")) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isContributor(User user) {
        Set<Role> roles = user.getRoles();
        if (!CollectionUtils.isEmpty(roles)) {
            for (Role r : roles) {
                if (r.getRoleName().equalsIgnoreCase("ROLE_CONTRIBUTOR")) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isSuperUser(User user) {
        Set<Role> roles = user.getRoles();
        if (!CollectionUtils.isEmpty(roles)) {
            for (Role r : roles) {
                if (r.getRoleName().equalsIgnoreCase("ROLE_SUPERVISOR")) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isPerspectiveResearcher(User user) {
        Set<Role> roles = user.getRoles();
        if (!CollectionUtils.isEmpty(roles)) {
            for (Role r : roles) {
                if (r.getRoleName().equalsIgnoreCase("ROLE_PERSPECTIVE_RESEARCHER")) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isResearcher(User user) {
        Date today = new Date();
        if (null != user && !CollectionUtils.isEmpty(user.getUserRoles())) {
            for (UserRole ur : user.getUserRoles()) {
                if (ur.getRole().getRoleName().equalsIgnoreCase("ROLE_RESEARCHER") &&
                        ur.getValidateFrom() != null && ur.getValidateTo() != null &&
                        today.compareTo(ur.getValidateFrom()) >= 0 && today.compareTo(ur.getValidateTo()) <= 0) {
                    return true;
                }
            }
        }

        return false;
    }

    public static boolean authorizeFileDownload(String username, String access, String researchFileOwner, String patientRecordOwner) {
        return (researchFileOwner.equalsIgnoreCase(username) ||
                patientRecordOwner.equalsIgnoreCase(username) ||
                //Authorization.authorizeContributor(user) ||
                (access != null && !access.isEmpty() && access.contains(username)));
    }

    public static boolean authorizeFileUpload(User user) {
        return Authorization.authorize(user, "file_upload");
    }

    private static boolean authorize(User user, String operation) {
        boolean flag;
        Properties properties = Utility.getProperties("idsd.properties");
        try {
            Set<Role> roles = user.getRoles();

            String ops = null;
            if (operation.equalsIgnoreCase("read")) {
                ops = properties.getProperty("read_record_roles").toUpperCase();
            } else if (operation.equalsIgnoreCase("create")) {
                ops = properties.getProperty("create_record_roles").toUpperCase();
            } else if (operation.equalsIgnoreCase("update")) {
                ops = properties.getProperty("update_record_roles").toUpperCase();
            } else if (operation.equalsIgnoreCase("delete")) {
                ops = properties.getProperty("delete_record_roles").toUpperCase();
            } else if (operation.equalsIgnoreCase("file_upload")) {
                ops = properties.getProperty("file_upload_roles").toUpperCase();
            }

            if (null == ops || null == roles || ops.length() == 0 || roles.size() == 0) {
                log.debug("operation string defined in idsd.properties file is null or empty. Or shibboleth roles do not exist!");
                flag = false;
            } else if (ops.equalsIgnoreCase("*")) {
                flag = true;
            } else {
                String[] os = ops.split(";");
                boolean[] f = new boolean[roles.size()];
                int i = 0;
                for (Role r : roles) {
                    for (String o : os) {
                        if (r.getRoleName().trim().equalsIgnoreCase(o.trim())) {
                            f[i] = true;
                            break;
                        } else {
                            f[i] = false;
                        }
                    }
                    i++;
                }
                flag = false;
                for (boolean aF : f) {
                    flag = flag || aF;
                }
            }

        } catch (NullPointerException e) {
            flag = false;
            log.error("NullPointerException when authorizing", e);
        } catch (Exception e) {
            log.error("Exception in Authorization.authorize()", e);
            flag = false;
        }
        return Utility.isDebug() || flag;
    }

}
