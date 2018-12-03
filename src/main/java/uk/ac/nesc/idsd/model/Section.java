package uk.ac.nesc.idsd.model;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.annotations.BatchSize;
import uk.ac.nesc.idsd.util.comparator.ParameterComparator;

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
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

@Entity
@Table(name = "section", catalog = "idsd")
public class Section implements Serializable {
    private static final long serialVersionUID = 2300442174772112457L;

    private Integer sectionId;
    private String name;
    private Set<Parameter> parameters = new HashSet<Parameter>(0);

    public Section() {
    }

    public Section(Integer sectionId, String name, Set<Parameter> parameters) {
        this.sectionId = sectionId;
        this.name = name;
        this.parameters = parameters;
    }

    public Section(Section that) {
        if (that == null) {
            return;
        }
        this.setSectionId(that.getSectionId());
        this.setName(that.getName());
        if (CollectionUtils.isNotEmpty(that.getParameters())) {
            Set<Parameter> newParameterSet = new TreeSet<Parameter>(new ParameterComparator());
            for (Parameter parameter : that.getParameters()) {
                newParameterSet.add(new Parameter(parameter));
            }
            this.setParameters(newParameterSet);
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "section_id", unique = true, nullable = false)
    public Integer getSectionId() {
        return this.sectionId;
    }

    public void setSectionId(Integer sectionId) {
        this.sectionId = sectionId;
    }

    @Column(name = "name", length = 100)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "section_id")
    @OrderBy
    @BatchSize(size = 100)
    public Set<Parameter> getParameters() {
        return this.parameters;
    }

    public void setParameters(Set<Parameter> parameters) {
        this.parameters = parameters;
    }

    @Override
    public String toString() {
        return "Section{" +
                "sectionId=" + sectionId +
                ", name='" + name +
                "}";
    }
}