package model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Book {

    private static SimpleDateFormat fmt = new SimpleDateFormat("dd:MM:yyyy HH:mm:ss");

    // attributes
    private Integer id;
    private String title;
    private Date withdrawDate;
    private Date deliveryDate;

    // constructor
    public Book(){
    }

    public Book(Integer id, String title, Date withdrawDate, Date deliveryDate) {
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

    public Date getWithdrawDate() {
        return withdrawDate;
    }

    public void setWithdrawDate(Date withdrawDate) {
        this.withdrawDate = withdrawDate;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
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
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", withdrawDate=" + fmt.format(withdrawDate) +
                ", deliveryDate=" + fmt.format(deliveryDate) +
                '}';
    }
}
