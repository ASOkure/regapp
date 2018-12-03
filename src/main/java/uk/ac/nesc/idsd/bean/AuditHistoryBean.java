package uk.ac.nesc.idsd.bean;

import java.util.Date;

public class AuditHistoryBean {
    private String registerId;
    private String action;
    private Date timestamp;
    private String username;

    public AuditHistoryBean(String registerId, String action, Date timestamp,
                            String username) {
        super();
        this.registerId = registerId;
        this.action = action;
        this.timestamp = timestamp;
        this.username = username;
    }

    public String getRegisterId() {
        return registerId;
    }

    public void setRegisterId(String registerId) {
        this.registerId = registerId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
