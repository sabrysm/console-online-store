package oop.project.onlineshop.menus.impl;

import oop.project.onlineshop.configs.ApplicationContext;
import oop.project.onlineshop.menus.Menu;

import java.util.Scanner;

public class SettingsMenu implements Menu {

    private static final String SETTINGS = "1. Change Password" + System.lineSeparator()
            + "2. Change Email";

    private final ApplicationContext context;

    {
        context = ApplicationContext.getInstance();
    }

    @Override
    public void start() {
        Scanner sc = new Scanner(System.in);
        Menu nextMenu = new MainMenu();
        String userInput;
        label:
        while (true) {
            if (context.getLoggedInUser() == null) {
                System.out.println("Please, log in or create new account to change your account settings");
                break;
            }
            System.out.println(SETTINGS);
            System.out.print("Select an Option: ");
            userInput = sc.next();
            switch (userInput) {
                case MainMenu.MENU_COMMAND:
                    break label;
                case "1":
                    System.out.print("Enter the new Password: ");
                    userInput = sc.next();
                    context.getLoggedInUser().setPassword(userInput);
                    System.out.println("Your password has been successfully changed");
                    break label;
                case "2":
                    System.out.print("Enter the new Email: ");
                    userInput = sc.next();
                    context.getLoggedInUser().setEmail(userInput);
                    System.out.println("Your email has been successfully changed");
                    break label;
                default:
                    System.out.println("Only 1, 2 are allowed. Try one more time");
                    break;
            }
        }
        context.setMainMenu(nextMenu);
        nextMenu.start();
        sc.close();
    }

    @Override
    public void printMenuHeader() {
        System.out.println("*** SETTINGS ***\n");
    }

}