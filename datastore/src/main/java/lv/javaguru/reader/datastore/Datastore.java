package lv.javaguru.reader.datastore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;

/**
 * Project Javaguru_Reader.
 */

@Configuration
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = "lv.javaguru.reader.datastore.repositories")
@Import(RepositoryRestMvcConfiguration.class)
public class Datastore {

    public static void main(String[] args) {

        SpringApplication.run(Datastore.class, args);

    }
}
