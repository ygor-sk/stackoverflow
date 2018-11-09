package sk.ygor.stackoverflow.q53224496.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Setter
@Getter
@Entity
@Table(name = "todo_block")
public class ToDoBlock {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String name;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "archive_id")
//    private Archive archive;

    @Setter(AccessLevel.PRIVATE)
    @OneToMany(mappedBy = "toDoBlock")
    private List<ToDo> toDos = new ArrayList<>();

    public void addToDo(ToDo toDo) {
        toDos.add(toDo);
        toDo.setToDoBlock(this);
    }

    public void removeToDo(ToDo toDo) {
        toDos.remove(toDo);
        toDo.setToDoBlock(null);
    }

}
