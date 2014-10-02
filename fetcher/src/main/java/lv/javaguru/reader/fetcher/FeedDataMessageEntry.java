package lv.javaguru.reader.fetcher;

import java.io.Serializable;

public class FeedDataMessageEntry implements Serializable {
    private String url;
    private String title;

    public FeedDataMessageEntry(String url, String title) {
        this.url = url;
        this.title = title;
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

}
