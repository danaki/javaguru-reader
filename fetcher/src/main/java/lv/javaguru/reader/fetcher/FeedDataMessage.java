package lv.javaguru.reader.fetcher;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FeedDataMessage implements Serializable {

    private String url;
    private String title;

    private List<FeedDataMessageEntry> entries = new ArrayList<FeedDataMessageEntry>();

    public FeedDataMessage(String url, SyndFeed feed) {
        this.url = url;
        this.title = feed.getTitle();

        Iterator iter = feed.getEntries().iterator();

        while (iter.hasNext()) {
            SyndEntry entry = (SyndEntry) iter.next();
            entries.add(new FeedDataMessageEntry(entry.getUri(), entry.getTitle(), entry.getPublishedDate()));
        }
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<FeedDataMessageEntry> getEntries() {
        return entries;
    }

    public void setEntries(List<FeedDataMessageEntry> entries) {
        this.entries = entries;
    }
}
