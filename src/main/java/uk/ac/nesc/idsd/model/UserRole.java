package uk.ac.nesc.idsd.model;

import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

//@FilterDef(name = "betweenDates", parameters = {@ParamDef(name = "today", type = "java.util.Date") })
//@Filters({ @Filter(name = "betweenDates", condition = "( (role.id = 1 AND validateFrom <= :today AND validateTo >= :today) OR role.id <> 1 )") })
@Entity
@FilterDef(name = "betweenDates", parameters = {@ParamDef(name = "today", type = "java.util.Date")},
        defaultCondition = " (( role_id = 1 AND validate_from <= :today AND validate_to >= :today ) OR role_id <> 1 ) ")
//@Filters({ @Filter(name = "betweenDates", condition = "( (role.id = 1 AND validateFrom <= :today AND validateTo >= :today) OR role.id <> 1 )") })
@Table(name = "user_role", catalog = "idsd")
public class UserRole implements java.io.Serializable {
    private static final long serialVersionUID = 3494795890289521040L;

    private Long userRoleId;
    private Role role;
    private PortalUser portalUser;
    private Date validateFrom;
    private Date validateTo;
    private String approvedBy;

    public UserRole() {
    }

    public UserRole(Role role, PortalUser portalUser) {
        this.role = role;
        this.portalUser = portalUser;
    }

    public UserRole(Role role, PortalUser portalUser, Date validateFrom, Date validateTo, String approvedBy) {
        this.role = role;
        this.portalUser = portalUser;
        this.validateFrom = validateFrom;
        this.validateTo = validateTo;
        this.approvedBy = approvedBy;
    }

    @Id
    @GeneratedValue
    @Column(name = "user_role_id", unique = true)
    public Long getUserRoleId() {
        return this.userRoleId;
    }

    public void setUserRoleId(Long userRoleId) {
        this.userRoleId = userRoleId;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    public Role getRole() {
        return this.role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "username", nullable = false)
    public PortalUser getPortalUser() {
        return this.portalUser;
    }

    public void setPortalUser(PortalUser portalUser) {
        this.portalUser = portalUser;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "validate_from", length = 10)
    public Date getValidateFrom() {
        return this.validateFrom;
    }

    public void setValidateFrom(Date validateFrom) {
        this.validateFrom = validateFrom;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "validate_to", length = 10)
    public Date getValidateTo() {
        return this.validateTo;
    }

    public void setValidateTo(Date validateTo) {
        this.validateTo = validateTo;
    }

    @Column(name = "approved_by", length = 300)
    public String getApprovedBy() {
        return this.approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "userRoleId=" + userRoleId +
                ", role=" + role +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        UserRole other = (UserRole) obj;
        if (approvedBy == null) {
            if (other.approvedBy != null) {
                return false;
            }
        } else if (!approvedBy.equals(other.approvedBy)) {
            return false;
        }
        if (role == null) {
            if (other.role != null) {
                return false;
            }
        } else if (!role.equals(other.role)) {
            return false;
        }
        if (userRoleId == null) {
            if (other.userRoleId != null) {
                return false;
            }
        } else if (!userRoleId.equals(other.userRoleId)) {
            return false;
        }
        if (validateFrom == null) {
            if (other.validateFrom != null) {
                return false;
            }
        } else if (!validateFrom.equals(other.validateFrom)) {
            return false;
        }
        if (validateTo == null) {
            if (other.validateTo != null) {
                return false;
            }
        } else if (!validateTo.equals(other.validateTo)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((approvedBy == null) ? 0 : approvedBy.hashCode());
        result = prime * result + ((role == null) ? 0 : role.hashCode());
        result = prime * result
                + ((userRoleId == null) ? 0 : userRoleId.hashCode());
        result = prime * result
                + ((validateFrom == null) ? 0 : validateFrom.hashCode());
        result = prime * result
                + ((validateTo == null) ? 0 : validateTo.hashCode());
        return result;
    }
}