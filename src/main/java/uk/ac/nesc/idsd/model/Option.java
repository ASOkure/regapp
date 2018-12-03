package uk.ac.nesc.idsd.model;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.BatchSize;
import uk.ac.nesc.idsd.util.comparator.TertiaryComparator;

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
import java.util.TreeSet;

@Entity
@Table(name = "option", catalog = "idsd")
public class Option implements java.io.Serializable {
    private static final long serialVersionUID = 5718045721329769793L;

    private Integer optionId;
    private String value;
    private Float score;
    private Set<Tertiary> tertiaries = new HashSet<Tertiary>(0);

    public Option() {
    }

    public Option(Integer optionId, String value) {
        this.optionId = optionId;
        this.value = value;
    }

    public Option(String value, Float score, Set<Tertiary> tertiaries) {
        this.value = value;
        this.score = score;
        this.tertiaries = tertiaries;
    }

    public Option(Option that) {
        if (that == null) {
            return;
        }
        this.setOptionId(that.getOptionId());
        if (StringUtils.isNotBlank(that.getValue())) {
            this.setValue(that.getValue());
        }
        this.setScore(that.getScore());
        if (CollectionUtils.isNotEmpty(that.getTertiaries())) {
            Set<Tertiary> newTertiarySet = new TreeSet<Tertiary>(new TertiaryComparator());
            for (Tertiary tertiary : that.getTertiaries()) {
                newTertiarySet.add(new Tertiary(tertiary));
            }
            this.setTertiaries(newTertiarySet);
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "option_id", unique = true, nullable = false)
    public Integer getOptionId() {
        return this.optionId;
    }

    public void setOptionId(Integer optionId) {
        this.optionId = optionId;
    }

    @Column(name = "value", length = 200)
    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Column(name = "score", precision = 12, scale = 0)
    public Float getScore() {
        return this.score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "option_id")
    @OrderBy
    @BatchSize(size = 200)
    public Set<Tertiary> getTertiaries() {
        return this.tertiaries;
    }

    public void setTertiaries(Set<Tertiary> tertiaries) {
        this.tertiaries = tertiaries;
    }

    @Override
    public String toString() {
        return "Option{" +
                "optionId=" + optionId +
                ", value='" + value + '\'' +
                ", tertiaries=" + tertiaries +
                '}';
    }
}