package org.jarivm.relationGraph.repositories;

import org.jarivm.relationGraph.domains.Issued;
import org.jarivm.relationGraph.domains.WorkedOn;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Jari Van Melckebeke
 * @since 31.10.16.
 */
@Repository
public interface WorkedOnRepository extends GraphRepository<WorkedOn> {
	@Query("MATCH ()-[n:WorkedOn]-() where id(n)={id} return n")
	WorkedOn findById(@Param(value = "id") Long id);

	@Query("MATCH ()-[n:WorkedOn]-(a:Project) where id(a)={id} return n")
	List<WorkedOn> findByProject(@Param("id") Long id);
}
