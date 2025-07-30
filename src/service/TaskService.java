package service;

import model.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TaskService {
    private final List<Task> tasks = new ArrayList<>();

    public void addTask(Task task) {
        tasks.add(task);
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public boolean deleteTask(String id) {
        return tasks.removeIf(t -> t.getId().equals(id));
    }

    public Optional<Task> findById(String id) {
        return tasks.stream().filter(t -> t.getId().equals(id)).findFirst();
    }
}
