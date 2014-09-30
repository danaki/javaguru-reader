package lv.javaguru.reader.datastore;

import lv.javaguru.reader.datastore.domain.Feed;
import lv.javaguru.reader.datastore.integration.rest.api.FeedResource;
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

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//import org.joda.time.LocalDate;
//import org.mockito.MockitoAnnotations;


/**
 * Test class for the FeedResource REST controller.
 *
 * @see FeedResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Datastore.class)
@WebAppConfiguration
//@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
//    DirtiesContextTestExecutionListener.class,
//    TransactionalTestExecutionListener.class })
//@ActiveProfiles("dev")
public class FeedResourceTest {
    
    private static final Long DEFAULT_ID = new Long(1L);

    @Autowired
    private FeedRepository feedRepository;

    private MockMvc restFeedMockMvc;

    private Feed feed;

    @Before
    public void setup() {
//        MockitoAnnotations.initMocks(this);
        FeedResource feedResource = new FeedResource();
        ReflectionTestUtils.setField(feedResource, "feedRepository", feedRepository);

        this.restFeedMockMvc = MockMvcBuilders.standaloneSetup(feedResource).build();

        feed = new Feed();
        feed.setId(DEFAULT_ID);
//        feed.setEntriesUpdatedAt(DEFAULT_SAMPLE_DATE_ATTR);
        feed.setUrl("http://example.org/feed");
        feedRepository.save(feed);
    }

    @Test
    public void testListFeeds() throws Exception {
//        restFeedMockMvc.perform(get("/feeds")).andDo(print());
        restFeedMockMvc.perform(get("/feeds"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    public void testListFeedsContainsDefaultFeed() throws Exception {
        restFeedMockMvc.perform(get("/feeds"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[*].id", containsInAnyOrder(DEFAULT_ID.intValue())));
    }

    @Test
    public void testReadFeed() throws Exception {
        restFeedMockMvc.perform(get("/feeds/{id}", DEFAULT_ID))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(DEFAULT_ID.intValue()))
                .andExpect(jsonPath("$.url").value("http://example.org/feed"));
    }

    @Test
    public void testReadNonexistingFeed() throws Exception {
        restFeedMockMvc.perform(get("/feeds/{id}", DEFAULT_ID + 1)
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isNotFound());
    }
}
