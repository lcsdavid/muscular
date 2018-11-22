package fr.davidlegras.customer;

public class NotSignedInException extends SigningException {

    public NotSignedInException() {
        super();
    }

    public NotSignedInException(String message) {
        super(message);
    }
}
