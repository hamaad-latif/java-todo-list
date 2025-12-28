package services.taskservice;

import userandtask.Priority;
import userandtask.Status;
import userandtask.Task;
import userandtask.User;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class TaskServiceImpl implements TaskService{

    private final List<Task> tasks;
    private int taskIdCounter = 1;

    public TaskServiceImpl(User user) {
        this.tasks = Objects.requireNonNull(user.getTasksInternal());
    }

    @Override
    public Task addTask(String title, String desctiption, LocalDate dueDate, Priority priority) {
        Task task = new Task(taskIdCounter, title, desctiption, dueDate, priority, Status.PENDING);
        tasks.add(task);
        return task;
    }

    @Override
    public boolean updateTask(int taskId, String title, String description, Priority priority, LocalDate dueDate) {
        Task task = findTaskById(taskId);

        if (task == null) {
            return false;
        }

        task.updateTask(title, description, priority, dueDate);
        return true;
    }

    @Override
    public boolean deleteTask(int taskId) {
        return tasks.removeIf(task -> task.getTaskId() == taskId);
    }

    @Override
    public boolean markTaskCompleted(int taskId) {
        Task task = findTaskById(taskId);
        if (task == null) {
            return false;
        }
        task.markCompleted();
        return true;
    }

    @Override
    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks);
    }

    @Override
    public List<Task> filterByStatus(Status status) {
        return tasks
                .stream()
                .filter(task -> task.getStatus() == status)
                .collect(Collectors.toList());
    }

    @Override
    public List<Task> filterByPriority(Priority priority) {
        return tasks
                .stream()
                .filter(task -> task.getPriority() == priority)
                .collect(Collectors.toList());
    }

    public Task findTaskById(int taskId) {
        for (Task task : tasks) {
            if (task != null && task.getTaskId() == taskId) {
                return task;
            }
        }
        return null;
    }
}
