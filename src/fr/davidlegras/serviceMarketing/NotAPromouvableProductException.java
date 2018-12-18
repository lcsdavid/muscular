package fr.davidlegras.serviceMarketing;

public class NotAPromouvableProductException extends Exception {
    public NotAPromouvableProductException() {
        super();
    }

    public NotAPromouvableProductException(String message) {
        super(message);
    }
}
