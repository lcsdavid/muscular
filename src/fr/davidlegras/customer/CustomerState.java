package fr.davidlegras.customer;

public interface CustomerState {

    String name();

    int price(final Customer context);

    void signIn(final Customer context, final String login, final String passwordHash) throws AlreadySignedInException, WrongCredentials;

    void signOut(final Customer context) throws NotSignedInException;
}
