/*
 * Copyright (c) 2016. MIT-license for Jari Van Melckebeke
 * Note that there was a lot of educational work in this project,
 * this project was (or is) used for an assignment from Realdolmen in Belgium.
 * Please just don't abuse my work
 */

package org.jarivm.relationGraph.controllers;

import org.jarivm.relationGraph.config.AuthenticationConfig;
import org.jarivm.relationGraph.constants.AuthType;
import org.jarivm.relationGraph.constants.NodeType;
import org.jarivm.relationGraph.domains.Client;
import org.jarivm.relationGraph.domains.Employee;
import org.jarivm.relationGraph.domains.Project;
import org.jarivm.relationGraph.domains.Sector;
import org.jarivm.relationGraph.exceptions.ForbiddenException;
import org.jarivm.relationGraph.exceptions.NFException;
import org.jarivm.relationGraph.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.HashMap;
import java.util.Map;

import static org.jarivm.relationGraph.constants.NodeType.*;

/**
 * @author Jari Van Melckebeke
 * @since 29.10.16.
 */
@ControllerAdvice
public class BaseController {
	@Autowired
	public ProjectRepository projectRepository;
	@Autowired
	public ClientRepository clientRepository;
	@Autowired
	public SectorRepository sectorRepository;
	@Autowired
	public EmployeeRepository employeeRepository;
	@Autowired
	public WorkedOnRepository workedOnRepository;
	@Autowired
	public IssuedRepository issuedRepository;
	@Autowired
	public RequestMappingHandlerMapping requestMappingHandlerMapping;
	public Map<String, Boolean> authRole;

	@ModelAttribute("clientKeys")
	public String[] getClientKeys() {
		return clientRepository.findProperties();
	}

	@ModelAttribute("employeeKeys")
	public String[] getEmployeeKeys() {
		return employeeRepository.findProperties();
	}

	//todo: create sitemap :exclamation:
	@ModelAttribute("endpoints")
	public String[] getMappings() {
		return null;
	}

	@ModelAttribute("projectKeys")
	public String[] getProjectKeys() {
		return projectRepository.findProperties();
	}


	@ModelAttribute("sectorKeys")
	public String[] getSectorKeys() {
		return sectorRepository.findProperties();
	}

	@ModelAttribute("auth")
	Map<String, Boolean> authMap() {
		if (authRole == null) {
			authRole = new HashMap<>();
			authRole.put("ADMIN", isAdmin());
			authRole.put("CLIENT", isClient());
			authRole.put("DEVELOPER", isDeveloper());
			authRole.put("PROJECT_LEADER", isProjectLeader());
			authRole.put("EMPLOYEE", isEmployee());
			authRole.put("NONE", !isAuthenticated());
		}
		System.out.println(authRole.toString());
		return authRole;
	}

	boolean isAdmin() {
		return isAdmin(AuthenticationConfig.getRole());
	}

	boolean isClient() {
		return isClient(AuthenticationConfig.getRole());
	}

	boolean isDeveloper() {
		return isDeveloper(AuthenticationConfig.getRole());
	}

	boolean isProjectLeader() {
		return isProjectLeader(AuthenticationConfig.getRole());
	}

	boolean isEmployee() {
		return isEmployee(AuthenticationConfig.getRole());
	}

	boolean isAuthenticated() {
		return isAuthenticated(AuthenticationConfig.getRole());
	}

	private boolean isAdmin(AuthType role) {
		return (role == AuthType.ADMIN);
	}

	private boolean isClient(AuthType role) {
		return (role == AuthType.ADMIN) || (role == AuthType.CLIENT);
	}

	private boolean isDeveloper(AuthType role) {
		return (role == AuthType.ADMIN) || (role == AuthType.PROJECT_LEADER) || (role == AuthType.DEVELOPER)
				|| (role == AuthType.EMPLOYEE);
	}

	private boolean isProjectLeader(AuthType role) {
		return (role == AuthType.ADMIN) || (role == AuthType.PROJECT_LEADER);
	}

	private boolean isEmployee(AuthType role) {
		return (role == AuthType.ADMIN) || (role == AuthType.EMPLOYEE);
	}

	private boolean isAuthenticated(AuthType role) {
		return (role != AuthType.NONE);
	}

	public void resetAuth() {
		AuthenticationConfig.setRole();
		authRole = null;
		authMap();
		System.out.println("authentication resetted");
	}

	public String noAccess() {
		System.out.println("NO ACCESS");
		throw new ForbiddenException();
	}

	public String notFound() {
		throw new NFException();
	}

	public boolean hasRelationWithProject(Long id) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String name = authentication.getName();
		switch (AuthenticationConfig.getRole()) {
			case ADMIN:
				return true;
			case PROJECT_LEADER:
				return projectRepository.relationToWithProjectLeader(name, id);
			case EMPLOYEE:
				return projectRepository.relationToWithEmployee(name, id);
			case CLIENT:
				return projectRepository.relationToWithClient(name, id);
			default:
				return false;
		}
	}

	NodeType getTypeOfNode(Long id) {
		Client c = clientRepository.findById(id);
		Employee e = employeeRepository.findById(id);
		Project p = projectRepository.findById(id);
		Sector s = sectorRepository.findById(id);
		if (c != null) return CLIENT_TYPE;
		if (e != null) return EMPLOYEE_TYPE;
		if (p != null) return PROJECT_TYPE;
		if (s != null) return SECTOR_TYPE;
		return INVALID;
	}
}


