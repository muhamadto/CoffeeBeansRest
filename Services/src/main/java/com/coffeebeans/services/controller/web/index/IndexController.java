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
package com.coffeebeans.services.controller.web.index;

import com.coffeebeans.services.controller.base.BaseController;
import com.coffeebeans.services.service.version.VersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by muhamadto on 4/07/2015.
 */

@Controller
public class IndexController extends BaseController {

    @Autowired
    private VersionService versionService;

    @RequestMapping(value={"","index**", "welcome**"}, method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView displayAPIVersion() throws Exception {
        Map<String, Object> model = new HashMap<>();
        model.put("apiversion",  versionService.getVersion().getApiVersion());
        ModelAndView modelAndView = new ModelAndView("version", model);

        return modelAndView;
    }
}
