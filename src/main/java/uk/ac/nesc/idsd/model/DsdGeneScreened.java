package uk.ac.nesc.idsd.model;

import org.apache.commons.lang3.StringUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "dsd_gene_screened", catalog = "idsd")
public class DsdGeneScreened implements java.io.Serializable {
    private static final long serialVersionUID = 4996459206884018102L;

    private Long screenId;
    private DsdGeneAnalysis dsdGeneAnalysis;
    private Gene gene;
    private String directSequence;
    private String otherSequence;
    private Boolean mutationFound;
    private String mutationDetail;

    public DsdGeneScreened() {
    }

    public DsdGeneScreened(DsdGeneAnalysis dsdGeneAnalysis, Gene gene,
                           String directSequence, String otherSequence, Boolean mutationFound,
                           String mutationDetail) {
        this.dsdGeneAnalysis = dsdGeneAnalysis;
        this.gene = gene;
        this.directSequence = directSequence;
        this.otherSequence = otherSequence;
        this.mutationFound = mutationFound;
        this.mutationDetail = mutationDetail;
    }

    public DsdGeneScreened(DsdGeneScreened that) {
        if (that == null) {
            return;
        }
        if (StringUtils.isNotBlank(that.getDirectSequence())) {
            this.setDirectSequence(that.getDirectSequence().trim());
        }
        if (that.getDsdGeneAnalysis() != null) {
            this.setDsdGeneAnalysis(that.getDsdGeneAnalysis());
        }
        if (that.getGene() != null) {
            this.setGene(that.getGene());
        }
        if (StringUtils.isNotBlank(that.getMutationDetail())) {
            this.setMutationDetail(that.getMutationDetail().trim());
        }
        if (that.getMutationFound() != null) {
            this.setMutationFound(that.getMutationFound());
        }
        if (StringUtils.isNotBlank(that.getOtherSequence())) {
            this.setOtherSequence(that.getOtherSequence().trim());
        }
        if (that.getScreenId() != null) {
            this.setScreenId(that.getScreenId());
        }
    }

    @Id
    @GeneratedValue
    @Column(name = "screen_id", unique = true, nullable = false)
    public Long getScreenId() {
        return this.screenId;
    }

    public void setScreenId(Long screenId) {
        this.screenId = screenId;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "register_id")
    public DsdGeneAnalysis getDsdGeneAnalysis() {
        return this.dsdGeneAnalysis;
    }

    public void setDsdGeneAnalysis(DsdGeneAnalysis dsdGeneAnalysis) {
        this.dsdGeneAnalysis = dsdGeneAnalysis;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "gene_id")
    public Gene getGene() {
        return this.gene;
    }

    public void setGene(Gene gene) {
        this.gene = gene;
    }

    @Column(name = "direct_sequence", length = 10)
    public String getDirectSequence() {
        return this.directSequence;
    }

    public void setDirectSequence(String directSequence) {
        this.directSequence = directSequence;
    }

    @Column(name = "other_sequence")
    public String getOtherSequence() {
        return this.otherSequence;
    }

    public void setOtherSequence(String otherSequence) {
        this.otherSequence = otherSequence;
    }

    @Column(name = "mutation_found")
    public Boolean getMutationFound() {
        return this.mutationFound;
    }

    public void setMutationFound(Boolean mutationFound) {
        this.mutationFound = mutationFound;
    }

    @Column(name = "mutation_detail")
    public String getMutationDetail() {
        return this.mutationDetail;
    }

    public void setMutationDetail(String mutationDetail) {
        this.mutationDetail = mutationDetail;
    }

    public String toAuditString() {
        if (this == null) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        if (this.getScreenId() != null) {
            stringBuilder.append("Screen ID = ").append(this.getScreenId());
        }
        if (this.getGene() != null) {
            stringBuilder.append("Gene ID = ").append(this.getGene().getGeneId()).append(";Gene Name = ").append(this.getGene().getGeneName()).append(";");
        }
        if (StringUtils.isNotBlank(this.getDirectSequence())) {
            stringBuilder.append("Direct Sequence = ").append(this.getDirectSequence()).append(";");
        }
        if (StringUtils.isNotBlank(this.getOtherSequence())) {
            stringBuilder.append("Other Sequence = ").append(this.getOtherSequence()).append(";");
        }
        if (this.getMutationFound() != null) {
            stringBuilder.append("Mutation Found = ").append(this.getMutationFound()).append(";");
        }
        if (StringUtils.isNotBlank(this.getMutationDetail())) {
            stringBuilder.append("Mutation Detail = ").append(this.getMutationDetail()).append(";");
        }
        return stringBuilder.toString();
    }
}