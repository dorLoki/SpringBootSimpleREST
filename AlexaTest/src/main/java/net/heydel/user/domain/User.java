package net.heydel.user.domain;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import net.heydel.common.BaseEntity;
import net.heydel.security.UserRole;

/**
 * Eine Entität, die einen User repräsentiert und UserDetails implementiert, um
 * die Integration mit Spring Security zu ermöglichen.
 */
@Entity
public class User extends BaseEntity implements UserDetails {

    /**
     * Die eindeutige Versions-ID für die Serialisierung.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Konstruktor für die User-Klasse mit Parametern.
     * 
     * @param name      Der Name des Users.
     * @param loginName Der Benutzername für das Login.
     * @param password  Das Password des Users.
     * @param roles     Die Rollen/Rechte des Users.
     */
    public User(String name, String loginName, String password, Set<UserRole> roles) {
        this.setName(name);
        this.setLoginName(loginName);
        this.setPasswordHash(password);
        this.setRoles(roles);
    }

    /**
     * Standard Konstruktor für Spring.
     */
    public User() {
    }

    /**
     * Der Name des Users.
     */
    private String name;
    /**
     * Der Benutzername des Users für die Anmeldung.
     */
    private String loginName;
    /**
     * Der gehashte Passwortwert des Users.
     */
    private String passwordHash;
    /**
     * Die Rollen des Users.
     */
    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    private Set<UserRole> roles = new HashSet<>();

    // UserDetails Override
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();
        for (UserRole role : roles) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.name()));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.passwordHash;
    }

    @Override
    public String getUsername() {
        return this.loginName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    // Getter Setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String password) {
        this.passwordHash = new BCryptPasswordEncoder().encode(password);
    }

    public Set<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<UserRole> roles) {
        this.roles = roles;
    }

    public void addRole(UserRole role) {
        this.roles.add(role);
    }
}
