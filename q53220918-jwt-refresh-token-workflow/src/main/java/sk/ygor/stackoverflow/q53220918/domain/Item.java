package sk.ygor.stackoverflow.q53220918.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.MathContext;

@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @SuppressWarnings("DefaultAnnotationParam")
    @Column(nullable = false, length = 255)
    private String content;

    @Column(nullable = false)
    private Integer impact;

    @Column(nullable = false)
    private Integer ease;

    @Column(nullable = false)
    private Integer confidence;

    @Column(nullable = false, name = "created_at")
    @JsonProperty("created_at")
    private Long createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;

    public Item() {
    }

    public Item(String content, Integer impact, Integer ease, Integer confidence, Long createdAt, User user) {
        this.content = content;
        this.impact = impact;
        this.ease = ease;
        this.confidence = confidence;
        this.createdAt = createdAt;
        this.user = user;
    }

    @JsonProperty("average_score")
    public BigDecimal getAverageScore() {
        return new BigDecimal(impact + ease + confidence)
                .divide(new BigDecimal(3), MathContext.DECIMAL64);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getImpact() {
        return impact;
    }

    public void setImpact(Integer impact) {
        this.impact = impact;
    }

    public Integer getEase() {
        return ease;
    }

    public void setEase(Integer ease) {
        this.ease = ease;
    }

    public Integer getConfidence() {
        return confidence;
    }

    public void setConfidence(Integer confidence) {
        this.confidence = confidence;
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
                ", content='" + content + '\'' +
                ", impact=" + impact +
                ", ease=" + ease +
                ", confidence=" + confidence +
                ", average_score=" + getAverageScore() +
                ", createdAt=" + createdAt +
                ", user=" + user.getId() +
                '}';
    }
}
