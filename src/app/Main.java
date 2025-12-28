package app;

import services.taskservice.TaskService;
import services.taskservice.TaskServiceImpl;
import services.userservice.UserService;
import services.userservice.UserServiceImpl;
import userandtask.Priority;
import userandtask.Task;
import userandtask.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        UserService userService = new UserServiceImpl();

        User activeUser = null;
        TaskService taskService = null;

        while (true) {
            System.out.println("\n==== TODO APPLICATION ====");
            System.out.println("1. Create User");
            System.out.println("2. Select User");
            System.out.println("3. Add Task");
            System.out.println("4. View Tasks");
            System.out.println("5. Mark Task Completed");
            System.out.println("6. Delete Task");
            System.out.println("0. Exit");
            System.out.print("Choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {

                case 1:
                    System.out.print("Name: ");
                    String name = scanner.nextLine();

                    System.out.print("Email: ");
                    String email = scanner.nextLine();

                    activeUser = userService.createUser(name, email);
                    taskService = new TaskServiceImpl(activeUser);
                    System.out.println("User created with ID: " + activeUser.getUserId());
                    break;

                case 2:
                    System.out.print("Enter User ID: ");
                    int userId = scanner.nextInt();
                    scanner.nextLine();

                    activeUser = userService.getUserById(userId);
                    if (activeUser == null) {
                        System.out.println("User not found");
                    } else {
                        taskService = new TaskServiceImpl(activeUser);
                        System.out.println("User selected: " + activeUser.getName());
                    }
                    break;

                case 3:
                    ensureUserSelected(activeUser);

                    System.out.print("Title: ");
                    String title = scanner.nextLine();

                    System.out.print("Description: ");
                    String desc = scanner.nextLine();

                    System.out.print("Priority (LOW/MEDIUM/HIGH): ");
                    Priority priority = Priority.valueOf(scanner.nextLine().toUpperCase());

                    System.out.print("Due date (yyyy-mm-dd): ");
                    LocalDate dueDate = LocalDate.parse(scanner.nextLine());

                    taskService.addTask(title, desc, dueDate, priority);
                    System.out.println("Task added");
                    break;

                case 4:
                    ensureUserSelected(activeUser);

                    List<Task> tasks = taskService.getAllTasks();
                    if (tasks.isEmpty()) {
                        System.out.println("No tasks found");
                    } else {
                        tasks.forEach(task ->
                                System.out.println(
                                        task.getTaskId() + " | " +
                                                task.getTitle() + " | " +
                                                task.getStatus() + " | " +
                                                task.getPriority()
                                )
                        );
                    }
                    break;

                case 5:
                    ensureUserSelected(activeUser);

                    System.out.print("Enter Task ID: ");
                    int taskId = scanner.nextInt();
                    scanner.nextLine();

                    if (taskService.markTaskCompleted(taskId)) {
                        System.out.println("Task marked as completed");
                    } else {
                        System.out.println("Task not found");
                    }
                    break;

                case 6:
                    ensureUserSelected(activeUser);

                    System.out.print("Enter Task ID: ");
                    int deleteId = scanner.nextInt();
                    scanner.nextLine();

                    if (taskService.deleteTask(deleteId)) {
                        System.out.println("Task deleted");
                    } else {
                        System.out.println("Task not found");
                    }
                    break;

                case 0:
                    System.out.println("Exiting application...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    /* ------------------ Helper ------------------ */

    private static void ensureUserSelected(User user) {
        if (user == null) {
            throw new IllegalStateException("Please select or create a user first");
        }
    }
}
