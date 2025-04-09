package com.pranjalkaler.server.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DataBaseSetupConfigurations {

    private final DataBaseConfigurations dataBaseConfigurations;

    public DataBaseSetupConfigurations(DataBaseConfigurations dataBaseConfigurations) {
        this.dataBaseConfigurations = dataBaseConfigurations;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(dataBaseConfigurations.getDriverClassName());
        dataSource.setUrl(dataBaseConfigurations.getUrl());
        dataSource.setUsername(dataBaseConfigurations.getUsername());
        dataSource.setPassword(dataBaseConfigurations.getPassword());
        return dataSource;
    }

}
