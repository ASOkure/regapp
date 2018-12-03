package uk.ac.nesc.idsd.util.comparator;

import uk.ac.nesc.idsd.model.Section;

import java.util.Comparator;

public class SectionComparator implements Comparator<Section> {

    public int compare(Section s1, Section s2) {
        if (s1.equals(s2)) {
            return 0;
        } else if (s1.getSectionId() < s2.getSectionId()) {
            return -1;
        } else {
            return 1;
        }
    }

}