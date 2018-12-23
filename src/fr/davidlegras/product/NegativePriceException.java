package fr.davidlegras.product;

public class NegativePriceException extends Exception {
    public NegativePriceException() {
        super();
    }

    public NegativePriceException(String message) {
        super(message);
    }

}
