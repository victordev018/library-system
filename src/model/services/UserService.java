package model.services;

import model.dao.DaoFactory;
import model.dao.ManagerDao;
import model.dao.StudentDao;
import model.entities.StudentUser;
import model.entities.User;

public class UserService {

    // register
    public static Boolean createRegister(User obj){

        if (obj instanceof StudentUser){
            StudentDao studentDao = DaoFactory.createStudentDao();
            return studentDao.insert(obj);
        }

        // case instance of ManagerUser
        ManagerDao managerDao = DaoFactory.createManagerDao();
        return managerDao.insert(obj);
    }

    // login
    public static User searchLoginData(User obj){

        if (obj instanceof  StudentUser){
            StudentDao studentDao = DaoFactory.createStudentDao();
            return studentDao.findByUserName(obj.getUserName());
        }

        // case instance of ManagerUser
        ManagerDao managerDao = DaoFactory.createManagerDao();
        return managerDao.findByUserName(obj.getUserName());
    }
}
