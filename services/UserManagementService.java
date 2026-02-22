package oop.project.onlineshop.services;

import oop.project.onlineshop.entities.User;

import java.util.List;

public interface UserManagementService {

    String registerUser(User user);

    String authenticateUser(String email, String password);

    List<User> getUsers();

    User getUserByEmail(String userEmail);

    User getUserById(int userId);

}
