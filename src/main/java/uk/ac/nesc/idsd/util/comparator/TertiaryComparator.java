package uk.ac.nesc.idsd.util.comparator;

import uk.ac.nesc.idsd.model.Tertiary;

import java.util.Comparator;

public class TertiaryComparator implements Comparator<Tertiary> {

    public int compare(Tertiary t1, Tertiary t2) {
        if (t1.equals(t2)) {
            return 0;
        } else if (t1.getTertiaryId() < t2.getTertiaryId()) {
            return -1;
        } else {
            return 1;
        }
    }

}