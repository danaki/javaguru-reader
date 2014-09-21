package lv.javaguru.reader.ui.web;

import com.codahale.metrics.annotation.Timed;
import lv.javaguru.reader.common.domain.Feed;
import lv.javaguru.reader.common.repository.FeedRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Feed.
 */
@RestController
@RequestMapping("/app")
public class FeedResource {

    private final Logger log = LoggerFactory.getLogger(FeedResource.class);

    @Inject
    private FeedRepository feedRepository;

    /**
     * POST  /rest/feeds -> Create a new feed.
     */
    @RequestMapping(value = "/rest/feeds",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void create(@RequestBody Feed feed) {
        log.debug("REST request to save Feed : {}", feed);
        feedRepository.save(feed);
    }

    /**
     * GET  /rest/feeds -> get all the feeds.
     */
    @RequestMapping(value = "/rest/feeds",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<Feed> getAll() {
        log.debug("REST request to get all Feeds");
        return feedRepository.findAll();
    }

    /**
     * GET  /rest/feeds/:id -> get the "id" feed.
     */
    @RequestMapping(value = "/rest/feeds/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Feed> get(@PathVariable Long id) {
        log.debug("REST request to get Feed : {}", id);
        return Optional.ofNullable(feedRepository.findOne(id))
            .map(feed -> new ResponseEntity<>(
                feed,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /rest/feeds/:id -> delete the "id" feed.
     */
    @RequestMapping(value = "/rest/feeds/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete Feed : {}", id);
        feedRepository.delete(id);
    }
}
