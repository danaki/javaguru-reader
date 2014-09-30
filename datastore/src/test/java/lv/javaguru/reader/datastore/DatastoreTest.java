package lv.javaguru.reader.datastore;

import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Datastore.class)
@WebAppConfiguration
@IntegrationTest("localhost:8080")
public class DatastoreTest {
//    private static final String SERVER = "http://localhost:8080";
//
//	private ReaderSvcApi restSvc = new RestAdapter.Builder()
//			.setEndpoint(SERVER).build()
//			.create(ReaderSvcApi.class);
//
//    @Autowired
//    private EntryRepository entryRepository;
//
//    @Autowired
//    private FeedRepository feedRepository;
//
//    private Feed feed1;
//    private Entry entry1;
//    private Entry entry2;
//
//    @Before
//    public void setUp() {
//    }
//
//	@Test
//	public void testGetFeeds() throws Exception {
//        feed1 = new Feed();
//        feed1.setUrl("http://www.example.org/feed");
//
//        entry1 = new Entry();
//        entry1.setFeed(feed1);
//        entry1.setTitle("First post");
//        entry1.setUrl("http://www.example.org/first-post");
//
//        entry2 = new Entry();
//        entry2.setFeed(feed1);
//        entry2.setTitle("Second article");
//        entry2.setUrl("http://www.example.org/second-article");
//
//        feedRepository.deleteAll();
//        entryRepository.deleteAll();
//
//        feedRepository.save(feed1);
//        entryRepository.save(Arrays.asList(entry1, entry2));
//
//
//		Collection<Feed> stored = restSvc.getFeedList();
//
////        assertTrue(stored.size() == 0);
//	}
}