/*
 * Copyright (c) 2016. LICENCED BY (c) Jari Van Melckebeke, all rights reserved, use only with permission of Jari Van Melckebeke
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
