package application;

import database.DB;
import model.entities.Book;
import model.entities.StudentUser;
import model.entities.User;

import java.util.Date;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        while (true) {

            // UI
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
                    Environments.openEnvironmentStudent(in);
                    break;
                case 3:
                    System.out.println("closing system...");
                    return;
            }
            UI.clearScreen();
        }
    }
}
