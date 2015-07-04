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
package com.coffeebeans.services.controller.rest.user;

import com.coffeebeans.persistence.domain.model.user.Role;
import com.coffeebeans.persistence.domain.request.user.UserSignupRequest;
import com.coffeebeans.persistence.domain.response.UserResponse;
import com.coffeebeans.services.controller.base.BaseController;
import com.coffeebeans.services.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

/**
 * Created by muhamadto on 12/07/2015.
 */

@RestController
@RequestMapping(value = "user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController extends BaseController {
    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public UserResponse signupUser(@RequestBody final UserSignupRequest userSignupRequest){
        UserResponse userResponse = this.userService.createUser(userSignupRequest, Role.ROLE_ANONYMOUS);
        URI location = this.buildLocationFromCurrentRequestUri(userResponse.getUsername());
        userResponse.setLocation(location);
        return userResponse;
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/{username}")
    public UserResponse getUserByUsername(@PathVariable String username) {
        UserResponse userResponse = this.userService.getUserByUsername(username);
        URI location = this.buildLocationFromCurrentServletMapping("user", userResponse.getUsername());
        userResponse.setLocation(location);
        return userResponse;
    }
}
