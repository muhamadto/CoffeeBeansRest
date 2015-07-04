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
package com.coffeebeans.utilities.generic;

/**
 * Created by muhamadto on 2/08/2015.
 */
public class Constants {
    public static final String REDIS_HOST_NAME="REDIS_HOST";
    public static final String REDIS_PORT ="REDIS_PORT";
    public static final long REDIS_DEFAULT_EXPIRY_TIME=300;

    public static final String COFFEEBEANS_REPOSITORY_PACKAGE="repository.package";
    public static final String COFFEEBEANS_MODEL_PACKAGE="model.package";

    public static final String JDBC_DRIVER_CLASS="jdbc.driverClass";
    public static final String DB_URL="DB_URL";
    public static final String DB_USERNAME="DB_USERNAME";
    public static final String DB_PASSWORD="DB_PASSWORD";

    public static final String BONECP_IDLE_CONNECTION_TEST_PERIOD_IN_MINUTES="bonecp.idleConnectionTestPeriodInMinutes";
    public static final String BONECP_IDLE_MAX_AGE_IN_MINUTES="bonecp.idleMaxAgeInMinutes";
    public static final String BONECP_MAX_CONNECTIONS_PER_PARTITION="bonecp.maxConnectionsPerPartition";
    public static final String BONECP_MIN_CONNECTIONS_PER_PARTITION="bonecp.minConnectionsPerPartition";
    public static final String BONECP_PARTITION_COUNT="bonecp.partitionCount";
    public static final String BONECP_ACQUIRE_INCREMENT="bonecp.acquireIncrement";
    public static final String BONECP_STATEMENTS_CACHE_SIZE="bonecp.statementsCacheSize";

    public static final String HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE="hibernate.cache.use_second_level_cache";
    public static final String HIBERNATE_CACHE_REGION_FACTORY_CLASS="hibernate.cache.region.factory_class";
    public static final String HIBERNATE_CACHE_USE_QUERY_CACHE="hibernate.cache.use_query_cache";
    public static final String HIBERNATE_GENERATE_STATISTICS="hibernate.generate_statistics";
    public static final String HIBERNATE_DIALECT="hibernate.dialect";

    public static final String LOCALHOST="localhost";

    public final static String MVC_DISPATCHER_NAME = "mvc-dispatcher";

    public static final String COFFEEBEAN_RESOURCE_ID = "coffeebean";

}
