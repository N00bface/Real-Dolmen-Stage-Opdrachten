/*
 * Copyright (c) 2016. MIT-license for Jari Van Melckebeke
 * Note that there was a lot of educational work in this project,
 * this project was (or is) used for an assignment from Realdolmen in Belgium.
 * Please just don't abuse my work
 */

package org.jarivm.relationGraph.config;

import org.jarivm.relationGraph.constants.AuthType;
import org.jarivm.relationGraph.repositories.ClientRepository;
import org.jarivm.relationGraph.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;

/**
 * @author Jari Van Melckebeke
 * @since 25.12.16.
 */
public class AuthenticationConfig {
	private static AuthType role;
	private static String name;
	private static Long id;
	@Autowired
	private static EmployeeRepository employeeRepository;
	@Autowired
	private static ClientRepository clientRepository;

	public static Long getId() {
		return id;
	}

	public static void setId(Long id) {
		AuthenticationConfig.id = id;
	}

	public static String getName() {
		return name;
	}

	public static AuthType getRole() {
		return role;
	}

	public static boolean isAuthenticated() {
		return role != AuthType.NONE;
	}

	public static void setId() {
		if (name == null)
			setName();
		if (role == null)
			setRole();
		switch (role) {
			case ADMIN:
				id = -1L;
				break;
			case DEVELOPER:
				id = employeeRepository.findByProperty("name", name).iterator().next().getId();
				break;
			case CLIENT:
				id = clientRepository.findByProperty("name", name).iterator().next().getId();
		}
	}

	public static void setName() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		name = authentication.getName();
	}

	public static void setRole() {
		Collection<GrantedAuthority> authorities =
				(Collection<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		String a = authorities.iterator().next().getAuthority();
		System.out.println(a);
		switch (a) {
			case "ROLE_ADMIN":
				role = AuthType.ADMIN;
				break;
			case "ROLE_CLIENT":
				role = AuthType.CLIENT;
				break;
			case "ROLE_PROJECT_LEADER":
				role = AuthType.PROJECT_LEADER;
				break;
			case "ROLE_EMPLOYEE":
				role = AuthType.DEVELOPER;
				break;
			default:
				role = AuthType.NONE;
		}
	}

	public static void resetRole() {
		role = AuthType.NONE;
	}
}
