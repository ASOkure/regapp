package uk.ac.nesc.idsd.model;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import uk.ac.nesc.idsd.util.Utility;
import uk.ac.nesc.idsd.util.comparator.DsdGeneScreenedComparator;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

@Entity
@Table(name = "dsd_gene_analysis", catalog = "idsd")
public class DsdGeneAnalysis implements java.io.Serializable {
    private static final long serialVersionUID = -7754563929650845234L;

    private Long registerId;
    private DsdIdentifier dsdIdentifier;
    private String geneticAnalysis;
    private String singleGeneAnalysis;
    private String chromosomalRearrangement;
    private String chromosomalRearrangementDetail;
    private String cgh;
    private String cghDetail;
    private String functionalStudy;
    private String functionalStudyDetail;
    private String publishedCase;
    private String publishDetail;
    private String furtherStudies;
    private Set<DsdGeneScreened> dsdGeneScreeneds = new HashSet<DsdGeneScreened>(0);

    public DsdGeneAnalysis() {
    }

    public DsdGeneAnalysis(DsdIdentifier dsdIdentifier) {
        this.dsdIdentifier = dsdIdentifier;
    }

    public DsdGeneAnalysis(DsdIdentifier dsdIdentifier, String geneticAnalysis,
                           String singleGeneAnalysis, String chromosomalRearrangement,
                           String chromosomalRearrangementDetail, String cgh,
                           String cghDetail, String functionalStudy,
                           String functionalStudyDetail, String publishedCase,
                           String publishDetail, String furtherStudies,
                           Set<DsdGeneScreened> dsdGeneScreeneds) {
        this.dsdIdentifier = dsdIdentifier;
        this.geneticAnalysis = geneticAnalysis;
        this.singleGeneAnalysis = singleGeneAnalysis;
        this.chromosomalRearrangement = chromosomalRearrangement;
        this.chromosomalRearrangementDetail = chromosomalRearrangementDetail;
        this.cgh = cgh;
        this.cghDetail = cghDetail;
        this.functionalStudy = functionalStudy;
        this.functionalStudyDetail = functionalStudyDetail;
        this.publishedCase = publishedCase;
        this.publishDetail = publishDetail;
        this.furtherStudies = furtherStudies;
        this.dsdGeneScreeneds = dsdGeneScreeneds;
    }

    public DsdGeneAnalysis(DsdGeneAnalysis that) {
        if (that == null) {
            return;
        }
        this.setCgh(Utility.trimValue(that.getCgh()));
        this.setCghDetail(Utility.trimValue(that.getCghDetail()));
        this.setChromosomalRearrangement(Utility.trimValue(that.getChromosomalRearrangement()));
        this.setChromosomalRearrangementDetail(Utility.trimValue(that.getChromosomalRearrangementDetail()));
        this.setDsdIdentifier(Utility.trimValue(that.getDsdIdentifier()));
        this.setFunctionalStudy(Utility.trimValue(that.getFunctionalStudy()));
        this.setFunctionalStudyDetail(Utility.trimValue(that.getFunctionalStudyDetail()));
        this.setFurtherStudies(Utility.trimValue(that.getFurtherStudies()));
        this.setGeneticAnalysis(Utility.trimValue(that.getGeneticAnalysis()));
        this.setPublishDetail(Utility.trimValue(that.getPublishDetail()));
        this.setPublishedCase(Utility.trimValue(that.getPublishedCase()));
        this.setRegisterId(Utility.trimValue(that.getRegisterId()));
        this.setSingleGeneAnalysis(Utility.trimValue(that.getSingleGeneAnalysis()));
        if (CollectionUtils.isNotEmpty(that.getDsdGeneScreeneds())) {
            Set<DsdGeneScreened> newDsdGeneScreeneds = new TreeSet<DsdGeneScreened>(new DsdGeneScreenedComparator());
            for (DsdGeneScreened dgs : that.getDsdGeneScreeneds()) {
                newDsdGeneScreeneds.add(new DsdGeneScreened(dgs));
            }
            this.setDsdGeneScreeneds(newDsdGeneScreeneds);
        }
    }

    @GenericGenerator(name = "generator", strategy = "foreign", parameters = {@Parameter(name = "property", value = "dsdIdentifier")})
    @Id
    @GeneratedValue(generator = "generator")
    @Column(name = "register_id", unique = true, nullable = false)
    public Long getRegisterId() {
        return this.registerId;
    }

    public void setRegisterId(Long registerId) {
        this.registerId = registerId;
    }

    @OneToOne(fetch = FetchType.EAGER)
    @PrimaryKeyJoinColumn
    public DsdIdentifier getDsdIdentifier() {
        return this.dsdIdentifier;
    }

    public void setDsdIdentifier(DsdIdentifier dsdIdentifier) {
        this.dsdIdentifier = dsdIdentifier;
    }

    @Column(name = "genetic_analysis", length = 10)
    public String getGeneticAnalysis() {
        return this.geneticAnalysis;
    }

    public void setGeneticAnalysis(String geneticAnalysis) {
        this.geneticAnalysis = geneticAnalysis;
    }

    @Column(name = "single_gene_analysis", length = 10)
    public String getSingleGeneAnalysis() {
        return this.singleGeneAnalysis;
    }

    public void setSingleGeneAnalysis(String singleGeneAnalysis) {
        this.singleGeneAnalysis = singleGeneAnalysis;
    }

    @Column(name = "chromosomal_rearrangement", length = 10)
    public String getChromosomalRearrangement() {
        return this.chromosomalRearrangement;
    }

    public void setChromosomalRearrangement(String chromosomalRearrangement) {
        this.chromosomalRearrangement = chromosomalRearrangement;
    }

    @Column(name = "chromosomal_rearrangement_detail")
    public String getChromosomalRearrangementDetail() {
        return this.chromosomalRearrangementDetail;
    }

    public void setChromosomalRearrangementDetail(
            String chromosomalRearrangementDetail) {
        this.chromosomalRearrangementDetail = chromosomalRearrangementDetail;
    }

    @Column(name = "cgh", length = 10)
    public String getCgh() {
        return this.cgh;
    }

    public void setCgh(String cgh) {
        this.cgh = cgh;
    }

    @Column(name = "cgh_detail")
    public String getCghDetail() {
        return this.cghDetail;
    }

    public void setCghDetail(String cghDetail) {
        this.cghDetail = cghDetail;
    }

    @Column(name = "functional_study", length = 10)
    public String getFunctionalStudy() {
        return this.functionalStudy;
    }

    public void setFunctionalStudy(String functionalStudy) {
        this.functionalStudy = functionalStudy;
    }

    @Column(name = "functional_study_detail")
    public String getFunctionalStudyDetail() {
        return this.functionalStudyDetail;
    }

    public void setFunctionalStudyDetail(String functionalStudyDetail) {
        this.functionalStudyDetail = functionalStudyDetail;
    }

    @Column(name = "published_case", length = 10)
    public String getPublishedCase() {
        return this.publishedCase;
    }

    public void setPublishedCase(String publishedCase) {
        this.publishedCase = publishedCase;
    }

    @Column(name = "publish_detail")
    public String getPublishDetail() {
        return this.publishDetail;
    }

    public void setPublishDetail(String publishDetail) {
        this.publishDetail = publishDetail;
    }

    @Column(name = "further_studies", length = 100)
    public String getFurtherStudies() {
        return this.furtherStudies;
    }

    public void setFurtherStudies(String furtherStudies) {
        this.furtherStudies = furtherStudies;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "dsdGeneAnalysis")
    @OrderBy("screenId")
    public Set<DsdGeneScreened> getDsdGeneScreeneds() {
        return this.dsdGeneScreeneds;
    }

    public void setDsdGeneScreeneds(Set<DsdGeneScreened> dsdGeneScreeneds) {
        this.dsdGeneScreeneds = dsdGeneScreeneds;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        if (StringUtils.isNotBlank(this.getSingleGeneAnalysis())) {
            stringBuilder.append("Single Gene Analysis = ").append(this.getSingleGeneAnalysis()).append(";");
        }
        if (StringUtils.isNotBlank(this.getChromosomalRearrangement())) {
            stringBuilder.append("Chromosomal Rearrangement = ").append(this.getChromosomalRearrangement()).append(";");
        }
        if (StringUtils.isNotBlank(this.getChromosomalRearrangementDetail())) {
            stringBuilder.append("Chromosomal Rearrangement Detail = ").append(this.getChromosomalRearrangementDetail()).append(";");
        }
        if (StringUtils.isNotBlank(this.getCgh())) {
            stringBuilder.append("CGH = ").append(this.getCgh()).append(";");
        }
        if (StringUtils.isNotBlank(this.getCghDetail())) {
            stringBuilder.append("CGH Detail = ").append(this.getCghDetail()).append(";");
        }
        if (StringUtils.isNotBlank(this.getFunctionalStudy())) {
            stringBuilder.append("Functional Study = ").append(this.getFunctionalStudy()).append(";");
        }
        if (StringUtils.isNotBlank(this.getFunctionalStudyDetail())) {
            stringBuilder.append("Functional Study Detail= ").append(this.getFunctionalStudyDetail()).append(";");
        }
        if (StringUtils.isNotBlank(this.getPublishedCase())) {
            stringBuilder.append("Published Case = ").append(this.getPublishedCase()).append(";");
        }
        if (StringUtils.isNotBlank(this.getPublishDetail())) {
            stringBuilder.append("Publication Detail = ").append(this.getPublishDetail()).append(";");
        }
        if (StringUtils.isNotBlank(this.getFurtherStudies())) {
            stringBuilder.append("Further Study = ").append(this.getFurtherStudies()).append(";");
        }
        //go through DsdGeneScreened
        if (CollectionUtils.isNotEmpty(this.getDsdGeneScreeneds())) {
            for (DsdGeneScreened dsdGeneScreened : this.getDsdGeneScreeneds()) {
                stringBuilder.append(dsdGeneScreened.toAuditString());
            }
        }
        return stringBuilder.toString();
    }

    public String toLongString() {
        return "DsdGeneAnalysis{" +
                "registerId=" + registerId +
                ", geneticAnalysis='" + geneticAnalysis + '\'' +
                ", singleGeneAnalysis='" + singleGeneAnalysis + '\'' +
                ", chromosomalRearrangement='" + chromosomalRearrangement + '\'' +
                ", chromosomalRearrangementDetail='" + chromosomalRearrangementDetail + '\'' +
                ", cgh='" + cgh + '\'' +
                ", cghDetail='" + cghDetail + '\'' +
                ", functionalStudy='" + functionalStudy + '\'' +
                ", functionalStudyDetail='" + functionalStudyDetail + '\'' +
                ", publishedCase='" + publishedCase + '\'' +
                ", publishDetail='" + publishDetail + '\'' +
                ", furtherStudies='" + furtherStudies + '\'' +
                ", dsdGeneScreeneds=" + dsdGeneScreeneds +
                '}';
    }
}