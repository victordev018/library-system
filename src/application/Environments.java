package application;

import model.entities.ManagerUser;
import model.entities.StudentUser;
import model.entities.User;
import model.services.AccountService;

import java.util.Scanner;

public class Environments {

    // STUDENT

    public static void openEnvironmentStudentOfLogin(Scanner in) {

        while (true) {
            UI.clearScreen();
            UI.showStudentEnvironment();
            System.out.print("> Enter a option: ");
            int option = in.nextInt();

            // directing to the chosen option
            switch (option) {
                case 1:
                    UI.clearScreen();
                    studentLogin(in);
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

    private static void studentLogin(Scanner in){
        UI.clearScreen();
        System.out.print(">       Student       <");
        StudentUser accountAccess = (StudentUser) tryToLogIn(new StudentUser(), in);
        if (accountAccess != null){
            System.out.println("Access granted");
        }
        else {
            System.out.println();
            UI.screenErrorDataLogin("! unregistered user !");
            System.out.print("> Enter a option: ");
            int option = in.nextInt();
            if (option == 1){
                studentLogin(in);
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

    private static User tryToLogIn(User user, Scanner in){

        UI.clearScreen();
        System.out.println("\n----- Login ------");
        System.out.print("> username: ");
        in.nextLine();
        user.setUserName(in.nextLine());
        System.out.print("> password: ");
        user.setPassword(in.nextLine());
        User obj2 = AccountService.searchLoginData(user);

        if (obj2 != null){

            // if correct password and user
            if (checkPasswordAndUserName(user, obj2)){
                return obj2;
            }
            // data incorrect
            UI.screenErrorDataLogin("! Email or password incorrect !");
            System.out.print("> Enter a option: ");
            int option = in.nextInt();
            if (option == 1) {
                tryToLogIn(user, in);
            }
        }
        return obj2;
    }

    private static Boolean checkPasswordAndUserName(User u1, User u2){
        return u1.getUserName().equals(u2.getUserName())
                && u1.getPassword().equals(u2.getPassword());
    }
}
