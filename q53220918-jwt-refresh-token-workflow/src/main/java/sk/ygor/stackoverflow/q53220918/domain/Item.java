package sk.ygor.stackoverflow.q53220918.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @SuppressWarnings("DefaultAnnotationParam")
    @Column(nullable = false, length = 255)
    private String description;

    @Column(nullable = false)
    private Integer count;

    @Column(nullable = false, name = "created_at")
    @JsonProperty("created_at")
    private Long createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;

    public Item() {
    }

    public Item(String description, Integer count, Long createdAt, User user) {
        this.description = description;
        this.count = count;
        this.createdAt = createdAt;
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override public String toString() {
        return "Item{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", count=" + count +
                ", createdAt=" + createdAt +
                ", user=" + user.getId() +
                '}';
    }
}
