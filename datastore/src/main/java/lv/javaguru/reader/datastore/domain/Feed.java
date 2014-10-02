package lv.javaguru.reader.datastore.domain;

//import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
//import com.fasterxml.jackson.databind.annotation.JsonSerialize;
//import com.fasterxml.jackson.datatype.joda.deser.LocalDateDeserializer;
//import lv.javaguru.reader.domain.util.CustomLocalDateSerializer;
//import org.hibernate.annotations.Cache;
//import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

//import org.joda.time.LocalDate;

/**
 * A Feed.
 */
@Entity
//@Table(name = "T_FEED")
//@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Feed implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @Size(min = 1, max = 255)
    @Column(name = "url", length = 255)
    private String url;

    @Size(min = 1, max = 255)
    @Column(name = "title", length = 255)
    private String title;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "feed_id")
    private Set<Entry> entries = new HashSet<Entry>();

    public Feed() {}

    public Feed(String url, String title) {
        this.url = url;
        this.title = title;
    }

//    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
//    @JsonDeserialize(using = LocalDateDeserializer.class)
//    @JsonSerialize(using = CustomLocalDateSerializer.class)
//    @Column(name = "entries_updated_at", nullable = false)
//    private LocalDate entriesUpdatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Set<Entry> getEntries() {
        return entries;
    }

    public void setEntries(Set<Entry> entries) {
        this.entries = entries;
    }

//    public LocalDate getEntriesUpdatedAt() {
//        return entriesUpdatedAt;
//    }
//
//    public void setEntriesUpdatedAt(LocalDate entriesUpdatedAt) {
//        this.entriesUpdatedAt = entriesUpdatedAt;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Feed feed = (Feed) o;

        if (id != feed.id) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {
        return "Feed{" +
                "id=" + id +
                ", url='" + url + '\'' +
//                ", entriesUpdatedAt=" + entriesUpdatedAt +
                '}';
    }
}
