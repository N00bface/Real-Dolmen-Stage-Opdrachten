/*
 * Copyright (c) 2017. MIT-license for Jari Van Melckebeke
 * Note that there was a lot of educational work in this project,
 * this project was (or is) used for an assignment from Realdolmen in Belgium.
 * Please just don't abuse my work
 */

package org.jarivm.relationGraph.config;

import org.apache.log4j.Logger;
import org.jarivm.relationGraph.constants.AuthType;
import org.jarivm.relationGraph.repositories.ClientRepository;
import org.jarivm.relationGraph.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

/**
 * @author Jari Van Melckebeke
 * @since 25.12.16.
 */
public class AuthenticationConfig implements AuthenticationSuccessHandler {
	private AuthType role;
	private String name;
	private Long id;
	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private ClientRepository clientRepository;
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	private Logger logger = Logger.getLogger(this.getClass());

	public AuthenticationConfig() {
		resetRole();
	}

	public void resetRole() {
		role = AuthType.NONE;
		id = null;
		name = null;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public AuthType getRole() {
		return role;
	}

	public boolean isAuthenticated() {
		return role != AuthType.NONE;
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
		setRole();
		setName();
		setId();
		if (httpServletResponse.isCommitted()) {
			logger.debug("Response has already been commited. Unalble redirect to /user/index.html");
			return;
		}
		redirectStrategy.sendRedirect(httpServletRequest, httpServletResponse, "/user/index.html");
	}

	public void setRole() {
		logger.info("setting new role");
		Collection<GrantedAuthority> authorities =
				(Collection<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		String a = authorities.iterator().next().getAuthority();
		logger.info("authority: " + a);
		switch (a) {
			case "ADMIN":
				role = AuthType.ADMIN;
				return;
			case "CLIENT":
				role = AuthType.CLIENT;
				return;
			case "PROJECT_LEADER":
				role = AuthType.PROJECT_LEADER;
				return;
			case "EMPLOYEE":
				role = AuthType.DEVELOPER;
				return;
			default:
				role = AuthType.NONE;
		}
		logger.info("user logged in as " + role);
	}

	public void setName() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		name = authentication.getName();
	}

	public void setId() {
		if (name == null)
			setName();
		if (role == null)
			setRole();
		switch (role) {
			case ADMIN:
				id = 1L;
				break;
			case DEVELOPER:
				id = employeeRepository.findBySurname(name).iterator().next().getId();
				break;
			case CLIENT:
				logger.info("Client \'" + clientRepository.findByName(name) + "\' logged in");
				id = clientRepository.findByName(name).iterator().next().getId();
		}
	}
}
