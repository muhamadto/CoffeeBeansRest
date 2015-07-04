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
package com.coffeebeans.persistence.config;

import com.coffeebeans.utilities.generic.Constants;
import com.jolbox.bonecp.BoneCPDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by muhamadto on 12/07/2015.
 */

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"com.coffeebeans.persistence.repository"})
@PropertySource("classpath:persistence-default.properties")
public class PersistenceConfig {
    @Autowired
    Environment env;

    @Bean(destroyMethod = "close")
    public DataSource dataSource() {
        BoneCPDataSource dataSource = new BoneCPDataSource();
        dataSource.setDriverClass(env.getProperty(Constants.JDBC_DRIVER_CLASS));
        dataSource.setJdbcUrl(System.getProperty(Constants.DB_URL));
        dataSource.setUsername(System.getProperty(Constants.DB_USERNAME));
        dataSource.setPassword(System.getProperty(Constants.DB_PASSWORD));
        dataSource.setIdleConnectionTestPeriodInMinutes(Integer.parseInt(env.getProperty(Constants.BONECP_IDLE_CONNECTION_TEST_PERIOD_IN_MINUTES)));
        dataSource.setIdleMaxAgeInMinutes(Integer.parseInt(env.getProperty(Constants.BONECP_IDLE_MAX_AGE_IN_MINUTES)));
        dataSource.setMaxConnectionsPerPartition(Integer.parseInt(env.getProperty(Constants.BONECP_MAX_CONNECTIONS_PER_PARTITION)));
        dataSource.setMinConnectionsPerPartition(Integer.parseInt(env.getProperty(Constants.BONECP_MIN_CONNECTIONS_PER_PARTITION)));
        dataSource.setPartitionCount(Integer.parseInt(env.getProperty(Constants.BONECP_PARTITION_COUNT)));
        dataSource.setAcquireIncrement(Integer.parseInt(env.getProperty(Constants.BONECP_ACQUIRE_INCREMENT))) ;
        dataSource.setStatementsCacheSize( Integer.parseInt(env.getProperty(Constants.BONECP_STATEMENTS_CACHE_SIZE)));
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan(env.getProperty(Constants.COFFEEBEANS_MODEL_PACKAGE));
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(additionalProperties());
        return em;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);

        return transactionManager;
    }

    private Properties additionalProperties() {
        Properties prop = new Properties();
        prop.setProperty(Constants.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE, env.getProperty(Constants.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
        prop.setProperty(Constants.HIBERNATE_CACHE_REGION_FACTORY_CLASS, env.getProperty(Constants.HIBERNATE_CACHE_REGION_FACTORY_CLASS));
        prop.setProperty(Constants.HIBERNATE_CACHE_USE_QUERY_CACHE, env.getProperty(Constants.HIBERNATE_CACHE_USE_QUERY_CACHE));
        prop.setProperty(Constants.HIBERNATE_GENERATE_STATISTICS, env.getProperty(Constants.HIBERNATE_DIALECT));
        prop.setProperty(Constants.HIBERNATE_DIALECT, env.getProperty(Constants.HIBERNATE_DIALECT));
        return prop;
    }
}
