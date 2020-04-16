package com.doeveryday.doeverydaysecurity.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Set;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Entity
@Table(name = "User")
public class AppUser extends BaseEntity implements UserDetails {

    @NotNull
    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @NotNull
    @Column(name = "password", nullable = false)
    private String password;

    @NotNull
    @Column(name = "account_non_expired", columnDefinition = "bit default 1", nullable = false)
    private boolean accountNonExpired;

    @NotNull
    @Column(name = "account_non_locked", columnDefinition = "bit default 1", nullable = false)
    private boolean accountNonLocked;

    @NotNull
    @Column(name = "credentials_non_expired", columnDefinition = "bit default 1", nullable = false)
    private boolean credentialsNonExpired;

    @NotNull
    @Column(name = "enabled", columnDefinition = "bit default true", nullable = false)
    private boolean enabled;

    @NotNull
    @Column(nullable = false)
    private AppUserRole role;

    @Nullable
    @Lob
    private byte [] photo;

    @Nullable
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

    /**
     * This method set:
     *         accountNonExpired = true
     *         accountNonLocked = true
     *         credentialsNonExpired = true
     *         enabled = true
     */
    public void setDefaultValueToWorkAccount(){
        accountNonExpired = true;
        accountNonLocked = true;
        credentialsNonExpired = true;
        enabled = true;
    }

    @PrePersist
    public void setDefaultRoleToUser(){
        if (role == null){
            role = AppUserRole.USER;
        }
    }
}
