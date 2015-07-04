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
package com.coffeebeans.services.api.security;

import com.coffeebeans.persistence.domain.model.user.User;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import java.security.Principal;

/**
 * Created by muhamadto on 12/07/2015.
 */
@Data
public class CoffeeBeansPrincipal implements Principal {
    @NotNull @Email
    private String email;

    @NotNull
    private String username;

    @Override
    public String getName(){
        return this.username;
    }

    public CoffeeBeansPrincipal(){

    }

    public CoffeeBeansPrincipal(User user) {
        this.email = user.getEmail();
        this.username = user.getUsername();
    }

    public CoffeeBeansPrincipal(String email, String username) {
        this.email = email;
        this.username = StringUtils.defaultIfEmpty(username, StringUtils.substringBefore(email, "@"));
    }
}
