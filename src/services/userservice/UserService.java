package services.userservice;

import userandtask.User;

import java.util.List;

public interface UserService {
    public User createUser(String name, String email);
    public List<User> getAllUsers();
    public User getUserById(int userId);
}