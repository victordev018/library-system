package model.dao.impl;

import database.DBException;
import model.dao.StudentDao;
import model.entities.StudentUser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentDaoJDBC implements StudentDao {

    Connection conn;

    public StudentDaoJDBC(Connection conn){
        this.conn = conn;
    }

    @Override
    public boolean insert(StudentUser obj) {

        PreparedStatement ps = null;

        try {

            ps = conn.prepareStatement(
                    "insert into student "
                       +"(FullName, UserName, Email, Password) "
                       +"values "
                       +"(?, ?, ?, ?)"
            );

            ps.setString(1, obj.getFullName());
            ps.setString(2, obj.getUserName());
            ps.setString(3, obj.getEmail());
            ps.setString(4, obj.getPassword());

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0){
                return true;
            }
            return false;
        }
        catch (SQLException e){
            throw new DBException(e.getMessage());
        }
    }
}
