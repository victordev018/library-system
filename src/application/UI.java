package application;

import model.entities.StudentUser;

import java.util.Scanner;

public class UI {

    // main menu
    public static void ShowMainMenu(){
        System.out.println("""
                --------- Initial Menu ----------
                > 1 - Manager environment       \s
                > 2 - Student environment       \s
                > 3 - Close system              \s
                """);
    }

    // MANAGER

    public static void showManagerEnvironment(){
        System.out.println("""
                Welcome manager environment
                > 1 - Login                             \s
                > 2 - Register user                     \s
                > 3 - Back                              \s
                """);
    }

    public static void managerMenu(){
        System.out.println("""
                -------- Manager Menu ----------
                > 1 - Register withdraw
                > 2 - Show withdraw history
                > 3 - Register delivery
                > 4 - Back
                """);
    }

    // STUDENT

    public static void showStudentEnvironment(){
        System.out.println("""
                Welcome student environment
                > 1 - Login                            \s
                > 2 - Register user                    \s
                > 3 - Back                             \s
                """
        );
    }

    public static void studentMenu() {
        System.out.println("""
                -------- Student Menu ---------
                > 1 - My books
                > 2 - Request renewal
                > 3 - Back
                """);
    }

    public static void showStudentBooks(StudentUser user){
        System.out.println("> books in possession: ");
        user.getList().forEach(System.out::println);
    }

    // FOR ALL
    public static void screenErrorDataLogin(String messageError){
        System.out.println("\n" + messageError + "\n" +
                """
                > 1 - try again             \s
                > 2 - back                  \s
                """);
    }

    // clear screen
    public static void clearScreen() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    public static void pressEnterToGoBack(Scanner in) {
        System.out.print("> press enter to go back");
        in.nextLine();
        in.nextLine();
    }
}
