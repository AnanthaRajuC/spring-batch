package org.platformcommons.config.db;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "assessmentEntityManagerFactory",
        transactionManagerRef = "assessmentTransactionManager",
        basePackages = { "org.platformcommons.domain.assessmentdb" }
)
public class AssessmentDBConfig {

    @Bean(name="assessmentDataSource")
    @ConfigurationProperties(prefix="spring.assessmentdb")
    public DataSource assessmentDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "assessmentEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean assessmentEntityManagerFactory(EntityManagerFactoryBuilder builder,
                                                                                 @Qualifier("assessmentDataSource") DataSource assessmentDataSource) {
        return builder
                .dataSource(assessmentDataSource)
                .packages("org.platformcommons.domain.assessmentdb")
                .build();
    }

    @Bean(name = "assessmentTransactionManager")
    public PlatformTransactionManager assessmentTransactionManager(
            @Qualifier("assessmentEntityManagerFactory") EntityManagerFactory assessmentEntityManagerFactory) {
        return new JpaTransactionManager(assessmentEntityManagerFactory);
    }
}
