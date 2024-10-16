package model.dao;

import model.entities.StudentUser;
import model.entities.User;

import java.util.Optional;

public interface StudentDao {

    // contracts
    boolean insert(User obj);
    Optional<User> findByUserName(String userName);
}
