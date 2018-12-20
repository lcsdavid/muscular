package fr.davidlegras.customer;

import javafx.application.Platform;

public interface CustomerState {

    int price(final Platform platform, final Customer context);

    void signIn(final Platform platform, final Customer context, String login, String passwordHash) throws AlreadySignedInException, WrongCredentials;

    void signOut(final Platform platform, final Customer context) throws NotSignedInException;
}
