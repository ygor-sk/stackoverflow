package sk.ygor.stackoverflow.q53207105;

import javax.persistence.*;

@Entity
public class Audi {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String description;

    protected Audi() {
    }

    public Audi(String description) {
        this.description = description;
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

    @Override public String toString() {
        return "Audi{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }

}
