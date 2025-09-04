package oop.project.onlineshop.services;

import oop.project.onlineshop.entities.User;

public interface UserManagementService {

    String registerUser(User user);

    User[] getUsers();

    User getUserByEmail(String userEmail);

}
