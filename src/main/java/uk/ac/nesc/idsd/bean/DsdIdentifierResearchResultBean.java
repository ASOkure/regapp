package uk.ac.nesc.idsd.bean;

import java.sql.Blob;
import java.sql.Timestamp;

public class DsdIdentifierResearchResultBean {
    private Long resultId;
    private Long registerId;
    private String categoryName;
    private Blob pdf;
    private String comment;
    private Timestamp uploadTime;
    private String uploader;
    private String fileName;
    private boolean allow;

    public DsdIdentifierResearchResultBean() {
        super();

    }

    public Long getResultId() {
        return resultId;
    }

    public void setResultId(Long resultId) {
        this.resultId = resultId;
    }

    public Long getRegisterId() {
        return registerId;
    }

    public void setRegisterId(Long registerId) {
        this.registerId = registerId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Blob getPdf() {
        return pdf;
    }

    public void setPdf(Blob pdf) {
        this.pdf = pdf;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Timestamp getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Timestamp uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getUploader() {
        return uploader;
    }

    public void setUploader(String uploader) {
        this.uploader = uploader;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public boolean isAllow() {
        return allow;
    }

    public void setAllow(boolean allow) {
        this.allow = allow;
    }

}
