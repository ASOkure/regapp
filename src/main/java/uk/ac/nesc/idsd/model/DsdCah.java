package uk.ac.nesc.idsd.model;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.BatchSize;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

@Entity
@Table(name = "dsd_cah", catalog = "idsd")
@BatchSize(size = 10)
public class DsdCah implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    private Long cahId;
    private DsdIdentifier dsdIdentifier;
    private String prenatalDiagnosis;
    private String prenatalDexamethasoneTherapy;
    private String gestationalAge;
    private Double fatherHeight;
    private Double motherHeight;
    private Double targetHeight;
    private String praderStageAtFirstPresentation;
    private String swCrisisAtPresentation;
    private String adrenalCrisesAfterFirstPresentation;
    //    private String currentGcReplacement;
    private String currentFcTreatment;
    private String currentSaltReplacement;
    private String cahFreeText;
    private Set<DsdCahVisit> dsdCahVisits = new HashSet<DsdCahVisit>(0);

    public DsdCah() {
    }

    public DsdCah(DsdIdentifier dsdIdentifier,
                  String prenatalDiagnosis,
                  Double fatherHeight, Double motherHeight, Double targetHeight,
                  String praderStageAtFirstPresentation,
                  String swCrisisAtPresentation,
                  String adrenalCrisesAfterFirstPresentation, String gestationalAge,
//                  String currentGcReplacement,
                  String currentFcTreatment,
                  String currentSaltReplacement, String cahFreeText,
                  Set<DsdCahVisit> dsdCahVisits) {
        this.dsdIdentifier = dsdIdentifier;
        this.prenatalDiagnosis = prenatalDiagnosis;
        this.fatherHeight = fatherHeight;
        this.motherHeight = motherHeight;
        this.targetHeight = targetHeight;
        this.praderStageAtFirstPresentation = praderStageAtFirstPresentation;
        this.swCrisisAtPresentation = swCrisisAtPresentation;
        this.adrenalCrisesAfterFirstPresentation = adrenalCrisesAfterFirstPresentation;
        this.gestationalAge = gestationalAge;
//        this.currentGcReplacement = currentGcReplacement;
        this.currentFcTreatment = currentFcTreatment;
        this.currentSaltReplacement = currentSaltReplacement;
        this.cahFreeText = cahFreeText;
        this.dsdCahVisits = dsdCahVisits;
    }

    public DsdCah(DsdCah that) {
        if (that == null) {
            return;
        }
        if (null != that.getCahId()) {
            this.setCahId(that.getCahId());
        }
        if (null != that.getDsdIdentifier()) {
            this.setDsdIdentifier(that.getDsdIdentifier());
        }
        if (StringUtils.isNotBlank(that.getPrenatalDiagnosis())) {
            this.setPrenatalDiagnosis(that.getPrenatalDiagnosis().trim());
        }
        if (StringUtils.isNotBlank(that.getPrenatalDexamethasoneTherapy())) {
            this.setPrenatalDexamethasoneTherapy(that.getPrenatalDexamethasoneTherapy());
        }
        if (StringUtils.isNotBlank(that.getGestationalAge())) {
            this.setGestationalAge(that.getGestationalAge().trim());
        }
        if (that.getFatherHeight() != null) {
            this.setFatherHeight(that.getFatherHeight());
        }
        if (that.getMotherHeight() != null) {
            this.setMotherHeight(that.getMotherHeight());
        }
        if (that.getTargetHeight() != null) {
            this.setTargetHeight(that.getTargetHeight());
        }
        if (StringUtils.isNotBlank(that.getPraderStageAtFirstPresentation())) {
            this.setPraderStageAtFirstPresentation(that.getPraderStageAtFirstPresentation().trim());
        }
        if (StringUtils.isNotBlank(that.getSwCrisisAtPresentation())) {
            this.setSwCrisisAtPresentation(that.getSwCrisisAtPresentation().trim());
        }
        if (StringUtils.isNotBlank(that.getAdrenalCrisesAfterFirstPresentation())) {
            this.setAdrenalCrisesAfterFirstPresentation(that.getAdrenalCrisesAfterFirstPresentation().trim());
        }
//        if (StringUtils.isNotBlank(that.getCurrentGcReplacement())) {
//            this.setCurrentGcReplacement(that.getCurrentGcReplacement().trim());
//        }
        if (StringUtils.isNotBlank(that.getCurrentFcTreatment())) {
            this.setCurrentFcTreatment(that.getCurrentFcTreatment().trim());
        }
        if (StringUtils.isNotBlank(that.getCurrentSaltReplacement())) {
            this.setCurrentSaltReplacement(that.getCurrentSaltReplacement().trim());
        }
        if (StringUtils.isNotBlank(that.getCahFreeText())) {
            this.setCahFreeText(that.getCahFreeText().trim());
        }
        Set<DsdCahVisit> dsdCahVisits = new TreeSet<DsdCahVisit>();
        if (CollectionUtils.isNotEmpty(that.getDsdCahVisits())) {
            for (DsdCahVisit visit : that.getDsdCahVisits()) {
                dsdCahVisits.add(new DsdCahVisit(visit));
            }
            this.setDsdCahVisits(dsdCahVisits);
        }
    }

    @Id
    @GeneratedValue
    @Column(name = "cah_id", unique = true, nullable = false)
    public Long getCahId() {
        return this.cahId;
    }

    public void setCahId(Long cahId) {
        this.cahId = cahId;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "register_id")
    public DsdIdentifier getDsdIdentifier() {
        return this.dsdIdentifier;
    }

    public void setDsdIdentifier(DsdIdentifier dsdIdentifier) {
        this.dsdIdentifier = dsdIdentifier;
    }

    @Column(name = "prenatal_diagnosis", length = 45)
    public String getPrenatalDiagnosis() {
        return prenatalDiagnosis;
    }

    public void setPrenatalDiagnosis(String prenatalDiagnosis) {
        this.prenatalDiagnosis = prenatalDiagnosis;
    }

    @Column(name = "prenatal_dexamethasone_therapy", length = 45)
    public String getPrenatalDexamethasoneTherapy() {
        return prenatalDexamethasoneTherapy;
    }

    public void setPrenatalDexamethasoneTherapy(String prenatalDexamethasoneTherapy) {
        this.prenatalDexamethasoneTherapy = prenatalDexamethasoneTherapy;
    }

    @Column(name = "gestational_age", length = 100)
    public String getGestationalAge() {
        return this.gestationalAge;
    }

    public void setGestationalAge(String gestationalAge) {
        this.gestationalAge = gestationalAge;
    }

    @Column(name = "prader_stage_at_first_presentation", length = 45)
    public String getPraderStageAtFirstPresentation() {
        return this.praderStageAtFirstPresentation;
    }

    public void setPraderStageAtFirstPresentation(
            String praderStageAtFirstPresentation) {
        this.praderStageAtFirstPresentation = praderStageAtFirstPresentation;
    }

    @Column(name = "sw_crisis_at_presentation", length = 45)
    public String getSwCrisisAtPresentation() {
        return this.swCrisisAtPresentation;
    }

    public void setSwCrisisAtPresentation(String swCrisisAtPresentation) {
        this.swCrisisAtPresentation = swCrisisAtPresentation;
    }

    @Column(name = "adrenal_crises_after_first_presentation", length = 45)
    public String getAdrenalCrisesAfterFirstPresentation() {
        return this.adrenalCrisesAfterFirstPresentation;
    }

    public void setAdrenalCrisesAfterFirstPresentation(
            String adrenalCrisesAfterFirstPresentation) {
        this.adrenalCrisesAfterFirstPresentation = adrenalCrisesAfterFirstPresentation;
    }

    @Column(name = "current_fc_treatment", length = 45)
    public String getCurrentFcTreatment() {
        return this.currentFcTreatment;
    }

    public void setCurrentFcTreatment(String currentFcTreatment) {
        this.currentFcTreatment = currentFcTreatment;
    }

    @Column(name = "current_salt_replacement", length = 45)
    public String getCurrentSaltReplacement() {
        return this.currentSaltReplacement;
    }

    public void setCurrentSaltReplacement(String currentSaltReplacement) {
        this.currentSaltReplacement = currentSaltReplacement;
    }

    @Column(name = "cah_free_text", length = 65535)
    public String getCahFreeText() {
        return this.cahFreeText;
    }

    public void setCahFreeText(String cahFreeText) {
        this.cahFreeText = cahFreeText;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "dsdCah")
    @OrderBy("dsdCahVisitId ASC")
    @BatchSize(size = 100)
//    @Sort(type = SortType.NATURAL)
    public Set<DsdCahVisit> getDsdCahVisits() {
        return this.dsdCahVisits;
    }

    public void setDsdCahVisits(Set<DsdCahVisit> dsdCahVisits) {
        this.dsdCahVisits = dsdCahVisits;
    }

    @Column(name = "father_height", precision = 12, scale = 2)
    public Double getFatherHeight() {
        return fatherHeight;
    }

    public void setFatherHeight(Double fatherHeight) {
        this.fatherHeight = fatherHeight;
    }

    @Column(name = "mother_height", precision = 12, scale = 2)
    public Double getMotherHeight() {
        return motherHeight;
    }

    public void setMotherHeight(Double motherHeight) {
        this.motherHeight = motherHeight;
    }

    @Column(name = "target_height", precision = 12, scale = 2)
    public Double getTargetHeight() {
        return targetHeight;
    }

    public void setTargetHeight(Double targetHeight) {
        this.targetHeight = targetHeight;
    }

    @Transient
    public boolean isEmpty() {
        return (null != getPrenatalDiagnosis() || null != getGestationalAge()
                || null != getPraderStageAtFirstPresentation() || null != getSwCrisisAtPresentation()
                || null != getAdrenalCrisesAfterFirstPresentation()
//                || null != getCurrentFcTreatment() || null != getCurrentGcReplacement()
                || null != getCurrentSaltReplacement()
                || null != getCahFreeText());
    }

    @Override
    public String toString() {
        if (this == null) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        if (null != this.getCahId()) {
            stringBuilder.append("cahId=").append(this.getCahId()).append(";");
        }
        if (null != this.getDsdIdentifier() && null != this.getDsdIdentifier().getRegisterId()) {
            stringBuilder.append("registerId=").append(this.getDsdIdentifier().getRegisterId()).append(";");
        }
        if (StringUtils.isNotBlank(this.getPrenatalDiagnosis())) {
            stringBuilder.append("prenatalDiagnosis=").append(this.getPrenatalDiagnosis()).append(";");
        }
        if (StringUtils.isNotBlank(this.getPrenatalDexamethasoneTherapy())) {
            stringBuilder.append("prenatalDexamethasoneTherapy=").append(this.getPrenatalDexamethasoneTherapy()).append(";");
        }
        if (StringUtils.isNotBlank(this.getGestationalAge())) {
            stringBuilder.append("gestationalAge=").append(this.getGestationalAge()).append(";");
        }
        if (StringUtils.isNotBlank(this.getPraderStageAtFirstPresentation())) {
            stringBuilder.append("praderStageAtFirstPresentation=").append(this.getPraderStageAtFirstPresentation()).append(";");
        }
        if (StringUtils.isNotBlank(this.getSwCrisisAtPresentation())) {
            stringBuilder.append("swCrisisAtPresentation=").append(this.getSwCrisisAtPresentation()).append(";");
        }
        if (StringUtils.isNotBlank(this.getAdrenalCrisesAfterFirstPresentation())) {
            stringBuilder.append("adrenalCrisesAfterFirstPresentation=").append(this.getAdrenalCrisesAfterFirstPresentation()).append(";");
        }
        if (StringUtils.isNotBlank(this.getCurrentFcTreatment())) {
            stringBuilder.append("currentFcTreatment=").append(this.getCurrentFcTreatment()).append(";");
        }
        if (StringUtils.isNotBlank(this.getCurrentSaltReplacement())) {
            stringBuilder.append("currentSaltReplacement=").append(this.getCurrentSaltReplacement()).append(";");
        }
        if (StringUtils.isNotBlank(this.getCahFreeText())) {
            stringBuilder.append("cahFreeText=").append(this.getCahFreeText()).append(";");
        }
        if (null != this.getFatherHeight()) {
            stringBuilder.append("fatherHeight=").append(this.getFatherHeight()).append(";");
        }
        if (null != this.getMotherHeight()) {
            stringBuilder.append("motherHeight=").append(this.getMotherHeight()).append(";");
        }
        if (null != this.getTargetHeight()) {
            stringBuilder.append("targetHeight=").append(this.getTargetHeight()).append(";");
        }
        if (null != this.getDsdCahVisits() && CollectionUtils.isNotEmpty(this.getDsdCahVisits())) {
            stringBuilder.append("dsdCahVisits=").append(this.getDsdCahVisits()).append(";");
        }

        return stringBuilder.toString();
    }

    public String toLongString() {
        String registerId;
        if (null != dsdIdentifier) {
            registerId = ", dsdIdentifier=" + dsdIdentifier.getRegisterId();
        } else {
            registerId = ", dsdIdentifier=null";
        }
        return "DsdCah{" +
                "cahId=" + cahId +
                registerId +
                ", prenatalDiagnosis=" + prenatalDiagnosis +
                ", prenatalDexamethasoneTherapy=" + prenatalDexamethasoneTherapy +
                ", gestationalAge=" + gestationalAge +
                ", fatherHeight=" + fatherHeight +
                ", motherHeight=" + motherHeight +
                ", targetHeight=" + targetHeight +
                ", praderStageAtFirstPresentation=" + praderStageAtFirstPresentation +
                ", swCrisisAtPresentation=" + swCrisisAtPresentation +
                ", adrenalCrisesAfterFirstPresentation=" + adrenalCrisesAfterFirstPresentation +
//                ", currentGcReplacement='" + currentGcReplacement + '\'' +
                ", currentFcTreatment='" + currentFcTreatment + '\'' +
                ", currentSaltReplacement=" + currentSaltReplacement +
                ", cahFreeText=" + cahFreeText +
                ", dsdCahVisits=" + dsdCahVisits +
                "}";
    }
}