package uk.ac.nesc.idsd.model;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import uk.ac.nesc.idsd.util.Utility;

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
@Table(name = "parameter", catalog = "idsd")
public class Parameter implements java.io.Serializable, Comparable<Parameter> {
    private static final long serialVersionUID = 5275335269655885845L;

    private Integer paramId;
    private Menu menu;
    private String type;
    private String label;
    private String paramName;
    private Boolean mandatory;
    private Boolean hidden;
    private Boolean searchable;
    private String tooltip;

    public Parameter() {
    }

    public Parameter(Menu menu, String type, String label, String paramName, Boolean mandatory, Boolean hidden, Boolean searchable, String tooltip) {
        this.menu = menu;
        this.type = type;
        this.label = label;
        this.paramName = paramName;
        this.mandatory = mandatory;
        this.hidden = hidden;
        this.searchable = searchable;
        this.tooltip = tooltip;
    }

    public Parameter(Parameter that) {
        if (that == null) {
            return;
        }
        this.setParamId(that.getParamId());
        this.setParamName(Utility.trimValue(that.getParamName()));
        this.setType(Utility.trimValue(that.getType()));
        this.setLabel(Utility.trimValue(that.getLabel()));
        if (that.getMenu() != null) {
            this.setMenu(new Menu(that.getMenu()));
        }
        this.setMandatory(that.getMandatory());
        this.setHidden(that.getHidden());
        this.setSearchable(that.getSearchable());
        this.setTooltip(Utility.trimValue(that.getTooltip()));
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "param_id", unique = true, nullable = false)
    public Integer getParamId() {
        return this.paramId;
    }

    public void setParamId(Integer paramId) {
        this.paramId = paramId;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "menu_id")
    @Fetch(FetchMode.JOIN)
    @BatchSize(size = 100)
    public Menu getMenu() {
        return this.menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    @Column(name = "type", length = 100)
    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column(name = "label", length = 100)
    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Column(name = "param_name", length = 100)
    public String getParamName() {
        return this.paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    @Column(name = "mandatory")
    public Boolean getMandatory() {
        return this.mandatory;
    }

    public void setMandatory(Boolean mandatory) {
        this.mandatory = mandatory;
    }

    @Column(name = "hidden")
    public Boolean getHidden() {
        return this.hidden;
    }

    public void setHidden(Boolean hidden) {
        this.hidden = hidden;
    }

    @Column(name = "searchable")
    public Boolean getSearchable() {
        return this.searchable;
    }

    public void setSearchable(Boolean searchable) {
        this.searchable = searchable;
    }

    @Column(name = "tooltip", length = 65535)
    public String getTooltip() {
        return this.tooltip;
    }

    public void setTooltip(String tooltip) {
        this.tooltip = tooltip;
    }

    @Override
    public int compareTo(Parameter p) {
        if (this.equals(p)) {
            return 0;
        } else if (this.getParamId() < p.getParamId()) {
            return -1;
        } else {
            return 1;
        }
    }

    @Override
    public String toString() {
        return "Parameter{" +
                "paramId=" + paramId +
                ", type='" + type + '\'' +
                ", label='" + label + '\'' +
                ", paramName='" + paramName + '\'' +
                ", mandatory=" + mandatory +
                ", hidden=" + hidden +
                ", searchable=" + searchable +
                ", menu=" + menu +
                '}';
    }
}