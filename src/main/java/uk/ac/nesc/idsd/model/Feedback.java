package uk.ac.nesc.idsd.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "feedback", catalog = "idsd")
public class Feedback implements java.io.Serializable {
    private static final long serialVersionUID = 4674145557224918207L;

    private Long fdbkId;
    private String userId;
    private Timestamp time;
    private String fdbkTxt;
    private Long parent;

    public Feedback() {
    }

    public Feedback(Timestamp time) {
        this.time = time;
    }

    public Feedback(String userId, Timestamp time, String fdbkTxt, Long parent) {
        this.userId = userId;
        this.time = time;
        this.fdbkTxt = fdbkTxt;
        this.parent = parent;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fdbk_id", unique = true, nullable = false)
    public Long getFdbkId() {
        return this.fdbkId;
    }

    public void setFdbkId(Long fdbkId) {
        this.fdbkId = fdbkId;
    }

    @Column(name = "user_id", length = 1000)
    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Column(name = "time", nullable = false, length = 19)
    public Timestamp getTime() {
        return this.time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    @Column(name = "fdbk_txt")
    public String getFdbkTxt() {
        return this.fdbkTxt;
    }

    public void setFdbkTxt(String fdbkTxt) {
        this.fdbkTxt = fdbkTxt;
    }

    @Column(name = "parent")
    public Long getParent() {
        return this.parent;
    }

    public void setParent(Long parent) {
        this.parent = parent;
    }

}