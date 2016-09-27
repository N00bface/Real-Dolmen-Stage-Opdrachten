package org.jarivm.relationGraph.objects.repositories;

import org.jarivm.relationGraph.objects.domains.Client;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Jari Van Melckebeke
 * @since 23.09.16
 */
public interface ClientRepository extends CrudRepository<Client, Long> {

}
