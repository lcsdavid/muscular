package fr.davidlegras.customer;

public class AlreadyConnectedException extends SigningException {

    public AlreadyConnectedException() {
        super();
    }

    public AlreadyConnectedException(String message) {
        super(message);
    }
}
