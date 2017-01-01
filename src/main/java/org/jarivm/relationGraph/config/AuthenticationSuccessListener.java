/*
 * Copyright (c) 2016. MIT-license for Jari Van Melckebeke
 * Note that there was a lot of educational work in this project,
 * this project was (or is) used for an assignment from Realdolmen in Belgium.
 * Please just don't abuse my work
 */

package org.jarivm.relationGraph.config;

import org.jarivm.relationGraph.controllers.BaseController;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;

/**
 * @author Jari Van Melckebeke
 * @since 25.12.16.
 */
@Configuration
public class AuthenticationSuccessListener implements ApplicationListener<InteractiveAuthenticationSuccessEvent> {

	@Override
	public void onApplicationEvent(InteractiveAuthenticationSuccessEvent interactiveAuthenticationSuccessEvent) {
		System.out.println("USER LOGGED IN AS " + interactiveAuthenticationSuccessEvent.getAuthentication().getName());
		AuthenticationConfig.setRole();
		AuthenticationConfig.setName();
	}
}
