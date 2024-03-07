package org.example.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
//@EnableJpaRepositories
//        (
//        basePackages = {"org.example.jpa"},
//        entityManagerFactoryRef = "entityManagerFactory",
//        transactionManagerRef = "jpaTransactionManager"
//)
//@EnableTransactionManagement
public class DataSourceAndJpaConfig {

    @Bean(destroyMethod = "close")
    public DataSource dataSource(Environment environment) {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName(environment.getProperty("spring.datasource.driver-class-name"));
        hikariConfig.setJdbcUrl(environment.getProperty("spring.datasource.url"));
        hikariConfig.setUsername(environment.getProperty("spring.datasource.username"));
        hikariConfig.setPassword(environment.getProperty("spring.datasource.password"));
        hikariConfig.setMinimumIdle(Integer.parseInt(environment.getProperty("spring.datasource.minimum-idle")));
        hikariConfig.setMaximumPoolSize(Integer.parseInt(environment.getProperty("spring.datasource.maximum-pool-size")));
        hikariConfig.setConnectionTimeout(Long.parseLong(environment.getProperty("spring.datasource.connectionTimeout")));
        hikariConfig.setValidationTimeout(Long.parseLong(environment.getProperty("spring.datasource.validationTimeout")));

        return new HikariDataSource(hikariConfig);
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        localContainerEntityManagerFactoryBean.setDataSource(dataSource);
        localContainerEntityManagerFactoryBean.setPackagesToScan("org");

        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        localContainerEntityManagerFactoryBean.setJpaVendorAdapter(hibernateJpaVendorAdapter);
        localContainerEntityManagerFactoryBean.setJpaDialect(new HibernateJpaDialect());
        localContainerEntityManagerFactoryBean.setJpaPropertyMap(jpaProperties());
        return localContainerEntityManagerFactoryBean;
    }

    protected Map<String, Object> jpaProperties() {
        Map<String, Object> props = new HashMap<>();
//        props.put("hibernate.implicit_naming_strategy", SpringImplicitNamingStrategy.class.getName());
        props.put("javax.persistence.query.timeout", 10000);
        return props;
    }

    @Bean
    public PlatformTransactionManager jpaTransactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

}
