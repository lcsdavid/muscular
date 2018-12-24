package fr.davidlegras;

public interface NotConnectedCustomer extends CustomerState {

    @Override
    default void connect(final Platform platform, final Customer context, String login, String passwordHash) throws WrongCredentials {
        context.customerState(platform.connect(login, passwordHash));
    }

    @Override
    default void disconnect(final Platform unusedPlatform, final Customer unusedCustomer) throws NotConnectedException {
        throw new NotConnectedException();
    }
}
