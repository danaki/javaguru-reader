package lv.javaguru.reader.datastore.repositories;

import lv.javaguru.reader.datastore.domain.Entry;
import org.springframework.data.repository.CrudRepository;

/**
 * Project Javaguru_Reader.
 */
//public class UserRepository {}
public interface EntryRepository extends CrudRepository<Entry, Long>{
}
