package lv.javaguru.reader.datastore.integration.rest.api;

//import com.codahale.metrics.annotation.Timed;

import lv.javaguru.reader.datastore.domain.Entry;
import lv.javaguru.reader.datastore.domain.Feed;
import lv.javaguru.reader.datastore.repositories.EntryRepository;
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Entry.
 */
@RestController
public class EntryResource {

    private final Logger log = LoggerFactory.getLogger(EntryResource.class);

    @Autowired
    private FeedRepository feedRepository;

    @Autowired
    private EntryRepository entryRepository;

    @RequestMapping(value = "/feeds/{id}/entries",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
//    @Timed
    public ResponseEntity<List<Entry>> getFeedEntries(@PathVariable Long id) {
        Feed feed = feedRepository.findOne(id);

        if (feed == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        log.debug("REST request to get feed entries");
        List<Entry> list = new ArrayList<Entry>(feed.getEntries());
        return Optional.ofNullable(list)
            .map(feeds -> new ResponseEntity<>(
                feeds,
                HttpStatus.OK))
            .orElse(new ResponseEntity<List<Entry>>(Collections.<Entry>emptyList(), HttpStatus.OK));
    }

//    /**
//     * GET  /rest/entrys/:id -> get the "id" entry.
//     */
//    @RequestMapping(value = "/entries",
//            method = RequestMethod.GET,
//            produces = MediaType.APPLICATION_JSON_VALUE)
////    @Timed
//    public ResponseEntity<Entry> get(@PathVariable Long id) {
//        log.debug("REST request to get Entry : {}", id);
//        return Optional.ofNullable(entryRepository.findOne(id))
//            .map(entry -> new ResponseEntity<>(
//                entry,
//                HttpStatus.OK))
//            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
//    }
}
