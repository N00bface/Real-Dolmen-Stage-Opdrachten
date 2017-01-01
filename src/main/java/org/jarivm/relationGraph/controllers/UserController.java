/*
 * Copyright (c) 2016. MIT-license for Jari Van Melckebeke
 * Note that there was a lot of educational work in this project,
 * this project was (or is) used for an assignment from Realdolmen in Belgium.
 * Please just don't abuse my work
 */

package org.jarivm.relationGraph.controllers;

import org.jarivm.relationGraph.config.AuthenticationConfig;
import org.jarivm.relationGraph.domains.Client;
import org.jarivm.relationGraph.domains.Employee;
import org.jarivm.relationGraph.domains.Project;
import org.jarivm.relationGraph.domains.WorkedOn;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Jari Van Melckebeke
 * @since 17.10.16.
 */
@Controller
@RequestMapping("user")
public class UserController extends BaseController {

	@RequestMapping(value = {"/index", "/", "/home"}, name = "user home")
	public String index(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		model.addAttribute("userprojects", projectRepository.findByClientName(authentication.getName()));
		return "/user/index";
	}

	@RequestMapping(value = "/tableOverview", name = "table overview")
	public String myProjects(Model model) {
		if (!isAuthenticated()) {
			return noAccess();
		}
		ArrayList<Client> clients = (ArrayList<Client>) clientRepository.findAll();
		ArrayList<Project> projects = (ArrayList<Project>) projectRepository.findAll();
		if (isAdmin()) {
			model.addAttribute("clients", clients);
			model.addAttribute("projects", projects);
			return "/user/tableOverview";
		}
		if (isClient()) {
			projects.removeIf(project -> !project.getIssued().get(0).getClient().getId().equals(AuthenticationConfig.getId()));
			clients.removeIf(client -> !client.getId().equals(AuthenticationConfig.getId()));
			model.addAttribute("clients", clients);
			model.addAttribute("projects", projects);
			return "/user/tableOverview";
		}
		for (Project p : projects) {
			if (isDeveloper()) {
				boolean b = false;
				for (int i = 0; i < p.getWorkedOn().size(); i++) {
					if (Objects.equals(p.getWorkedOn().get(i).getEmployee().getId(), AuthenticationConfig.getId())) {
						b = true;
						break;
					}
				}
				if (!b) {
					projects.remove(p);
				}
			} else break;
		}
		for (Client c : clients) {
			if (isDeveloper()) {
				boolean b = false;
				for (Project p : projectRepository.findByClientName(c.getName())) {
					for (WorkedOn w : p.getWorkedOn()) {
						if (w.getEmployee().getId().equals(AuthenticationConfig.getId())) {
							b = true;
							break;
						}
					}
				}
				if (!b) clients.remove(c);
			} else break;
		}
		return "/user/tableOverview";
	}

	@RequestMapping(value = "/employeeByScore", name = "employee of the month")
	public String employeeByScore(@RequestParam(name = "limit", defaultValue = "150", required = false) Long limit, Model model) {
		model.addAttribute("graphEmployee", employeeRepository.employeesOfAllTime(limit));
		return "/user/employeeByScore";
	}

	@RequestMapping("/viewClient/{id}")
	public String viewClient(@PathVariable(name = "id") Long id, Model model) {
		if (!isDeveloper())
			return noAccess();
		model.addAttribute("client", clientRepository.findById(id));
		return "/view/client";
	}

	@RequestMapping("/viewEmployee/{id}")
	public String viewEmployee(@PathVariable(name = "id") Long id, Model model) {
		if (!isDeveloper())
			return noAccess();
		model.addAttribute("employee", employeeRepository.findById(id));
		return "/view/employee";
	}

	@RequestMapping("/viewProject/{id}")
	public String viewProject(@PathVariable(name = "id") Long id, Model model) {
		if (!isDeveloper() && !(isClient() && hasRelationWithProject(id))) {
			return noAccess();
		}
		model.addAttribute("project", projectRepository.findById(id));
		return "/view/project";
	}

	@RequestMapping("/viewSector/{id}")
	public String viewSector(@PathVariable(name = "id") Long id, Model model) {
		if (!isDeveloper())
			return noAccess();
		model.addAttribute("sector", sectorRepository.findById(id));
		return "/view/sector";
	}

	@RequestMapping(value = "/delete/{id}")
	public String deleteNode(@PathVariable(name = "id") Long id, Model model) {
		switch (getTypeOfNode(id)) {
			case CLIENT_TYPE:
				if (isClient())
					clientRepository.delete(id);
				break;
			case EMPLOYEE_TYPE:
				if (isDeveloper())
					employeeRepository.delete(id);
				break;
			case PROJECT_TYPE:
				if (isDeveloper() || isClient())
					projectRepository.delete(id);
				break;
			case SECTOR_TYPE:
				if (isAdmin())
					sectorRepository.delete(id);
				break;
			case INVALID:
				return notFound();
		}
		return "redirect:/user/index.html";
	}

	@RequestMapping(value = "/evaluate/{id}")
	public String evaluate(@PathVariable("id") Long id, Model model) {
		if (!isDeveloper() && !(isClient() && hasRelationWithProject(id))) {
			return noAccess();
		}
		Project p = projectRepository.findById(id);
		List<WorkedOn> workedOns = p.getWorkedOn();
		workedOns.sort((t, t1) -> (int) (t.getEmployee().getId() - t1.getEmployee().getId()));
		List<Employee> employeesCollaborated = p.getWorkedOn().stream().map(WorkedOn::getEmployee).collect(Collectors.toList());
		model.addAttribute("project", p);
		model.addAttribute("workedOns", workedOns.iterator());
		model.addAttribute("employeesCollaborated", employeesCollaborated);
		return "/user/evaluate";
	}

	@RequestMapping(value = "/evaluate/{id}/confirm")
	public String confirmEvaluate(@PathVariable("id") Long id,
								  @RequestParam("issuedId") Long issuedId,
								  @RequestParam("score") List<Double> score,
								  @RequestParam("workedOnId") List<Long> workedOnId,
								  @RequestParam("cost") Float cost,
								  Model model) {
		if (!isDeveloper() && !(isClient() && hasRelationWithProject(id))) {
			return noAccess();
		}
		List<WorkedOn> workedOns = new ArrayList<>();
		for (int i = 0; i < workedOnId.size(); i++) {
			WorkedOn w = workedOnRepository.findById(workedOnId.get(i));
			w.setScore(score.get(i));
			workedOns.add(w);
		}
		Project p = projectRepository.findById(id);
		p.setCost(cost);
		issuedRepository.save(issuedRepository.findById(issuedId));
		workedOnRepository.save(workedOns);
		projectRepository.save(p);
		return "redirect:/user/index.html";
	}
}
