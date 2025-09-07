package oop.project.onlineshop.menus.impl;

import oop.project.onlineshop.configs.ApplicationContext;
import oop.project.onlineshop.menus.Menu;

public class SignOutMenu implements Menu {

    private ApplicationContext context;
    private static final String SIGNOUT_MESSAGE = "Have a nice day! Look forward to welcoming back!";

    {
        context = ApplicationContext.getInstance();
    }

    @Override
    public void start() {
        System.out.println(SIGNOUT_MESSAGE);
        context.setLoggedInUser(null);
        Menu nextMenu = new MainMenu();
        context.setMainMenu(nextMenu);
        nextMenu.start();
    }

    @Override
    public void printMenuHeader() {
        System.out.println("*** SIGN OUT ***\n");
    }

}
