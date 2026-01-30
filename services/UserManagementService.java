package oop.project.onlineshop.services;

import oop.project.onlineshop.entities.User;

public interface UserManagementService {

    String registerUser(User user);

    String authenticateUser(String email, String password);

    User[] getUsers();

    User getUserByEmail(String userEmail);

    User getUserById(int userId);

}
