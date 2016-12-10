package org.jarivm.relationGraph.controllers;

import groovyjarjarantlr.LexerSharedInputState;
import org.jarivm.relationGraph.domains.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
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
		return "redirect:/user/index.html";
	}

	@RequestMapping(value = "/createEmployee")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_EMPLOYEE')")
	public String createNewEmployee(Employee employee, BindingResult bindingResult, Model model) {
		System.out.println(employee);
		employeeRepository.save(employee);
		return "redirect:/user/index.html";
	}

	@RequestMapping(value = "/createSector")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_PROJECT_LEADER', 'ROLE_CLIENT')")
	public String createNewSector(Sector sector, BindingResult bindingResult, Model model) {
		System.out.println(sector);
		sectorRepository.save(sector);
		return "redirect:/user/index.html";
	}

	@RequestMapping(value = "/createProject")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_PROJECT_LEADER', 'ROLE_CLIENT')")
	public String createNewProject(@RequestParam(name = "employeesCollaborated") List<Long> employees,
								   @RequestParam(name = "roles") List<String> roles,
								   @RequestParam(name = "client") Long client,
								   @ModelAttribute Project project,
								   BindingResult bindingResult, Model model) {
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
				return "/error/404";
		}
	}

	@RequestMapping(value = "/edit/client/{id}/confirm")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_CLIENT')")
	public String confirmEditClient(@PathVariable("id") Long id, Client node, BindingResult bindingResult, Model model) {
		System.out.println(node);
		node.setId(id);
		clientRepository.save(node);
		return "redirect:/user/index.html";
	}

	@RequestMapping(value = "/edit/employee/{id}/confirm")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_EMPLOYEE')")
	public String confirmEditEmployee(@PathVariable("id") Long id, Employee node, BindingResult bindingResult, Model model) {
		System.out.println(node);
		node.setId(id);
		employeeRepository.save(node);
		return "redirect:/user/index.html";
	}

	@RequestMapping(value = "/edit/project/{id}/confirm")
	public String confirmEditProject(@PathVariable("id") Long id,
									 @RequestParam(name = "employeesCollaborated") List<Long> employees,
									 @RequestParam(name = "roles") List<String> roles,
									 @RequestParam(name = "issuedId") Long issuedId,
									 Project node, BindingResult bindingResult, Model model) {
		Issued issued = issuedRepository.findById(issuedId);
		List<Issued> issuedList = new ArrayList<>();
		issuedList.add(issued);
		List<Employee> employeesCollaborated = (List<Employee>) employeeRepository.findAll(employees);
		List<WorkedOn> w = workedOnRepository.findByProject(id);
		for (int i = 0; i < employeesCollaborated.size(); i++) {
			boolean b = false;
			for (int j = 0; j < w.size(); j++) {
				if (employeesCollaborated.get(i).getId().equals(w.get(i).getEmployee().getId())) {
					b = true;
					break;
				}
			}
			if (!b) {
				w.add(new WorkedOn(employeesCollaborated.get(i), node));
			}
		}
		workedOnRepository.save(w);
		issuedRepository.save(issuedList);
		projectRepository.save(node);
		return "redirect:/user/index.html";
	}

	@RequestMapping(value = "/edit/sector/{id}/confirm")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public String confirmEditSector(@PathVariable("id") Long id, Sector node, BindingResult bindingResult, Model model) {
		System.out.println(node);
		node.setId(id);
		sectorRepository.save(node);
		return "redirect:/user/index.html";
	}
}
