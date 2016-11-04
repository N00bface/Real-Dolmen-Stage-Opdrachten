package org.jarivm.relationGraph;

import org.neo4j.ogm.session.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.template.Neo4jTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * @author Jari Van Melckebeke
 * @since 03.11.16.
 */
@Configuration
@EnableNeo4jRepositories(basePackages = "org.jarivm.relationGraph.repositories")
@ComponentScan(basePackages = {"org.jarivm.relationGraph"})
public class TestApplication extends Neo4jConfiguration {
	public static final String URL = "http://localhost:7474";

	@Bean(name = "dataSource")
	public DriverManagerDataSource dataSource() {
		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
		driverManagerDataSource.setDriverClassName("com.mysql.jdbc.Driver");
		driverManagerDataSource.setUrl("jdbc:mysql://localhost:3306/springstageopdracht?autoReconnect=true&useSSL=false");
		driverManagerDataSource.setUsername("root");
		driverManagerDataSource.setPassword("Tanzania1");
		return driverManagerDataSource;
	}

	@Bean
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
				.setCredentials("neo4j", "tanzania");
		return config;
	}
/*
	@Bean
	@Override
	public PlatformTransactionManager transactionManager() throws Exception {
		return new Neo4jTransactionManager(getSession());
	}*/
}
