package uk.ac.nesc.idsd.model;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Filters;
import org.hibernate.annotations.Proxy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import uk.ac.nesc.idsd.security.spring.User;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.beans.Transient;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
//@FilterDef(name = "betweenDates", parameters = {@ParamDef(name = "today", type = "java.util.Date") })
//@Filters({ @Filter(name = "betweenDates", condition = "( (role.id = 1 AND validateFrom <= :today AND validateTo >= :today) OR role.id <> 1 )") })
@Table(name = "user", catalog = "idsd", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
@Proxy(lazy = false)
//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PortalUser extends User {
    private static final long serialVersionUID = -5868814879220179780L;

    @Column(name = "email", unique = true, length = 200)
    private String email;
    @Column(name = "name", length = 200)
    private String name;
    @Column(name = "country", length = 100)
    private String country;
    @Column(name = "centre", length = 200)
    private String centre;
    @Column(name = "tel", length = 25)
    private String tel;
    @Column(name = "fax", length = 25)
    private String fax;
    @Column(name = "position")
    private String position;
    @Column(name = "society")
    private String society;
    @Column(name = "address")
    private String address;
    @Column(name = "primary_role", length = 300)
    private String primaryRole;
    @Column(name = "secondary_roles", length = 1000)
    private String secondaryRoles;
    @Column(name = "secondary_roles_other")
    private String secondaryRolesOther;
    @Column(name = "interest", length = 1000)
    private String interest;
    @Column(name = "interest_other")
    private String interestOther;
    @Column(name = "special_interest")
    private String specialInterest;
    @Column(name = "searchable")
    private Boolean searchable;
    @Column(name = "profile_url")
    private String profileUrl;
    @Column(name = "centre_leader", length = 200)
    private String centreLeader;


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "portalUser")
//  @JoinTable
    //filter on the this entity table
//  @Filter(name="betweenDates", condition=":minLength <= length and :maxLength >= length")
    //filter on the association table
    @Filters(value = {@Filter(name = "betweenDates")})
//  @Filters({ @Filter(name = "betweenDates", condition = "( (role.id = 1 AND validateFrom <= :today AND validateTo >= :today) OR role.id <> 1 )") })
//  @FilterJoinTable(name="betweenDates", condition="validateFrom <= :today AND validateTo >= :today")
    @BatchSize(size = 10)
    private Set<UserRole> userRoles = new HashSet<UserRole>(0);

//	@ManyToMany(targetEntity = Role.class, fetch = FetchType.EAGER)
//	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "username"), inverseJoinColumns = @JoinColumn(name = "role_id"))
//	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
//	private Set<Role> roles = new HashSet<Role>(0);

    public PortalUser() {
    }

    public PortalUser(String name) {
        this.name = name;
    }

    public PortalUser(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public PortalUser(String username, String name, String email) {
        this.username = username;
        this.name = name;
        this.email = email;
    }

    /**
     * minimal constructor
     */
//    public PortalUser(String username, Timestamp lastLogin) {
//        this.username = username;
//        this.lastLogin = lastLogin;
//    }
//
    public PortalUser(String username, String name, String email, String country, String centre) {
        this.username = username;
        this.name = name;
        this.email = email;
        this.country = country;
        this.centre = centre;
    }

    public PortalUser(String password, String email, String name, String country,
                      String centre, String tel, Timestamp registerTime,
                      Timestamp lastLogin, Timestamp lastPasswordChange,
                      Long loginCounter, boolean enabled, boolean accountNonExpired,
                      boolean accountNonLocked, boolean credentialsNonExpired, String ip,
                      String fax, String position, String society, String address,
                      String primaryRole, String secondaryRoles,
                      String secondaryRolesOther, String interest, String interestOther,
                      String specialInterest, Boolean searchable, String profileUrl, String centreLeader,
                      Set<UserRole> userRoles) {
        this.password = password;
        this.email = email;
        this.name = name;
        this.country = country;
        this.centre = centre;
        this.tel = tel;
        this.registerTime = registerTime;
        this.lastLogin = lastLogin;
        this.lastPasswordChange = lastPasswordChange;
        this.loginCounter = loginCounter;
        this.enabled = enabled;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
        this.ip = ip;
        this.fax = fax;
        this.position = position;
        this.society = society;
        this.address = address;
        this.primaryRole = primaryRole;
        this.secondaryRoles = secondaryRoles;
        this.secondaryRolesOther = secondaryRolesOther;
        this.interest = interest;
        this.interestOther = interestOther;
        this.specialInterest = specialInterest;
        this.searchable = searchable;
        this.profileUrl = profileUrl;
        this.centreLeader = centreLeader;
//		this.roles = roles;
        this.userRoles = userRoles;
    }

    @Transient
    public Set<Role> getRoles() {
        Set<Role> roles = new HashSet<Role>();
        for (UserRole userRole : this.userRoles) {
            roles.add(userRole.getRole());
        }
        return roles;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCentre() {
        return this.centre;
    }

    public void setCentre(String centre) {
        this.centre = centre;
    }

    //@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    //@JoinTable(name = "user_role", catalog = "idsd",
    //joinColumns = { @JoinColumn(name = "username", nullable = false, updatable = false) },
    //inverseJoinColumns = { @JoinColumn(name = "role_id", nullable = false, updatable = false) })

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>(this.userRoles.size());
        for (UserRole userRole : this.userRoles) {
            grantedAuthorities.add(new SimpleGrantedAuthority(userRole.getRole().getRoleName()));
        }
        return grantedAuthorities;
    }

    public String getAuthoritiesString() {
        List<String> authorities = new ArrayList<String>();
        for (GrantedAuthority authority : this.getAuthorities()) {
            authorities.add(authority.getAuthority());
        }
        return StringUtils.join(authorities, ",");
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getSociety() {
        return society;
    }

    public void setSociety(String society) {
        this.society = society;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPrimaryRole() {
        return primaryRole;
    }

    public void setPrimaryRole(String primaryRole) {
        this.primaryRole = primaryRole;
    }

    public String getSecondaryRoles() {
        return secondaryRoles;
    }

    public void setSecondaryRoles(String secondaryRoles) {
        this.secondaryRoles = secondaryRoles;
    }

    public String getSecondaryRolesOther() {
        return secondaryRolesOther;
    }

    public void setSecondaryRolesOther(String secondaryRolesOther) {
        this.secondaryRolesOther = secondaryRolesOther;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public String getInterestOther() {
        return interestOther;
    }

    public void setInterestOther(String interestOther) {
        this.interestOther = interestOther;
    }

    public String getSpecialInterest() {
        return specialInterest;
    }

    public void setSpecialInterest(String specialInterest) {
        this.specialInterest = specialInterest;
    }

    public Boolean getSearchable() {
        return searchable;
    }

    public void setSearchable(Boolean searchable) {
        this.searchable = searchable;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    public String getCentreLeader() {
        return centreLeader;
    }

    public void setCentreLeader(String centreLeader) {
        this.centreLeader = centreLeader;
    }

    public Set<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    @Override
    public String toString() {
        return "User{" + "username='" + username + '}';
    }

    public String toLongString() {
        return "PortalUser{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", centre='" + centre + '\'' +
                ", tel='" + tel + '\'' +
                ", fax='" + fax + '\'' +
                ", position='" + position + '\'' +
                ", society='" + society + '\'' +
                ", address='" + address + '\'' +
                ", primaryRole='" + primaryRole + '\'' +
                ", secondaryRoles='" + secondaryRoles + '\'' +
                ", secondaryRolesOther='" + secondaryRolesOther + '\'' +
                ", interest='" + interest + '\'' +
                ", interestOther='" + interestOther + '\'' +
                ", specialInterest='" + specialInterest + '\'' +
                ", searchable=" + searchable +
                ", profileUrl=" + profileUrl +
                ", centreLeader=" + centreLeader +
                ", userRoles=" + userRoles +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PortalUser that = (PortalUser) o;

        if (address != null ? !address.equals(that.address) : that.address != null) {
            return false;
        }
        if (centre != null ? !centre.equals(that.centre) : that.centre != null) {
            return false;
        }
        if (country != null ? !country.equals(that.country) : that.country != null) {
            return false;
        }
        if (email != null ? !email.equals(that.email) : that.email != null) {
            return false;
        }
        if (fax != null ? !fax.equals(that.fax) : that.fax != null) {
            return false;
        }
        if (interest != null ? !interest.equals(that.interest) : that.interest != null) {
            return false;
        }
        if (interestOther != null ? !interestOther.equals(that.interestOther) : that.interestOther != null) {
            return false;
        }
        if (name != null ? !name.equals(that.name) : that.name != null) {
            return false;
        }
        if (position != null ? !position.equals(that.position) : that.position != null) {
            return false;
        }
        if (primaryRole != null ? !primaryRole.equals(that.primaryRole) : that.primaryRole != null) {
            return false;
        }
        if (searchable != null ? !searchable.equals(that.searchable) : that.searchable != null) {
            return false;
        }
        if (secondaryRoles != null ? !secondaryRoles.equals(that.secondaryRoles) : that.secondaryRoles != null) {
            return false;
        }
        if (secondaryRolesOther != null ? !secondaryRolesOther.equals(that.secondaryRolesOther) : that.secondaryRolesOther != null) {
            return false;
        }
        if (society != null ? !society.equals(that.society) : that.society != null) {
            return false;
        }
        if (specialInterest != null ? !specialInterest.equals(that.specialInterest) : that.specialInterest != null) {
            return false;
        }
        if (tel != null ? !tel.equals(that.tel) : that.tel != null) {
            return false;
        }
        if (profileUrl != null ? !profileUrl.equals(that.profileUrl) : that.profileUrl != null) {
            return false;
        }
        if (centreLeader != null ? !centreLeader.equals(that.centreLeader) : that.centreLeader != null) {
            return false;
        }
        return !(userRoles != null ? !userRoles.equals(that.userRoles) : that.userRoles != null);
    }

    @Override
    public int hashCode() {
        int result = userRoles != null ? userRoles.hashCode() : 0;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (centre != null ? centre.hashCode() : 0);
        result = 31 * result + (tel != null ? tel.hashCode() : 0);
        result = 31 * result + (fax != null ? fax.hashCode() : 0);
        result = 31 * result + (position != null ? position.hashCode() : 0);
        result = 31 * result + (society != null ? society.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (primaryRole != null ? primaryRole.hashCode() : 0);
        result = 31 * result + (secondaryRoles != null ? secondaryRoles.hashCode() : 0);
        result = 31 * result + (secondaryRolesOther != null ? secondaryRolesOther.hashCode() : 0);
        result = 31 * result + (interest != null ? interest.hashCode() : 0);
        result = 31 * result + (interestOther != null ? interestOther.hashCode() : 0);
        result = 31 * result + (specialInterest != null ? specialInterest.hashCode() : 0);
        result = 31 * result + (profileUrl != null ? profileUrl.hashCode() : 0);
        result = 31 * result + (centreLeader != null ? centreLeader.hashCode() : 0);
        result = 31 * result + (searchable != null ? searchable.hashCode() : 0);
        return result;
    }

    public PortalUser copy(PortalUser source) {
        //copy values
        if (StringUtils.isNotBlank(source.getUsername())) {
            this.setUsername(source.getUsername());
        }
        if (StringUtils.isNotBlank(source.getName())) {
            this.setName(source.getName());
        }
        if (StringUtils.isNotBlank(source.getEmail())) {
            this.setEmail(source.getEmail());
        }
        if (StringUtils.isNotBlank(source.getCountry())) {
            this.setCountry(source.getCountry());
        }
        if (StringUtils.isNotBlank(source.getCentre())) {
            this.setCentre(source.getCentre());
        }
        if (StringUtils.isNotBlank(source.getTel())) {
            this.setTel(source.getTel());
        }
        if (StringUtils.isNotBlank(source.getFax())) {
            this.setFax(source.getFax());
        }
        if (StringUtils.isNotBlank(source.getAddress())) {
            this.setAddress(source.getAddress());
        }
        if (StringUtils.isNotBlank(source.getSociety())) {
            this.setSociety(source.getSociety());
        }
        if (StringUtils.isNotBlank(source.getPosition())) {
            this.setPosition(source.getPosition());
        }
        if (StringUtils.isNotBlank(source.getPrimaryRole())) {
            this.setPrimaryRole(source.getPrimaryRole());
        }
        if (StringUtils.isNotBlank(source.getSecondaryRoles())) {
            this.setSecondaryRoles(source.getSecondaryRoles());
        }
        if (StringUtils.isNotBlank(source.getSecondaryRolesOther())) {
            this.setSecondaryRolesOther(source.getSecondaryRolesOther());
        }
        if (StringUtils.isNotBlank(source.getInterest())) {
            this.setInterest(source.getInterest());
        }
        if (StringUtils.isNotBlank(source.getInterestOther())) {
            this.setInterestOther(source.getInterestOther());
        }
        if (StringUtils.isNotBlank(source.getSpecialInterest())) {
            this.setSpecialInterest(source.getSpecialInterest());
        }
        if (StringUtils.isNotBlank(source.getProfileUrl())) {
            this.setProfileUrl(source.getProfileUrl());
        }
        if (StringUtils.isNotBlank(source.getCentreLeader())) {
            this.setCentreLeader(source.getCentreLeader());
        }
        if (source.getSearchable() != null) {
            this.setSearchable(source.getSearchable());
        }
        return this;
    }
}