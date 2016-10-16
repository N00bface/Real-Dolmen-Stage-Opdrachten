package org.jarivm.relationGraph.repositories;

import org.jarivm.relationGraph.domains.Client;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Jari Van Melckebeke
 * @since 02.10.16
 */
@Repository
public interface ClientRepository extends GraphRepository<Client> {
    @Query("MATCH (n:Client)-[ISSUED]->(a:Project) WHERE n.name = {name} return n.name as name, n.experienceWithRealDolmen as experience, " +
            "collect({name:a.name ,version:a.version ,lang: a.language , cost:a.cost ,score:a.scoreFromClient}) as projects")
    List<Map<String, Client>> findByName(@Param(value = "name") String name);

    @Query("MATCH (n:Client)-[ISSUED]->(a:Project) WHERE id(n)={id} return n.name as name, n.experienceWithRealDolmen as experience, " +
            "collect({name:a.name ,version:a.version ,lang: a.language , cost:a.cost ,score:a.scoreFromClient}) as projects")
    List<Map<String, Client>> findById(@Param(value = "id") Long id);

    @Query("MATCH (s:Sector)-[]->(m:Client)-[ISSUED]->(a:Project) RETURN s.name as sector, m.name as client, m.experience as experience," +
            "collect({id:a.id, name:a.name}) as projects ORDER BY s.name LIMIT {l}")
    List<Map<String, Client>> graph(@Param("l") int l);
}
