package model.services;

import model.dao.DaoFactory;
import model.dao.StudentDao;
import model.entities.ManagerUser;
import model.entities.StudentUser;
import model.entities.User;

public class AccountService {

    // register
    public static Boolean createRegister(User obj){

        if (obj instanceof StudentUser){
            StudentDao studentDao = DaoFactory.createStudentDao();
            return studentDao.insert(obj);
        }
        // TODO faça o mesmo com ManagerUserDao
        return false;
    }

    // login
    public static User searchLoginData(User obj){

        if (obj instanceof  StudentUser){
            StudentDao studentDao = DaoFactory.createStudentDao();
            return studentDao.findByUserName(obj.getUserName());
        }
        return null;    // TODO : impelemente o mesmo para o ManageUser
    }
}
