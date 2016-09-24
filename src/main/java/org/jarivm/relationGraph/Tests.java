package org.jarivm.relationGraph;

import org.apache.commons.collections4.set.ListOrderedSet;
import org.jarivm.relationGraph.objects.domains.Client;
import org.jarivm.relationGraph.objects.domains.Employee;
import org.jarivm.relationGraph.objects.domains.Project;
import org.jarivm.relationGraph.objects.domains.Sector;
import org.junit.Before;
import org.junit.Test;
import org.neo4j.ogm.cypher.Filter;
import org.neo4j.ogm.session.Session;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;


/**
 * @author Jari Van Melckebeke
 * @since 02.09.16
 */
public class Tests extends Application {
    @Autowired
    private Session session;

    @Before
    public void tests() throws Exception {
        session = getSession();
        session.clear();
    }

    /**
     * test function
     */
    @Test
    public void persistedEmployeeShouldBeRetrievableFromGraphDB() {
        Employee employee = new Employee("john", "adams");
        //System.out.println(session.getTransaction().status());
        if (!findEmployeeByProperty("name", employee.getName()).iterator().hasNext()) {
            session.save(employee);

            Employee foundHim = findEmployeeByProperty("name", employee.getName()).iterator().next();
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
        session.save(sector);
        session.save(employee);
        session.save(client);
        session.save(project);

        Client foundHim = findClientByProperty("name", client.getName()).iterator().next();
        assert foundHim.getId().equals(client.getId());
        assert foundHim.getName().equals(client.getName());
    }

    @Test
    public void checkSession() {
        assert session != null;
    }


    private Iterable<Employee> findEmployeeByProperty(String propertyName, String propertyValue) {
        return session.loadAll(Employee.class, new Filter(propertyName, propertyValue));
    }

    private Iterable<Client> findClientByProperty(String propertyName, String propertyValue) {
        return session.loadAll(Client.class, new Filter(propertyName, propertyValue));
    }


}
