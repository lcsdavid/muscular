package fr.davidlegras.customer;

public class AlreadyConnectedException extends ConnectionException {

    public AlreadyConnectedException() {
        super();
    }

    public AlreadyConnectedException(String message) {
        super(message);
    }
}
