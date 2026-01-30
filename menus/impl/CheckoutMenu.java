package oop.project.onlineshop.menus.impl;

import oop.project.onlineshop.configs.ApplicationContext;
import oop.project.onlineshop.entities.Order;
import oop.project.onlineshop.entities.impl.DefaultOrder;
import oop.project.onlineshop.menus.Menu;
import oop.project.onlineshop.services.OrderManagementService;
import oop.project.onlineshop.services.impl.DefaultOrderManagementService;

import java.util.Scanner;

public class CheckoutMenu implements Menu {

    private final ApplicationContext context;
    private final OrderManagementService orderManagementService;

    {
        context = ApplicationContext.getInstance();
        orderManagementService = DefaultOrderManagementService.getInstance();
    }

    @Override
    public void start() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            printMenuHeader();
            String creditCardNumber = sc.next();
            if (!createOrder(creditCardNumber)) {
                continue;
            }
            createOrder(creditCardNumber);
            break;
        }
        System.out.println(System.lineSeparator());
        System.out.println(Order.SUCCESS_MESSAGE);
        // clear session cart after successful checkout
        context.getSessionCart().clear();
        Menu nextMenu = new MainMenu();
        context.setMainMenu(nextMenu);
        nextMenu.start();
        sc.close();
    }

    private boolean createOrder(String creditCardNumber) {
        Order order = new DefaultOrder();
        if (!order.isCreditCardNumberValid(creditCardNumber)) {
            System.out.println("You entered invalid credit card number. Valid credit card should contain 16 digits. Please, try one more time.");
            return false;
        }
        order.setCreditCardNumber(creditCardNumber);
        order.setCustomerId(context.getLoggedInUser().getId());
        order.setProducts(context.getSessionCart().getProducts());
        orderManagementService.addOrder(order);
        return true;
    }


    @Override
    public void printMenuHeader() {
        System.out.println("\n*** CHECKOUT ***\n");
        System.out.println("Enter your credit card number without spaces and press enter if you confirm purchase");
    }

}
