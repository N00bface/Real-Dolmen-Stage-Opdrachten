/*
 * Copyright (c) 2017. MIT-license for Jari Van Melckebeke
 * Note that there was a lot of educational work in this project,
 * this project was (or is) used for an assignment from Realdolmen in Belgium.
 * Please just don't abuse my work
 */

package org.jarivm.relationGraph.controllers;

import org.jarivm.relationGraph.domains.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Jari Van Melckebeke
 * @since 17.10.16.
 */
@Controller
@RequestMapping("/user/create")
public class NodeController extends BaseController {

	@RequestMapping(value = "/client", name = "create client")
	public String createClient(Model model) {
		if (!isAdmin()) {
			return noAccess();
		}
		model.addAttribute("clientToken", new Client());
		model.addAttribute("sectors", sectorRepository.findAll());
		return "/user/create/client";
	}

	@RequestMapping(value = "/employee", name = "create employee")
	public String createEmployee(Model model) {
		if (!isProjectLeader()) {
			return noAccess();
		}
		model.addAttribute("employeeToken", new Employee());
		return "/user/create/employee";
	}

	@RequestMapping(value = "/sector", name = "create sector")
	public String createSector(Model model) {
		if (!isAdmin())
			return noAccess();
		model.addAttribute("sectorToken", new Sector());
		return "/user/create/sector";
	}

	@RequestMapping(value = "/project", name = "create project")
	public String createProject(Model model) {
		if (!isProjectLeader()) {
			return noAccess();
		}
		model.addAttribute("projectToken", new Project());
		model.addAttribute("employees", employeeRepository.findAll());
		model.addAttribute("clients", clientRepository.findAll());
		return "/user/create/project";
	}

	@RequestMapping(value = "/createClient")
	public String createNewClient(@RequestParam(name = "sector") Long s, Client client, BindingResult bindingResult, Model model) {
		if (!isClient()) {
			return "";
		}
		client.setSector(sectorRepository.findOne(s));
		System.out.println(client);
		clientRepository.save(client);
		return "redirect:/user/index.html";
	}

	@RequestMapping(value = "/createEmployee")
	public String createNewEmployee(Employee employee, BindingResult bindingResult, Model model) {
		if (!isAdmin())
			return noAccess();
		System.out.println(employee);
		employeeRepository.save(employee);
		return "redirect:/user/index.html";
	}

	@RequestMapping(value = "/createSector")
	public String createNewSector(Sector sector, BindingResult bindingResult, Model model) {
		if (!isDeveloper()) {
			return noAccess();
		}
		System.out.println(sector);
		sectorRepository.save(sector);
		return "redirect:/user/index.html";
	}

	@RequestMapping(value = "/createProject")
	public String createNewProject(@RequestParam(name = "employeesCollaborated") List<Long> employees,
								   @RequestParam(name = "roles") List<String> roles,
								   @RequestParam(name = "client") Long client,
								   @ModelAttribute Project project,
								   BindingResult bindingResult, Model model) {
		if (!isDeveloper()) {
			return noAccess();
		}
		Client c = clientRepository.findById(client);
		Issued issued = new Issued(c, project);
		List<Issued> issuedList = new ArrayList<>();
		issuedList.add(issued);
		List<Employee> employeesCollaborated = (List<Employee>) employeeRepository.findAll(employees);
		List<WorkedOn> workedOnSet = new ArrayList<>();
		for (int i = 0; i < employeesCollaborated.size(); i++) {
			WorkedOn workedOn = new WorkedOn(employeesCollaborated.get(i), project, roles.get(i));
			workedOn.setScore(0);
			workedOnSet.add(workedOn);
		}
		projectRepository.save(project);
		issuedRepository.save(issuedList);
		workedOnRepository.save(workedOnSet);
		return "redirect:/user/index.html";
	}

	@RequestMapping(value = "/edit/{id}", name = "edit entity")
	public String editEntity(@PathVariable("id") Long id, Model model) {
		switch (getTypeOfNode(id)) {
			case CLIENT_TYPE:
				if (!isAuthenticated())
					return noAccess();
				//todo: better auth system hierarchy
				model.addAttribute("client", clientRepository.findById(id));
				model.addAttribute("sectors", sectorRepository.findAll());
				return "/user/edit/client";
			case EMPLOYEE_TYPE:
				if (!isDeveloper())
					return noAccess();
				Employee e = employeeRepository.findById(id);
				model.addAttribute("employee", e);
				return "/user/edit/employee";
			case PROJECT_TYPE:
				if (!isAuthenticated())
					return noAccess();
				Project p = projectRepository.findById(id);
				model.addAttribute("project", p);
				List<WorkedOn> workedOns = p.getWorkedOn();
				workedOns.sort((t, t1) -> (t.getEmployee().getSurname().compareTo(t1.getEmployee().getSurname())));
				model.addAttribute("workedOns", workedOns);
				List<Employee> otherEmployees = (List<Employee>) employeeRepository.findAll();
				otherEmployees.removeIf(employee -> workedOns.stream().anyMatch(w -> w.getEmployee() == employee));
				model.addAttribute("otherEmployees", otherEmployees);
				model.addAttribute("clients", clientRepository.findAll());
				return "/user/edit/project";
			case SECTOR_TYPE:
				if (!isAdmin())
					return noAccess();
				model.addAttribute("sector", sectorRepository.findById(id));
				return "/user/edit/sector";
			default:
				return notFound();
		}
	}

	@RequestMapping(value = "/edit/client/{id}/confirm")
	public String confirmEditClient(@PathVariable("id") Long id, Client node, BindingResult bindingResult, Model model) {
		if (!isClient()) {
			return noAccess();
		}
		System.out.println(node);
		node.setId(id);
		clientRepository.save(node);
		return "redirect:/user/index.html";
	}

	@RequestMapping(value = "/edit/employee/{id}/confirm")
	public String confirmEditEmployee(@PathVariable("id") Long id, Employee node, BindingResult bindingResult, Model model) {
		if (!isDeveloper()) {
			return noAccess();
		}
		System.out.println(node);
		node.setId(id);
		employeeRepository.save(node);
		logger.info("employee with id " + id.toString() + " edited");
		return "redirect:/user/index.html";
	}

	@RequestMapping(value = "/edit/project/{id}/confirm")
	public String confirmEditProject(@PathVariable("id") Long id,
									 @RequestParam(name = "employeesCollaborated") List<Long> employees,
									 @RequestParam(name = "role") List<String> roles,
									 @RequestParam(name = "issuedId") Long issuedId,
									 @RequestParam(name = "projectId") Long projectId,
									 @RequestParam(name = "projectName") String projectName,
									 @RequestParam(name = "projectCost") Float projectCost,
									 @RequestParam(name = "projectVersion") String projectVersion,
									 @RequestParam(name = "client") Long clientId,
									 @ModelAttribute Project undefined,
									 BindingResult bindingResult, Model model) {
		Project project = projectRepository.findById(projectId);
		project.setId(id);
		project.setCost(projectCost);
		project.setName(projectName);
		project.setVersion(projectVersion);
		Client client = clientRepository.findById(clientId);
		Issued issued = issuedRepository.findById(issuedId);
		issued.setClient(client);
		project.setIssued(Collections.singletonList(issued));
		System.out.println(workedOnRepository.findByProject(projectId));
		workedOnRepository.delete(workedOnRepository.findByProject(projectId));
		int follow = 0;
		List<WorkedOn> workedOns = new ArrayList<>();
		for (Long emp : employees) {
			Employee e = employeeRepository.findById(emp);
			WorkedOn w = new WorkedOn(e, project, roles.get(follow++));
			workedOns.add(w);
		}
		project.setWorkedOn(workedOns);
		projectRepository.save(project);
		issuedRepository.save(issued);
		workedOnRepository.save(workedOns);
		return "redirect:/user/index.html";
	}

	@RequestMapping(value = "/edit/sector/{id}/confirm")
	public String confirmEditSector(@PathVariable("id") Long id, Sector node, BindingResult bindingResult, Model model) {
		if (!isAdmin()) {
			return noAccess();
		}
		System.out.println(node);
		node.setId(id);
		sectorRepository.save(node);
		return "redirect:/user/index.html";
	}
}
