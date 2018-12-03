package uk.ac.nesc.idsd.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "dsd_cah_visit_episode", catalog = "idsd", uniqueConstraints = @UniqueConstraint(columnNames = "dsd_cah_visit_id"))
public class DsdCahVisitEpisode implements java.io.Serializable, Comparable<DsdCahVisitEpisode> {

    private Long dsdCahVisitEpisodeId;
    private DsdCahVisit dsdCahVisit;
    private Integer numberOfDays;
    private String oralSteroids;
    private String hcInjection;
    private String adrenalCrisis;
    private String emergencyAttendance;
    private String predisposingCondition;

    public DsdCahVisitEpisode() {
    }

    public DsdCahVisitEpisode(DsdCahVisit dsdCahVisit) {
        this.dsdCahVisit = dsdCahVisit;
    }

    public DsdCahVisitEpisode(DsdCahVisit dsdCahVisit, Integer numberOfDays, String emergencyAttendance) {
        this.dsdCahVisit = dsdCahVisit;
        this.numberOfDays = numberOfDays;
        this.emergencyAttendance = emergencyAttendance;
    }

    public DsdCahVisitEpisode(DsdCahVisit dsdCahVisit, Integer numberOfDays, String oralSteroids, String hcInjection, String adrenalCrisis, String emergencyAttendance, String predisposingCondition) {
        this.dsdCahVisit = dsdCahVisit;
        this.numberOfDays = numberOfDays;
        this.oralSteroids = oralSteroids;
        this.hcInjection = hcInjection;
        this.adrenalCrisis = adrenalCrisis;
        this.emergencyAttendance = emergencyAttendance;
        this.predisposingCondition = predisposingCondition;
    }

    public DsdCahVisitEpisode(DsdCahVisitEpisode that) {
        if (that == null) {
            return;
        }
        this.setDsdCahVisitEpisodeId(that.getDsdCahVisitEpisodeId());
        this.setDsdCahVisit(that.getDsdCahVisit());
        this.setNumberOfDays(that.getNumberOfDays());
        this.setOralSteroids(that.getOralSteroids());
        this.setHcInjection(that.getHcInjection());
        this.setAdrenalCrisis(that.getAdrenalCrisis());
        this.setEmergencyAttendance(that.getEmergencyAttendance());
        this.setPredisposingCondition(that.getPredisposingCondition());
    }

    @Id
    @GeneratedValue
    @Column(name = "dsd_cah_visit_episode_id", unique = true, nullable = false)
    public Long getDsdCahVisitEpisodeId() {
        return this.dsdCahVisitEpisodeId;
    }

    public void setDsdCahVisitEpisodeId(Long dsdCahVisitEpisodeId) {
        this.dsdCahVisitEpisodeId = dsdCahVisitEpisodeId;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dsd_cah_visit_id", unique = true, nullable = false)
    public DsdCahVisit getDsdCahVisit() {
        return this.dsdCahVisit;
    }

    public void setDsdCahVisit(DsdCahVisit dsdCahVisit) {
        this.dsdCahVisit = dsdCahVisit;
    }

    @Column(name = "number_of_days")
    public Integer getNumberOfDays() {
        return this.numberOfDays;
    }

    public void setNumberOfDays(Integer numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

    @Column(name = "oral_steroids", length = 45)
    public String getOralSteroids() {
        return oralSteroids;
    }

    public void setOralSteroids(String oralSteroids) {
        this.oralSteroids = oralSteroids;
    }

    @Column(name = "hc_injection", length = 45)
    public String getHcInjection() {
        return hcInjection;
    }

    public void setHcInjection(String hcInjection) {
        this.hcInjection = hcInjection;
    }

    @Column(name = "adrenal_crisis", length = 45)
    public String getAdrenalCrisis() {
        return adrenalCrisis;
    }

    public void setAdrenalCrisis(String adrenalCrisis) {
        this.adrenalCrisis = adrenalCrisis;
    }

    @Column(name = "emergency_attendance", length = 200)
    public String getEmergencyAttendance() {
        return emergencyAttendance;
    }

    public void setEmergencyAttendance(String emergencyAttendance) {
        this.emergencyAttendance = emergencyAttendance;
    }

    @Column(name = "predisposing_condition", length = 200)
    public String getPredisposingCondition() {
        return predisposingCondition;
    }

    public void setPredisposingCondition(String predisposingCondition) {
        this.predisposingCondition = predisposingCondition;
    }

    @Override
    public String toString() {
        return "DsdCahVisitEpisode{" +
                "dsdCahVisitEpisodeId=" + dsdCahVisitEpisodeId +
                ", numberOfDays=" + numberOfDays +
                ", oralSteroids='" + oralSteroids + '\'' +
                ", hcInjection='" + hcInjection + '\'' +
                ", adrenalCrisis='" + adrenalCrisis + '\'' +
                ", emergencyAttendance='" + emergencyAttendance + '\'' +
                ", predisposingCondition='" + predisposingCondition + '\'' +
                '}';
    }

    @Override
    public int compareTo(DsdCahVisitEpisode o) {
        return getDsdCahVisitEpisodeId().compareTo(o.getDsdCahVisitEpisodeId());
    }
}