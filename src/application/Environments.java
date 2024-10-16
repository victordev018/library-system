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







    // FOR ALL





}
