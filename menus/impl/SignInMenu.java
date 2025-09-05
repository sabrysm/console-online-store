package oop.project.onlineshop.menus.impl;

import oop.project.onlineshop.configs.ApplicationContext;
import oop.project.onlineshop.entities.impl.DefaultUser;
import oop.project.onlineshop.menus.Menu;
import oop.project.onlineshop.services.UserManagementService;
import oop.project.onlineshop.services.impl.DefaultUserManagementService;

import java.util.Scanner;

public class SignInMenu implements Menu {

    private ApplicationContext context;
    private UserManagementService userManagementService;

    {
        context = ApplicationContext.getInstance();
        userManagementService = DefaultUserManagementService.getInstance();
    }

    @Override
    public void start() {
        Scanner sc = new Scanner(System.in);
        String email = null, password = null;
        printMenuHeader();

        while (email == null) {
            System.out.print("Enter your Email Address: ");
            email = sc.next();
        }

        while (password == null) {
            System.out.print("Enter your Password: ");
            password = sc.next();
        }

        UserManagementService userManagementService1 = DefaultUserManagementService.getInstance();
        System.out.println(userManagementService1.authenticateUser(email, password));
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

