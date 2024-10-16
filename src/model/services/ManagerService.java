package model.services;

import application.UI;
import model.entities.StudentUser;
import model.entities.User;

import java.util.Optional;
import java.util.Scanner;

public class ManagerService {

    public static void makeWithDrawBook(Scanner in) {

        UI.clearScreen();

        // get student data
        StudentUser student = StudentService.getStudentWithUsername(in);

        // searching by account with the username
        Optional<User> accountStudent = UserService.searchLoginData(student);

        UI.clearScreen();
        if (accountStudent.isPresent()){
            System.out.print("\n> how many books? ");
            int quantityBooks = in.nextInt();

            // insert the books
            BookService.readAndInsertBooks(in, quantityBooks, (StudentUser) accountStudent.get());

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

}
