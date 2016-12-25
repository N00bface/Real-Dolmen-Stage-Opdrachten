/*
 * Copyright (c) 2016. LICENCED BY (c) Jari Van Melckebeke, all rights reserved, use only with permission of Jari Van Melckebeke
 */

package org.jarivm.relationGraph.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author Jari Van Melckebeke
 * @since 16.11.16.
 */
@EnableWebSecurity
@Configuration
@Profile("CI")
public class TestSecurityConfig extends WebSecurityConfigurerAdapter {


	// possible roles: ROLE_ADMIN, ROLE_EMPLOYEE, ROLE_CLIENT, ROLE_PROJECT_LEADER
	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("admin").password("root").roles("ADMIN");
		auth.inMemoryAuthentication().withUser("Colruyt").password("client").roles("CLIENT");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/public").permitAll()
				.anyRequest().authenticated()
				.and()
				.formLogin()
				.permitAll()
				.and()
				.logout().permitAll();
	}
}

