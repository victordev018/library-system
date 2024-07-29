package model.entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Book {

    private static final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    // attributes
    private Integer id;
    private String title;
    private LocalDateTime withdrawDate;
    private LocalDateTime deliveryDate;

    // constructor
    public Book(){
    }

    public Book(Integer id, String title, LocalDateTime withdrawDate, LocalDateTime deliveryDate) {
        this.id = id;
        this.title = title;
        this.withdrawDate = withdrawDate;
        this.deliveryDate = deliveryDate;
    }

    // getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getWithdrawDate() {
        return withdrawDate;
    }

    public void setWithdrawDate(LocalDateTime withdrawDate) {
        this.withdrawDate = withdrawDate;
    }

    public LocalDateTime getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDateTime deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    // hashCode and equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    // toString
    @Override
    public String toString() {
        return "-------------------------------------"+
               "\n| Title: " + getTitle()+
               "\n| WithDrawDate: " + getWithdrawDate().format(fmt)+
               "\n| DeliveryDate: " + getDeliveryDate().format(fmt)+
               "\n-------------------------------------";

    }
}
