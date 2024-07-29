package model.dao;

import model.entities.Book;
import model.entities.StudentUser;

import java.util.List;

public interface BookDao {

    // contracts
    List<Book> findByStudentId(StudentUser user);
}
