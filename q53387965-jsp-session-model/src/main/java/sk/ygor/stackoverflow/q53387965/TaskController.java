package sk.ygor.stackoverflow.q53387965;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.Date;
import java.util.List;

@Controller
@SessionAttributes("taskList")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @GetMapping("/task-view")
    public String showTaskList() {
        return "task/view";
    }

    @GetMapping("/task-add")
    public String showAddTaskForm(@ModelAttribute("task") Task newTask) {
        return "task/add";
    }

    @PostMapping("/task-add")
    public String addTask(ModelMap model, @ModelAttribute("task") Task newTask, @ModelAttribute("taskList") List<Task> taskList) {
        System.out.println("ADDING NEW TASK: " + taskList.size());
        taskList.add(taskService.addTask(newTask.getTitle(), newTask.getDesc(), new Date()));
        System.out.println("DONE ADDING NEW TASK: " + taskList.size());
        return "redirect:/task-view";
    }

    @ModelAttribute("taskList")
    public List<Task> taskList() {
        System.out.println("CALLING TASK SERVICE TO GET LIST OF TASKS");
        return taskService.retrieveTasks();
    }
}
