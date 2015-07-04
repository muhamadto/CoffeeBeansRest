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
package com.coffeebeans.services.service.oauth2;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.annotation.Jsr250SecurityConfig;
import org.springframework.security.access.annotation.Jsr250Voter;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.Assert;

import java.util.Collection;

/**
 * Created by mohamedhamtou on 17/02/15.
 */
public class HierarchicalJsr250Voter extends Jsr250Voter {

    private RoleHierarchy roleHierarchy = null;

    public HierarchicalJsr250Voter(RoleHierarchy roleHierarchy) {
        Assert.notNull(roleHierarchy, "RoleHierarchy must not be null");
        this.roleHierarchy = roleHierarchy;
    }

    @Override
    public int vote(Authentication authentication, Object object, Collection<ConfigAttribute> definition) {
        boolean jsr250AttributeFound = false;

        for (ConfigAttribute attribute : definition) {
            if (Jsr250SecurityConfig.PERMIT_ALL_ATTRIBUTE.equals(attribute)) {
                return ACCESS_GRANTED;
            }

            if (Jsr250SecurityConfig.DENY_ALL_ATTRIBUTE.equals(attribute)) {
                return ACCESS_DENIED;
            }

            if (supports(attribute)) {
                jsr250AttributeFound = true;
                // Attempt to find a matching granted authority
                for (GrantedAuthority authority : extractAuthorities(authentication)) {
                    if (attribute.getAttribute().equals(authority.getAuthority())) {
                        return ACCESS_GRANTED;
                    }
                }
            }
        }

        return jsr250AttributeFound ? ACCESS_DENIED : ACCESS_ABSTAIN;
    }

    Collection<? extends GrantedAuthority> extractAuthorities(Authentication authentication) {
        return roleHierarchy.getReachableGrantedAuthorities(authentication.getAuthorities());
    }
}
