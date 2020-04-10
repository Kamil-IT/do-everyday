package com.doeveryday.doeverydaysecurity.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import java.util.Set;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Entity
@Table(name = "User")
public class AppUser extends BaseEntity implements UserDetails {

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "account_non_expired", nullable = false, columnDefinition = "bit default 1")
    private boolean accountNonExpired;

    @Column(name = "account_non_locked", nullable = false, columnDefinition = "bit default 1")
    private boolean accountNonLocked;

    @Column(name = "credentials_non_expired", nullable = false, columnDefinition = "bit default 1")
    private boolean credentialsNonExpired;

    @Column(name = "enabled", nullable = false, columnDefinition = "bit default true")
    private boolean enabled;

    private AppUserRole role;

    @Lob
    private byte [] photo;

    @Email
    private String email;

    @Builder
    public AppUser(UUID id, String username, String password, boolean accountNonExpired, boolean accountNonLocked,
                   boolean credentialsNonExpired, boolean enabled, AppUserRole role, byte [] photo, String email) {
        super(id);
        this.username = username;
        this.password = password;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
        this.enabled = enabled;
        this.role = role;
        this.photo = photo;
        this.email = email;
    }

    @Override
    public Set<GrantedAuthority> getAuthorities() {
        return role.getAuthorities();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
