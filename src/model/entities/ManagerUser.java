package model.entities;

public class ManagerUser extends User{

    // constructor
    public ManagerUser(){
    }

    public ManagerUser(Integer id, String fullName, String userName, String email, String password) {
        super(id, fullName, userName, email, password);
    }

    @Override
    public String toString(){
        return "{ " + this.getUserName() + ", " + this.getPassword() + "}";
    }
}
