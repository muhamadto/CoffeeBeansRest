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
package com.coffeebeans.services.exception.validation;

import com.coffeebeans.services.api.error.ValidationError;

import javax.validation.ConstraintViolation;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by muhamadto on 12/07/2015.
 */
public class ValidationException extends RuntimeException{
    private List<ValidationError> validationErrors = new ArrayList<ValidationError>();
    public ValidationException (){
        super();
    }

    public ValidationException (String message){
        super(message);
    }

    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }
    public ValidationException(Throwable cause) {
        super(cause);
    }

    protected ValidationException(String message, Throwable cause,
                                  boolean enableSuppression,
                                  boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ValidationException(Set<? extends ConstraintViolation<?>> constraintViolations) {
        Iterator<? extends ConstraintViolation<?>> iterator = constraintViolations.iterator();

        while(iterator.hasNext()){
            ConstraintViolation invalidConstraint = iterator.next();
            ValidationError validationError = new ValidationError();
            validationError.setMessage(invalidConstraint.getMessage());
            validationError.setPropertyName(String.valueOf(invalidConstraint.getPropertyPath()));
            validationError.setPropertyValue(String.valueOf(invalidConstraint.getInvalidValue()));
            validationErrors.add(validationError);
        }
    }

    public List<ValidationError> getValidationErrors() {
        return validationErrors;
    }
}
