package fr.davidlegras;

public class NotConnectedException extends ConnectionException {

    public NotConnectedException() {
        super();
    }

    public NotConnectedException(String message) {
        super(message);
    }
}
