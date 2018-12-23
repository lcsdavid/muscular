package fr.davidlegras.product;

import java.util.Date;
import java.util.Objects;

public class Book extends AbstractProduct {
    private String title;
    private String author;
    private Date release;

    public Book(String productTitle, double price, String title, String author, Date release) {
        super(productTitle, price);
        this.title = title;
        this.author = author;
        this.release = release;
    }

    @Override
    public int hashCode() {
        return Objects.hash(productTitle(), price(), title, author, release);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (obj instanceof Book) {
            Book book = (Book)obj;
            if (!productTitle().equals(book.productTitle()))
                return false;
            if (Double.compare(price(), book.price()) != 0)
                return false;
            if (!author.equals(book.author))
                return false;
            return release.equals(book.release);
        }
        return false;
    }
}
