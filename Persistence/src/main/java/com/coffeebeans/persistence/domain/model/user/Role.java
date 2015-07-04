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
package com.coffeebeans.persistence.domain.model.user;

/**
 * Created by muhamadto on 5/07/2015.
 */
public enum Role {

    ROLE_ANONYMOUS("ROLE_ANONYMOUS"),
    ROLE_USER("ROLE_USER"),
    ROLE_ADMIN("ROLE_ADMIN");

    private String userAuthorityType;
    Role(String userAuthorityType){
        this.userAuthorityType= userAuthorityType;
    }

    public String toString() {
        return userAuthorityType;
    }
}

