package fr.davidlegras;

import java.util.Date;
import java.util.Objects;

public final class Book extends AbstractProduct {
    private String author;
    private Date release;

    public Book(String productTitle, double price, int fidelityPoints, String author, Date release) throws NegativePriceException {
        super(productTitle, price, fidelityPoints);
        this.author = author;
        this.release = release;
    }

    @Override
    public int hashCode() {
        return Objects.hash(productTitle(), price(), author, release);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (obj instanceof Book) {
            Book book = (Book) obj;
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
