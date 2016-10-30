package org.jarivm.relationGraph.services;

import com.fasterxml.jackson.databind.deser.Deserializers;
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
    public String userIndex() {
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

    @RequestMapping("/newClient")
    public String newClient(Model model) {
        model.addAttribute("clientToken", new Client());
        model.addAttribute("sectors", sectorRepository.findAll());
        return "/user/create/newClient";
    }

    @RequestMapping("/newEmployee")
    public String newEmployee(Model model) {
        model.addAttribute("employeeToken", new Employee());
        return "/user/create/newEmployee";
    }

    @RequestMapping("/newSector")
    public String newSector(Model model) {
        model.addAttribute("sectorToken", new Sector());
        return "/user/create/newSector";
    }

    @RequestMapping("/newProject")
    public String newProject(Model model) {
        model.addAttribute("projectToken", new Project());
        model.addAttribute("employees", employeeRepository.findAll());
        model.addAttribute("clients", clientRepository.findAll());
        return "/user/create/newProject";
    }
}
