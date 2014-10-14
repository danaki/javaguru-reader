package lv.javaguru.reader.datastore.integration.jms;

import lv.javaguru.reader.datastore.domain.Entry;
import lv.javaguru.reader.datastore.domain.Feed;
import lv.javaguru.reader.datastore.repositories.EntryRepository;
import lv.javaguru.reader.datastore.repositories.FeedRepository;
import lv.javaguru.reader.fetcher.FeedDataMessage;
import lv.javaguru.reader.fetcher.FeedDataMessageEntry;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class FetcherOutputListener {

    private static final Logger logger = LoggerFactory.getLogger(FetcherOutputListener.class);

    @Autowired
    private FeedRepository feedRepository;

    @Autowired
    private EntryRepository entryRepository;

    @JmsListener(destination = "fetcherOutput")
    public void processOrder(FeedDataMessage message) {
        logger.info("Received parsed feed message {} {}", message.getUrl(), message.getTitle());

        Feed feed = feedRepository.findByUrl(message.getUrl());
        if (feed == null) {
            feed = new Feed(message.getUrl(), message.getTitle());
        } else {
            feed.setTitle(message.getTitle());
        }

        System.out.println(new LocalDate());

        feed.setEntriesUpdatedAt(new LocalDateTime());

        feedRepository.save(feed);

        for (FeedDataMessageEntry messageEntry: message.getEntries()) {
            Entry entry = entryRepository.findByFeedAndUrl(feed, messageEntry.getUrl());
            if (entry == null) {
                entry = new Entry();
                entry.setFeed(feed);
                entry.setUrl(messageEntry.getUrl());
                entry.setTitle(messageEntry.getTitle());
                entry.setPublishedAt(new LocalDateTime(messageEntry.getPublishedAt()));

                entryRepository.save(entry);

                logger.info("New entry {}", messageEntry.getUrl());
            }
        }

        //System.out.println(feed.getFeed());
    }

}