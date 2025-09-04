package oop.project.onlineshop;

import oop.project.onlineshop.menus.Menu;
import oop.project.onlineshop.menus.impl.MainMenu;

public class Main {

	public static final String EXIT_COMMAND = "exit";

	public static void main(String[] args) {
		Menu mainMenu = new MainMenu();
		mainMenu.start();
	}
	
}
