package uk.ac.nesc.idsd.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.transaction.annotation.Transactional;

import uk.ac.nesc.idsd.security.spring.User;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "patient_user", catalog = "idsd")

public class PatientUser extends User {
    private static final long serialVersionUID = 2672383328660262830L;

    @Column(name = "first_login", length = 19)
    protected Timestamp firstLogin;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "introduced_by")
    private PortalUser introducedBy;
    @Column(name = "dsd_identifier_id", nullable = false)
    private Long dsdIdentifierId;
    @Transient
    private Set<GrantedAuthority> authorities;

    public PatientUser() {
    }

    public PatientUser(Long dsdIdentifierId) {
        this.dsdIdentifierId = dsdIdentifierId;
    }

    public PatientUser(String password, PortalUser introducedBy,
                       Timestamp registerTime, Timestamp lastLogin, Long loginCounter,
                       Long dsdIdentifierId, boolean enabled, boolean accountNonExpired,
                       boolean accountNonLocked, boolean credentialsNonExpired, String ip) {
        this.password = password;
        this.introducedBy = introducedBy;
        this.registerTime = registerTime;
        this.lastLogin = lastLogin;
        this.loginCounter = loginCounter;
        this.dsdIdentifierId = dsdIdentifierId;
        this.enabled = enabled;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
        this.ip = ip;
    }

    public PortalUser getIntroducedBy() {
        return this.introducedBy;
    }

    public void setIntroducedBy(PortalUser introducedBy) {
        this.introducedBy = introducedBy;
    }

    public Long getDsdIdentifierId() {
        return dsdIdentifierId;
    }

    public void setDsdIdentifierId(Long dsdIdentifierId) {
        this.dsdIdentifierId = dsdIdentifierId;
    }

    public Timestamp getFirstLogin() {
        return firstLogin;
    }

    public void setFirstLogin(Timestamp firstLogin) {
        this.firstLogin = firstLogin;
    }

    @Override
    public Set<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public String toString() {
        return "PatientUser{" +
                "username=" + username +
                ", introducedBy=" + introducedBy +
                ", dsdIdentifierId=" + dsdIdentifierId +
                ", firstLogin=" + firstLogin +
                '}';
    }
}