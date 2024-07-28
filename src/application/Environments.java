package application;

import model.entities.ManagerUser;
import model.entities.StudentUser;
import model.entities.User;
import model.services.AccountService;

import java.util.Scanner;

public class Environments {

    // STUDENT

    public static void openEnvironmentStudent(Scanner in) {

        while (true) {
            UI.clearScreen();
            UI.showStudentEnvironment();
            System.out.print("> Enter a option: ");
            int option = in.nextInt();

            // directing to the chosen option
            switch (option) {
                case 1:
                    UI.clearScreen();
                    System.out.println("Login");
                    break;
                case 2:
                    UI.clearScreen();
                    registerAccount(new StudentUser(), in);
                    break;
                case 3:
                    UI.clearScreen();
                    System.out.println("closing system...");
                    return;
            }
        }
    }

    // FOR ALL
    private static void registerAccount(User user, Scanner in){

        User obj = user instanceof StudentUser? new StudentUser() : new ManagerUser();
        System.out.println("\n----- Enter the data ------");
        System.out.print("> full name: ");
        in.nextLine();
        obj.setFullName(in.nextLine());
        System.out.print("> username: ");
        obj.setUserName(in.nextLine());
        System.out.print("> email: ");
        obj.setEmail(in.nextLine());
        System.out.print("> password: ");
        String password1 = in.nextLine();
        System.out.print("> confirm password: ");
        String password2 = in.nextLine();
        if (password1.equals(password2)){
            obj.setPassword(password1);
        }
        else {
            System.out.println("password incorrect!");
            registerAccount(user, in);
        }

        if (AccountService.createRegister(obj)){
            System.out.println("successful registration!\nPress enter to go back");
            in.nextLine();
            return;
        }
        System.out.println("Failed registration!\nPress enter to go back");
        in.nextLine();
    }
}
