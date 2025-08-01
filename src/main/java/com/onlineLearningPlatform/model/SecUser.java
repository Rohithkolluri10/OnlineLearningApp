package com.onlineLearningPlatform.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class SecUser implements UserDetails {

    private String name;

    private String emailAddress;

    private String password;

    private boolean blocked;

    private boolean active;

    private List<GrantedAuthority> authorities;

    public SecUser(User user){
        this.name = user.getName();
        this.emailAddress=user.getEmailAddress();
        this.password=user.getPassword();
        this.blocked=user.isBlocked();
        this.active=user.isActive();
        this.authorities = user.getRoles().stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role.name())).collect(Collectors.toUnmodifiableList());
        }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.emailAddress;

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
}

