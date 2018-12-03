package uk.ac.nesc.idsd.security.spring;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import uk.ac.nesc.idsd.model.Role;
import uk.ac.nesc.idsd.model.UserRole;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by jiangj on 16/09/2014.
 */
@MappedSuperclass
public abstract class User implements UserDetails {
    private static final long serialVersionUID = 3516368795683006311L;

    @Id
    @Column(name = "username", unique = true, nullable = false, length = 200)
    protected String username;
    @Column(name = "password", length = 200)
    protected String password;
    @Column(name = "register_time", length = 19)
    protected Timestamp registerTime;
    @Column(name = "last_login", length = 19)
    protected Timestamp lastLogin;
    @Column(name = "login_counter")
    protected Long loginCounter;
    @Column(name = "enabled")
    protected boolean enabled;
    @Column(name = "account_non_expired")
    protected boolean accountNonExpired;
    @Column(name = "account_non_locked")
    protected boolean accountNonLocked;
    @Column(name = "credentials_non_expired")
    protected boolean credentialsNonExpired;
    @Column(name = "ip", length = 100)
    protected String ip;
    @Column(name = "last_password_change", length = 19)
    protected Timestamp lastPasswordChange;
    @Transient
    protected Set<UserRole> userRoles = new HashSet<UserRole>(0);
    @Transient
    protected Set<Role> roles = new HashSet<Role>();

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Timestamp registerTime) {
        this.registerTime = registerTime;
    }

    public Timestamp getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Timestamp lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Long getLoginCounter() {
        return loginCounter;
    }

    public void setLoginCounter(Long loginCounter) {
        this.loginCounter = loginCounter;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Timestamp getLastPasswordChange() {
        return lastPasswordChange;
    }

    public void setLastPasswordChange(Timestamp lastPasswordChange) {
        this.lastPasswordChange = lastPasswordChange;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public Set<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
