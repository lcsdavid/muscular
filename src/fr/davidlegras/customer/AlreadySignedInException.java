package fr.davidlegras.customer;

public class AlreadySignedInException extends SigningException {

    public AlreadySignedInException() {
        super();
    }

    public AlreadySignedInException(String message) {
        super(message);
    }
}
