package oop.project.onlineshop.menus.impl;

import oop.project.onlineshop.configs.ApplicationContext;
import oop.project.onlineshop.entities.Order;
import oop.project.onlineshop.menus.Menu;
import oop.project.onlineshop.services.OrderManagementService;
import oop.project.onlineshop.services.impl.DefaultOrderManagementService;

import java.util.Arrays;
import java.util.Scanner;

public class MyOrdersMenu implements Menu {

    private final ApplicationContext context;
    private final OrderManagementService orderManagementService;

    {
        context = ApplicationContext.getInstance();
        orderManagementService = DefaultOrderManagementService.getInstance();
    }

    @Override
    public void start() {
        Scanner sc = new Scanner(System.in);
        Menu nextMenu;
        String userInput;

        while (true) {
            if (context.getLoggedInUser() == null) {
                System.out.println("Please, log in or create new account to see list of your orders");
                nextMenu = new MainMenu();
                break;
            }
            System.out.println(System.lineSeparator());
            printMenuHeader();
            printPurchaseList(orderManagementService.getOrdersByUserId(context.getLoggedInUser().getId()));
            System.out.println("Your input: ");
            userInput = sc.next();

            if (userInput.equals(MainMenu.MENU_COMMAND)) {
                nextMenu = new MainMenu();
                break;
            }
        }
        sc.close();
        context.setMainMenu(nextMenu);
        nextMenu.start();
    }

    @Override
    public void printMenuHeader() {
        System.out.println("*** My Orders ***\n");
    }

    void printPurchaseList(Order[] purchases) {
        if (purchases == null || purchases.length == 0) {
            System.out.println("Unfortunately, you don’t have any orders yet. Navigate back to main menu " +
                    "to place a new order");
            return;
        }
        Arrays.stream(purchases).forEach(System.out::println);
    }

}
