package application;

import java.util.Scanner;

public class Environments {

    // STUDENT

    public static void openEnvironmentStudent(Scanner in) {

        while (true) {
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
                    System.out.println("Register");
                    break;
                case 3:
                    UI.clearScreen();
                    System.out.println("closing system...");
                    return;
            }
        }
    }
}
