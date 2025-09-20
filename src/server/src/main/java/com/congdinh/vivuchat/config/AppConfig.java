package com.congdinh.vivuchat.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Spring Core Java-based Configuration class
 * Configures DataSource, SessionFactory, and Transaction Management
 * Includes component scanning for Spring services and repositories
 */
@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = "com.congdinh.vivuchat")
@PropertySource("classpath:database.properties")
public class AppConfig {

    // Database connection properties
    @Value("${db.driver}")
    private String driverClassName;
    
    @Value("${db.url}")
    private String url;
    
    @Value("${db.username}")
    private String username;
    
    @Value("${db.password}")
    private String password;
    
    // HikariCP configuration properties
    @Value("${db.hikari.connection-timeout}")
    private long connectionTimeout;
    
    @Value("${db.hikari.maximum-pool-size}")
    private int maximumPoolSize;
    
    @Value("${db.hikari.minimum-idle}")
    private int minimumIdle;
    
    @Value("${db.hikari.idle-timeout}")
    private long idleTimeout;
    
    @Value("${db.hikari.max-lifetime}")
    private long maxLifetime;
    
    // Hibernate properties
    @Value("${hibernate.dialect}")
    private String hibernateDialect;
    
    @Value("${hibernate.hbm2ddl.auto}")
    private String hibernateHbm2ddlAuto;
    
    @Value("${hibernate.show_sql}")
    private String hibernateShowSql;
    
    @Value("${hibernate.format_sql}")
    private String hibernateFormatSql;
    
    @Value("${hibernate.use_sql_comments}")
    private String hibernateUseSqlComments;
    
    @Value("${hibernate.jdbc.batch_size}")
    private String hibernateBatchSize;
    
    @Value("${hibernate.connection.autocommit}")
    private String hibernateAutocommit;

    /**
     * Configure DataSource using HikariCP connection pool
     */
    @Bean(name = "dataSource")
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName(driverClassName);
        config.setJdbcUrl(url);
        config.setUsername(username);
        config.setPassword(password);
        config.setConnectionTimeout(connectionTimeout);
        config.setMaximumPoolSize(maximumPoolSize);
        config.setMinimumIdle(minimumIdle);
        config.setIdleTimeout(idleTimeout);
        config.setMaxLifetime(maxLifetime);
        config.setLeakDetectionThreshold(60000);
        config.setConnectionTestQuery("SELECT 1");
        
        return new HikariDataSource(config);
    }

    /**
     * Configure Hibernate SessionFactory
     */
    @Bean(name = "sessionFactory")
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan("com.congdinh.vivuchat.entities");
        sessionFactory.setHibernateProperties(hibernateProperties());
        
        return sessionFactory;
    }

    /**
     * Configure Hibernate properties
     */
    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", hibernateDialect);
        properties.setProperty("hibernate.hbm2ddl.auto", hibernateHbm2ddlAuto);
        properties.setProperty("hibernate.show_sql", hibernateShowSql);
        properties.setProperty("hibernate.format_sql", hibernateFormatSql);
        properties.setProperty("hibernate.use_sql_comments", hibernateUseSqlComments);
        properties.setProperty("hibernate.jdbc.batch_size", hibernateBatchSize);
        properties.setProperty("hibernate.connection.autocommit", hibernateAutocommit);
        properties.setProperty("hibernate.connection.pool_size", "10");
        properties.setProperty("hibernate.current_session_context_class", "thread");
        properties.setProperty("hibernate.transaction.coordinator_class", "jdbc");
        
        return properties;
    }

    /**
     * Configure Transaction Manager for Hibernate
     */
    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        return transactionManager;
    }

    /**
     * Alternative DataSource Transaction Manager (can be used instead of Hibernate Transaction Manager)
     * Uncomment if you prefer JDBC-based transaction management
     */
    /*
    @Bean(name = "dataSourceTransactionManager")
    public PlatformTransactionManager dataSourceTransactionManager() {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource());
        return transactionManager;
    }
    */
}