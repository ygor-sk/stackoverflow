package sk.ygor.stackoverflow.q123456789;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class MyEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String content;

    public MyEntity() {
    }

    public MyEntity(String content) {
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override public String toString() {
        return "MyEntity{" +
                "id=" + id +
                ", content='" + content + '\'' +
                '}';
    }
}
