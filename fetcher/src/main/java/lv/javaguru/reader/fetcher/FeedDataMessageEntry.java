package lv.javaguru.reader.fetcher;

import java.io.Serializable;
import java.util.Date;

public class FeedDataMessageEntry implements Serializable {
    private String url;
    private String title;
    private Date publishedAt;

    public FeedDataMessageEntry(String url, String title, Date publishedAt) {
        this.url = url;
        this.title = title;
        this.publishedAt = publishedAt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(Date publishedAt) {
        this.publishedAt = publishedAt;
    }
}
