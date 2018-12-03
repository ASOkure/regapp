package uk.ac.nesc.idsd.security;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import uk.ac.nesc.idsd.model.DsdIdentifier;
import uk.ac.nesc.idsd.model.PortalUser;

import java.util.ArrayList;
import java.util.List;

public class Anonymization {
    private static final Log log = LogFactory.getLog(Anonymization.class);

    public static List<DsdIdentifier> anonymize(List<DsdIdentifier> _dsdIdentifiers, PortalUser portalUser) {
        List<DsdIdentifier> dsdIdentifiers = new ArrayList<DsdIdentifier>();
        if (Authorization.isAuditor(portalUser)) {
            return _dsdIdentifiers;
        } else {
            for (DsdIdentifier d : _dsdIdentifiers) {
                d = anonymize(d, portalUser);
                dsdIdentifiers.add(d);
            }
            return dsdIdentifiers;
        }
    }

    public static DsdIdentifier anonymize(DsdIdentifier d, PortalUser portalUser) {
        DsdIdentifier dsdIdentifier = new DsdIdentifier(d);
        if (!Authorization.isAuditor(portalUser)) {
            if (!Ownership.isEditable(dsdIdentifier, portalUser)) {
                dsdIdentifier.setLocalId("XXXXXX");
            }
        }
        return dsdIdentifier;
    }
}
