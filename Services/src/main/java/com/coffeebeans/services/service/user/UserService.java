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
package com.coffeebeans.services.service.user;

import com.coffeebeans.persistence.domain.model.user.Role;
import com.coffeebeans.persistence.domain.request.user.UserSignupRequest;
import com.coffeebeans.persistence.domain.response.UserResponse;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Created by muhamadto on 12/07/2015.
 */
public interface UserService extends UserDetailsService {
    UserResponse createUser(final UserSignupRequest userSignupRequest, Role role);
    UserResponse getUserByUsername(String username);
}
