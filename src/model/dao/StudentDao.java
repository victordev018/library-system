package model.dao;

import model.entities.StudentUser;
import model.entities.User;

public interface StudentDao {

    // contracts
    boolean insert(User obj);
    StudentUser findByUserName(String userName);
}
