package model.dao.impl;

import database.DBException;
import model.dao.BookDao;
import model.entities.Book;
import model.entities.StudentUser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class BookDaoJDBC implements BookDao {

    private Connection conn;

    public BookDaoJDBC (Connection conn){
        this.conn = conn;
    }

    @Override
    public List<Book> findByStudentId(StudentUser user){

        PreparedStatement ps = null;
        ResultSet rs = null;

        try{

            ps = conn.prepareStatement(
                    "select * from book "
                       +"where StudentId = ?"
            );

            ps.setInt(1, user.getId());
            rs = ps.executeQuery();

            List<Book> list = new ArrayList<>();
            while (rs.next()){
                list.add(instantiateBook(rs));
            }
            return list;
        }
        catch (SQLException e){
            throw new DBException(e.getMessage());
        }
    }

    // instantiate Book
    private static Book instantiateBook(ResultSet rs) throws SQLException {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        Book book = new Book();
        book.setId(rs.getInt("Id"));
        book.setTitle(rs.getString("Title"));
        book.setWithdrawDate(LocalDateTime.parse(rs.getString("WithDrawDate"), fmt));
        book.setDeliveryDate(LocalDateTime.parse(rs.getString("DeliveryDate"), fmt));
        return book;
    }
}
