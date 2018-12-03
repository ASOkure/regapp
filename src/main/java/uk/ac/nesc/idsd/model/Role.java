package uk.ac.nesc.idsd.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "role", catalog = "idsd")
//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Role implements java.io.Serializable {
    public static final String ROLE_PATIENT = "ROLE_PATIENT";
    private static final long serialVersionUID = -9129557561250307103L;
    @Id
    @GeneratedValue
    @Column(name = "role_id", unique = true, nullable = false)
    private Long roleId;
    @Column(name = "role_name", length = 200)
    private String roleName;
    @Column(name = "role_description")
    private String roleDescription;
    @Column(name = "created_by", length = 200)
    private String createdBy;
    @Column(name = "created_timestamp", nullable = false, length = 19)
    private Timestamp createdTimestamp;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "role")
    private Set<UserRole> userRoles = new HashSet<UserRole>(0);

    public Role() {
    }

    public Role(Timestamp createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }

    public Role(String roleName) {
        this.roleName = roleName;
    }

    public Role(String roleName, String roleDescription, String createdBy,
                Timestamp createdTimestamp, Set<UserRole> userRoles) {
        this.roleName = roleName;
        this.roleDescription = roleDescription;
        this.createdBy = createdBy;
        this.createdTimestamp = createdTimestamp;
//		this.users = users;
        this.userRoles = userRoles;
    }

    public Long getRoleId() {
        return this.roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return this.roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDescription() {
        return this.roleDescription;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Timestamp getCreatedTimestamp() {
        return this.createdTimestamp;
    }

    public void setCreatedTimestamp(Timestamp createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }

    public Set<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    @Override
    public String toString() {
        return "Role(" + roleName + ")";
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
        Role other = (Role) obj;
        if (createdBy == null) {
            if (other.createdBy != null) {
                return false;
            }
        } else if (!createdBy.equals(other.createdBy)) {
            return false;
        }
        if (createdTimestamp == null) {
            if (other.createdTimestamp != null) {
                return false;
            }
        } else if (!createdTimestamp.equals(other.createdTimestamp)) {
            return false;
        }
        if (roleDescription == null) {
            if (other.roleDescription != null) {
                return false;
            }
        } else if (!roleDescription.equals(other.roleDescription)) {
            return false;
        }
        if (roleId == null) {
            if (other.roleId != null) {
                return false;
            }
        } else if (!roleId.equals(other.roleId)) {
            return false;
        }
        if (roleName == null) {
            if (other.roleName != null) {
                return false;
            }
        } else if (!roleName.equals(other.roleName)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((createdBy == null) ? 0 : createdBy.hashCode());
        result = prime
                * result
                + ((createdTimestamp == null) ? 0 : createdTimestamp.hashCode());
        result = prime * result
                + ((roleDescription == null) ? 0 : roleDescription.hashCode());
        result = prime * result + ((roleId == null) ? 0 : roleId.hashCode());
        result = prime * result
                + ((roleName == null) ? 0 : roleName.hashCode());
        return result;
    }

}