package uk.ac.nesc.idsd.model;

import org.hibernate.annotations.BatchSize;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "gene_category", catalog = "idsd")
public class GeneCategory implements java.io.Serializable {
    private static final long serialVersionUID = 5905972585560703035L;

    private Integer categoryId;
    private String name;
    private Set<Gene> genes = new HashSet<Gene>(0);

    public GeneCategory() {
    }

    public GeneCategory(String name, Set<Gene> genes) {
        this.name = name;
        this.genes = genes;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id", unique = true, nullable = false)
    public Integer getCategoryId() {
        return this.categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    @Column(name = "name", length = 200)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    @OrderBy
    @BatchSize(size = 200)
    public Set<Gene> getGenes() {
        return this.genes;
    }

    public void setGenes(Set<Gene> genes) {
        this.genes = genes;
    }

}