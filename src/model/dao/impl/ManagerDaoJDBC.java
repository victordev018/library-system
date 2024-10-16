package model.dao.impl;

import database.DB;
import database.DBException;
import model.dao.ManagerDao;
import model.entities.ManagerUser;
import model.entities.StudentUser;
import model.entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

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
        finally {
            DB.closeStatement(ps);
        }
    }

    @Override
    public Optional<User> findByUserName(String userName) {

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            ps = conn.prepareStatement(
                    "select * from manager "
                       +"where UserName = ?"
            );

            ps.setString(1, userName);
            rs = ps.executeQuery();

            if (rs.next()){
                return Optional.of(instantiateManager(rs));
            }
            return Optional.empty();
        }
        catch (SQLException e){
            throw new DBException(e.getMessage());
        }
        finally {
            DB.closeStatement(ps);
            DB.closeResultSet(rs);
        }
    }

    // instantiate ManagerUer
    private static ManagerUser instantiateManager(ResultSet rs) throws SQLException {
        ManagerUser obj = new ManagerUser();
        obj.setId(rs.getInt("Id"));
        obj.setFullName(rs.getString("FullName"));
        obj.setUserName(rs.getString("UserName"));
        obj.setEmail(rs.getString("Email"));
        obj.setPassword(rs.getString("Password"));
        return obj;
    }
}
