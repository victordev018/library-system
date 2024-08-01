package model.dao.impl;

import database.DBException;
import model.dao.ManagerDao;
import model.entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ManagerDaoJDBC implements ManagerDao {

    Connection conn;

    public ManagerDaoJDBC(Connection conn){
        this.conn = conn;
    }

    @Override
    public boolean insert(User obj) {

        PreparedStatement ps = null;

        try {

            ps = conn.prepareStatement(
                    "insert into manager "
                       +"(FullName, UserName, Email, Password) "
                       +"values (?, ?, ?, ?)"
            );

            ps.setString(1, obj.getFullName());
            ps.setString(2, obj.getUserName());
            ps.setString(3, obj.getEmail());
            ps.setString(4, obj.getPassword());

            int rowsAffected = ps.executeUpdate();

            return rowsAffected > 0;

        }
        catch (SQLException e){
            throw new DBException(e.getMessage());
        }
    }
}
