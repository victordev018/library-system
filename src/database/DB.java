package database;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DB {

    private static Connection conn;

    // get connection
    public static Connection getConnection(){

        try {
            Properties prop = loadProperties();
            String url = prop.getProperty("dburl");
            conn = DriverManager.getConnection(url, prop);
            return conn;
        }
        catch (SQLException e){
            throw new DBException(e.getMessage());
        }
    }

    // close connection
    public static void closeConnection() {

        if (conn != null){
            try {
                conn.close();
            }
            catch (SQLException e){
                throw new DBException(e.getMessage());
            }
        }
    }

    // load properties
    private static Properties loadProperties() {

        try (FileInputStream fs = new FileInputStream("db.properties")){
            Properties prop = new Properties();
            prop.load(fs);
            return prop;
        }
        catch (IOException e){
            throw new DBException(e.getMessage());
        }
    }

    // close Statement
    public static void closeStatement(Statement statement){

        if (statement != null){
            try {
                statement.close();
            }
            catch (SQLException e) {
                throw new DBException(e.getMessage());
            }
        }
    }

    // close ResultSet
    public static void closeResultSet(ResultSet rs){

        if (rs != null){
            try {
                rs.close();
            }
            catch (SQLException e) {
                throw new DBException(e.getMessage());
            }
        }
    }
}
