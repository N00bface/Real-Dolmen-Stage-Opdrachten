package org.jarivm.relationGraph.services;

import org.jarivm.relationGraph.Application;
import org.jarivm.relationGraph.domains.Client;
import org.jarivm.relationGraph.domains.Project;
import org.jarivm.relationGraph.domains.Sector;
import org.jarivm.relationGraph.repositories.ClientRepository;
import org.jarivm.relationGraph.repositories.ProjectRepository;
import org.jarivm.relationGraph.repositories.SectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author Jari Van Melckebeke
 * @since 02.10.16
 */
@RestController("/")
public class RESTController extends Application {

    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    SectorRepository sectorRepository;

    @RequestMapping(value = "/clients")
    public List<Map<String, Client>> clients(@RequestParam(value = "limit", required = false) Integer limit) {
        return clientRepository.graph((limit == null) ? 25 : limit);
    }

    @RequestMapping(value = "/clients/{name}")
    public List<Map<String, Client>> clientByName(@PathVariable(name = "name") String name) {
        return clientRepository.findByName(name);
    }

    @RequestMapping(value = "/projects")
    public List<Map<String, Project>> projects(@RequestParam(value = "limit", required = false) Integer limit) throws Exception {
        return projectRepository.graph((limit == null) ? 25 : limit);
    }

    @RequestMapping(value = "/sectors")
    public List<Map<String, Sector>> sectors(@RequestParam(value = "limit", required = false) Integer limit) throws Exception {
        return sectorRepository.graph((limit == null) ? 25 : limit);
    }
}
