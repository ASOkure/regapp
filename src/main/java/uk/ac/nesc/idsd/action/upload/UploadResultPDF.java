package uk.ac.nesc.idsd.action.upload;

import org.apache.commons.io.FileUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import uk.ac.nesc.idsd.action.AbstractAction;
import uk.ac.nesc.idsd.bean.ResearchResultBean;
import uk.ac.nesc.idsd.model.PortalUser;
import uk.ac.nesc.idsd.model.ResearchResult;
import uk.ac.nesc.idsd.model.ResearchResultCategory;
import uk.ac.nesc.idsd.security.Authorization;
import uk.ac.nesc.idsd.service.AuditService;
import uk.ac.nesc.idsd.service.DsdIdentifierService;
import uk.ac.nesc.idsd.service.ResearchResultManager;
import uk.ac.nesc.idsd.service.UserService;
import uk.ac.nesc.idsd.service.exception.DeleteDiskFileException;
import uk.ac.nesc.idsd.service.exception.ServiceException;
import uk.ac.nesc.idsd.util.Utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UploadResultPDF extends AbstractAction {
    private static final long serialVersionUID = 1237775053309839984L;

    private File upload;// The actual file
    private String uploadContentType; // The content type of the file
    private String uploadFileName; // The uploaded file name
    private String fileCaption;// The caption of the file entered by user

    private List<PortalUser> portalUsers = new ArrayList<PortalUser>();

    private ResearchResultManager researchResultManager;
    private UserService userService;
    private DsdIdentifierService dsdIdentifierService;
    private AuditService auditService;

    private List<ResearchResultBean> myUploads = new ArrayList<ResearchResultBean>();
    private List<ResearchResultBean> othersUploads = new ArrayList<ResearchResultBean>();

    private ResearchResult researchResult = new ResearchResult();
    private Integer categoryId;

    private List<String> registerIds = new ArrayList<String>();
    private List<ResearchResultCategory> categories = new ArrayList<ResearchResultCategory>();
    private long pdfId;
    private InputStream fileInputStream;

    public static void main(String[] args) {
        String userId = "K39TOrbSRe6iPe5Km3FHe4md6m4=@nesc.gla.ac.uk";
        String fullFileName = "/home/jiang/idsd/temp/" + userId + "_" + (new Date()).toString();
        log.info(fullFileName);
    }

    public void prepare() {
        try {
            this.portalUsers = this.userService.getAllButCurrentPortalUser(Utility.getLoginUserName());
        } catch (ServiceException e) {
            log.error("Could not get username from the session in Upload PDF Action!", e);
        }
        this.registerIds = this.dsdIdentifierService.getRegisterIds();
        this.categories = this.researchResultManager.getAllCategories();
    }

    public String uploadResultPDFView() {
        return SUCCESS;
    }

    
    //uploadResultPDF
    
    @PreAuthorize("hasAnyRole('ROLE_CONTRIBUTOR','ROLE_LOCAL_CONTRIBUTOR','ROLE_RESEARCHER','ROLE_AUDITOR','ROLE_SUPERVISOR')")
    public String uploadResultPDF() {
        if (this.upload == null) {
            log.info("The upload object is null");
            return INPUT;
        }
        String path = Utility.getFileStorageLocation();
        researchResult.setOriginalFileName(uploadFileName);
        researchResult.setContentType(this.uploadContentType);
        researchResult.setPath(path);
        String loginUserName = null;
        try {
            loginUserName = Utility.getLoginUserName();
        } catch (ServiceException e) {
            log.error("Error while retrieving current logged in user", e);
            this.addActionError(e.getMessage());
        }
        researchResult.setUploader(loginUserName);
        researchResult.setUploadTime(new Timestamp(System.currentTimeMillis()));
        researchResult.setResearchResultCategory(researchResultManager.getCategory(categoryId));
        researchResultManager.saveResultPDF(researchResult);
        log.info("research result is created in database, with ID: " + researchResult.getResultId());
        String diskFileName = researchResult.getResultId() + "_" + uploadFileName;
        File diskFile;

        if (!upload.exists()) {
            addActionError("The file you are trying to upload is empty, ");
            return INPUT;
        }
        
        
        	try {
            diskFile = new File(path + File.separator + diskFileName);
            FileUtils.copyFile(upload, diskFile);
        } catch (Exception e) {
            addActionError("File upload is not successful. Cannot store the file. Please try again or contact the I-DSD System Administrator. ");
            log.error("File upload failed for file: " + uploadFileName, e);
            try {
                this.researchResultManager.deleteResultPDF(researchResult);
            } catch (Exception e1) {
                addActionError("Error handling temp file with name: " + uploadFileName);
                log.error("Error handling temp file with name: " + uploadFileName, e1);
            }
            return INPUT;
        }
        log.info("Uploaded file has been copied to repository. ");

        researchResult.setDiskFileName(diskFileName);
        researchResultManager.updateResultPDF(researchResult);

        log.info("Resave the research result db record to update the file name");

        try {
            this.auditService.logResearchResultFileUpload(loginUserName, Utility.getLoginUserIp(), researchResult);
        } catch (Exception e) {
            addActionError("Error when saving auditing record for file: " + uploadFileName);
            log.error("Research Result file upload auditing failure for fileName = " + uploadFileName, e);
        }
        log.info("Added audit record for this file upload action. ");
        return SUCCESS;
    }

    @PreAuthorize("hasAnyRole('ROLE_CONTRIBUTOR','ROLE_LOCAL_CONTRIBUTOR','ROLE_RESEARCHER','ROLE_AUDITOR','ROLE_SUPERVISOR')")
    public String viewMyUploads() {
        try {
            this.myUploads = this.researchResultManager.viewMyUploads(Utility.getLoginUserName());
        } catch (ServiceException e) {
            log.error("Error while retrieving my uploads for current logged in user: " + Utility.getLoginUserName(), e);
            this.addActionError(e.getMessage());
        }
        return SUCCESS;
    }

    @PreAuthorize("hasAnyRole('ROLE_CONTRIBUTOR','ROLE_LOCAL_CONTRIBUTOR','ROLE_RESEARCHER','ROLE_AUDITOR','ROLE_SUPERVISOR')")
    public String viewOthersResultPDF() {
        try {
            this.othersUploads = this.researchResultManager.viewOthersResultPDF(Utility.getLoginUserName());
        } catch (ServiceException e) {
            log.error("Error while retrieving others' uploaded files for current logged in user: " + Utility.getLoginUserName(), e);
            this.addActionError(e.getMessage());
        }
        return SUCCESS;
    }

    
    // downloadPdf
    @PreAuthorize("hasAnyRole('ROLE_CONTRIBUTOR','ROLE_LOCAL_CONTRIBUTOR','ROLE_RESEARCHER','ROLE_AUDITOR','ROLE_SUPERVISOR')")
    public String downloadPdf() {
        log.info("Download PDF ID is " + pdfId);
        if (pdfId == 0) {
            return ERROR;
        }

        ResearchResult rr = this.researchResultManager.getResult(pdfId);
        log.info("Found research result by file ID");

        this.uploadContentType = rr.getContentType();
        this.uploadFileName = rr.getOriginalFileName();

        String loginUserName = null;
        try {
            loginUserName = Utility.getLoginUserName();
        } catch (ServiceException e) {
            log.error("Error while retrieving logged in user", e);
            this.addActionError(e.getMessage());
        }
        if (!Authorization.authorizeFileDownload(loginUserName, rr.getAccess(), rr.getUploader(), rr.getDsdIdentifier().getUploader().getUsername())) {
            return AUTHZ;
        }

        log.info("Passed authen");
        File resultFile;
        try {
            resultFile = new File(rr.getPath() + File.separator + rr.getDiskFileName());
            this.fileInputStream = new FileInputStream(resultFile);
        } catch (Exception e) {
            addActionError("File download is not successful. Please try again or contact the EuroDSD System Administrator. ");
            log.error("File download is not successful. Please try again or contact the EuroDSD System Administrator. ", e);
            return INPUT;
        }
        log.info("Found file, and put in fileInputStream");

        try {
            this.auditService.logResearchResultFileDownload(loginUserName, Utility.getLoginUserIp(), rr);
        } catch (Exception e) {
            this.addActionError("Error when saving auditing record for PDF downloads with PDF ID: " + pdfId);
            log.error("Research Result file download auditing failure with pdfId = " + pdfId, e);
        }
        log.info("Logged into audit");
        return SUCCESS;
    }
    
    // remove uploaded file

    @PreAuthorize("hasAnyRole('ROLE_CONTRIBUTOR','ROLE_LOCAL_CONTRIBUTOR','ROLE_RESEARCHER','ROLE_AUDITOR','ROLE_SUPERVISOR')")
    public String removeUpload() {
        log.info("Deleting research result ID: " + pdfId);
        ResearchResult rr = this.researchResultManager.getResult(pdfId);
        try {
            this.researchResultManager.deleteResultPDF(rr);
        } catch (DeleteDiskFileException e) {
            log.error("Error while deleting uploaded result file for pdfId: " + pdfId, e);
            addActionError("Research result file does not exist! ");
            return INPUT;
        }

        try {
            this.auditService.logResearchResultFileDelete(Utility.getLoginUserName(), Utility.getLoginUserIp(), rr);
        } catch (Exception e) {
            addActionError("Error when saving auditing record for PDF ID: " + pdfId);
            log.error("Research Result file removal auditing failure for pdfId = " + pdfId, e);
        }

        return SUCCESS;
    }

    public File getUpload() {
        return upload;
    }

    public void setUpload(File upload) {
        this.upload = upload;
    }

    public String getUploadContentType() {
        return uploadContentType;
    }

    public void setUploadContentType(String uploadContentType) {
        this.uploadContentType = uploadContentType;
    }

    public String getUploadFileName() {
        return uploadFileName;
    }

    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

    public String getFileCaption() {
        return fileCaption;
    }

    public void setFileCaption(String fileCaption) {
        this.fileCaption = fileCaption;
    }

    public List<PortalUser> getPortalUsers() {
        return portalUsers;
    }

    public void setPortalUsers(List<PortalUser> portalUsers) {
        this.portalUsers = portalUsers;
    }

    public void setResearchResultManager(
            ResearchResultManager researchResultManager) {
        this.researchResultManager = researchResultManager;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void setDsdIdentifierService(DsdIdentifierService dsdIdentifierService) {
        this.dsdIdentifierService = dsdIdentifierService;
    }

    public List<ResearchResultBean> getMyUploads() {
        return myUploads;
    }

    public void setMyUploads(List<ResearchResultBean> myUploads) {
        this.myUploads = myUploads;
    }

    public List<ResearchResultBean> getOthersUploads() {
        return othersUploads;
    }

    public void setOthersUploads(List<ResearchResultBean> othersUploads) {
        this.othersUploads = othersUploads;
    }

    /**
     * @return the pdfId
     */
    public long getPdfId() {
        return pdfId;
    }

    /**
     * @param pdfId the pdfId to set
     */
    public void setPdfId(long pdfId) {
        this.pdfId = pdfId;
    }

    /**
     * @return the fileInputStream
     */
    public InputStream getFileInputStream() {
        return fileInputStream;
    }

    /**
     * @param fileInputStream the fileInputStream to set
     */
    public void setFileInputStream(InputStream fileInputStream) {
        this.fileInputStream = fileInputStream;
    }

    public List<String> getRegisterIds() {
        return registerIds;
    }

    public void setRegisterIds(List<String> registerIds) {
        this.registerIds = registerIds;
    }

    public List<ResearchResultCategory> getCategories() {
        return categories;
    }

    public void setCategories(List<ResearchResultCategory> categories) {
        this.categories = categories;
    }

    /**
     * @return the researchResult
     */
    public ResearchResult getResearchResult() {
        return researchResult;
    }

    /**
     * @param researchResult the researchResult to set
     */
    public void setResearchResult(ResearchResult researchResult) {
        this.researchResult = researchResult;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public void setAuditService(AuditService auditService) {
        this.auditService = auditService;
    }

}
