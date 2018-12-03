package uk.ac.nesc.idsd.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import uk.ac.nesc.idsd.AbstractTest;
import uk.ac.nesc.idsd.factory.data.DsdIdentifierFactory;
import uk.ac.nesc.idsd.model.DsdCah;
import uk.ac.nesc.idsd.model.DsdCahVisit;
import uk.ac.nesc.idsd.model.DsdIdentifier;
import uk.ac.nesc.idsd.model.Parameter;
import uk.ac.nesc.idsd.service.exception.ServiceException;

import java.util.Arrays;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class DsdIdentifierServiceImplTest extends AbstractTest {
    @Autowired
    private DsdIdentifierService dsdIdentifierService;
    @Autowired
    private DsdIdentifierFactory dsdIdentifierFactory;

    @Test
    public void testSaveCahVisit() throws ServiceException {
        DsdIdentifier dsdIdentifier = dsdIdentifierFactory.create();

        DsdCah dsdCah = new DsdCah();
        dsdCah.setDsdIdentifier(dsdIdentifier);
        dsdIdentifier = dsdIdentifierService.saveCah(dsdIdentifier, dsdCah);
        log.info("dsdIdentifier.getDsdCah() = " + dsdIdentifier.getDsdCah());

        DsdCahVisit dsdCahVisit = new DsdCahVisit();
        dsdCahVisit.setDsdCah(dsdCah);
        dsdCahVisit.setAge(10.0);
        dsdCahVisit.setDate(new Date());

        dsdIdentifierService.saveCahVisit(dsdIdentifier, dsdCahVisit);
        log.info("dsdCahVisit ID = " + dsdCahVisit.getDsdCahVisitId());
        DsdCahVisit dbInstance = dsdIdentifierService.getCahVisitById(dsdCahVisit.getDsdCahVisitId());
        log.info("DB stored CAH Visit = " + dbInstance.getAge());
    }

    @Test
    public void testUpdateCahVisit() throws ServiceException {
        DsdIdentifier dsdIdentifier = dsdIdentifierFactory.create();

        DsdCah dsdCah = new DsdCah();
        dsdCah.setDsdIdentifier(dsdIdentifier);
        dsdIdentifier = dsdIdentifierService.saveCah(dsdIdentifier, dsdCah);
        log.info("dsdIdentifier.getDsdCah() = " + dsdIdentifier.getDsdCah());

        DsdCahVisit dsdCahVisit = new DsdCahVisit();
        dsdCahVisit.setDsdCah(dsdCah);
        dsdCahVisit.setAge(10.0);
        dsdCahVisit.setDate(new Date());

        //create
        dsdIdentifierService.saveCahVisit(dsdIdentifier, dsdCahVisit);

        dsdIdentifier.setBirthWeight(2000.0);
        dsdIdentifier.setBirthLength(50.0);

        //update
        dsdIdentifierService.saveCahVisit(dsdIdentifier, dsdCahVisit);

        assertNotNull("dsdCahVisit ID cannot be null", dsdCahVisit.getDsdCahVisitId() == null);
        DsdCahVisit dbDsdCahVisit = dsdIdentifierService.getCahVisitById(dsdCahVisit.getDsdCahVisitId());
        DsdIdentifier dbDsdIdentifier = dsdIdentifierService.getById(dsdIdentifier.getRegisterId());
        assertEquals("DsdCahVisit age from db", 10.0, dbDsdCahVisit.getAge(), 0.0);
        assertEquals("Birth weight", 2000.0, dbDsdIdentifier.getBirthWeight(), 0.0);
        assertEquals("Birth Length", 50.0, dbDsdIdentifier.getBirthLength(), 0.0);
    }

    @Test
    public void testGetByExample() throws ServiceException {
        DsdIdentifier dsdIdentifier = new DsdIdentifier();
        dsdIdentifier.setRegisterId(0L);

        Parameter parameter = new Parameter(null, "String", "Test Label", "dsdIdentifier,registerId", false, false, false, "Test Tooltip");
        parameter.setParamId(1);
        dsdIdentifierService.getByExample(Arrays.asList(parameter), dsdIdentifier, null, null, null);
    }
}
