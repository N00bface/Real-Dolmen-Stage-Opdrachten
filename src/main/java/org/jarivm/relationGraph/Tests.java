package org.jarivm.relationGraph;

import org.jarivm.relationGraph.domains.Employee;
import org.jarivm.relationGraph.domains.Project;
import org.jarivm.relationGraph.domains.WorkedOn;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.Iterator;


/**
 * @author Jari Van Melckebeke
 * @since 02.09.16
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles(profiles = "test")
@Service
@ContextConfiguration(classes = org.jarivm.relationGraph.Application.class, loader = AnnotationConfigContextLoader.class)
public class Tests{
    private Facade facade;

    @Before
    public void setUp() throws Exception {
        facade = new Facade();
    }

    @After
    public void tearDown() throws Exception {
        facade.tearDown();
    }


    @Test
    public void persistedEmployeeShouldBeRetrievableFromGraphDB() {
        Employee employee = new Employee("John", "Adams");
        if (!facade.findObjectByProperty(Employee.class, "name", employee.getName()).iterator().hasNext()) {
            facade.commit(employee);
            Employee foundHim = facade.findObjectByProperty(Employee.class, "name", employee.getName()).iterator().next();
            assert foundHim.getId().equals(employee.getId());
        }
    }

    @Test
    public void createdNodesShoudBeEditable() {
        Iterator<Employee> employees = facade.findAll(Employee.class).iterator();
        Project project = facade.findAll(Project.class).iterator().next();
        for (int i = 0; i < 5; i++) {
            Employee e = employees.next();
            WorkedOn workedOn = new WorkedOn(e, project);
            facade.commit(workedOn);
        }
    }

    @Test
    public void teamMatesShouldBeViewable() {
        Project p = facade.findObjectByProperty(Project.class, "name", "Matsoft").iterator().next();
        Iterable<Employee> e = facade.getTeamMates(p);
        System.out.println(e.iterator());
        for (Employee em : e) {
            System.out.println(em);
        }
    }
}