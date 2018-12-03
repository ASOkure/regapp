package uk.ac.nesc.idsd.util.comparator;

import uk.ac.nesc.idsd.model.DsdAssessment;

import java.io.Serializable;
import java.util.Comparator;

public class DsdAssessmentDateComparator implements Comparator<DsdAssessment>, Serializable {

    private static final long serialVersionUID = -4788231537701672395L;

    public int compare(DsdAssessment da1, DsdAssessment da2) {
        if (da1.equals(da2)) {
            return 0;
        } else if (da1.getAssessmentDate().before(da2.getAssessmentDate())) {
            return -1;
        } else {
            return 1;
        }
    }
}