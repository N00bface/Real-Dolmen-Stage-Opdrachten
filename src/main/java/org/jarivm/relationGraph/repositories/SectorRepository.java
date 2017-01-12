/*
 * Copyright (c) 2017. MIT-license for Jari Van Melckebeke
 * Note that there was a lot of educational work in this project,
 * this project was (or is) used for an assignment from Realdolmen in Belgium.
 * Please just don't abuse my work
 */

package org.jarivm.relationGraph.repositories;

import org.jarivm.relationGraph.domains.Project;
import org.jarivm.relationGraph.domains.Sector;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Jari Van Melckebeke
 * @since 02.10.16
 */
@Repository
public interface SectorRepository extends GraphRepository<Sector> {
	@Query("MATCH (m:Sector) WHERE m.name={name} return m limit 1")
	LinkedHashMap<String, Object> findByName(@Param("name") String name);

	Collection<Project> findByNameContaining(@Param("0") String name);

	@Query("MATCH (n:Sector) where id(n)={id} return n")
	Sector findById(@Param("id") Long id);

	@Query("MATCH (m:Sector)-[IS_SECTOR_FOR]->(a:Client) RETURN m.name as sector, collect(a.name) as clients")
	List<Map<String, Sector>> graph(@Param("l") int l);

	@Query("MATCH (n:Sector) return keys(n) limit 1")
	String[] findProperties();

	@Query("MATCH (m:Sector) WHERE ANY(prop in keys(m) where prop={propertyKey} and m[prop] contains {propertyValue}) RETURN m;")
	Iterable<Sector> findByProperty(@Param("propertyKey") String propertyName, @Param("propertyValue") String propertyValue);

	@Query("MATCH (m:Sector) where ANY(prop in keys(m) where tostring(m[prop]) contains {q}) return m")
	Iterable<Sector> findByAny(@Param("q") String query);
}
