package oop.project.onlineshop.services.impl;

import oop.project.onlineshop.configs.ApplicationContext;
import oop.project.onlineshop.entities.User;
import oop.project.onlineshop.entities.impl.DefaultUser;
import oop.project.onlineshop.services.UserManagementService;

import java.util.Objects;

public class DefaultUserManagementService implements UserManagementService {

    private static final String NOT_UNIQUE_EMAIL_ERROR_MESSAGE = "This email is already used by another user. Please, use another email";
    private static final String EMPTY_EMAIL_ERROR_MESSAGE = "You have to input email to register. Please, try one more time";
    private static final String NO_ERROR_MESSAGE = "";
    private static final String USER_REGISTER_SUCCESS_MESSAGE = "New user has been created";

    private static final int DEFAULT_USERS_CAPACITY = 10;

    private static DefaultUserManagementService instance;

    private User[] users;
    private int lastUserIndex;

    {
        users = new User[DEFAULT_USERS_CAPACITY];
        lastUserIndex = 0;
    }

    private DefaultUserManagementService() {
    }

    @Override
    public String registerUser(User user) {
        ApplicationContext.getInstance().setLoggedInUser(user);
        users[lastUserIndex++] = user;
        return USER_REGISTER_SUCCESS_MESSAGE;
    }

    public static UserManagementService getInstance() {
        if (instance == null) {
            instance = new DefaultUserManagementService();
        }
        return instance;
    }


    @Override
    public User[] getUsers() {
        return users;
    }

    @Override
    public User getUserByEmail(String userEmail) {
        for (User user : users) {
            if (Objects.equals(user.getEmail(), userEmail)) return user;
        }
        return null;
    }

    void clearServiceState() {
        users = new User[0];
        lastUserIndex = 0;
    }
}
