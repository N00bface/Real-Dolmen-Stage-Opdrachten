package org.jarivm.relationGraph;

import org.jarivm.relationGraph.objects.domains.Client;
import org.jarivm.relationGraph.objects.domains.Employee;
import org.jarivm.relationGraph.objects.domains.Project;
import org.jarivm.relationGraph.objects.domains.Sector;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Jari Van Melckebeke
 * @since 26.09.16
 */
public class TestDataInput extends Application {
    Facade facade;
    File inputFile;
    FileReader fileReader;
    BufferedReader bufferedReader;

    @Before
    public void setUp() throws Exception {
        facade = new Facade();
        inputFile = new File("/home/jari/github/Real-Dolmen-stage-opdrachten/src/main/resources/data_neo4j_graph web app/person_project_client_sector.csv");
        fileReader = new FileReader(inputFile);
        bufferedReader = new BufferedReader(fileReader);
    }

    @Test
    public void include() throws IOException {
        //structure: project, first_name, last_name, role, company, sector
        String line = null;

        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(line);
            String[] objects = line.split(",");
            if (facade.findProjectByProperty("name", objects[0]).iterator().hasNext()) {
                Project p = facade.findProjectByProperty("name", objects[0]).iterator().next();
                Employee e = new Employee(objects[1], objects[2], objects[3]);
                p.getTeam().add(e);
                facade.commit(e);
                facade.commit(p);
            } else {
                if (facade.findClientByProperty("name", objects[4]).iterator().hasNext()) {
                    Client c = facade.findClientByProperty("name", objects[4]).iterator().next();
                    Project p = new Project(objects[0]);
                    Employee e = new Employee(objects[1], objects[2], objects[3]);
                    Set<Employee> set = new HashSet<Employee>();
                    set.add(e);
                    p.setTeam(set);
                    p.setClient(c);
                    facade.commit(e);
                    facade.commit(c);
                    facade.commit(p);
                } else {
                    if (facade.findSectorByProperty("name", objects[5]).iterator().hasNext()) {
                        Sector s = facade.findSectorByProperty("name", objects[5]).iterator().next();
                        Client c = new Client(objects[4]);
                        c.setSector(s);
                        Project p = new Project(objects[0]);
                        Employee e = new Employee(objects[1], objects[2], objects[3]);
                        Set<Employee> set = new HashSet<Employee>();
                        set.add(e);
                        p.setTeam(set);
                        p.setClient(c);
                        facade.commit(e);
                        facade.commit(c);
                        facade.commit(p);
                    } else {
                        Sector s = new Sector(objects[5]);
                        Client c = new Client(objects[4]);
                        c.setSector(s);
                        Project p = new Project(objects[0]);
                        Employee e = new Employee(objects[1], objects[2], objects[3]);
                        Set<Employee> set = new HashSet<Employee>();
                        set.add(e);
                        p.setTeam(set);
                        p.setClient(c);
                        facade.commit(s);
                        facade.commit(e);
                        facade.commit(c);
                        facade.commit(p);
                    }
                }
            }
        }
        facade.tearDown();
    }
}
