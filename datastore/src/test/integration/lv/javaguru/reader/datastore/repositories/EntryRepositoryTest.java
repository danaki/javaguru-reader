package lv.javaguru.reader.datastore.repositories;

import lv.javaguru.reader.datastore.Datastore;
import lv.javaguru.reader.datastore.domain.Entry;
import lv.javaguru.reader.datastore.domain.Feed;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(classes = Datastore.class)

public class EntryRepositoryTest {

    @Autowired FeedRepository feedRepository;
    @Autowired EntryRepository entryRepository;

    final private String SHARED_URL = "http://example.org/article1";

    final private Feed feed1 = new Feed("http://example.org/feed", "Example.org feed");
    final private Feed feed2 = new Feed("http://primer.com/feed", "Primer.com feed");

    final private Entry entry1 = new Entry(feed1, SHARED_URL, "Example.org article in Example.org feed");
    final private Entry entry2 = new Entry(feed2, "http://primer.com/post1", "Primer.com article in Primer.com feed");
    final private Entry entry3 = new Entry(feed2, SHARED_URL, "Example.org article in Primer.com feed");

    final private List<Feed> feeds = new ArrayList<>(Arrays.asList(feed1, feed2));
    final private List<Entry> entries = new ArrayList<>(Arrays.asList(entry1, entry2, entry3));

    @Before
    public void setUp() throws Exception {
        feedRepository.deleteAll();
        entryRepository.deleteAll();

        feedRepository.save(feeds);
        entryRepository.save(entries);
    }

    @Test
    public void entryEntityCreation() throws Exception {
        final List<Entry> allEntries = (List<Entry>) entryRepository.findAll();
        assertTrue(allEntries.containsAll(entries));
    }

    @Test
    public void searchForEntryByFeedAndUrl() throws Exception {
        assertEquals(entry1, entryRepository.findByFeedAndUrl(feed1, SHARED_URL));
//        assertEquals(1, entryRepository.findByFeedAndUrl(feed1, SHARED_URL).size());
    }

//    @Test
//    public void deleteUser() {
//        repository.delete(user3);
//        final List<User> repositoryAll = (List<User>) repository.findAll();
//        assertFalse(repositoryAll.containsAll(users));
//    }
}