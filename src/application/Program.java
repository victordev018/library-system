package application;

import model.entities.Book;
import model.entities.StudentUser;
import model.entities.User;

import java.util.Date;

public class Program {
    public static void main(String[] args) {

        // books
        Book b1 = new Book(1, "book 1", new Date(), new Date());
        Book b2 = new Book(2, "book 2", new Date(), new Date());

        // students
        StudentUser u1 = new StudentUser(1, "joao Victor", "jotadev", "joao@gmail.com", "1234");

        u1.insertIntoList(b1);
        u1.insertIntoList(b2);

        System.out.println(u1);
    }
}
