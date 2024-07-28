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
                Welcome student environment
                > 1 - Login                            \s
                > 2 - Register User                    \s
                > 3 - Back                             \s
                """
        );
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
}
