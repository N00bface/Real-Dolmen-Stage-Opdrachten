/*
 * Copyright (c) 2016. MIT-license for Jari Van Melckebeke
 * Note that there was a lot of educational work in this project,
 * this project was (or is) used for an assignment from Realdolmen in Belgium.
 * Please just don't abuse my work
 */

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
	@Query("MATCH total=(n:Employee)-[r:WorkedOn]->(p:Project) " +
			"RETURN sum(toFloat(r.score)) / toFloat(count(p)) as score , n.name as name, n.surname as surname, collect(r.role) as roles, n.start_date_of_work as startDate, n.experience as experience, \n" +
			"        n.email as email, n.age as age, collect(p.name) as projects ORDER BY score DESC, surname, name LIMIT {l}")
	List<Map<String, Project>> employeesOfAllTime(@Param("l") Long l);

	@Query("MATCH (n:Employee) where id(n)={id} return n")
	Employee findById(@Param(value = "id") Long id);

	@Query("MATCH (n:Employee) return n")
	Iterable<Employee> findAll();

	@Query("MATCH (n:Employee) where id(n) in {ids} return n")
	Iterable<Employee> findAll(@Param(value = "ids") Iterable<Long> ids);

	@Query("MATCH (n:Employee) where n.surname={surname} return n")
	List<Map<String, Employee>> findBySurname(@Param(value = "surname") String surname);

	@Query("MATCH (n:Employee)-[r:WorkedOn]->(p:Project) where id(n)={id} return sum(toFloat(p.efficiency))/toFloat(count(p))")
	Long getAvgScore(@Param("id") Long id);

	@Query("MATCH (n:Employee) return keys(n) limit 1")
	String[] findProperties();

	@Query("MATCH (m:Employee) WHERE ANY(prop in keys(m) where prop={propertyKey} and m[prop] contains {propertyValue}) RETURN m;")
	Iterable<Employee> findByProperty(@Param("propertyKey") String propertyName, @Param("propertyValue") String propertyValue);

}
