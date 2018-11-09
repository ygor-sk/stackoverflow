package sk.ygor.stackoverflow.q53224496.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "todo")
public class ToDo {

    @Id
    @GeneratedValue
    private Long id;

    @Column (nullable = false)
    private String text;

    @Column
    private String scaryness;

    @Column
    private String hardness;

    @Column
    private boolean ready;

    @Column
    private LocalDateTime createdOn;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "todo_block_id")
    private ToDoBlock toDoBlock;

    public ToDo(String text, ToDoBlock todoBlock) {
        this.text = text;
        this.toDoBlock = todoBlock;
        this.ready = false;
        this.createdOn = LocalDateTime.now();
    }

}