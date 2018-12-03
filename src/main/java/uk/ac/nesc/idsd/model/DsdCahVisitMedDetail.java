package uk.ac.nesc.idsd.model;

import uk.ac.nesc.idsd.util.Utility;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.sql.Time;

@Entity
@Table(name = "dsd_cah_visit_med_detail", catalog = "idsd", uniqueConstraints = @UniqueConstraint(columnNames = "dsd_cah_visit_id"))
public class DsdCahVisitMedDetail implements java.io.Serializable, Comparable<DsdCahVisitMedDetail> {

    private Long dsdCahVisitMedDetailId;
    private String medicineName;
    private DsdCahVisit dsdCahVisit;
    private Double dose;
    private String unit;
    private Time time;
    private String note;

    public DsdCahVisitMedDetail() {
    }

    public DsdCahVisitMedDetail(DsdCahVisit dsdCahVisit) {
        this.dsdCahVisit = dsdCahVisit;
    }

    public DsdCahVisitMedDetail(DsdCahVisit dsdCahVisit, String medicineName, Double dose, String unit, Time time, String note) {
        this.dsdCahVisit = dsdCahVisit;
        this.medicineName = medicineName;
        this.dose = dose;
        this.time = time;
        this.unit = unit;
        this.note = note;
    }

    public DsdCahVisitMedDetail(DsdCahVisitMedDetail that) {
        if (that == null) {
            return;
        }
        this.setDsdCahVisitMedDetailId(that.getDsdCahVisitMedDetailId());
        this.setMedicineName(that.getMedicineName());
        this.setDsdCahVisit(that.getDsdCahVisit());
        this.setDose(that.getDose());
        this.setUnit(Utility.trimValue(that.getUnit()));
        this.setTime(that.getTime());
        this.setNote(Utility.trimValue(that.getNote()));
    }

    @Id
    @GeneratedValue
    @Column(name = "dsd_cah_visit_med_detail_id", unique = true, nullable = false)
    public Long getDsdCahVisitMedDetailId() {
        return this.dsdCahVisitMedDetailId;
    }

    public void setDsdCahVisitMedDetailId(Long dsdCahVisitMedDetailId) {
        this.dsdCahVisitMedDetailId = dsdCahVisitMedDetailId;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dsd_cah_visit_id", unique = true, nullable = false)
    public DsdCahVisit getDsdCahVisit() {
        return this.dsdCahVisit;
    }

    public void setDsdCahVisit(DsdCahVisit dsdCahVisit) {
        this.dsdCahVisit = dsdCahVisit;
    }

    @Column(name = "medicine_name", length = 200)
    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    @Column(name = "dose", precision = 22, scale = 0)
    public Double getDose() {
        return this.dose;
    }

    public void setDose(Double dose) {
        this.dose = dose;
    }

    @Column(name = "time", length = 8)
    public Time getTime() {
        return this.time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    @Column(name = "unit", length = 45)
    public String getUnit() {
        return this.unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Column(name = "note", length = 65535)
    public String getNote() {
        return this.note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "DsdCahVisitMedDetail{" +
                "dsdCahVisitMedDetailId=" + dsdCahVisitMedDetailId +
                ", medicineName=" + medicineName +
                ", dose=" + dose +
                ", unit='" + unit + '\'' +
                ", time=" + time +
                ", note='" + note + '\'' +
                '}';
    }

    @Override
    public int compareTo(DsdCahVisitMedDetail o) {
        return getDsdCahVisitMedDetailId().compareTo(o.getDsdCahVisitMedDetailId());
    }
}