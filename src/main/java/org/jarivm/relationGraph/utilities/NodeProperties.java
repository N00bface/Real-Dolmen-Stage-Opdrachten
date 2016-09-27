package org.jarivm.relationGraph.utilities;

import org.jarivm.relationGraph.Facade;
import org.jarivm.relationGraph.objects.domains.Employee;
import org.jarivm.relationGraph.objects.domains.Project;
import org.springframework.data.neo4j.template.Neo4jTemplate;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Jari Van Melckebeke
 * @since 24.09.16
 */
public class NodeProperties {


    public String getRelation(Iterable<Employee> employees) throws Exception {
        //todo: union find
        Facade facade = new Facade();
        Neo4jTemplate template = new Neo4jTemplate(facade.getSession());
        Collection<Long> ids = new ArrayList<Long>();
        for (Employee e : employees)
            ids.add(e.getId());
        Collection collection = template.loadAll(Project.class);
        for (Object obj : collection) {
            if (obj instanceof Project)
                if (((Project) obj).getTeam().containsAll((Collection<Employee>) employees))
                    return "WORKED ON SAME PROJECT";
        }
        return "NO RELATION";
    }
}
