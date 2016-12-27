/*
 * Copyright (c) 2016. MIT-license for Jari Van Melckebeke
 * Note that there was a lot of educational work in this project,
 * this project was (or is) used for an assignment from Realdolmen in Belgium.
 * Please just don't abuse my work
 */

package org.jarivm.relationGraph.config;

import org.neo4j.ogm.session.SessionFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.template.Neo4jTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @author Jari Van Melckebeke
 * @since 23.09.16
 */
@Configuration
@WebAppConfiguration
@EnableNeo4jRepositories(basePackages = "org.jarivm.relationGraph.repositories")
@ComponentScan(basePackages = {"org.jarivm.relationGraph"})
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableAutoConfiguration
@Profile("prod")
public class Application extends Neo4jConfiguration {
	public static final String URL = "http://localhost:7474";

	public Application() {
	}

	@Bean(name = "dataSource")
	public DriverManagerDataSource dataSource() {
		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
		driverManagerDataSource.setDriverClassName("com.mysql.jdbc.Driver");
		driverManagerDataSource.setUrl("jdbc:mysql://127.0.0.1:3306/springstageopdracht");
		driverManagerDataSource.setUsername("root");
		driverManagerDataSource.setPassword("Tanzania1");
		return driverManagerDataSource;
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
				.setCredentials("neo4j", "tanzania");
		return config;
	}

	/*@Bean
	public EmbeddedServletContainerFactory servletContainer() {
		return new TomcatEmbeddedServletContainerFactory();
	}*/
}