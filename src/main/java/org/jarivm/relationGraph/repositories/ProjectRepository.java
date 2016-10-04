package org.jarivm.relationGraph.repositories;

import org.jarivm.relationGraph.domains.Client;
import org.jarivm.relationGraph.domains.Project;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author Jari Van Melckebeke
 * @since 02.10.16
 */
public interface ProjectRepository extends GraphRepository<Client> {
    Project findByName(@Param("0") String name);

    Collection<Project> findByNameContaining(@Param("0") String name);

    @Query("MATCH (m:Project)<-[WORKED_ON]-(a:Employee) RETURN m.name as project, collect([a.name, a.surname, a.role]) as team LIMIT {l}")
    List<Map<String, Project>> graph(@Param("l") int l);

}
