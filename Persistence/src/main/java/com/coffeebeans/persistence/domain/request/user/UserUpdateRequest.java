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
package com.coffeebeans.persistence.domain.request.user;

import com.coffeebeans.persistence.domain.model.user.Role;
import lombok.Data;
import org.hibernate.validator.constraints.Email;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public @Data class UserUpdateRequest {
    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull @Email
    private String emailAddress;

    @NotNull @Valid
    Role role;
}
