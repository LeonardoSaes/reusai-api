package com.br.reusai.api.domain.config;

import com.br.reusai.api.gateway.mysql.repository.ItemRepository;
import com.br.reusai.api.gateway.postgre.repository.ImagesRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        basePackageClasses = ImagesRepository.class,
        entityManagerFactoryRef = "postgreSQLEntityManager")
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
                .build();

    }
}
