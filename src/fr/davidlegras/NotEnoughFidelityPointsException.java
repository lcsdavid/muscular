package fr.davidlegras;

public class NotEnoughFidelityPointsException extends Exception {

    public NotEnoughFidelityPointsException() {
        super();
    }

    public NotEnoughFidelityPointsException(String message) {
        super(message);
    }
}
