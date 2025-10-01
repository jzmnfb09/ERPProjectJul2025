package com.example.nerp.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class DataSourceConfig {

    @Value("${spring.datasource.url}")
    private String db2Url;

    @Value("${spring.datasource.username}")
    private String db2Username;

    @Value("${spring.datasource.password}")
    private String db2Password;

    @Value("${psql.datasource.url}")
    private String psqlUrl;

    @Value("${psql.datasource.username}")
    private String psqlUsername;

    @Value("${psql.datasource.password}")
    private String psqlPassword;

    // Configuración del DataSource para DB2 (AS400)
    @Primary
    @Bean(name = "db2DataSource")
    @Qualifier("db2")
    DataSource dataSourceDB2() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.ibm.as400.access.AS400JDBCDriver");
        dataSource.setUrl(db2Url);
        dataSource.setUsername(db2Username);
        dataSource.setPassword(db2Password);
        return dataSource;
    }

    // Configuración del DataSource para PostgreSQL
    @Bean(name = "psqlDataSource")
    @Qualifier("psql")
    DataSource dataSourcePostgres() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl(psqlUrl);
        dataSource.setUsername(psqlUsername);
        dataSource.setPassword(psqlPassword);
        return dataSource;
    }

    // Configuración del JdbcTemplate para DB2
    @Primary
    @Bean(name = "db2JdbcTemplate")
    JdbcTemplate jdbcTemplateDB2(@Qualifier("db2DataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    // Configuración del JdbcTemplate para PostgreSQL
    @Bean(name = "psqlJdbcTemplate")
    JdbcTemplate jdbcTemplatePostgres(@Qualifier("psqlDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
