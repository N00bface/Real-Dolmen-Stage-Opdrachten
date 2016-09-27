package org.jarivm.relationGraph;

import org.jarivm.relationGraph.objects.domains.Client;
import org.jarivm.relationGraph.objects.domains.Employee;
import org.jarivm.relationGraph.objects.domains.Project;
import org.jarivm.relationGraph.objects.domains.Sector;
import org.neo4j.ogm.cypher.Filter;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.transaction.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.annotation.Query;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Jari Van Melckebeke
 * @since 24.09.16
 */
public class Facade extends Application {
    @Autowired
    Session session;
    Transaction tx;

    public Facade(Session session) throws Exception {
        this.session = session;
        if (this.session == null)
            this.session = getSession();
        tx = session.beginTransaction();
    }

    public Facade() throws Exception {
        this.session = getSession();
        tx = session.beginTransaction();
    }

    public void commit(Client client) {
        session.save(client);
    }

    public void commit(Employee employee) {
        session.save(employee);
    }

    public void commit(Project project) {
        session.save(project);
    }

    public void commit(Sector sector) {
        session.save(sector);
    }

    public void tearDown() {
        System.out.println(tx.status());
        tx.commit();
        tx.close();
    }

    public Iterable<Project> findProjectByProperty(String propertyName, String propertyValue) {
        return session.loadAll(Project.class, new Filter(propertyName, propertyValue));
    }

    public Iterable<Employee> findEmployeeByProperty(String propertyName, String propertyValue) {
        return session.loadAll(Employee.class, new Filter(propertyName, propertyValue));
    }

    public Iterable<Client> findClientByProperty(String propertyName, String propertyValue) {
        return session.loadAll(Client.class, new Filter(propertyName, propertyValue));
    }

    public Iterable<Sector> findSectorByProperty(String propertyName, String propertyValue) {
        return session.loadAll(Sector.class, new Filter(propertyName, propertyValue));
    }

    public Iterable<Sector> findAllSectors() {
        return session.loadAll(Sector.class);
    }

    public Iterable<Client> findAllClientsInSector(Sector sector) {
        Iterable<Client> clients = session.loadAll(Client.class);
        ArrayList<Client> ret = new ArrayList<Client>();
        for (Client c : clients) {
            if (c.getSector() == sector) {
                ret.add(c);
            }
        }
        return ret;
    }

    public Iterable<Project> findAllProjectsByClient(Client client) {
        Iterable<Client> clients = findAllClientsInSector(client.getSector());
        Iterable<Project> projectIt = session.loadAll(Project.class);
        ArrayList<Project> projects = new ArrayList<Project>();
        for (Project p : projectIt) {
            if (p.getClient() == client)
                projects.add(p);
        }
        return projects;
    }

    @Query("MATCH a=(:Employee)-[:WORKED_ON]->(p:Project) WHERE id(p)={0} RETURN a")
    Iterable<Employee> getTeamMates(Project client) {
        HashMap<String, Long> map = new HashMap<String, Long>();
        map.put("clientId", client.getId());
        return session.query(Employee.class, "MATCH a=(:Employee)-[:WORKED_ON]->(p:Project) WHERE id(p)={clientId} RETURN a", map);
    }
}
