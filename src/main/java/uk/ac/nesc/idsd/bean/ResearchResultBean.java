package uk.ac.nesc.idsd.bean;

import uk.ac.nesc.idsd.model.DsdIdentifier;
import uk.ac.nesc.idsd.model.ResearchResult;
import uk.ac.nesc.idsd.model.ResearchResultCategory;

import java.sql.Blob;
import java.sql.Timestamp;
import java.util.List;

public class ResearchResultBean {

    private Long resultId;
    private DsdIdentifier dsdIdentifier;
    private Long registerId;
    private ResearchResultCategory researchResultCategory;
    private String categoryName;
    private String comment;
    private Blob pdf;
    private List<String> access;
    private String uploader;
    private Timestamp uploadTime;
    private String fileName;
    private String contentType;

    public ResearchResultBean() {
        super();
    }

    public ResearchResultBean(ResearchResult researchResult, List<String> access) {
        super();
        this.resultId = researchResult.getResultId();
        this.dsdIdentifier = researchResult.getDsdIdentifier();
        this.registerId = this.dsdIdentifier.getRegisterId();
        this.researchResultCategory = researchResult.getResearchResultCategory();
        this.categoryName = this.researchResultCategory.getName();
        this.comment = researchResult.getComment();
        this.access = access;
        this.uploader = researchResult.getUploader();
        this.uploadTime = researchResult.getUploadTime();
        this.fileName = researchResult.getOriginalFileName();
        this.contentType = researchResult.getContentType();
    }

    public Long getResultId() {
        return resultId;
    }

    public void setResultId(Long resultId) {
        this.resultId = resultId;
    }

    public DsdIdentifier getDsdIdentifier() {
        return dsdIdentifier;
    }

    public void setDsdIdentifier(DsdIdentifier dsdIdentifier) {
        this.dsdIdentifier = dsdIdentifier;
    }

    public Long getRegisterId() {
        return registerId;
    }

    public void setRegisterId(Long registerId) {
        this.registerId = registerId;
    }

    public ResearchResultCategory getResearchResultCategory() {
        return researchResultCategory;
    }

    public void setResearchResultCategory(
            ResearchResultCategory researchResultCategory) {
        this.researchResultCategory = researchResultCategory;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Blob getPdf() {
        return pdf;
    }

    public void setPdf(Blob pdf) {
        this.pdf = pdf;
    }

    public List<String> getAccess() {
        return access;
    }

    public void setAccess(List<String> access) {
        this.access = access;
    }

    public String getUploader() {
        return uploader;
    }

    public void setUploader(String uploader) {
        this.uploader = uploader;
    }

    public Timestamp getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Timestamp uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

}
