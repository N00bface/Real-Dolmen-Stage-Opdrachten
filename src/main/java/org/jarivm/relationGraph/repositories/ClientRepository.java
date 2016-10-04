package org.jarivm.relationGraph.repositories;

import org.jarivm.relationGraph.domains.Client;
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
public interface ClientRepository extends GraphRepository<Client> {
    @Query("MATCH (n:Client)-[ISSUED]->(a:Project) WHERE n.name = {name} return n.name as name, n.experienceWithRealDolmen as experience, " +
            "collect({name:a.name ,version:a.version ,lang: a.language , cost:a.cost ,score:a.scoreFromClient}) as projects")
    List<Map<String, Client>> findByName(@Param(value = "name") String name);

    Collection<Client> findByNameContaining(@Param(value = "0") String name);

    @Query("MATCH (m:Client)-[ISSUED]->(a:Project) RETURN m.name as client, collect(a.name) as projects LIMIT {l}")
    List<Map<String, Client>> graph(@Param("l") int l);
}
