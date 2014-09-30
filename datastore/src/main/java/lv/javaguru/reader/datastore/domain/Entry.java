package lv.javaguru.reader.datastore.domain;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

//import com.fasterxml.jackson.datatype.joda.deser.LocalDateDeserializer;
//import lv.javaguru.reader.domain.util.CustomLocalDateSerializer;
//import org.hibernate.annotations.Cache;
//import org.hibernate.annotations.CacheConcurrencyStrategy;
//import org.joda.time.LocalDate;

/**
 * A Entry.
 */
@Entity
//@Table(name = "T_ENTRY")
//@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Entry implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @Size(min = 1, max = 255)
    @Column(name = "url", length = 255)
    private String url;

    @Size(min = 1, max = 255)
    @Column(name = "title", length = 255)
    private String title;

//    @NotNull
//    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
//    @JsonDeserialize(using = LocalDateDeserializer.class)
//    @JsonSerialize(using = CustomLocalDateSerializer.class)
//    @Column(name = "created_at", nullable = false)
//    private LocalDate createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "feed_id", nullable = false)
    private Feed feed;

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

//    public LocalDate getCreatedAt() {
//        return createdAt;
//    }

//    public void setCreatedAt(LocalDate createdAt) {
//        this.createdAt = createdAt;
//    }

    public Feed getFeed() { return feed; }

    public void setFeed(Feed feed) { this.feed = feed; }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Entry entry = (Entry) o;

        if (id != entry.id) {
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
        return "Entry{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", title='" + title + '\'' +
//                ", createdAt=" + createdAt +
                '}';
    }
}
