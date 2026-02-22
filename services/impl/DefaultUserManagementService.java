package oop.project.onlineshop.services.impl;

import oop.project.onlineshop.configs.ApplicationContext;
import oop.project.onlineshop.entities.User;
import oop.project.onlineshop.entities.impl.DefaultUser;
import oop.project.onlineshop.services.UserManagementService;
import oop.project.onlineshop.storage.UserStorageService;
import oop.project.onlineshop.storage.impl.DefaultUserStorageService;

import java.util.List;
import java.util.Objects;

public class DefaultUserManagementService implements UserManagementService {
    private static final String USER_CANNOT_BE_NULL_MESSAGE = "The User provided cannot be null";
    private static final String NOT_UNIQUE_EMAIL_ERROR_MESSAGE = "This email is already used by another user. Please, use another email";
    private static final String EMPTY_EMAIL_ERROR_MESSAGE = "You have to input email to register. Please, try one more time";
    private static final String INVALID_CREDENTIALS_MESSAGE = "Unfortunately, such login and password doesn’t exist";
    private static final String USER_REGISTER_SUCCESS_MESSAGE = "New user has been created";

    private static UserManagementService instance;
    private final UserStorageService userStorageService;


    private DefaultUserManagementService() {
        userStorageService = DefaultUserStorageService.getInstance();
    }

    @Override
    public String registerUser(User user) {
        if (user == null) return USER_CANNOT_BE_NULL_MESSAGE;
        if (getUserByEmail(user.getEmail()) != null) return NOT_UNIQUE_EMAIL_ERROR_MESSAGE;
        if (user.getEmail().isEmpty()) return EMPTY_EMAIL_ERROR_MESSAGE;
        ApplicationContext.getInstance().setLoggedInUser(user);
        userStorageService.saveUser(user);
        return USER_REGISTER_SUCCESS_MESSAGE;
    }

    @Override
    public String authenticateUser(String email, String password) {
        if (getUserByEmail(email) == null) return INVALID_CREDENTIALS_MESSAGE;
        User user = getUserByEmail(email);
        if (!Objects.equals(user.getPassword(), password)) return INVALID_CREDENTIALS_MESSAGE;
        ApplicationContext.getInstance().setLoggedInUser(user);
        return "Glad to see you back " + user.getFirstName() + " " + user.getLastName();
    }

    public static UserManagementService getInstance() {
        if (instance == null) {
            instance = new DefaultUserManagementService();
        }
        return instance;
    }


    @Override
    public List<User> getUsers() {
        List<User> nonNullUsers = userStorageService.loadUsers();
        DefaultUser.setCounter(nonNullUsers.size());

        return nonNullUsers;
    }

    @Override
    public User getUserByEmail(String userEmail) {
        for (User user : getUsers()) {
            if (Objects.equals(user.getEmail(), userEmail)) return user;
        }
        return null;
    }

    @Override
    public User getUserById(int id) {
        for (User user : getUsers()) {
            if (Objects.equals(user.getId(), id)) return user;
        }
        return null;
    }
}
