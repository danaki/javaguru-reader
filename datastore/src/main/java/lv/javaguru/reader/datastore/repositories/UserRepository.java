package lv.javaguru.reader.datastore.repositories;

import lv.javaguru.reader.datastore.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Project Javaguru_Reader.
 */
//public class UserRepository {}
public interface UserRepository extends CrudRepository<User, Long>{

    List<User> findByLastName(String lastName);
}
