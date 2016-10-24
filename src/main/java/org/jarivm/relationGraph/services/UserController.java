package org.jarivm.relationGraph.services;

import org.jarivm.relationGraph.Application;
import org.jarivm.relationGraph.domains.*;
import org.jarivm.relationGraph.repositories.ClientRepository;
import org.jarivm.relationGraph.repositories.EmployeeRepository;
import org.jarivm.relationGraph.repositories.ProjectRepository;
import org.jarivm.relationGraph.repositories.SectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.Banner;
import org.springframework.data.neo4j.template.Neo4jOperations;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

/**
 * @author Jari Van Melckebeke
 * @since 17.10.16.
 */
@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private SectorRepository sectorRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    public UserController() {

    }

    @RequestMapping("/index")
    public String userIndex() {
        return "/user/index";
    }

    @RequestMapping(value = "/tableOverview")
    public String graph(@RequestParam(name = "limit", defaultValue = "150", required = false) int limit, Model model) {
        model.addAttribute("graphClient", clientRepository.graph(limit));
        model.addAttribute("graphProject", projectRepository.graph(limit));
        return "/user/tableOverview";
    }

    @RequestMapping(value = "/employeeByScore")
    public String employeeByScore(@RequestParam(name = "limit", defaultValue = "150", required = false) int limit, Model model) {
        model.addAttribute("graphEmployee", employeeRepository.employeesOfAllTime(limit));
        return "/user/employeeByScore";
    }

    @RequestMapping(value = "/search")
    public String search(Model model) {
        model.addAttribute("clients", clientRepository.findAll());
        model.addAttribute("employees", employeeRepository.findAll());
        model.addAttribute("projects", projectRepository.findAll());
        model.addAttribute("sectors", sectorRepository.findAll());
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
