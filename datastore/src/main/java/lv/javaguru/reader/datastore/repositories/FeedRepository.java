package lv.javaguru.reader.datastore.repositories;

import lv.javaguru.reader.datastore.domain.Feed;
import org.springframework.data.repository.CrudRepository;

/**
 * Project Javaguru_Reader.
 */
//public class UserRepository {}
public interface FeedRepository extends CrudRepository<Feed, Long>{
}
