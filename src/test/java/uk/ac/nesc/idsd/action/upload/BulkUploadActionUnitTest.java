package uk.ac.nesc.idsd.action.upload;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import uk.ac.nesc.idsd.AbstractTest;
import uk.ac.nesc.idsd.model.DsdIdentifier;
import uk.ac.nesc.idsd.model.PortalUser;
import uk.ac.nesc.idsd.service.exception.ServiceException;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by jiangj on 12/06/2015.
 */
public class BulkUploadActionUnitTest extends AbstractTest {
    @Autowired
    private BulkUploadAction bulkUploadAction;

    @Test
    public void testParseCsvToDsdIdentifierList() throws ServiceException, IOException, URISyntaxException {
        File file = findBulkUploadSampleFile();
        List<DsdIdentifier> list = bulkUploadAction.parseCsvToDsdIdentifiers(file, createTempPortalUser());
        assertEquals("Expecting 2 records", 2, list.size());
        log.info(list);
    }

    private File findBulkUploadSampleFile() throws URISyntaxException {
        ClassLoader classLoader = getClass().getClassLoader();
        URL url = classLoader.getResource("bulk_core_upload_test.csv");
        log.info(url.getPath());
        Path resPath = Paths.get(url.toURI());
        return resPath.toFile();
    }

    @Test
    public void testSaveDsdIdentifiers() throws URISyntaxException {
        File file = findBulkUploadSampleFile();
        List<DsdIdentifier> list = bulkUploadAction.parseCsvToDsdIdentifiers(file, createTempPortalUser());
        bulkUploadAction.saveDsdIdentifiers(list);
    }

    @Test
    public void testPath() {
        ClassLoader classLoader = getClass().getClassLoader();
        String path = classLoader.getResource("bulk_core_upload_test.csv").getPath();
        log.info(path);
    }

    @Test
    public void testCsvParsingWithException() throws ServiceException {
        //test when validation fails
    }

    protected PortalUser createTempPortalUser() {
        String defaultPassword = "abc";
        String email = "test_user@test.com";
        PortalUser portalUser = new PortalUser("test_user", "test_user", email, "United Kingdom", "Glasgow");
        portalUser.setPassword(defaultPassword);
        return portalUser;
    }
}
