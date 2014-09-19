package lv.javaguru.reader.repository;

import lv.javaguru.reader.domain.Feed;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the Feed entity.
 */
public interface FeedRepository extends JpaRepository<Feed, Long> {

}
