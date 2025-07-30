package data;

import model.Task;
import model.User;
import model.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DatabaseAccess {
    private static DatabaseAccess instance;

    private final List<User> users = new ArrayList<>();
    private final List<Task> tasks = new ArrayList<>();

    private DatabaseAccess() {
        DatabaseSeeder.seed(this);
    }

    public static DatabaseAccess getInstance() {
        if (instance == null) {
            instance = new DatabaseAccess();
        }
        return instance;
    }

    public List<User> getUsers() {
        return users;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public User findUserById(String id) throws NotFoundException {
        return users.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("User with id " + id + " not found"));
    }

    public Task findTaskById(String id) throws NotFoundException {
        return tasks.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Task with id " + id + " not found"));
    }

    public boolean deleteTaskById(String id) {
        return tasks.removeIf(t -> t.getId().equals(id));
    }

    public boolean deleteUserById(String id) {
        return users.removeIf(u -> u.getId().equals(id));
    }
}
