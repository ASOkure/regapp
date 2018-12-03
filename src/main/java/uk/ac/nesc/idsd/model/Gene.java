package uk.ac.nesc.idsd.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "gene", catalog = "idsd")
public class Gene implements java.io.Serializable {
    private static final long serialVersionUID = -1209940306821335887L;

    private Integer geneId;
    private String geneName;
    private String description;

    public Gene() {
    }

    public Gene(String geneName, String description) {
        this.geneName = geneName;
        this.description = description;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gene_id", unique = true, nullable = false)
    public Integer getGeneId() {
        return this.geneId;
    }

    public void setGeneId(Integer geneId) {
        this.geneId = geneId;
    }

    @Column(name = "gene_name", length = 200)
    public String getGeneName() {
        return this.geneName;
    }

    public void setGeneName(String geneName) {
        this.geneName = geneName;
    }

    @Column(name = "description", length = 1000)
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}