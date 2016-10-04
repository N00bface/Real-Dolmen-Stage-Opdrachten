package org.jarivm.relationGraph;

import org.jarivm.relationGraph.domains.Client;
import org.jarivm.relationGraph.domains.Employee;
import org.jarivm.relationGraph.domains.Project;
import org.jarivm.relationGraph.domains.Sector;
import org.neo4j.ogm.cypher.Filter;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.transaction.Transaction;

import java.util.HashMap;

/**
 * this class is responsible for transactions to (and some from) the database
 *
 * @author Jari Van Melckebeke
 * @since 24.09.16
 */
public class Facade extends Application {
    Session session;
    Transaction tx;

    /**
     * basic constructor if there's a session
     *
     * @param session the session parameter, used to control transactions to and from
     */
    Facade(Session session) throws Exception {
        this.session = session;
        if (this.session == null)
            this.session = getSession();
        tx = session.beginTransaction();
    }

    /**
     * basic constructor
     */
    public Facade() throws Exception {
        this.session = getSession();
        tx = session.beginTransaction();
    }

    /**
     * saves the passed object to the database
     *
     * @param object the object that has to be saved to the database
     * @param <T>    all types that have graph elements
     */
    <T> void commit(T object) {
        System.out.println("committing: " + object);
        session.save(object);
        tx.commit();
        tx.close();
        tx = session.beginTransaction();
    }

    /**
     * ends the transaction, has to be used on the end of the program
     */
    public void tearDown() {
        tx.commit();
        tx.close();
    }

    /**
     * find an object of type T in the database by propertyValue
     *
     * @param c             the object's class that has to be searched
     * @param propertyName  the content that has to be compared, for example 'name'
     * @param propertyValue the propertyName his value
     * @param <T>           the object that has to be searched for
     * @return an iterable with the matched objects
     */
    <T> Iterable<T> findObjectByProperty(Class<T> c, String propertyName, String propertyValue) {
        return session.loadAll(c, new Filter(propertyName, propertyValue));
    }

    /**
     * finds all nodes with the given class (label)
     *
     * @param s the class
     * @return all nodes with class 's'
     */
    <T> Iterable<T> findAll(Class<T> s) {
        return session.loadAll(s);
    }

    /**
     * returns employees that worked on a given project
     *
     * @param project the project where the employees were working on
     * @return an iterable of Employee with the matched worked value
     */
    Iterable<Employee> getTeamMates(Project project) {
        HashMap<String, Long> map = new HashMap<String, Long>();
        map.put("projectId", project.getId());
        return session.query(Employee.class, "MATCH a=(n:Employee)-[:WORKED_ON]->(m:Project) WHERE id(m)={projectId} return a", map);
    }

    Iterable<Project> getProjects(Client client) {
        HashMap<String, Long> map = new HashMap<String, Long>();
        map.put("clientId", client.getId());
        return session.query(Project.class, "match (n:Client)-[:ISSUED]->(b:Project) WHERE id(n)={ClientId} return b", map);
    }

    Iterable<Client> getClients(Sector sector) {
        HashMap<String, Long> map = new HashMap<String, Long>();
        map.put("sectorId", sector.getId());
        return session.query(Client.class, "match (n:Client)-[:ISSUED]->(b:Project) WHERE id(n)={sectorId} return a", map);
    }
}
