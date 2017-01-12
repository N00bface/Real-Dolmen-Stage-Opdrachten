/*
 * Copyright (c) 2017. MIT-license for Jari Van Melckebeke
 * Note that there was a lot of educational work in this project,
 * this project was (or is) used for an assignment from Realdolmen in Belgium.
 * Please just don't abuse my work
 */

package org.jarivm.relationGraph.repositories;

import org.jarivm.relationGraph.domains.Issued;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;

/**
 * @author Jari Van Melckebeke
 * @since 11.11.16.
 */
public interface IssuedRepository extends GraphRepository<Issued> {
	@Query("MATCH ()-[n:Issued]-() where id(n)={id} return n")
	Issued findById(@Param(value = "id") Long id);
}
