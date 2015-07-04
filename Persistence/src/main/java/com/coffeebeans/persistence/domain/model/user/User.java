/*
 * Copyright (C) 2015 muhamadto
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.coffeebeans.persistence.domain.model.user;

import com.coffeebeans.persistence.domain.model.base.DomainObject;
import com.coffeebeans.persistence.domain.request.user.UserSignupRequest;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Created by mohamedhamtou on 28/12/14.
 */
@Entity
@Table(name="USER")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@ToString(exclude={"password", "role", "enabled", "verified"})
@EqualsAndHashCode(callSuper = false)
@Getter
@Setter
public class User extends DomainObject implements UserDetails {
    @Column(length = 36, unique = true, nullable = false)
    protected String username;

    @Column(length = 36, unique = true, nullable = false)
    protected String email;

    @Column(length = 16, nullable = false)
    protected String password;

    @Type(type = "numeric_boolean")
    protected boolean enabled;
    
    @Type(type = "numeric_boolean")
    protected boolean verified = false;

    @Enumerated(EnumType.STRING)
    protected Role role;
    
    public User() {
        this(UUID.randomUUID());
    }

    public User(UUID uuid) {
        super(uuid);
        setRole(Role.ROLE_ANONYMOUS);
    }
    public User(String username, String email, String encryptedPassword) {
        super(UUID.randomUUID());
        this.username = username.toLowerCase();
        this.email = email.toLowerCase();
        this.password = encryptedPassword;
    }

    public User(UserSignupRequest userSignupRequest) {
        super(UUID.randomUUID());
        this.username = userSignupRequest.getUsername().toLowerCase();
        this.email = userSignupRequest.getEmail().toLowerCase();
        this.password = userSignupRequest.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        GrantedAuthority authority = new SimpleGrantedAuthority(role.name());
        authorities.add(authority);
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return enabled;
    }

    @Override
    public boolean isAccountNonLocked() {
        return enabled;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return enabled;
    }
}