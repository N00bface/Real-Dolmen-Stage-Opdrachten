/*
 * Copyright (c) 2016. LICENCED BY (c) Jari Van Melckebeke, all rights reserved, use only with permission of Jari Van Melckebeke
 */

package org.jarivm.relationGraph.controllers;

import org.jarivm.relationGraph.domains.*;
import org.jarivm.relationGraph.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import static org.jarivm.relationGraph.controllers.EntityConstants.*;

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

	@ModelAttribute("clientKeys")
	public String[] getClientKeys() {
		return clientRepository.findProperties();
	}

	@ModelAttribute("employeeKeys")
	public String[] getEmployeeKeys() {
		return employeeRepository.findProperties();
	}

	@ModelAttribute("projectKeys")
	public String[] getProjectKeys() {
		return projectRepository.findProperties();
	}

	@ModelAttribute("sectorKeys")
	public String[] getSectorKeys() {
		return sectorRepository.findProperties();
	}

	public boolean getHasRelationWithProject(Long id) {
		//todo: complete has relation
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return projectRepository.relationToWithClient(authentication.getName(), id)
				|| projectRepository.relationToWithProjectLeader(authentication.getName(), id)
				|| projectRepository.relationToWithEmployee(authentication.getName(), id)
				|| authentication.getName().equals("admin");
	}

	public EntityConstants getTypeOfNode(Long id) {
		Client c = clientRepository.findById(id);
		Employee e = employeeRepository.findById(id);
		Project p = projectRepository.findById(id);
		Sector s = sectorRepository.findById(id);
		if (c != null) {
			return CLIENT_TYPE;
		}
		if (e != null) {
			return EMPLOYEE_TYPE;
		}
		if (p != null) {
			return PROJECT_TYPE;
		}
		if (s != null) {
			return SECTOR_TYPE;
		}
		return INVALID;
	}
}


