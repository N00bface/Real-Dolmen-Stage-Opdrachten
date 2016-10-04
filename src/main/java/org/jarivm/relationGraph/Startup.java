package org.jarivm.relationGraph;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Jari Van Melckebeke
 * @since 02.10.16
 */
@SpringBootApplication
@EnableNeo4jRepositories
@EnableTransactionManagement
@Import(Application.class)
@EntityScan(basePackages = "org.jarivm.relationGraph")
public class Startup {

    public static void main(String[] args) {
        SpringApplication.run(Startup.class, args);
    }
}
