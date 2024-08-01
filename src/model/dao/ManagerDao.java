package model.dao;

import model.entities.User;

public interface ManagerDao {

    // contracts
    boolean insert(User obj);
}
