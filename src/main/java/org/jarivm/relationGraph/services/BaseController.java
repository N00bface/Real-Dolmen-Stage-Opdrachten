package org.jarivm.relationGraph.services;

import org.jarivm.relationGraph.repositories.ClientRepository;
import org.jarivm.relationGraph.repositories.EmployeeRepository;
import org.jarivm.relationGraph.repositories.ProjectRepository;
import org.jarivm.relationGraph.repositories.SectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.text.SimpleDateFormat;

/**
 * @author Jari Van Melckebeke
 * @since 29.10.16.
 */
@ControllerAdvice
public class BaseController {
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    SectorRepository sectorRepository;
    @Autowired
    EmployeeRepository employeeRepository;


    @ModelAttribute("clientKeys")
    public String[] getClientKeys() {
        return clientRepository.findProperties();
    }

    @ModelAttribute("employeeKeys")
    public String[] getEmployeeKeys() {
        return employeeRepository.findProperties();
    }

    @ModelAttribute("projectKeys")
    public String[] getProjectKeys() {
        return projectRepository.findProperties();
    }

    @ModelAttribute("sectorKeys")
    public String[] getSectorKeys() {
        return sectorRepository.findProperties();
    }


}
