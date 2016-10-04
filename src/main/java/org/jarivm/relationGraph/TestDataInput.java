package org.jarivm.relationGraph;

import org.jarivm.relationGraph.domains.*;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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
        inputFile = new File("/home/jari/github/Real-Dolmen-stage-opdrachten/src/main/resources/MOCK_DATA.csv");
        fileReader = new FileReader(inputFile);
        bufferedReader = new BufferedReader(fileReader);
    }

    @Test
    public void include() throws IOException, ParseException {
        //structure: first_name, last_name,email, Gender, start_date_of_work, Age, experience,
        // total_efficiency, start_date_project,end_date_project, on_time, role_on_project,
        // n_of_project, deathline, name_of_project, version, cost,
        // client_experience, client_name
        String line = null;
        bufferedReader.readLine();
        String[] sectors = {"agriculture", "Information Technology", "Mining", "Real Estate", "Transport"};
        int follow = 0;
        int i = 0;
        while ((line = bufferedReader.readLine()) != null) {
            i++;
            System.out.println(line);
            String[] objects = line.split(",");
            DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            /*String name, String surname, String email, Date dateOfBirth, Long age, Date dateOfAssigment, String role*/
            Employee employee = new Employee();
            employee.setName(objects[0]);
            employee.setSurname(objects[1]);
            employee.setEmail(objects[2]);
            employee.setDateOfBirth(format.parse(objects[4]));
            employee.setAge(Long.parseLong(objects[5]));
            employee.setExperience(Long.parseLong(objects[6]));
            employee.setRole(objects[11]);
            Iterable<Project> p = facade.findObjectByProperty(Project.class, "name", objects[13]);
            Project project = (p.iterator().hasNext()) ? p.iterator().next() : null;
            if (project == null) {
                project = new Project();
                project.setName(objects[14]);
                project.setLanguage("java");
                project.setCost(Double.parseDouble(objects[16]));
                project.setnConflicts(0L);
                project.setVersion(objects[15]);
                project.setScoreFromClient(Double.parseDouble(objects[7]));
            }
            WorkedOn workedOn = new WorkedOn(employee, project, format.parse(objects[8]), objects[11]);
            System.out.println(objects[18]);
            Iterable<Client> c = facade.findObjectByProperty(Client.class, "name", objects[18]);
            Client client = (c.iterator().hasNext()) ? c.iterator().next() : null;
            if (client == null) {
                client = new Client();
                client.setName(objects[18]);
                client.setExperienceWithRealDolmen(Long.parseLong(objects[17]));
            }
            Issued issued = new Issued(client, project);
            Iterable<Sector> s = facade.findObjectByProperty(Sector.class, "name", sectors[follow]);
            Sector sector = (s.iterator().hasNext()) ? s.iterator().next() : null;
            if (sector == null) {
                sector = new Sector(sectors[follow]);
            }
            follow++;
            if (follow >= sectors.length) {
                follow = 0;
            }
            client.setSector(sector);
            facade.commit(issued);
            facade.commit(workedOn);
            System.out.println(employee);
        }
        facade.tearDown();
    }
}
