package org.platformcommons.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "ptldEntityManagerFactory",
        transactionManagerRef = "ptldTransactionManager",
        basePackages = { "org.platformcommons.domain.ptld" }
)
public class PtldDBConfig {

    @Bean(name="ptldDataSource")
    @Primary
    @ConfigurationProperties(prefix="spring.ptlddb")
    public DataSource ptldDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "ptldEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean ptldEntityManagerFactory(EntityManagerFactoryBuilder builder,
                                                                           @Qualifier("ptldDataSource") DataSource ptldDataSource) {
        return builder
                .dataSource(ptldDataSource)
                .packages("org.platformcommons.domain.ptld")
                .build();
    }

    @Bean(name = "ptldTransactionManager")
    public PlatformTransactionManager ptldTransactionManager(
            @Qualifier("ptldEntityManagerFactory") EntityManagerFactory ptldEntityManagerFactory) {
        return new JpaTransactionManager(ptldEntityManagerFactory);
    }
}