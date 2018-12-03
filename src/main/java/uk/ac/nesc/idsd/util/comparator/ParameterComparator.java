package uk.ac.nesc.idsd.util.comparator;

import uk.ac.nesc.idsd.model.Parameter;

import java.util.Comparator;

public class ParameterComparator implements Comparator<Parameter> {

    public int compare(Parameter p1, Parameter p2) {
        if (p1.equals(p2)) {
            return 0;
        } else if (p1.getParamId() < p2.getParamId()) {
            return -1;
        } else {
            return 1;
        }
    }

}
