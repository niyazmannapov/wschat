package ru.itis.chat.websockets.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
@ComponentScan(basePackages = "ru.itis.chat.websockets")
public class ApplicationContextConfig {

    @Autowired
    private Environment environment;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(hikariDataSource());
    }

    @Bean
    public DataSource hikariDataSource() {
        return new HikariDataSource(hikariConfig());
    }

    @Bean
    public HikariConfig hikariConfig() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(environment.getProperty("db.url"));
        config.setUsername(environment.getProperty("db.user"));
        config.setPassword(environment.getProperty("db.password"));
        config.setDriverClassName(environment.getProperty("db.driver"));
        return config;
    }
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(DataSource dataSource) {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setDatabase(Database.POSTGRESQL);

        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();

        entityManagerFactory.setDataSource(dataSource);
        entityManagerFactory.setJpaVendorAdapter(vendorAdapter);
        entityManagerFactory.setPackagesToScan("ru.itis.chat.websockets.models");
        entityManagerFactory.setJpaProperties(jpaProperties());

        return entityManagerFactory;
    }

    @Bean
    public Properties jpaProperties() {
        Properties properties = new Properties();

        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL10Dialect");

        return properties;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);

        return transactionManager;
    }





}

