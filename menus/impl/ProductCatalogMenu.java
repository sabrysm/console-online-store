package oop.project.onlineshop.menus.impl;

import oop.project.onlineshop.configs.ApplicationContext;
import oop.project.onlineshop.entities.Cart;
import oop.project.onlineshop.entities.Product;
import oop.project.onlineshop.entities.impl.DefaultCart;
import oop.project.onlineshop.entities.impl.DefaultProduct;
import oop.project.onlineshop.menus.Menu;
import oop.project.onlineshop.services.ProductManagementService;
import oop.project.onlineshop.services.UserManagementService;
import oop.project.onlineshop.services.impl.DefaultProductManagementService;
import oop.project.onlineshop.services.impl.DefaultUserManagementService;

import java.util.List;
import java.util.Scanner;

public class ProductCatalogMenu implements Menu {

    private static final String CHECKOUT_COMMAND = "checkout";
    private ApplicationContext context;
    private ProductManagementService productManagementService;
    private static final String PRODUCT_CATALOG_LIST_FOR_USER = "Enter product id to add it to the cart or ‘menu’ if you want to navigate back to the main menu";

    {
        context = ApplicationContext.getInstance();
        productManagementService = DefaultProductManagementService.getInstance();
    }

    public void printProductList(List<Product> products) {
        for (Product p : products) {
            System.out.println(p + System.lineSeparator());
        }
    }

    @Override
    public void start() {
        Scanner sc = new Scanner(System.in);
        Menu nextMenu;
        String userInput;
        List<Product> products = productManagementService.getProducts();
        Cart cart = new DefaultCart();

        while (true) {
            System.out.println(System.lineSeparator());
            printMenuHeader();
            printProductList(products);
            System.out.println(PRODUCT_CATALOG_LIST_FOR_USER);
            System.out.print("Your input: ");
            userInput = sc.next();
            System.out.println(System.lineSeparator());

            if (userInput.equals(MainMenu.MENU_COMMAND)) {
                nextMenu = new MainMenu();
                break;
            } else if (userInput.equals(CHECKOUT_COMMAND)) {
                if (cart.isEmpty()) {
                    System.out.println("Your cart is empty. Please, add product to cart first and then proceed with checkout");
                    continue;
                }
                context.setSessionCart(cart);
                nextMenu = new CheckoutMenu();
                break;
            }
            else if (productManagementService.getProductById(Integer.parseInt(userInput)) != null) {
                if (context.getLoggedInUser() == null) {
                    System.out.println("You are not logged in. Please, sign in or create new account");
                    nextMenu = new MainMenu();
                    break;
                }
                // Add Product to the Cart
                Product p = productManagementService.getProductById(Integer.parseInt(userInput));
                cart.addProduct(p);
                System.out.printf("Product %s has been added to your cart. If you want to add a new product - enter the product id. If you want to proceed with checkout - enter word ‘checkout’ to console\n", p.getProductName());
            } else {
                    System.out.println("Please, enter product ID if you want to add product to cart. Or enter ‘checkout’ if you want to proceed with checkout. Or enter ‘menu’ if you want to navigate back to the main menu." + System.lineSeparator());
            }
        }
        if (!context.getSessionCart().isEmpty())
            context.setSessionCart(cart);
        context.setMainMenu(nextMenu);
        nextMenu.start();
        sc.close();
    }

    @Override
    public void printMenuHeader() {
        System.out.println("*** PRODUCT CATALOG ***\n");
    }

}
