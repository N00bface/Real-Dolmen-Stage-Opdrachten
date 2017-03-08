/*
 * Copyright (c) 2017. MIT-license for Jari Van Melckebeke
 * Note that there was a lot of educational work in this project,
 * this project was (or is) used for an assignment from Realdolmen in Belgium.
 * Please just don't abuse my work
 */

package org.jarivm.relationGraph.controllers;

import org.jarivm.relationGraph.MysqlDB.Account;
import org.jarivm.relationGraph.domains.Client;
import org.jarivm.relationGraph.domains.Employee;
import org.jarivm.relationGraph.domains.Project;
import org.jarivm.relationGraph.domains.WorkedOn;
import org.kohsuke.github.GHCommit;
import org.kohsuke.github.GHRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.*;
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
		ArrayList<Project> projects;
		if (!isAuthenticated())
			noAccess();
		if (isAdmin()) {
			projects = (ArrayList<Project>) projectRepository.findAll();
		} else if (isClient()) {
			projects = (ArrayList<Project>) projectRepository.findByClientName(authenticationConfig.getName());
		} else {
			projects = (ArrayList<Project>) projectRepository.findByEmployee(authenticationConfig.getId());
		}
		model.addAttribute("userprojects", projects);
		return "/user/index";
	}

	@RequestMapping(value = "/tableOverview", name = "table overview")
	public String myProjects(Model model) throws IOException {
		if (!isAuthenticated()) {
			noAccess();
		}
		ArrayList<Client> clients;
		ArrayList<Project> projects;
		Map<String, Iterable<Integer>> reposCommits = new HashMap<>();
		Map<String, GHRepository> repos = new HashMap<>();
		if (isAdmin()) {
			repos = gitHub.getOrganization("Realdolmen365").getRepositories();
			clients = (ArrayList<Client>) clientRepository.findAll();
			projects = (ArrayList<Project>) projectRepository.findAll();
		} else if (isClient()) {
			clients = new ArrayList<>();
			clients.add(clientRepository.findById(authenticationConfig.getId()));
			System.out.println(clients);
			projects = (ArrayList<Project>) projectRepository.findByClientName(authenticationConfig.getName());
			Calendar cal = Calendar.getInstance();
			for (Project project : projects) {
				GHRepository r = gitHub.getRepository(project.getName());
				List<Integer> months = new ArrayList<>(12);
				Collections.fill(months, 0);
				for (GHCommit c : r.listCommits()) {
					cal.setTime(c.getCommitDate());
					int i = cal.get(Calendar.MONTH);
					months.set(i, months.get(i) + 1);
				}
				reposCommits.put(project.getName(), months);
				repos.put(project.getName(), r);
			}
		} else {
			Iterable<GHRepository> it = gitHub.getMyself().listRepositories();
			for (GHRepository repo : it) {
				repos.put(repo.getName(), repo);
			}
			clients = (ArrayList<Client>) clientRepository.findClientsByEmployee(authenticationConfig.getId());
			projects = (ArrayList<Project>) projectRepository.findByEmployee(authenticationConfig.getId());
		}
		model.addAttribute("clients", clients);
		model.addAttribute("projects", projects);
		model.addAttribute("commitMap", reposCommits);
		model.addAttribute("GHrepos", repos);
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
			noAccess();
		model.addAttribute("client", clientRepository.findById(id));
		return "/view/client";
	}

	@RequestMapping("/viewEmployee/{id}")
	public String viewEmployee(@PathVariable(name = "id") Long id, Model model) {
		if (!isDeveloper())
			noAccess();
		model.addAttribute("employee", employeeRepository.findById(id));
		return "/view/employee";
	}

	@RequestMapping("/viewProject/{id}")
	public String viewProject(@PathVariable(name = "id") Long id, Model model) {
		if (!isDeveloper() && !(isClient() && hasRelationWithProject(id))) {
			noAccess();
		}
		model.addAttribute("project", projectRepository.findById(id));
		return "/view/project";
	}

	@RequestMapping("/viewSector/{id}")
	public String viewSector(@PathVariable(name = "id") Long id, Model model) {
		if (!isDeveloper())
			noAccess();
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
				notFound();
		}
		return "redirect:/user/index.html";
	}

	@RequestMapping(value = "/evaluate/{id}")
	public String evaluate(@PathVariable("id") Long id, Model model) {
		if (!isDeveloper() && !(isClient() && hasRelationWithProject(id))) {
			noAccess();
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
		if (!isDeveloper() && !(isClient() && hasRelationWithProject(id))) noAccess();
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

	@RequestMapping(value = "/settings")
	public String settings(Model model) {
		if (!isAuthenticated()) noAccess();
		Account acc = accountRepository.findOne(authenticationConfig.getId());
		model.addAttribute("image", acc.getAvatar());
		model.addAttribute("user", acc);
		return "/user/settings";
	}
}
