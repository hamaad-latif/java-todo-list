package services.taskservice;

import userandtask.Priority;
import userandtask.Status;
import userandtask.Task;

import java.time.LocalDate;
import java.util.List;

public interface TaskService {

    public Task addTask(String title,
                        String description,
                        LocalDate dueDate,
                        Priority priority);

    public boolean updateTask(int taskId,
                       String title,
                       String description,
                       Priority priority,
                       LocalDate dueDate);

    public boolean deleteTask(int taskId);

    public boolean markTaskCompleted(int taskId);

    public List<Task> getAllTasks();

    public List<Task> filterByStatus(Status status);

    public List<Task> filterByPriority(Priority priority);
}
