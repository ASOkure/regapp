package uk.ac.nesc.idsd.util.comparator;

import uk.ac.nesc.idsd.model.Option;

import java.util.Comparator;

public class OptionComparator implements Comparator<Option> {

    public int compare(Option o1, Option o2) {
        if (o1.equals(o2)) {
            return 0;
        } else if (o1.getOptionId() < o2.getOptionId()) {
            return -1;
        } else {
            return 1;
        }
    }

}
