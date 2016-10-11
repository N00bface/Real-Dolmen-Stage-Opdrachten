package org.jarivm.relationGraph.repositories;

import org.jarivm.relationGraph.domains.Employee;
import org.jarivm.relationGraph.domains.Project;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

/**
 * @author Jari Van Melckebeke
 * @since 10.10.16
 */
public interface EmployeeRepository extends GraphRepository<Employee> {
    @Query("MATCH total=(n:Employee)-[r:WORKED_ON]->(p:Project)\n" +
            "RETURN p.scoreFromClient/count(p) as score , n.name as name, n.surname as surname, n.role as role, n.dateOfBirth as dateOfBirth, n.experience as experience, \n" +
            "        n.email as email, n.age as age, collect(p.name) as projects ORDER BY score DESC LIMIT {l}")
    List<Map<String, Project>> employeesOfAllTime(@Param("l") int l);
}
