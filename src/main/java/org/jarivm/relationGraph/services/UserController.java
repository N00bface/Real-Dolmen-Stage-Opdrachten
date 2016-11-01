package org.jarivm.relationGraph.services;

import com.fasterxml.jackson.databind.deser.Deserializers;
import org.apache.log4j.Priority;
import org.codehaus.groovy.runtime.dgmimpl.arrays.IntegerArrayGetAtMetaMethod;
import org.jarivm.relationGraph.domains.Client;
import org.jarivm.relationGraph.domains.Employee;
import org.jarivm.relationGraph.domains.Project;
import org.jarivm.relationGraph.domains.Sector;
import org.jarivm.relationGraph.repositories.ClientRepository;
import org.jarivm.relationGraph.repositories.EmployeeRepository;
import org.jarivm.relationGraph.repositories.ProjectRepository;
import org.jarivm.relationGraph.repositories.SectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

	@RequestMapping(value = "/search")
	public String search(Model model) {
		return "/user/search";
	}

	@RequestMapping("/viewClient/{id}")
	public String viewClient(@PathVariable(name = "id") Long id, Model model) {
		model.addAttribute("client", clientRepository.findById(id));
		return "/view/client";
	}

	@RequestMapping("/viewEmployee/{id}")
	public String viewEmployee(@PathVariable(name = "id") Long id, Model model) {
		model.addAttribute("employee", employeeRepository.findById(id));
		return "/view/employee";
	}

	@RequestMapping("/viewProject/{id}")
	public String viewProject(@PathVariable(name = "id") Long id, Model model) {
		model.addAttribute("project", projectRepository.findById(id));
		return "/view/project";
	}

	@RequestMapping("/viewSector/{id}")
	public String viewSector(@PathVariable(name = "id") Long id, Model model) {
		model.addAttribute("sector", sectorRepository.findById(id));
		return "/view/sector";
	}
}
