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
import com.coffeebeans.persistence.domain.model.user.User;
import com.coffeebeans.persistence.domain.request.user.UserSignupRequest;
import com.coffeebeans.persistence.domain.response.UserResponse;
import com.coffeebeans.persistence.repository.user.UserRepository;
import com.coffeebeans.services.exception.general.DuplicateEntryException;
import com.coffeebeans.services.exception.general.NotFoundException;
import com.coffeebeans.services.service.base.BaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static org.springframework.util.Assert.notNull;

/**
 * Created by muhamadto on 12/07/2015.
 */

@Service("userService")
public class UserServiceImpl extends BaseService implements UserService {
    public static Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public UserResponse createUser(UserSignupRequest userSignupRequest, Role role) {
        validate(userSignupRequest);

        UserResponse newUser;

        final String email = userSignupRequest.getEmail().toLowerCase();
        if(this.userRepository.findByEmail(email) == null){
            LOGGER.info("User does not  exist - creating a new user [{}].", userSignupRequest.getUsername());

            User user = new User(userSignupRequest);
            user.setRole(role);
            Date now = new Date();

            // ugh code smell
            // TODO don't have global mutable state inside the method, move this code
            user.setInsertDate(now);
            user.setLastModified(now);

            user = this.userRepository.save(user);

            newUser = UserResponse.convert(user);

            LOGGER.debug("Created new user [{}].", user);
        } else{
            LOGGER.error("Duplicate user located [{}], exception raised with appropriate HTTP response code.", email);
            throw new DuplicateEntryException("User already exists.");
        }
        return newUser;
    }

    @Override
    @Cacheable(value = "users")
    public UserResponse getUserByUsername(String username) {
        UserResponse userResponse;
        User user = doGetUserByUsername(username);
        userResponse = UserResponse.convert(user);
        LOGGER.debug("User has been found [{}].", user);
        return userResponse;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return doGetUserByUsername(username);
    }

    private User doGetUserByUsername(String username) {
        notNull(username, "Mandatory argument 'username' missing.");
        User user = userRepository.findByUsername(username.toLowerCase());
        if (user == null) {
            LOGGER.debug("Credentials [{}] failed to locate a user.", username.toLowerCase());
            throw new NotFoundException(String.format("Could not find a user for search criteria [username = %s]", username));
        }
        return user;
    }
}
