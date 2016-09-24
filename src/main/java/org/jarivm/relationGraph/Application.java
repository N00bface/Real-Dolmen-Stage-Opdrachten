package org.jarivm.relationGraph;

import org.neo4j.ogm.session.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Jari Van Melckebeke
 * @since 23.09.16
 */
@EnableTransactionManagement
@ComponentScan(basePackages = {"org.jarivm.relationGraph"})
@Configuration
@EnableNeo4jRepositories(basePackages = "org.jarivm.relationGraph.objects.repositories")
public class Application extends Neo4jConfiguration {

    public static final String URL = System.getenv("NEO4J_URL") != null ? System.getenv("NEO4J_URL") : "http://localhost:7474";

    @Bean
    public org.neo4j.ogm.config.Configuration getConfiguration() {
        org.neo4j.ogm.config.Configuration config = new org.neo4j.ogm.config.Configuration();
        config
                .driverConfiguration()
                .setDriverClassName("org.neo4j.ogm.drivers.http.driver.HttpDriver")
                .setURI(URL)
                .setCredentials("neo4j", "tanzania");
        return config;
    }

    @Override
    public SessionFactory getSessionFactory() {
        System.out.println("done");
        return new SessionFactory(getConfiguration(), "org.jarivm.relationGraph.objects.domains");
    }
}