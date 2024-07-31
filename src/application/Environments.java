package application;

import model.entities.Book;
import model.entities.ManagerUser;
import model.entities.StudentUser;
import model.entities.User;
import model.services.BookService;
import model.services.UserService;

import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

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
                    return;
            }
        }
    }

    private static void studentLogin(Scanner in){
        UI.clearScreen();
        System.out.print(">       Student       <");
        StudentUser accountAccess = (StudentUser) tryToLogIn(new StudentUser(), in);
        if (accountAccess != null){
            UI.clearScreen();
            System.out.println("Access granted\n");
            studentMenu(accountAccess, in);
        }
    }

    private static void studentMenu(StudentUser user, Scanner in){

        while (true) {
            UI.studentMenu();
            System.out.print("> Enter a option: ");
            int option = in.nextInt();

            switch (option) {
                case 1:
                    showStudentBooks(user, in);
                    break;
                case 2:
                    System.out.println("request renewal");
                    break;
                case 3:
                    return;
            }
            UI.clearScreen();
        }
    }

    private static void showStudentBooks(StudentUser user, Scanner in){

        List<Book> bookList = BookService.getStudentBooks(user);
        if (!bookList.isEmpty()){
            user.getList().addAll(bookList);
            UI.clearScreen();
            UI.showStudentBooks(user);
            System.out.print("> press enter to go back");
            in.nextLine();
            in.nextLine();
        }
        else{
            UI.clearScreen();
            System.out.println("\n! no books !");
            System.out.print("> press enter to go back");
            in.nextLine();
            in.nextLine();
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

        if (UserService.createRegister(obj)){
            System.out.println("successful registration!\nPress enter to go back");
            in.nextLine();
            return;
        }
        System.out.println("Failed registration!\nPress enter to go back");
        in.nextLine();
    }

    // TODO -> crie uma sobrecaega deste método que receba um objeto do tipo MAnagerUser
    private static User tryToLogIn(StudentUser user, Scanner in){

        UI.clearScreen();
        System.out.println("\n----- Login ------");
        System.out.print("> username: ");
        in.nextLine();
        user.setUserName(in.nextLine());
        System.out.print("> password: ");
        user.setPassword(in.nextLine());
        User obj2 = UserService.searchLoginData(user);

        if (obj2 != null) {

            // if correct password and user
            if (checkPasswordAndUserName(user, obj2)) {
                return obj2;
            }
            // data incorrect
            UI.clearScreen();
            UI.screenErrorDataLogin("! Password incorrect !");
            System.out.print("> Enter a option: ");
            int option = in.nextInt();
            if (option != 2) {
                studentLogin(in);
            }
        }
        else {
            System.out.println();
            UI.screenErrorDataLogin("! unregistered user !");
            System.out.print("> Enter a option: ");
            int option = in.nextInt();
            if (option == 1) {
                studentLogin(in);
            }
        }
        return null;
    }

    private static Boolean checkPasswordAndUserName(User u1, User u2){
        return u1.getUserName().equals(u2.getUserName())
                && u1.getPassword().equals(u2.getPassword());
    }
}
