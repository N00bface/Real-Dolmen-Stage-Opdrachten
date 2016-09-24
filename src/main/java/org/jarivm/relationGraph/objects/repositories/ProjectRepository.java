package org.jarivm.relationGraph.objects.repositories;

import org.jarivm.relationGraph.objects.domains.Project;
import org.springframework.data.neo4j.repository.GraphRepository;

/**
 * @author Jari Van Melckebeke
 * @since 23.09.16
 */
public interface ProjectRepository extends GraphRepository<Project> {
}
