package sk.ygor.stackoverflow.q53224496.services;

import org.springframework.stereotype.Component;
import sk.ygor.stackoverflow.q53224496.domain.ToDo;
import sk.ygor.stackoverflow.q53224496.domain.ToDoBlock;

import javax.persistence.EntityManager;
import java.util.List;

@Component
public class TodoService {

    private final EntityManager entityManager;

    public TodoService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void saveToDoBlock(ToDoBlock toDoBlock) {
        entityManager.persist(toDoBlock);
    }

    public void saveSimpleToDoByBlockId(String text, long blockId) {
        ToDoBlock block = entityManager.getReference(ToDoBlock.class, blockId);
        ToDo toDo = new ToDo(text, block);
        block.addToDo(toDo);
        entityManager.persist(toDo);
        entityManager.persist(block);
    }

    public List<ToDo> findAllTodos() {
        return entityManager.createQuery("select todo from ToDo todo", ToDo.class).getResultList();
    }

    public List<ToDoBlock> findAllBlocks() {
        return entityManager.createQuery("select todo from ToDoBlock todo", ToDoBlock.class).getResultList();
    }
}
