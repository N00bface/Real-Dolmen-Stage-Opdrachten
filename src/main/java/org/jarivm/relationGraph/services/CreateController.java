package org.jarivm.relationGraph.services;

import org.jarivm.relationGraph.Application;
import org.jarivm.relationGraph.domains.*;
import org.jarivm.relationGraph.repositories.ClientRepository;
import org.jarivm.relationGraph.repositories.EmployeeRepository;
import org.jarivm.relationGraph.repositories.ProjectRepository;
import org.jarivm.relationGraph.repositories.SectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

/**
 * @author Jari Van Melckebeke
 * @since 17.10.16.
 */
@Controller
@RequestMapping("user/create")
@Transactional
public class CreateController extends Application {
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    SectorRepository sectorRepository;
    @Autowired
    EmployeeRepository employeeRepository;
    Map<String, Employee> employeeCache;


    @RequestMapping(value = "/createNewClient")
    public String createNewClient(@RequestParam(name = "sector") Long s, Client client, BindingResult bindingResult, Model model) {
        client.setSector(sectorRepository.findOne(s));
        System.out.println(client);
        clientRepository.save(client);
        return "/user/index";
    }

    @RequestMapping(value = "/createNewEmployee")
    public String createNewEmployee(Employee employee, BindingResult bindingResult, Model model) {
        System.out.println(employee);
        employeeRepository.save(employee);
        return "/user/index";
    }

    @RequestMapping(value = "/createNewSector")
    public String createNewSector(Sector sector, BindingResult bindingResult, Model model) {
        System.out.println(sector);
        sectorRepository.save(sector);
        return "/user/index";
    }

    @RequestMapping(value = "/createNewProject")
    public String createNewProject(@RequestParam(name = "employeesCollaborated") List<Long> employees,
                                   @RequestParam(name = "roles") List<String> roles,
                                   @RequestParam(name = "client") Long client,
                                   Project project,
                                   BindingResult bindingResult, Model model) {
        project.setClient(clientRepository.findById(client));
        List<Employee> employeesCollaborated = (List<Employee>) employeeRepository.findAll(employees);
        Set<WorkedOn> workedOnSet = new HashSet<WorkedOn>();
        for (int i = 0; i < employeesCollaborated.size(); i++) {
            WorkedOn workedOn = new WorkedOn(employeesCollaborated.get(i), project, roles.get(i));
            workedOn.setScore(employeeRepository.getAvgScore(employees.get(i)));
            workedOnSet.add(workedOn);
        }
        project.setWorkedOn(workedOnSet);
        projectRepository.save(project);
        return "/user/index";
    }
}
