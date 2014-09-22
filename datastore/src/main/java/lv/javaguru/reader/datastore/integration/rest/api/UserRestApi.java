package lv.javaguru.reader.datastore.integration.rest.api;

import lv.javaguru.reader.datastore.domain.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Project Javaguru_Reader.
 */

@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface UserRestApi extends PagingAndSortingRepository <User, Long> {

    List<User> findByLastName(@Param("name") String lastName);

}
