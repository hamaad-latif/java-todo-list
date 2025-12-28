package userandtask;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class User {
    private final int userId;
    private String name;
    private String email;
    private final List<Task> tasks;

    public User(int userId, String name, String email) {
        validate(name, email);
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.tasks = new ArrayList<>();
    }

    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void validate(String name, String email) {

        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("User name cannot be empty.");
        }

        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("User email cannot be empty.");
        }
    }

    /**
     * Returns an unmodifiable view to protect encapsulation
     */
    public List<Task> getTasks() {
        return Collections.unmodifiableList(tasks);
    }

    // package-private (no modifier)
    public List<Task> getTasksInternal() {
        return tasks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;
        return user.getUserId() == userId;
    }


    @Override
    public int hashCode() {
        return Integer.hashCode(userId);
    }

}