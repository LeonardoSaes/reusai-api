package com.br.reusai.api.domain.config;

import com.br.reusai.api.gateway.mysql.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        basePackageClasses = ItemRepository.class,
        entityManagerFactoryRef = "mySQLEntityManager")
public class MySQLConfig {

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.mysql")
    public DataSource mySQLDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean mySQLEntityManager(
            EntityManagerFactoryBuilder builder,
            @Qualifier("mySQLDataSource") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages("com.br.reusai.api.gateway.mysql.entity")
                .build();
    }
}
