package uk.ac.nesc.idsd.bean;

import uk.ac.nesc.idsd.model.PortalUser;

import java.sql.Timestamp;

public class FeedbackBean {
    Long fdbkId;
    PortalUser portalUser;
    Timestamp time;
    String fdbkTxt;
    Long parentId;

    // List<FeedbackBean> parent = new ArrayList<FeedbackBean>(0);
    public FeedbackBean(Long fdbkId, PortalUser portalUser, Timestamp time, String fdbkTxt,
                        Long parentId) {
        super();
        this.fdbkId = fdbkId;
        this.portalUser = portalUser;
        this.time = time;
        this.fdbkTxt = fdbkTxt;
        this.parentId = parentId;
    }

    public Long getFdbkId() {
        return fdbkId;
    }

    public void setFdbkId(Long fdbkId) {
        this.fdbkId = fdbkId;
    }

    public PortalUser getPortalUser() {
        return portalUser;
    }

    public void setPortalUser(PortalUser portalUser) {
        this.portalUser = portalUser;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public String getFdbkTxt() {
        return fdbkTxt;
    }

    public void setFdbkTxt(String fdbkTxt) {
        this.fdbkTxt = fdbkTxt;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

}
