package fr.davidlegras;

public interface ConnectedCustomer extends CustomerState {

    @Override
    default void connect(final Platform platform, final Customer context, String login, String passwordHash) throws AlreadyConnectedException {
        throw new AlreadyConnectedException();
    }

    @Override
    default void disconnect(final Platform unused, final Customer context) {
        context.customerState(Visitor.getVisitor());
    }
}
