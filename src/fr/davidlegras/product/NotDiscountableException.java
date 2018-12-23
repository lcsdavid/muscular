package fr.davidlegras.product;

public class NotDiscountableException extends Exception {
    public NotDiscountableException() {
        super();
    }

    public NotDiscountableException(String message) {
        super(message);
    }
}
