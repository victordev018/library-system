package application;

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
                    System.out.println("under construction!");
                    break;
                case 2:
                    UI.clearScreen();
                    Environments.openEnvironmentStudentOfLogin(in);
                    break;
                case 3:
                    DB.closeConnection();
                    System.out.println("closing system...");
                    return;
            }
        }
    }
}
