package org.jarivm.relationGraph;

import org.jarivm.relationGraph.objects.domains.Employee;
import org.jarivm.relationGraph.objects.domains.Project;
import org.jarivm.relationGraph.objects.repositories.EmployeeRepository;
import org.jarivm.relationGraph.utilities.NodeProperties;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.HashSet;
import java.util.Iterator;


/**
 * @author Jari Van Melckebeke
 * @since 02.09.16
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = org.jarivm.relationGraph.Application.class, loader = AnnotationConfigContextLoader.class)
public class Tests {
    private Facade facade;
    @Autowired
    private EmployeeRepository employeeRepository;

    @Before
    public void setUp() throws Exception {
        facade = new Facade();
    }

    @After
    public void tearDown() throws Exception {
        facade.tearDown();
    }

/*
    @Test
    public void persistedEmployeeShouldBeRetrievableFromGraphDB() {
        Employee employee = new Employee("john", "adams");
        //System.out.println(session.getTransaction().status());
        if (!facade.findEmployeeByProperty("name", employee.getName()).iterator().hasNext()) {
            facade.commit(employee);

            Employee foundHim = facade.findEmployeeByProperty("name", employee.getName()).iterator().next();
            assert foundHim.getId().equals(employee.getId());
            assert foundHim.getName().equals(employee.getName());
        }
    }

    @Test
    public void persistedChainShouldBeRetrievableFromGraphDB() {
        Employee employee = new Employee("john", "myles");
        Client client = new Client();
        Sector sector = new Sector();
        Project project = new Project();
        client.setName("Real Dolmen");
        project.setClient(client);
        project.setCost(100.0);
        project.setName("project highrise");
        Set<Employee> set = new ListOrderedSet<Employee>();
        set.add(employee);
        project.setTeam(set);
        sector.setName("game");
        client.setSector(sector);
        facade.commit(sector);
        facade.commit(employee);
        facade.commit(client);
        facade.commit(project);

        Client foundHim = facade.findClientByProperty("name", client.getName()).iterator().next();
        assert foundHim.getId().equals(client.getId());
        assert foundHim.getName().equals(client.getName());
    }


    @Test
    public void projectShouldBeInsertableAlone() {
        Project project = new Project();
        project.setName("random");
        project.setLanguage("Java");
        facade.commit(project);

        Project foundHim = facade.findProjectByProperty("name", project.getName()).iterator().next();
        assert foundHim.getId().equals(project.getId());
    }

    @Test
    public void clientShouldBeInsertableAlone() {
        Client client = new Client();
        client.setName("Colruyt");

        facade.commit(client);

        Client foundHim = facade.findClientByProperty("name", client.getName()).iterator().next();
        assert foundHim.getId().equals(client.getId());
    }*/

    @Test
    public void createdNodesShoudBeEditable() {
        Iterator<Employee> employees = facade.findEmployeeByProperty("name", "john").iterator();
        Project project = facade.findProjectByProperty("name", "random").iterator().next();
        while (employees.hasNext()) {
            Employee e = employees.next();
            if (project.getTeam() == null)
                project.setTeam(new HashSet<Employee>());
            project.getTeam().add(e);
        }
        facade.commit(project);
    }

    @Test
    public void teamMatesShouldBeViewable() {
        Project p = facade.findProjectByProperty("name", "Matsoft").iterator().next();
        Iterable<Employee> e = facade.getTeamMates(p);
        System.out.println(e.iterator());
        for (Employee em : e) {
            System.out.println(em);
        }
    }

    @Test
    public void relationShouldBeRetrievableBetweenEmployees() throws Exception {
        NodeProperties properties = new NodeProperties();
        Iterable<Employee> employees = facade.findEmployeeByProperty("name", "john");
        System.out.println(properties.getRelation(employees));
    }


}
