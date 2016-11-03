package org.jarivm.relationGraph.services;

import org.apache.log4j.Logger;
import org.jarivm.relationGraph.Application;
import org.jarivm.relationGraph.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Arrays;

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


}
