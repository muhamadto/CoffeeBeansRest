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
package com.coffeebeans.services.controller.base;

import com.coffeebeans.services.api.error.ErrorResponse;
import com.coffeebeans.services.exception.general.DuplicateEntryException;
import com.coffeebeans.services.exception.general.NotFoundException;
import com.coffeebeans.services.exception.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

/**
 * Created by muhamadto on 4/07/2015.
 */
public abstract class BaseController {

    @ResponseBody
    @ExceptionHandler(DuplicateEntryException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    ErrorResponse handleException(DuplicateEntryException e){
        return new ErrorResponse(HttpStatus.CONFLICT.toString(), e.getMessage(), "Duplicated User");
    }

    @ResponseBody
    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    ErrorResponse handleException(ValidationException e){
        return new ErrorResponse(HttpStatus.BAD_REQUEST.toString(), e.getMessage(), "Bad Request", e.getValidationErrors());
    }

    @ResponseBody
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    ErrorResponse handleException(NotFoundException e){
        return new ErrorResponse(HttpStatus.NOT_FOUND.toString(), e.getMessage(), "NOT FOUND (aka Huh?)");
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    ErrorResponse handleException(Exception e){
        return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.toString(), e.getMessage(), "Internal Error", null);
    }


    protected URI buildLocationFromCurrentRequestUri(String username){
        URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/").path(username).build().toUri();
        return location;
    }

    protected URI buildLocationFromCurrentServletMapping(String mapping, String username){
        URI location = ServletUriComponentsBuilder.fromCurrentServletMapping().path("/").path(mapping).path("/").path(username).build().toUri();
        return location;
    }
}
