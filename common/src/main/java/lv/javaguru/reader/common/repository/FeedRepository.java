package lv.javaguru.reader.common.repository;

import lv.javaguru.reader.common.domain.Feed;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the Feed entity.
 */
public interface FeedRepository extends JpaRepository<Feed, Long> {

}
