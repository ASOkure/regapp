package uk.ac.nesc.idsd.security;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import uk.ac.nesc.idsd.model.DsdIdentifier;
import uk.ac.nesc.idsd.model.PortalUser;
import uk.ac.nesc.idsd.model.Role;
import uk.ac.nesc.idsd.util.Utility;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

public class Consent {
    @SuppressWarnings("unused")
    private static final Log log = LogFactory.getLog(Consent.class);

    public static List<DsdIdentifier> enforce(List<DsdIdentifier> originalList, PortalUser portalUser) {
        List<DsdIdentifier> consentList = new ArrayList<DsdIdentifier>();
        if (Authorization.isAuditor(portalUser)) {
            return originalList;
        } else {
            for (DsdIdentifier d : originalList) {
                if (Consent.check(d, portalUser) || Ownership.isEditable(d, portalUser)) {
                    consentList.add(d);
                }
            }
            return consentList;
        }

    }

    public static boolean check(DsdIdentifier d, PortalUser portalUser) {
        boolean b = false;
//		log.error("local ID = " + d.getLocalId());
//		log.error("birth weight = " + d.getBirthWeight());
//        log.error("consent check dsdIdentifier = " + d.getRegisterId() + ", consent = " + d.getConsent());
        if (null != d && null != d.getConsent()) {
            switch (d.getConsent()) {
                case 0:
                    b = false;
                    break;
                case 1:
                    b = portalUser.getCentre().equalsIgnoreCase(d.getCentreName()) && portalUser.getCountry().equalsIgnoreCase(d.getCountryName());
                    break;
                case 2:
                    b = portalUser.getCountry().equalsIgnoreCase(d.getCountryName());
                    break;
                case 3:
                    b = isMember(portalUser.getRoles());
                    break;
                //case 4:  b = isEEAMember(d.getCountry()); break;
                case 4:
                    b = true;
                    break;
            }
        }
        return b;
    }

    /*
     * check if user holds any idsd roles.
     */
    public static boolean isMember(Set<Role> roles) {
        Properties properties = Utility.getProperties("idsd.properties");
        String memberString = properties.getProperty("idsd_member").toLowerCase();

        for (Role r : roles) {
            if (memberString.contains(r.getRoleName().toLowerCase())) {
                return true;
            }
        }
        return false;
    }

}
