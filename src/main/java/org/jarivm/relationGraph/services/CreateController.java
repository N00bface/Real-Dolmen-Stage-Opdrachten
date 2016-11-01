package org.jarivm.relationGraph.services;

import com.sun.org.apache.bcel.internal.classfile.Node;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.jarivm.relationGraph.Application;
import org.jarivm.relationGraph.domains.*;
import org.jarivm.relationGraph.repositories.ClientRepository;
import org.jarivm.relationGraph.repositories.EmployeeRepository;
import org.jarivm.relationGraph.repositories.ProjectRepository;
import org.jarivm.relationGraph.repositories.SectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
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
public class CreateController extends BaseController {
	@RequestMapping("/client")
	public String createClient(Model model) {
		model.addAttribute("clientToken", new Client());
		model.addAttribute("sectors", sectorRepository.findAll());
		return "/user/create/client";
	}

	@RequestMapping("/employee")
	public String createEmployee(Model model) {
		model.addAttribute("employeeToken", new Employee());
		return "/user/create/employee";
	}

	@RequestMapping("/sector")
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
	public String createNewClient(@RequestParam(name = "sector") Long s, Client client, BindingResult bindingResult, Model model) {
		client.setSector(sectorRepository.findOne(s));
		System.out.println(client);
		clientRepository.save(client);
		return "forward:/user/index";
	}

	@RequestMapping(value = "/createEmployee")
	public String createNewEmployee(Employee employee, BindingResult bindingResult, Model model) {
		System.out.println(employee);
		employeeRepository.save(employee);
		return "forward:/user/index";
	}

	@RequestMapping(value = "/createSector")
	public String createNewSector(Sector sector, BindingResult bindingResult, Model model) {
		System.out.println(sector);
		sectorRepository.save(sector);
		return "forward:/user/index";
	}

	@RequestMapping(value = "/createProject")
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
		return "forward:/user/index";
	}

	@RequestMapping(value = "/edit/{id}")
	public String editEntity(@PathVariable("id") Long id, Model model) {
		Client c = clientRepository.findById(id);
		Employee e = employeeRepository.findById(id);
		Project p = projectRepository.findById(id);
		Sector s = sectorRepository.findById(id);
		if (c != null) {
			model.addAttribute("client", c);
			model.addAttribute("sectors", sectorRepository.findAll());
			return "/user/edit/client";
		}
		if (e != null) {
			model.addAttribute("employee", e);
			return "/user/edit/employee";
		}
		if (p != null) {
			model.addAttribute("project", p);
			model.addAttribute("employees", employeeRepository.findAll());
			List<WorkedOn> workedOns = p.getWorkedOn();
			Collections.sort(workedOns, (t, t1) -> (int) (t.getEmployee().getId() - t1.getEmployee().getId()));
			model.addAttribute("workedOns", workedOns.iterator());
			List<Long> employeesCollaborated = new ArrayList<>();
			for (WorkedOn w : p.getWorkedOn())
				employeesCollaborated.add(w.getEmployee().getId());
			model.addAttribute("employeesCollaborated", employeesCollaborated);
			model.addAttribute("clients", clientRepository.findAll());
			return "/user/edit/project";
		}
		if (s != null) {
			model.addAttribute("sector", s);
			return "/user/edit/sector";
		}
		return "/user/err404";
	}

	@RequestMapping(value = "/edit/client/{id}/confirm")
	public String confirmEditClient(@PathVariable("id") Long id, Client node, BindingResult bindingResult, Model model) {
		System.out.println(node);
		node.setId(id);
		clientRepository.save(node);
		return "forward:/user/index";
	}

	@RequestMapping(value = "/edit/employee/{id}/confirm")
	public String confirmEditEmployee(@PathVariable("id") Long id, Employee node, BindingResult bindingResult, Model model) {
		System.out.println(node);
		node.setId(id);
		employeeRepository.save(node);
		return "forward:/user/index";
	}

	@RequestMapping(value = "/edit/project/{id}/confirm")
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
			workedOn.setRole(roles.get(follow++));
			workedOnList.add(workedOn);
		}
		node.setWorkedOn(workedOnList);
		workedOnRepository.save(workedOnList);
		projectRepository.save(node);  //todo: NULL pointer exception
		return "forward:/user/index";
	}

	@RequestMapping(value = "/edit/sector/{id}/confirm")
	public String confirmEditSector(@PathVariable("id") Long id, Sector node, BindingResult bindingResult, Model model) {
		System.out.println(node);
		node.setId(id);
		sectorRepository.save(node);
		return "forward:/user/index";
	}

}
