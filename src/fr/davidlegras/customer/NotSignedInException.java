package fr.davidlegras.customer;

public class NotSignedInException extends Exception {

    public NotSignedInException() {
        super();
    }

    public NotSignedInException(String message) {
        super(message);
    }
}
