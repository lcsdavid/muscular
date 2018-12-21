package fr.davidlegras.customer;

public class NotConnectedException extends SigningException {

    public NotConnectedException() {
        super();
    }

    public NotConnectedException(String message) {
        super(message);
    }
}
