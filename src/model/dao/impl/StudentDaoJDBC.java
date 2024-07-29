package model.dao.impl;

import database.DB;
import database.DBException;
import model.dao.StudentDao;
import model.entities.StudentUser;
import model.entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentDaoJDBC implements StudentDao {

    Connection conn;

    public StudentDaoJDBC(Connection conn){
        this.conn = conn;
    }

    @Override
    public boolean insert(User obj) {

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
        finally {
            DB.closeStatement(ps);
        }
    }

    @Override
    public StudentUser findByUserName(String userName) {

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            ps = conn.prepareStatement(
                    "select * from student "
                       +"where userName = ?"
            );

            ps.setString(1, userName);
            rs = ps.executeQuery();

            if (rs.next()){
                return instantiateStudent(rs);
            }
            return null;
        }
        catch (SQLException e){
            throw new DBException(e.getMessage());
        }
        finally {
            DB.closeStatement(ps);
            DB.closeResultSet(rs);
        }
    }

    // instantiate StudentUser
    private static StudentUser instantiateStudent(ResultSet rs) throws SQLException {
        StudentUser obj = new StudentUser();
        obj.setId(rs.getInt("Id"));
        obj.setFullName(rs.getString("FullName"));
        obj.setUserName(rs.getString("UserName"));
        obj.setEmail(rs.getString("Email"));
        obj.setPassword(rs.getString("Password"));
        return obj;
    }
}
