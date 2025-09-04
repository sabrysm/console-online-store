package oop.project.onlineshop.menus.impl;


import oop.project.onlineshop.configs.ApplicationContext;
import oop.project.onlineshop.entities.impl.DefaultUser;
import oop.project.onlineshop.menus.Menu;
import oop.project.onlineshop.services.UserManagementService;
import oop.project.onlineshop.services.impl.DefaultUserManagementService;

import java.util.Scanner;

public class SignUpMenu implements Menu {

    private UserManagementService userManagementService;
    private ApplicationContext context;

    {
        userManagementService = DefaultUserManagementService.getInstance();
        context = ApplicationContext.getInstance();
    }

    @Override
    public void start() {
        DefaultUser newUser = new DefaultUser();
        Scanner sc = new Scanner(System.in);
        String userInput;
        printMenuHeader();
        while (newUser.getFirstName() == null) {
            System.out.print("Enter your first Name: ");
            userInput = sc.next();
            if (userInput.matches("[a-zA-Z]{3,}")) {
                newUser.setFirstName(userInput);
            }
        }

        while (newUser.getLastName() == null) {
            System.out.print("Enter your last Name: ");
            userInput = sc.next();
            if (userInput.matches("[a-zA-Z]{3,}")) {
                newUser.setLastName(userInput);
            }
        }

        while (newUser.getPassword() == null) {
            System.out.print("Enter your Password with at least 1 special character and 1 capital letter and minimum of 8 character: ");
            userInput = sc.next();
            if (userInput.matches("^(?=.*[A-Z])(?=.*[^a-zA-Z0-9]).{8,}$")) {
                newUser.setPassword(userInput);
            }
        }

        while (newUser.getEmail() == null) {
            System.out.print("Enter your Email Address: ");
            userInput = sc.next();
            if (userInput.matches("[\\w.]+@\\w+\\.\\w+")) {
                newUser.setEmail(userInput);
            }
        }

        UserManagementService userManagementService1 = DefaultUserManagementService.getInstance();
        System.out.println(userManagementService1.registerUser(newUser));
        Menu nextMenu = new MainMenu();
        context.setMainMenu(nextMenu);
        nextMenu.start();
        sc.close();
    }

    @Override
    public void printMenuHeader() {
        // <write your code here>
    }

}
