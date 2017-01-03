/*
 * Copyright (c) 2017. MIT-license for Jari Van Melckebeke
 * Note that there was a lot of educational work in this project,
 * this project was (or is) used for an assignment from Realdolmen in Belgium.
 * Please just don't abuse my work
 */

package org.jarivm.relationGraph.config;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;

/**
 * @author Jari Van Melckebeke
 * @since 25.12.16.
 */
@Configuration
public class AuthenticationSuccessListener implements ApplicationListener<InteractiveAuthenticationSuccessEvent> {

	static Logger logger = Logger.getLogger(AuthenticationConfig.class.getName());
	@Autowired
	private AuthenticationConfig authenticationConfig;

	@Override
	public void onApplicationEvent(InteractiveAuthenticationSuccessEvent interactiveAuthenticationSuccessEvent) {
		logger.info("USER LOGGED IN AS " + interactiveAuthenticationSuccessEvent.getAuthentication().getName() + " WITH ROLE " +
				interactiveAuthenticationSuccessEvent.getAuthentication().getAuthorities().iterator().next());
		authenticationConfig.setRole();
		authenticationConfig.setName();
		authenticationConfig.setId();
	}
}
