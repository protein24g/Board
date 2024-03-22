package com.study.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class DatabaseConfig {
    @Autowired
    private ApplicationContext context;

    @Autowired
    private Environment env;

    // Board DataSource Configuration
    @Primary
    @Bean(name = "boardHikariConfig")
    @ConfigurationProperties(prefix = "spring.datasource.board")
    public HikariConfig boardHikariConfig() {
        return new HikariConfig();
    }

    @Primary
    @Bean(name = "boardDataSource")
    public DataSource boardDataSource() {
        return new HikariDataSource(boardHikariConfig());
    }

    // Member DataSource Configuration
    @Bean(name = "memberHikariConfig")
    @ConfigurationProperties(prefix = "spring.datasource.member")
    public HikariConfig memberHikariConfig() {
        return new HikariConfig();
    }

    @Bean(name = "memberDataSource")
    public DataSource memberDataSource() {
        return new HikariDataSource(memberHikariConfig());
    }
}