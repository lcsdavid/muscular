package fr.davidlegras.product;

import java.util.Date;

public class Book extends Category {
    private String author;
    private Date release;

    public Book(String author, Date release) {
        super("Livre");
        this.author = author;
        this.release = release;
    }

    @Override
    public String toString() {
        return super.toString() + "de " + author + " (" + release.getYear() + ')';
    }

}
