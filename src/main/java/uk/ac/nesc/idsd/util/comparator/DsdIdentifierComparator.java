package uk.ac.nesc.idsd.util.comparator;

import uk.ac.nesc.idsd.model.DsdIdentifier;

import java.util.Comparator;

public class DsdIdentifierComparator implements Comparator<DsdIdentifier> {

    public int compare(DsdIdentifier p1, DsdIdentifier p2) {
        if (p1.equals(p2)) {
            return 0;
        } else if (p1.getRegisterId() < p2.getRegisterId()) {
            return -1;
        } else {
            return 1;
        }
    }
}