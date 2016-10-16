package org.jarivm.relationGraph.repositories;

import org.jarivm.relationGraph.domains.Employee;
import org.jarivm.relationGraph.domains.Project;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Jari Van Melckebeke
 * @since 10.10.16
 */
@Repository
public interface EmployeeRepository extends GraphRepository<Employee> {
    @Query("MATCH total=(n:Employee)-[r:WorkedOn]->(p:Project)\n" +
            "RETURN sum(toFloat(p.total_efficiency)) / toFloat(count(p)) as score , n.first_name as name, n.last_name as surname, collect(r.role) as roles, n.start_date_of_work as startDate, n.experience as experience, \n" +
            "        n.email as email, n.age as age, collect(p.name) as projects ORDER BY score DESC, surname, name LIMIT {l}")
    List<Map<String, Project>> employeesOfAllTime(@Param("l") int l);
}
