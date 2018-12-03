package uk.ac.nesc.idsd.util.comparator;

import uk.ac.nesc.idsd.model.Centre;

import java.util.Comparator;

public class CentreComparator implements Comparator<Centre> {

    public int compare(Centre c1, Centre c2) {
        if (c1.equals(c2)) {
            return 0;
        } else if (c1.getCentreName().compareTo(c2.getCentreName()) < 0) {
            return -1;
        } else {
            return 1;
        }
    }

}