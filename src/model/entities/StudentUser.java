package model.entities;

import java.util.ArrayList;
import java.util.List;

public class StudentUser extends User{

    // composition
    private List<Book> list = new ArrayList<>();

    // constructor
    public StudentUser(){
    }

    public StudentUser(Integer id , String fullName, String userName, String email, String password){
        super(id, fullName, userName, email, password);
    }

    // methods of the list
    public List<Book> getList(){
        return list;
    }

    public void insertIntoList(Book book){
        this.list.add(book);
    }

    // toString
    @Override
    public String toString() {
        return "User{" +
                "email='" + getEmail() + '\'' +
                ", id=" + getId() +
                ", fullName='" + getFullName() + '\'' +
                ", userName='" + getUserName() + '\'' +
                ", password='" + getPassword() + '\'' +
                ", list books='" + getList() + '\'' +
                '}';
    }
}
