package org.jarivm.relationGraph.services;

import org.jarivm.relationGraph.Application;
import org.jarivm.relationGraph.repositories.ClientRepository;
import org.jarivm.relationGraph.repositories.EmployeeRepository;
import org.jarivm.relationGraph.repositories.ProjectRepository;
import org.jarivm.relationGraph.repositories.SectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Jari Van Melckebeke
 * @since 02.10.16
 */
@Controller
public class RepositoryController extends Application {
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    SectorRepository sectorRepository;
    @Autowired
    EmployeeRepository employeeRepository;

    private boolean authenticated = false;

    @RequestMapping("/login")
    public String login(Model model) {
        model.addAttribute("authToken", new AuthToken());
        return "login";
    }

    @RequestMapping("/auth")
    public String auth(AuthToken token, Model model) {
        if (token.getLoginName().equals("jari") && token.getLoginPass().equals("root")) {
            model.addAttribute("name", "jari");
            authenticated = true;
            //todo: connect with some sort of mySQL database
            return "access";
        }
        model.addAttribute("tried", true);
        return "login";
    }

    @RequestMapping("/tableOverview")
    public String graph(Model model) {
        if (authenticated) {
            model.addAttribute("graphClient", clientRepository.graph(150));
            model.addAttribute("graphProject", projectRepository.graph(150));
            return "tableOverview";
        }
        model.addAttribute("authToken", new AuthToken());
        return "login";
    }

    @RequestMapping("/employeeByScore")
    public String employeeByScore(Model model) {
        if (authenticated) {
            model.addAttribute("graphEmployee", employeeRepository.employeesOfAllTime(150));
            return "employeeByScore";
        }
        model.addAttribute("authToken", new AuthToken());
        return "login";
    }
}
