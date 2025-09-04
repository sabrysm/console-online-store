package oop.project.onlineshop.menus.impl;


import oop.project.onlineshop.configs.ApplicationContext;
import oop.project.onlineshop.menus.Menu;
import oop.project.onlineshop.services.UserManagementService;
import oop.project.onlineshop.services.impl.DefaultUserManagementService;

public class SignUpMenu implements Menu {

    private UserManagementService userManagementService;
    private ApplicationContext context;

    {
        userManagementService = DefaultUserManagementService.getInstance();
        context = ApplicationContext.getInstance();
    }

    @Override
    public void start() {
        // <write your code here>
    }

    @Override
    public void printMenuHeader() {
        // <write your code here>
    }

}
