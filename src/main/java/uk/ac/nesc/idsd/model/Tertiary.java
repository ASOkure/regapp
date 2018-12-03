package uk.ac.nesc.idsd.model;

import uk.ac.nesc.idsd.util.Utility;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tertiary", catalog = "idsd")
public class Tertiary implements java.io.Serializable {
    private static final long serialVersionUID = 7246973394343623100L;

    private Integer tertiaryId;
    private String value;
    private Boolean idsdPartner;

    public Tertiary() {
    }

    public Tertiary(Integer tertiaryId, String value) {
        this.tertiaryId = tertiaryId;
        this.value = value;
    }

    public Tertiary(String value, Boolean idsdPartner) {
        this.value = value;
        this.idsdPartner = idsdPartner;
    }

    public Tertiary(Tertiary that) {
        if (that == null) {
            return;
        }
        this.setTertiaryId(that.getTertiaryId());
        this.setValue(Utility.trimValue(that.getValue()));
        this.setIdsdPartner(that.getIdsdPartner());
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tertiary_id", unique = true, nullable = false)
    public Integer getTertiaryId() {
        return this.tertiaryId;
    }

    public void setTertiaryId(Integer tertiaryId) {
        this.tertiaryId = tertiaryId;
    }

    @Column(name = "value", length = 100)
    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Column(name = "idsd_partner")
    public Boolean getIdsdPartner() {
        return this.idsdPartner;
    }

    public void setIdsdPartner(Boolean idsdPartner) {
        this.idsdPartner = idsdPartner;
    }

    @Override
    public String toString() {
        return "Tertiary{" +
                "tertiaryId=" + tertiaryId +
                ", value='" + value + '\'' +
                ", idsdPartner=" + idsdPartner +
                '}';
    }
}