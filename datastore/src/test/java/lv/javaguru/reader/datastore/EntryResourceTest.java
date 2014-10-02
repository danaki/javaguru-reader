package lv.javaguru.reader.datastore;

import lv.javaguru.reader.datastore.domain.Entry;
import lv.javaguru.reader.datastore.domain.Feed;
import lv.javaguru.reader.datastore.integration.rest.api.EntryResource;
import lv.javaguru.reader.datastore.repositories.EntryRepository;
import lv.javaguru.reader.datastore.repositories.FeedRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//import org.mockito.MockitoAnnotations;


/**
 * Test class for the EntryResource REST controller.
 *
 * @see EntryResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Datastore.class)
@WebAppConfiguration
//@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
//    DirtiesContextTestExecutionListener.class,
//    TransactionalTestExecutionListener.class })
//@ActiveProfiles("dev")
public class EntryResourceTest {
    @Autowired
    private EntryRepository entryRepository;

    @Autowired
    private FeedRepository feedRepository;

    private Feed feed1;
    private Entry entry1;
    private Entry entry2;

    private MockMvc restEntryMockMvc;

    private Entry entry;

    @Before
    public void setup() {
//        MockitoAnnotations.initMocks(this);
        EntryResource entryResource = new EntryResource();
        ReflectionTestUtils.setField(entryResource, "entryRepository", entryRepository);
        ReflectionTestUtils.setField(entryResource, "feedRepository", feedRepository);

        this.restEntryMockMvc = MockMvcBuilders.standaloneSetup(entryResource).build();

        feed1 = new Feed();
        feed1.setUrl("http://www.example.org/feed");

        entry1 = new Entry();
        entry1.setFeed(feed1);
        entry1.setTitle("First post");
        entry1.setUrl("http://www.example.org/first-post");

        entry2 = new Entry();
        entry2.setFeed(feed1);
        entry2.setTitle("Second article");
        entry2.setUrl("http://www.example.org/second-article");

        entryRepository.deleteAll();
        feedRepository.deleteAll();

        feedRepository.save(feed1);
        entryRepository.save(Arrays.asList(entry1, entry2));

//        Set<Entry> s = new HashSet<Entry>();
//
//        s.add(entry1);
//        s.add(entry2);
//        feed1.setEntries(s);
//        feedRepository.save(feed1);
    }

    @Test
    public void testListFeedEntries() throws Exception {
        restEntryMockMvc.perform(get("/feeds/{id}/entries", feed1.getId())).andDo(print());

        restEntryMockMvc.perform(get("/feeds/{id}/entries", feed1.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[*].id", containsInAnyOrder(entry1.getId(), entry2.getId())));
    }
}
