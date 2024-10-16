package controller;

import application.UI;
import model.entities.ManagerUser;
import model.services.ManagerService;
import model.services.UserService;

import java.util.Scanner;

public class ManagerController {

    public static void openEnvironmentManagerOfLogin(Scanner in) {

        while (true){
            UI.clearScreen();
            UI.showManagerEnvironment();
            System.out.print("> Enter a option: ");
            int option = in.nextInt();

            // directing to the chosen option
            switch (option){
                case 1:
                    managerLogin(in);
                    break;
                case 2:
                    UI.clearScreen();
                    UserService.registerAccount(new ManagerUser(), in);
                    break;
                case 3:
                    UI.clearScreen();
                    return;
            }
        }
    }

    public static void managerLogin(Scanner in){
        UI.clearScreen();
        System.out.print(">       Manager       <");
        ManagerUser accountAccess = (ManagerUser) UserService.tryToLogIn(new ManagerUser(), in);
        if (accountAccess != null){
            UI.clearScreen();
            System.out.println("Access granted\n");
            managerMenu(in);
        }
    }

    public static void managerMenu(Scanner in){

        while (true){
            UI.managerMenu();
            System.out.print("> Enter a option: ");
            int option = in.nextInt();

            switch (option){
                case 1:
                    ManagerService.makeWithDrawBook(in);
                    break;
                case 2:
                    System.out.println("show withdraw history");
                    break;
                case 3:
                    System.out.println("register delivery");
                    break;
                case 4:
                    return;
            }
            UI.clearScreen();
        }
    }

}
