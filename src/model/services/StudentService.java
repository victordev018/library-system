package model.services;

import model.entities.StudentUser;

import java.util.Scanner;

public class StudentService {

    public static StudentUser getStudentWithUsername(Scanner in){
        StudentUser student = new StudentUser();
        System.out.println("> Enter student data: ");
        in.nextLine();
        System.out.print("> username: ");
        student.setUserName(in.nextLine());
        return student;
    }
}
