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
package com.coffeebeans.persistence.domain.model.base;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

/**
 * Created by muhamadto on 12/07/2015.
 */

@MappedSuperclass
@Data
public class DomainObject extends AbstractPersistable<Long> {

    @Version
    protected int version;

    @Column(length = 36, unique = true, nullable = false)
    protected String uuid;

    @CreatedDate
    protected Date insertDate;

    @LastModifiedDate
    protected Date lastModified;

    public DomainObject() {
        this(UUID.randomUUID());
    }

    public DomainObject(UUID uuid) {
        Assert.notNull(uuid, "UUID is required");
        this.uuid = uuid.toString();
        this.insertDate = new Date();
    }
}
