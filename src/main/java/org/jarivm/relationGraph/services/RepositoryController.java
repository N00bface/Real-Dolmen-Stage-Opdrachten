package org.jarivm.relationGraph.services;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.jarivm.relationGraph.Application;
import org.jarivm.relationGraph.domains.Client;
import org.jarivm.relationGraph.domains.Sector;
import org.jarivm.relationGraph.repositories.ClientRepository;
import org.jarivm.relationGraph.repositories.EmployeeRepository;
import org.jarivm.relationGraph.repositories.ProjectRepository;
import org.jarivm.relationGraph.repositories.SectorRepository;
import org.neo4j.ogm.session.Neo4jSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.neo4j.template.Neo4jOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.LinkedHashMap;

/**
 * @author Jari Van Melckebeke
 * @since 02.10.16
 */
@Controller
public class RepositoryController extends BaseController{

    @RequestMapping("/auth")
    public String auth(Model model) {
        model.addAttribute("name", "jari");
        return "access";
    }

    @RequestMapping("/")
    public String root() {
        return "redirect:/index";
    }

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }
}
