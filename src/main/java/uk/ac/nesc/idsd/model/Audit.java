package uk.ac.nesc.idsd.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "audit", catalog = "idsd")
public class Audit implements java.io.Serializable {
    private static final long serialVersionUID = -746840161864838990L;

    private Long logId;
    private String userId;
    private Timestamp timestamp;
    private String action;
    private String recordId;
    private String param;
    private String ip;

    public Audit() {
    }

    public Audit(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public Audit(String userId, Timestamp timestamp, String action,
                 String recordId, String param, String ip) {
        this.userId = userId;
        this.timestamp = timestamp;
        this.action = action;
        this.recordId = recordId;
        this.param = param;
        this.ip = ip;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "log_id", unique = true, nullable = false)
    public Long getLogId() {
        return this.logId;
    }

    public void setLogId(Long logId) {
        this.logId = logId;
    }

    @Column(name = "user_id", length = 100)
    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Column(name = "timestamp", nullable = false, length = 19)
    public Timestamp getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    @Column(name = "action", length = 100)
    public String getAction() {
        return this.action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    @Column(name = "record_id")
    public String getRecordId() {
        return this.recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    @Column(name = "param")
    public String getParam() {
        return this.param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    @Column(name = "ip", length = 100)
    public String getIp() {
        return this.ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public String toString() {
        return "Audit{" +
                "logId=" + logId +
                ", userId='" + userId + '\'' +
                ", timestamp=" + timestamp +
                ", action='" + action + '\'' +
                ", recordId='" + recordId + '\'' +
                ", param='" + param + '\'' +
                ", ip='" + ip + '\'' +
                '}';
    }
}