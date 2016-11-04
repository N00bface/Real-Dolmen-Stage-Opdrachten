package org.jarivm.relationGraph.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Jari Van Melckebeke
 * @since 17.10.16.
 */
@Controller
@RequestMapping(value = "user")
public class UserController extends BaseController {

	@RequestMapping("/index")
	public String root() {
		return "/user/index";
	}

	@RequestMapping(value = "/tableOverview")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_PROJECT_LEADER')")
	public String graph(@RequestParam(name = "limit", defaultValue = "150", required = false) Long limit, Model model) {
		System.out.println(limit);
		model.addAttribute("graphClient", clientRepository.graph(limit));
		model.addAttribute("graphProject", projectRepository.graph(limit));
		return "/user/tableOverview";
	}

	@RequestMapping(value = "/employeeByScore")
	public String employeeByScore(@RequestParam(name = "limit", defaultValue = "150", required = false) Long limit, Model model) {
		model.addAttribute("graphEmployee", employeeRepository.employeesOfAllTime(limit));
		return "/user/employeeByScore";
	}

	@RequestMapping("/viewClient/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EMPLOYEE', 'ROLE_PROJECT_LEADER')")
	public String viewClient(@PathVariable(name = "id") Long id, Model model) {
		model.addAttribute("client", clientRepository.findById(id));
		return "/view/client";
	}

	@RequestMapping("/viewEmployee/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EMPLOYEE', 'ROLE_PROJECT_LEADER')")
	public String viewEmployee(@PathVariable(name = "id") Long id, Model model) {
		model.addAttribute("employee", employeeRepository.findById(id));
		return "/view/employee";
	}

	@RequestMapping("/viewProject/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EMPLOYEE', 'ROLE_PROJECT_LEADER')")
	public String viewProject(@PathVariable(name = "id") Long id, Model model) {
		model.addAttribute("project", projectRepository.findById(id));
		return "/view/project";
	}

	@RequestMapping("/viewSector/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EMPLOYEE', 'ROLE_PROJECT_LEADER')")
	public String viewSector(@PathVariable(name = "id") Long id, Model model) {
		model.addAttribute("sector", sectorRepository.findById(id));
		return "/view/sector";
	}

	@RequestMapping(value = "/delete/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String deleteNode(@PathVariable(name = "id") Long id, Model model) {
		switch (getTypeOfNode(id)) {
			case CLIENT_TYPE:
				clientRepository.delete(id);
				return "redirect:/user/index";
			case EMPLOYEE_TYPE:
				employeeRepository.delete(id);
				return "redirect:/user/index";
			case PROJECT_TYPE:
				projectRepository.delete(id);
				return "redirect:/user/index";
			case SECTOR_TYPE:
				sectorRepository.delete(id);
				return "redirect:/user/index";
			default:
				return "redirect:/user/err404";
		}
	}

}
