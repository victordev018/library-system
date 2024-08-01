package model.dao;

import model.entities.ManagerUser;
import model.entities.User;

public interface ManagerDao {

    // contracts
    boolean insert(User obj);
    ManagerUser findByUserName(String userName);
}
