package lv.javaguru.reader.ui.repository;

import lv.javaguru.reader.ui.domain.Feed;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the Feed entity.
 */
public interface FeedRepository extends JpaRepository<Feed, Long> {

}
