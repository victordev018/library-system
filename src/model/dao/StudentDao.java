package model.dao;

import model.entities.StudentUser;

public interface StudentDao {

    // contracts
    boolean insert(StudentUser obj);
}
