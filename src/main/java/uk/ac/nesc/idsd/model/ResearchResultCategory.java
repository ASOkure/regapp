package uk.ac.nesc.idsd.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "research_result_category", catalog = "idsd")
public class ResearchResultCategory implements java.io.Serializable {
    private static final long serialVersionUID = 1387879888362779850L;

    private Integer categoryId;
    private String name;
    private Set<ResearchResult> researchResults = new HashSet<ResearchResult>(0);

    public ResearchResultCategory() {
    }

    public ResearchResultCategory(String name, Set<ResearchResult> researchResults) {
        this.name = name;
        this.researchResults = researchResults;
    }

    @Id
    @GeneratedValue
    @Column(name = "category_id", unique = true, nullable = false)
    public Integer getCategoryId() {
        return this.categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    @Column(name = "name", length = 100)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "researchResultCategory")
    public Set<ResearchResult> getResearchResults() {
        return this.researchResults;
    }

    public void setResearchResults(Set<ResearchResult> researchResults) {
        this.researchResults = researchResults;
    }

}