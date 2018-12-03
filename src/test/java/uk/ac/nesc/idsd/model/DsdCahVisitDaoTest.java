package uk.ac.nesc.idsd.model;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import uk.ac.nesc.idsd.AbstractTest;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by jiangj on 10/03/2015.
 */
public class DsdCahVisitDaoTest extends AbstractTest {

    @Autowired
    private DsdCahVisitDao dsdCahVisitDao;
    @Autowired
    private DsdIdentifierDao dsdIdentifierDao;
    @Autowired
    private DsdCahDao dsdCahDao;

    @Test
    public void testDsdCahVisitSave() {
        DsdIdentifier dsdIdentifier = new DsdIdentifier();
        dsdIdentifierDao.save(dsdIdentifier);

        DsdCah dsdCah = new DsdCah();
        dsdCah.setDsdIdentifier(dsdIdentifier);
        dsdIdentifier.setDsdCah(dsdCah);
        dsdCahDao.save(dsdCah);

        DsdCahVisit dsdCahVisit = new DsdCahVisit();
        dsdCahVisit.setDsdCah(dsdCah);
        dsdCah.getDsdCahVisits().add(dsdCahVisit);
        Set<DsdCahVisitEpisode> episodeSet = new HashSet<>();
        episodeSet.add(new DsdCahVisitEpisode(dsdCahVisit, 1, "Yes"));
        episodeSet.add(new DsdCahVisitEpisode(dsdCahVisit, 2, "Yes"));
        dsdCahVisit.setDsdCahVisitEpisodes(episodeSet);

        dsdCahVisitDao.save(dsdCahVisit);

        log.info("dsdCahVisit ID = " + dsdCahVisit.getDsdCahVisitId());
        log.info("episodes: " + dsdCahVisit.getDsdCahVisitEpisodes());
    }

    @Test
    public void testCloneWithEmptyString() throws CloneNotSupportedException {
        DsdCahVisit dsdCahVisit = new DsdCahVisit();
        dsdCahVisit.setCushingoid("");
        DsdCahVisit clone = new DsdCahVisit(dsdCahVisit);
        Assert.assertNull("Cloned field should be null as source is empty", clone.getCushingoid());
    }


}
