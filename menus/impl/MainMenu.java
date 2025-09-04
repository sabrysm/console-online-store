package oop.project.onlineshop.menus.impl;

import oop.project.onlineshop.configs.ApplicationContext;
import oop.project.onlineshop.menus.Menu;

import java.util.Scanner;

public class MainMenu implements Menu {

    public static final String MENU_COMMAND = "menu";

    private static final String MAIN_MENU_TEXT_FOR_LOGGED_OUT_USER = "Please, enter number in console to proceed." + System.lineSeparator()
            + "1. Sign Up" + System.lineSeparator() + "2. Sign In"
            + System.lineSeparator() + "3. Product Catalog" + System.lineSeparator()
            + "4. My Orders" + System.lineSeparator() + "5. Settings" + System.lineSeparator() +
            "6. Customer List";

    private static final String MAIN_MENU_TEXT_FOR_LOGGED_IN_USER = "Please, enter number in console to proceed." + System.lineSeparator()
            + "1. Sign Up" + System.lineSeparator() + "2. Sign Out"
            + System.lineSeparator() + "3. Product Catalog" + System.lineSeparator()
            + "4. My Orders" + System.lineSeparator() + "5. Settings" + System.lineSeparator() +
            "6. Customer List";;

    private ApplicationContext context;

    {
        context = ApplicationContext.getInstance();
    }

    @Override
    public void start() {
        Scanner sc = new Scanner(System.in);
        printMenuHeader();
        while (true) {
            System.out.print("Your input: ");
            String userInput = sc.next();
            Menu newMenu = null;

            switch (userInput) {
                case "1":
                    newMenu = new SignUpMenu();
                    break;
                case "2":
                    if (context.getLoggedInUser() == null) {
                        newMenu = new SignInMenu();
                    } else {
                        newMenu = new SignOutMenu();
                    }
                    break;
                case "3":
                    newMenu = new ProductCatalogMenu();
                    break;
                case "4":
                    newMenu = new MyOrdersMenu();
                    break;
                case "5":
                    newMenu = new SettingsMenu();
                    break;
                case "6":
                    newMenu = new CustomerListMenu();
                    break;
                case "exit":
                    return;
                default:
                    System.out.println("Only 1, 2, 3, 4, 5, 6 is allowed. Try one more time.");
                    continue;
            }
            context.setMainMenu(newMenu);
            newMenu.start();
            sc.close();
            return;
        }
    }

    @Override
    public void printMenuHeader() {
        if (context.getLoggedInUser() == null) {
            System.out.println(MAIN_MENU_TEXT_FOR_LOGGED_OUT_USER);
        } else {
            System.out.println(MAIN_MENU_TEXT_FOR_LOGGED_IN_USER);
        }
    }

}
