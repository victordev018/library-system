package model.services;

import model.dao.DaoFactory;
import model.dao.StudentDao;
import model.entities.StudentUser;
import model.entities.User;

public class AccountService {

    // register
    public static Boolean createRegister(User obj){

        if (obj instanceof StudentUser){
            StudentDao studentDao = DaoFactory.createStudentDao();
            return studentDao.insert((StudentUser) obj);
        }
        // TODO faça o mesmo com ManagerUserDao
        return false;
    }
}
