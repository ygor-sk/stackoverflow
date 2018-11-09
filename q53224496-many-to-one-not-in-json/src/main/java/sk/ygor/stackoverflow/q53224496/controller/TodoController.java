package sk.ygor.stackoverflow.q53224496.controller;

import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import sk.ygor.stackoverflow.q53224496.domain.ToDo;
import sk.ygor.stackoverflow.q53224496.domain.ToDoBlock;
import sk.ygor.stackoverflow.q53224496.services.TodoService;

import java.util.List;

@RestController
public class TodoController {

    private final TodoService toDoService;

    public TodoController(TodoService toDoService) {
        this.toDoService = toDoService;
    }

    @PostMapping(value = "/create-todo-block")
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public void createToDoBlock(@RequestBody ToDoBlock toDoBlock){
        toDoService.saveToDoBlock(toDoBlock);
    }

    @PostMapping(value = "/create-simple-todo/{blockId}")
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public void createToDo(@PathVariable long blockId, @RequestBody String text){
        toDoService.saveSimpleToDoByBlockId(text, blockId);
    }

    @GetMapping(value = "/find-all-todos")
    @ResponseStatus(HttpStatus.OK)
    public List<ToDo> findAllTodos(){
        return toDoService.findAllTodos();
    }

    @GetMapping(value = "/find-all-blocks")
    @ResponseStatus(HttpStatus.OK)
    public List<ToDoBlock> findAllBlocks(){
        return toDoService.findAllBlocks();
    }

}

