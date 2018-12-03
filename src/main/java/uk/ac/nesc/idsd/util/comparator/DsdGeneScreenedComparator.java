package uk.ac.nesc.idsd.util.comparator;

import uk.ac.nesc.idsd.model.DsdGeneScreened;

import java.util.Comparator;

public class DsdGeneScreenedComparator implements Comparator<DsdGeneScreened> {

    public int compare(DsdGeneScreened p1, DsdGeneScreened p2) {
        if (p1.equals(p2)) {
            return 0;
        } else if (p1.getGene().getGeneId() < p2.getGene().getGeneId()) {
            return -1;
        } else {
            return 1;
        }
    }
}