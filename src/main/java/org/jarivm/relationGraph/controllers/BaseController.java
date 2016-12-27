/*
 * Copyright (c) 2016. LICENCED BY (c) Jari Van Melckebeke, all rights reserved, use only with permission of Jari Van Melckebeke
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

	boolean isAdmin() {
		System.out.println(AuthenticationConfig.getRole());
		return isAdmin(AuthenticationConfig.getRole());
	}

	private boolean isAdmin(AuthType role) {
		return (role == AuthType.ADMIN);
	}

	boolean isClient() {
		return isClient(AuthenticationConfig.getRole());
	}

	private boolean isClient(AuthType role) {
		return (role == AuthType.ADMIN) || (role == AuthType.CLIENT);
	}

	boolean isDeveloper() {
		return isDeveloper(AuthenticationConfig.getRole());
	}

	private boolean isDeveloper(AuthType role) {
		return (role == AuthType.ADMIN) || (role == AuthType.PROJECT_LEADER) || (role == AuthType.DEVELOPER);
	}

	boolean isProjectLeader() {
		return isProjectLeader(AuthenticationConfig.getRole());
	}

	private boolean isProjectLeader(AuthType role) {
		return (role == AuthType.ADMIN) || (role == AuthType.PROJECT_LEADER);
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


