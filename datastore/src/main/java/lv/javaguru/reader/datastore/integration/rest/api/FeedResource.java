package lv.javaguru.reader.datastore.integration.rest.api;

//import com.codahale.metrics.annotation.Timed;

import lv.javaguru.reader.datastore.domain.Feed;
import lv.javaguru.reader.datastore.repositories.FeedRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Feed.
 */
@RestController
public class FeedResource {

    private final Logger log = LoggerFactory.getLogger(FeedResource.class);

    @Autowired
    private FeedRepository feedRepository;

    /**
     * GET  /rest/feeds -> get all the feeds.
     */
    @RequestMapping(value = "/feeds",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
//    @Timed
    public ResponseEntity<List<Feed>> getAll() {
        log.debug("REST request to get all Feeds");
        return Optional.ofNullable((List<Feed>) feedRepository.findAll())
            .map(feeds -> new ResponseEntity<>(
                feeds,
                HttpStatus.OK))
            .orElse(new ResponseEntity<List<Feed>>(Collections.<Feed>emptyList(), HttpStatus.OK));
    }

    /**
     * GET  /rest/feeds/:id -> get the "id" feed.
     */
    @RequestMapping(value = "/feeds/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
//    @Timed
    public ResponseEntity<Feed> get(@PathVariable Long id) {
        log.debug("REST request to get Feed : {}", id);
        return Optional.ofNullable(feedRepository.findOne(id))
            .map(feed -> new ResponseEntity<>(
                feed,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
