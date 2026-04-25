package com.br.reusai.api.domain.config;

import com.br.reusai.api.gateway.postgre.repository.ImagesRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        basePackageClasses = ImagesRepository.class,
        entityManagerFactoryRef = "postgreSQLEntityManager",
        transactionManagerRef = "postgreSQLTransactionManager")
public class PostgreSQLConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.postgresql")
    public DataSource postgreSQLDataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean postgreSQLEntityManager(
            EntityManagerFactoryBuilder builder,
            @Qualifier("postgreSQLDataSource") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages("com.br.reusai.api.gateway.postgre.entity")
                .persistenceUnit("postgreSQL")
                .build();
    }

    @Bean
    public PlatformTransactionManager postgreSQLTransactionManager(
            @Qualifier("postgreSQLEntityManager") LocalContainerEntityManagerFactoryBean entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory.getObject());
    }
}
