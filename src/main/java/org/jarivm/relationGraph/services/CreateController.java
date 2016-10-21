package org.jarivm.relationGraph.services;

import org.jarivm.relationGraph.Application;
import org.jarivm.relationGraph.domains.*;
import org.jarivm.relationGraph.repositories.ClientRepository;
import org.jarivm.relationGraph.repositories.EmployeeRepository;
import org.jarivm.relationGraph.repositories.ProjectRepository;
import org.jarivm.relationGraph.repositories.SectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

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


    @RequestMapping(value = "/createNewClient")
    public String createNewClient(Client client, BindingResult bindingResult, Model model) {
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
    public String createNewProject(Project project, BindingResult bindingResult, Model model) {
        System.out.println(project);
        List<WorkedOn> workedOns = project.getWorkedOn();
        projectRepository.save(project);
        return "/user/index";
    }
}
