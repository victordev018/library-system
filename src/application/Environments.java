package application;

import model.entities.Book;
import model.entities.ManagerUser;
import model.entities.StudentUser;
import model.entities.User;
import model.services.BookService;
import model.services.StudentService;
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
                    makeWithDrawBook(in);
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

        UI.clearScreen();

        // get student data
        StudentUser student = StudentService.getStudentWithUsername(in);

        // searching by account with the username
        StudentUser accountStudent = (StudentUser) UserService.searchLoginData(student);

        UI.clearScreen();
        if (accountStudent != null){
            System.out.print("\n> how many books? ");
            int quantityBooks = in.nextInt();

            // insert the books
            BookService.readAndInsertBooks(in, quantityBooks, accountStudent);

            UI.clearScreen();
            System.out.println("!!! books insertion completed !!!");
            UI.pressEnterToGoBack(in);
        }
        else{
            UI.screenErrorDataLogin("! Student not found !");
            System.out.print("\n> Enter a option: ");
            int option = in.nextInt();
            if (option == 1){
                makeWithDrawBook(in);
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
            UI.pressEnterToGoBack(in);
        }
        else{
            UI.clearScreen();
            System.out.println("\n! no books !");
            UI.pressEnterToGoBack(in);
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
