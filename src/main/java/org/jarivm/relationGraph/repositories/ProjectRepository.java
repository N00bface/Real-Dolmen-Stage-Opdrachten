/*
 * Copyright (c) 2016. MIT-license for Jari Van Melckebeke
 * Note that there was a lot of educational work in this project,
 * this project was (or is) used for an assignment from Realdolmen in Belgium.
 * Please just don't abuse my work
 */

package org.jarivm.relationGraph.repositories;

import org.jarivm.relationGraph.domains.Project;
import org.jarivm.relationGraph.domains.WorkedOn;
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
public interface ProjectRepository extends GraphRepository<Project> {
	@Query("MATCH (n:Project)<-[WORKED_ON]-(a:Employee) WHERE n.name = {name} return n.name as name, n.cost as cost, " +
			"n.language as language, n.nConflicts as nConflicts, n.scoreFromClient as score," +
			"collect({name:a.name ,surname:a.surname ,role:a.role , dateOfBirth:a.dateOfBirth ,experience:a.experience, email:a.email, age:a.age}) as projects")
	List<Map<String, Project>> findByName(@Param(value = "name") String name);

	@Query("MATCH r=(c:Client)-[:Issued]->(n:Project)<-[w:WorkedOn]-() where id(n)={id} return r")
	Project findById(@Param(value = "id") Long id);

	Collection<Project> findByNameContaining(@Param("0") String name);

	@Query("MATCH (m:Project)<-[r:WorkedOn]-(a:Employee) RETURN m.name as name, m.cost as cost," +
			"sum(r.score)/toFloat(count(r)) as score, m.version as version," +
			" collect({id: id(a)}) as team LIMIT {l}")
	List<Map<String, Project>> graph(@Param("l") Long l);

	@Query("MATCH (n:Project) return keys(n) limit 1")
	String[] findProperties();

	@Query("MATCH r=(:Client)-[:Issued]->(m:Project)<-[:WorkedOn]-() WHERE ANY(prop in keys(m) where prop={propertyKey} and m[prop] contains {propertyValue}) RETURN r")
	Iterable<Project> findByProperty(@Param("propertyKey") String propertyName, @Param("propertyValue") String propertyValue);

	@Query("MATCH (:Client)-[:Issued]->(p:Project)<-[r:WorkedOn]-(e:Employee) where id(p)={projectId} and id(e) in {empIds} return r order by id(e)")
	Iterable<WorkedOn> findWorkedOnsByEmployees(@Param("empIds") List<Long> employees, @Param("projectId") Long projectId);

	@Query("MATCH (:Client)-[:Issued]->(p:Project)<-[r:WorkedOn]-(e:Employee) where id(p)={projectId} and id(e)={empId} return r")
	WorkedOn findWorkedOnByEmployee(@Param("empId") Long employee, @Param("projectId") Long projectId);

	@Query("MATCH (c:Client)-[:Issued]->(p:Project)<-[:WorkedOn]-(:Employee) where c.name={clientName} with p match a=(p)<-[:WorkedOn]-() return a")
	Iterable<Project> findByClientName(@Param("clientName") String name);

	@Query("MATCH a=(c:Client)-[:Issued]->(p:Project) where id(p)={target} and c.name={client} return length(a)>0")
	boolean relationToWithClient(@Param("client") String client, @Param("target") Long target);

	@Query("MATCH a=(e:Employee)-[r:WorkedOn]->(p:Project) where id(p)={target} and c.name={employee} and r.role='project leader' return length(a)>0")
	boolean relationToWithProjectLeader(@Param("employee") String projectLeaderName, @Param("target") Long projectId);

	@Query("MATCH a=(e:Employee)-[r:WorkedOn]->(p:Project) where id(p)={target} and c.name={employee} return length(a)>0")
	boolean relationToWithEmployee(@Param("employee") String employee, @Param("target") Long projectId);
}
