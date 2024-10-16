package model.services;

import application.UI;
import model.dao.DaoFactory;
import model.dao.ManagerDao;
import model.dao.StudentDao;
import model.entities.StudentUser;
import model.entities.User;

import java.util.Scanner;

public class UserService {

    // register
    public static Boolean createRegister(User obj){

        if (obj instanceof StudentUser){
            StudentDao studentDao = DaoFactory.createStudentDao();
            return studentDao.insert(obj);
        }

        // case instance of ManagerUser
        ManagerDao managerDao = DaoFactory.createManagerDao();
        return managerDao.insert(obj);
    }

    // login
    public static User searchLoginData(User obj){

        if (obj instanceof  StudentUser){
            StudentDao studentDao = DaoFactory.createStudentDao();
            return studentDao.findByUserName(obj.getUserName());
        }

        // case instance of ManagerUser
        ManagerDao managerDao = DaoFactory.createManagerDao();
        return managerDao.findByUserName(obj.getUserName());
    }

    public static User tryToLogIn(User user, Scanner in){

        UI.clearScreen();
        System.out.println("\n----- Login ------");
        System.out.print("> username: ");
        in.nextLine();
        user.setUserName(in.nextLine());
        System.out.print("> password: ");
        user.setPassword(in.nextLine());
        User obj2 = UserService.searchLoginData(user);

        if (obj2 != null) {

            // if correct password and user
            if (checkPasswordAndUserName(user, obj2)) {
                return obj2;
            }
            // data incorrect
            UI.clearScreen();
            System.out.println("\n! Password incorrect !\n");
            UI.pressEnterToGoBack(in);
        }
        else {
            System.out.println("\n\n! unregistered user !\n");
            UI.pressEnterToGoBack(in);
        }
        return null;
    }

    private static Boolean checkPasswordAndUserName(User u1, User u2){
        return u1.getUserName().equals(u2.getUserName())
                && u1.getPassword().equals(u2.getPassword());
    }

    public static void registerAccount(User user, Scanner in){

        System.out.println("\n----- Enter the data ------");
        System.out.print("> full name: ");
        in.nextLine();
        user.setFullName(in.nextLine());
        System.out.print("> username: ");
        user.setUserName(in.nextLine());
        System.out.print("> email: ");
        user.setEmail(in.nextLine());
        System.out.print("> password: ");
        String password1 = in.nextLine();
        System.out.print("> confirm password: ");
        String password2 = in.nextLine();
        if (password1.equals(password2)){
            user.setPassword(password1);
        }
        else {
            System.out.println("password incorrect!");
            registerAccount(user, in);
        }

        if (UserService.createRegister(user)){
            System.out.println("successful registration!\nPress enter to go back");
            in.nextLine();
            return;
        }
        System.out.println("Failed registration!\nPress enter to go back");
        in.nextLine();
    }
}
