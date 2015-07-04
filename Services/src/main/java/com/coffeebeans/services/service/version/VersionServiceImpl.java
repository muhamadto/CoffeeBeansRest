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
package com.coffeebeans.services.service.version;

import com.coffeebeans.services.api.version.ApiVersion;
import com.coffeebeans.services.service.base.BaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

/**
 * Created by muhamadto on 19/07/2015.
 */
@Service("versionService")
public class VersionServiceImpl extends BaseService implements VersionService{
    public static Logger LOGGER = LoggerFactory.getLogger(VersionServiceImpl.class);

    @Autowired
    MessageSource messageSource;

    @Override
    public ApiVersion getVersion() {
        ApiVersion version = new ApiVersion();

        String buildVersion = messageSource.getMessage("build.version", null,  Locale.getDefault());
        version.setBuildVersion(buildVersion);

        String buildTime = messageSource.getMessage("build.time", null,  Locale.getDefault());
        version.setTimestamp(buildTime);

        String apiVersion = messageSource.getMessage("api.version", null,  Locale.getDefault());
        version.setApiVersion(apiVersion);

        LOGGER.debug("API version is [{}]", version);
        return version;
    }
}
