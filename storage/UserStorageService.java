package oop.project.onlineshop.storage;

import oop.project.onlineshop.entities.User;

import java.util.List;

public interface UserStorageService {
    void saveUser(User user);
    List<User> loadUsers();
}
