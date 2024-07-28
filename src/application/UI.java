package application;

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

    // STUDENT

    public static void showStudentEnvironment(){
        System.out.println("""
                > Welcome student environment
                > 1 - Login                            \s
                > 2 - Register User                    \s
                > 3 - Back                             \s
                """
        );
    }

    // clear screen
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
