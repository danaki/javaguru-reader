package lv.javaguru.reader.datastore;

import lv.javaguru.reader.datastore.domain.User;
import lv.javaguru.reader.datastore.repositories.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;

/**
 * Project Javaguru_Reader.
 */

@Configuration
@EnableAutoConfiguration
@EnableJpaRepositories
@Import(RepositoryRestMvcConfiguration.class)
public class Datastore {

    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(Datastore.class, args);
        UserRepository repository = context.getBean(UserRepository.class);


        // save a couple of customers
        repository.save(new User("Jack", "Bauer"));
        repository.save(new User("Chloe", "O'Brian"));
        repository.save(new User("Kim", "Bauer"));
        repository.save(new User("David", "Palmer"));
        repository.save(new User("Michelle", "Dessler"));

//        // fetch all customers
//        Iterable<User> customers = repository.findAll();
//        System.out.println("Customers found with findAll():");
//        System.out.println("-------------------------------");
//        for (User customer : customers) {
//            System.out.println(customer);
//        }
//        System.out.println();
//
//        // fetch an individual customer by ID
//        User customer = repository.findOne(1L);
//        System.out.println("Customer found with findOne(1L):");
//        System.out.println("--------------------------------");
//        System.out.println(customer);
//        System.out.println();
//
//        // fetch customers by last name
//        List<User> bauers = repository.findByLastName("Bauer");
//        System.out.println("Customer found with findByLastName('Bauer'):");
//        System.out.println("--------------------------------------------");
//        for (User bauer : bauers) {
//            System.out.println(bauer);
//        }
//
//
//        context.close();
    }
}
