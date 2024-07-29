package model.services;

import model.dao.BookDao;
import model.dao.DaoFactory;
import model.entities.Book;
import model.entities.StudentUser;

import java.util.List;

public class BookService {

    public static List<Book> getStudentBooks(StudentUser user){
        BookDao bookDao = DaoFactory.createBookDao();
        return bookDao.findByStudentId(user);
    }
}
