package fr.davidlegras.customer;

import javafx.application.Platform;

public interface CustomerState {

    int price(final Platform platform, final Customer context);

    void connect(final Platform platform, final Customer context, String login, String passwordHash) throws AlreadyConnectedException, WrongCredentials;

    void disconnect(final Platform platform, final Customer context) throws NotConnectedException;
}
