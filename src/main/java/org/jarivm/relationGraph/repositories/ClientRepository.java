/*
 * Copyright (c) 2017. MIT-license for Jari Van Melckebeke
 * Note that there was a lot of educational work in this project,
 * this project was (or is) used for an assignment from Realdolmen in Belgium.
 * Please just don't abuse my work
 */

package org.jarivm.relationGraph.repositories;

import org.jarivm.relationGraph.domains.Client;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author Jari Van Melckebeke
 * @since 02.10.16
 */
@Repository
public interface ClientRepository extends GraphRepository<Client> {
	@Query("MATCH (n:Client) WHERE n.name = {name} return n")
	Iterable<Client> findByName(@Param(value = "name") String name);

	@Query("MATCH a=(n:Client)-[:IsSectorFor]-(:Sector) WHERE id(n)={id} return a")
	Client findById(@Param(value = "id") Long id);

	@Query("MATCH (s:Sector)-[]->(m:Client)-[ISSUED]->(a:Project) RETURN s.name as sector, m.name as client, m.experience as experience," +
			"collect({id:a.id, name:a.name}) as projects ORDER BY s.name LIMIT {l}")
	List<Map<String, Client>> graph(@Param("l") Long l);

	@Query("MATCH n=(a:Client)<-[:IsSectorFor]-(:Sector) return n")
	Collection<Client> findAll();

	@Query("MATCH (n:Client) return keys(n) limit 1")
	String[] findProperties();

	@Query("MATCH (m:Client) WHERE ANY(prop in keys(m) where prop={propertyKey} and toString(m[prop]) contains {propertyValue}) RETURN m;")
	Iterable<Client> findByProperty(@Param("propertyKey") String propertyName, @Param("propertyValue") String propertyValue);

	@Query("MATCH (m:Client) where ANY(prop in keys(m) where tostring(m[prop]) contains {q}) return m")
	Iterable<Client> findByAny(@Param("q") String query);

	@Query("MATCH (e:Employee)-[:WorkedOn]-(:Project)-[:Issued]-(c:Client)-[:IsSectorFor]-(s:Sector) where id(e) = {EmpId} " +
			"with c match a=(c)<-[:IsSectorFor]-(:Sector) return a; ")
	Iterable<Client> findClientsByEmployee(@Param("EmpId") Long id);
}
