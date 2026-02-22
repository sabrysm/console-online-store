package oop.project.onlineshop.menus.impl;

import oop.project.onlineshop.configs.ApplicationContext;
import oop.project.onlineshop.menus.Menu;
import oop.project.onlineshop.services.OrderManagementService;
import oop.project.onlineshop.services.UserManagementService;
import oop.project.onlineshop.services.impl.DefaultOrderManagementService;
import oop.project.onlineshop.services.impl.DefaultUserManagementService;

import java.util.stream.Collectors;

public class CustomerListMenu implements Menu {
    private static final String NO_CUSTOMERS_YET_MESSAGE = "No Customers yet!";
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
        System.out.println(System.lineSeparator());
        context.setMainMenu(nextMenu);
        nextMenu.start();
    }

    @Override
    public void printMenuHeader() {
        System.out.println("*** Customer List ***\n");
    }

    public void printCustomerList() {
        if (orderManagementService.getOrders().isEmpty()) System.out.println(NO_CUSTOMERS_YET_MESSAGE);
        orderManagementService.getOrders().stream().map(
            order -> userManagementService.getUserById(order.getCustomerId())
            ).collect(Collectors.toSet())
            .forEach(customer -> System.out.printf("\n- %s <%s>", customer.getFirstName(), customer.getEmail()));
    }

}
