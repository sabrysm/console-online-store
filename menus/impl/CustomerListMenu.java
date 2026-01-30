package oop.project.onlineshop.menus.impl;

import oop.project.onlineshop.configs.ApplicationContext;
import oop.project.onlineshop.menus.Menu;
import oop.project.onlineshop.services.OrderManagementService;
import oop.project.onlineshop.services.UserManagementService;
import oop.project.onlineshop.services.impl.DefaultOrderManagementService;
import oop.project.onlineshop.services.impl.DefaultUserManagementService;

import java.util.Arrays;

public class CustomerListMenu implements Menu {

    private final ApplicationContext context;
    private final UserManagementService userManagementService;
    private final OrderManagementService orderManagementService;

    {
        context = ApplicationContext.getInstance();
        userManagementService = DefaultUserManagementService.getInstance();
        orderManagementService = DefaultOrderManagementService.getInstance();
    }

    @Override
    public void start() {
        Menu nextMenu = new MainMenu();
        printCustomerList();
        context.setMainMenu(nextMenu);
        nextMenu.start();
    }

    @Override
    public void printMenuHeader() {
        System.out.println("*** Customer List ***\n");
    }

    public void printCustomerList() {
        Arrays.stream(orderManagementService.getOrders()).map(
                order -> userManagementService.getUserById(order.getCustomerId())
                ).forEach(System.out::println);
    }

}
