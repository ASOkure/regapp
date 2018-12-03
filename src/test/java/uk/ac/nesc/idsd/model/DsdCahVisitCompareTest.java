package uk.ac.nesc.idsd.model;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import uk.ac.nesc.idsd.AbstractTest;

import java.util.List;

/**
 * Created by jiangj on 21/03/2015.
 */
public class DsdCahVisitCompareTest extends AbstractTest {
    @Test
    public void testCompare() {
        DsdCahVisit visit1 = new DsdCahVisit();
        DsdCahVisit visit2 = new DsdCahVisit();
        visit1.setDsdCahVisitId(1L);
        visit2.setDsdCahVisitId(2L);
        log.info(visit1.compareTo(visit2));
    }

}
