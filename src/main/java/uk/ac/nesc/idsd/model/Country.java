package uk.ac.nesc.idsd.model;

import org.hibernate.annotations.BatchSize;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "country", catalog = "idsd")
public class Country implements java.io.Serializable {
    private static final long serialVersionUID = 8117219800613384773L;
    private Long countryId;
    private String countryName;
    private Set<Centre> centres = new HashSet<Centre>(0);

    public Country() {
    }

    public Country(String countryName) {
        this.countryName = countryName;
    }

    public Country(String countryName, Set<Centre> centres) {
        this.countryName = countryName;
        this.centres = centres;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "country_id", unique = true, nullable = false)
    public Long getCountryId() {
        return this.countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    @Column(name = "country_name", length = 200)
    public String getCountryName() {
        return this.countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "country")
    @BatchSize(size = 100)
    public Set<Centre> getCentres() {
        return this.centres;
    }

    public void setCentres(Set<Centre> centres) {
        this.centres = centres;
    }

    @Override
    public String toString() {
        StringBuilder centreNames = new StringBuilder();
        centreNames.append(", centres=[");
        if (null != centres && !centres.isEmpty()) {
            for (Centre c : centres) {
                centreNames.append("centreName=");
                centreNames.append(c.getCentreName());
                centreNames.append(";");
            }
        }
        centreNames.append("]");

        return "Country{" +
                "countryId=" + countryId +
                ", countryName='" + countryName + '\'' +
                centreNames.toString() +
                '}';
    }
}