package fr.davidlegras.customer;

public class WrongCredentials extends ConnectionException {
    public static boolean NO_HINTS = false;

    public WrongCredentials() {
        super();
    }

    public WrongCredentials(String message) {
        super(NO_HINTS ? "Incorrect user credentials..." : message);
    }
}
