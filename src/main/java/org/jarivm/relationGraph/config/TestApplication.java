/*
 * Copyright (c) 2016. LICENCED BY (c) Jari Van Melckebeke, all rights reserved, use only with permission of Jari Van Melckebeke
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
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

/**
 * @author Jari Van Melckebeke
 * @since 16.11.16.
 */
@Configuration
@EnableNeo4jRepositories(basePackages = "org.jarivm.relationGraph.repositories")
@ComponentScan(basePackages = {"org.jarivm.relationGraph"})
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableAutoConfiguration
@Profile("CI")
public class TestApplication extends Neo4jConfiguration {
	public static final String URL = "http://localhost:7474";

	public TestApplication() {
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
}
