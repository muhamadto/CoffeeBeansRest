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
package com.coffeebeans.services.controller.rest.version;

import com.coffeebeans.services.api.version.ApiVersion;
import com.coffeebeans.services.controller.base.BaseController;
import com.coffeebeans.services.service.version.VersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by muhamadto on 4/07/2015.
 */

@RestController
public class ApiVersionController extends BaseController {

    @Autowired
    private VersionService versionService;

    @ResponseBody
    @RequestMapping(value="version", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiVersion getAPIVersion() throws Exception {
        return versionService.getVersion();
    }
}
