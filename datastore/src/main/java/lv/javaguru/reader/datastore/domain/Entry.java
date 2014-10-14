package lv.javaguru.reader.datastore.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.joda.deser.LocalDateTimeDeserializer;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import lv.javaguru.reader.datastore.domain.util.CustomLocalDateTimeSerializer;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDateTime;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
//@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
@JsonIdentityInfo(generator=JSOGGenerator.class)
public class Entry implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "url", length = 255, nullable = false)
    private String url;

    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "title", length = 255, nullable = false)
    private String title;

    @NotNull
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    @Column(name = "created_at", nullable = false)
    private LocalDateTime publishedAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "feed_id", nullable = false)
//    @JsonBackReference
    private Feed feed;

    public Entry() {}

    public Entry(Feed feed, String url, String title) {
        this.feed = feed;
        this.url = url;
        this.title = title;
    }

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

    public LocalDateTime getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(LocalDateTime publishedAt) {
        this.publishedAt = publishedAt;
    }

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
//                ", publishedAt=" + publishedAt +
                '}';
    }
}
