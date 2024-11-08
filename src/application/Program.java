package application;

import controller.ManagerController;
import controller.StudentController;
import database.DB;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        while (true) {

            // UI
            UI.clearScreen();
            UI.ShowMainMenu();
            System.out.print("> enter a option: ");
            int option = in.nextInt();

            // directing to the chosen option
            switch (option) {
                case 1:
                    UI.clearScreen();
                    ManagerController.openEnvironmentManagerOfLogin(in);
                    break;
                case 2:
                    UI.clearScreen();
                    StudentController.openEnvironmentStudentOfLogin(in);
                    break;
                case 3:
                    DB.closeConnection();
                    System.out.println("closing system...");
                    return;
            }
        }
    }
}
