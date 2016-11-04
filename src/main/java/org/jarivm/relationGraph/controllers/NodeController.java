package org.jarivm.relationGraph.controllers;

import org.jarivm.relationGraph.domains.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Jari Van Melckebeke
 * @since 17.10.16.
 */
@Controller
@RequestMapping("user/create")
public class NodeController extends BaseController {
	@RequestMapping("/client")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_CLIENT')")
	public String createClient(Model model) {
		model.addAttribute("clientToken", new Client());
		model.addAttribute("sectors", sectorRepository.findAll());
		return "/user/create/client";
	}

	@RequestMapping("/employee")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_EMPLOYEE', 'ROLE_PROJECT_LEADER')")
	public String createEmployee(Model model) {
		model.addAttribute("employeeToken", new Employee());
		return "/user/create/employee";
	}

	@RequestMapping("/sector")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_PROJECT_LEADER', 'ROLE_CLIENT')")
	public String createSector(Model model) {
		model.addAttribute("sectorToken", new Sector());
		return "/user/create/sector";
	}

	@RequestMapping("/project")
	public String createProject(Model model) {
		model.addAttribute("projectToken", new Project());
		model.addAttribute("employees", employeeRepository.findAll());
		model.addAttribute("clients", clientRepository.findAll());
		return "/user/create/project";
	}

	@RequestMapping(value = "/createClient")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_CLIENT')")
	public String createNewClient(@RequestParam(name = "sector") Long s, Client client, BindingResult bindingResult, Model model) {
		client.setSector(sectorRepository.findOne(s));
		System.out.println(client);
		clientRepository.save(client);
		return "forward:/user/index";
	}

	@RequestMapping(value = "/createEmployee")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_EMPLOYEE')")
	public String createNewEmployee(Employee employee, BindingResult bindingResult, Model model) {
		System.out.println(employee);
		employeeRepository.save(employee);
		return "redirect:/user/index";
	}

	@RequestMapping(value = "/createSector")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_PROJECT_LEADER', 'ROLE_CLIENT')")
	public String createNewSector(Sector sector, BindingResult bindingResult, Model model) {
		System.out.println(sector);
		sectorRepository.save(sector);
		return "redirect:/user/index";
	}

	@RequestMapping(value = "/createProject")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_PROJECT_LEADER')")
	public String createNewProject(@RequestParam(name = "employeesCollaborated") List<Long> employees,
								   @RequestParam(name = "roles") List<String> roles,
								   @RequestParam(name = "client") Long client,
								   Project project,
								   BindingResult bindingResult, Model model) {
		project.setClient(clientRepository.findById(client));
		List<Employee> employeesCollaborated = (List<Employee>) employeeRepository.findAll(employees);
		List<WorkedOn> workedOnSet = new ArrayList<>();
		for (int i = 0; i < employeesCollaborated.size(); i++) {
			WorkedOn workedOn = new WorkedOn(employeesCollaborated.get(i), project, roles.get(i));
			workedOn.setScore(employeeRepository.getAvgScore(employees.get(i)));
			workedOnSet.add(workedOn);
		}
		project.setWorkedOn(workedOnSet);
		projectRepository.save(project);
		return "redirect:/user/index";
	}

	@RequestMapping(value = "/edit/{id}")
	public String editEntity(@PathVariable("id") Long id, Model model) {
		switch (getTypeOfNode(id)) {
			case CLIENT_TYPE:
				model.addAttribute("client", clientRepository.findById(id));
				model.addAttribute("sectors", sectorRepository.findAll());
				return "/user/edit/client";
			case EMPLOYEE_TYPE:
				Employee e = employeeRepository.findById(id);
				model.addAttribute("employee", e);
				return "/user/edit/employee";
			case PROJECT_TYPE:
				Project p = projectRepository.findById(id);
				model.addAttribute("project", p);
				model.addAttribute("employees", employeeRepository.findAll());
				List<WorkedOn> workedOns = p.getWorkedOn();
				Collections.sort(workedOns, (t, t1) -> (int) (t.getEmployee().getId() - t1.getEmployee().getId()));
				model.addAttribute("workedOns", workedOns.iterator());
				List<Long> employeesCollaborated = p.getWorkedOn().stream().map(w -> w.getEmployee().getId()).collect(Collectors.toList());
				model.addAttribute("employeesCollaborated", employeesCollaborated);
				model.addAttribute("clients", clientRepository.findAll());
				return "/user/edit/project";
			case SECTOR_TYPE:
				model.addAttribute("sector", sectorRepository.findById(id));
				return "/user/edit/sector";
			default:
				return "/user/err404";
		}
	}

	@RequestMapping(value = "/edit/client/{id}/confirm")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_CLIENT')")
	public String confirmEditClient(@PathVariable("id") Long id, Client node, BindingResult bindingResult, Model model) {
		System.out.println(node);
		node.setId(id);
		clientRepository.save(node);
		return "redirect:/user/index";
	}

	@RequestMapping(value = "/edit/employee/{id}/confirm")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_EMPLOYEE')")
	public String confirmEditEmployee(@PathVariable("id") Long id, Employee node, BindingResult bindingResult, Model model) {
		System.out.println(node);
		node.setId(id);
		employeeRepository.save(node);
		return "redirect:/user/index";
	}

	@RequestMapping(value = "/edit/project/{id}/confirm")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_PROJECT_LEADER')")
	public String confirmEditProject(@PathVariable("id") Long id,
									 @RequestParam(name = "employeesCollaborated") List<Long> employees,
									 @RequestParam(name = "roles") List<String> roles,
									 @RequestParam(name = "client") Long client,
									 Project node, BindingResult bindingResult, Model model) {
		node.setClient(clientRepository.findById(client));
		List<Employee> employeesCollaborated = (List<Employee>) employeeRepository.findAll(employees);
		Iterator<WorkedOn> WorkedOnsAlready = projectRepository.findWorkedOnsByEmployees(employees, id).iterator();
		List<WorkedOn> workedOnList = new ArrayList<>();
		int follow = 0;
		for (Employee e : employeesCollaborated) {
			if (projectRepository.findWorkedOnByEmployee(e.getId(), id) == null) {
				workedOnList.add(new WorkedOn(e, projectRepository.findById(id), roles.get(follow++)));
			}
		}
		while (WorkedOnsAlready.hasNext()) {
			WorkedOn workedOn = WorkedOnsAlready.next();
			workedOn.setProject(node);
			workedOn.setRole(roles.get(follow++));
			workedOnList.add(workedOn);
		}
		node.setWorkedOn(workedOnList);
		workedOnRepository.save(workedOnList);
		projectRepository.save(node);
		return "redirect:/user/index";
	}

	@RequestMapping(value = "/edit/sector/{id}/confirm")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public String confirmEditSector(@PathVariable("id") Long id, Sector node, BindingResult bindingResult, Model model) {
		System.out.println(node);
		node.setId(id);
		sectorRepository.save(node);
		return "redirect:/user/index";
	}
}
