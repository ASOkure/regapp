package uk.ac.nesc.idsd.util.comparator;

import uk.ac.nesc.idsd.bean.FeedbackBean;

import java.util.Comparator;

public class FeedbackComparator implements Comparator<FeedbackBean> {
    /**
     * DIFF FROM NORMAL COMPARATOR, REVERSE ORDER
     * <p/>
     * normal comparator, < is negative, > is positive.
     * Here, we use, < is positive, > is negative.
     */
    public int compare(FeedbackBean f1, FeedbackBean f2) {
        if (f1.equals(f2)) {
            return 0;
        } else if (f1.getTime().before(f2.getTime())) {
            return 1;
        } else {
            return -1;
        }
    }

}