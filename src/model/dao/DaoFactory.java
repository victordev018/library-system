package model.dao;

import database.DB;
import model.dao.impl.StudentDaoJDBC;

public class DaoFactory {

    public static StudentDao createStudentDao(){
        return new StudentDaoJDBC(DB.getConnection());
    }
}
