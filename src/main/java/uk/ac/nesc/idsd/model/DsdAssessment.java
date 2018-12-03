package uk.ac.nesc.idsd.model;

import org.apache.commons.lang3.StringUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "dsd_assessment", catalog = "idsd")
public class DsdAssessment implements java.io.Serializable, Comparable<DsdAssessment> {
    private static final long serialVersionUID = 6246855225692158314L;

    private Long assessmentId;
    private DsdIdentifier dsdIdentifier;
    private Date assessmentDate;
    private Float phallusLength;
    private String phallusSize;
    private String urinaryMeatus;
    private String labioscrotalFusion;
    private String rightGonad;
    private String leftGonad;
    private String mullerianStructure;
    private String wolffianStructure;
    private Float ems;
    private String modifiedPrader;
    private String tannerStage;
    private String gonadectomy;
    private String gestation;
    private String freeText;

    public DsdAssessment() {
    }

    public DsdAssessment(DsdIdentifier dsdIdentifier, Date assessmentDate,
                         Float phallusLength, String phallusSize, String urinaryMeatus,
                         String labioscrotalFusion, String rightGonad, String leftGonad,
                         String mullerianStructure, String wolffianStructure, Float ems,
                         String modifiedPrader, String tannerStage, String gonadectomy,
                         String gestation) {
        this.dsdIdentifier = dsdIdentifier;
        this.assessmentDate = assessmentDate;
        this.phallusLength = phallusLength;
        this.phallusSize = phallusSize;
        this.urinaryMeatus = urinaryMeatus;
        this.labioscrotalFusion = labioscrotalFusion;
        this.rightGonad = rightGonad;
        this.leftGonad = leftGonad;
        this.mullerianStructure = mullerianStructure;
        this.wolffianStructure = wolffianStructure;
        this.ems = ems;
        this.modifiedPrader = modifiedPrader;
        this.tannerStage = tannerStage;
        this.gonadectomy = gonadectomy;
        this.gestation = gestation;
    }

    public DsdAssessment(DsdAssessment that) {
        if (that == null) {
            return;
        }
        if (that.getAssessmentDate() != null) {
            this.setAssessmentDate(that.getAssessmentDate());
        }
        if (that.getAssessmentId() != null) {
            this.setAssessmentId(that.getAssessmentId());
        }
        if (that.getDsdIdentifier() != null) {
            this.setDsdIdentifier(that.getDsdIdentifier());
        }
        if (that.getEms() != null) {
            this.setEms(that.getEms());
        }
        if (StringUtils.isNotBlank(that.getLabioscrotalFusion())) {
            this.setLabioscrotalFusion(that.getLabioscrotalFusion().trim());
        }
        if (StringUtils.isNotBlank(that.getLeftGonad())) {
            this.setLeftGonad(that.getLeftGonad().trim());
        }
        if (StringUtils.isNotBlank(that.getModifiedPrader())) {
            this.setModifiedPrader(that.getModifiedPrader().trim());
        }
        if (StringUtils.isNotBlank(that.getMullerianStructure())) {
            this.setMullerianStructure(that.getMullerianStructure().trim());
        }
        if (that.getPhallusLength() != null) {
            this.setPhallusLength(that.getPhallusLength());
        }
        if (StringUtils.isNotBlank(that.getPhallusSize())) {
            this.setPhallusSize(that.getPhallusSize().trim());
        }
        if (StringUtils.isNotBlank(that.getUrinaryMeatus())) {
            this.setUrinaryMeatus(that.getUrinaryMeatus().trim());
        }
        if (StringUtils.isNotBlank(that.getRightGonad())) {
            this.setRightGonad(that.getRightGonad().trim());
        }
        if (StringUtils.isNotBlank(that.getTannerStage())) {
            this.setTannerStage(that.getTannerStage().trim());
        }
        if (StringUtils.isNotBlank(that.getUrinaryMeatus())) {
            this.setUrinaryMeatus(that.getUrinaryMeatus().trim());
        }
        if (StringUtils.isNotBlank(that.getWolffianStructure())) {
            this.setWolffianStructure(that.getWolffianStructure().trim());
        }
        if (StringUtils.isNotBlank(that.getGonadectomy())) {
            this.setGonadectomy(that.getGonadectomy().trim());
        }
        if (StringUtils.isNotBlank(that.getRightGonad())) {
            this.setRightGonad(that.getRightGonad().trim());
        }
        if (StringUtils.isNotBlank(that.getFreeText())) {
            this.setFreeText(that.getFreeText().trim());
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "assessment_id", unique = true, nullable = false)
    public Long getAssessmentId() {
        return this.assessmentId;
    }

    public void setAssessmentId(Long assessmentId) {
        this.assessmentId = assessmentId;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "register_id")
    public DsdIdentifier getDsdIdentifier() {
        return this.dsdIdentifier;
    }

    public void setDsdIdentifier(DsdIdentifier dsdIdentifier) {
        this.dsdIdentifier = dsdIdentifier;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "assessment_date", length = 10)
    public Date getAssessmentDate() {
        return this.assessmentDate;
    }

    public void setAssessmentDate(Date assessmentDate) {
        this.assessmentDate = assessmentDate;
    }

    @Column(name = "phallus_length", precision = 12, scale = 0)
    public Float getPhallusLength() {
        return this.phallusLength;
    }

    public void setPhallusLength(Float phallusLength) {
        this.phallusLength = phallusLength;
    }

    @Column(name = "phallus_size", length = 100)
    public String getPhallusSize() {
        return this.phallusSize;
    }

    public void setPhallusSize(String phallusSize) {
        this.phallusSize = phallusSize;
    }

    @Column(name = "urinary_meatus", length = 100)
    public String getUrinaryMeatus() {
        return this.urinaryMeatus;
    }

    public void setUrinaryMeatus(String urinaryMeatus) {
        this.urinaryMeatus = urinaryMeatus;
    }

    @Column(name = "labioscrotal_fusion", length = 100)
    public String getLabioscrotalFusion() {
        return this.labioscrotalFusion;
    }

    public void setLabioscrotalFusion(String labioscrotalFusion) {
        this.labioscrotalFusion = labioscrotalFusion;
    }

    @Column(name = "right_gonad", length = 100)
    public String getRightGonad() {
        return this.rightGonad;
    }

    public void setRightGonad(String rightGonad) {
        this.rightGonad = rightGonad;
    }

    @Column(name = "left_gonad", length = 100)
    public String getLeftGonad() {
        return this.leftGonad;
    }

    public void setLeftGonad(String leftGonad) {
        this.leftGonad = leftGonad;
    }

    @Column(name = "mullerian_structure", length = 100)
    public String getMullerianStructure() {
        return this.mullerianStructure;
    }

    public void setMullerianStructure(String mullerianStructure) {
        this.mullerianStructure = mullerianStructure;
    }

    @Column(name = "wolffian_structure", length = 100)
    public String getWolffianStructure() {
        return this.wolffianStructure;
    }

    public void setWolffianStructure(String wolffianStructure) {
        this.wolffianStructure = wolffianStructure;
    }

    @Column(name = "ems", precision = 12, scale = 0)
    public Float getEms() {
        return this.ems;
    }

    public void setEms(Float ems) {
        this.ems = ems;
    }

    @Column(name = "modified_prader", length = 100)
    public String getModifiedPrader() {
        return this.modifiedPrader;
    }

    public void setModifiedPrader(String modifiedPrader) {
        this.modifiedPrader = modifiedPrader;
    }

    @Column(name = "tanner_stage", length = 10)
    public String getTannerStage() {
        return this.tannerStage;
    }

    public void setTannerStage(String tannerStage) {
        this.tannerStage = tannerStage;
    }

    @Column(name = "gonadectomy", length = 100)
    public String getGonadectomy() {
        return this.gonadectomy;
    }

    public void setGonadectomy(String gonadectomy) {
        this.gonadectomy = gonadectomy;
    }

    @Column(name = "gestation", length = 100)
    public String getGestation() {
        return this.gestation;
    }

    public void setGestation(String gestation) {
        this.gestation = gestation;
    }

    @Column(name = "free_text")
    public String getFreeText() {
        return this.freeText;
    }

    public void setFreeText(String freeText) {
        this.freeText = freeText;
    }

    @Override
    public String toString() {
        if (this == null) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        if (null != this.getAssessmentDate()) {
            stringBuilder.append("Assessment Date = ").append(this.getAssessmentDate()).append(";");
        }
        if (null != this.getPhallusLength()) {
            stringBuilder.append("Phallus Length = ").append(this.getPhallusLength()).append(";");
        }
        if (StringUtils.isNotBlank(this.getPhallusSize())) {
            stringBuilder.append("Phallus Size = ").append(this.getPhallusSize()).append(";");
        }
        if (StringUtils.isNotBlank(this.getUrinaryMeatus())) {
            stringBuilder.append("Urinary Meatus = ").append(this.getUrinaryMeatus()).append(";");
        }
        if (StringUtils.isNotBlank(this.getLabioscrotalFusion())) {
            stringBuilder.append("Labioscrotal Fusion = ").append(this.getLabioscrotalFusion()).append(";");
        }
        if (StringUtils.isNotBlank(this.getRightGonad())) {
            stringBuilder.append("Right Gonad = ").append(this.getRightGonad()).append(";");
        }
        if (StringUtils.isNotBlank(this.getLeftGonad())) {
            stringBuilder.append("Left Gonad = ").append(this.getLeftGonad()).append(";");
        }
        if (StringUtils.isNotBlank(this.getMullerianStructure())) {
            stringBuilder.append("Mullerian Structure = ").append(this.getMullerianStructure()).append(";");
        }
        if (StringUtils.isNotBlank(this.getWolffianStructure())) {
            stringBuilder.append("Wolffian Structure = ").append(this.getWolffianStructure()).append(";");
        }
        if (this.getEms() != null) {
            stringBuilder.append("EMS = ").append(this.getEms()).append(";");
        }
        if (StringUtils.isNotBlank(this.getModifiedPrader())) {
            stringBuilder.append("Modified Prader = ").append(this.getModifiedPrader()).append(";");
        }
        if (StringUtils.isNotBlank(this.getTannerStage())) {
            stringBuilder.append("Tanner Stage = ").append(this.getTannerStage()).append(";");
        }
        if (StringUtils.isNotBlank(this.getGonadectomy())) {
            stringBuilder.append("Gonadectomy = ").append(this.getGonadectomy()).append(";");
        }
        if (StringUtils.isNotBlank(this.getGestation())) {
            stringBuilder.append("Gestation = ").append(this.getGestation()).append(";");
        }
        if (StringUtils.isNotBlank(this.getFreeText())) {
            stringBuilder.append("Free Text = ").append(this.getFreeText()).append(";");
        }
        return stringBuilder.toString();
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public int compareTo(DsdAssessment that) {
        final int BEFORE = -1;
        final int AFTER = 1;

        if (that == null) {
            return BEFORE;
        }

        Comparable thisAssessment = this.getAssessmentDate();
        Comparable thatAssessment = that.getAssessmentDate();

        if (thisAssessment == null) {
            return AFTER;
        } else if (thatAssessment == null) {
            return BEFORE;
        } else {
            return thisAssessment.compareTo(thatAssessment);
        }
    }

    public String toLongString() {
        String registerId;
        if (null != dsdIdentifier) {
            registerId = ", dsdIdentifier=" + dsdIdentifier.getRegisterId();
        } else {
            registerId = ", dsdIdentifier=null";
        }
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        String assessmentDateString = null;
        if (null != assessmentDate) {
            assessmentDateString = dateFormatter.format(assessmentDate);
        }

        return "DsdAssessment{" +
                "assessmentId=" + assessmentId +
                registerId +
                ", assessmentDate=" + assessmentDateString +
                ", phallusLength=" + phallusLength +
                ", phallusSize='" + phallusSize + '\'' +
                ", urinaryMeatus='" + urinaryMeatus + '\'' +
                ", labioscrotalFusion='" + labioscrotalFusion + '\'' +
                ", rightGonad='" + rightGonad + '\'' +
                ", leftGonad='" + leftGonad + '\'' +
                ", mullerianStructure='" + mullerianStructure + '\'' +
                ", wolffianStructure='" + wolffianStructure + '\'' +
                ", ems=" + ems +
                ", modifiedPrader='" + modifiedPrader + '\'' +
                ", tannerStage='" + tannerStage + '\'' +
                ", gonadectomy='" + gonadectomy + '\'' +
                ", gestation='" + gestation + '\'' +
                ", freeText='" + freeText + '\'' +
                '}';
    }

}