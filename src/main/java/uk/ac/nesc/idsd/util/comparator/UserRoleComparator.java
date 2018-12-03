package uk.ac.nesc.idsd.util.comparator;

import uk.ac.nesc.idsd.model.UserRole;

import java.util.Comparator;

public class UserRoleComparator implements Comparator<UserRole> {

    public int compare(UserRole u1, UserRole u2) {
        return u1.getRole().getRoleId().compareTo(u2.getRole().getRoleId());
    }

}