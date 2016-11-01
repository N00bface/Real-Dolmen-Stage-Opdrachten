package org.jarivm.relationGraph.repositories;

import org.jarivm.relationGraph.domains.WorkedOn;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Jari Van Melckebeke
 * @since 31.10.16.
 */
@Repository
public interface WorkedOnRepository extends GraphRepository<WorkedOn> {
}
