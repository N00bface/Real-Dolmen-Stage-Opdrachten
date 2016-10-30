package org.jarivm.relationGraph.repositories;

import org.jarivm.relationGraph.domains.Client;
import org.jarivm.relationGraph.domains.Project;
import org.jarivm.relationGraph.domains.WorkedOn;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author Jari Van Melckebeke
 * @since 02.10.16
 */
@Repository
public interface ProjectRepository extends GraphRepository<Project> {
    @Query("MATCH (n:Project)<-[WORKED_ON]-(a:Employee) WHERE n.name = {name} return n.name as name, n.cost as cost, " +
            "n.language as language, n.nConflicts as nConflicts, n.scoreFromClient as score," +
            "collect({name:a.name ,surname:a.surname ,role:a.role , dateOfBirth:a.dateOfBirth ,experience:a.experience, email:a.email, age:a.age}) as projects")
    List<Map<String, Project>> findByName(@Param(value = "name") String name);

    @Query("MATCH (n:Project)<-[WORKED_ON]-(a:Employee) WHERE id(n)={id} return n.name as name, n.cost as cost, " +
            "n.language as language, n.nConflicts as nConflicts, n.scoreFromClient as score," +
            "collect({name:a.name ,surname:a.surname ,role:a.role , dateOfBirth:a.dateOfBirth ,experience:a.experience, " +
            "email:a.email, age:a.age}) as projects")
    List<Map<String, Project>> findById(@Param(value = "id") Long id);

    Collection<Project> findByNameContaining(@Param("0") String name);

    @Query("MATCH (m:Project)<-[r:WorkedOn]-(a:Employee) RETURN m.name as name, m.cost as cost," +
            "sum(r.score)/toFloat(count(r)) as score, m.version as version," +
            " collect({id: id(a)}) as team LIMIT {l}")
    List<Map<String, Project>> graph(@Param("l") Long l);

    @Query("MATCH (n:Project) return keys(n) limit 1")
    String[] findProperties();

    @Query("MATCH (m:Project) WHERE ANY(prop in keys(m) where prop={propertyKey} and m[prop] contains {propertyValue}) RETURN m;")
    Iterable<Project> findByProperty(@Param("propertyKey") String propertyName, @Param("propertyValue") String propertyValue);
}
