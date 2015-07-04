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
package com.coffeebeans.services.service.base;

import com.coffeebeans.services.exception.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

/**
 * Created by muhamadto on 12/07/2015.
 */
public abstract class BaseService {

    @Autowired
    Validator validator;

    protected void validate(Object request) throws ValidationException {
        Set<? extends ConstraintViolation<?>> constraintViolations = validator.validate(request);
        if (!CollectionUtils.isEmpty(constraintViolations)) {
            throw new ValidationException(constraintViolations);
        }
    }
}
