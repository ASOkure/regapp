package uk.ac.nesc.idsd.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "research_result", catalog = "idsd")
public class ResearchResult implements java.io.Serializable {
    private static final long serialVersionUID = -5123558209135992758L;

    private Long resultId;
    private DsdIdentifier dsdIdentifier;
    private ResearchResultCategory researchResultCategory;
    private String originalFileName;
    private String contentType;
    private String access;
    private String comment;
    private String uploader;
    private Timestamp uploadTime;
    private String path;
    private String diskFileName;

    public ResearchResult() {
    }

    public ResearchResult(Timestamp uploadTime) {
        this.uploadTime = uploadTime;
    }

    public ResearchResult(DsdIdentifier dsdIdentifier,
                          ResearchResultCategory researchResultCategory,
                          String originalFileName, String contentType, String access,
                          String comment, String uploader, Timestamp uploadTime, String path,
                          String diskFileName) {
        this.dsdIdentifier = dsdIdentifier;
        this.researchResultCategory = researchResultCategory;
        this.originalFileName = originalFileName;
        this.contentType = contentType;
        this.access = access;
        this.comment = comment;
        this.uploader = uploader;
        this.uploadTime = uploadTime;
        this.path = path;
        this.diskFileName = diskFileName;
    }

    @Id
    @GeneratedValue
    @Column(name = "result_id", unique = true, nullable = false)
    public Long getResultId() {
        return this.resultId;
    }

    public void setResultId(Long resultId) {
        this.resultId = resultId;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "register_id")
    public DsdIdentifier getDsdIdentifier() {
        return this.dsdIdentifier;
    }

    public void setDsdIdentifier(DsdIdentifier dsdIdentifier) {
        this.dsdIdentifier = dsdIdentifier;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    public ResearchResultCategory getResearchResultCategory() {
        return this.researchResultCategory;
    }

    public void setResearchResultCategory(ResearchResultCategory researchResultCategory) {
        this.researchResultCategory = researchResultCategory;
    }

    @Column(name = "original_file_name", length = 65535)
    public String getOriginalFileName() {
        return this.originalFileName;
    }

    public void setOriginalFileName(String originalFileName) {
        this.originalFileName = originalFileName;
    }

    @Column(name = "content_type", length = 100)
    public String getContentType() {
        return this.contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    @Column(name = "access")
    public String getAccess() {
        return this.access;
    }

    public void setAccess(String access) {
        this.access = access;
    }

    @Column(name = "comment", length = 65535)
    public String getComment() {
        return this.comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Column(name = "uploader", length = 65535)
    public String getUploader() {
        return this.uploader;
    }

    public void setUploader(String uploader) {
        this.uploader = uploader;
    }

    @Column(name = "upload_time", nullable = false, length = 19)
    public Timestamp getUploadTime() {
        return this.uploadTime;
    }

    public void setUploadTime(Timestamp uploadTime) {
        this.uploadTime = uploadTime;
    }

    @Column(name = "path", length = 65535)
    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Column(name = "disk_file_name", length = 65535)
    public String getDiskFileName() {
        return this.diskFileName;
    }

    public void setDiskFileName(String diskFileName) {
        this.diskFileName = diskFileName;
    }

}