package sk.ygor.stackoverflow.q53387965;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TaskService {
    private List<Task> taskCache = new ArrayList<>();

    public List<Task> retrieveTasks() {
        System.out.println("GETTING TASK LIST FOR THE FIRST TIME");
        return new ArrayList<>(taskCache);
    }

    public Task addTask(String title, String desc, Date targetDate) {
        Task newTask = new Task(title, desc, targetDate);
        taskCache.add(newTask);

        return newTask;
    }
}
