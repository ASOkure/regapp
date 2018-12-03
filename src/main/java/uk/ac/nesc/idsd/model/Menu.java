package uk.ac.nesc.idsd.model;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.annotations.BatchSize;
import uk.ac.nesc.idsd.util.Utility;
import uk.ac.nesc.idsd.util.comparator.OptionComparator;

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
@Table(name = "menu", catalog = "idsd")
public class Menu implements java.io.Serializable {
    private static final long serialVersionUID = -7552188392264752900L;

    private Integer menuId;
    private String menuName;
    private Boolean tertiary;
    private String tertiaryParamName;
    private Set<Option> options = new HashSet<Option>(0);

    public Menu() {
    }

    public Menu(String menuName, Boolean tertiary, String tertiaryParamName, Set<Option> options) {
        this.menuName = menuName;
        this.tertiary = tertiary;
        this.tertiaryParamName = tertiaryParamName;
        this.options = options;
    }

    public Menu(Menu that) {
        if (that == null) {
            return;
        }
        this.setMenuId(that.getMenuId());
        this.setMenuName(Utility.trimValue(that.getMenuName()));
        this.setTertiaryParamName(Utility.trimValue(that.getTertiaryParamName()));
        this.setTertiary(that.getTertiary());
        if (CollectionUtils.isNotEmpty(that.getOptions())) {
            Set newOptionSet = new TreeSet(new OptionComparator());
            for (Option option : that.getOptions()) {
                newOptionSet.add(new Option(option));
            }
            this.setOptions(newOptionSet);
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_id", unique = true, nullable = false)
    public Integer getMenuId() {
        return this.menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    @Column(name = "menu_name", length = 100)
    public String getMenuName() {
        return this.menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    @Column(name = "tertiary")
    public Boolean getTertiary() {
        return this.tertiary;
    }

    public void setTertiary(Boolean tertiary) {
        this.tertiary = tertiary;
    }

    @Column(name = "tertiary_param_name", length = 100)
    public String getTertiaryParamName() {
        return this.tertiaryParamName;
    }

    public void setTertiaryParamName(String tertiaryParamName) {
        this.tertiaryParamName = tertiaryParamName;
    }

    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "menu_id")
    @OrderBy
    @BatchSize(size = 100)
    public Set<Option> getOptions() {
        return this.options;
    }

    public void setOptions(Set<Option> options) {
        this.options = options;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "menuId=" + menuId +
                ", menuName='" + menuName + '\'' +
                ", tertiary=" + tertiary +
                ", tertiaryParamName='" + tertiaryParamName + '\'' +
                ", options=" + options +
                '}';
    }
}