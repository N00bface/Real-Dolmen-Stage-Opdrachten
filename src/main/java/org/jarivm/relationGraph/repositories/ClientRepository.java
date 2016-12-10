package org.jarivm.relationGraph.repositories;

import org.jarivm.relationGraph.domains.Client;
import org.neo4j.ogm.annotation.Property;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Jari Van Melckebeke
 * @since 02.10.16
 */
@Repository
public interface ClientRepository extends GraphRepository<Client> {
    @Query("MATCH (n:Client)-[:ISSUED]->(a:Project) WHERE n.name = {name} return n.name as name, n.experienceWithRealDolmen as experience, " +
            "collect({name:a.name ,version:a.version ,lang: a.language , cost:a.cost ,score:a.scoreFromClient}) as projects")
    List<Map<String, Client>> findByName(@Param(value = "name") String name);

    @Query("MATCH (n:Client) WHERE id(n)={id} return n")
    Client findById(@Param(value = "id") Long id);

    @Query("MATCH (s:Sector)-[]->(m:Client)-[ISSUED]->(a:Project) RETURN s.name as sector, m.name as client, m.experience as experience," +
            "collect({id:a.id, name:a.name}) as projects ORDER BY s.name LIMIT {l}")
    List<Map<String, Client>> graph(@Param("l") Long l);

    @Query("MATCH (n:Client) return n")
    Collection<Client> findAll();

    @Query("MATCH (n:Client) return keys(n) limit 1")
    String[] findProperties();

    @Query("MATCH (m:Client) WHERE ANY(prop in keys(m) where prop={propertyKey} and m[prop] contains {propertyValue}) RETURN m;")
    Iterable<Client> findByProperty(@Param("propertyKey") String propertyName, @Param("propertyValue") String propertyValue);
}
