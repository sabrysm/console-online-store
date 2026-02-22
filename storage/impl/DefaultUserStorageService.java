package oop.project.onlineshop.storage.impl;

import oop.project.onlineshop.entities.User;
import oop.project.onlineshop.entities.impl.DefaultUser;
import oop.project.onlineshop.storage.UserStorageService;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


public class DefaultUserStorageService implements UserStorageService {
    private static final String RESOURCES_FOLDER = "resources";
    private static final String USERS_INFO = "users.csv";
    private static final int ID_INDEX = 0;
    private static final int FIRST_NAME_INDEX = 1;
    private static final int LAST_NAME_INDEX = 2;
    private static final int EMAIL_INDEX = 3;
    private static final int PASSWORD_INDEX = 4;
    private static final String DELIMITER = ",";

    private static UserStorageService instance;


    @Override
    public void saveUser(User user) {
        List<Object> seqChars = new ArrayList<>(List.of(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword()));
        try {
            Files.writeString(
                    Path.of(RESOURCES_FOLDER,USERS_INFO),
                    seqChars.stream().map(String::valueOf).collect(Collectors.joining(DELIMITER))+"\n",
                    StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND
                    );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> loadUsers() {
        try (var loadedUsersStream = Files.lines(Paths.get(RESOURCES_FOLDER, USERS_INFO))) {
            return loadedUsersStream.filter(Objects::nonNull).filter(line->!line.isEmpty()).map(
                    line -> {
                        String[] userElements = line.split(DELIMITER);
                        return new DefaultUser(Integer.parseInt(userElements[ID_INDEX]), userElements[FIRST_NAME_INDEX], userElements[LAST_NAME_INDEX], userElements[EMAIL_INDEX], userElements[PASSWORD_INDEX]);
                    }).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.EMPTY_LIST;
        }
    }

    public static UserStorageService getInstance() {
        if (instance == null) {
            instance = new DefaultUserStorageService();
        }
        return instance;
    }
}
