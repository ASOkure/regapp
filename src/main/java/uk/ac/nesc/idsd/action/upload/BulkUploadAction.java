package uk.ac.nesc.idsd.action.upload;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import uk.ac.nesc.idsd.action.AbstractAction;
import uk.ac.nesc.idsd.model.DsdIdentifier;
import uk.ac.nesc.idsd.model.PortalUser;
import uk.ac.nesc.idsd.service.AuditService;
import uk.ac.nesc.idsd.service.DsdIdentifierService;
import uk.ac.nesc.idsd.service.UserService;
import uk.ac.nesc.idsd.service.exception.ServiceException;
import uk.ac.nesc.idsd.util.BulkUploadDsdIdentifierValidator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BulkUploadAction extends AbstractAction {
    private static final long serialVersionUID = 1237775053309839984L;

    @Autowired
    private AuditService auditService;
    @Autowired
    private UserService userService;
    @Autowired
    private DsdIdentifierService dsdIdentifierService;
    @Autowired
    BulkUploadDsdIdentifierValidator dsdIdentifierValidator;

    private File file;// The actual file
    private String contentType; // The content type of the file
    private String fileName; // The uploaded file name
    private String fileCaption;// The caption of the file entered by user
    private InputStream fileInputStream;
    private Map<Long, String> savedDsdCoreIdMap;

    private static Map<String, String> columnMapping = new HashMap<>();

    static {
        columnMapping.put("local patient id", "localId");
        columnMapping.put("year of birth", "yob");
        columnMapping.put("original sex assigned", "sexAssigned");
        columnMapping.put("country", "countryName");
        columnMapping.put("centre", "centreName");
        columnMapping.put("doctor surname", "clinician");
        columnMapping.put("email", "contact");
        columnMapping.put("karyotype", "karyotype");
        columnMapping.put("disorder type", "disorderType");
        columnMapping.put("actual diagnosis", "actualDiagnosis");
        columnMapping.put("level of sharing", "consent");
    }

    @Override
    public void prepare() {
    }

    @PreAuthorize("hasAnyRole('ROLE_CONTRIBUTOR','ROLE_LOCAL_CONTRIBUTOR','ROLE_RESEARCHER','ROLE_AUDITOR','ROLE_SUPERVISOR')")
    public String file() {
        if (this.file == null) {
            log.error("The file uploaded is null");
            addActionError("Uploaded file is null, cannot proceed. Please try again");
            return INPUT;
        }

//        restrictFileLineSize();

        List<DsdIdentifier> dsdIdentifiers;
        try {
            dsdIdentifiers = parseCsvToDsdIdentifiers(file, userService.getCurrentSessionUser());
        } catch (ServiceException e) {
            log.error("Error while parsing CSV file to DsdIdentifiers for current logged in user: " + userService.getCurrentSessionUser(), e);
            this.addActionError(e.getMessage());
            return INPUT;
        }
        saveDsdIdentifiers(dsdIdentifiers);
        return SUCCESS;
    }

    protected void saveDsdIdentifiers(List<DsdIdentifier> dsdIdentifiers) {
        this.savedDsdCoreIdMap = dsdIdentifierService.saveDsdCoreInBulk(dsdIdentifiers);
    }

    private void restrictFileLineSize() {
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(Paths.get(file.getAbsolutePath()), StandardCharsets.UTF_8);
        } catch (IOException e) {
            log.error("Error reading content from the uploaded file. ", e);
            this.addActionError("Error reading content from the uploaded file. ");
        }

        if (lines.size() == 0 || lines.size() == 1) {
            this.addActionError("Uploaded file is empty");
        } else if (lines.size() > 500) {
            this.addActionError("File is too large to bulk upload. Please break it down to smaller sets (<=500) and try again. ");
        }
        //check if first line is headers
//        String headerLine = lines.get(0);
    }

    protected List<DsdIdentifier> parseCsvToDsdIdentifiers(File file, PortalUser portalUser) throws ServiceException {
        List<DsdIdentifier> dsdIdentifiers = readDsdIdentifiersFromFile(file);
//        log.info(dsdIdentifiers);
        processDsdIdentifier(dsdIdentifiers, portalUser);
        return dsdIdentifiers;
    }

    private List<DsdIdentifier> readDsdIdentifiersFromFile(File file) {
        HeaderColumnNameTranslateMappingStrategy<DsdIdentifier> strategy = new HeaderColumnNameTranslateMappingStrategy<>();
        strategy.setType(DsdIdentifier.class);
        strategy.setColumnMapping(columnMapping);
        CsvToBean<DsdIdentifier> csvToBean = new CsvToBean<>();
        return csvToBean.parse(strategy, createCsvReaderFromFile(file));
    }

    private CSVReader createCsvReaderFromFile(File file) {
        try {
            return new CSVReader(new InputStreamReader(new FileInputStream(file)));
        } catch (FileNotFoundException e) {
            throw new ServiceException("Cannot find uploaded file to process", e);
        }
    }

    private void processDsdIdentifier(List<DsdIdentifier> dsdIdentifiers, PortalUser portalUser) throws ServiceException {
        if (CollectionUtils.isNotEmpty(dsdIdentifiers)) {
            for (DsdIdentifier d : dsdIdentifiers) {
                enrichDsdIdentifier(portalUser, d);
//                log.info(d.toCoreString());
            }
        }
    }

    private void enrichDsdIdentifier(PortalUser portalUser, DsdIdentifier dsdIdentifier) throws ServiceException {
        dsdIdentifier.setUploader(portalUser);
        dsdIdentifier.setUploadTime(new Timestamp(System.currentTimeMillis()));
        dsdIdentifier.setCompleteness(true);
        dsdIdentifier.setDeleteStatus(false);
        // validate and translate
        dsdIdentifierValidator.validate(dsdIdentifier);
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileCaption() {
        return fileCaption;
    }

    public void setFileCaption(String fileCaption) {
        this.fileCaption = fileCaption;
    }

    public void setUpload(File file) {
        this.file = file;
    }

    public void setUploadContentType(String contentType) {
        this.contentType = contentType;
    }

    public void setUploadFileName(String fileName) {
        this.fileName = fileName;
    }

    public Map<Long, String> getSavedDsdCoreIdMap() {
        return savedDsdCoreIdMap;
    }

    public void setSavedDsdCoreIdMap(Map<Long, String> savedDsdCoreIdMap) {
        this.savedDsdCoreIdMap = savedDsdCoreIdMap;
    }
}
