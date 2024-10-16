package model.dao;

import model.entities.ManagerUser;
import model.entities.User;

import java.util.Optional;

public interface ManagerDao {

    // contracts
    boolean insert(User obj);
    Optional<User> findByUserName(String userName);
}
