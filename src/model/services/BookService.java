package model.services;

import application.UI;
import model.dao.BookDao;
import model.dao.DaoFactory;
import model.entities.Book;
import model.entities.StudentUser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class BookService {

    public static List<Book> getStudentBooks(StudentUser user){
        BookDao bookDao = DaoFactory.createBookDao();
        return bookDao.findByStudentId(user);
    }

    public static void insertNewBook(Book book){
        BookDao bookDao = DaoFactory.createBookDao();
        bookDao.insert(book);
    }

    public static void readAndInsertBooks(Scanner in, int quantityBooks, StudentUser student){

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        for (int c = 1; c <= quantityBooks; c++){
            UI.clearScreen();
            Book currentBook = new Book();
            System.out.printf("-------- Book #%d --------\n", c);
            System.out.print("> Title: ");
            in.nextLine();
            currentBook.setTitle(in.nextLine());
            currentBook.setWithdrawDate(LocalDateTime.now());
            System.out.print("> standard or custom delivery date? (s/c): ");
            Character response = in.next().charAt(0);
            if (response.equals('c')){
                System.out.print("> enter delivery date: (dd/MM/yyyy HH:mm:ss): ");
                in.nextLine();
                currentBook.setDeliveryDate(LocalDateTime.parse(in.nextLine(), fmt));
            }
            else{
                LocalDateTime deliveryDate = currentBook.getWithdrawDate().plusDays(30);
                currentBook.setDeliveryDate(deliveryDate);
            }

            currentBook.setStudentId(student.getId());
            BookService.insertNewBook(currentBook);
        }
    }
}
