package uk.ac.nesc.idsd.security;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import uk.ac.nesc.idsd.model.DsdIdentifier;
import uk.ac.nesc.idsd.model.PortalUser;

public class Ownership {
    private static final Log log = LogFactory.getLog(Ownership.class);

    public static boolean isEditable(DsdIdentifier d, PortalUser u) {
        return isUploader(d, u) || (isSameCountry(d, u) && isSameCentre(d, u));
    }

    /**
     * check ownership of given DsdIdentifier
     * Two conditions will return true
     * 1. the User is the actual record owner: d.getUploader == user.getUserId
     * 2. the User is a centre leader, and he/she is from same centre as DSD record
     *
     * @param d
     * @param u
     * @return
     */
    public static boolean check(DsdIdentifier d, PortalUser u) {
        return (isLeaderOfCentre(d, u) && isSameCountry(d, u) && isSameCentre(d, u)) || isUploader(d, u);
    }

    public static boolean isUnableToDelete(DsdIdentifier d, PortalUser u) {
        return ((!isLeaderOfCentre(d, u) || !isSameCountry(d, u) || !isSameCentre(d, u)));
    }

    /**
     * Check the centre leader stored in CENTRE table is the same user as current user.
     *
     * @param dsdIdentifier
     * @param user
     * @return
     */
    public static boolean isLeaderOfCentre(DsdIdentifier dsdIdentifier, PortalUser user) {
        if (null != dsdIdentifier && null != dsdIdentifier.getCentre()) {
            if (user.getUsername().equalsIgnoreCase(dsdIdentifier.getCentre().getLeader().getUsername())) {
                return true;
            }
        }
        return false;
    }

    public static boolean isSameCountry(DsdIdentifier d, PortalUser user) {
        return null != d && null != d.getCountryName() && null != user && null != user.getCountry()
                && d.getCountryName().trim().equalsIgnoreCase(user.getCountry().trim());
    }

    public static boolean isSameCentre(DsdIdentifier d, PortalUser user) {
        return null != d && null != d.getCentreName() && null != user && null != user.getCentre()
                && d.getCentreName().trim().equalsIgnoreCase(user.getCentre().trim());
    }

    /**
     * check if current user is uploader of the record
     *
     * @param d
     * @param u
     * @return
     */
    public static boolean isUploader(DsdIdentifier d, PortalUser u) {
        return null != d && null != d.getUploader() && null != d.getUploader().getUsername() && null != u
                && null != u.getUsername() && (d.getUploader().getUsername().trim().equalsIgnoreCase(u.getUsername().trim())
//                && isSameCountry(d, u) && isSameCentre(d, u)
        );
    }

    public static void main(String[] args) {
        PortalUser u = new PortalUser();
        u.setUsername("desloovere");
        u.setCountry("Belgium");
        u.setCentre("Ghent");
        DsdIdentifier d = new DsdIdentifier();
        d.setCountryName("Belgium");
        d.setCentreName("Ghent");
//        d.setUploader("desloovere");
        log.debug(isUploader(d, u));
    }
}
