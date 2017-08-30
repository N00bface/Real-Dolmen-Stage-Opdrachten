/*
 * Copyright (c) 2017. MIT-license for Jari Van Melckebeke
 * Note that there was a lot of educational work in this project,
 * this project was (or is) used for an assignment from Realdolmen in Belgium.
 * Please just don't abuse my work
 */

package org.jarivm.relationGraph.config;

import org.kohsuke.github.GitHub;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.template.Neo4jTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import java.io.IOException;

/**
 * @author Jari Van Melckebeke
 * @since 23.09.16
 */
@Configuration
@WebAppConfiguration
@EnableNeo4jRepositories(basePackages = "org.jarivm.relationGraph.repositories")
@ComponentScan(basePackages = {"org.jarivm.relationGraph"})
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = {"org.jarivm.relationGraph.MySQLRepository"})
public class Application extends Neo4jConfiguration {
    public static final String URL = "http://" + System.getenv("DB_NEO4J_HOST") + ":7474";

    public Application() {
    }

    @Bean
    @Override
    public Neo4jTemplate neo4jTemplate() throws Exception {
        return new Neo4jTemplate(getSession());
    }

    @Bean
    @Override
    public SessionFactory getSessionFactory() {
        return new SessionFactory(getConfiguration(), "org.jarivm.relationGraph.domains", "org.jarivm.relationGraph.repositories");
    }

    @Bean
    public org.neo4j.ogm.config.Configuration getConfiguration() {
        org.neo4j.ogm.config.Configuration config = new org.neo4j.ogm.config.Configuration();
        config
                .driverConfiguration()
                .setDriverClassName("org.neo4j.ogm.drivers.http.driver.HttpDriver")
                .setURI(URL)
                .setCredentials(System.getenv("neo4j_user"), System.getenv("neo4j_pass"));
        return config;
    }

    @Bean
    public GitHub gitHub() throws IOException {
        return GitHub.connectAnonymously();
    }

    @Bean
    public AuthenticationConfig authenticationConfig() {
        return new AuthenticationConfig();
    }

    @Bean
    public ReloadableResourceBundleMessageSource messageSource() {
        return new ReloadableResourceBundleMessageSource();
    }

    @Bean
    public Mapper mapper() {
        return new Mapper();
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}