package uk.ac.nesc.idsd.model;

import org.hibernate.annotations.BatchSize;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "centre", catalog = "idsd")
public class Centre implements java.io.Serializable {
    private static final long serialVersionUID = -5935397917325797779L;

    private Long centreId;
    private PortalUser leader;
    private Country country;
    private String centreName;
    private String description;
    private String address;
    private String clinicsDates;
    private String localResources;
    private String localEvents;
    private String activeStudies;
    private String additionalInfo;

    public Centre() {
    }

    public Centre(Long centreId) {
        this.centreId = centreId;
    }

    public Centre(String centreName) {
        this.centreName = centreName;
    }

    public Centre(String centreName, String countryName) {
        this.centreName = centreName;
        if (this.country == null) {
            this.country = new Country(countryName);
        } else {
            this.country.setCountryName(countryName);
        }
    }

    public Centre(Country country) {
        this.country = country;
    }

    public Centre(String centreName, String name, String email) {
        this.centreName = centreName;
        if (null == this.leader) {
            this.leader = new PortalUser(name, email);
        } else {
            this.leader.setName(name);
            this.leader.setEmail(email);
        }
    }

    public Centre(PortalUser leader, Country country, String centreName, String description, String address) {
        this.leader = leader;
        this.country = country;
        this.centreName = centreName;
        this.description = description;
        this.address = address;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "centre_id", unique = true, nullable = false)
    public Long getCentreId() {
        return this.centreId;
    }

    public void setCentreId(Long centreId) {
        this.centreId = centreId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @BatchSize(size = 100)
    @JoinColumn(name = "leader")
    public PortalUser getLeader() {
        return this.leader;
    }

    public void setLeader(PortalUser leader) {
        this.leader = leader;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "country_id", nullable = false)
    public Country getCountry() {
        return this.country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @Column(name = "centre_name", length = 200)
    public String getCentreName() {
        return this.centreName;
    }

    public void setCentreName(String centreName) {
        this.centreName = centreName;
    }

    @Column(name = "description", length = 65535)
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "address", length = 65535)
    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name = "clinics_dates", length = 65535)
    public String getClinicsDates() {
        return clinicsDates;
    }

    public void setClinicsDates(String clinicsDates) {
        this.clinicsDates = clinicsDates;
    }

    @Column(name = "local_resources", length = 65535)
    public String getLocalResources() {
        return localResources;
    }

    public void setLocalResources(String localResources) {
        this.localResources = localResources;
    }

    @Column(name = "local_events", length = 65535)
    public String getLocalEvents() {
        return localEvents;
    }

    public void setLocalEvents(String localEvents) {
        this.localEvents = localEvents;
    }

    @Column(name = "active_studies", length = 65535)
    public String getActiveStudies() {
        return activeStudies;
    }

    public void setActiveStudies(String activeStudies) {
        this.activeStudies = activeStudies;
    }

    @Column(name = "additional_info", length = 65535)
    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    @Override
    public String toString() {
        String leaderName;
        if (null != leader) {
            leaderName = ", leader=" + leader.getUsername();
        } else {
            leaderName = ", leader=null";
        }
        String countryName;
        if (null != country) {
            countryName = ", country=" + country.getCountryName();
        } else {
            countryName = "null";
        }
        return "Centre{" +
                "centreId=" + centreId +
                leaderName +
                countryName +
                ", centreName='" + centreName + '\'' +
                ", description='" + description + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

}