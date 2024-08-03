package application;

import model.entities.Book;
import model.entities.ManagerUser;
import model.entities.StudentUser;
import model.entities.User;
import model.services.BookService;
import model.services.UserService;

import java.util.List;
import java.util.Scanner;

public class Environments {

    // MANAGER

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
                    registerAccount(new ManagerUser(), in);
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
        ManagerUser accountAccess = (ManagerUser) tryToLogIn(new ManagerUser(), in);
        if (accountAccess != null){
            UI.clearScreen();
            System.out.println("Access granted\n");
            managerMenu(accountAccess, in);
        }
    }

    public static void managerMenu(ManagerUser user, Scanner in){

        while (true){
            UI.managerMenu();
            System.out.print("> Enter a option: ");
            int option = in.nextInt();

            switch (option){
                case 1:
                    System.out.println("register withdraw");
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

    public static void makeWithDrawBook(Scanner in) {

        // student data
        StudentUser student = new StudentUser();
        System.out.println("> Enter student data: ");
        System.out.print("> username: ");
        student.setUserName(in.nextLine());

        StudentUser accountStudent = (StudentUser) UserService.searchLoginData(student);

        if (accountStudent != null){

            System.out.println("\n> how many books? ");
            int quantityBooks = in.nextInt();

            // insert the books
            for (int c = 0; c < quantityBooks; c++){
                //TODO -> insert the books
            }
        }
    }

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

        System.out.println("\n----- Enter the data ------");
        System.out.print("> full name: ");
        in.nextLine();
        user.setFullName(in.nextLine());
        System.out.print("> username: ");
        user.setUserName(in.nextLine());
        System.out.print("> email: ");
        user.setEmail(in.nextLine());
        System.out.print("> password: ");
        String password1 = in.nextLine();
        System.out.print("> confirm password: ");
        String password2 = in.nextLine();
        if (password1.equals(password2)){
            user.setPassword(password1);
        }
        else {
            System.out.println("password incorrect!");
            registerAccount(user, in);
        }

        if (UserService.createRegister(user)){
            System.out.println("successful registration!\nPress enter to go back");
            in.nextLine();
            return;
        }
        System.out.println("Failed registration!\nPress enter to go back");
        in.nextLine();
    }

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

    private static User tryToLogIn(ManagerUser user, Scanner in){

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
                managerLogin(in);
            }
        }
        else {
            System.out.println();
            UI.screenErrorDataLogin("! unregistered user !");
            System.out.print("> Enter a option: ");
            int option = in.nextInt();
            if (option == 1) {
                managerLogin(in);
            }
        }
        return null;
    }

    private static Boolean checkPasswordAndUserName(User u1, User u2){
        return u1.getUserName().equals(u2.getUserName())
                && u1.getPassword().equals(u2.getPassword());
    }
}
