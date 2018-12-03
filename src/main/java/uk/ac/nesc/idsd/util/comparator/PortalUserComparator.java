package uk.ac.nesc.idsd.util.comparator;

import uk.ac.nesc.idsd.model.PortalUser;

import java.util.Comparator;

public class PortalUserComparator implements Comparator<PortalUser> {

    public int compare(PortalUser u1, PortalUser u2) {
        if (u1.equals(u2)) {
            return 0;
        } else if (u1.getName().compareTo(u2.getName()) < 0) {
            return -1;
        } else {
            return 1;
        }
    }

}