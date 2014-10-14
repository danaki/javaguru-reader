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
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.web.bind.annotation.*;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;
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
    JmsTemplate jmsTemplate;

    @Autowired
    private FeedRepository feedRepository;

    @RequestMapping(value = "/feeds",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
//    @Timed
    public void create(@RequestBody Feed feed) {
        log.debug("REST request to save Feed : {}", feed);
        feed.setTitle("[UPDATE IN PROGRESS]");
        feedRepository.save(feed);
    }

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

    @RequestMapping(value = "/feeds/{id}",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
//    @Timed
    public ResponseEntity<Feed> refresh(@PathVariable Long id) {
        log.debug("REST request to refresh Feed : {}", id);
        Feed feed = feedRepository.findOne(id);
        if (feed == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        MessageCreator messageCreator = new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                ObjectMessage msg = session.createObjectMessage();
                msg.setObject(feed.getUrl());
                return msg;
            }
        };

        jmsTemplate.send("fetcherInput", messageCreator);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/feeds/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
//    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete Feed : {}", id);
        feedRepository.delete(id);
    }
}
