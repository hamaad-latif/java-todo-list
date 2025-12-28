package services.userservice;

import userandtask.User;

import java.util.*;

public class UserServiceImpl implements UserService{
    Map<Integer, User> users = new HashMap<>();
    int userIdCounter = 1;
    @Override
    public User createUser(String name, String email) {
        validateUserInput(name, email);
        User user = new User(userIdCounter, name, email);
        userIdCounter += 1;
        users.put(userIdCounter, user);
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        return new ArrayList<>(users.values());
    }

    @Override
    public User getUserById(int userId) {
        return users.get(userId);
    }

    private void validateUserInput(String name, String email) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("User name cannot be empty");
        }
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be empty");
        }
    }
}

