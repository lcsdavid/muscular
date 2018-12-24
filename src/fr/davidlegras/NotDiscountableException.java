package fr.davidlegras;

public class NotDiscountableException extends DiscountException {
    public NotDiscountableException() {
        super();
    }

    public NotDiscountableException(String message) {
        super(message);
    }
}
