package userandtask;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Entity class representing a Task in the To-Do system.
 */
public class Task {

    private final int taskId;
    private String title;
    private String description;
    private Priority priority;
    private Status status;
    private LocalDate dueDate;
    private final LocalDateTime createdAt;

    /* ------------------ Constructor ------------------ */

    public Task(int taskId,
                String title,
                String description,
                LocalDate dueDate,
                Priority priority,
                Status status) {

        validate(title, priority, dueDate);

        this.taskId = taskId;
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.status = Status.PENDING;
        this.dueDate = dueDate;
        this.createdAt = LocalDateTime.now();
    }

    /* ------------------ Getters ------------------ */

    public int getTaskId() {
        return taskId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Priority getPriority() {
        return priority;
    }

    public Status getStatus() {
        return status;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /* ------------------ Business Methods ------------------ */

    public void updateTask(String title,
                           String description,
                           Priority priority,
                           LocalDate dueDate) {

        validate(title, priority, dueDate);

        this.title = title;
        this.description = description;
        this.priority = priority;
        this.dueDate = dueDate;
    }

    public void markCompleted() {
        this.status = Status.COMPLETED;
    }

    public void startTask() {
        this.status = Status.IN_PROGRESS;
    }

    /* ------------------ Equality ------------------ */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;
        Task task = (Task) o;
        return taskId == task.taskId;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(taskId);
    }

    /* ------------------ Validation ------------------ */

    private void validate(String title, Priority priority, LocalDate dueDate) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Task title cannot be empty");
        }
        Objects.requireNonNull(priority, "Priority cannot be null");
        Objects.requireNonNull(dueDate, "Due date cannot be null");
    }
}