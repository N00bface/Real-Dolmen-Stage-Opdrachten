/*
 * Copyright (c) 2017. MIT-license for Jari Van Melckebeke
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
	private AuthType role;
	private String name;
	private Long id;
	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private ClientRepository clientRepository;

	public AuthenticationConfig() {
		resetRole();
	}

	public void resetRole() {
		role = AuthType.NONE;
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

	public void setId() {
		if (name == null)
			setName();
		if (role == null)
			setRole();
		switch (role) {
			case ADMIN:
				id = -1L;
				break;
			case DEVELOPER:
				id = employeeRepository.findBySurname(name).iterator().next().getId();
				break;
			case CLIENT:
				System.out.println(clientRepository.findByName(name));
				id = clientRepository.findByName(name).iterator().next().getId();
		}
	}

	public void setName() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		name = authentication.getName();
	}

	public void setRole() {
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
}
