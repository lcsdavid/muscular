package fr.davidlegras.customer;

public class NotEnoughFidelityPointsException extends Exception {

    public NotEnoughFidelityPointsException() {
        super();
    }

    public NotEnoughFidelityPointsException(String message) {
        super(message);
    }
}
