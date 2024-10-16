package controller;

import application.UI;
import model.entities.Book;
import model.entities.StudentUser;
import model.services.BookService;
import model.services.UserService;

import java.util.List;
import java.util.Scanner;

public class StudentController {

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
                    UserService.registerAccount(new StudentUser(), in);
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
        StudentUser accountAccess = (StudentUser) UserService.tryToLogIn(new StudentUser(), in);
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
}
